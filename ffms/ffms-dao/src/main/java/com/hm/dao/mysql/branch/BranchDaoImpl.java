/**
 * 
 */
package com.hm.dao.mysql.branch;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
import com.hm.util.model.CityDTO;
import com.hm.util.model.ProductDTO;
import com.hm.util.model.filter.BranchFilter;

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

	private static final String BRANCH_DETAILS="select b.idBranch,b.branchName,b.code,b.cityId,c.cityName,c.state,b.status from Branch b inner join City c on b.cityId=c.idCity";

	private static final String GET_Branch_COUNT_BASEDON_FILTER="select count(*)from Branch b inner join City c on b.cityId=c.idCity";

	
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
	public List<BranchDTO> findAllBranch() {
		
		List<BranchDTO> branchDTOList = new ArrayList<BranchDTO>();
		List<Object[]> branch = entityManager.createNativeQuery(BRANCH_DETAILS).getResultList();
	
		for (Object[] objects : branch) {
			BranchDTO branchDTO=new BranchDTO();
			branchDTO.setBranchId(String.valueOf(objects[0]));
			branchDTO.setBranchName(String.valueOf(objects[1]));
			branchDTO.setCode(String.valueOf(objects[2]));
			branchDTO.setCityId(String.valueOf(objects[3]));
			branchDTO.setCityName(String.valueOf(objects[4]));
			branchDTO.setState(String.valueOf(objects[5]));
			branchDTO.setStatusId(String.valueOf(objects[6]));
			branchDTOList.add(branchDTO);
			
		}
		
		return branchDTOList;
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

	@Override
	public List<BranchDTO> findBranchDetailsByFilter(BranchFilter filter) {
		StringBuilder whereCluse = new StringBuilder("");
		List<BranchDTO> list = new ArrayList<BranchDTO>();
		try {

			if (filter.getBranchName() != null && !filter.getBranchName().isEmpty())
				whereCluse.append(" and b.branchName LIKE :branchName ");

			/*
			 * if (filter.getName() != null && filter.getCityCode() != null) {
			 * whereCluse.append(" and "); }
			 */
			if (filter.getCode() != null && !filter.getCode().isEmpty())
				whereCluse.append(" and b.code LIKE :code");

			if (filter.getPageSize() > 0 && filter.getOffset() >= 0) {
				whereCluse.append(" limit " + filter.getOffset() + " , " + filter.getPageSize());
			}

			String completeQuery = BRANCH_DETAILS.toString().concat(whereCluse.toString()).replaceFirst("and",
					"where");
			
			System.out.println("**********************"+completeQuery);
			Query query = entityManager.createNativeQuery(completeQuery);
			if (filter.getBranchName() != null && !filter.getBranchName().isEmpty()) {
				query.setParameter("branchName","%"+ filter.getBranchName()+"%");
			}

			if (filter.getCode() != null && !filter.getCode().isEmpty()) {
				query.setParameter("code", "%"+filter.getCode()+"%");
			}

			List<Object[]> branchList = query.getResultList();
			System.out.println("************" + branchList);
			for (Object obj[] : branchList) {
				BranchDTO branchDto = new BranchDTO();
				branchDto.setBranchId(String.valueOf(obj[0]));
				branchDto.setBranchName(String.valueOf(obj[1]));
				branchDto.setCode(String.valueOf(obj[2]));
				branchDto.setCityId(String.valueOf(obj[3]));
				branchDto.setCityName(String.valueOf(obj[4]));
				branchDto.setState(String.valueOf(obj[5]));
				branchDto.setStatusId(String.valueOf(obj[6]));
				list.add(branchDto);

			}

			System.out.println("City details value after setting is :::::::" + branchList);

		} catch (Exception e) {
			System.out.println("Exception:::::::::" + e);
		}
		return list;
	}

	@Override
	public Integer getTotalBranchCount(BranchFilter filter) {
		StringBuilder whereCluse = new StringBuilder("");
		Integer count = 0;
		try {
			if (filter.getBranchName() != null && !filter.getBranchName().isEmpty())
				whereCluse.append(" and b.branchName LIKE :branchName ");

			if (filter.getCode() != null && !filter.getCode().isEmpty())
				whereCluse.append(" and  b.code LIKE :code");
			
			String countQuery = GET_Branch_COUNT_BASEDON_FILTER.toString().concat(whereCluse.toString()).replaceFirst("and", "where").split("limit")[0];
			System.out.println("Branch count query is:::::" + countQuery);
			Query queryCount = entityManager.createNativeQuery(countQuery);

			if (filter.getBranchName() != null && !filter.getBranchName().isEmpty()) {
				queryCount.setParameter("branchName","%" +filter.getBranchName() +"%");
			}

			if (filter.getCode() != null && !filter.getCode().isEmpty()) {
				queryCount.setParameter("code", "%"+filter.getCode()+"%");
			}
			BigInteger totalCityCount = (BigInteger) queryCount.getResultList().stream().findFirst().orElse(0);
			count = totalCityCount.intValue();
			System.out.println("Total No of branch in the database is ::" + count);
		} catch (Exception e) {
		}
		return count;
	}



}
