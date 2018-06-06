package com.hm.dao.mysql.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hm.util.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
