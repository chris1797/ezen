package com.ezen.demo.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/files")
public class UploadController 
{
   @Autowired
   ResourceLoader resourceLoader;

   @GetMapping("/upload")
   public String getForm() {
      return "upload/upload_form";
   }
   
   @PostMapping("/upload")
   @ResponseBody
   public String upload(@RequestParam("files")MultipartFile[] mfiles, /* spring이 지원해주는 class
		   																파일의 정보가 mfiles에 담겨서 객체배열로 저장 */
		   				HttpServletRequest request,
		   				@RequestParam("author") String author) {
      ServletContext context = request.getServletContext();
      String savePath = context.getRealPath("/WEB-INF/files");
      // WEB-INF를 쓴다는건 client로 부터 안전한 디렉토리
      //스테틱은 브라우저에서 바로 접근이 가능한 디렉토리

      /* static/upload 디렉토리에 업로드하려면, 아래처럼 절대경로를 구하여 사용하면 된다
      * Resource resource = resourceLoader.getResource("classpath:/static");
      * String absolutePath = resource.getFile().getAbsolutePath();
      */ 
      try {
         for(int i=0;i<mfiles.length;i++) {
        	 String[] token = mfiles[i].getOriginalFilename().split("\\.");
        	String pName = token[0] + System.nanoTime() + "." + token[1];
            mfiles[i].transferTo( // 메모리에 있는 파일을 저장경로에 옮기는 method, local 디렉토리에 있는 그 파일만 셀렉가능
              new File(savePath+"/"+pName));
//            MultipartFile 주요 메소드
//            String cType = mfiles[i].getContentType();
//            String pName = mfiles[i].getName();
//            Resource res = mfiles[i].getResource();
//            long fSize = mfiles[i].getSize();
            
//            boolean empty = mfiles[i].isEmpty();
//            
         }
         String msg = String.format("파일(%d)개 저장성공(작성자:%s)", mfiles.length,author);
         for(int i=0; i<mfiles.length; i++) {
        	 msg += String.format("<div>파일명 : %s / 사이즈(Byte) : %d</div>", mfiles[i].getOriginalFilename(), mfiles[i].getSize());
        	 msg += String.format("<div>파일명 : %s / 사이즈(Byte) : %d</div>", mfiles[i].getOriginalFilename(), mfiles[i].getSize());
         }
         return msg;
      } catch (Exception e) {
         e.printStackTrace();
         return "파일 저장 실패:";
      }
   }
   
   @GetMapping("/download/{filename}")
   public ResponseEntity<Resource> download(
         HttpServletRequest request,
         @PathVariable String filename){
      Resource resource = resourceLoader.getResource("WEB-INF/files/"+filename);
      System.out.println("파일명:"+resource.getFilename());
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
 
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                //HttpHeaders.CONTENT_DISPOSITION는 http header를 조작하는 것, 화면에 띄우지 않고 첨부화면으로 넘어가게끔한다 
                //filename=\"" + resource.getFilename() + "\"" 는 http프로토콜의 문자열을 고대로 쓴 것
                .body(resource);
   }
}
