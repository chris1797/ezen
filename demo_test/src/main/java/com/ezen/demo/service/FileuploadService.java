package com.ezen.demo.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.demo.mappers.FileuploadMapper;
import com.ezen.demo.model.AttachVO;
import com.ezen.demo.model.Fileupload;

@Service
public class FileuploadService 
{
	@Autowired
	private FileuploadMapper dao;

	public boolean insert(Fileupload vo) 
	{
		dao.insertUpload(vo);    //upload_tb에 저장
		int pnum = vo.getNum();  // 자동 증가된 업로드 번호를 받음
		
		List<AttachVO> attList = vo.getFname();

		int totalSuccess = 0;
		for(int i=0;i<attList.size();i++) {
			Map<String,Object> fmap = new HashMap<>();
			fmap.put("pnum", Integer.valueOf(pnum));
			fmap.put("fname", attList.get(i));
			fmap.put("fpath", vo.getFpath());
			totalSuccess += dao.insertAttach(fmap);   // 첨부파일 정보 저장
		}
		return totalSuccess==attList.size();
	}
	
	public List<AttachVO> getList(){
		List<Map<String, Object>> list = dao.getList();
		List<AttachVO> attvoList = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			Map<String, Object> map = list.get(i);
			
			int nums = ((BigDecimal)map.get("NUM")).intValue();
			String fnames = (String) map.get("FNAME");
			
			AttachVO attvo = new AttachVO();
			Fileupload vo = new Fileupload();
			vo.setNum(nums);
			
			if(attvoList.contains(vo)) {
				attvoList.get(attvoList.size()-1).setFname(fnames);
			} else {
				String writer = (String) map.get("WRITER");
				Date udate = new Date(((Timestamp)map.get("UDATE")).getTime());
				String comments = (String) map.get("COMMENTS");
				
				vo.setWriter(writer);
				vo.setUdate(udate);
				vo.setComments(comments);
				attvo.setFname(fnames);
				
				attvoList.add(attvo);
			}
		}
		return attvoList;
	}


}
