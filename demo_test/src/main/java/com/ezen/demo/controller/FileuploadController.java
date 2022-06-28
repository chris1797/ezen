package com.ezen.demo.controller;

import java.io.File;
import java.sql.Timestamp;
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
import com.ezen.demo.model.AttachVO;
import com.ezen.demo.model.Fileupload;
import com.ezen.demo.service.FileuploadService;

@Controller
@RequestMapping("/filesupload")
public class FileuploadController {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	private FileuploadService svc;
	
	@GetMapping("/upload")
	public String getForm() {
		return "fileupload/fileupload_form";
	}
	
	@PostMapping("/upload")
	   @ResponseBody
	   public String upload(@RequestParam("files")MultipartFile[] mfiles,
			   				HttpServletRequest request,
			   				Fileupload fileupload,
			   				AttachVO attach) {
	      ServletContext context = request.getServletContext();
	      String savePath = context.getRealPath("/WEB-INF/files");
			
	      attach.setFpath(savePath);
			
			/* static/upload 디렉토리에 업로드하려면, 아래처럼 절대경로를 구하여 사용하면 된다
			* Resource resource = resourceLoader.getResource("classpath:/static");
			* String absolutePath = resource.getFile().getAbsolutePath();
			*/ 
			try {
				for(int i=0;i<mfiles.length;i++) {
					
					String fname_orign = mfiles[i].getOriginalFilename();
					String[] token = fname_orign.split("\\.");
					String fname_changed = token[0]+System.nanoTime()+"."+token[1];
					
					
					mfiles[i].transferTo(
					  new File(savePath+"/"+fname_changed));
					
					attach.setFname(fname_changed);
					/* MultipartFile 주요 메소드
					String cType = mfiles[i].getContentType();
					String pName = mfiles[i].getName();
					Resource res = mfiles[i].getResource();
					long fSize = mfiles[i].getSize();
					boolean empty = mfiles[i].isEmpty();
					*/
				}
				//String msg = String.format("파일(%d)개 저장성공(작성자:%s)", mfiles.length,author);
				
				boolean inserted = svc.insert(fileupload);
				
				return "inserted=" + inserted;
			} catch (Exception e) {
				e.printStackTrace();
				return "파일 저장 실패:";
			}
	}
	
	@GetMapping("/list")
	public String getList(Model model) {
		//model.addAttribute("vo.fnames", );
		// 행 중복을 제거하려면 view에 보내기 전에 가공이 필요
		// List<Map<String, Object>> list = dao.getList();
		// list를 loof돌려서 넣어야 함
		// > List<Fileupload> list = dao.getList();
		List<AttachVO> list = svc.getList();
		model.addAttribute("list", list);
		return "fileupload/fileupload_list";
	}
}
