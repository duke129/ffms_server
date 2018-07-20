/**
 * 
 */
package com.hm.dao.mysql.city;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hm.util.entity.City;
import com.hm.util.entity.Status;
import com.hm.util.entity.User;
import com.hm.util.model.CityDTO;

/**
 * @author kiran
 *
 */
@Repository
public class CityDaoImpl implements CityDao {

	private static final Logger logger = LoggerFactory.getLogger(CityDaoImpl.class);
	private static final String GET_TOTAL_CITY = "select count(*) from City";
	private static final String GET_TOTAL_BRANCH = "select count(*) from Branch";
	private static final String GET_TOTAL_AREA = "select count(*) from Area";

	@Autowired
	CityRepository cityRepository;
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void saveCity(CityDTO cityDto) {
		try {
			
			City city = new City();
			BeanUtils.copyProperties(cityDto,city);
			city.setCreatedOn(new Date());
			city.setModifiedOn(new Date());
			User user=new User();
			user.setIdUser(1L);
			city.setUser1(user);
			city.setUser2(user);
			Status status=new Status();
			if(cityDto.getStatus().equalsIgnoreCase("Enable")) {
				status.setIdStatus(1);
				
			}else {
				status.setIdStatus(0);
			}
			
			city.setCode(cityDto.getCode());
			city.setStatusBean(status);
			city.setState(cityDto.getState());
			logger.info("City value that going to be save into databases ************"+city);
			cityRepository.save(city);
			
		} catch (Exception e) {
			logger.info(e.getMessage());
			
		}

	}

	@Override
	public List<City> findAllCity() {
		List<City> listcity=cityRepository.findAll();
		logger.info("city list view is :::"+listcity);
		return listcity;
	}

	@Override
	public Integer getTotalCityCount() {
		BigInteger citycount = (BigInteger)entityManager.createNativeQuery(GET_TOTAL_CITY).getSingleResult();
		logger.info("Total city count is :::"+citycount);
		Integer count=citycount.intValue();
		return count;
	}

	@Override
	public Integer getTotalBranchCount() {
		BigInteger citycount = (BigInteger)entityManager.createNativeQuery(GET_TOTAL_BRANCH).getSingleResult();
		logger.info("Total city count is :::"+citycount);
		Integer count=citycount.intValue();
		return count;
	}

	@Override
	public Integer getTotalAreaCount() {
		BigInteger citycount = (BigInteger)entityManager.createNativeQuery(GET_TOTAL_AREA).getSingleResult();
		logger.info("Total city count is :::"+citycount);
		Integer count=citycount.intValue();
		return count;
	}

}
