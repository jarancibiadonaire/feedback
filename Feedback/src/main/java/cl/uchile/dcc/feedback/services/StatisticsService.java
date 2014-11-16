package cl.uchile.dcc.feedback.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.model.IndexSummaryVO;
import cl.uchile.dcc.feedback.model.StatisticsDataVO;
import cl.uchile.dcc.feedback.model.StatisticsFeedsVO;
import cl.uchile.dcc.feedback.model.StatisticsSerieVO;
import cl.uchile.dcc.feedback.model.StatisticsTagsVO;
import cl.uchile.dcc.feedback.model.StatisticsUserVO;
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
		result.setTagsFrecuency(tagsFrec);
		return result;
	}
	
	@Override
	public StatisticsFeedsVO getStatisticsFeed(){
		StatisticsFeedsVO result=new StatisticsFeedsVO();
		List<StatisticsDataVO> commented=statisticsRepo.getCommentFeed(20);
		result.setCommentedFeed(commented);
		List<StatisticsSerieVO> rated=statisticsRepo.getRatedFeed(6);
		result.setRatedFeed(rated);
		return result;
	}
	
	@Override
	public StatisticsUserVO getStatisticsUser(){
		StatisticsUserVO result=new StatisticsUserVO();
		List<StatisticsDataVO> users=statisticsRepo.getFreqUser(8);
		result.setUserFreq(users);
		return result;
	}
	

}
