package com.ezen.demo.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.model.Empmail;

@Mapper
public interface MailMapper {
	String getEmailByEmpno(int empno);
}
