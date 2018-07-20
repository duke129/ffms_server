package com.hm.product.manager;

import java.util.List;

import com.hm.util.model.APIResponse;
import com.hm.util.model.ImageDTO;
import com.hm.util.model.ProductDTO;

public interface ProductManager {
	
	List<ProductDTO> findProductByAssetId(Long assetId);
	
	List<ImageDTO> findProductImageListByProductId(String modelId);
	
	APIResponse getImageByProductId(Long id);

}
