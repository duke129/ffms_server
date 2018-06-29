package com.hm.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hm.customer.manager.CustomerManager;
import com.hm.util.entity.Customer;

@CrossOrigin
@Controller
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerManager customerManager;
	
	@GetMapping(path = "/customer")
	public String customerTest()
	{
		return "Customer tested";
	}
	
	@PostMapping(path = "/add")
	public String addCustomer(@RequestBody Customer customer)
	{
		System.out.println("ADD customer controller @@@@@@@@@@@@"+customer);
		 return customerManager.addCustomer(customer);
		 
	}
	
	
	@GetMapping(path = "/getall")
	public List<Customer> getAllCustomers()
	{
		return customerManager.getAllCustomers();
	}
	
	@PutMapping(path = "/modify")
	public String modifyCustomerDetails(@RequestBody Customer customer)
	{
		System.out.println("started customer modification");
		return customerManager.modifyCustomerDetails(customer);
	}
	
	@GetMapping(path = "/delete")
	public String deleteCustomer()
	{
		return "Customer tested";
	}
}
