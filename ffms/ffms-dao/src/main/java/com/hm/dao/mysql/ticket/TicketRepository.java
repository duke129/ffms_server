/**
 * 
 */
package com.hm.dao.mysql.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hm.util.entity.Status;
import com.hm.util.entity.Ticket;
import com.hm.util.entity.TicketType;

/**
 * @author kiran
 *
 */
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	
	Long countByStatusBeanAndTicketType(Status statusBean,TicketType ticketType);

}
