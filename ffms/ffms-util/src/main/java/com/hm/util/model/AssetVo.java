package com.hm.util.model;

import java.math.BigInteger;

public class AssetVo {

	private Long idAsset;
	private Object assetDescription;
	private Integer status;
	private String installationLat;
	private String installationLong;
	private Integer idAssetType;
	private String assetTypeDes;
	private String assetName;
	private String serialNo;
	private String customerName;
	private String customerId;
	private BigInteger count;

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public Long getIdAsset() {
		return idAsset;
	}

	public void setIdAsset(Long idAsset) {
		this.idAsset = idAsset;
	}

	public String getAssetTypeDes() {
		return assetTypeDes;
	}

	public void setAssetTypeDes(String assetTypeDes) {
		this.assetTypeDes = assetTypeDes;
	}

	public Object getAssetDescription() {
		return assetDescription;
	}

	public void setAssetDescription(Object assetDescription) {
		this.assetDescription = assetDescription;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getInstallationLat() {
		return installationLat;
	}

	public void setInstallationLat(String installationLat) {
		this.installationLat = installationLat;
	}

	public String getInstallationLong() {
		return installationLong;
	}

	public void setInstallationLong(String installationLong) {
		this.installationLong = installationLong;
	}

	public Integer getIdAssetType() {
		return idAssetType;
	}

	public void setIdAssetType(Integer idAssetType) {
		this.idAssetType = idAssetType;
	}
	
	

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BigInteger getCount() {
		return count;
	}

	public void setCount(BigInteger count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "AssetVo [idAsset=" + idAsset + ", assetDescription=" + assetDescription + ", status=" + status
				+ ", installationLat=" + installationLat + ", installationLong=" + installationLong + ", idAssetType="
				+ idAssetType + ", assetTypeDes=" + assetTypeDes + "]";
	}

}
