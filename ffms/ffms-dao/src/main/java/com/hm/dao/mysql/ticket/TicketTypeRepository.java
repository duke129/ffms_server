/**
 * 
 */
package com.hm.dao.mysql.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hm.util.entity.TicketType;

/**
 * @author kiran
 *
 */
public interface TicketTypeRepository extends JpaRepository<TicketType, Integer>{

}
