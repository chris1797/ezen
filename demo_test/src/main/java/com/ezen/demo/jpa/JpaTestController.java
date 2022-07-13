package com.ezen.demo.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpa")
public class JpaTestController {
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository; //JPA가 구현해놓은 객체
	
	@GetMapping("")
	public String jpaTest() {
		return "JPA Test";
	}
	
	@GetMapping("/save")
	public ResponseEntity<User> saveUser() {
		User user = new User();
		user.setUname("smith");
		user.setEmail("smith@gmail.com");
		
		User savedUser = userRepository.save(user); // 레코드 추가 (한 행 추가)
		boolean saved = user.getUname().equals(savedUser.getUname());
		
		logger.debug("num={}, uname={}, email={}", 
				savedUser.getNum(), savedUser.getUname(), savedUser.getEmail());
		
		return new ResponseEntity<>(savedUser, HttpStatus.OK); 
		//HttpStatus는 Header에 OK를 넣어주는 것
		//OK, NOT_FOUND, INTERNAL_SERVER_ERROR(서버로직에 문제가 있어서 웹브라우저 화면에 정상적으로 출력할 수 없을 때)
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<User>> findAllUser() {
		
		List<User> userlist = userRepository.findAll();
		
		return new ResponseEntity<>(userlist, HttpStatus.OK); 
	}
	
	@GetMapping("/findbyid/{num}")
	public ResponseEntity<User> findbyid(@PathVariable("num") int num) {
		
		Optional<User> op = userRepository.findById(num);
		User user = op.get();
		if(op.isEmpty()) {
			//검색 실패
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK); 
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<User>> findByUname(@PathVariable("name") String uname){
		List<User> userlist = userRepository.findByUname(uname);
		
		return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
	}
	
	@GetMapping("/name_email/{name}/{email}")
	public ResponseEntity<List<User>> findByUnameAndEmail(@PathVariable("name") String uname, 
														@PathVariable("email") String email){
		List<User> userlist = userRepository.findByUnameAndEmail(uname, email);
		
		return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
	}
	
	@GetMapping("/update/{num}/{name}/{email}")
	public ResponseEntity<User> update(@PathVariable("num") int num,
										@PathVariable("name") String uname,
										@PathVariable("email") String email) {
		User user = new User();
		user.setNum(num);
		user.setUname(uname);
		user.setEmail(email);
		
		User savedUser = userRepository.save(user); // 레코드 추가 (한 행 추가)
		
		return new ResponseEntity<>(savedUser, HttpStatus.OK); 
	}
	
	@GetMapping("/delete/{num}")
	public String delete(@PathVariable("num") int num) {
		
		Optional<User> op = userRepository.findById(num);
		
		if(op.isPresent()) {
			userRepository.deleteById(num);
			return "삭제 성공";
		} else {
			return "삭제 실패";
		}
	}
	
	@GetMapping("/jpql/{start}/{end}")
	public ResponseEntity<List<User>> getAllBet(@PathVariable("start") int start,
												@PathVariable("start") int end) {
		
		return new ResponseEntity<>(userRepository.findAllNumBet(start, end), HttpStatus.OK);
	}
	
}
