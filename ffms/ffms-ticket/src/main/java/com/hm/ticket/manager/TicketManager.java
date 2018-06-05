/**
 * 
 */
package com.hm.ticket.manager;

import java.util.List;

import com.hm.util.entity.Ticket;
import com.hm.util.model.TicketCardViewData;
import com.hm.util.model.TicketDetails;

/**
 * @author kiran
 *
 */
public interface TicketManager {
	
	boolean addTicket(Ticket ticket);
	
	List<TicketDetails> getAllTickets();
	
	List<TicketCardViewData> getTicketSummary();
	
	Ticket getTicketSummaryZoom(Long id);

}
