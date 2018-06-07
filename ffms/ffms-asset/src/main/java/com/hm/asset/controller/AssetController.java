package com.hm.asset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hm.asset.manager.AssetManager;
import com.hm.util.model.AssetVo;

@RestController
@RequestMapping("/asset")
public class AssetController {

	@Autowired
	AssetManager assetManager;

	@CrossOrigin
	@GetMapping(path = "/list")
	public List<AssetVo> getAllAsset() {

		List<AssetVo> listOfAsset = assetManager.findAll();
		return listOfAsset;
	}

}
