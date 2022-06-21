package com.ezen.demo.mappers;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.vo.Dept;

@Mapper   // 구현될 dao method를 여기다 선언, 구현은 MyBatis가 한다.
public interface DeptMapper {
	List<Dept> getList();
}
