/**
 * 
 */
package com.hm.dao.mysql.status;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hm.util.entity.Status;

/**
 * @author kiran
 *
 */
@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>{

}
