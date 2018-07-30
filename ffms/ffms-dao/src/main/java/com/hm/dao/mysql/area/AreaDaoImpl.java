/**
 * 
 */
package com.hm.dao.mysql.area;

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
import com.hm.util.entity.Area;
import com.hm.util.entity.Branch;
import com.hm.util.entity.Status;
import com.hm.util.entity.User;
import com.hm.util.model.AreaDTO;
import com.hm.util.model.BranchDTO;
import com.hm.util.model.filter.AreaFilter;

/**
 * @author kiran
 *
 */

@Repository
public class AreaDaoImpl implements AreaDao{

	private static final Logger logger = LoggerFactory.getLogger(AreaDaoImpl.class);
	
	private static final String AREA_BY_BRANCHID="select a.idArea,a.areaName,a.branchId,b.branchName,b.cityId,c.cityName,a.status from Area a inner join Branch b on a.branchId=b.idBranch inner join City c on c.idCity=b.cityId where a.branchId=?";
	
	private static final String AREA_Details="select a.idArea,a.areaName,a.code,a.branchId,b.branchName,b.cityId,c.cityName,a.status from Area a inner join Branch b on a.branchId=b.idBranch inner join City c on c.idCity=b.cityId";
	
	private static final String GET_Area_COUNT_BASEDON_FILTER="select count(*) from Area a inner join Branch b on a.branchId=b.idBranch inner join City c on c.idCity=b.cityId";
	@Autowired
	AreaRepository areaRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void saveArea(AreaDTO areaDTO) {
		try {

			Area area = new Area();
			BeanUtils.copyProperties(areaDTO, area);
			User user = new User();
			Status status = new Status();
			Branch branch=new Branch();
			if(areaDTO.getAreaId()!=null) {
				Optional<Area> optionalArea=areaRepository.findById(Long.valueOf(areaDTO.getAreaId()));
				logger.info("area findbyId is ::"+optionalArea);
				area.setIdArea(Long.valueOf(areaDTO.getAreaId()));
				area.setModifiedOn(new Date());
				area.setCreatedOn(optionalArea.get().getCreatedOn());
				user.setIdUser(1L);
				area.setUser2(user);
				branch.setIdBranch(1L);
				area.setBranch(branch);
				status.setIdStatus(1);
				area.setStatus(status);
				area.setCode(areaDTO.getCode());
				Area areaResult=areaRepository.save(area);
				logger.info("Area updation is going to be save into database::" + areaResult);
			}else {
				area.setCreatedOn(new Date());
				user.setIdUser(1L);
				area.setCreatedByUser(user);
				area.setUser2(user);
				status.setIdStatus(1);
				area.setStatus(status);
				branch.setIdBranch(1L);
				area.setBranch(branch);
				area.setCode(areaDTO.getCode());
				logger.info("Area value that going to be save into database::" + area);
				Area areaResult=areaRepository.save(area);
				logger.info("After saving into database"+areaResult);
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
	}
	@Override
	public List<AreaDTO> findAllArea() {
		List<AreaDTO> areaDTOList = new ArrayList<AreaDTO>();
		List<Object[]> area = entityManager.createNativeQuery(AREA_Details).getResultList();
	
		for (Object[] objects : area) {
			AreaDTO areaDTO=new AreaDTO();
			areaDTO.setAreaId(String.valueOf(objects[0]));
			areaDTO.setAreaName(String.valueOf(objects[1]));
			areaDTO.setCode(String.valueOf(objects[2]));
			areaDTO.setBranchId(String.valueOf(objects[3]));
			areaDTO.setBranchName(String.valueOf(objects[4]));
			areaDTO.setCityId(String.valueOf(objects[5]));
			areaDTO.setCityName(String.valueOf(objects[6]));
			areaDTO.setStatusId(String.valueOf(objects[7]));
			
			areaDTOList.add(areaDTO);
			
		}
		
		return areaDTOList;
	}
	
	
	@Override
	public List<AreaDTO> getAreaByBranchId(Long id) {
		List<AreaDTO> areaDTOList = new ArrayList<AreaDTO>();
		List<Object[]> area = entityManager.createNativeQuery(AREA_BY_BRANCHID).setParameter(1,id).getResultList();
	
		for (Object[] objects : area) {
			AreaDTO areaDTO=new AreaDTO();
			areaDTO.setAreaId(String.valueOf(objects[0]));
			areaDTO.setAreaName(String.valueOf(objects[1]));
			areaDTO.setBranchId(String.valueOf(objects[2]));
			areaDTO.setBranchName(String.valueOf(objects[3]));
			areaDTO.setCityId(String.valueOf(objects[4]));
			areaDTO.setCityName(String.valueOf(objects[5]));
			areaDTO.setStatusId(String.valueOf(objects[6]));
			
			areaDTOList.add(areaDTO);
			
		}
		
		return areaDTOList;
	}
	@Override
	public Integer getTotalAreaCount(AreaFilter filter) {
		StringBuilder whereCluse = new StringBuilder("");
		Integer count = 0;
		try {
			if (filter.getAreaName() != null && !filter.getAreaName().isEmpty())
				whereCluse.append(" and a.areaName LIKE :areaName ");

			if (filter.getCode() != null && !filter.getCode().isEmpty())
				whereCluse.append(" and a.code LIKE :code");
			
			String countQuery = GET_Area_COUNT_BASEDON_FILTER.toString().concat(whereCluse.toString()).replaceFirst("and", "where").split("limit")[0];
			System.out.println("Area count query is:::::" + countQuery);
			Query queryCount = entityManager.createNativeQuery(countQuery);

			if (filter.getAreaName() != null && !filter.getAreaName().isEmpty()) {
				queryCount.setParameter("areaName", "%"+filter.getAreaName()+"%");
			}

			if (filter.getCode() != null && !filter.getCode().isEmpty()) {
				queryCount.setParameter("code", "%"+filter.getCode()+"%");
			}
			BigInteger totalCityCount = (BigInteger) queryCount.getResultList().stream().findFirst().orElse(0);
			count = totalCityCount.intValue();
			System.out.println("Total No of Area in the database is ::" + count);
		} catch (Exception e) {
		}
		return count;
	}
	@Override
	public List<AreaDTO> findAreaDetailsByFilter(AreaFilter filter) {
		StringBuilder whereCluse = new StringBuilder("");
		List<AreaDTO> list = new ArrayList<AreaDTO>();
		try {

			if (filter.getAreaName() != null && !filter.getAreaName().isEmpty())
				whereCluse.append(" and a.areaName LIKE:areaName ");
			
			if (filter.getCode() != null && !filter.getCode().isEmpty())
				whereCluse.append(" and a.code LIKE:code");

			if (filter.getPageSize() > 0 && filter.getOffset() >= 0) {
				whereCluse.append(" limit " + filter.getOffset() + " , " + filter.getPageSize());
			}

			String completeQuery = AREA_Details.toString().concat(whereCluse.toString()).replaceFirst("and",
					"where");
			
			System.out.println("Area complete query is :::"+completeQuery);
			Query query = entityManager.createNativeQuery(completeQuery);
			if (filter.getAreaName() != null && !filter.getAreaName().isEmpty()) {
				query.setParameter("areaName", "%"+filter.getAreaName()+"%");
			}

			if (filter.getCode() != null && !filter.getCode().isEmpty()) {
				query.setParameter("code", "%"+filter.getCode()+"%");
			}

			List<Object[]> areaList = query.getResultList();
			System.out.println("************" + areaList);
			for (Object obj[] : areaList) {
				AreaDTO areaDto = new AreaDTO();
				areaDto.setAreaId(String.valueOf(obj[0]));
				areaDto.setAreaName(String.valueOf(obj[1]));
				areaDto.setCode(String.valueOf(obj[2]));
				areaDto.setBranchId(String.valueOf(obj[3]));
				areaDto.setBranchName(String.valueOf(obj[4]));
				areaDto.setCityId(String.valueOf(obj[5]));
				areaDto.setCityName(String.valueOf(obj[6]));
				areaDto.setStatusId(String.valueOf(obj[7]));
				list.add(areaDto);

			}

			System.out.println("Area details value after setting is :::::::" + areaList);

		} catch (Exception e) {
			System.out.println("Exception:::::::::" + e);
		}
		return list;
	}

}
