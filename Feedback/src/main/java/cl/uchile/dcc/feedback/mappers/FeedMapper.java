package cl.uchile.dcc.feedback.mappers;

import cl.uchile.dcc.feedback.entities.Feed;
import cl.uchile.dcc.feedback.model.FeedVO;

public class FeedMapper implements Mapper<Feed,FeedVO> {

	@Override
	public FeedVO getSummary(Feed entity) {
		return null;
	}

	@Override
	public FeedVO getBasic(Feed entity) {
		if(entity==null)
			return null;
		FeedVO vo = new FeedVO();
		vo.setId(entity.getId());
		vo.setTitle(entity.getTitle());
		vo.setDescription(entity.getDescription());
		if(entity.getUser()!=null)
			vo.setUser(entity.getUser().getUserName());
		if(entity.getOrigin()!=null)
			vo.setOrigin(entity.getOrigin().getType());
		if(entity.getVisibility()!=null)
			vo.setVisibility(entity.getVisibility().getType());
		vo.setCreatedDate(entity.getCreatedDate());		
		return vo;
	}

	@Override
	public FeedVO getData(Feed entity) {
		return null;
	}

}
