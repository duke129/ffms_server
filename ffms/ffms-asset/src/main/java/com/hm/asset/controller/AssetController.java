package com.hm.asset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class AssetController {
	
	@GetMapping(path = "/asset")
	public String assetTest()
	{
		return "hi";
		
	}

}
