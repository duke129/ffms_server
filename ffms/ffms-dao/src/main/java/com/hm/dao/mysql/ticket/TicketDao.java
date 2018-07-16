/**
 * 
 */
package com.hm.dao.mysql.ticket;

import java.util.List;

import com.hm.util.entity.Ticket;
import com.hm.util.model.BasicInfoUpdate;
import com.hm.util.model.DashBoardSummaryCountVo;
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
	
	List<TicketCardViewData> getTicketSummary(Integer status);
	
	int basicInfoUpdate(BasicInfoUpdate basicInfoUpdate);
	
	List<DashBoardSummaryCountVo> getDashBoardSummary();
	
	int closeTicket(String customerComments , Long ticketId);
	
	String getTicketCommentsByTicketId(Long ticketId);

}
