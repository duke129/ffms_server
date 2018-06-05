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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hm.dao.mysql.status.SatusRepository;
import com.hm.ticket.manager.TicketManager;
import com.hm.util.entity.Status;
import com.hm.util.entity.Ticket;
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
	
	
	@PostMapping("create")
	public ResponseEntity<Void> addTicket(@RequestBody Ticket ticket)
	{
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		
	}
	
//	@GetMapping("status")
//	public List<Status> getSatusById()
//	{
//		return statuRepository.findAll();
//	}
	
	@GetMapping("test")
	public List<TicketDetails> getAllTickets()
	{
		return ticketManager.getAllTickets();
	}
	
	@PostMapping("list-view")
	public List<TicketCardViewData> getTicketSummary()
	{
		return ticketManager.getTicketSummary();
	}
	
	@GetMapping("detail/{id}")
	public Ticket getTicketById(@PathVariable("id") Long id)
	{
		return ticketManager.getTicketSummaryZoom(id);
	}

}
