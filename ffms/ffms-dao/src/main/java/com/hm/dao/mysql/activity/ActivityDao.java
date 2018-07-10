/**
 * 
 */
package com.hm.dao.mysql.activity;

import java.util.List;

import com.hm.util.model.ActivityVo;

/**
 * @author kiran
 *
 */
public interface ActivityDao {
	
	List<ActivityVo> getAllActivities();
	
	List<ActivityVo> getActivitiesForTicketType(Integer ticketTypeId);

	List<ActivityVo> getAllActivitiesByType(int type);

}
