package cl.uchile.dcc.feedback.cassandra.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.cassandra.entities.UserTagSession;

@Component
public class UserTagSessionRepository {
	@Autowired
	@Qualifier(value="cassandraTemplate")
	public CassandraOperations cassandraOperations;
	
	public List<Integer> getTagsIdsByUserId(Integer id){
		if(id==null)
			return null;
		String query="select tag_id from user_tag_session where user_id="+id;
		List<UserTagSession> list=this.cassandraOperations.select(query, UserTagSession.class);
		List<Integer> result=new ArrayList<Integer>();
		for(UserTagSession s:list)
			result.add(s.getTagId());
		return result;
	}
	
	public void addTagsToUser(List<Integer> tagsIds,Integer userId){
		if(tagsIds==null || tagsIds.size()<1 || userId==null)
			return;
		List<UserTagSession> list=new ArrayList<UserTagSession>();
		for(Integer i:tagsIds){
			UserTagSession u=new UserTagSession();
			u.setUserId(userId);
			u.setTagId(i);
			u.setDate(new Date());
			list.add(u);
		}
		this.cassandraOperations.insert(list);
	}	
}
