/**
 * 
 */
package com.hm.dao.mysql.ticket;

import com.hm.util.entity.Ticket;

/**
 * @author kiran
 *
 */
public interface TicketDo {
	
	void addTicket(Ticket ticket);
	
	Ticket getTicketById(Long ticketId);

}
