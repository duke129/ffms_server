/**
 * 
 */
package com.hm.ticket.manager;

import java.util.List;

import com.hm.util.entity.Ticket;
import com.hm.util.model.DashBoardSummaryCountVo;
import com.hm.util.model.ProspectCreation;
import com.hm.util.model.TicketCardViewData;
import com.hm.util.model.TicketDetails;

/**
 * @author kiran
 *
 */
public interface TicketManager {
	
	boolean createTicket(ProspectCreation prospectCreation);
	
	List<TicketDetails> getTicketDetails(Long id);
	
	List<TicketCardViewData> getTicketSummary();
	
	List<DashBoardSummaryCountVo> getDashBoardSummary();
	
}
