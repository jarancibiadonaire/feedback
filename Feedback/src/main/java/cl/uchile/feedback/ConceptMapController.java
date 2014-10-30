package cl.uchile.feedback;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConceptMapController {	

	@RequestMapping(value = { "/conceptmap"}, method = RequestMethod.GET)
	public ModelAndView microblogging() {
		ModelAndView model = new ModelAndView();
		model.setViewName("conceptmap");
		model.addObject("dateTest", new Date());		
		return model;
	}
	@RequestMapping(value = { "/conceptmap/test"}, method = RequestMethod.GET)
	public ModelAndView test() {
		ModelAndView model = new ModelAndView();
		model.setViewName("test");
		model.addObject("dateTest", new Date());		
		return null;
	}
}
