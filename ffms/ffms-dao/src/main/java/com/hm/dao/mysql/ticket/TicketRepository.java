/**
 * 
 */
package com.hm.dao.mysql.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hm.util.entity.Status;
import com.hm.util.entity.Ticket;

/**
 * @author kiran
 *
 */
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	
	Long countByStatusBean(Status statusBean);

}
