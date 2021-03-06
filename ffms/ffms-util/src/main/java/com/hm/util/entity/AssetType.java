package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AssetType database table.
 * 
 */
@Entity
@NamedQuery(name="AssetType.findAll", query="SELECT a FROM AssetType a")
public class AssetType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAssetType;

	private String assetTypeDescription;

	/*//bi-directional many-to-one association to Asset
	@OneToMany(mappedBy="assetType")
	private List<Asset> assets;
*/
	public AssetType() {
	}

	public int getIdAssetType() {
		return this.idAssetType;
	}

	public void setIdAssetType(int idAssetType) {
		this.idAssetType = idAssetType;
	}

	public String getAssetTypeDescription() {
		return this.assetTypeDescription;
	}

	public void setAssetTypeDescription(String assetTypeDescription) {
		this.assetTypeDescription = assetTypeDescription;
	}

	@Override
	public String toString() {
		return "AssetType [idAssetType=" + idAssetType + ", assetTypeDescription=" + assetTypeDescription + "]";
	}

	
}