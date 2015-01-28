package cl.uchile.feedback;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cl.uchile.dcc.feedback.model.CommentVO;
import cl.uchile.dcc.feedback.model.ConfigTagVO;
import cl.uchile.dcc.feedback.model.FeedGraphVO;
import cl.uchile.dcc.feedback.model.FeedVO;
import cl.uchile.dcc.feedback.model.RatingVO;
import cl.uchile.dcc.feedback.model.TagVO;
import cl.uchile.dcc.feedback.services.FeedServiceRemote;

@Controller
public class HomeController {

	@Autowired
	FeedServiceRemote feedService;	
	
	@RequestMapping(value = { "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView home(String var) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
		List<TagVO> listTags=feedService.getAllTags();
		List<Integer> tagsFollow=feedService.getFollowingTags(username);
		ConfigTagVO configTag= new ConfigTagVO();
		if(tagsFollow!=null && tagsFollow.size()>0)
			configTag.setTagsIds(tagsFollow);
		ModelAndView model =  new ModelAndView();
		model.setViewName("home");
		model.addObject("feed", new FeedVO());
		model.addObject("comment", new CommentVO());
		model.addObject("rating", new RatingVO());
		model.addObject("listTags", listTags);
		model.addObject("configTag", configTag);
		return model;
	}
	
	@RequestMapping(value = "/publish_feed", method = RequestMethod.POST)
	public ModelAndView publishFeed(@ModelAttribute("feed") FeedVO feed,
			BindingResult result) {
		if(feed.getTitle()==null || feed.getUser()==null || 
				feed.getTitle().isEmpty() || feed.getUser().isEmpty())
			return home("error");
		Integer var=feedService.createFeed(feed);
		if(var==null)
			return home("error");
		else
			return home("success");
	}
	
	@RequestMapping(value = "/config_tag", method = RequestMethod.POST)
	public ModelAndView configTags(@ModelAttribute("configTag") ConfigTagVO config,
			BindingResult result) {
		if(config.getUsername().isEmpty())
			return home("error");
		if(config.getTagsIds()==null)
			feedService.configFollowTags(config.getUsername(), new ArrayList<Integer>());
		else
			feedService.configFollowTags(config.getUsername(), config.getTagsIds());
		return home("success");
	}
	
	
	@RequestMapping(value = "/home/comment_feed", method = RequestMethod.POST)
	public ModelAndView commentFeed(@ModelAttribute("feed") CommentVO comment,
			BindingResult result) {
		if(comment.getFeed()==null || comment.getComment()==null || comment.getUser()==null)
			return home("success");
		Integer id=feedService.commentFeed(comment);
		if(id!=null)
			return home("success");
		else
			return home("error");
	}
	
	@RequestMapping(value="/home/vote" , method= RequestMethod.POST)
	public ModelAndView voteFeed(@ModelAttribute("rating") RatingVO rating,
			BindingResult result){
		if(rating.getUser()!=null && rating.getFeed()!=null && rating.getScore()!=null){
			feedService.voteFeed(rating);
		}			
		return home(null);
	}
	
	@RequestMapping(value = { "/home/feeds/{type}" }, method = RequestMethod.GET)
	public @ResponseBody List<FeedVO> getFeeds(@PathVariable String type) {
		List<FeedVO> feeds=feedService.getAllFeeds();
		return feeds;
	}
		
	@RequestMapping(value = "/ajax/publish_feed", method = RequestMethod.POST)
	public @ResponseBody FeedVO publishFeedAJAX(@ModelAttribute("feed") FeedVO feed,
			BindingResult result) {
		if(feed.getTitle()==null || feed.getUser()==null || 
				feed.getTitle().isEmpty() || feed.getUser().isEmpty())
			return null;
		Integer var=feedService.createFeed(feed);
		if(var==null)
			return null;
		else
			return feedService.findFeedById(var);
	}
	
	@RequestMapping(value = "/ajax/comment_feed", method = RequestMethod.POST)
	public @ResponseBody CommentVO commentFeedAJAX(@ModelAttribute("feed") CommentVO comment,
			BindingResult result) {
		if(comment.getFeed()==null || comment.getComment()==null || 
				comment.getComment().isEmpty()|| comment.getUser()==null)
			return null;
		Integer id=feedService.commentFeed(comment);
		if(id!=null)
			return feedService.findCommentById(id);
		else
			return null;
	}
	
	@RequestMapping(value="/ajax/vote" , method= RequestMethod.POST)
	public @ResponseBody FeedVO voteFeedAJAX(@ModelAttribute("rating") RatingVO rating,
			BindingResult result){
		if(rating.getUser()!=null && rating.getFeed()!=null && rating.getScore()!=null){
			feedService.voteFeed(rating);
			return feedService.findFeedById(rating.getFeed());
		}else{
			return null;
		}
	}
	@RequestMapping(value="/ajax/feed_graph", method=RequestMethod.GET)
	public @ResponseBody FeedGraphVO getGraph(@RequestParam String feedIds){
		List<Integer> ids=null;
		if(feedIds!=null && !feedIds.isEmpty()){			
			ids=new ArrayList<Integer>();
			for(String s:feedIds.split(",")){
				try{
					ids.add(Integer.parseInt(s));
				}catch(Exception e){
					e.printStackTrace();
					return null;
				}				
			}
		}
		FeedGraphVO graph=feedService.getFeedGraph(ids);
		return graph;
	}
	
	@RequestMapping(value = "/ajax/add_tag", method = RequestMethod.POST)
	public @ResponseBody FeedVO addTagAJAX(@ModelAttribute("feed") FeedVO feed,
			BindingResult result) {
		if(feed.getId()==null || feed.getUser()==null || feed.getUser().isEmpty()
				|| feed.getTags()==null || feed.getTags().size()<1)
			return null;
		Integer id=feedService.addTags(feed);		
		return feedService.findFeedById(id);
	}
	
	@RequestMapping(value = "/ajax/search_feeds", method = RequestMethod.GET)
	public @ResponseBody List<FeedVO> searchFeedsAJAX(@RequestParam("q") String q) {
		if(q==null)
			return null;
		else
			return feedService.searchFeedsByText(q);
	}
	
	@RequestMapping(value = "/ajax/get_feeds_rated", method = RequestMethod.GET)
	public @ResponseBody List<Integer> getRatingFeedsAJAX(@RequestParam("username") String username) {
		if(username==null)
			return null;
		else
			return feedService.getFeedsIdsRatingByUsername(username);
	}
	
	@RequestMapping(value = "/ajax/get_following_tags", method = RequestMethod.GET)
	public @ResponseBody List<Integer> getFollowingTagsAJAX(@RequestParam("username") String username) {
		if(username==null)
			return null;
		else{
			return feedService.getFollowingTags(username);
		}
	}
	
	@RequestMapping(value = "/ajax/get_own_tags", method = RequestMethod.GET)
	public @ResponseBody List<Integer> getOwnTagsAJAX(@RequestParam("username") String username) {
		if(username==null)
			return null;
		else{
			return feedService.getTagsByUsername(username);
		}
	}
}
