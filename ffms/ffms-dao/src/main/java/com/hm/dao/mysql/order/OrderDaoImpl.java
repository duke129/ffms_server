/**
 * 
 */
package com.hm.dao.mysql.order;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hm.dao.mysql.product.ProductRepository;
import com.hm.dao.mysql.ticket.TicketDao;
import com.hm.dao.mysql.ticketlog.TicketActivityLogDao;
import com.hm.util.FFMSConstant;
import com.hm.util.entity.Order;
import com.hm.util.model.OrderActivityUpdate;
import com.hm.util.model.OrderVo;
import com.hm.util.model.TicketActivityLogVo;

/**
 * @author kiran
 *
 */
@Transactional
@Repository
public class OrderDaoImpl implements OrderDao{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	TicketActivityLogDao ticketActivityLogDao;
	
	@Autowired
	TicketDao ticketDao;
	
	@Autowired
	ProductRepository productRepository;
		
	private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

	@Override
	public List<Order> saveOrder(OrderActivityUpdate orderActivityUpdate) {
		
		if(orderActivityUpdate.getOrdersVo() != null &&  !orderActivityUpdate.getOrdersVo().isEmpty())
		{
			
			List<Order> orders = new ArrayList<Order>();
			
			orderActivityUpdate.getOrdersVo().forEach(orderVo -> {
								
				Order order = new Order();
				
				order.setPrice(orderVo.getPrice());
				order.setQuantity(orderVo.getQuantity());
				order.setTicketId(BigInteger.valueOf(orderActivityUpdate.getTicketId()));
				order.setProductId(BigInteger.valueOf(orderVo.getProductId()));
				
				orders.add(order);
				
			});
			
			List<Order> savedOrders = orderRepository.saveAll(orders);
			
			if(savedOrders != null && !savedOrders.isEmpty())
			{
				List<TicketActivityLogVo> ticketActivityLogs = new ArrayList<TicketActivityLogVo>();
				
				TicketActivityLogVo demoTicketActivityLog = new TicketActivityLogVo();
				 
				demoTicketActivityLog.setActivityId(FFMSConstant.ActivityConstant.DEMO);
				demoTicketActivityLog.setTicketId(orderActivityUpdate.getTicketId());
				demoTicketActivityLog.setActivityStatus(FFMSConstant.ACTIVITY_COMPLETED);
				ticketActivityLogs.add(demoTicketActivityLog);
				
				TicketActivityLogVo orderTicketActivityLog = new TicketActivityLogVo();
				 
				orderTicketActivityLog.setActivityId(FFMSConstant.ActivityConstant.ORDER);
				orderTicketActivityLog.setTicketId(orderActivityUpdate.getTicketId());
				orderTicketActivityLog.setActivityStatus(FFMSConstant.ACTIVITY_COMPLETED);
				ticketActivityLogs.add(orderTicketActivityLog);
				
				ticketActivityLogDao.saveTicketActivityLog(ticketActivityLogs);
				
				ticketDao.closeTicket(orderActivityUpdate.getComments(), orderActivityUpdate.getTicketId());
				 
			}
			return savedOrders;
		}
		
		return null;
	}

	@Override
	public List<OrderVo> getOrdersByTicketId(Long ticketId) {
		
		List<OrderVo> returingOrders = new ArrayList<OrderVo>();
		
		if(ticketId != null && ticketId > 0)
		{
			
			List<Order> orders = orderRepository.findByTicketId(BigInteger.valueOf(ticketId));
			
			orders.forEach(order -> {
				
				OrderVo orderVo = new OrderVo();
				BeanUtils.copyProperties(order, orderVo);
				orderVo.setProductId(order.getProductId().longValue());
				orderVo.setProductName(productRepository.findById(order.getProductId().longValue()).get().getName());
				//orderVo.setTicketId(order.getTicketId().longValue());
				returingOrders.add(orderVo);
			});
			
			return returingOrders;
		}
		
		return returingOrders;
	}

}
