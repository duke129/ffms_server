/**
 * 
 */
package com.hm.order.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hm.dao.mysql.order.OrderDao;
import com.hm.util.entity.Order;
import com.hm.util.model.APIResponse;
import com.hm.util.model.OrderVo;

/**
 * @author kiran
 *
 */
@Transactional
@Repository
public class OrderManagerImpl implements OrderManager{
	
	@Autowired
	OrderDao orderDao;

	@Override
	public APIResponse saveOrder(List<OrderVo> ordersVo) {
		
		APIResponse response = new APIResponse();
		
		if(ordersVo != null)
		{
			List<Order> ordersSaved = orderDao.saveOrder(ordersVo);
			
			if(ordersSaved != null)
			{
				response.setStatusId(200);
				response.setStatusMessage("Successfully saved");
				response.setData(ordersSaved);
				
				return response;
			}
			else
			{
				response.setStatusId(205);
				response.setStatusMessage("Failed while saving");
				return response;
				
			}
		}
		else
		{
			response.setStatusId(206);
			response.setStatusMessage("Request body can't be empty");
			return response;
		}
		
	}

	@Override
	public APIResponse getOrdersByTicketId(Long ticketId) {
		
		APIResponse response = new APIResponse();
		
		if(ticketId != null && ticketId > 0)
		{
			List<OrderVo> ordersList = orderDao.getOrdersByTicketId(ticketId);
			
			response.setStatusId(200);
			response.setStatusMessage("Success");
			response.setData(ordersList);
			
			return response;
		}
		else
		{
			response.setStatusId(205);
			response.setStatusMessage("Ticket id required ");
			
			return response;
		}
	}

}
