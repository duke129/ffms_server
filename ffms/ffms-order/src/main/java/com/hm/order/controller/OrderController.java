/**
 * 
 */
package com.hm.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hm.order.manager.OrderManager;
import com.hm.util.model.APIResponse;
import com.hm.util.model.OrderVo;

/**
 * @author kiran
 *
 */
@Controller
@RestController
@CrossOrigin
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	OrderManager orderManager;
	
	
	@PostMapping("save")
	public APIResponse saveOrder(@RequestBody List<OrderVo> ordersVo)
	{
		System.out.println("@@@@@@@@ order :: "+ordersVo);
		return orderManager.saveOrder(ordersVo);
	}
	
	@GetMapping("getByTicketId/{ticketId}")
	public APIResponse getOrdersByTicketId(@PathVariable("ticketId") Long ticketId)
	{
		return orderManager.getOrdersByTicketId(ticketId);
	}

}
