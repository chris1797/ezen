package com.ezen.demo.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.model.Fileupload;

@Mapper
public interface FileuploadMapper {

	int insertUpload(Fileupload vo);
	
	int insertAttach(Map<String, Object> map);

	List<Map<String, Object>> getList();
	
	String getFname(int num);

}
