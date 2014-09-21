package cl.uchile.feedback;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MicrobloggingController {	

	@RequestMapping(value = { "/microblogging"}, method = RequestMethod.GET)
	public ModelAndView microblogging() {
		ModelAndView model = new ModelAndView();
		model.setViewName("microblogging");
		return model;
	}
}