package com.hm.productspecification.controller;

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
import com.hm.productspecification.manager.ProductSpecManager;
import com.hm.util.model.APIResponse;
import com.hm.util.model.ProductSpecificationDTO;

@Controller
@RestController
@RequestMapping("/productspec")
@CrossOrigin
public class ProductSpecificationController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductSpecificationController.class);
	@Autowired
	ProductSpecManager productSpecManager;

	//-------------------Retrieve product details on Asset Id --------------------------------------------------------
	
		@RequestMapping(value = "/model/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public APIResponse getProductSpecByProductId(@PathVariable("id") String id) {
			APIResponse apiResponse=new APIResponse();
			logger.info("Fetching Product details  with product id " + id);
	        List<ProductSpecificationDTO> productSpecList = productSpecManager.findProductSpecByProductId(id);
	        if (productSpecList == null) {
	        	apiResponse.setData(null);
	        	apiResponse.setStatusId(404);
	        	apiResponse.setStatusMessage("Record not found");
	            return apiResponse;
	        }
	        apiResponse.setStatusId(200);
	        apiResponse.setStatusMessage("Successful");
	        apiResponse.setData(productSpecList);
	        return apiResponse;
	    }
}
