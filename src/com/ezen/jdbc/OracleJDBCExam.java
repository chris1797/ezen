package com.ezen.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.ezen.dao.EmployeeDAO;
import com.ezen.vo.Employee;

public class OracleJDBCExam 
{
   public static void main(String[] args) 
   {
//      System.out.println("Oracle 11gR2 XE, ojdbc8.jar 테스트");
//      Connection conn = null;
//      try {
//         conn = DriverManager.getConnection(
//                   "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
//         Statement stmt = conn.createStatement();
//         ResultSet rs = stmt.executeQuery("SELECT * FROM emp");
//         
//         while(rs.next())
//         {
//            int empno = rs.getInt("EMPNO");
//            String ename = rs.getString("ENAME");
//            java.sql.Date hiredate = rs.getDate("HIREDATE");
//            float salary = rs.getFloat("SAL");
//            
//            System.out.printf("%d\t%s\t%s\t%f \n", empno,ename,hiredate,salary);
//         }
//         
//         rs.close();
//         stmt.close();
//         conn.close();
//         
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
      
      	EmployeeDAO dao = new EmployeeDAO();
      	List<Employee> list = dao.getList();
      	for(int i=0; i<list.size(); i++) {
      		System.out.println(list.get(i));
      	}
   }
}