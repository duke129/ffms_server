/**
 * 
 */
package com.hm.dao.mysql.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hm.util.entity.Area;
import com.hm.util.entity.Product;

/**
 * @author kiran
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
