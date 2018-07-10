/**
 * 
 */
package com.hm.util.model;

import java.sql.Date;

/**
 * @author kiran
 *
 */
public class TicketActivityLogVo {
	
	private Long ticketId;
	private Integer activityId;
	private Integer activityStatus;
	private String vendorId;
	private String reasons;
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	@Override
	public String toString() {
		return "TicketActivityLogVo [ticketId=" + ticketId + ", activityId=" + activityId + ", activityStatus="
				+ activityStatus + ", vendorId=" + vendorId + ", reasons=" + reasons + "]";
	}
	
	

}
