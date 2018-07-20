/**
 * 
 */
package com.hm.dao.mysql.order;

import java.util.List;

import com.hm.util.entity.Order;
import com.hm.util.model.OrderActivityUpdate;
import com.hm.util.model.OrderVo;

/**
 * @author kiran
 *
 */
public interface OrderDao {
	
	List<Order> saveOrder(OrderActivityUpdate orderActivityUpdate);
	
	List<OrderVo> getOrdersByTicketId(Long ticketId);

}
