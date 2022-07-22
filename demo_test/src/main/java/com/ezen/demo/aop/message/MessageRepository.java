package com.ezen.demo.aop.message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MessageRepository extends JpaRepository<Message, Integer>{

	
	List<Message> findByTOID(String toid);

	@Transactional
	@Modifying
	@Query("delete FROM Message m WHERE m.TOID=?1")
	boolean deleteAllByTOID(String uid);

}
