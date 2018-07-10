/**
 * 
 */
package com.hm.dao.mysql.customer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hm.util.entity.Customer;
import com.hm.util.model.TitleDTO;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	private EntityManager  entityManager;

	@Autowired
	CustomerRepository customerRepository;
	
	private static final String getCustomerData = "SELECT  * FROM Customer";
	
	private static final String GET_TILTLE_QUERY="select * from Title";
	
	@Override
	public List<Customer> getAllCustomers() {
		
		List<Customer> customers = new ArrayList<Customer>();
		
		List<Object[]> customersEntity = entityManager.createNativeQuery(getCustomerData).getResultList();
		
		
		for (Object[] object : customersEntity) {
			Customer cos = new Customer();
			cos.setId(((BigInteger) object[0]).longValue());
			cos.setTitle((String) object[1]);
			cos.setFirstName((String) object[2]);      
			cos.setLastName((String) object[3]);
			cos.setMobileNumber((String) object[5]);
			cos.setCommunicationAdderss((String) object[8]);
			cos.setEmailId((String) object[7]);
	
			customers.add(cos);
		}
		
		return customers;
		
		//customerRepository.findAll();
		
	}
	
	@Override
	public String addCustomer(Customer customer) {
		 customerRepository.save(customer);
		 return "Success";
	}

	@Override
	public String modifyCustomerDetails(Customer customer) {
		
		Optional<Customer>  cost = customerRepository.findById(customer.getId());
		Customer cos = cost.get();
		
		if(customer.getFirstName()!=null)
		cos.setFirstName(customer.getFirstName());
		
		if(customer.getLastName()!=null)
		cos.setLastName(customer.getLastName());
		
		if(customer.getBranch()!=null)
		cos.setBranch(null);
		
		if(customer.getArea()!=null)
		cos.setArea(customer.getArea());
		
		if(customer.getCity()!=null)
		cos.setCity(customer.getCity());
		
		if(customer.getCommunicationAdderss()!=null)
		cos.setCommunicationAdderss(customer.getCommunicationAdderss());
		
		if(customer.getCustomerType()!=null)
		cos.setCustomerType(customer.getCustomerType());
		
		if(customer.getEmailId()!=null)
		cos.setEmailId(customer.getEmailId());
		
		if(customer.getMobileNumber()!=null)
		cos.setMobileNumber(null);
		
		if(customer.getAlternativeMobileNo()!=null)
		cos.setAlternativeMobileNo(customer.getAlternativeMobileNo());
		
		customerRepository.save(cos);
		
		return "Modified Success";
	}

	@Override
	public List<TitleDTO> getTitles() {
		
		List<TitleDTO> titleList=new ArrayList<TitleDTO>();
		
		List<Object[]> titlelist = entityManager.createNativeQuery(GET_TILTLE_QUERY).getResultList();
		
		for (Object[] objects : titlelist) {
			TitleDTO titleDTO=new TitleDTO();
			titleDTO.setId(String.valueOf(objects[0]));
			titleDTO.setName(String.valueOf(objects[1]));
			titleList.add(titleDTO);
		}
		return titleList;
	}

}
