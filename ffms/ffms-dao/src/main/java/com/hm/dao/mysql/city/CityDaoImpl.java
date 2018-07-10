/**
 * 
 */
package com.hm.dao.mysql.city;

import java.util.Date;
import java.util.List;

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

	@Autowired
	CityRepository cityRepository;

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
			if(cityDto.getStatusId().equalsIgnoreCase("Enable")) {
				status.setIdStatus(1);
				
			}else {
				status.setIdStatus(0);
			}
			
			city.setCode(cityDto.getCode());
			city.setStatusBean(status);
			System.out.println("City value that going to be save into databases ************"+city);
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

}
