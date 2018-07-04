/**
 * 
 */
package com.hm.location.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hm.location.manager.LocationManager;
import com.hm.util.model.AreaDTO;
import com.hm.util.model.BranchDTO;
import com.hm.util.model.CityDTO;

/**
 * @author Pawan
 *
 */
@Controller
@RestController
@RequestMapping("/location")
@CrossOrigin
public class LocationController {
	
	private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
	@Autowired
	LocationManager locationService;
	
	@RequestMapping(value = "/city/save", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCity(@RequestBody CityDTO citydto,UriComponentsBuilder ucBuilder) {
		logger.info("updating city controller is called");
		logger.info("Updating city with cityId::: " +citydto);
 
//        if (locationService.isCityExist(citydto.getIdCity())) {
//            System.out.println("A city with name " + citydto.getCityName() + " already exist");
//            return new ResponseEntity<String>(HttpStatus.CONFLICT);
//        }
 
       locationService.saveCity(citydto);
 
       
//    	   HttpHeaders headers = new HttpHeaders();
//           headers.setLocation(ucBuilder.path("/city/{idCity}").buildAndExpand(citydto.getIdCity()).toUri());
          
    	   return new ResponseEntity<Void>(HttpStatus.CREATED);
        
    }
	
	
	@RequestMapping(value = "/branch/save", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBranch(@RequestBody BranchDTO branchDto,UriComponentsBuilder ucBuilder) {
		logger.info("saving Branch controller is called");
		logger.info("Creating Branch " +branchDto);
 
//        if (locationService.isCityExist(citydto.getIdCity())) {
//            System.out.println("A city with name " + citydto.getCityName() + " already exist");
//            return new ResponseEntity<String>(HttpStatus.CONFLICT);
//        }
 
            locationService.saveBranch(branchDto);
    	  // HttpHeaders headers = new HttpHeaders();
          // headers.setLocation(ucBuilder.path("/branch/{idBranch}").buildAndExpand(branchDto.getIdBranch()).toUri());
           return new ResponseEntity<Void>(HttpStatus.CREATED);
       
        
    }
	
	
	
	@RequestMapping(value = "/area/save", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createArea(@RequestBody AreaDTO areaDTO,UriComponentsBuilder ucBuilder) {
		logger.info("saving Area controller is called");
		logger.info("Creating Area " +areaDTO);
 
//        if (locationService.isCityExist(citydto.getIdCity())) {
//            System.out.println("A city with name " + citydto.getCityName() + " already exist");
//            return new ResponseEntity<String>(HttpStatus.CONFLICT);
//        }
 
        	locationService.saveArea(areaDTO);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
       
        
    }
	
	
	//-------------------Retrieve All City--------------------------------------------------------
    
    @RequestMapping(value = "/city/all", method = RequestMethod.GET)
    public ResponseEntity<List<CityDTO>> listAllCity() {
        List<CityDTO> cityDTO = locationService.findAllCity();
        logger.info("In location controller listofcity details is :::"+cityDTO);
        if(cityDTO.isEmpty()){
            return new ResponseEntity<List<CityDTO>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<CityDTO>>(cityDTO, HttpStatus.OK);
    }
	
    
//-------------------Retrieve All Branch--------------------------------------------------------
    
    @RequestMapping(value = "/branch/all", method = RequestMethod.GET)
    public ResponseEntity<List<BranchDTO>> listAllBranch() {
        List<BranchDTO> branchDTO = locationService.findAllBranch();
        logger.info("In location controller listofcity details is :::"+branchDTO);
        if(branchDTO.isEmpty()){
            return new ResponseEntity<List<BranchDTO>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<BranchDTO>>(branchDTO, HttpStatus.OK);
    }
	
    
//-------------------Retrieve All Area--------------------------------------------------------
    
    @RequestMapping(value = "/area/all", method = RequestMethod.GET)
    public ResponseEntity<List<AreaDTO>> listAllArea() {
        List<AreaDTO> areaDTO = locationService.findAllArea();
        if(areaDTO.isEmpty()){
            return new ResponseEntity<List<AreaDTO>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<AreaDTO>>(areaDTO, HttpStatus.OK);
    }

}
