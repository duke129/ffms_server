/**
 * 
 */
package com.hm.util.model;

import java.util.Date;

import com.hm.util.entity.Status;

/**
 * @author pawan
 *
 */
public class CityDTO {

	private String cityId;
	private String cityName;
	private String statusId;
	private String code;
	private String state;
	private String status;
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		return "CityDTO [cityId=" + cityId + ", cityName=" + cityName + ", statusId=" + statusId + ", code=" + code
				+ ", state=" + state + ", status=" + status + "]";
	}
	
	
	
	

	
	
}