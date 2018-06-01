/**
 * 
 */
package com.hm.dao.mysql.ticket;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hm.util.entity.Ticket;

/**
 * @author kiran
 *
 */
@Transactional
@Repository
public class TicketDaoImpl implements TicketDo {
	
	private static final Logger logger = LoggerFactory.getLogger(TicketDaoImpl.class);
	
	@PersistenceContext	
	private EntityManager entityManager;

	/**
	 * @author kiran
	 * @param  ticket
	 *  add ticket in data base 
	 */
	@Override
	public void addTicket(Ticket ticket) {
		
		try {
			entityManager.persist(ticket);
		} catch (Exception e) {
			logger.error("Error occured while adding ticket " + e.getMessage());
		}
		
	}

	/**
	 * @author kiran
	 * @param ticketId
	 * get ticket by ticket id
	 */
	@Override
	public Ticket getTicketById(Long ticketId) {
		return entityManager.find(Ticket.class, ticketId);
	}
	
}
