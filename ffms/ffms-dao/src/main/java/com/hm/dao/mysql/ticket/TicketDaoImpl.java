/**
 * 
 */
package com.hm.dao.mysql.ticket;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hm.dao.mysql.area.AreaRepository;
import com.hm.dao.mysql.branch.BranchRepository;
import com.hm.dao.mysql.city.CityRepository;
import com.hm.dao.mysql.customer.CustomerRepository;
import com.hm.dao.mysql.status.StatusRepository;
import com.hm.dao.mysql.user.UserRepository;
import com.hm.util.GenericUtil;
import com.hm.util.entity.Customer;
import com.hm.util.entity.Status;
import com.hm.util.entity.Ticket;
import com.hm.util.entity.User;
import com.hm.util.model.CustomerVo;
import com.hm.util.model.ProspectCreation;
import com.hm.util.model.TicketCardViewData;
import com.hm.util.model.TicketDetails;

/**
 * @author kiran
 *
 */
@Transactional
@Repository
public class TicketDaoImpl  implements TicketDao {
	
	private static final Logger logger = LoggerFactory.getLogger(TicketDaoImpl.class);
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	BranchRepository branchRepository;

	@Autowired
	AreaRepository areaRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TicketTypeRepository ticketTypeRepository;
	
	private static final String GET_CARD_VIEW_DATA = "select t.idTicket , t.createdOn , c.firstName , c.mobileNumber , c.communicationAdderss "
			+ "from Ticket t inner join Customer c on c.id = t.idCustomer ";
	
	private static final String TICKET_DETAILS_BASE_QUERY = "select t.idTicket , t.ticketDescription , t.assignedTo , u.firstName as assigneedToName , t.status , t.createdOn , t.ticketTypeId , tt.ticketType ," + 
			" a.idAsset , a.assetDescription , at.idAssetType , at.assetTypeDescription , a.installationLat ," + 
			" a.installationLong , c.id , c.title , c.firstName ,c.lastName ,c.mobileNumber , " + 
			" c.alternativeMobileNo, c.emailId,c.communicationAdderss from Ticket t join TicketType tt on t.ticketTypeId = tt.idTicketType left join Asset a on t.idAsset = a.idAsset " + 
			" left join AssetType at on at.idAssetType = a.idAssetType join Customer c on t.idCustomer = c.id join User u on u.idUser = t.assignedTo where t.idTicket = :tickekId ";

	/**
	 * @author kiran
	 * @param  ticket
	 *  add ticket in data base 
	 */
	@Override
	public boolean createTicket(ProspectCreation prospectCreation) {
		
		try {

			Customer customer = new Customer();

			Ticket ticket = new Ticket();

			if (prospectCreation.getCustomer() != null) {
				
				customer = convertCustomerVoToCustomer(prospectCreation.getCustomer());

				Customer savedCustomer = customerRepository.save(customer);

				ticket.setCustomer(savedCustomer);
				ticket.setStatusBean(savedCustomer.getStatusBean());
				ticket.setTicketDescription("testing " + savedCustomer.getId());
				ticket.setUser1(savedCustomer.getCreatedByUserId());
				ticket.setUser2(savedCustomer.getCreatedByUserId());
				ticket.setUser3(savedCustomer.getCreatedByUserId());
				ticket.setTicketType(ticketTypeRepository.findById(1).get());
				ticket.setCreatedOn(new Date());
				ticket.setModifiedOn(new Date());

				ticketRepository.save(ticket);
				
				return true;
			}
			else
				return false;

		} catch (Exception e) {
			logger.error("Error occured while adding ticket " + e.getMessage());
			return false;
		}
		
	}

	private Customer convertCustomerVoToCustomer(CustomerVo prospectCreation) {
		
		Customer customer = new  Customer();
		
		customer.setTitle(prospectCreation.getCustomerTittle());
		customer.setFirstName(prospectCreation.getCustomerFirstName());
		customer.setLastName(prospectCreation.getCustomerLastName());
		customer.setMobileNumber(prospectCreation.getCustomerMobileNumber());
		customer.setAlternativeMobileNo(prospectCreation.getCustomerAternateMobileNumber());
		customer.setEmailId(prospectCreation.getCustomerEmailId());
		customer.setCommunicationAdderss(prospectCreation.getCustomerCommunicationAddress());
		customer.setCity(cityRepository.findById(1l).get());
		customer.setBranch(branchRepository.findById(1l).get());
		customer.setArea(areaRepository.findById(1L).get());
		customer.setCreatedOn(new Date());
		customer.setModifiedOn(new Date());
		Status status = statusRepository.findById(1).get();
		customer.setStatusBean(status);

		User user = userRepository.findById(1l).get();
		customer.setCreatedByUserId(user);
		customer.setModifiedByUserId(user);
		
		return customer;
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
	public List<TicketDetails> getTicketDetails(Long id) {
		
		List<TicketDetails> ticketLists = new ArrayList<TicketDetails>();
		
		List<Object[]> tickets = entityManager.createNativeQuery(TICKET_DETAILS_BASE_QUERY).setParameter("tickekId", id).getResultList();
		
		
		for (Object[] object : tickets) {
			
			TicketDetails td = new TicketDetails();
			
			td.setTicketId(((BigInteger) object[0]).longValue());
			td.setTicketNo(((BigInteger) object[0]).toString());
			td.setTicketDescription((String) object[1]);
			td.setCurrentAssigneeId(((BigInteger)object[2]).longValue());
			td.setCurrentAssigneeName((String)object[3]);
			td.setTicketStatus((Integer)object[4]);
			td.setTicketCreatedTime((Date)object[5]);
			td.setTicketTypeId(((Integer) object[6]).longValue());
			td.setTicketTypeName((String) object[7]);
			
			if(object[8] != null)
				td.setAssetId(((BigInteger) object[8]).longValue() );
			if(object[9] != null)
				td.setAssetDescription((String) object[9]);
			if(object[10] != null)
				td.setAssetTypeId(((Integer) object[10]).longValue());
			if(object[11] != null)
				td.setAssetTypeName((String) object[11]);
			if(object[12] != null)
				td.setAssetLat((String) object[12]);
			if(object[13] != null)
				td.setAssetLong((String) object[13]);
			
			td.setCustomerId(((BigInteger) object[14]).longValue());
			td.setCustomerTittle((String) object[15]);
			td.setCustomerFirstName((String) object[16]);
			td.setCustomerLastName((String) object[17]);
			td.setCustomerMobileNumber((String) object[18]);
			td.setCustomerAternateMobileNumber((String) object[19]);
			td.setCustomerEmailId((String) object[20]);
			td.setCustomerCommunicationAddress((String) object[21]);
			
			ticketLists.add(td);
		}
		
		return ticketLists;
		
	
	}

	@Override
	public List<TicketCardViewData> getTicketSummary() {
		
		List<Object[]> tickets = entityManager.createNativeQuery(GET_CARD_VIEW_DATA).getResultList();
		
		return xTransformToCardViewList(tickets);
	}

	private List<TicketCardViewData> xTransformToCardViewList(List<Object[]> tickets) {

		List<TicketCardViewData> ticketLists = new ArrayList<TicketCardViewData>();
		
		
		for (Object[] object : tickets) {
			
			TicketCardViewData cardViewData = new TicketCardViewData();
			Random rnd = new Random();
			int ticketNoRnd = 100000 + rnd.nextInt(900000);
			cardViewData.setTicketId(Long.valueOf(((BigInteger)object[0]).longValue()));
			cardViewData.setTicketNumber(String.valueOf(ticketNoRnd));
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
