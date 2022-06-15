package com.ezen.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.ezen.vo.EmpVO;

public class EmpDAO 
{
	private static String path = "D:/test/emp.txt";
	
	public List<EmpVO> getList() 
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			String line = null;
			List<EmpVO> list = new ArrayList<>();
			
			while((line=br.readLine())!=null)
			{
				String[] info = line.split(" ");
				EmpVO emp = new EmpVO(Integer.valueOf(info[0]),info[1],info[2],info[3]);
				list.add(emp);
			}
			br.close();
			return list;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public EmpVO getEmp(String sNum) 
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			String line = null;
			EmpVO key = new EmpVO(Integer.parseInt(sNum));
			while((line=br.readLine())!=null)
			{
				EmpVO emp = new EmpVO(line.split(" "));

				if(emp.equals(key)) {
					br.close();
					return emp;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean delete(String sNum) 
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			String line = null;
			List<EmpVO> list = new ArrayList<>();
			
			while((line=br.readLine())!=null)
			{
				String[] info = line.split(" ");
				if(info[0].equals(sNum)) continue;  // <-- 삭제대상 발견시
				EmpVO emp = new EmpVO(Integer.valueOf(info[0]),info[1],info[2],info[3]);
				list.add(emp);
			}
			br.close();
			return overwrite(list);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	private boolean overwrite(List<EmpVO> list) 
	{
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(EmpDAO.path));
			for (int i=0;i<list.size();i++) {
				pw.println(list.get(i));
			}
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(EmpVO key) {
		// 파일에서 로드하면서 동일객체 검색, setPhone(), setEmail() 호출
		// 로드된 리스트 데이터를 기존 파일에 덮어쓰기
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			String line = null;
			List<EmpVO> list = new ArrayList<>();
			
			while((line=br.readLine())!=null)
			{
				EmpVO emp = new EmpVO(line.split(" "));
				if(emp.equals(key)) // <-- 수정대상 발견시
				{ 
					emp.setPhone(key.getPhone());
					emp.setEmail(key.getEmail());
				}
				list.add(emp);
			}
			br.close();
			return overwrite(list);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean add(EmpVO emp) 
	{
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(EmpDAO.path, true));
			pw.println(emp);
			pw.close();  
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
