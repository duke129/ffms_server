/**
 * 
 */
package com.hm.dao.mysql.ticket;

import java.util.List;

import com.hm.util.entity.Ticket;
import com.hm.util.model.TicketCardViewData;
import com.hm.util.model.TicketDetails;

/**
 * @author kiran
 *
 */
public interface TicketDo {
	
	void addTicket(Ticket ticket);
	
	Ticket getTicketById(Long ticketId);
	
	List<TicketDetails> getAllTickets();
	
	List<TicketCardViewData> getTicketSummary();

}
