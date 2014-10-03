package cl.uchile.dcc.feedback.comparators;

import java.util.Comparator;

import cl.uchile.dcc.feedback.model.FeedVO;

public class FeedComparator implements Comparator<FeedVO> {

	@Override
	public int compare(FeedVO o1, FeedVO o2) {
		return o2.getCreatedDate().compareTo(o1.getCreatedDate());
	}

}
