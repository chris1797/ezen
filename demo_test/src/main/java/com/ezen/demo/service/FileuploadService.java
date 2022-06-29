package com.ezen.demo.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

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

	public boolean insert(Fileupload vo) {
		dao.insertUpload(vo);    //upload_tb에 저장
		int pnum = vo.getNum();  // 자동 증가된 업로드 번호를 받음
		
		List<AttachVO> attList = vo.getAttach();

		int totalSuccess = 0;
		for(int i=0;i<attList.size();i++)
		{
			Map<String,Object> fmap = new HashMap<>();
			fmap.put("pnum", Integer.valueOf(pnum));
			fmap.put("fname", attList.get(i).getFname());
			fmap.put("fpath", vo.getFpath());
			totalSuccess += dao.insertAttach(fmap);   // 첨부파일 정보 저장
		}
		return totalSuccess==attList.size();
	}
	
	public List<Fileupload> getList() {
		List<Map<String,Object>> list = dao.getList();
		List<Fileupload> volist = new ArrayList<>();
		
		for(int i=0; i<list.size(); i++) {
			Map<String, Object> map = list.get(i);
			
			String fname = (String) map.get("FNAME");
			
			
			AttachVO attvo = null;
			
			if(fname != null) {
				attvo = new AttachVO();
				int fnum = ((BigDecimal)map.get("FNUM")).intValue();
				attvo.setNum(fnum);
				attvo.setFname(fname);
			}
			
			Fileupload vo = new Fileupload();
			int num = ((BigDecimal)map.get("NUM")).intValue();
			vo.setNum(num);
			
			if(volist.contains(vo)) {
				volist.get(volist.size()-1).getAttach().add(attvo);
			} else {
				String writer = (String) map.get("WRITER");
				Date udate = new Date(((Timestamp)map.get("UDATE")).getTime());
				String comments = (String) map.get("COMMENTS");
				
				vo.setWriter(writer);
				vo.setUdate(udate);
				vo.setComments(comments);
				vo.getAttach().add(attvo);
				
				volist.add(vo);
			}
		}
		return volist;
	}

	public String getFname(int num) {
		String fname = dao.getFname(num);
		return fname;
	}

	public List<Fileupload> getDetailByNum(int num) {
		List<Map<String,Object>> list = dao.getDetailByNum(num);
		List<Fileupload> volist = new ArrayList<>();
		
		for(int i=0; i<list.size(); i++) {
			Map<String, Object> map = list.get(i);
			
			int unum = ((BigDecimal)map.get("NUM")).intValue();
			int fnum = ((BigDecimal)map.get("FNUM")).intValue();
			String fname = (String) map.get("FNAME");
			
			
			AttachVO attvo = null;
			if(fname != null) {
			attvo = new AttachVO();
			attvo.setNum(fnum);
			attvo.setFname(fname);
			}
			
			Fileupload vo = new Fileupload();
			vo.setNum(unum);
			
			if(volist.contains(vo)) {
				volist.get(volist.size()-1).getAttach().add(attvo);
			} else {
				String writer = (String) map.get("WRITER");
				Date udate = new Date(((Timestamp)map.get("UDATE")).getTime());
				String comments = (String) map.get("COMMENTS");
				
				vo.setWriter(writer);
				vo.setUdate(udate);
				vo.setComments(comments);
				vo.getAttach().add(attvo);
				
				volist.add(vo);
			}
		}
		return volist;
	}

	public boolean remove(int num) {
		int removed = dao.remove(num);
		return removed > 0;
	}

}
