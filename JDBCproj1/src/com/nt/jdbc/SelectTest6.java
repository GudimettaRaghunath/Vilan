package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest6 {
	
	public static void main(String[] args) {
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
			
			if(con!=null)
				st=con.createStatement();
			 
			String query=" SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL)FROM EMP)";
			
			System.out.println(query);
			
			if(st!=null)
			rs=st.executeQuery(query);
			
			if(rs!=null)	{
				boolean flag=false;
				
				if(rs.next()) {
					flag=true;
					
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					
				}
				if(flag==false)
					System.out.println("No records");
			}
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
			
		}//finally
		
	}//main

}//class
