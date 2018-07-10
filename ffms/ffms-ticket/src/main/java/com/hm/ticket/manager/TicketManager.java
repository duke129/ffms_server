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

/**
 * @author kiran
 *
 */
public interface TicketManager {
	
	boolean createTicket(ProspectCreation prospectCreation);
	
	List<TicketDetails> getTicketDetails(Long id);
	
	List<TicketCardViewData> getTicketSummary(Integer status);
	
	APIResponse getDashBoardSummary();
	
	APIResponse basicInfoUpdate(BasicInfoUpdate basicInfoUpdate);
	
}
