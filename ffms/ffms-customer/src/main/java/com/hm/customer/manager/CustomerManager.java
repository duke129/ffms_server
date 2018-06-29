/**
 * 
 */
package com.hm.customer.manager;

import java.util.List;

import com.hm.util.entity.Customer;

public interface CustomerManager {
	
	List<Customer> getAllCustomers();
	
	String addCustomer(Customer customer);
	
	String modifyCustomerDetails(Customer customer);

}
