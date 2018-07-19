/**
 * 
 */
package com.hm.dao.mysql.customer;

import java.util.List;

import com.hm.util.entity.Customer;
import com.hm.util.model.CustomerVo;
import com.hm.util.model.TitleDTO;
import com.hm.util.model.filter.CustomerFilter;

public interface CustomerDao {
	

	String GET_CUSTOMER_BY_FILTER = " select c.id,CONCAT(c.firstName,' ',c.lastName),c.mobileNumber,c.status,"
			+ "c.communicationAdderss,c.currentAddress,c. customerType  from Customer c";
	
	String GET_CUSTOMER_COUNT = "select count(id)  from Customer c";

	
	List<Customer> getAllCustomers();
	
	String addCustomer(Customer customer);
	
	String modifyCustomerDetails(Customer customer);
	
	List<TitleDTO> getTitles();
	
	List<CustomerVo> getCustomerByFilter(CustomerFilter filter);

}
