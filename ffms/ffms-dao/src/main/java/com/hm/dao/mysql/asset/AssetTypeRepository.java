package com.hm.dao.mysql.asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hm.util.entity.Asset;
import com.hm.util.entity.AssetType;

@Repository
public interface AssetTypeRepository extends JpaRepository<AssetType, Long> {
}
