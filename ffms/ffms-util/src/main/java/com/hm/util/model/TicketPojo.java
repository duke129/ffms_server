/**
 * 
 */
package com.hm.util.model;

import java.math.BigInteger;
import java.util.Date;

import com.hm.util.entity.TicketType;

/**
 * @author kiran
 *
 */
public class TicketPojo {
	
	private BigInteger idTicket;
	private Date createdOn;
	private Date modifiedOn;
	private String ticketDescription;
	private BigInteger idAsset;
	private BigInteger idCustomer;
	private Integer status;
	private BigInteger ticketTypeId;
	private BigInteger createdByUserId;
	private BigInteger modifiedByUserId;
	private BigInteger assignedTo;
	public BigInteger getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(BigInteger idTicket) {
		this.idTicket = idTicket;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getTicketDescription() {
		return ticketDescription;
	}
	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}
	public BigInteger getIdAsset() {
		return idAsset;
	}
	public void setIdAsset(BigInteger idAsset) {
		this.idAsset = idAsset;
	}
	public BigInteger getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(BigInteger idCustomer) {
		this.idCustomer = idCustomer;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigInteger getTicketTypeId() {
		return ticketTypeId;
	}
	public void setTicketTypeId(BigInteger ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}
	public BigInteger getCreatedByUserId() {
		return createdByUserId;
	}
	public void setCreatedByUserId(BigInteger createdByUserId) {
		this.createdByUserId = createdByUserId;
	}
	public BigInteger getModifiedByUserId() {
		return modifiedByUserId;
	}
	public void setModifiedByUserId(BigInteger modifiedByUserId) {
		this.modifiedByUserId = modifiedByUserId;
	}
	public BigInteger getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(BigInteger assignedTo) {
		this.assignedTo = assignedTo;
	}
	
	
	
	
	

}
