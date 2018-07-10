/**
 * 
 */
package com.hm.dao.mysql.ticketlog;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hm.util.entity.TicketActivityLog;


/**
 * @author kiran
 *
 */
public interface TicketActivityLogRepository  extends JpaRepository<TicketActivityLog, Long>{
	
	List<TicketActivityLog> findByTicketId(BigInteger ticketId);

}
