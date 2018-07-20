/**
 * 
 */
package com.hm.dao.mysql.product;

import java.util.List;

import com.hm.util.entity.Area;
import com.hm.util.model.AreaDTO;
import com.hm.util.model.ImageDTO;
import com.hm.util.model.ProductDTO;

/**
 * @author pawan
 *
 */
public interface ProductDao {
	
	List<ProductDTO> findAllProductDetails();
	
	List<ProductDTO> findAllProductByAssetId(Long id);
	
	ProductDTO findProductImageListByProductId(Long modelId);
	
	String getImageByProductId(Long id);
	

}
