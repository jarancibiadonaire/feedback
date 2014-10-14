package cl.uchile.feedback;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cl.uchile.dcc.feedback.model.FeedVO;
import cl.uchile.dcc.feedback.services.FeedServiceRemote;
import cl.uchile.dcc.feedback.services.UserServiceRemote;

@Controller
public class MicrobloggingController {	
	@Autowired
	FeedServiceRemote feedService;
	@Autowired
	UserServiceRemote userService;

	@RequestMapping(value = { "/microblogging"}, method = RequestMethod.GET)
	public ModelAndView microblogging() {
		ModelAndView model = new ModelAndView("feed", "command", new FeedVO());
		model.setViewName("microblogging");
		
		return model;
	}
	
	@RequestMapping(value = { "/microblogging/feeds/{type}" }, method = RequestMethod.GET)
	public @ResponseBody List<FeedVO> getFeeds(@PathVariable String type) {
		System.out.println(type);
		List<FeedVO> feeds=feedService.getAllFeeds();
		return feeds;
	}
}
