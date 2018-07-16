/**
 * 
 */
package com.hm.order.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hm.dao.mysql.order.OrderDao;
import com.hm.dao.mysql.ticket.TicketDao;
import com.hm.util.entity.Order;
import com.hm.util.model.APIResponse;
import com.hm.util.model.OrderActivityUpdate;
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
	
	@Autowired
	TicketDao ticketDao;

	@Override
	public APIResponse saveOrder(OrderActivityUpdate orderActivityUpdate) {
		
		APIResponse response = new APIResponse();
		
		if(orderActivityUpdate != null)
		{
			List<Order> ordersSaved = orderDao.saveOrder(orderActivityUpdate);
			
			if(ordersSaved != null)
			{
				response.setStatusId(200);
				response.setStatusMessage("Successfully saved");
				
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
			OrderActivityUpdate orderActivityUpdate = new OrderActivityUpdate();
			
			List<OrderVo> ordersList = orderDao.getOrdersByTicketId(ticketId);
			orderActivityUpdate.setOrdersVo(ordersList);
			orderActivityUpdate.setTicketId(ticketId);
			orderActivityUpdate.setComments(ticketDao.getTicketCommentsByTicketId(ticketId));
			
			response.setStatusId(200);
			response.setStatusMessage("Success");
			response.setData(orderActivityUpdate);
			
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
