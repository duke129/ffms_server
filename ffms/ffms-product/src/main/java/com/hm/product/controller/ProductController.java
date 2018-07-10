package com.hm.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hm.dao.mysql.product.ProductDaoImpl;
import com.hm.product.manager.ProductManager;
import com.hm.util.model.APIResponse;
import com.hm.util.model.ImageDTO;
import com.hm.util.model.ProductDTO;
import com.hm.util.model.ProductSpecificationDTO;

@Controller
@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductManager productManager;
	
	 //-------------------Retrieve product details on Asset Id --------------------------------------------------------
	
	@RequestMapping(value = "/assetType/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResponse getProductByAssetId(@PathVariable("id") long id) {
		APIResponse apiResponse=new APIResponse();
		logger.info("Fetching User with id " + id);
        List<ProductDTO> productDTO = productManager.findProductByAssetId(id);
        
        if (productDTO == null) {
        	logger.info("Product with id " + id + " not found");
            apiResponse.setStatusId(404);
            apiResponse.setStatusMessage("Record not found");
            apiResponse.setData(null);
            return apiResponse;
        }
        apiResponse.setStatusId(200);
        apiResponse.setStatusMessage("successful");
        apiResponse.setData(productDTO);
        return apiResponse;
    }
	
	 //-------------------Retrieve Image List of the product based on Product Id --------------------
	
	@RequestMapping(value = "/imagelist/model/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResponse getProductImageListByProductId(@PathVariable("id") String id) {
		
		APIResponse apiResponse=new APIResponse();
		logger.info("Fetching Product details  with product id " + id);
        List<ImageDTO> productSpecList = productManager.findProductImageListByProductId(id);
        if (productSpecList == null) {
        	apiResponse.setStatusId(404);
        	apiResponse.setStatusMessage("Record not found");
        	apiResponse.setData(null);
            return apiResponse;
        }
        apiResponse.setStatusId(200);
        apiResponse.setStatusMessage("Successful");
        apiResponse.setData(productSpecList);
        return apiResponse;
    }

}
