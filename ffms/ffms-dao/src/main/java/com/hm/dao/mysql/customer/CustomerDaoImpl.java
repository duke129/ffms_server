/**
 * 
 */
package com.hm.dao.mysql.customer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hm.util.GenericUtil;
import com.hm.util.entity.Customer;
import com.hm.util.model.CustomerVo;
import com.hm.util.model.TitleDTO;
import com.hm.util.model.filter.CustomerFilter;

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
	
	@Override
	public List<CustomerVo> getCustomerByFilter(CustomerFilter filter) {

		System.out.println("*************" + filter);
		StringBuilder whereCluse = new StringBuilder("");

		List<CustomerVo> list = new ArrayList<CustomerVo>();
		whereCluse.append(" where c.status=:statusId");

		System.out.println("where:::::::" + whereCluse);
		try {
			if (filter.getStartDate() != null && filter.getEndDate() != null)
				whereCluse.append(" and c.addedOn >=:startDate").append(" and c.addedOn <=:endDate ");

			if (filter.getName() != null && !filter.getName().isEmpty())
				whereCluse.append(" and CONCAT(c.firstName, ' ', c.lastName) LIKE :fullName");
			if (filter.getMobileNo() != null && !filter.getMobileNo().isEmpty())
				whereCluse.append(" and c.mobileNumber LIKE :mobileNumber");

			if (filter.getPageSize() > 0 && filter.getOffset() >= 0) {
				whereCluse.append(" limit " + filter.getOffset() + " , " + filter.getPageSize());
			}

			String completeQuery = GET_CUSTOMER_BY_FILTER.toString().concat(whereCluse.toString());
			String countQuery = GET_CUSTOMER_COUNT.toString().concat(whereCluse.toString()).split("limit")[0];

			System.out.println("*******************************" + completeQuery);

			Query query = entityManager.createNativeQuery(completeQuery);
			Query queryCount = entityManager.createNativeQuery(countQuery);

			System.out.println("*******************************" + completeQuery);
			if (filter.getName() != null && !filter.getName().isEmpty()) {
				query.setParameter("fullName", "%" + filter.getName() + "%");
				queryCount.setParameter("fullName", "%" + filter.getName() + "%");
			}
			if (filter.getStartDate() != null && filter.getEndDate() != null) {
				query.setParameter("startDate", filter.getStartDate()).setParameter("endDate", filter.getEndDate());
				queryCount.setParameter("startDate", filter.getStartDate()).setParameter("endDate", filter.getEndDate());
			}
			if (filter.getMobileNo() != null && !filter.getMobileNo().isEmpty()){
				query.setParameter("mobileNumber", "%" + filter.getMobileNo() + "%");
				queryCount.setParameter("mobileNumber", "%" + filter.getMobileNo() + "%");
			}

			query.setParameter("statusId", 1);
			queryCount.setParameter("statusId", 1);

			List<Object[]> customerList = query.getResultList();
			BigInteger count = (BigInteger) queryCount.getResultList().stream().findFirst().orElse(0);

			System.out.println("*********"+count);
			for (Object obj[] : customerList) {
				CustomerVo vo = new CustomerVo();
				vo.setCityId(Long.valueOf(obj[0].toString()));
				vo.setFirstName(obj[1] != null ? obj[1].toString() : null);
				vo.setMobileNumber(obj[2] != null ? obj[2].toString() : null);
				//vo.setCommunicationAddress(obj[4] != null ? GenericUtil.addressParserToObject(obj[4].toString()) : null);
				//vo.setCurrentAddress(obj[5] != null ? GenericUtil.addressParserToObject(obj[5].toString()) : null);
				vo.setType(obj[6] != null ? obj[6].toString() : null);
				vo.setCount(count);
				list.add(vo);
			}
			System.out.println("list::"+list);
		} catch (Exception e) {
			System.out.println("Exception::::::::::" + e);
		}
		return list;

	}

}
