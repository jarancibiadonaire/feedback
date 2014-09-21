package cl.uchile.dcc.feedback.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.entities.Role;
import cl.uchile.dcc.feedback.entities.Sex;
import cl.uchile.dcc.feedback.entities.User;
import cl.uchile.dcc.feedback.model.SexVO;
import cl.uchile.dcc.feedback.model.UserVO;
import cl.uchile.dcc.feedback.repositories.RoleRepository;
import cl.uchile.dcc.feedback.repositories.SexRepository;
import cl.uchile.dcc.feedback.repositories.UserRepository;

@Component
public class UserService implements UserServiceRemote{
	
	@Autowired
	UserRepository userRepo;	
	@Autowired
	SexRepository sexRepo;
	@Autowired
	RoleRepository roleRepo;
	
	@Override
	public Integer createUser(UserVO u){
		if(validateNewUser(u)){			
			User newUser=new User();
			newUser.setFirstName(u.getFirstName());
			newUser.setLastName(u.getLastName());
			if(u.getSex()!=0)
				newUser.setSex(sexRepo.findOne(u.getSex()));
			newUser.setUserName(u.getUserName());
			newUser.setPassword(u.getPassword());
			newUser.setEmail(u.getEmail());
			newUser.setEnabled(true);
			newUser.setCreatedDate(new Date());
			userRepo.save(newUser);
			Role role=roleRepo.findByRole("ROLE_USER");
			Set<Role> set=new HashSet<Role>();
			set.add(role);
			newUser.setRoles(set);
			userRepo.save(newUser);
			return newUser.getId();
		}else{
			return null;
		}
	}
	private boolean validateNewUser(UserVO u){
		if(u==null)
			return false;
		List<User> users=userRepo.findByUserNameOrEmail(u.getUserName(), u.getEmail());
		if(users==null || users.size()==0)
			return true;
		else
			return false;		
	}
	@Override
	public List<SexVO> getSexs(){
		Iterable<Sex> sexs=sexRepo.findAll();
		List<SexVO> vos=new ArrayList<SexVO>();
		for(Sex s:sexs)
			vos.add(new SexVO(s.getId(),s.getDescription()));
		return vos;
	}
	@Override
	public UserVO findUserByEmail(String email){
		User u=userRepo.findByEmail(email);
		System.out.println("email"+u);
		if(u!=null)
			return new UserVO();
		else
			return null;
	}
	@Override
	public UserVO findUserByUserName(String userName){
		User u=userRepo.findByUserName(userName);
		System.out.println("username"+u);
		if(u!=null)
			return new UserVO();
		else
			return null;
	}
}
