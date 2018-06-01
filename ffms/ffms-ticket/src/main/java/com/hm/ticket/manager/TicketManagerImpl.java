/**
 * 
 */
package com.hm.ticket.manager;

import org.springframework.stereotype.Service;

import com.hm.util.entity.Ticket;

/**
 * @author kiran
 *
 */
@Service
public class TicketManagerImpl implements TicketManager{

	/**
	 * @author kiran
	 * @param ticket
	 *  create ticket and ticket classification
	 */
	@Override
	public boolean addTicket(Ticket ticket) {
		return false;
	}

}
