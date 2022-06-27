package com.ezen.demo.controller;

import java.io.File;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.demo.mappers.FileuploadMapper;
import com.ezen.demo.model.Fileupload;

@Controller
@RequestMapping("/filesupload")
public class FileuploadController {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	private FileuploadMapper dao;
	
	@GetMapping("/upload")
	public String getForm() {
		return "fileupload/fileupload_form";
	}
	
	@PostMapping("/upload")
	   @ResponseBody
	   public String upload(@RequestParam("files")MultipartFile[] mfiles,
			   				HttpServletRequest request,
			   				Fileupload fileupload) {
	      ServletContext context = request.getServletContext();
	      String savePath = context.getRealPath("/WEB-INF/files");
	      
	      try {
	    	  //업로드
	    	  dao.insertUpload(fileupload);
	    	  System.out.println(fileupload.getNum());
	    	  
	          for(int i=0;i<mfiles.length;i++) {
//	         	 String[] token = mfiles[i].getOriginalFilename().split("\\.");
//	         	 String pName = token[0] + System.nanoTime() + "." + token[1];
	             mfiles[i].transferTo( // 메모리에 있는 파일을 저장경로에 옮기는 method, local 디렉토리에 있는 그 파일만 셀렉가능
	               new File(savePath+"/"+mfiles[i].getOriginalFilename()));
//	             MultipartFile 주요 메소드
//	             String cType = mfiles[i].getContentType();
//	             String pName = mfiles[i].getName();
//	             Resource res = mfiles[i].getResource();
//	             long fSize = mfiles[i].getSize();
	             
//	             boolean empty = mfiles[i].isEmpty();
//	             
//	             fileupload.setFname(mfiles[i].getOriginalFilename());
	          }
	          
	          
	          for(int i=0; i<mfiles.length; i++	) {
	        	  int key = fileupload.getNum();
	        	  String pname = mfiles[i].getOriginalFilename();
	        	  fileupload.setPnum(key);
	        	  fileupload.setFname(pname);
	        	  fileupload.setFpath(savePath);
	        	  dao.insertAttach(fileupload);
	          }
	          
	          String msg = String.format("파일(%d)개 저장성공(작성자:%s)", mfiles.length,fileupload.getWriter());
	          return msg;
	       } catch (Exception e) {
	          e.printStackTrace();
	          return "파일 저장 실패:";
	       }
	}
	
	@GetMapping("/list")
	public String getList(Model model) {
		model.addAttribute("list", dao.getList());
		return "fileupload/fileupload_list";
	}
}
