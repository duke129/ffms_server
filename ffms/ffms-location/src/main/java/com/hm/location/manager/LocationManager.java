/**
 * 
 */
package com.hm.location.manager;

import java.util.List;

import com.hm.util.model.AreaDTO;
import com.hm.util.model.BranchDTO;
import com.hm.util.model.CityDTO;
import com.hm.util.model.TypeHeadVo;

/**
 * @author kiran
 *
 */
public interface LocationManager {
	
	void saveCity(CityDTO city);
	
	boolean isCityExist(Long id);
	
	void saveBranch(BranchDTO branchDto);
	
	void saveArea(AreaDTO areaDTO);
	
	 List<CityDTO> findAllCity();
	 
	 List<BranchDTO>  findAllBranch();
	 
	 List<AreaDTO> findAllArea();
	 
	 BranchDTO findBranchById(String id);
	 
	 List<TypeHeadVo> getBranchDetailsByCityId(String cityId);
	 
	 List<TypeHeadVo> getAreaDetailsByBranchId(String branchId);
	 
	 Integer getTotalCityCount();
	 
	 Integer getTotalBranchCount();
	 
	 Integer getTotalAreaCount();
	 

}
