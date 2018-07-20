package com.hm.util.model.filter;

import java.util.Date;

public class FfmsFilter {
	private String name;
	private String ticketNo;
	private String mobileNo;
	private Date startDate;
	private Date endDate;
	private int days;
	private String sortBy;
	private int offset;
	private int pageSize;
	private int status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	@Override
	public String toString() {
		return "FfmsFilter [name=" + name + ", ticketNo=" + ticketNo + ", mobileNo=" + mobileNo + ", startDate="
				+ startDate + ", endDate=" + endDate + ", days=" + days + ", sortBy=" + sortBy + ", offset=" + offset
				+ ", pageSize=" + pageSize + ", status=" + status + "]";
	}

}
