/**
 * 
 */
package com.hm.dao.mysql.city;

import java.util.List;

import com.hm.util.entity.City;
import com.hm.util.model.CityDTO;

/**
 * @author pawan
 *
 */
public interface CityDao {
	
	void saveCity(CityDTO cityDto);
	List<City> findAllCity();
	Integer getTotalCityCount();
	Integer getTotalBranchCount();
	Integer getTotalAreaCount();
	

}
