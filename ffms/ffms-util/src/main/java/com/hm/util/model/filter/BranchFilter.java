package com.hm.util.model.filter;

public class BranchFilter extends FfmsFilter {
	private String branchId;
	private String branchName;
    private String cityId;
    private String cityName;
    private String statusId;
	private String code;
	private String state;
	
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "BranchFilter [branchId=" + branchId + ", branchName=" + branchName + ", cityId=" + cityId
				+ ", cityName=" + cityName + ", statusId=" + statusId + ", code=" + code + ", state=" + state + "]";
	}

	
	
	
	

}
