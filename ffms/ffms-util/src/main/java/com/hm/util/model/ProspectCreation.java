/**
 * 
 */
package com.hm.util.model;

import java.util.Date;

/**
 * @author kiran
 *
 */
public class ProspectCreation {
	
	private String ticketDescription;
	private Long ticketTypeId;
	private Date prefferdCallTime;
	private CustomerVo customer;
	private Long assetId;

	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	public Long getTicketTypeId() {
		return ticketTypeId;
	}

	public void setTicketTypeId(Long ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}

	public Date getPrefferdCallTime() {
		return prefferdCallTime;
	}

	public void setPrefferdCallTime(Date prefferdCallTime) {
		this.prefferdCallTime = prefferdCallTime;
	}

	public CustomerVo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerVo customer) {
		this.customer = customer;
	}
	
	

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	@Override
	public String toString() {
		return "ProspectCreation [ticketDescription=" + ticketDescription + ", ticketTypeId=" + ticketTypeId
				+ ", prefferdCallTime=" + prefferdCallTime + ", customer=" + customer + "]";
	}
	
	

}
