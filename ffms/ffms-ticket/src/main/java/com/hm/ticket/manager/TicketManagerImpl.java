/**
 * 
 */
package com.hm.ticket.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.hm.dao.mysql.area.AreaRepository;
import com.hm.dao.mysql.branch.BranchRepository;
import com.hm.dao.mysql.city.CityRepository;
import com.hm.dao.mysql.customer.CustomerRepository;
import com.hm.dao.mysql.status.StatusRepository;
import com.hm.dao.mysql.ticket.TicketDao;
import com.hm.dao.mysql.ticket.TicketRepository;
import com.hm.dao.mysql.ticket.TicketTypeRepository;
import com.hm.dao.mysql.user.UserRepository;
import com.hm.util.FFMSConstant;
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
import com.hm.util.model.TicketFilter;

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
	public List<TicketCardViewData> getTicketSummary(Integer status) {

		return ticketDAO.getTicketSummary(status);
	}

	/**
	 * @author kiran
	 * get DashBoard summary count 
	 */
	@Override
	public APIResponse getDashBoardSummary(Integer ticketType) {
		
		APIResponse response = new APIResponse();
		
		if(ticketType != null && ticketType > 0)
		{	
			response.setStatusId(200);
			response.setStatusMessage("Success");
			response.setData(ticketDAO.getDashBoardSummary(ticketType));
		}
		
		response.setStatusId(302);
		response.setStatusMessage("Failed to get Dashboard count ");
		
		return response;
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
			int result = ticketDAO.basicInfoUpdate(basicInfoUpdate);
			
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

	@Override
	public APIResponse searchTicketWithFilter(TicketFilter ticketFilter) {
		
		APIResponse response = new APIResponse();
		
		response.setStatusId(200);
		response.setStatusMessage("Success");
		response.setData(ticketDAO.searchTicketWithFilter(ticketFilter));
		
		return response;
	}

}
