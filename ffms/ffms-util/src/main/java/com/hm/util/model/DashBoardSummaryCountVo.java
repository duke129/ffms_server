package com.hm.util.model;

public class DashBoardSummaryCountVo {

	private String statusName;
	private int totalCounts;
	
	public String getStatusName() {
		return statusName;
	}
	
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	public int getTotalCounts() {
		return totalCounts;
	}
	
	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
	}

	@Override
	public String toString() {
		return "DashBoardSummaryCountVo [statusName=" + statusName + ", totalCounts=" + totalCounts + "]";
	}
	
	

}
