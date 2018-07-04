package com.hm.util.model;

public class AreaDTO {
	
	private Long areaId;
	private String areaName;
	private String branchName;
	private String branchId;
	private String status;
	private String areaCode;
	
	
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	@Override
	public String toString() {
		return "AreaDTO [areaId=" + areaId + ", areaName=" + areaName + ", branchName=" + branchName + ", branchId="
				+ branchId + ", status=" + status + ", areaCode=" + areaCode + "]";
	}
	
	
	

}
