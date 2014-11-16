package cl.uchile.dcc.feedback.services;

import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.model.IndexSummaryVO;
import cl.uchile.dcc.feedback.model.StatisticsFeedsVO;
import cl.uchile.dcc.feedback.model.StatisticsTagsVO;
import cl.uchile.dcc.feedback.model.StatisticsUserVO;

@Component
public interface StatisticsServiceRemote {

	IndexSummaryVO getIndexSummary();

	StatisticsTagsVO getStatisticsTags();

	StatisticsFeedsVO getStatisticsFeed();

	StatisticsUserVO getStatisticsUser();

}
