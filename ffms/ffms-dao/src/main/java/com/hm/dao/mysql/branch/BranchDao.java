/**
 * 
 */
package com.hm.dao.mysql.branch;

import java.util.List;

import com.hm.util.entity.Branch;
import com.hm.util.model.AreaDTO;
import com.hm.util.model.BranchDTO;
import com.hm.util.model.filter.BranchFilter;

/**
 * @author kiran
 *
 */
public interface BranchDao {
	
	void saveBranch(BranchDTO branchDto);
	
	List<BranchDTO> findAllBranch();
	List<BranchDTO> getBranchByCityId(Long id);
	
	List<BranchDTO> findBranchDetailsByFilter(BranchFilter filter);
	
	Integer getTotalBranchCount(BranchFilter filter);
	
	
}
