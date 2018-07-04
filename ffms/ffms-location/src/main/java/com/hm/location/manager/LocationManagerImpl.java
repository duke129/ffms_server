/**
 * 
 */
package com.hm.location.manager;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.mysql.area.AreaDao;
import com.hm.dao.mysql.branch.BranchDao;
import com.hm.dao.mysql.city.CityDao;
import com.hm.location.controller.LocationController;
import com.hm.util.entity.Area;
import com.hm.util.entity.Branch;
import com.hm.util.entity.City;
import com.hm.util.model.AreaDTO;
import com.hm.util.model.BranchDTO;
import com.hm.util.model.CityDTO;

/**
 * @author pawan
 *
 */
@Service
public class LocationManagerImpl implements LocationManager {

	private static final Logger logger = LoggerFactory.getLogger(LocationManagerImpl.class);
	@Autowired
	CityDao cityDao;
	@Autowired
	BranchDao branchDao;

	@Autowired
	AreaDao areaDao;

	@Override
	public void saveCity(CityDTO cityDto) {

		cityDao.saveCity(cityDto);

	}

	@Override
	public boolean isCityExist(Long id) {

		return true;

	}

	public void saveBranch(BranchDTO branchDto) {
		branchDao.saveBranch(branchDto);
	}

	@Override
	public void saveArea(AreaDTO areaDTO) {
		areaDao.saveArea(areaDTO);

	}

	@Override
	public List<CityDTO> findAllCity() {
		List<City> listcitydetails = cityDao.findAllCity();
		logger.info("In location service Impl city details is :: " + listcitydetails);
		// convert inside the map() method directly.
		List<CityDTO> listofcityresult = listcitydetails.stream().map(temp -> {
			CityDTO cityDTO = new CityDTO();
			cityDTO.setStatusBean(Integer.toString(temp.getStatusBean().getIdStatus()));
			cityDTO.setIdCity(temp.getIdCity());
			cityDTO.setCityName(temp.getCityName());
			//cityDTO.setCreatedOn(temp.getCreatedOn());
			//cityDTO.setModifiedOn(temp.getModifiedOn());
			return cityDTO;
		}).collect(Collectors.toList());
		logger.info("In LocationServiceImpl city value converted into CityDTO details is :: " + listofcityresult);
		return listofcityresult;
	}

	@Override
	public List<BranchDTO> findAllBranch() {
		List<Branch> listbranchdetails=branchDao.findAllBranch();
		logger.info("In location service Impl city details is :: "+listbranchdetails);
		List<BranchDTO> listofbranchdtoresult = listbranchdetails.stream().map(temp -> {
    	return convertIntoBranchDTO(temp);
        }).collect(Collectors.toList());
		logger.info("In location service Impl city value converted into CityDTO  details is :: "+listofbranchdtoresult);
       return listofbranchdtoresult;
	}

	public BranchDTO convertIntoBranchDTO(Branch temp) {

		BranchDTO branchDTO = new BranchDTO();
		branchDTO.setStatusBean(temp.getStatusBean());
		branchDTO.setIdBranch(Long.toString(temp.getIdBranch()));
		branchDTO.setBranchName(temp.getBranchName());
		branchDTO.setCity(temp.getCity());
//		branchDTO.setCreatedOn(temp.getCreatedOn());
//		branchDTO.setModifiedOn(temp.getModifiedOn());
		return branchDTO;
	}

	@Override
	public List<AreaDTO> findAllArea() {
		List<Area> listbranchdetails=areaDao.findAllArea();
		logger.info("In location service Impl city details is :: "+listbranchdetails);
		List<AreaDTO> listOfAreaDtoResult = listbranchdetails.stream().map(temp -> {
    	return convertIntoAreaDTO(temp);
        }).collect(Collectors.toList());
		logger.info("In location service Impl city value converted into CityDTO  details is :: "+listOfAreaDtoResult);
       return listOfAreaDtoResult;
	}
	
	public AreaDTO convertIntoAreaDTO(Area temp) {

		AreaDTO areaDTO = new AreaDTO();
		areaDTO.setAreaName(temp.getAreaName());
		areaDTO.setAreaId((long)temp.getIdArea());
		areaDTO.setStatus(temp.getStatus().getStatusDescription());
		areaDTO.setBranchName(temp.getBranch().getBranchName());
		return areaDTO;
	}

}
