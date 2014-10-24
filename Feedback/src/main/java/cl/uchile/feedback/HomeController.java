package cl.uchile.feedback;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

@Controller
public class HomeController {

	@Autowired
	FeedServiceRemote feedService;	
	
	@RequestMapping(value = { "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView home(String var) {
		List<FeedVO> feeds=feedService.getAllFeeds();
		List<TagVO> listTags=feedService.getAllTags();
		String username=getUsername();
		ModelAndView model =  new ModelAndView();
		model.setViewName("home");
		model.addObject("feed", new FeedVO());
		model.addObject("comment", new CommentVO());
		model.addObject("rating", new RatingVO());
		model.addObject("feeds", feeds);
		model.addObject("listTags", listTags);
		model.addObject("var", var);
		return model;
	}
	
	@RequestMapping(value = "/publish_feed", method = RequestMethod.POST)
	public ModelAndView publishFeed(@ModelAttribute("feed") FeedVO feed,
			BindingResult result) {
		if(feed.getTitle()==null || feed.getUser()==null || 
				feed.getTitle().compareTo("")==0 || feed.getUser().compareTo("")==0)
			return home("error");
		Integer var=feedService.createFeed(feed);
		if(var==null)
			return home("error");
		else
			return home("success");
	}
	
	@RequestMapping(value = { "/home/feed/{id}" }, method = RequestMethod.GET)
	public @ResponseBody FeedVO getFeeds(@PathVariable Integer id) {
		FeedVO feed=feedService.findFeedById(id);
		return feed;
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
	private String getUsername(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {			
		  return ((UserDetails)principal).getUsername();
		} else {			
		  return principal.toString();
		}
	}
}
