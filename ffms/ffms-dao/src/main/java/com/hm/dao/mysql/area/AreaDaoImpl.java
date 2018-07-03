/**
 * 
 */
package com.hm.dao.mysql.area;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hm.dao.mysql.branch.BranchDaoImpl;
import com.hm.util.entity.Area;
import com.hm.util.entity.Branch;
import com.hm.util.entity.City;
import com.hm.util.entity.Status;
import com.hm.util.entity.User;
import com.hm.util.model.AreaDTO;
import com.jayway.jsonpath.ParseContext;

/**
 * @author kiran
 *
 */

@Repository
public class AreaDaoImpl implements AreaDao{

	private static final Logger logger = LoggerFactory.getLogger(BranchDaoImpl.class);
	
	@Autowired
	AreaRepository areaRepository;
	@Override
	public void saveArea(AreaDTO areaDTO) {
		try {

			Area area = new Area();
			BeanUtils.copyProperties(areaDTO, area);
			User user = new User();
			Status status = new Status();
			Branch branch=new Branch();
			
			Optional<Area> optionalArea=areaRepository.findById(areaDTO.getAreaId());
			System.out.println("area findbyId is ::"+optionalArea);
			if(areaDTO.getAreaId()!=null) {
				area.setIdArea(areaDTO.getAreaId());
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

}
