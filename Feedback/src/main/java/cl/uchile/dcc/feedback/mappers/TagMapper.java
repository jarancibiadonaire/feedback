package cl.uchile.dcc.feedback.mappers;

import cl.uchile.dcc.feedback.entities.Tag;
import cl.uchile.dcc.feedback.model.TagVO;

public class TagMapper implements Mapper<Tag, TagVO> {

	@Override
	public TagVO getSummary(Tag entity) {
		return null;
	}

	@Override
	public TagVO getBasic(Tag entity) {
		if(entity==null)
			return null;
		TagVO tag=new TagVO();
		tag.setId(entity.getId());
		tag.setName(entity.getName());
		tag.setDescription(entity.getDescription());
		tag.setVisibility(entity.getVisibility().getType());
		if(entity.getUser()!=null)
			tag.setUser(entity.getUser().getUserName());
		if(entity.getVisibility().getId()==2)
			tag.setRootTag(true);
		else
			tag.setRootTag(false);
		return tag;
	}

	@Override
	public TagVO getData(Tag entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
