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

import com.hm.util.entity.Order;
import com.hm.util.model.OrderVo;

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
	
	private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

	@Override
	public List<Order> saveOrder(List<OrderVo> ordersVo) {
		
		if(ordersVo != null &&  !ordersVo.isEmpty())
		{
			List<Order> orders = new ArrayList<Order>();
			
			ordersVo.forEach(orderVo -> {
				
				Order order = new Order();
				logger.info("order vo :: " +orderVo);
				order.setPrice(orderVo.getPrice());
				order.setQuantity(orderVo.getQuantity());
				order.setTicketId(BigInteger.valueOf(orderVo.getTicketId()));
				order.setProductId(BigInteger.valueOf(orderVo.getProductId()));
				
				orders.add(order);
				
			});
			
			
			return orderRepository.saveAll(orders);
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
				orderVo.setTicketId(order.getTicketId().longValue());
				returingOrders.add(orderVo);
			});
			
			return returingOrders;
		}
		
		return returingOrders;
	}

}
