package com.ezen.demo.thymeleaf.person;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_track")
public class User_Track {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_TRACK_NUM_SEQ")
	@SequenceGenerator(sequenceName = "USER_TRACK_NUM_SEQ", allocationSize = 1, name = "USER_TRACK_NUM_SEQ")
	private int num;
	
	@NotNull
	private String user_id;
	
	@NotNull
	private java.sql.Date wdate;
	
	@NotNull
	private String userlog;
}
