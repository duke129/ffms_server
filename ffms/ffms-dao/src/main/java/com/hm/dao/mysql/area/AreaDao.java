/**
 * 
 */
package com.hm.dao.mysql.area;

import java.util.List;

import com.hm.util.entity.Area;
import com.hm.util.model.AreaDTO;

/**
 * @author pawan
 *
 */
public interface AreaDao {
	
	void saveArea(AreaDTO areaDTO);
	
	List<AreaDTO> findAllArea();
	
	List<AreaDTO> getAreaByBranchId(Long id);

}
