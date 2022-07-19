package com.ezen.demo.thymeleaf.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ezen.demo.jpa.board.Board;
import com.ezen.demo.jpa.emp2.Emp2;
import com.ezen.demo.jpa.emp2.emp2Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;

//===============================================================================
	public Page<Person> getList(Pageable pageable) {
		Page<Person> pageInfo = personRepository.findAll(pageable);
		
		return pageInfo;
	}
//===============================================================================
	public boolean add(Person person) {
		log.trace("input_person={}", person.toString());
		Person addedperson = personRepository.save(person);
		
		return addedperson.equals(person);
	}
//===============================================================================
	public int[] getLinkRange(Page<Person> pageInfo) {
		int start = 0;
		int end = 0;

		if (pageInfo.getNumber() - 2 < 0) {
			start = 0;
		} else {
			start = pageInfo.getNumber() - 2;
		}

		if (pageInfo.getTotalPages() < (start + 4)) {
			end = pageInfo.getTotalPages();
			start = (end - 4) < 0 ? 0 : (end - 4);
		} else {
			end = start + 4;
		}
		return new int[] { start, end };
	}
//===============================================================================
	public Person findByNum(int num) {
		return personRepository.findByNum(num);
	}
	public boolean update(Person person) {
		System.out.println(person.getNum());
		System.out.println(person.getName());
		System.out.println(person.getEmail());
		System.out.println(person.getAge());
		
		return 0 < personRepository.update(person.getNum(), person.getName(), person.getEmail(), person.getAge());
	}
	public void delete(int num) {
		personRepository.deleteById(num);
	}
	
}
