/**
 * 
 */
package com.hm.asset.manager;

import java.util.List;

import com.hm.util.model.AssetVo;
import com.hm.util.model.ProductDTO;
import com.hm.util.model.TypeHeadVo;

public interface AssetManager {
	public List<AssetVo> findAllAsset();
	
	public List<TypeHeadVo> getAssetForSelection();
	
	public List<ProductDTO> getProductDetails();

}
