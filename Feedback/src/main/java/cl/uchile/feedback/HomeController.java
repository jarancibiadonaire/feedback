package cl.uchile.feedback;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cl.uchile.dcc.feedback.model.FeedVO;
import cl.uchile.dcc.feedback.services.FeedServiceRemote;

@Controller
public class HomeController {

	@Autowired
	FeedServiceRemote feedService;	
	
	@RequestMapping(value = { "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView home(String var) {
		List<FeedVO> feeds=feedService.getAllFeeds();
		//Collections.sort(feeds, new FeedComparator());
		ModelAndView model =  new ModelAndView("feed", "command", new FeedVO());
		model.setViewName("home");
		model.addObject("feeds", feeds);
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
}
