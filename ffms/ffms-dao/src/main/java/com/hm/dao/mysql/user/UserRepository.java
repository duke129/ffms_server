/**
 * 
 */
package com.hm.dao.mysql.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hm.util.entity.User;

/**
 * @author kiran
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{

}
