/**
 * 
 */
package com.hm.customer.manager;

import java.util.List;

import com.hm.util.entity.Customer;
import com.hm.util.model.TitleDTO;

public interface CustomerManager {
	
	List<Customer> getAllCustomers();
	
	String addCustomer(Customer customer);
	
	String modifyCustomerDetails(Customer customer);
	
	List<TitleDTO> getTitles();

}
