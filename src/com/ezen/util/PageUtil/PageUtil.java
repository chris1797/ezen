package com.ezen.util.PageUtil;

import java.util.*;

public class PageUtil {
	private int currPage;
	private int lastPage;
	

	public PageUtil(int currPage, int ttlPages) {
		this.currPage = currPage;
		this.lastPage = ttlPages;
	}

	public List<Integer> getNavs() { // EL에서 콜할때는 get빼고 navs로 콜하면 됨, 첫 대소문자 변경
		int start = this.currPage-2 >= 1 ? this.currPage-2 : 1;
		int end = start+4 <= this.lastPage ? start+4 : this.lastPage;
		System.out.println(lastPage);
		if( end == this.lastPage) {
			start = end-4;
			if(start<1) start=1;
		}
		System.out.println(start +","+ end);
		
		List<Integer> nums = new ArrayList<>();
		for(int i=start; i<=end; i++) {
			nums.add(i);
		}
		return nums;
	}
	
	public int getCurrPage() {
		return currPage;
	}
	
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	
	public int getLastPage() {
		return lastPage;
	}
	
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
}
