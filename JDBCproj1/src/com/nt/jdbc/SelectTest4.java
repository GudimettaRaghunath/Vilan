package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest4 {
	
	public static void main(String[] args) {
		
		Scanner sc=null;
		int dno=0;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			sc=new Scanner(System.in);
			if(sc!=null) 
				System.out.println("Enter dept no::");
				dno=sc.nextInt();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
			
			if(con!=null)
				st=con.createStatement();
			 
			String query="SELECT DEPTNO,DNAME,LOC FROM DEPT WHERE DEPTNO="+dno;
			
			System.out.println(query);
			
			if(st!=null)
				
				rs=st.executeQuery(query);
			if(rs!=null)	
				
			if(rs.next()) {
			
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				}
			else
				System.out.println("records not found");
		}catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}catch(Exception e) {
				e.printStackTrace();
			}	
			
		}//finally
		
	}//main

}//class
