/**
 * 
 */
package com.hm.dao.mysql.ticketlog;

import java.util.List;

import com.hm.util.entity.TicketActivityLog;
import com.hm.util.model.TicketActivityLogVo;

/**
 * @author kiran
 *
 */
public interface TicketActivityLogDao {

	List<TicketActivityLog> findTicketActivityLogByTicketId(Long ticketId);
	
	List<TicketActivityLog> saveTicketActivityLog(List<TicketActivityLogVo> ticketActivityLogVos);
}
