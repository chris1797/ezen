package com.ezen.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer>{
	// 이게 DAO 역할을 하게 됨
	// JPA는 메소드 이름을 이용하여 SQL을 파생 (Query Method)
	// User 클래스의 uname 속성을 이용하여 이름으로 검색되도록 메소드 작성
	
	List<User> findByUname(String uname); //User테이블을 대상을 이름을 검색
	//select * from user_tb where name=uname;
	
	List<User> findByUnameAndEmail(String uname, String email);
	
	//num값이 5~10 사이에 있는 행을 추출하려고 한다. 우리가 만든 메소드에 우리가 만든 쿼리문을 넣은 것
	@Query("SELECT u FROM User u WHERE u.num BETWEEN ?1 AND ?2") // JPQL, 이름은 아무렇게나 써도 상관없음
	List<User> findAllNumBet(@Param("start") int start, @Param("end")int end); // named parameter
}
