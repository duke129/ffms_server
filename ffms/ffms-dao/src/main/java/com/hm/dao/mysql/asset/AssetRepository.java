package com.hm.dao.mysql.asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hm.util.entity.Asset;

@Repository

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
