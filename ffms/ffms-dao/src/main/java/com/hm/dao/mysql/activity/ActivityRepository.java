/**
 * 
 */
package com.hm.dao.mysql.activity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hm.util.entity.Activity;

/**
 * @author kiran
 *
 */
public interface ActivityRepository extends JpaRepository<Activity, Integer>{

}
