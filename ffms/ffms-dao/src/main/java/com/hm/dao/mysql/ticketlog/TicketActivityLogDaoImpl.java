/**
 * 
 */
package com.hm.dao.mysql.ticketlog;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hm.dao.mysql.activity.ActivityRepository;
import com.hm.util.entity.TicketActivityLog;
import com.hm.util.model.TicketActivityLogVo;

/**
 * @author kiran
 *
 */
@Repository
public class TicketActivityLogDaoImpl implements TicketActivityLogDao{
	
	@Autowired
	TicketActivityLogRepository ticketActivityLogRepository;
	
	@Autowired
	ActivityRepository activityRepository;

	@Override
	public List<TicketActivityLog> findTicketActivityLogByTicketId(Long ticketId) {
		
		if(ticketId != null && ticketId > 0)
		{
			return ticketActivityLogRepository.findByTicketId(BigInteger.valueOf(ticketId));
		}
		
		return null;
	}

	@Override
	public List<TicketActivityLog> saveTicketActivityLog(List<TicketActivityLogVo> ticketActivityLogVos) {
		
		if(ticketActivityLogVos != null && !ticketActivityLogVos.isEmpty())
		{
			List<TicketActivityLog> activityLogs = new ArrayList<TicketActivityLog>();
			
			ticketActivityLogVos.forEach(ticketActivityLogVo -> {
				
				TicketActivityLog activityLog = new TicketActivityLog();
				
				activityLog.setTicketId(BigInteger.valueOf(ticketActivityLogVo.getTicketId()));
				activityLog.setActivityCreatedOn(new Date());
				activityLog.setStatus(ticketActivityLogVo.getActivityStatus());
				activityLog.setActivity(activityRepository.findById(Integer.valueOf(ticketActivityLogVo.getActivityId())).get());
				activityLog.setActivityDoneBy((BigInteger.valueOf(1)));
				
				activityLogs.add(activityLog);
				
			});
			
			
			return ticketActivityLogRepository.saveAll(activityLogs);
			
		}
		
		return null;
	}
	
	

}
