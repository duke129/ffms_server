/**
 * 
 */
package com.hm.order.manager;


import java.util.List;

import org.springframework.stereotype.Service;

import com.hm.util.model.APIResponse;
import com.hm.util.model.OrderActivityUpdate;
import com.hm.util.model.OrderVo;

/**
 * @author kiran
 *
 */
@Service
public interface OrderManager {

	APIResponse saveOrder(OrderActivityUpdate orderActivityUpdate);
	
	APIResponse getOrdersByTicketId(Long ticketId);
}
