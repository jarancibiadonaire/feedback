package cl.uchile.dcc.feedback.comparators;

import java.util.Comparator;

import cl.uchile.dcc.feedback.model.CommentVO;

public class CommentComparator implements Comparator<CommentVO> {

	@Override
	public int compare(CommentVO o1, CommentVO o2) {
		return o2.getCreatedDate().compareTo(o1.getCreatedDate());
	}

}
