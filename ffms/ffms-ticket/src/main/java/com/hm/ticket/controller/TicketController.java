/**
 * 
 */
package com.hm.ticket.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hm.dao.mysql.status.StatusRepository;
import com.hm.ticket.manager.TicketManager;
import com.hm.util.entity.Status;
import com.hm.util.entity.Ticket;
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
	public String createTicket(@RequestBody ProspectCreation prospectCreation)
	{
		 boolean result = ticketManager.createTicket(prospectCreation);
		 
		 HttpHeaders headers = new HttpHeaders();
		 
		 if(result)
		 {
			 return  "Created Successfully";
		 }
		 else
			 return "Failed to create";
		
		
		
	}
	
	@CrossOrigin
	@GetMapping("details/{id}")
	public List<TicketDetails> getTicketDetails(@PathVariable("id") Long id)
	{
		return ticketManager.getTicketDetails(id);
	}
	
	@CrossOrigin
	@GetMapping("list-view")
	public List<TicketCardViewData> getTicketSummary()
	{
		return ticketManager.getTicketSummary();
	}
	
	@CrossOrigin
	@GetMapping("dashboard-count")
	public List<DashBoardSummaryCountVo> getDashBoardSummary()
	{
		return ticketManager.getDashBoardSummary();
	}

}
