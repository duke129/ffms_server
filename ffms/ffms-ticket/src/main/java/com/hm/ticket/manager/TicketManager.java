/**
 * 
 */
package com.hm.ticket.manager;

import java.util.List;


import com.hm.util.model.APIResponse;
import com.hm.util.model.BasicInfoUpdate;
import com.hm.util.model.ProspectCreation;
import com.hm.util.model.TicketCardViewData;
import com.hm.util.model.TicketDetails;
import com.hm.util.model.TicketFilter;

/**
 * @author kiran
 *
 */
public interface TicketManager {
	
	boolean createTicket(ProspectCreation prospectCreation);
	
	List<TicketDetails> getTicketDetails(Long id);
	
	List<TicketCardViewData> getTicketSummary(Integer status);
	
	APIResponse getDashBoardSummary(Integer ticketType);
	
	APIResponse basicInfoUpdate(BasicInfoUpdate basicInfoUpdate);
	
	APIResponse searchTicketWithFilter(TicketFilter ticketFilter);
	
}
