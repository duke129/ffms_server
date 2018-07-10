/**
 * 
 */
package com.hm.dao.mysql.branch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hm.util.entity.Branch;
import com.hm.util.entity.City;
import com.hm.util.entity.Status;
import com.hm.util.entity.User;
import com.hm.util.model.AreaDTO;
import com.hm.util.model.BranchDTO;
import com.hm.util.model.ProductDTO;

/**
 * @author kiran
 *
 */
@Repository
public class BranchDaoImpl implements BranchDao {

	private static final Logger logger = LoggerFactory.getLogger(BranchDaoImpl.class);
	@Autowired
	BranchRepository branchRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	private static final String UPDATE_BRANCH = "update Branch set branchName=? where idBranch=?";
	
	private static final String BRANCH_BY_CITYID = "select b.idBranch,b.branchName,b.cityId,c.cityName,b.status from Branch b inner join City c on b.cityId=c.idCity where b.cityId=?";


	
	@Override
	public void saveBranch(BranchDTO branchDto) {

		try {

			Branch branch = new Branch();
			BeanUtils.copyProperties(branchDto, branch);
			Branch saveResult=null;
			String branchId=branchDto.getBranchId();
			User user = new User();
			user.setIdUser(1L);
			Status status = new Status();
			City city = new City();
			if(branchId!=null) {
				Optional<Branch> updateBranch=branchRepository.findById(Long.valueOf(branchId));
				branch.setIdBranch(Long.valueOf(branchId));
				branch.setCreatedOn(updateBranch.get().getCreatedOn());
				branch.setModifiedOn(new Date());
				branch.setUser2(user);
				branch.setBranchName(branchDto.getBranchName());
				branch.setCode(branchDto.getCode());
				status.setIdStatus(1);
				city.setIdCity(1L);
				branch.setCity(city);
				branch.setStatusBean(status);
				logger.info("when branch Id not null branch is ::"+branch);
				saveResult=branchRepository.save(branch);
			}else {
				branch.setCreatedOn(new Date());
				branch.setModifiedOn(new Date());
				branch.setUser1(user);
				branch.setUser2(user);
				status.setIdStatus(1);
				city.setIdCity(1L);
				branch.setCity(city);
				branch.setCode(branchDto.getCode());
				branch.setStatusBean(status);
				System.out.println(" branch details that going to be save into databases::************" + branch);
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

	@Override
	public List<BranchDTO> getBranchByCityId(Long id) {
		
		List<BranchDTO> branchDTOList = new ArrayList<BranchDTO>();
		List<Object[]> branch = entityManager.createNativeQuery(BRANCH_BY_CITYID).setParameter(1,id).getResultList();
	
		for (Object[] objects : branch) {
			BranchDTO branchDTO=new BranchDTO();
			branchDTO.setBranchId(String.valueOf(objects[0]));
			branchDTO.setBranchName(String.valueOf(objects[1]));
			branchDTO.setCityId(String.valueOf(objects[2]));
			branchDTO.setCityName(String.valueOf(objects[3]));
			branchDTO.setStatusId(String.valueOf(objects[4]));
			
			branchDTOList.add(branchDTO);
			
		}
		
		return branchDTOList;
	}



}
