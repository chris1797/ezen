package com.ezen.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.ezen.vo.EmpVO;

public class EmpDAO {
	//접근해야 할 경로명
	private static String path = "D:/test/emp.txt";
	
	public List<EmpVO> getList() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
		
			String line = null;
			List<EmpVO> list = new ArrayList<>();
		
			while((line=br.readLine()) != null) {
				String[] info = line.split(" ");

				EmpVO emp = new EmpVO(Integer.valueOf(info[0]),info[1],info[2],info[3]);
				list.add(emp);
			}
			br.close();
			return list;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public EmpVO getEmp(int num) {
		EmpVO empvo = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = null;
			
			while((line = br.readLine()) != null) {
				String info[] = line.split(" ");
				if(Integer.valueOf(info[0]) == num) {
					empvo = new EmpVO(Integer.valueOf(info[0]),info[1],info[2],info[3]);
				}
			}
			br.close();
			return empvo;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
