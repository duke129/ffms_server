/**
 * 
 */
package com.hm.dao.mysql.ticket;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hm.dao.mysql.activity.ActivityDao;
import com.hm.dao.mysql.area.AreaRepository;
import com.hm.dao.mysql.asset.AssetRepository;
import com.hm.dao.mysql.branch.BranchRepository;
import com.hm.dao.mysql.city.CityRepository;
import com.hm.dao.mysql.customer.CustomerRepository;
import com.hm.dao.mysql.status.StatusRepository;
import com.hm.dao.mysql.ticketlog.TicketActivityLogDao;
import com.hm.dao.mysql.user.UserRepository;
import com.hm.util.FFMSConstant;
import com.hm.util.GenericUtil;
import com.hm.util.entity.Customer;
import com.hm.util.entity.Status;
import com.hm.util.entity.Ticket;
import com.hm.util.entity.TicketActivityLog;
import com.hm.util.entity.User;
import com.hm.util.model.ActivityVo;
import com.hm.util.model.AddressVo;
import com.hm.util.model.BasicInfoUpdate;
import com.hm.util.model.CustomerVo;
import com.hm.util.model.DashBoardSummaryCountVo;
import com.hm.util.model.ProspectCreation;
import com.hm.util.model.TicketActivityLogVo;
import com.hm.util.model.TicketCardViewData;
import com.hm.util.model.TicketDetails;
import com.hm.util.model.TicketFilter;

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
	
	@Autowired
	ActivityDao activityDao;
	
	@Autowired
	TicketActivityLogDao ticketActivityLogDao;
	
	@Autowired
	AssetRepository assetRepository;
	
	/**
	 * @author kiran
	 * query to fetch list view of tickets
	 */
	private static final String GET_CARD_VIEW_DATA = "select t.idTicket , t.createdOn , c.firstName , c.mobileNumber , c.communicationAdderss , t.ticketTypeId , t.tickectNo "
			+ "from Ticket t inner join Customer c on c.id = t.idCustomer where c.id > 0 ";
	
	/**
	 * @author kiran
	 * query to fetch detailed view of ticket
	 */
	private static final String TICKET_DETAILS_BASE_QUERY = "select t.idTicket , t.ticketDescription , t.assignedTo , u.firstName as assigneedToName , t.status , t.createdOn , t.ticketTypeId , tt.ticketType ," + 
			" a.idAsset , a.assetDescription , at.idAssetType , at.assetTypeDescription , a.installationLat ," + 
			" a.installationLong , c.id , c.title , c.firstName ,c.lastName ,c.mobileNumber , " + 
			" c.alternativeMobileNo, c.emailId,c.communicationAdderss , c.cityId , city.cityName ,"
			+ " c.branchId , b.branchName , c.areaId , area.areaName , t.preferedCallDate , "
			+ " t.tickectNo , c.middleName , c.alternativeEmailId , c.officeNumber , t.rejectionReason from Ticket t join TicketType tt on t.ticketTypeId = tt.idTicketType left join Asset a on t.idAsset = a.idAsset " + 
			" left join AssetType at on at.idAssetType = a.idAssetType join Customer c on t.idCustomer = c.id join User u on u.idUser = t.assignedTo "
			+ " join City city on city.idCity = c.cityId join Branch b on b.idBranch = c.branchId "
			+ " join Area area on area.idArea = c.areaId where t.idTicket = :tickekId ";

	/**
	 * @author kiran
	 * query to update basic details of ticket
	 */
	private static final String BASIC_INFO_UPDATE = "update Customer c join Ticket t  on t.idCustomer = c.id "
			+ "set c.title = :title , c.firstName = :firstName ,c.lastName = :lastName ,c.mobileNumber = :mobileNumber ,"
			+ "c.alternativeMobileNo = :alternativeMobileNo , c.emailId = :emailId ,"
			+ "c.communicationAdderss = :communicationAdderss , t.status = :status ,"
			+ " t.preferedCallDate = :preferedCallDate , c.currentAddress = :currentAddress , c.branchId = :branchId ,"
			+ " c.alternativeEmailId = :alternativeEmailId , c.officeNumber = :officeNumber ,"
			+ " c.areaId = :areaId where t.tickectNo = :ticketId";
	
	/**
	 * @author kiran
	 * query to update customer comments and ticket closer status
	 */
	private static final String CUSTOMER_COMMENTS_UPDATE = "update Ticket t set t.comments = :comments , t.status = :status where t.idTicket = :ticketId";
	
	
	private static final String GET_CUSTOMER_COMMENTS = " select comments from Ticket where idTicket = :ticketId ";
	
	
	private static final String UPDATE_CUSTOMER_NOT_INTERESTED = " update Ticket t set t.rejectionReason = :rejectionReason , t.status = :status where t.idTicket = :ticketId ";
	
	/**
	 * @author kiran
	 * @param  ticket
	 *  add ticket in data base 
	 */
	@Override
	public boolean createTicket(ProspectCreation prospectCreation) {
		
		try {
			
			logger.info("prospectCreation request data :: " +prospectCreation);

			Customer customer = new Customer();

			Ticket ticket = new Ticket();

			if (prospectCreation.getCustomer() != null) {
				
				customer = convertCustomerVoToCustomer(prospectCreation.getCustomer());

				Customer savedCustomer = customerRepository.save(customer);

				ticket.setCustomer(savedCustomer);
				ticket.setTickectNo(GenericUtil.generateTicketNo());
				ticket.setStatusBean(statusRepository.findById(FFMSConstant.NEW_LEAD).get());
				//ticket.setTicketDescription("testing " + savedCustomer.getId());
				ticket.setUser1(savedCustomer.getCreatedByUserId());
				ticket.setUser2(savedCustomer.getCreatedByUserId());
				ticket.setUser3(savedCustomer.getCreatedByUserId());
				ticket.setTicketType(ticketTypeRepository.findById(prospectCreation.getTicketTypeId().intValue()).get());
				ticket.setCreatedOn(new Date());
				ticket.setModifiedOn(new Date());
				
				if(prospectCreation.getTicketTypeId().equals(FFMSConstant.SERVICE_REQUEST))
				{
					ticket.setAsset(assetRepository.findById(prospectCreation.getAssetId()).get());
				}

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

	/**
	 * @author kiran
	 * @param prospectCreation
	 * CustomerVo to Customer object converter
	 * @return
	 */
	private Customer convertCustomerVoToCustomer(CustomerVo prospectCreation) {
		
		Customer customer = new  Customer();
		
		customer.setTitle(prospectCreation.getTitle());
		customer.setFirstName(prospectCreation.getFirstName());
		customer.setMiddleName(prospectCreation.getMiddleName());
		customer.setLastName(prospectCreation.getLastName());
		customer.setMobileNumber(prospectCreation.getMobileNumber());
		customer.setAlternativeMobileNo(prospectCreation.getAlternateMobileNumber());
		customer.setEmailId(prospectCreation.getEmailId());
		customer.setCommunicationAdderss(GenericUtil.addressParserObjectToString(prospectCreation.getCommunicationAddress()));
		customer.setCity(cityRepository.findById(prospectCreation.getCityId()).get());
		customer.setBranch(branchRepository.findById(prospectCreation.getBranchId()).get());
		customer.setArea(areaRepository.findById(prospectCreation.getAreaId()).get());
		customer.setCreatedOn(new Date());
		customer.setModifiedOn(new Date());
		Status status = statusRepository.findById(1).get();
		customer.setStatusBean(status);
		
		if(prospectCreation.getAlternateEmailId() != null)
			customer.setAlternativeEmailId(prospectCreation.getAlternateEmailId());

		if(prospectCreation.getOfficeNumber() != null)
			customer.setOfficeNumber(prospectCreation.getOfficeNumber());
		
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

	/**
	 * @author kiran
	 * @param id
	 * get complete ticket details by passing ticket id
	 */
	@Override
	public List<TicketDetails> getTicketDetails(Long id) {
		
		List<Object[]> tickets = entityManager.createNativeQuery(TICKET_DETAILS_BASE_QUERY).setParameter("tickekId", id).getResultList();
		
		return xTransformToTicketDetails(tickets);
		
	
	}

	/**
	 * @author kiran
	 * @param tickets
	 * query result to TicketDetails object converter
	 * @return
	 */
	private List<TicketDetails> xTransformToTicketDetails(List<Object[]> tickets) {

		List<TicketDetails> ticketLists = new ArrayList<TicketDetails>();

		if (tickets != null && !tickets.isEmpty()) {
			
			tickets.stream().forEach(object -> {
				
				TicketDetails td = new TicketDetails();

				td.setTicketId(((BigInteger) object[0]).longValue());
				td.setTicketDescription((String) object[1]);
				td.setCurrentAssigneeId(((BigInteger) object[2]).longValue());
				td.setCurrentAssigneeName((String) object[3]);
				td.setTicketStatus((Integer) object[4]);
				td.setTicketCreatedTime((Date) object[5]);
				td.setTicketTypeId(((Integer) object[6]).longValue());
				td.setTicketTypeName((String) object[7]);

				if (object[8] != null)
					td.setAssetId(((BigInteger) object[8]).longValue());
				if (object[9] != null)
					td.setAssetDescription((String) object[9]);
				if (object[10] != null)
					td.setAssetTypeId(((Integer) object[10]).longValue());
				if (object[11] != null)
					td.setAssetTypeName((String) object[11]);
				if (object[12] != null)
					td.setAssetLat((String) object[12]);
				if (object[13] != null)
					td.setAssetLong((String) object[13]);

				td.setCustomerId(((BigInteger) object[14]).longValue());
				td.setTitle((String) object[15]);
				td.setFirstName((String) object[16]);
				td.setLastName((String) object[17]);
				td.setMobileNumber((String) object[18]);
				td.setAlternateMobileNumber((String) object[19]);
				td.setEmailId((String) object[20]);

				AddressVo addressVo = GenericUtil.addressParserToObject((String) object[21]);
				td.setCommunicationAddress(addressVo);

				td.setCityId(((BigInteger) object[22]).longValue());
				td.setCityName((String) object[23]);
				td.setBranchId(((BigInteger) object[24]).longValue());
				td.setBranchName((String) object[25]);
				td.setAreaId(((BigInteger) object[26]).longValue());
				td.setAreaName((String) object[27]);
				td.setActivities(populateActivities((Integer) object[6], td.getTicketId()));
				td.setPreferredCallTime(GenericUtil.convertDateToStringFromate((Date) object[28]));
				td.setTicketNo((String)object[29]);
				td.setMiddleName((String)object[30]);
				td.setAlternateEmailId((String)object[31]);
				td.setOfficeNumber((String)object[32]);
				td.setRejectionReason((String)object[33]);

				ticketLists.add(td);
				
			});

				
		}

		return ticketLists;
	}

	/**
	 * @author kiran
	 * @param status
	 * get all ticket details summary by passing -1 or by passing ticket status
	 */
	@Override
	public List<TicketCardViewData> getTicketSummary(Integer status) {
		
		StringBuilder completeQuery = new StringBuilder(GET_CARD_VIEW_DATA);
		
		if(status > 0 )
		{
			completeQuery.append(" and t.status = "+status);
			
		}
		
		
		List<Object[]> tickets = entityManager.createNativeQuery(completeQuery.toString()).getResultList();
		
		return xTransformToCardViewList(tickets);
	}

	/**
	 * @author kiran
	 * @param tickets
	 * ticket details summary from query result to TicketCardViewData object converter
	 * @return
	 */
	private List<TicketCardViewData> xTransformToCardViewList(List<Object[]> tickets) {

		List<TicketCardViewData> ticketLists = new ArrayList<TicketCardViewData>();
		
		if(tickets != null && !tickets.isEmpty())
		{
			tickets.stream().forEach(object -> {
				
				TicketCardViewData cardViewData = new TicketCardViewData();
				
				cardViewData.setTicketId(Long.valueOf(((BigInteger)object[0]).longValue()));
				cardViewData.setTicketNumber((String)object[6]);
				cardViewData.setTicketCreationDate((Date)object[1]);
				cardViewData.setCommittedETR(GenericUtil.convertDateToStringFromate((Date)object[1]));
				cardViewData.setCustomerName((String)object[2]);
				cardViewData.setCustomerMobileNumber((String)object[3]);
				cardViewData.setCustomerAddress(GenericUtil.addressParserToObject((String)object[4]));
				cardViewData.setActivities(populateActivities((Integer)object[5],cardViewData.getTicketId()));
				ticketLists.add(cardViewData);
				
			});
			
			Collections.sort(ticketLists, 
				    Comparator.comparing(TicketCardViewData::getTicketCreationDate).reversed());
		}
		
		return ticketLists;

	}

	/**
	 * @author kiran
	 * @param basicInfoUpdate
	 * basic info update of the particular ticket , status change of the ticket to inprogress 
	 * and activity update
	 */
	@Override
	public int basicInfoUpdate(BasicInfoUpdate basicInfoUpdate) {
		
		int result = 0;
		try {
			
			logger.info("basic info update :: "+basicInfoUpdate);
			
			 result = entityManager.createNativeQuery(BASIC_INFO_UPDATE)
			.setParameter("title", basicInfoUpdate.getTitle())
			.setParameter("firstName", basicInfoUpdate.getFirstName())
			.setParameter("lastName", basicInfoUpdate.getLastName())
			.setParameter("mobileNumber", basicInfoUpdate.getMobileNumber())
			.setParameter("alternativeMobileNo", basicInfoUpdate.getAlternateMobileNumber())
			.setParameter("emailId", basicInfoUpdate.getEmailId())
			.setParameter("communicationAdderss", GenericUtil.addressParserObjectToString(basicInfoUpdate.getCommunicationAddress()))
			.setParameter("status", FFMSConstant.IN_PROGRESS)
			.setParameter("preferedCallDate", GenericUtil.convertStringToDateFromate(basicInfoUpdate.getPreferredCallTime()))
			.setParameter("currentAddress", GenericUtil.addressParserObjectToString(basicInfoUpdate.getCurrentAddress()))
			.setParameter("branchId", basicInfoUpdate.getBranchId())
			.setParameter("areaId", basicInfoUpdate.getAreaId())
			.setParameter("alternativeEmailId", basicInfoUpdate.getAlternateEmailId())
			.setParameter("officeNumber", basicInfoUpdate.getOfficeNumber())
			.setParameter("ticketId", basicInfoUpdate.getTicketId()).executeUpdate();
			 
			 if(result > 0)
			 {
				 String ticketId = entityManager.createNativeQuery(" select idTicket from Ticket where tickectNo = "+basicInfoUpdate.getTicketId())
						 .getSingleResult().toString();
				 
				 if(basicInfoUpdate.getRejectionReason() != null)
					 updateAsCustomerNotInterested(basicInfoUpdate, ticketId);
				 else
					 updateAsCustomerInterested(basicInfoUpdate,ticketId);
				 
			 }
			
			return result;
			
		} catch (Exception e) {
			
			logger.error("Error While updating basic info update :: " +e.getMessage());
			return result;
			
		}
		
	}
	
	private void updateAsCustomerNotInterested(BasicInfoUpdate basicInfoUpdate, String ticketId) {
		
		int result = 0;
		
		result = entityManager.createNativeQuery(UPDATE_CUSTOMER_NOT_INTERESTED)
				.setParameter("rejectionReason", basicInfoUpdate.getRejectionReason())
				.setParameter("status", FFMSConstant.REJECTED)
				.setParameter("ticketId", ticketId).executeUpdate();
		
		if(result > 0)
		{
			List<TicketActivityLogVo> ticketActivityLogs = new ArrayList<TicketActivityLogVo>();
			
			TicketActivityLogVo basicTicketActivityLog = new TicketActivityLogVo();
			 
			basicTicketActivityLog.setActivityId(FFMSConstant.ActivityConstant.BASIC_INFO_UPDATE);
			basicTicketActivityLog.setTicketId(Long.valueOf(ticketId));
			basicTicketActivityLog.setActivityStatus(FFMSConstant.ACTIVITY_COMPLETED);
			ticketActivityLogs.add(basicTicketActivityLog);
			
			TicketActivityLogVo demoTicketActivityLog = new TicketActivityLogVo();
			 
			demoTicketActivityLog.setActivityId(FFMSConstant.ActivityConstant.DEMO);
			demoTicketActivityLog.setTicketId(Long.valueOf(ticketId));
			demoTicketActivityLog.setActivityStatus(FFMSConstant.ACTIVITY_REJECTED);
			ticketActivityLogs.add(demoTicketActivityLog);
			
			TicketActivityLogVo orderTicketActivityLog = new TicketActivityLogVo();
			 
			orderTicketActivityLog.setActivityId(FFMSConstant.ActivityConstant.ORDER);
			orderTicketActivityLog.setTicketId(Long.valueOf(ticketId));
			orderTicketActivityLog.setActivityStatus(FFMSConstant.ACTIVITY_REJECTED);
			ticketActivityLogs.add(orderTicketActivityLog);
			
			ticketActivityLogDao.saveTicketActivityLog(ticketActivityLogs);
		}
		
	}

	private void updateAsCustomerInterested(BasicInfoUpdate basicInfoUpdate , String ticketId) {

		TicketActivityLogVo ticketActivityLog = new TicketActivityLogVo();
		 
		 ticketActivityLog.setActivityId(FFMSConstant.ActivityConstant.BASIC_INFO_UPDATE);
		 
		 ticketActivityLog.setTicketId(Long.valueOf(ticketId));
		 ticketActivityLog.setActivityStatus(FFMSConstant.ACTIVITY_COMPLETED);
		 
		 ticketActivityLogDao.saveTicketActivityLog(Collections.singletonList(ticketActivityLog));
		 
	}

	/**
	 * 
	 * @param ticketTypeId
	 * @param ticketId
	 * get activities details for particular ticket by passing ticket type and ticket id
	 * @return
	 */
	private List<ActivityVo> populateActivities(Integer ticketTypeId,Long ticketId)
	{

		if(ticketTypeId != null && ticketTypeId > 0)
		{
			List<ActivityVo> activities = activityDao.getAllActivitiesByType(ticketTypeId);
			List<TicketActivityLog> activityStatusVos = ticketActivityLogDao.findTicketActivityLogByTicketId(ticketId);
			
			
			if (activityStatusVos != null && !activityStatusVos.isEmpty()) {
				
				activities.stream().forEach(activity -> {

					activityStatusVos.stream().forEach(ticketActivity -> {

						
						if (Long.valueOf(ticketActivity.getTicketId() + "") == ticketId) {
							
							if (activity.getId().equals((ticketActivity.getActivity().getId()))) {
								activity.setStatus(ticketActivity.getStatus());
							}
						}
					});

				});
			}
			
			return activities;
		}
		return new ArrayList<ActivityVo>();
		
	}

	/**
	 * @author kiran
	 * get dash board summary count by ticket status (IN_PROGRESS ,REJECTED,COMPLETED,NEW_LEAD)
	 */
	@Override
	public List<DashBoardSummaryCountVo> getDashBoardSummary(Integer ticketType) {
		
		List<DashBoardSummaryCountVo> countResult =  new ArrayList<DashBoardSummaryCountVo>();
		FFMSConstant.ticketStatusList.forEach(status -> {
			
			DashBoardSummaryCountVo dashBoardSummaryCountVo = new DashBoardSummaryCountVo();
			
			dashBoardSummaryCountVo.setStatusId(status);
			dashBoardSummaryCountVo.setTotalCounts(ticketRepository.countByStatusBeanAndTicketType(statusRepository.findById(status).get(),ticketTypeRepository.findById(ticketType).get()).intValue());
			
			if (status == FFMSConstant.IN_PROGRESS) {
				
				dashBoardSummaryCountVo.setStatusName("in-progress");
			}
			else if (status == FFMSConstant.REJECTED) {
				
				dashBoardSummaryCountVo.setStatusName("rejected");
			}
			else if (status == FFMSConstant.COMPLETED) {
				
				dashBoardSummaryCountVo.setStatusName("completed");
			}
			else if (status == FFMSConstant.NEW_LEAD) {
				
				dashBoardSummaryCountVo.setStatusName("new-leads");
			}
			
			countResult.add(dashBoardSummaryCountVo);
			
		});
		
		return countResult;
	
	}

	@Override
	public int closeTicket(String customerComments , Long ticketId) {
		
		int result = 0;
		
		 try {
			result = entityManager.createNativeQuery(CUSTOMER_COMMENTS_UPDATE)
					.setParameter("comments", customerComments)
					.setParameter("status", FFMSConstant.COMPLETED)
					.setParameter("ticketId", ticketId)
					.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return result;
		}
		
	}

	@Override
	public String getTicketCommentsByTicketId(Long ticketId) {
		
		return entityManager.createNativeQuery(GET_CUSTOMER_COMMENTS).setParameter("ticketId", ticketId).getSingleResult().toString();
	}

	@Override
	public List<TicketCardViewData> searchTicketWithFilter(TicketFilter ticketFilter) {
		
		if(ticketFilter != null)
		{
			StringBuilder completeQuery = new StringBuilder(GET_CARD_VIEW_DATA);
			
			if(ticketFilter.getTicketNo() != null && !ticketFilter.getTicketNo().isEmpty())
				completeQuery.append(" and t.tickectNo = " +ticketFilter.getTicketNo());
			
			if(ticketFilter.getMobileNumber() != null && !ticketFilter.getMobileNumber().isEmpty())
				completeQuery.append(" and c.mobileNumber = " +ticketFilter.getMobileNumber());
			
			List<Object[]> tickets = entityManager.createNativeQuery(completeQuery.toString()).getResultList();
			
			return xTransformToCardViewList(tickets);
		}
		
		return null;
	}
	
	
	
}
