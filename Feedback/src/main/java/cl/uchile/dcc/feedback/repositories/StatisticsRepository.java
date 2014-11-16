package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.model.StatisticsDataVO;
import cl.uchile.dcc.feedback.model.StatisticsSerieVO;

@Component
public interface StatisticsRepository {

	List<StatisticsDataVO> getFreqTag(Integer limit);

	List<StatisticsDataVO> getCommentTag(Integer limit);

	List<StatisticsSerieVO> getRatedFeed(Integer limit);

	List<StatisticsDataVO> getCommentFeed(Integer limit);

	List<StatisticsDataVO> getFreqUser(Integer limit);

}
