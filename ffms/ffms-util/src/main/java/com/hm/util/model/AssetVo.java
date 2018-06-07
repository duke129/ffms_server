package com.hm.util.model;

public class AssetVo {

	private Long idAsset;
	private Object assetDescription;
	private int status;
	private String installationLat;
	private String installationLong;
	private Long idAssetType;
	private String assetTypeDes;
	private String assetName;

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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

	public Long getIdAssetType() {
		return idAssetType;
	}

	public void setIdAssetType(Long idAssetType) {
		this.idAssetType = idAssetType;
	}

	@Override
	public String toString() {
		return "AssetVo [idAsset=" + idAsset + ", assetDescription=" + assetDescription + ", status=" + status
				+ ", installationLat=" + installationLat + ", installationLong=" + installationLong + ", idAssetType="
				+ idAssetType + ", assetTypeDes=" + assetTypeDes + "]";
	}

}
