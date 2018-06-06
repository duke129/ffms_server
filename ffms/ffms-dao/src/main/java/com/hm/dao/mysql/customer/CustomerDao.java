/**
 * 
 */
package com.hm.dao.mysql.customer;

import java.util.List;

import com.hm.util.entity.Customer;

/**
 * @author kiran
 *
 */
public interface CustomerDao {
	List<Customer> getAllCustomers();

}
