/**
 * 
 */
package com.hm.dao.mysql.customer;

import java.util.List;

import com.hm.util.entity.Customer;
import com.hm.util.model.TitleDTO;

public interface CustomerDao {
	
	List<Customer> getAllCustomers();
	
	String addCustomer(Customer customer);
	
	String modifyCustomerDetails(Customer customer);
	
	List<TitleDTO> getTitles();

}
