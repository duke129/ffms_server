/**
 * 
 */
package com.hm.dao.mysql.asset;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hm.util.entity.Asset;
import com.hm.util.model.AssetVo;

@Repository
public class AssetDaoImpl implements AssetDao {

	@Autowired
	AssetRepository assetRepository;

	@Override
	public List<AssetVo> findAllAsset() {

		List<Asset> listOfAsset = assetRepository.findAll();
		List<AssetVo> listOfVo = new ArrayList<AssetVo>();

		for (Asset asset : listOfAsset) {
			AssetVo vo = new AssetVo();
			vo.setAssetDescription(asset.getAssetDescription());
			vo.setAssetTypeDes(asset.getAssetType().getAssetTypeDescription());
			vo.setIdAsset(Long.valueOf(asset.getIdAsset().toString()));
			vo.setInstallationLat(asset.getInstallationLat());
			vo.setInstallationLong(asset.getInstallationLong());
			vo.setStatus(asset.getStatusBean().getIdStatus());
			vo.setIdAssetType(asset.getAssetType().getIdAssetType());
			vo.setAssetName("Refrigrator");
			listOfVo.add(vo);
		}
		return listOfVo;
	}
}
