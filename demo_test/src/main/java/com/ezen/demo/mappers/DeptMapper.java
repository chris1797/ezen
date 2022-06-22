package com.ezen.demo.mappers;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.vo.Dept;

@Mapper   // 구현될 dao method를 여기다 선언, MyBatis가 @Mapper를 구현한다.
public interface DeptMapper {
	List<Dept> getList(); // 전체 목록
	List<Dept> getListByDeptno(int deptno);
	Dept getInfoByDeptno(int deptno);
	
	int add(Dept dept); // 파라미터는 Dept, return은 int type
	int update(Dept dept);
	int delete(int deptno);
	
	List<Dept> getDeptByLike(String like);
	List<Map> getListMap(); // Map에 담아서 List로 포장
	
	List<Dept> getListByDeptnos(List<Integer> list);
}
