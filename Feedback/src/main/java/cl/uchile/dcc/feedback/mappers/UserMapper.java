package cl.uchile.dcc.feedback.mappers;

import cl.uchile.dcc.feedback.entities.User;
import cl.uchile.dcc.feedback.model.UserVO;

public class UserMapper implements Mapper<User,UserVO> {

	@Override
	public UserVO getSummary(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO getBasic(User entity) {
		if(entity==null)
			return null;
		UserVO u=new UserVO();
		u.setFirstName(entity.getFirstName());
		u.setLastName(entity.getLastName());
		u.setEmail(entity.getEmail());
		u.setCreatedDate(entity.getCreatedDate());
		u.setUserName(entity.getUserName());
		if(entity.getSex()!=null)
			u.setSexName(entity.getSex().getDescription());
		return u;
	}

	@Override
	public UserVO getData(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
