/**
 * 
 */
package com.hm.dao.mysql.ticket;

import java.util.List;

import com.hm.util.entity.Ticket;
import com.hm.util.model.ProspectCreation;
import com.hm.util.model.TicketCardViewData;
import com.hm.util.model.TicketDetails;

/**
 * @author kiran
 *
 */
public interface TicketDao {
	
	boolean createTicket(ProspectCreation ticket);
	
	Ticket getTicketById(Long ticketId);
	
	List<TicketDetails> getTicketDetails(Long id);
	
	List<TicketCardViewData> getTicketSummary();

}
