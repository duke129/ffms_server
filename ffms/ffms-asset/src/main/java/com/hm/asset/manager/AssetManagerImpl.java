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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hm.dao.mysql.asset.AssetDaoImpl;
import com.hm.dao.mysql.product.ProductDao;
import com.hm.util.model.AssetVo;
import com.hm.util.model.ProductDTO;
import com.hm.util.model.TypeHeadVo;

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
}
