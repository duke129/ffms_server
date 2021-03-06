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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hm.location.manager.LocationManager;
import com.hm.util.model.APIResponse;
import com.hm.util.model.AreaDTO;
import com.hm.util.model.AssetVo;
import com.hm.util.model.BranchDTO;
import com.hm.util.model.CityDTO;
import com.hm.util.model.TypeHeadVo;
import com.hm.util.model.filter.AreaFilter;
import com.hm.util.model.filter.AssetFilter;
import com.hm.util.model.filter.BranchFilter;
import com.hm.util.model.filter.CityFilter;

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
    
    
    @RequestMapping(value = "/branchByCityId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResponse getBranchByCityId(@PathVariable("id") String id) {
        logger.info("Fetching Branch with id " + id);
        List<TypeHeadVo> branchDTOList = locationService.getBranchDetailsByCityId(id);
        APIResponse apiResponse=new APIResponse();
        if (branchDTOList == null || branchDTOList.isEmpty()) {
        	apiResponse.setStatusId(404);
        	apiResponse.setStatusMessage("Record not found");
        	apiResponse.setData(null);
            return apiResponse;
        }
        
        apiResponse.setStatusId(200);
    	apiResponse.setStatusMessage("Successfully");
    	apiResponse.setData(branchDTOList);
    	
        return apiResponse; 
    }

    
    @RequestMapping(value = "/areaByBranchId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResponse getAreaByBranchId(@PathVariable("id") String id) {
        logger.info("Fetching Branch with id " + id);
        List<TypeHeadVo> areaDTOList = locationService.getAreaDetailsByBranchId(id);
        APIResponse apiResponse=new APIResponse();
        if (areaDTOList == null || areaDTOList.isEmpty()) {
        	apiResponse.setStatusId(404);
        	apiResponse.setStatusMessage("Record not found");
        	apiResponse.setData(null);
            return apiResponse;
        }
        
        apiResponse.setStatusId(200);
    	apiResponse.setStatusMessage("Successfully");
    	apiResponse.setData(areaDTOList);
    	
        return apiResponse; 
    }

    
  //-------------------Retrieve Total No.of Count of the City --------------------------------------------------------
    
    @RequestMapping(value = "/citycount", method = RequestMethod.POST)
    public ResponseEntity<Integer> getTotalCityCount(@RequestBody CityFilter filter) {
        Integer areaDTO = locationService.getTotalCityCount(filter);
        logger.info("Total city count is ::::"+areaDTO);
        if(areaDTO==null){
            return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Integer>(areaDTO, HttpStatus.OK);
    }
    
//-------------------Retrieve Total No.of Count of the Branch --------------------------------------------------------
    
    @RequestMapping(value = "/branchcount", method = RequestMethod.POST)
    public ResponseEntity<Integer> getTotalBranchCount(@RequestBody BranchFilter filter) {
        Integer areaDTO = locationService.getTotalBranchCount(filter);
        logger.info("Total city count is ::::"+areaDTO);
        if(areaDTO==null){
            return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Integer>(areaDTO, HttpStatus.OK);
    }
    
    
//-------------------Retrieve Total No.of Count of the Area --------------------------------------------------------
    
    @RequestMapping(value = "/areacount", method = RequestMethod.POST)
    public ResponseEntity<Integer> getTotalAreaCount(@RequestBody AreaFilter filter) {
        Integer areaDTO = locationService.getTotalAreaCount(filter);
        logger.info("Total city count is ::::"+areaDTO);
        if(areaDTO==null){
            return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Integer>(areaDTO, HttpStatus.OK);
    }
    
 /*##########Retrieve City record based on the Passing Filter's parameters.
    			If Filter value is None it will return all the city record#############*/
    
    @PostMapping(path = "/cityfilter")
	public List<CityDTO> getCityDetailsByFilter(@RequestBody CityFilter filter) {
    	System.out.println("Coming filter value "+filter);
    	List<CityDTO> cityList = locationService.findCityDetailsByFilter(filter);
    	logger.info(" City List details returning to APIResponse For UI :::: " + cityList);
		return cityList;
	}

    
    
      
    
    /*##########Retrieve Branch record based on the Passing Filter's parameters.
	If Filter value is None it will return all the Branch record #############*/

		@PostMapping(path = "/branchfilter")
		public List<BranchDTO> getBranchDetailsByFilter(@RequestBody BranchFilter filter) {
		System.out.println("Coming filter value "+filter);
		List<BranchDTO> branchList = locationService.findBranchDetailsByFilter(filter);
		logger.info(" Branch List details returning to APIResponse For UI :::: " + branchList);
		return branchList;
}
		
		
		 /*##########Retrieve Area record based on the Passing Filter's parameters.
		If Filter value is None it will return all the Area record #############*/

			@PostMapping(path = "/areafilter")
			public List<AreaDTO> getAreaDetailsByFilter(@RequestBody AreaFilter filter) {
			System.out.println("Coming filter value "+filter);
			List<AreaDTO> areaList = locationService.findAreaDetailsByFilter(filter);
			logger.info(" Area List details returning to APIResponse For UI :::: " + areaList);
			return areaList;
	}

    
}
