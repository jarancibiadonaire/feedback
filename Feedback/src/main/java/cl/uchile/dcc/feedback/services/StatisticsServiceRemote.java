package cl.uchile.dcc.feedback.services;

import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.model.IndexSummaryVO;
import cl.uchile.dcc.feedback.model.StatisticsTagsVO;

@Component
public interface StatisticsServiceRemote {

	IndexSummaryVO getIndexSummary();

	StatisticsTagsVO getStatisticsTags();

}
