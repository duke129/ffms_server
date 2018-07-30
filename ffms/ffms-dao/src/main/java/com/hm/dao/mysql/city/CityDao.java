/**
 * 
 */
package com.hm.dao.mysql.city;

import java.util.List;

import com.hm.util.entity.City;
import com.hm.util.model.BranchDTO;
import com.hm.util.model.CityDTO;
import com.hm.util.model.filter.BranchFilter;
import com.hm.util.model.filter.CityFilter;

/**
 * @author pawan
 *
 */
public interface CityDao {
	
	void saveCity(CityDTO cityDto);
	List<City> findAllCity();
	Integer getTotalCityCount(CityFilter filter);
	
	
	List<CityDTO> findCityDetailsByFilter(CityFilter filter);
	
	
	

}
