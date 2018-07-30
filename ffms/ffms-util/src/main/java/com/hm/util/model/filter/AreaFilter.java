package com.hm.util.model.filter;

public class AreaFilter extends FfmsFilter {
	private String areaId;
	private String areaName;
	private String branchName;
	private String branchId;
	private String statusId;
	private String code;
	private String cityId;
	private String cityName;
	
	
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
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
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Override
	public String toString() {
		return "AreaFilter [areaId=" + areaId + ", areaName=" + areaName + ", branchName=" + branchName + ", branchId="
				+ branchId + ", statusId=" + statusId + ", code=" + code + ", cityId=" + cityId + ", cityName="
				+ cityName + "]";
	}
	
	
	
	
	
	
	

}
