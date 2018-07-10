package com.hm.productspecification.manager;

import java.util.List;

import com.hm.util.model.ProductSpecificationDTO;

public interface ProductSpecManager {
	
	List<ProductSpecificationDTO> findProductSpecByProductId(String id);

}
