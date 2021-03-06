/**
 * 
 */
package com.hm.location.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.hm.dao.mysql.area.AreaDao;
import com.hm.dao.mysql.branch.BranchDao;
import com.hm.dao.mysql.city.CityDao;
import com.hm.util.entity.Area;
import com.hm.util.entity.Branch;
import com.hm.util.entity.City;
import com.hm.util.model.AreaDTO;
import com.hm.util.model.BranchDTO;
import com.hm.util.model.CityDTO;
import com.hm.util.model.TypeHeadVo;
import com.hm.util.model.filter.AreaFilter;
import com.hm.util.model.filter.BranchFilter;
import com.hm.util.model.filter.CityFilter;

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
			cityDTO.setStatusId(Integer.toString(temp.getStatusBean().getIdStatus()));
			cityDTO.setCityId(Long.toString(temp.getIdCity()));
			cityDTO.setCityName(temp.getCityName());
			cityDTO.setCode(temp.getCode());
			cityDTO.setState(temp.getState());
			return cityDTO;
		}).collect(Collectors.toList());
		logger.info("In LocationServiceImpl city value converted into CityDTO details is :: " + listofcityresult);
		return listofcityresult;
	}

	@Override
	public List<BranchDTO> findAllBranch() {
		List<BranchDTO> listbranchdetails=branchDao.findAllBranch();
		logger.info("In location service Impl Branch details is :: "+listbranchdetails);
       return listbranchdetails;
	}

	public BranchDTO convertIntoBranchDTO(Branch temp) {

		BranchDTO branchDTO = new BranchDTO();
		branchDTO.setBranchId(Long.toString(temp.getIdBranch()));
		branchDTO.setBranchName(temp.getBranchName());
		branchDTO.setCode(temp.getCode());
		branchDTO.setCityId((Long.toString(temp.getCity().getIdCity())));
		branchDTO.setStatusId(Integer.toString(temp.getStatusBean().getIdStatus()));
		logger.info("branch details populate for UI is ::"+branchDTO);
		return branchDTO;
	}

	@Override
	public List<AreaDTO> findAllArea() {
		List<AreaDTO> listbranchdetails=areaDao.findAllArea();
		logger.info("In location service Impl city details is :: "+listbranchdetails);
		return listbranchdetails;
	}
	
	public AreaDTO convertIntoAreaDTO(Area temp) {

		AreaDTO areaDTO = new AreaDTO();
		areaDTO.setAreaName(temp.getAreaName());
		areaDTO.setAreaId((Long.toString(temp.getIdArea())));
		areaDTO.setStatusId(Integer.toString(temp.getStatus().getIdStatus()));
		
		areaDTO.setBranchName(temp.getBranch().getBranchName());
		return areaDTO;
	}

	@Override
	public BranchDTO findBranchById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TypeHeadVo> getBranchDetailsByCityId(String cityId) {
		List<BranchDTO>branchDtoList=branchDao.getBranchByCityId(Long.valueOf(cityId));
		List<TypeHeadVo> branchTypeHeadVoList=new ArrayList<TypeHeadVo>();
		for (BranchDTO branchDTO : branchDtoList) {
			TypeHeadVo typehead=new TypeHeadVo();
			typehead.setId(Long.valueOf(branchDTO.getBranchId()));
			typehead.setName(branchDTO.getBranchName());
			branchTypeHeadVoList.add(typehead);
		}
		
		return branchTypeHeadVoList;
	}

	@Override
	public List<TypeHeadVo> getAreaDetailsByBranchId(String branchId) {
		List<AreaDTO> areaDtoList=areaDao.getAreaByBranchId(Long.valueOf(branchId));
		List<TypeHeadVo> areaTypeHeadVoList=new ArrayList<TypeHeadVo>();
		
		for (AreaDTO areaDTO : areaDtoList) {
			TypeHeadVo typehead=new TypeHeadVo();
			typehead.setId(Long.valueOf(areaDTO.getAreaId()));
			typehead.setName(areaDTO.getAreaName());
			areaTypeHeadVoList.add(typehead);
		}
		
		return areaTypeHeadVoList;
	}

	@Override
	public Integer getTotalCityCount(CityFilter filter) {
		Integer citycount=cityDao.getTotalCityCount(filter);
		return citycount;
	}

	@Override
	public Integer getTotalBranchCount(BranchFilter filter) {
		Integer citycount=branchDao.getTotalBranchCount(filter);
		return citycount;
		
		
	}

	@Override
	public Integer getTotalAreaCount(AreaFilter filter) {
		Integer citycount=areaDao.getTotalAreaCount(filter);
		return citycount;
	}

	@Override
	public List<CityDTO> findCityDetailsByFilter(CityFilter filter) {
		List<CityDTO> citylist=cityDao.findCityDetailsByFilter(filter);
		logger.info("city list is :::"+citylist);
		return citylist;
	}

	@Override
	public List<BranchDTO> findBranchDetailsByFilter(BranchFilter filter) {
		List<BranchDTO> branchList=branchDao.findBranchDetailsByFilter(filter);
		return branchList;
	}

	@Override
	public List<AreaDTO> findAreaDetailsByFilter(AreaFilter filter) {
		List<AreaDTO> areaDtoList=areaDao.findAreaDetailsByFilter(filter);
		return areaDtoList;
	}

}
