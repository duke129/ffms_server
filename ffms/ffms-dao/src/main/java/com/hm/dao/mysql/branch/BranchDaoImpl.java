/**
 * 
 */
package com.hm.dao.mysql.branch;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hm.util.entity.Branch;
import com.hm.util.entity.City;
import com.hm.util.entity.Status;
import com.hm.util.entity.User;
import com.hm.util.model.BranchDTO;

/**
 * @author kiran
 *
 */
@Repository
public class BranchDaoImpl implements BranchDao {

	private static final Logger logger = LoggerFactory.getLogger(BranchDaoImpl.class);
	@Autowired
	BranchRepository branchRepository;
	
	private static final String Update_Branch = "update Branch set branchName=? where idBranch=?";

	@Override
	public void saveBranch(BranchDTO branchDto) {

		try {

			Branch branch = new Branch();
			BeanUtils.copyProperties(branchDto, branch);
			
			Branch saveResult=null;
			String branchId=branchDto.getIdBranch();
			Optional<Branch> updateBranch=branchRepository.findById(Long.valueOf(branchId));
			User user = new User();
			user.setIdUser(1L);
			Status status = new Status();
			City city = new City();
			if(branchId!=null) {
				branch.setIdBranch(Long.valueOf(branchId));
				branch.setCreatedOn(updateBranch.get().getCreatedOn());
				branch.setModifiedOn(new Date());
				branch.setUser2(user);
				branch.setBranchName(branchDto.getBranchName());
				status.setIdStatus(1);
				city.setIdCity(1L);
				branch.setCity(city);
				branch.setStatusBean(status);
				logger.info("when branch Id not null branch is ::"+branch);
				saveResult=branchRepository.save(branch);
			}else {
				branch.setCreatedOn(new Date());
				branch.setUser1(user);
				branch.setUser2(user);
				status.setIdStatus(1);
				city.setIdCity(1L);
				branch.setCity(city);
				branch.setStatusBean(status);
				System.out.println("************" + branch);
				saveResult=branchRepository.save(branch);
			}
			
			logger.info("After saving into database"+saveResult);
			

		} catch (Exception e) {
			logger.info(e.getMessage());
		}

	}

	@Override
	public List<Branch> findAllBranch() {
		List<Branch> listbranch=branchRepository.findAll();
		logger.info("city list view is :::"+listbranch);
			
		return listbranch;
	}

}
