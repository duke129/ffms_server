/**
 * 
 */
package com.hm.customer.manager;

import java.util.List;

import com.hm.util.entity.Customer;
import com.hm.util.model.CustomerVo;
import com.hm.util.model.TitleDTO;
import com.hm.util.model.filter.CustomerFilter;

public interface CustomerManager {
	
	List<Customer> getAllCustomers();
	
	String addCustomer(Customer customer);
	
	String modifyCustomerDetails(Customer customer);
	
	List<TitleDTO> getTitles();
	
	List<CustomerVo> getCustomerByFilter(CustomerFilter filter);

}
