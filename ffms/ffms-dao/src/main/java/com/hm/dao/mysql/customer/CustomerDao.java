/**
 * 
 */
package com.hm.dao.mysql.customer;

import java.util.List;

import com.hm.util.entity.Customer;

public interface CustomerDao {
	
	List<Customer> getAllCustomers();
	
	String addCustomer(Customer customer);
	
	String modifyCustomerDetails(Customer customer);

}
