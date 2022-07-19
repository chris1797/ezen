package com.ezen.demo.thymeleaf.person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface PersonRepository extends JpaRepository<Person, Integer>{

	Person findByName(String name);

	Person findByNum(int num);

	@Transactional
	@Modifying
	@Query("UPDATE Person p SET p.name=?2, p.email=?3, p.age=?4 WHERE p.num = ?1")
	int update(int num, String name, String email, int age);

}
