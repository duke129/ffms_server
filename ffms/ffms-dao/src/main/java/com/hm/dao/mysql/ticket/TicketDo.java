/**
 * 
 */
package com.hm.dao.mysql.ticket;

import java.util.List;

import com.hm.util.entity.Ticket;
import com.hm.util.model.TicketCardViewData;
import com.hm.util.model.TicketPojo;;

/**
 * @author kiran
 *
 */
public interface TicketDo {
	
	void addTicket(Ticket ticket);
	
	Ticket getTicketById(Long ticketId);
	
	List<TicketPojo> getAllTickets();
	
	List<TicketCardViewData> getTicketSummary();

}
