/**
 * 
 */
package com.hm.dao.mysql.order;

import java.util.List;

import com.hm.util.entity.Order;
import com.hm.util.model.OrderVo;

/**
 * @author kiran
 *
 */
public interface OrderDao {
	
	List<Order> saveOrder(List<OrderVo> orderVo);
	
	List<OrderVo> getOrdersByTicketId(Long ticketId);

}
