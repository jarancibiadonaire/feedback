package cl.uchile.dcc.feedback.repositories;

import java.util.List;

import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.model.StatisticsDataVO;

@Component
public interface StatisticsRepository {

	List<StatisticsDataVO> getFreqTag(Integer limit);

	List<StatisticsDataVO> getCommentTag(Integer limit);

}
