/**
 * 
 */
package com.hm.location.manager;

import java.util.List;

import com.hm.util.model.AreaDTO;
import com.hm.util.model.BranchDTO;
import com.hm.util.model.CityDTO;

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

}
