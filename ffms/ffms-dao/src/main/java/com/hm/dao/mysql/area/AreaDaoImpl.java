/**
 * 
 */
package com.hm.dao.mysql.area;

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
import com.hm.util.entity.Area;
import com.hm.util.entity.Branch;
import com.hm.util.entity.Status;
import com.hm.util.entity.User;
import com.hm.util.model.AreaDTO;

/**
 * @author kiran
 *
 */

@Repository
public class AreaDaoImpl implements AreaDao{

	private static final Logger logger = LoggerFactory.getLogger(AreaDaoImpl.class);
	
	private static final String AREA_BY_BRANCHID="select a.idArea,a.areaName,a.branchId,b.branchName,b.cityId,c.cityName,a.status from Area a inner join Branch b on a.branchId=b.idBranch inner join City c on c.idCity=b.cityId where a.branchId=?";
	
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
			
			Optional<Area> optionalArea=areaRepository.findById(Long.valueOf(areaDTO.getAreaId()));
			logger.info("area findbyId is ::"+optionalArea);
			if(areaDTO.getAreaId()!=null) {
				area.setIdArea(Long.valueOf(areaDTO.getAreaId()));
				area.setModifiedOn(new Date());
				area.setCreatedOn(optionalArea.get().getCreatedOn());
				user.setIdUser(1L);
				area.setUser2(user);
				branch.setIdBranch(1L);
				area.setBranch(branch);
				status.setIdStatus(1);
				area.setStatus(status);
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
				logger.info("Area value that going to be save into database::" + area);
				Area areaResult=areaRepository.save(area);
				logger.info("After saving into database"+areaResult);
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
	}
	@Override
	public List<Area> findAllArea() {
		List<Area> listArea=areaRepository.findAll();
		logger.info("city list view is :::"+listArea);
		return listArea;
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

}
