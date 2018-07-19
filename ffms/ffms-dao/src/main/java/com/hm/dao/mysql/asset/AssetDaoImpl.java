/**
 * 
 */
package com.hm.dao.mysql.asset;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hm.dao.mysql.customer.CustomerDao;
import com.hm.util.entity.Asset;
import com.hm.util.entity.AssetType;
import com.hm.util.entity.Customer;
import com.hm.util.entity.Status;
import com.hm.util.model.AssetVo;
import com.hm.util.model.TypeHeadVo;
import com.hm.util.model.filter.AssetFilter;

@Repository
public class AssetDaoImpl implements AssetDao {

	@Autowired
	AssetRepository assetRepository;

	@Autowired
	AssetTypeRepository assetTypeRepository;

	@Autowired
	CustomerDao customerDao;

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<AssetVo> findAllAsset() {

		List<Asset> listOfAsset = assetRepository.findAll();
		List<AssetVo> listOfVo = new ArrayList<AssetVo>();

		for (Asset asset : listOfAsset) {
			AssetVo vo = new AssetVo();
			vo.setIdAsset(Long.valueOf(asset.getIdAsset().toString()));
			vo.setAssetName(asset.getName());
			vo.setAssetDescription(asset.getAssetDescription());
			vo.setIdAssetType(asset.getAssetType().getIdAssetType());
			vo.setStatus(asset.getStatusBean().getIdStatus());
			/*vo.setAssetTypeDes(asset.getAssetType().getAssetTypeDescription());
			vo.setInstallationLat(asset.getInstallationLat());
		    vo.setInstallationLong(asset.getInstallationLong());*/
			
		    listOfVo.add(vo);
		}
		return listOfVo;
	}

	@Override
	@Transactional
	public void addAsset(Asset asset) {
		entityManager.persist(asset);
	}

	@Override
	public List<AssetVo> findAssetByFilter(AssetFilter filter) {

		System.out.println("*************" + filter);
		StringBuilder whereCluse = new StringBuilder("");

		List<AssetVo> list = new ArrayList<AssetVo>();
		whereCluse.append(" where a.status=:statusId");

		System.out.println("where:::::::" + whereCluse);
		try {
			if (filter.getStartDate() != null && filter.getEndDate() != null)
				whereCluse.append(" and a.addedOn >=:startDate").append(" and a.addedOn <=:endDate ");

			if (filter.getName() != null && !filter.getName().isEmpty())
				whereCluse.append(" and CONCAT(c.firstName, ' ', c.lastName) LIKE :fullName");
			if (filter.getSerialNo() != null && !filter.getSerialNo().isEmpty())
				whereCluse.append(" and serialNo LIKE :serialNo");

			if (filter.getPageSize() > 0 && filter.getOffset() >= 0) {
				whereCluse.append(" limit " + filter.getOffset() + " , " + filter.getPageSize());
			}

			String completeQuery = GET_ASSET_BY_FILTER.toString().concat(whereCluse.toString());
			String countQuery = GET_ASSET_BY_COUNT.toString().concat(whereCluse.toString()).split("limit")[0];

			System.out.println("*******************************count" + countQuery);
			Query query = entityManager.createNativeQuery(completeQuery);
			Query queryCount = entityManager.createNativeQuery(countQuery);
			System.out.println("*******************************" + completeQuery);
			if (filter.getName() != null && !filter.getName().isEmpty()) {
				query.setParameter("fullName", "%" + filter.getName() + "%");
				queryCount.setParameter("fullName", "%" + filter.getName() + "%");
			}
			if (filter.getStartDate() != null && filter.getEndDate() != null) {
				query.setParameter("startDate", filter.getStartDate()).setParameter("endDate", filter.getEndDate());
				queryCount.setParameter("startDate", filter.getStartDate()).setParameter("endDate",
						filter.getEndDate());
			}
			if (filter.getSerialNo() != null && !filter.getSerialNo().isEmpty()) {
				query.setParameter("serialNo", "%" + filter.getSerialNo() + "%");
				queryCount.setParameter("serialNo", "%" + filter.getSerialNo() + "%");
			}

			query.setParameter("statusId", 1);
			queryCount.setParameter("statusId", 1);

			List<Object[]> assetList = query.getResultList();

			System.out.println("*************" + queryCount.getResultList());

			BigInteger count = (BigInteger) queryCount.getResultList().stream().findFirst().orElse(0);
			System.out.println("count********" + count);

			System.out.println("************" + assetList);
			for (Object obj[] : assetList) {
				AssetVo vo = new AssetVo();
				vo.setIdAsset(Long.valueOf(obj[0].toString()));
				vo.setAssetDescription(obj[1]);
				vo.setStatus(obj[2] != null ? Integer.valueOf(obj[2].toString()) : null);
				vo.setInstallationLat(obj[3] != null ? obj[3].toString() : null);
				vo.setInstallationLong(obj[4] != null ? obj[4].toString() : null);
				vo.setAssetTypeDes(obj[5] != null ? obj[5].toString() : null);
				vo.setCustomerName(obj[6] != null ? obj[6].toString() : null);
				vo.setSerialNo(obj[7] != null ? obj[7].toString() : null);
				vo.setAssetName(vo.getAssetDescription() != null ? vo.getAssetDescription().toString() : null);
				vo.setCustomerId(obj[8] != null ? obj[8].toString() : null);
				vo.setCount(count);
				list.add(vo);

			}

			System.out.println("******************" + assetList);

		} catch (Exception e) {
			System.out.println("Exception:::::::::" + e);
		}
		return list;
	}

	@Override
	public List<TypeHeadVo> findAllAssetType() {
		List<AssetType> listOfassetType = assetTypeRepository.findAll();

		List<TypeHeadVo> listOfNode = listOfassetType.stream()
				.map(asset -> 
					new TypeHeadVo(Long.valueOf(asset.getIdAssetType()), asset.getAssetTypeDescription())).sorted()
				.collect(Collectors.toList());

		return listOfNode;

	}

	@Override
	@Transactional
	public void updateAsset(AssetVo assetVo) {
		Optional<Asset> asset = assetRepository.findById(assetVo.getIdAsset());
		if (!asset.isPresent())
			return;
		Asset updateAsset = asset.get();

		if (assetVo.getAssetDescription() != null)
			updateAsset.setAssetDescription((String) assetVo.getAssetDescription());
		if (assetVo.getInstallationLat() != null)
			updateAsset.setInstallationLat(assetVo.getInstallationLat());
		if (assetVo.getInstallationLat() != null)
			updateAsset.setInstallationLong((assetVo.getInstallationLong()));
		if (assetVo.getStatus() != null) {
			Status status = new Status();
			status.setIdStatus(assetVo.getStatus());
			updateAsset.setStatusBean(status);
		}
		updateAsset.setModifiedDate(new Date());
		assetRepository.save(updateAsset);
		if (assetVo.getCustomerId() != null) {
			Customer cus = new Customer();
			cus.setId(Long.valueOf(assetVo.getCustomerId()));
			cus.setFirstName(assetVo.getCustomerName());
			customerDao.modifyCustomerDetails(cus);
		}
	}

	
	
	
	
}
