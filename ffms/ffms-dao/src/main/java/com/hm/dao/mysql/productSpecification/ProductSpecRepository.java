/**
 * 
 */
package com.hm.dao.mysql.productSpecification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hm.util.entity.Area;
import com.hm.util.entity.Product;
import com.hm.util.entity.ProductSpecification;

/**
 * @author kiran
 *
 */
@Repository
public interface ProductSpecRepository extends JpaRepository<ProductSpecification, Long>{

}
