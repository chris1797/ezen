package com.ezen.demo.aop.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ezen.demo.jpa.board.Board;
import com.ezen.demo.thymeleaf.person.Person;
import com.ezen.demo.thymeleaf.person.PersonRepository;
import com.ezen.demo.thymeleaf.person.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageService {
	
	private MessageManager mgr;
	
	@Autowired
	public void setCtx(MessageManager mgr) {
		this. mgr = mgr;
	}

	@Autowired
	private MessageRepository msgRepository;
	
	public Page<Message> getList(Pageable pageable) {
		Page<Message> msglist = msgRepository.findAll(pageable);
		
		return msglist;
	}
	
	public int[] getLinkRange(Page<Message> pageInfo) {
		int start = 0;
		int end = 0;

		if (pageInfo.getNumber() - 2 < 0) {
			start = 0;
		} else {
			start = pageInfo.getNumber() - 2;
		}

		if (pageInfo.getTotalPages() < (start + 4)) {
			end = pageInfo.getTotalPages();
			start = (end - 4) < 0 ? 0 : (end - 4);
		} else {
			end = start + 4;
		}
		return new int[] { start, end };
	}
	
	public boolean save(Message msg) {
		Message saveMsg = msgRepository.save(msg);
		return saveMsg.equals(msg);
	}
	
	public List<Message> getListByTOID(String id){
		List<Message> msglist = msgRepository.findByTOID(id);
		return msglist;
	}

	public boolean deleteByTOID(String uid) {
		return msgRepository.deleteAllByTOID(uid);
	}
}
