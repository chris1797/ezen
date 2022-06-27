package com.ezen.demo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.model.Fileupload;

@Mapper
public interface FileuploadMapper {

	boolean insertUpload(Fileupload uploadlist);
	
	boolean insertAttach(Fileupload uploadlist);

	List<Fileupload> getList();

}
