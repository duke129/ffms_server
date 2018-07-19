/**
 * 
 */
package com.hm.asset.manager;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hm.dao.mysql.asset.AssetDaoImpl;
import com.hm.dao.mysql.product.ProductDao;
import com.hm.util.entity.Asset;
import com.hm.util.entity.AssetType;
import com.hm.util.entity.Status;
import com.hm.util.entity.User;
import com.hm.util.model.AssetVo;
import com.hm.util.model.ProductDTO;
import com.hm.util.model.TypeHeadVo;
import com.hm.util.model.filter.AssetFilter;

@Service
public class AssetManagerImpl implements AssetManager {

	@Autowired
	AssetDaoImpl assetDao;
	
	@Autowired
	ProductDao productDao;

	@Override
	public List<AssetVo> findAllAsset() {

		return assetDao.findAllAsset();
	}

	@Override
	public List<TypeHeadVo> getAssetForSelection() {
		List<AssetVo> assetlist=assetDao.findAllAsset();
		List<TypeHeadVo> typeheadvoAssetList = assetlist.stream().map(assetvo->{
			return convertIntoTypeHeadVo(assetvo);
		}).collect(Collectors.toList());
		return typeheadvoAssetList;
	}
	public TypeHeadVo convertIntoTypeHeadVo(AssetVo assetvo) {

		TypeHeadVo typeHeadVo = new TypeHeadVo();
		typeHeadVo.setId(assetvo.getIdAsset());
		typeHeadVo.setName(assetvo.getAssetName());
		return typeHeadVo;
	
		}

	@Override
	public List<ProductDTO> getProductDetails() {
		List<ProductDTO> productDtolist=productDao.findAllProductDetails();
		System.out.println("Product dto list details in service manager is :::"+ productDtolist);
		
		
		return productDtolist;
	}
	
	@Override
	public List<AssetVo> findAssetByFilter(AssetFilter filter) {

		return assetDao.findAssetByFilter(filter);
	}

	@Override
	public void addAsset(AssetVo assetVo) {
		if (assetVo.getIdAsset() != null) {
			updateAsset(assetVo);
			return;
		}

		Asset asset = new Asset();
		BeanUtils.copyProperties(assetVo, asset);
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			String assetDescription=objectMapper.writeValueAsString(assetVo.getAssetDescription());
			asset.setAssetDescription(assetDescription);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("    *************"+assetVo.getAssetDescription());
		
		User user = new User();
		user.setIdUser(1l);
		Status status = new Status();
		status.setIdStatus(0);
		// status.setIdStatus(assetVo.getStatus());
		AssetType at = new AssetType();
		// at.setIdAssetType(assetVo.getIdAssetType());
		at.setIdAssetType(assetVo.getIdAssetType());
		asset.setUser1(user);
		asset.setUser2(user);
		asset.setAssetType(at);
		asset.setStatusBean(status);
		assetDao.addAsset(asset);
	}

	@Override
	public List<TypeHeadVo> findAllAssetType() {
		return assetDao.findAllAssetType();
	}

	@Override
	public void updateAsset(AssetVo assetVo) {
		assetDao.updateAsset(assetVo);
	}
}
