/**
 * 
 */
package com.hm.dao.mysql.asset;

import java.util.List;

import com.hm.util.entity.Asset;
import com.hm.util.model.AssetVo;
import com.hm.util.model.ProductDTO;
import com.hm.util.model.TypeHeadVo;
import com.hm.util.model.filter.AssetFilter;

/**
 * @author kiran
 *
 */
public interface AssetDao {
	
	String GET_ASSET_BY_FILTER = "Select a.idAsset,a.assetDescription,a.status,a.installationLat,a.installationLong,at.assetTypeDescription,c.firstName as fullName,a.serialNo,c.id  from Asset a inner join AssetType at on at.idAssetType=a.idAssetType left join  CustomerAssetMapping ca on a.idAsset=ca.idAsset"
			+ " left join  Customer c   on c.id=ca.idCustomer ";
	
	String GET_ASSET_BY_COUNT=	"Select count(a.idAsset) from Asset a inner join AssetType at on at.idAssetType=a.idAssetType left join  CustomerAssetMapping ca on a.idAsset=ca.idAsset"
			+ " left join  Customer c   on c.id=ca.idCustomer ";

	public List<AssetVo> findAllAsset();
	
	public void addAsset(Asset asset);

	public List<TypeHeadVo> findAllAssetType();

	public void updateAsset(AssetVo assetVo);

	public List<AssetVo> findAssetByFilter(AssetFilter filter);

}
