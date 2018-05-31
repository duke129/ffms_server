/**
 * 
 */
package com.hm.location.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kiran
 *
 */
@Controller
@RestController
public class LocationController {
	
	@GetMapping(path = "/location")
	public String locationTest()
	{
		return "Location Tested";
		
	}

}
