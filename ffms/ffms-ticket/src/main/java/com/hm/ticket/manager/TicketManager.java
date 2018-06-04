/**
 * 
 */
package com.hm.ticket.manager;

import java.util.List;

import com.hm.util.entity.Ticket;
import com.hm.util.model.TicketCardViewData;
import com.hm.util.model.TicketPojo;

/**
 * @author kiran
 *
 */
public interface TicketManager {
	
	boolean addTicket(Ticket ticket);
	
	List<TicketPojo> getAllTickets();
	
	List<TicketCardViewData> getTicketSummary();
	
	Ticket getTicketSummaryZoom(Long id);

}
