package com.ezen.demo.aop.message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="message")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSG_NUM_SEQ")
	@SequenceGenerator(sequenceName = "MSG_NUM_SEQ", allocationSize = 1, name = "MSG_NUM_SEQ")
	private int num;

	@NotEmpty
	private String fromid;
	
	@NotEmpty
	@Column(name="TOID")
	private String TOID;
	
	private java.sql.Date wdate;
	
	private String contents;
}
