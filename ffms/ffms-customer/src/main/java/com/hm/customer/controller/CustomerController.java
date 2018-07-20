package com.hm.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hm.customer.manager.CustomerManager;
import com.hm.util.entity.Customer;
import com.hm.util.model.APIResponse;
import com.hm.util.model.CustomerVo;
import com.hm.util.model.TitleDTO;
import com.hm.util.model.TypeHeadVo;
import com.hm.util.model.filter.CustomerFilter;

@CrossOrigin
@Controller
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerManager customerManager;
	
	@PostMapping(path = "/add")
	public String addCustomer(@RequestBody Customer customer)
	{
		logger.info("ADD customer controller :: "+customer);
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
	
	
	@RequestMapping(value = "/title", method = RequestMethod.GET)
    public APIResponse getTitles() {
		APIResponse aPIResponse=new APIResponse();
		List<TitleDTO> titleDtolist=customerManager.getTitles();
		List<TypeHeadVo> titleTypeheadvolist=new ArrayList<TypeHeadVo>();
		for (TitleDTO titleDTO : titleDtolist) {
			TypeHeadVo typeHeadVo=new TypeHeadVo();
			typeHeadVo.setId(Long.valueOf(titleDTO.getId()));
			typeHeadVo.setName(titleDTO.getName());
			titleTypeheadvolist.add(typeHeadVo);
		}
		if(titleTypeheadvolist==null || titleTypeheadvolist.isEmpty()) {
			aPIResponse.setStatusId(404);
	        aPIResponse.setStatusMessage("Record not found");
	        aPIResponse.setData(null);
		}
		
        aPIResponse.setStatusId(200);
        aPIResponse.setStatusMessage("successful");
        aPIResponse.setData(titleTypeheadvolist);
       
        return aPIResponse;
    }
	
	@PostMapping(path = "/filter")
	public List<CustomerVo> getCustomerByFilter(@RequestBody CustomerFilter filter) {
		
		logger.info("getCustomerByFilter  payload :: "+filter);
		return customerManager.getCustomerByFilter(filter);
	}
	
}
