package com.ezen.demo.thymeleaf.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_NUM_SEQ")
	@SequenceGenerator(sequenceName = "PERSON_NUM_SEQ", allocationSize = 1, name = "PERSON_NUM_SEQ")
	private int num;
	
	@NotEmpty(message="이름은 필수 입력항목입니다.")
	@Size(min=2, message="이름은 2자 이상입니다.")
	@Column(name="name")
	private String name;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	@Min(value=18)
	private int age;
	
}
