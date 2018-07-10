/**
 * 
 */
package com.hm.dao.mysql.order;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hm.util.entity.Order;

/**
 * @author kiran
 *
 */
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	List<Order> findByTicketId(BigInteger ticketId);

}
