package cl.uchile.feedback;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatisticsController {	

	@RequestMapping(value = { "/statistics"}, method = RequestMethod.GET)
	public ModelAndView microblogging() {
		ModelAndView model = new ModelAndView();
		model.setViewName("statistics");
		return model;
	}
}
