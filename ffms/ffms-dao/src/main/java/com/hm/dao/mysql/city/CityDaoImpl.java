/**
 * 
 */
package com.hm.dao.mysql.city;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hm.util.entity.City;
import com.hm.util.entity.Status;
import com.hm.util.entity.User;
import com.hm.util.model.AssetVo;
import com.hm.util.model.CityDTO;
import com.hm.util.model.filter.CityFilter;

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
	private static final String GET_CITY_BY_FILTER = "select c.cityName,c.code,c.state,c.status from City c ";
	private static final String GET_CITY_COUNT_BASEDON_FILTER = "select count(*) from City c ";

	@Autowired
	CityRepository cityRepository;

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void saveCity(CityDTO cityDto) {
		try {

			City city = new City();
			BeanUtils.copyProperties(cityDto, city);
			city.setCreatedOn(new Date());
			city.setModifiedOn(new Date());
			User user = new User();
			user.setIdUser(1L);
			city.setUser1(user);
			city.setUser2(user);
			Status status = new Status();
			if (cityDto.getStatus().equalsIgnoreCase("Enable")) {
				status.setIdStatus(1);

			} else {
				status.setIdStatus(0);
			}

			city.setCode(cityDto.getCode());
			city.setStatusBean(status);
			city.setState(cityDto.getState());
			logger.info("City value that going to be save into databases ************" + city);
			cityRepository.save(city);

		} catch (Exception e) {
			logger.info(e.getMessage());

		}

	}

	@Override
	public List<City> findAllCity() {
		List<City> listcity = cityRepository.findAll();
		logger.info("city list view is :::" + listcity);
		return listcity;
	}

	@Override
	public Integer getTotalCityCount(CityFilter filter) {
		StringBuilder whereCluse = new StringBuilder("");
		Integer count = 0;
		try {

			
			if (filter.getName() != null && !filter.getName().isEmpty())
				whereCluse.append(" and c.cityName LIKE :cityName ");

			if (filter.getCityCode() != null && !filter.getCityCode().isEmpty())
				whereCluse.append(" amd c.code LIKE :code");
			
			String countQuery = GET_CITY_COUNT_BASEDON_FILTER.toString().concat(whereCluse.toString()).replaceFirst("and", "where")
					.split("limit")[0];
			System.out.println("*******************************count" + countQuery);
			Query queryCount = entityManager.createNativeQuery(countQuery);

			if (filter.getName() != null && !filter.getName().isEmpty()) {
				queryCount.setParameter("cityName", "%"+filter.getName()+"%");
			}

			if (filter.getCityCode() != null && !filter.getCityCode().isEmpty()) {
				queryCount.setParameter("code", "%"+filter.getCityCode()+"%");
			}
			BigInteger totalCityCount = (BigInteger) queryCount.getResultList().stream().findFirst().orElse(0);
			count = totalCityCount.intValue();
		} catch (Exception e) {
		}
		return count;
	}
	
	@Override
	public List<CityDTO> findCityDetailsByFilter(CityFilter filter) {
		StringBuilder whereCluse = new StringBuilder("");
		List<CityDTO> list = new ArrayList<CityDTO>();
		try {

			if (filter.getName() != null && !filter.getName().isEmpty())
				whereCluse.append(" and c.cityName LIKE :cityName ");

			/*
			 * if (filter.getName() != null && filter.getCityCode() != null) {
			 * whereCluse.append(" and "); }
			 */
			if (filter.getCityCode() != null && !filter.getCityCode().isEmpty())
				whereCluse.append(" and c.code LIKE :code");

			if (filter.getPageSize() > 0 && filter.getOffset() >= 0) {
				whereCluse.append(" limit " + filter.getOffset() + " , " + filter.getPageSize());
			}

			String completeQuery = GET_CITY_BY_FILTER.toString().concat(whereCluse.toString()).replaceFirst("and",
					"where");
			
			System.out.println("**********************"+completeQuery);
			Query query = entityManager.createNativeQuery(completeQuery);
			if (filter.getName() != null && !filter.getName().isEmpty()) {
				query.setParameter("cityName", "%"+filter.getName()+"%");
			}

			if (filter.getCityCode() != null && !filter.getCityCode().isEmpty()) {
				query.setParameter("code", "%"+filter.getCityCode()+"%");
			}

			List<Object[]> assetList = query.getResultList();
			System.out.println("************" + assetList);
			for (Object obj[] : assetList) {
				CityDTO vo = new CityDTO();
				vo.setCityName(String.valueOf(obj[0]));
				vo.setCode(String.valueOf(obj[1]));
				vo.setState(String.valueOf(obj[2]));
				vo.setStatus(String.valueOf(obj[3]));
				list.add(vo);

			}

			System.out.println("City details value after setting is :::::::" + assetList);

		} catch (Exception e) {
			System.out.println("Exception:::::::::" + e);
		}

		return list;
	}

}
