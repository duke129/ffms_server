package com.hm.productspecification.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.mysql.productSpecification.ProductSpecDao;
import com.hm.productspecification.controller.ProductSpecificationController;
import com.hm.util.model.ProductSpecificationDTO;

@Service
public class ProductSpecManagerImpl implements ProductSpecManager {

	private static final Logger logger = LoggerFactory.getLogger(ProductSpecManagerImpl.class);
	@Autowired
	ProductSpecDao productSpecDao;
	@Override
	public List<ProductSpecificationDTO> findProductSpecByProductId(String id) {
		List<ProductSpecificationDTO> ProductSpecList=productSpecDao.findProductSpecByProductId(Long.valueOf(id));
		logger.info("ProductSpecManagerImpl ::: ProductSpecList :::"+ProductSpecList);
		return ProductSpecList;
		
	}

}
