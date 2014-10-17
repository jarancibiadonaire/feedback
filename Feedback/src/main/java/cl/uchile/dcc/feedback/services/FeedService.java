package cl.uchile.dcc.feedback.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.comparators.CommentComparator;
import cl.uchile.dcc.feedback.entities.Comment;
import cl.uchile.dcc.feedback.entities.Feed;
import cl.uchile.dcc.feedback.entities.Location;
import cl.uchile.dcc.feedback.entities.User;
import cl.uchile.dcc.feedback.entities.Visibility;
import cl.uchile.dcc.feedback.mappers.CommentMapper;
import cl.uchile.dcc.feedback.mappers.FeedMapper;
import cl.uchile.dcc.feedback.mappers.LocationMapper;
import cl.uchile.dcc.feedback.model.CommentVO;
import cl.uchile.dcc.feedback.model.FeedVO;
import cl.uchile.dcc.feedback.model.VisibilityVO;
import cl.uchile.dcc.feedback.repositories.CommentRepository;
import cl.uchile.dcc.feedback.repositories.ComunaRepository;
import cl.uchile.dcc.feedback.repositories.FeedRepository;
import cl.uchile.dcc.feedback.repositories.LocationRepository;
import cl.uchile.dcc.feedback.repositories.OriginRepository;
import cl.uchile.dcc.feedback.repositories.UserRepository;
import cl.uchile.dcc.feedback.repositories.VisibilityRepository;

@Component
public class FeedService implements FeedServiceRemote {

	@Autowired
	FeedRepository feedRepo;
	@Autowired
	LocationRepository locationRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	OriginRepository originRepo;
	@Autowired
	VisibilityRepository visibilityRepo;
	@Autowired
	ComunaRepository comunaRepo;
	@Autowired 
	CommentRepository commentRepo;
	
	@Override
	public Integer createFeed(FeedVO feedVO){
		if(feedVO==null)
			return null;
		Location location=new Location();
		location.setLat(feedVO.getLocation().getLat());
		location.setLng(feedVO.getLocation().getLng());
		location.setAddress(feedVO.getLocation().getAddress());
		location.setComuna(comunaRepo.findByNameIgnoreCase(feedVO.getLocation().getComuna()));
		location.setCreatedDate(new Date());
		Feed feed=new Feed();
		feed.setTitle(feedVO.getTitle());
		feed.setDescription(feedVO.getDescription());
		feed.setLocation(location);
		feed.setUser(userRepo.findByUserName(feedVO.getUser()));
		feed.setVisibility(visibilityRepo.findByType(feedVO.getVisibility()));
		feed.setOrigin(originRepo.findByTypeIgnoreCase(feedVO.getOrigin()));
		feed.setCreatedDate(new Date());
		feedRepo.save(feed);
		return feed.getId();
	}
	@Override
	public List<VisibilityVO> getVisibilities(){
		Iterable<Visibility> vs=visibilityRepo.findAll();
		List<VisibilityVO> results=new ArrayList<VisibilityVO>();
		for(Visibility v:vs)
			results.add(new VisibilityVO(v.getId(), v.getType()));
		return results;
	}
	
	@Override
	public List<FeedVO> getAllFeeds(){
		List<FeedVO> feeds=new ArrayList<FeedVO>();
		FeedMapper mapper=new FeedMapper();
		LocationMapper mapperL=new LocationMapper();
		CommentMapper mapperC=new CommentMapper();
		Iterable<Feed> f=feedRepo.findByVisibilityIdOrderByCreatedDateDesc(2);
		for(Feed feed:f){
			FeedVO fvo=mapper.getBasic(feed);
			fvo.setLocation(mapperL.getBasic(feed.getLocation()));
			List<CommentVO> comments=new ArrayList<CommentVO>();
			for(Comment comment:feed.getComments())
				comments.add(mapperC.getBasic(comment));
			Collections.sort(comments, new CommentComparator());
			fvo.setComments(comments);
			fvo.setTotalComments(comments.size());
			feeds.add(fvo);
		}		
		return feeds;
	}
	
	@Override
	public Integer commentFeed(CommentVO commentVO){
		if(commentVO==null || commentVO.getFeed()==null || commentVO.getUser()==null)
			return null;
		User u=userRepo.findByUserName(commentVO.getUser());
		Feed f=feedRepo.findOne(commentVO.getFeed());
		if(u==null || f==null)
			return null;
		Comment c=new Comment();
		c.setComment(commentVO.getComment());
		c.setUser(u);
		c.setFeed(f);
		c.setCreatedDate(new Date());
		c.setLevel(commentVO.getLevel());
		commentRepo.save(c);
		return c.getId();
	}
}
