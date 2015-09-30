package cl.uchile.feedback;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cl.uchile.dcc.feedback.model.ConfigTagVO;
import cl.uchile.dcc.feedback.model.TagVO;
import cl.uchile.dcc.feedback.model.UserVO;
import cl.uchile.dcc.feedback.services.FeedServiceRemote;
import cl.uchile.dcc.feedback.services.UserServiceRemote;

@Controller
public class ProfileController {	
	
	@Autowired
	UserServiceRemote userService;	
	@Autowired
	FeedServiceRemote feedService;

	@RequestMapping(value = { "/profile"}, method = RequestMethod.GET)
	public ModelAndView profile() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	    UserVO user=userService.findUserByUserName(username);
	    List<TagVO> listTags=feedService.getAllTags();
		List<Integer> tagsFollow=feedService.getFollowingTags(username);
		ConfigTagVO configTag= new ConfigTagVO();
		if(tagsFollow!=null && tagsFollow.size()>0)
			configTag.setTagsIds(tagsFollow);
		ModelAndView model = new ModelAndView();
		model.setViewName("profile");
		model.addObject("user", user);	
		model.addObject("listTags", listTags);
		model.addObject("configTag", configTag);
		return model;
	}
	@RequestMapping(value = { "/profile/update"}, method = RequestMethod.POST)
	public ModelAndView update() {
		ModelAndView model = new ModelAndView();
		model.setViewName("test");
		model.addObject("dateTest", new Date());		
		return null;
	}
}
