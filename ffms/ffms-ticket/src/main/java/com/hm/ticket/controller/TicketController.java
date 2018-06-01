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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hm.dao.mysql.status.SatusRepository;
import com.hm.util.entity.Status;
import com.hm.util.entity.Ticket;

/**
 * @author kiran
 *
 */
@Controller
@RestController
@RequestMapping("ticket")
public class TicketController {
	
	@Autowired
	SatusRepository statuRepository;
	
	@GetMapping(path = "/test")
	public String ticketTest()
	{
		return "Ticket Tested";
	}
	
	@PostMapping("create")
	public ResponseEntity<Void> addTicket(@RequestBody Ticket ticket)
	{
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		
	}
	
	@GetMapping("status")
	public List<Status> getSatusById()
	{
		return statuRepository.findAll();
	}

}
