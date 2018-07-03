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

	private Long idCity;
	private String cityName;
	private Date createdOn;
	private Date modifiedOn;
	private String statusBean;

	public Long getIdCity() {
		return idCity;
	}

	public void setIdCity(Long idCity) {
		this.idCity = idCity;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public String getStatusBean() {
		return statusBean;
	}

	public void setStatusBean(String statusBean) {
		this.statusBean = statusBean;
	}

	@Override
	public String toString() {
		return "CityDTO [idCity=" + idCity + ", cityName=" + cityName + ", createdOn=" + createdOn + ", modifiedOn="
				+ modifiedOn + ", statusBean=" + statusBean + "]";
	}

}
