/**
 * 
 */
package com.hm.customer.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.mysql.customer.CustomerDao;
import com.hm.util.entity.Customer;

/**
 * @author kiran
 *
 */
@Service
public class CustomerManagerImpl implements CustomerManager {

	@Autowired
	CustomerDao customerDao;

	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}

}
