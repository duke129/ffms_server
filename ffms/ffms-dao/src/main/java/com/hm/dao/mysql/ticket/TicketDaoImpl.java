/**
 * 
 */
package com.hm.dao.mysql.ticket;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hm.util.GenericUtil;
import com.hm.util.entity.Ticket;
import com.hm.util.model.TicketCardViewData;
import com.hm.util.model.TicketDetails;

/**
 * @author kiran
 *
 */
@Transactional
@Repository
public class TicketDaoImpl  implements TicketDo {
	
	private static final Logger logger = LoggerFactory.getLogger(TicketDaoImpl.class);
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	private static final String getCardViewData = "select t.idTicket , t.createdOn , c.firstName , c.mobileNumber , c.communicationAdderss "
			+ "from Ticket t inner join Customer c on c.id = t.idCustomer ";

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

	@Override
	public List<TicketDetails> getAllTickets() {
		TicketDetails tp = new TicketDetails();
		List<TicketDetails> ticketLists = new ArrayList<TicketDetails>();
		
		List<Object[]> tickets = entityManager.createNativeQuery("select * from Ticket").getResultList();
		
		for (Object[] object : tickets) {
			tp.setTicketId(((BigInteger) object[0]).longValue());
			tp.setTicketDescription((String) object[10]);
			tp.setCustomerId(((BigInteger) object[1]).longValue());
			ticketLists.add(tp);
		}
		
		return ticketLists;
		
	
	}

	@Override
	public List<TicketCardViewData> getTicketSummary() {
		
		List<Object[]> tickets = entityManager.createNativeQuery(getCardViewData).getResultList();
		
		return xTransformToCardViewList(tickets);
	}

	private List<TicketCardViewData> xTransformToCardViewList(List<Object[]> tickets) {

		List<TicketCardViewData> ticketLists = new ArrayList<TicketCardViewData>();
		
		TicketCardViewData cardViewData = new TicketCardViewData();
		
		for (Object[] object : tickets) {
			cardViewData.setTicketId(((BigInteger)object[0]).longValue());
			cardViewData.setTicketNumber(((BigInteger)object[0]).toString());
			cardViewData.setTicketCreationDate((Date)object[1]);
			cardViewData.setCommittedETR(GenericUtil.convertDateToStringFromate((Date)object[1]));
			cardViewData.setCustomerName((String)object[2]);
			cardViewData.setCustomerMobileNumber((String)object[3]);
			cardViewData.setCustomerAddress((String)object[4]);
			
			ticketLists.add(cardViewData);
			
		}
		
		return ticketLists;

	}
	
	
	
}
