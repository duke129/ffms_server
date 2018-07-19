/**
 * 
 */
package com.hm.asset.manager;

import java.util.List;

import com.hm.util.model.AssetVo;
import com.hm.util.model.ProductDTO;
import com.hm.util.model.TypeHeadVo;
import com.hm.util.model.filter.AssetFilter;

public interface AssetManager {
	public List<AssetVo> findAllAsset();
	
	public List<TypeHeadVo> getAssetForSelection();
	
	public List<ProductDTO> getProductDetails();
	
	public void addAsset(AssetVo assetVo);

	public List<TypeHeadVo> findAllAssetType();

	public void updateAsset(AssetVo assetVo);

	public List<AssetVo> findAssetByFilter(AssetFilter filter);

}
