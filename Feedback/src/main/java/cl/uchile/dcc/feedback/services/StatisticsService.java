package cl.uchile.dcc.feedback.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.model.IndexSummaryVO;
import cl.uchile.dcc.feedback.model.StatisticsDataVO;
import cl.uchile.dcc.feedback.model.StatisticsTagsVO;
import cl.uchile.dcc.feedback.repositories.CommentRepository;
import cl.uchile.dcc.feedback.repositories.FeedRepository;
import cl.uchile.dcc.feedback.repositories.StatisticsRepository;
import cl.uchile.dcc.feedback.repositories.TagRepository;
import cl.uchile.dcc.feedback.repositories.UserRepository;

@Component
public class StatisticsService implements StatisticsServiceRemote {
	
	@Autowired
	FeedRepository feedRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CommentRepository commentRepo;
	
	@Autowired
	TagRepository tagRepo;
	
	@Autowired
	StatisticsRepository statisticsRepo;
	
	@Override
	public IndexSummaryVO getIndexSummary(){
		Integer totalUsers=Integer.parseInt(userRepo.count()+"");
		Integer totalFeeds=Integer.parseInt(feedRepo.count()+"");
		Integer totalComments=Integer.parseInt(commentRepo.count()+"");
		Integer totalTags=Integer.parseInt(tagRepo.count()+"");
		IndexSummaryVO result=new IndexSummaryVO(totalUsers, totalFeeds, totalComments, totalTags);
		return result;
	}
	
	@Override
	public StatisticsTagsVO getStatisticsTags(){
		StatisticsTagsVO result=new StatisticsTagsVO();
		List<StatisticsDataVO> tagsFrec=statisticsRepo.getFreqTag(20);
		List<StatisticsDataVO> tagsCommented=statisticsRepo.getCommentTag(20);
		result.setTagsFrecuency(tagsFrec);
		result.setTagsCommented(tagsCommented);
		return result;
	}
	

}
