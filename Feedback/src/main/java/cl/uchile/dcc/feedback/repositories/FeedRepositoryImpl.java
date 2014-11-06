package cl.uchile.dcc.feedback.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.transaction.annotation.Transactional;

import cl.uchile.dcc.feedback.entities.Feed;
import cl.uchile.dcc.feedback.mappers.FeedMapper;
import cl.uchile.dcc.feedback.model.FeedVO;

public class FeedRepositoryImpl implements FeedRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<FeedVO> searchFeedsByTextOpt(String text) {
		if (text == null || text.compareTo("") == 0)
			return null;
		FullTextEntityManager fullTextEntityManager = Search
				.getFullTextEntityManager(em);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder().forEntity(Feed.class).get();
		org.apache.lucene.search.Query query = qb.keyword()
				.onFields("title", "description").matching(text)
				.createQuery();
		Query persistenceQuery = fullTextEntityManager
				.createFullTextQuery(query, Feed.class);
		List<Feed> resultList = persistenceQuery.getResultList();
		List<FeedVO> result=new ArrayList<FeedVO>();
		FeedMapper mapper=new FeedMapper();
		for(Feed f:resultList)
			result.add(mapper.getBasic(f));
		return result;
	}
	
	@Override
	@Transactional
	public List<FeedVO> getFeedsOpt(){
		TypedQuery<Feed> q=em.createQuery("select f from Feed f where f.visibility.id=2 order by f.createdDate desc", Feed.class);
		List<FeedVO> result=new ArrayList<FeedVO>();
		FeedMapper mapper=new FeedMapper();
		for(Feed f:q.getResultList())
			result.add(mapper.getBasic(f));
		return result;
	}
	
	@Override
	@Transactional
	public FeedVO getFeedOpt(Integer id){
		TypedQuery<Feed> q=em.createQuery("select f from Feed f where f.id=:id", Feed.class);
		q.setParameter("id", id);
		FeedMapper mapper=new FeedMapper();
		return mapper.getBasic(q.getSingleResult());
	}
	
	
}
