/**
 * 
 */
package com.hm.ticket.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.mysql.ticket.TicketDo;
import com.hm.dao.mysql.ticket.TicketRepository;
import com.hm.util.entity.Ticket;
import com.hm.util.model.TicketCardViewData;
import com.hm.util.model.TicketPojo;

/**
 * @author kiran
 *
 */
@Service
public class TicketManagerImpl implements TicketManager{
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketDo ticketDAO;

	/**
	 * @author kiran
	 * @param ticket
	 *  create ticket and ticket classification
	 */
	@Override
	public boolean addTicket(Ticket ticket) {
		return false;
	}

	/**
	 * 
	 */
	@Override
	public List<TicketPojo> getAllTickets() {
		
		//return ticketRepository.findAll();
		return ticketDAO.getAllTickets();
	}

	@Override
	public List<TicketCardViewData> getTicketSummary() {
		
		return ticketDAO.getTicketSummary();
	}

	@Override
	public Ticket getTicketSummaryZoom(Long id) {
		
		return ticketRepository.findById(id).get();
	}

}
