package cl.uchile.dcc.feedback.services;

import java.util.List;

import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.model.SexVO;
import cl.uchile.dcc.feedback.model.UserVO;

@Component
public interface UserServiceRemote {
	
	Integer createUser(UserVO u);

	List<SexVO> getSexs();

	UserVO findUserByEmail(String email);

	UserVO findUserByUserName(String userName);

}
