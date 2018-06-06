package com.hm.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hm.customer.manager.CustomerManager;
import com.hm.util.entity.Customer;


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
	
	@GetMapping(path = "/add")
	public String addCustomer()
	{
		return "Customer tested";
	}
	
	@CrossOrigin
	@GetMapping(path = "/getall")
	public List<Customer> getAllCustomers()
	{
		return customerManager.getAllCustomers();
	}
	
	@GetMapping(path = "/modify")
	public String modifyCustomerDetails()
	{
		return "Customer tested";
	}
	
	@GetMapping(path = "/delete")
	public String deleteCustomer()
	{
		return "Customer tested";
	}
}
