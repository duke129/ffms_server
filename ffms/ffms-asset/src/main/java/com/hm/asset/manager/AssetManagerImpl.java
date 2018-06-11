/**
 * 
 */
package com.hm.asset.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.mysql.asset.AssetDaoImpl;
import com.hm.util.model.AssetVo;

@Service
public class AssetManagerImpl implements AssetManager {

	@Autowired
	AssetDaoImpl assetDao;

	@Override
	public List<AssetVo> findAllAsset() {

		return assetDao.findAllAsset();
	}
}
