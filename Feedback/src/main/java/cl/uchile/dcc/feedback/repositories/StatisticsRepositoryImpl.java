package cl.uchile.dcc.feedback.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cl.uchile.dcc.feedback.model.StatisticsDataVO;

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
		Query q=em.createNativeQuery("select t.tag_name as data,count(*) as value, t.visibility as control from feed_tag ft,tag t,comment c where ft.feed=c.feed and ft.tag=t.tag_id group by data,control order by value desc limit "+limit);
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

}
