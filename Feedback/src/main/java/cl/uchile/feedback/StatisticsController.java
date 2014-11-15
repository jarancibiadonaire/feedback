package cl.uchile.feedback;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cl.uchile.dcc.feedback.model.ConfigTagVO;
import cl.uchile.dcc.feedback.model.StatisticsDataVO;
import cl.uchile.dcc.feedback.model.StatisticsSummaryVO;
import cl.uchile.dcc.feedback.model.StatisticsTagsVO;
import cl.uchile.dcc.feedback.model.TagVO;
import cl.uchile.dcc.feedback.repositories.StatisticsRepository;
import cl.uchile.dcc.feedback.services.FeedServiceRemote;
import cl.uchile.dcc.feedback.services.StatisticsService;

@Controller
public class StatisticsController {	
	
	@Autowired
	FeedServiceRemote feedService;
	
	@Autowired
	StatisticsService statService;

	@RequestMapping(value = { "/statistics"}, method = RequestMethod.GET)
	public ModelAndView statistics() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	    List<TagVO> listTags=feedService.getAllTags();
		List<Integer> tagsFollow=feedService.getFollowingTags(username);
		ConfigTagVO configTag= new ConfigTagVO();
		if(tagsFollow!=null && tagsFollow.size()>0)
			configTag.setTagsIds(tagsFollow);
		StatisticsTagsVO tagsData=statService.getStatisticsTags();
		StatisticsSummaryVO stat=new StatisticsSummaryVO();
		stat.setTagsData(tagsData);
		ModelAndView model = new ModelAndView();
		model.addObject("listTags", listTags);
		model.addObject("configTag", configTag);
		model.addObject("statSummary", stat);
		model.setViewName("statistics");
		return model;
	}
	
	@RequestMapping(value = "/statistics/config_tag", method = RequestMethod.POST)
	public ModelAndView configTags(@ModelAttribute("configTag") ConfigTagVO config,
			BindingResult result) {
		if(config.getUsername().compareTo("")==0)
			return statistics();
		if(config.getTagsIds()==null)
			feedService.configFollowTags(config.getUsername(), new ArrayList<Integer>());
		else
			feedService.configFollowTags(config.getUsername(), config.getTagsIds());
		return statistics();
	}
}