package cl.uchile.feedback;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.uchile.dcc.feedback.model.VisibilityVO;
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
		ModelAndView model = new ModelAndView("visibility", "command", new VisibilityVO());
		List<VisibilityVO> vsl=feedService.getVisibilities();
		model.addObject("visibilities",vsl);
		model.setViewName("microblogging");
		return model;
	}
	
	@RequestMapping(value = { "/add_feed"}, method = RequestMethod.GET)
	public ModelAndView addFeed(@RequestParam(value="title") String title,
			@RequestParam(value="description") String description,@RequestParam(value="visibility") String visibility) {
		System.out.println(title+" "+description+" "+visibility);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("microblogging");
		return model;
	}
}
