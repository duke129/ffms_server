/**
 * 
 */
package com.hm.dao.mysql.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hm.util.entity.City;

/**
 * @author kiran
 *
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
