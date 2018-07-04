/**
 * 
 */
package com.hm.ticket.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.mysql.area.AreaRepository;
import com.hm.dao.mysql.branch.BranchRepository;
import com.hm.dao.mysql.city.CityRepository;
import com.hm.dao.mysql.customer.CustomerRepository;
import com.hm.dao.mysql.status.StatusRepository;
import com.hm.dao.mysql.ticket.TicketDao;
import com.hm.dao.mysql.ticket.TicketRepository;
import com.hm.dao.mysql.ticket.TicketTypeRepository;
import com.hm.dao.mysql.user.UserRepository;
import com.hm.util.entity.Customer;
import com.hm.util.entity.Status;
import com.hm.util.entity.Ticket;
import com.hm.util.entity.User;
import com.hm.util.model.APIResponse;
import com.hm.util.model.BasicInfoUpdate;
import com.hm.util.model.DashBoardSummaryCountVo;
import com.hm.util.model.ProspectCreation;
import com.hm.util.model.TicketCardViewData;
import com.hm.util.model.TicketDetails;

/**
 * @author kiran
 *
 */
@Service
public class TicketManagerImpl implements TicketManager {

	@Autowired
	TicketDao ticketDAO;

	/**
	 * @author kiran
	 * @param ticket
	 *            create ticket and ticket classification
	 */
	@Override
	public boolean createTicket(ProspectCreation prospectCreation) {

		try {

			ticketDAO.createTicket(prospectCreation);
			return true;
			
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @author kiran
	 * @param id
	 * Get detailed view of ticket
	 */
	@Override
	public List<TicketDetails> getTicketDetails(Long id) {

		return ticketDAO.getTicketDetails(id);
	}

	/**
	 * @author kiran
	 * Get Ticket Summary details for list view
	 */
	@Override
	public List<TicketCardViewData> getTicketSummary() {

		return ticketDAO.getTicketSummary();
	}

	/**
	 * @author kiran
	 * get DashBoard summary count 
	 */
	@Override
	public List<DashBoardSummaryCountVo> getDashBoardSummary() {
		
		List<DashBoardSummaryCountVo> dashboardCountLists = new ArrayList<DashBoardSummaryCountVo>();
		
		DashBoardSummaryCountVo newLeadsDashBoardSummaryCountVo = new DashBoardSummaryCountVo();
		
		int newLeadsCount = generateRandomNumber();
		newLeadsDashBoardSummaryCountVo.setStatusName("new-leads");
		newLeadsDashBoardSummaryCountVo.setTotalCounts(newLeadsCount);
		dashboardCountLists.add(newLeadsDashBoardSummaryCountVo);
		
		
		DashBoardSummaryCountVo serviceRequestDashBoardSummaryCountVo = new DashBoardSummaryCountVo();
		
		int serviceRequestCount = generateRandomNumber();
		serviceRequestDashBoardSummaryCountVo.setStatusName("service-request");
		serviceRequestDashBoardSummaryCountVo.setTotalCounts(serviceRequestCount);
		dashboardCountLists.add(serviceRequestDashBoardSummaryCountVo);
		
		DashBoardSummaryCountVo progressDashBoardSummaryCountVo = new DashBoardSummaryCountVo();
		
		int progressCount = generateRandomNumber();
		progressDashBoardSummaryCountVo.setStatusName("in-progress");
		progressDashBoardSummaryCountVo.setTotalCounts(progressCount);
		dashboardCountLists.add(progressDashBoardSummaryCountVo);
		
        DashBoardSummaryCountVo totalDashBoardSummaryCountVo = new DashBoardSummaryCountVo();
		
		int totalCount = newLeadsCount + progressCount + serviceRequestCount;
		totalDashBoardSummaryCountVo.setStatusName("total-tickets");
		totalDashBoardSummaryCountVo.setTotalCounts(totalCount);
		dashboardCountLists.add(totalDashBoardSummaryCountVo);
		
		return dashboardCountLists;
	}

	/**
	 * generate random number between 0 to 100
	 * @return random count
	 */
	private int generateRandomNumber() {
		
		double randNumber = Math.random();
	    double count  =  randNumber * 100;
		return (int)count;
	}

	/**
	 * @author kiran
	 * @param basicInfoUpdate
	 * update basic details of customer
	 * 
	 */
	@Override
	public APIResponse basicInfoUpdate(BasicInfoUpdate basicInfoUpdate) {
		
		if(basicInfoUpdate != null)
		{
			int result = ticketDAO.updateTicket(basicInfoUpdate);
			
			if(result > 0)
			{
				APIResponse apiResponse = new APIResponse();
				apiResponse.setStatusId(200);
				apiResponse.setStatusMessage("Successfully updated");
				
				return apiResponse;
			}
			else
			{
				APIResponse apiResponse = new APIResponse();
				apiResponse.setStatusId(302);
				apiResponse.setStatusMessage("Updation Failed");
				return apiResponse;
			}
		}
		
		APIResponse apiResponse = new APIResponse();
		apiResponse.setStatusId(303);
		apiResponse.setStatusMessage("Request body can't be null");
		return apiResponse;
	}

}
