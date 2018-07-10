/**
 * 
 */
package com.hm.dao.mysql.productSpecification;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hm.util.entity.Product;
import com.hm.util.entity.ProductSpecification;
import com.hm.util.model.ProductDTO;
import com.hm.util.model.ProductSpecificationDTO;
import com.hm.util.model.TicketDetails;



/**
 * @author kiran
 *
 */

@Repository
public class ProductSpecDaoImpl implements ProductSpecDao{

	private static final Logger logger = LoggerFactory.getLogger(ProductSpecDaoImpl.class);

	
	private static final String model_Spec_Details_Query="select productId,propertyName, propertyValue from ProductSpecification where productId=?";
	
	@Autowired
	ProductSpecRepository productRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
		@Override
		public List<ProductSpecificationDTO> findProductSpecByProductId(Long id) {
			
			List<ProductSpecificationDTO> productSpecDTOList = new ArrayList<ProductSpecificationDTO>();
			
			List<Object[]> product = entityManager.createNativeQuery(model_Spec_Details_Query).setParameter(1,id).getResultList();
		
			for (Object[] objects : product) {
				ProductSpecificationDTO productSpecDTO=new ProductSpecificationDTO();
				productSpecDTO.setProductId(String.valueOf(objects[0]));
				productSpecDTO.setPropertyName(String.valueOf(objects[1]));
				productSpecDTO.setPropertyValue(String.valueOf(objects[2]));
				productSpecDTOList.add(productSpecDTO);
			}
			logger.info("product list is ::::"+productSpecDTOList);
			return productSpecDTOList;
		}

	
			

}
