package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Orders database table.
 * 
 */
@Entity
@Table(name="Orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idOrder;

	private String price;

	private java.math.BigInteger productId;

	private int quantity;

	private java.math.BigInteger ticketId;

	public Order() {
	}

	public Long getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public java.math.BigInteger getProductId() {
		return this.productId;
	}

	public void setProductId(java.math.BigInteger productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public java.math.BigInteger getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(java.math.BigInteger ticketId) {
		this.ticketId = ticketId;
	}

}