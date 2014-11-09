package cl.uchile.dcc.feedback.comparators;

import java.util.Comparator;

import cl.uchile.dcc.feedback.model.TagVO;

public class TagComparator implements Comparator<TagVO> {

	@Override
	public int compare(TagVO o1, TagVO o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
