/**
 * 
 */
package com.hm.dao.mysql.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hm.dao.mysql.ticket.TicketTypeRepository;
import com.hm.util.entity.TicketType;
import com.hm.util.model.ActivityVo;

/**
 * @author kiran
 *
 */
@Repository
public class ActivityDaoImpl implements ActivityDao{
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityDaoImpl.class);
	
	@Autowired
	TicketTypeRepository ticketTypeRepository;
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	private static Map<Integer, TicketType> ticketTypeCache = new HashMap<Integer, TicketType>();
	
	private static Map<Integer, List<ActivityVo>> ticketTypeActivitiesCache = new HashMap<Integer, List<ActivityVo>>();
	
	private static String GET_ALL_ACTIVITIES = "select a.id , a.activityName , attm.ticketTypeId from Activity a join ActivityTicketTypeMapping attm on a.id = attm.activityId ";
	private static String GET_ALL_ACTIVITIES_BY_TYPE = "select a.id , a.activityName , attm.ticketTypeId from Activity a "
			+ " join ActivityTicketTypeMapping attm on a.id = attm.activityId where attm.ticketTypeId=:type";

	  @PostConstruct
	  public void init(){
		  	    
		  List<TicketType> ticketTypes =  ticketTypeRepository.findAll();
		  
		  List<ActivityVo> activities = getAllActivities();
		  
		  ticketTypes.forEach(action -> {
			  
			  ticketTypeCache.put(action.getIdTicketType(), action);
			  
			  if(activities != null)
			  {
				  List<ActivityVo> filterActivities = activities.stream().filter(predicate -> 
					 predicate.getTicketType().equals(action.getIdTicketType()) ).collect(Collectors.toList()); 
				  
				  ticketTypeActivitiesCache.put(action.getIdTicketType(), filterActivities);
			  }
			  
		  });
		  
		  logger.info("ticketTypeActivitiesCache :: "+ticketTypeActivitiesCache);
		  
	  }

	@Override
	public List<ActivityVo> getAllActivities() {
		
		List<ActivityVo> activities =  new ArrayList<ActivityVo>();
		
		List<Object[]> result = entityManager.createNativeQuery(GET_ALL_ACTIVITIES).getResultList();
		
		if(result != null)
		{
			for (Object[] object : result) {
				
				ActivityVo activityVo = new ActivityVo();
				
				activityVo.setId(((Integer)object[0] ));
				activityVo.setActivityName((String)object[1]);
				activityVo.setActivityCode((String)object[1]);
				activityVo.setTicketType((Integer)object[2]);
				
				activities.add(activityVo);
				
			}
		}
		
		
		return activities;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityVo> getAllActivitiesByType(int type) {
		
		List<ActivityVo> activities =  new ArrayList<ActivityVo>();
		
		List<Object[]> result = entityManager.createNativeQuery(GET_ALL_ACTIVITIES_BY_TYPE).setParameter("type", type).getResultList();
		
		if(result != null)
		{
			for (Object[] object : result) {
				
				ActivityVo activityVo = new ActivityVo();
				
				activityVo.setId(((Integer)object[0] ));
				activityVo.setActivityName((String)object[1]);
				activityVo.setActivityCode((String)object[1]);
				activityVo.setTicketType((Integer)object[2]);
				
				activities.add(activityVo);
				
			}
		}
		
		
		return activities;
	}

	@Override
	public List<ActivityVo> getActivitiesForTicketType(Integer ticketTypeId) {
		
		if(ticketTypeId != null && ticketTypeId > 0)
		{
			return ticketTypeActivitiesCache.get(ticketTypeId);
		}
		
		return null;
	}

}
