/**
 * 
 */
package com.hm.dao.mysql.asset;

import java.util.List;

import com.hm.util.model.AssetVo;
import com.hm.util.model.ProductDTO;

/**
 * @author kiran
 *
 */
public interface AssetDao {

	public List<AssetVo> findAllAsset();

}
