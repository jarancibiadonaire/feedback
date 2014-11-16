package cl.uchile.dcc.feedback.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cl.uchile.dcc.feedback.model.StatisticsDataVO;
import cl.uchile.dcc.feedback.model.StatisticsSerieVO;

@Component
public class StatisticsRepositoryImpl implements StatisticsRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<StatisticsDataVO> getFreqTag(Integer limit){
		if(limit==null || limit<1)
			limit=20;
		Query q=em.createNativeQuery("select t.tag_name as data,count(*) as value,t.visibility as control from feed_tag ft,tag t where ft.tag=t.tag_id group by data, control order by value desc limit "+limit);
		List<Object[]> l=q.getResultList();
		List<StatisticsDataVO> result= new ArrayList<StatisticsDataVO>();
		for(Object[] o:l){
			StatisticsDataVO s=new StatisticsDataVO();
			s.setData((String)o[0]);
			s.setValue(Integer.parseInt(o[1]+""));
			if((Integer)o[2]==2)
				s.setControl(true);
			else
				s.setControl(false);
			result.add(s);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<StatisticsDataVO> getCommentTag(Integer limit){
		if(limit==null || limit<1)
			limit=20;
		Query q=em.createNativeQuery("select t.tag_name as data,count(*) as value, t.visibility as control"
				+ " from feed_tag ft,tag t,comment c where ft.feed=c.feed and ft.tag=t.tag_id group by data,control "
				+ "order by value desc limit "+limit);
		List<Object[]> l=q.getResultList();
		List<StatisticsDataVO> result= new ArrayList<StatisticsDataVO>();
		for(Object[] o:l){
			StatisticsDataVO s=new StatisticsDataVO();
			s.setData((String)o[0]);
			s.setValue(Integer.parseInt(o[1]+""));
			if((Integer)o[2]==2)
				s.setControl(true);
			else
				s.setControl(false);
			result.add(s);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<StatisticsDataVO> getCommentFeed(Integer limit){
		if(limit==null || limit<1)
			limit=20;
		Query q=em.createNativeQuery("select feed as f,feed.title title,count(*) as c from comment,feed "
				+ "where feed.feed_id=comment.feed group by f,title order by c desc limit "+limit);
		List<Object[]> l=q.getResultList();
		List<StatisticsDataVO> result= new ArrayList<StatisticsDataVO>();
		for(Object[] o:l){
			StatisticsDataVO s=new StatisticsDataVO();
			s.setData(o[1]+" ["+o[0]+"]");
			s.setValue(Integer.parseInt(o[2]+""));
			result.add(s);
		}
		return result;
	}
	
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<StatisticsSerieVO> getRatedFeed(Integer limit){
		Query q=em.createNativeQuery("select feed as f,feed.title title,count(*) as c,count(case score when 1 then 1 else null end) as like,"
				+ "count(case score when -1 then 1 else null end) as dislike from rating,feed where feed.feed_id=rating.feed group by f,title "
				+ "order by c desc limit "+limit);
		List<Object[]> l=q.getResultList();
		List<StatisticsSerieVO> result= new ArrayList<StatisticsSerieVO>();
		for(Object[] o:l){
			StatisticsSerieVO serie=new StatisticsSerieVO();
			serie.setName(o[1]+" ["+o[0]+"]");			
			StatisticsDataVO like=new StatisticsDataVO();
			like.setData("me gusta");
			like.setValue(Integer.parseInt(o[3]+""));
			StatisticsDataVO dislike=new StatisticsDataVO();
			dislike.setData("no me gusta");
			dislike.setValue(Integer.parseInt(o[4]+""));
			List<StatisticsDataVO> values=new ArrayList<StatisticsDataVO>();
			values.add(like);
			values.add(dislike);
			serie.setValues(values);
			result.add(serie);
		}
		return result;
	}
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<StatisticsDataVO> getFreqUser(Integer limit){
		Query q=em.createNativeQuery("select u.username,count(*) c from feed f,user_table u where f.user_id=u.user_id "
				+ "group by u.username order by c desc limit "+limit);
		List<Object[]> l=q.getResultList();
		List<StatisticsDataVO> result= new ArrayList<StatisticsDataVO>();
		for(Object[] o:l){
			StatisticsDataVO user=new StatisticsDataVO();
			user.setData(o[0]+"");
			user.setValue(Integer.parseInt(o[1]+""));
			result.add(user);
		}
		return result;
	}

}
