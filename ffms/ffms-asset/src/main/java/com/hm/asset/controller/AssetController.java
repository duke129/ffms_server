package com.hm.asset.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hm.asset.manager.AssetManager;
import com.hm.util.model.APIResponse;
import com.hm.util.model.AssetVo;
import com.hm.util.model.ProductDTO;
import com.hm.util.model.TypeHeadVo;
import com.hm.util.model.filter.AssetFilter;

@RestController
@RequestMapping("/asset")
public class AssetController {

	private static final Logger logger = LoggerFactory.getLogger(AssetController.class);
	@Autowired
	AssetManager assetManager;

	@CrossOrigin
	@GetMapping(path = "/list")
	public List<AssetVo> getAllAsset() {

		List<AssetVo> listOfAsset = assetManager.findAllAsset();
		return listOfAsset;
	}
	
	
	
	@RequestMapping(value = "/selection", method = RequestMethod.GET)
    public APIResponse getListForAssetSelection() {
		APIResponse aPIResponse=new APIResponse();
        List<TypeHeadVo> typeheadvoListForAssetSelection = assetManager.getAssetForSelection();
        aPIResponse.setStatusId(200);
        aPIResponse.setStatusMessage("successful");
        aPIResponse.setData(typeheadvoListForAssetSelection);
        logger.info("In Asset controller typeheadvoListForAssetSelection details is :::"+typeheadvoListForAssetSelection);
        return aPIResponse;
    }
	

	@RequestMapping(value = "/product/selection", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> getListForProductSelection() {
        List<ProductDTO> typeheadvoListForAssetSelection = assetManager.getProductDetails();
        logger.info("In Asset controller typeheadvoListForAssetSelection details is :::"+typeheadvoListForAssetSelection);
        if(typeheadvoListForAssetSelection.isEmpty()){
            return new ResponseEntity<List<ProductDTO>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ProductDTO>>(typeheadvoListForAssetSelection, HttpStatus.OK);
    }
	
	@CrossOrigin
	@PostMapping(path = "/add")
	public ResponseEntity<Void> addAsset(@RequestBody AssetVo assetVo) {

		logger.info("addAsset payload" + assetVo);
		assetManager.addAsset(assetVo);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@CrossOrigin
	@GetMapping(path = "/type/list")
	public List<TypeHeadVo> findAllAssetType() {

		return assetManager.findAllAssetType();

	}

	@CrossOrigin
	@PostMapping(path = "/filter")
	public List<AssetVo> findAssetByFilter(@RequestBody AssetFilter filter) {

		logger.info(" findAssetByFilter payload :: " + filter);
		List<AssetVo> listOfAsset = assetManager.findAssetByFilter(filter);
		return listOfAsset;
	}

	@CrossOrigin
	@PostMapping(path = "/update")
	public ResponseEntity<Void> updateAsset(@RequestBody AssetVo assetVo) {
		logger.info("updateAsset payload :: " + assetVo);
		
		assetManager.updateAsset(assetVo);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	
}
