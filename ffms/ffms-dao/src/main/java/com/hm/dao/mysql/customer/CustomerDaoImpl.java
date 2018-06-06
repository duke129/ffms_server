/**
 * 
 */
package com.hm.dao.mysql.customer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hm.util.entity.Customer;
import com.hm.util.model.TicketPojo;

/**
 * @author kiran
 *
 */
@Repository
public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	private EntityManager  entityManager;

	@Autowired
	CustomerRepository customerRepository;
	
	private static final String getCustomerData = "select  * from Customer";
	
	@Override
	public List<Customer> getAllCustomers() {
		
		Customer cos = new Customer();
		
		List<Customer> customers = new ArrayList<Customer>();
		
		List<Object[]> customersEntity = entityManager.createNativeQuery(getCustomerData).getResultList();
		
		
		for (Object[] object : customersEntity) {
			cos.setFirstName((String) object[2]);      
			cos.setLastName((String) object[3]);
			cos.setMobileNumber((String) object[5]);
			cos.setCommunicationAdderss((String) object[8]);
	
			customers.add(cos);
		}
		
		return customers;
		
	}

}
