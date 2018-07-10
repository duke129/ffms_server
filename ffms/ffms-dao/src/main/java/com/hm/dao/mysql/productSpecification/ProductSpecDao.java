/**
 * 
 */
package com.hm.dao.mysql.productSpecification;

import java.util.List;

import com.hm.util.entity.Area;
import com.hm.util.model.AreaDTO;
import com.hm.util.model.ProductDTO;
import com.hm.util.model.ProductSpecificationDTO;

/**
 * @author pawan
 *
 */
public interface ProductSpecDao {
	
	
	List<ProductSpecificationDTO> findProductSpecByProductId(Long id);
	
	

}
