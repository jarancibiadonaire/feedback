package cl.uchile.dcc.feedback.mappers;

import cl.uchile.dcc.feedback.entities.Comment;
import cl.uchile.dcc.feedback.model.CommentVO;

public class CommentMapper implements Mapper<Comment, CommentVO> {

	@Override
	public CommentVO getSummary(Comment entity) {
		return null;
	}

	@Override
	public CommentVO getBasic(Comment entity) {
		if(entity==null)
			return null;
		CommentVO c=new CommentVO();
		c.setComment(entity.getComment());
		c.setId(entity.getId());
		c.setCreatedDate(entity.getCreatedDate());
		if(entity.getFeed()!=null)
			c.setFeed(entity.getFeed().getId());
		c.setLevel(entity.getLevel());
		if(entity.getUser()!=null)
			c.setUser(entity.getUser().getUserName());
		return c;
	}

	@Override
	public CommentVO getData(Comment entity) {
		return null;
	}

}
