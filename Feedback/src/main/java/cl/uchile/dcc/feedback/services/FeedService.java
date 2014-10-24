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
import cl.uchile.dcc.feedback.entities.FeedTag;
import cl.uchile.dcc.feedback.entities.Location;
import cl.uchile.dcc.feedback.entities.Rating;
import cl.uchile.dcc.feedback.entities.Tag;
import cl.uchile.dcc.feedback.entities.User;
import cl.uchile.dcc.feedback.entities.Visibility;
import cl.uchile.dcc.feedback.mappers.CommentMapper;
import cl.uchile.dcc.feedback.mappers.FeedMapper;
import cl.uchile.dcc.feedback.mappers.LocationMapper;
import cl.uchile.dcc.feedback.mappers.TagMapper;
import cl.uchile.dcc.feedback.model.CommentVO;
import cl.uchile.dcc.feedback.model.FeedVO;
import cl.uchile.dcc.feedback.model.RatingVO;
import cl.uchile.dcc.feedback.model.TagVO;
import cl.uchile.dcc.feedback.model.VisibilityVO;
import cl.uchile.dcc.feedback.repositories.CommentRepository;
import cl.uchile.dcc.feedback.repositories.ComunaRepository;
import cl.uchile.dcc.feedback.repositories.FeedRepository;
import cl.uchile.dcc.feedback.repositories.FeedTagRepository;
import cl.uchile.dcc.feedback.repositories.LocationRepository;
import cl.uchile.dcc.feedback.repositories.OriginRepository;
import cl.uchile.dcc.feedback.repositories.RatingRepository;
import cl.uchile.dcc.feedback.repositories.TagRepository;
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
	@Autowired
	RatingRepository ratingRepo;
	@Autowired
	FeedTagRepository feedTagRepo;
	@Autowired
	TagRepository tagRepo;
	
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
		for(String tag:feedVO.getTags()){
			Tag t=tagRepo.findByNameIgnoreCase(tag);
			if(t==null){
				t=new Tag();
				t.setName(tag);
				t.setVisibility(visibilityRepo.findByType("Público"));
				tagRepo.save(t);
			}
			FeedTag ft=new FeedTag();
			ft.setFeed(feed);
			ft.setTag(t);
			ft.setUser(feed.getUser());
			ft.setVisibility(visibilityRepo.findByType("Público"));
			ft.setCreatedDate(new Date());
			feedTagRepo.save(ft);
		}
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
			int likes=0;
			int dislikes=0;
			for(Rating r:feed.getRates()){
				if(r.getScore()==1)
					likes++;
				else
					dislikes++;
			}			
			fvo.setTotalLikes(likes);
			fvo.setTotalDislikes(dislikes);
			List<String> tags=new ArrayList<String>();
			for(FeedTag ft:feed.getFeedTags())
				tags.add(ft.getTag().getName());
			fvo.setTags(tags);
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
	@Override
	public FeedVO findFeedById(Integer id){
		if(id==null)
			return null;
		Feed f=feedRepo.findOne(id);
		if(f!=null){
			FeedMapper mapper=new FeedMapper();
			LocationMapper mapperL=new LocationMapper();
			CommentMapper mapperC=new CommentMapper();
			FeedVO fvo=mapper.getBasic(f);
			fvo.setLocation(mapperL.getBasic(f.getLocation()));
			List<CommentVO> comments=new ArrayList<CommentVO>();
			for(Comment comment:f.getComments())
				comments.add(mapperC.getBasic(comment));
			Collections.sort(comments, new CommentComparator());
			fvo.setComments(comments);
			fvo.setTotalComments(comments.size());
			return fvo;
		}else{
			return null;
		}
		
	}
	@Override
	public Integer voteFeed(RatingVO rating){
		if(rating==null || rating.getScore()==null || rating.getFeed()==null || rating.getUser()==null)
			return null;
		User u=userRepo.findByUserName(rating.getUser());
		Feed f=feedRepo.findOne(rating.getFeed());
		if(u==null || f==null)
			return null;
		Rating r=new Rating();
		r.setFeed(f);
		r.setScore(rating.getScore());
		r.setUser(u);
		r.setCreatedDate(new Date());
		ratingRepo.save(r);
		return r.getId();
	}
	@Override
	public List<TagVO> getAllTags(){
		List<Tag> tags=tagRepo.findByVisibilityId(2);
		List<TagVO> list=new ArrayList<TagVO>();
		TagMapper mapper=new TagMapper();
		for(Tag t:tags)
			list.add(mapper.getBasic(t));
		return list;
	}
}
