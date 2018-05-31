package com.hm.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class CustomerController {
	
	@GetMapping(path = "/customer")
	public String customerTest()
	{
		return "Customer tested";
	}

}
