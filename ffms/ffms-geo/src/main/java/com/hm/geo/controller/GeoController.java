/**
 * 
 */
package com.hm.geo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kiran
 *
 */
@Controller
@RestController
public class GeoController {

	@GetMapping(path = "/geo")
	public String geoTest()
	{
		return "Geo tested";
	}
}
