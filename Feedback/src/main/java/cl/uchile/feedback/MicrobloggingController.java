package cl.uchile.feedback;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cl.uchile.dcc.feedback.model.CommentVO;
import cl.uchile.dcc.feedback.model.FeedVO;
import cl.uchile.dcc.feedback.model.RatingVO;
import cl.uchile.dcc.feedback.model.TagVO;
import cl.uchile.dcc.feedback.services.FeedServiceRemote;
import cl.uchile.dcc.feedback.services.UserServiceRemote;

@Controller
public class MicrobloggingController {	
	@Autowired
	FeedServiceRemote feedService;
	@Autowired
	UserServiceRemote userService;

	@RequestMapping(value = { "/microblogging"}, method = RequestMethod.GET)
	public ModelAndView microblogging(String var) {
		List<TagVO> listTags=feedService.getAllTags();
		ModelAndView model = new ModelAndView();
		model.addObject("feed", new FeedVO());
		model.addObject("comment", new CommentVO());
		model.addObject("rating", new RatingVO());
		model.addObject("listTags", listTags);
		model.addObject("var", var);
		model.setViewName("microblogging");		
		return model;
	}
	
	@RequestMapping(value = "/microblogging/publish_feed", method = RequestMethod.POST)
	public ModelAndView publishFeed(@ModelAttribute("feed") FeedVO feed,
			BindingResult result) {
		if(feed.getTitle()==null || feed.getUser()==null || 
				feed.getTitle().compareTo("")==0 || feed.getUser().compareTo("")==0)
			return microblogging("error");
		Integer id=feedService.createFeed(feed);
		if(id!=null)
			return microblogging("success");
		else
			return microblogging("error");
	}
	
	@RequestMapping(value = { "/microblogging/feeds/{type}" }, method = RequestMethod.GET)
	public @ResponseBody List<FeedVO> getFeeds(@PathVariable String type) {
		List<FeedVO> feeds=feedService.getAllFeeds();
		return feeds;
	}
	
	@RequestMapping(value = "/microblogging/comment_feed", method = RequestMethod.POST)
	public ModelAndView commentFeed(@ModelAttribute("feed") CommentVO comment,
			BindingResult result) {
		if(comment.getFeed()==null || comment.getComment()==null || comment.getUser()==null)
			return microblogging("success");
		Integer id=feedService.commentFeed(comment);
		if(id!=null)
			return microblogging("success");
		else
			return microblogging("error");
	}
	
	@RequestMapping(value="/microblogging/vote" , method= RequestMethod.POST)
	public ModelAndView voteFeed(@ModelAttribute("rating") RatingVO rating,
			BindingResult result){
		if(rating.getUser()!=null && rating.getFeed()!=null && rating.getScore()!=null){
			feedService.voteFeed(rating);
		}			
		return microblogging(null);
	}
	
}
