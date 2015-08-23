package cl.uchile.dcc.feedback.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.uchile.dcc.feedback.cassandra.repositories.UserTagSessionRepository;
import cl.uchile.dcc.feedback.comparators.TagComparator;
import cl.uchile.dcc.feedback.entities.Comment;
import cl.uchile.dcc.feedback.entities.Feed;
import cl.uchile.dcc.feedback.entities.FeedTag;
import cl.uchile.dcc.feedback.entities.Location;
import cl.uchile.dcc.feedback.entities.Rating;
import cl.uchile.dcc.feedback.entities.Tag;
import cl.uchile.dcc.feedback.entities.User;
import cl.uchile.dcc.feedback.entities.Visibility;
import cl.uchile.dcc.feedback.mappers.CommentMapper;
import cl.uchile.dcc.feedback.mappers.TagMapper;
import cl.uchile.dcc.feedback.model.CommentVO;
import cl.uchile.dcc.feedback.model.EdgeVO;
import cl.uchile.dcc.feedback.model.FeedGraphVO;
import cl.uchile.dcc.feedback.model.FeedVO;
import cl.uchile.dcc.feedback.model.NodeVO;
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
	@Autowired
	UserTagSessionRepository userTagRepo;
	
	@Override
	public Integer createFeed(FeedVO feedVO){
		if(feedVO==null)
			return null;		
		Feed feed=new Feed();
		feed.setTitle(feedVO.getTitle());
		feed.setDescription(feedVO.getDescription());
		if(feedVO.getLocation()!=null && feedVO.getLocation().getLat()!=null && feedVO.getLocation().getLng()!=null){		
			Location location=new Location();
			location.setLat(feedVO.getLocation().getLat());
			location.setLng(feedVO.getLocation().getLng());
			location.setAddress(feedVO.getLocation().getAddress());
			location.setComuna(comunaRepo.findByNameIgnoreCase(feedVO.getLocation().getComuna()));
			location.setCreatedDate(new Date());
			feed.setLocation(location);
		}
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
				t.setVisibility(visibilityRepo.findByType("Privado"));
				t.setUser(userRepo.findByUserName(feedVO.getUser()));
				tagRepo.save(t);
			}else{
				FeedTag oldFeedTag=feedTagRepo.findByFeedIdAndTagId(feed.getId(), t.getId());
				if(oldFeedTag!=null){
					continue;
				}
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
		List<FeedVO> feeds=feedRepo.getFeedsOpt();
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
		FeedVO f=feedRepo.getFeedOpt(id);
		return f;		
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
		List<Tag> tags=(List<Tag>) tagRepo.findAll();
		List<TagVO> list=new ArrayList<TagVO>();
		TagMapper mapper=new TagMapper();
		for(Tag t:tags)
			list.add(mapper.getBasic(t));
		Collections.sort(list, new TagComparator());
		return list;
	}
	@Override
	public List<Integer> getFollowingTags(String username){
		if(username==null || username.compareTo("")==0)
			return null;
		User u=userRepo.findByUserName(username);
		List<Integer> ids=userTagRepo.getTagsIdsByUserId(u.getId());
		return ids;
	}
	
	@Override
	public CommentVO findCommentById(Integer id){
		Comment c=commentRepo.findOne(id);
		CommentMapper mapper=new CommentMapper();
		CommentVO comment=mapper.getBasic(c);
		return comment;
	}
	@Override 
	public FeedGraphVO getFeedGraph(List<Integer> feedIds){
		FeedGraphVO graph=new FeedGraphVO();
		List<NodeVO> nodes=new ArrayList<NodeVO>();
		List<EdgeVO> edges=new ArrayList<EdgeVO>();
		Set<String> set = new HashSet<String>();
		List<Tag> tags=tagRepo.findByVisibilityId(2);
		for(Tag t:tags){
			//por cada tag se agrega un nodo
			NodeVO node=new NodeVO(t.getName(), -1);	
			if(feedIds!=null && feedIds.size()>0){
				List<FeedTag> feedTags=feedTagRepo.findByTagIdAndTagVisibilityIdAndFeedIdIn(t.getId(), 2,feedIds);
				node.setnFeeds(feedTags.size());
				if(feedTags!=null && feedTags.size()>0){
					List<String> list=new ArrayList<String>();
					for(FeedTag ft:feedTags){
						//si nos entregaron una lista de feeds, agregamos los feeds que estan ahi
						if(feedIds!=null){
							if(feedIds.contains(ft.getFeed().getId())){
								set.add(ft.getFeed().getTitle()+" ["+ft.getFeed().getId()+"]");
								list.add(ft.getFeed().getTitle()+" ["+ft.getFeed().getId()+"]");
							}
						}
					}
					edges.add(new EdgeVO(t.getName(), list));				
				}
			}
			nodes.add(node);
		}
		for(String s:set){
			Matcher m=Pattern.compile("\\[([^\\]]+)]").matcher(s);
			while(m.find()) {
				nodes.add(new NodeVO(s, Integer.parseInt(m.group(1)))); 
				break;
			}			
		}
		graph.setNodes(nodes);
		graph.setEdges(edges);		
		return graph;			
	}
	@Override
	public Integer addTags(FeedVO feedVO){
		if(feedVO==null)
			return null;
		User u=userRepo.findByUserName(feedVO.getUser());
		Feed feed=feedRepo.findOne(feedVO.getId());	
		for(String tag:feedVO.getTags()){
			Tag t=tagRepo.findByNameIgnoreCase(tag);
			if(t==null){
				t=new Tag();
				t.setName(tag);
				t.setVisibility(visibilityRepo.findByType("Privado"));
				tagRepo.save(t);
			}else{
				FeedTag oldFeedTag=feedTagRepo.findByFeedIdAndTagId(feed.getId(), t.getId());
				if(oldFeedTag!=null){
					continue;
				}
			}
			FeedTag ft=new FeedTag();
			ft.setFeed(feed);
			ft.setTag(t);
			ft.setUser(u);
			ft.setVisibility(visibilityRepo.findByType("Público"));
			ft.setCreatedDate(new Date());
			feedTagRepo.save(ft);
		}
		return feedVO.getId();
	}
	
	@Override
	public List<FeedVO> searchFeedsByText(String text){
		if(text==null || text.compareTo("")==0)
			return null;
		List<FeedVO> feeds=feedRepo.searchFeedsByTextOpt(text);
		return feeds;
	}
	
	@Override
	public List<Integer> getFeedsIdsRatingByUsername(String username){
		if(username==null || username.compareTo("")==0)
			return null;
		User u=userRepo.findByUserName(username);
		if(u==null)
			return null;
		List<Integer> ids=ratingRepo.findFeedsIdsRatingByUserId(u.getId());
		return ids;
	}
	
	@Override
	public List<Integer> getTagsByUsername(String username){
		if(username==null || username.compareTo("")==0)
			return null;
		User u=userRepo.findByUserName(username);
		if(u==null)
			return null;
		List<Integer> ft=feedTagRepo.findByUserId(u.getId());
		return ft;
	}
	
	@Override 
	public void configFollowTags(String username,List<Integer> ids){
		if(username==null || username.compareTo("")==0 || ids==null)
			return;
		User u=userRepo.findByUserName(username);
		if(u!=null){
			userTagRepo.addTagsToUser(ids, u.getId());
		}
	}
}
