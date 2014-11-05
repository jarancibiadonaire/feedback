package cl.uchile.dcc.feedback.mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cl.uchile.dcc.feedback.comparators.CommentComparator;
import cl.uchile.dcc.feedback.entities.Comment;
import cl.uchile.dcc.feedback.entities.Feed;
import cl.uchile.dcc.feedback.entities.FeedTag;
import cl.uchile.dcc.feedback.entities.Rating;
import cl.uchile.dcc.feedback.model.CommentVO;
import cl.uchile.dcc.feedback.model.FeedVO;
import cl.uchile.dcc.feedback.model.TagVO;

public class FeedMapper implements Mapper<Feed,FeedVO> {

	@Override
	public FeedVO getSummary(Feed entity) {
		return null;
	}

	@Override
	public FeedVO getBasic(Feed entity) {
		if(entity==null)
			return null;
		LocationMapper mapperL=new LocationMapper();
		CommentMapper mapperC=new CommentMapper();
		TagMapper mapperT=new TagMapper();
		
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
		vo.setLocation(mapperL.getBasic(entity.getLocation()));
		List<CommentVO> comments=new ArrayList<CommentVO>();
		for(Comment comment:entity.getComments())
			comments.add(mapperC.getBasic(comment));
		Collections.sort(comments, new CommentComparator());
		vo.setComments(comments);
		vo.setTotalComments(comments.size());
		int likes=0;
		int dislikes=0;
		for(Rating r:entity.getRates()){
			if(r.getScore()==1)
				likes++;
			else
				dislikes++;
		}			
		vo.setTotalLikes(likes);
		vo.setTotalDislikes(dislikes);
		List<String> tags=new ArrayList<String>();
		List<TagVO> tagsData=new ArrayList<TagVO>();
		List<TagVO> othersTagsData=new ArrayList<TagVO>();
		for(FeedTag ft:entity.getFeedTags()){
			tags.add(ft.getTag().getName());
			TagVO tag=mapperT.getBasic(ft.getTag());
			if(ft.getUser().getId()==entity.getUser().getId()){
				tag.setByOwner(true);
				tagsData.add(tag);
			}else{
				tag.setByOwner(false);
				othersTagsData.add(tag);
			}			
		}
		vo.setTags(tags);
		vo.setTagsData(tagsData);
		vo.setOthersTagsData(othersTagsData);
		return vo;
	}

	@Override
	public FeedVO getData(Feed entity) {
		return null;
	}

}
