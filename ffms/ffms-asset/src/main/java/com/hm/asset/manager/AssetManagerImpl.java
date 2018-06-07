/**
 * 
 */
package com.hm.asset.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.mysql.asset.AssetRepository;
import com.hm.util.entity.Asset;
import com.hm.util.model.AssetVo;

/**
 * @author kiran
 *
 */
@Service
public class AssetManagerImpl implements AssetManager {

	@Autowired
	AssetRepository assetRepository;

	@Override
	public List<AssetVo> findAll() {
		List<Asset> listOfAsset = assetRepository.findAll();
		List<AssetVo> listOfVo = new ArrayList<AssetVo>();
		for (Asset asset : listOfAsset) {
			AssetVo vo = new AssetVo();
			vo.setAssetDescription(asset.getAssetDescription());
			vo.setAssetTypeDes(asset.getAssetType().getAssetTypeDescription());
			vo.setIdAsset(Long.valueOf(asset.getIdAsset().toString()));
			vo.setAssetName("Refrigrator");
			listOfVo.add(vo);
		}
		return listOfVo;
	}

}
