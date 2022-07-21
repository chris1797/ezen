package com.ezen.demo.thymeleaf.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTrackRepository extends JpaRepository<User_Track, Integer>{
	
	User_Track findByNum(int num);

}
