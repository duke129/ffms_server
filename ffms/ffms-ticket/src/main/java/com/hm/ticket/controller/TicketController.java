/**
 * 
 */
package com.hm.ticket.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hm.ticket.manager.TicketManager;
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
@Controller
@RestController
@RequestMapping("ticket")
public class TicketController {
	
	@Autowired
	TicketManager ticketManager;
	
	@CrossOrigin
	@PostMapping("create")
	public APIResponse createTicket(@RequestBody ProspectCreation prospectCreation)
	{
		 boolean result = ticketManager.createTicket(prospectCreation);	
		 
		 APIResponse response = new APIResponse();
		 
		 if(result)
		 {
			 response.setStatusId(200);
			 response.setStatusMessage("Created Successfully");
			 
			 return  response;
		 }
		 else
		 {
			 response.setStatusId(203);
			 response.setStatusMessage("Failed to create");
			 
			 return response;
		 }
			
		
	}
	
	@CrossOrigin
	@GetMapping("details/{id}")
	public List<TicketDetails> getTicketDetails(@PathVariable("id") Long id)
	{
		return ticketManager.getTicketDetails(id);
	}
	
	@CrossOrigin
	@GetMapping("list-view/{status}")
	public List<TicketCardViewData> getTicketSummary(@PathVariable("status") Integer status)
	{
		return ticketManager.getTicketSummary(status);
	}
	
	@CrossOrigin
	@GetMapping("dashboard-count")
	public APIResponse getDashBoardSummary()
	{
		return ticketManager.getDashBoardSummary();
	}
	
	@CrossOrigin
	@PostMapping("basic-info-update")
	public APIResponse  basicInfoUpdate(@RequestBody BasicInfoUpdate basicInfoUpdate)
	{
		return ticketManager.basicInfoUpdate(basicInfoUpdate);
	}

}
