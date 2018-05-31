/**
 * 
 */
package com.hm.ticket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kiran
 *
 */
@Controller
@RestController
public class TicketController {
	
	@GetMapping(path = "/ticket")
	public String ticketTest()
	{
		return "Ticket Tested";
	}

}
