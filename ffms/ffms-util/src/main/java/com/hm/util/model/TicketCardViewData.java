package com.hm.util.model;

import java.util.Date;

public class TicketCardViewData {
	
	private Long ticketId;
	private String ticketNumber;
	private String customerName;
	private String customerMobileNumber;
	private String customerAddress;
	private Date ticketCreationDate;
	private Date ticketETR;
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}
	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public Date getTicketCreationDate() {
		return ticketCreationDate;
	}
	public void setTicketCreationDate(Date ticketCreationDate) {
		this.ticketCreationDate = ticketCreationDate;
	}
	public Date getTicketETR() {
		return ticketETR;
	}
	public void setTicketETR(Date ticketETR) {
		this.ticketETR = ticketETR;
	}
	
	
	
	
	

}
