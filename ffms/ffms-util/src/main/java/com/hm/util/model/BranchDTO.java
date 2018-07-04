package com.hm.util.model;

import com.hm.util.entity.City;
import com.hm.util.entity.Status;

public class BranchDTO {
	
	private String idBranch;
	private String branchName;
	private City city;
	private Status statusBean;
	
	public String getIdBranch() {
		return idBranch;
	}
	public void setIdBranch(String idBranch) {
		this.idBranch = idBranch;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Status getStatusBean() {
		return statusBean;
	}
	public void setStatusBean(Status statusBean) {
		this.statusBean = statusBean;
	}
	@Override
	public String toString() {
		return "BranchDTO [idBranch=" + idBranch + ", branchName=" + branchName + ", city=" + city + ", statusBean="
				+ statusBean + "]";
	}
	
	
		

}
