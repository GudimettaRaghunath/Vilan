package com.nt.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectTest3 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			sc=new Scanner(System.in);
			String initChar=null;
			
			if(sc!=null) {
				System.out.println("Enter init character::");
				initChar=sc.next();
			}
			initChar="'"+initChar+"%'";
			//TO load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
			
			if(con!=null)
				
			 st=con.createStatement();
				
			//prepare sql query
			String query="select empno,ename,job,sal from emp where ename like"+initChar;
			
			System.out.println(query);
			
			if(st!=null)
				rs=st.executeQuery(query);
			
			if(rs!=null) { 
				boolean flag = false;
				while(rs.next()) {
					flag=true;
					
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(4)+" "+rs.getFloat(4));
				}
				
				if(flag==false)
					System.out.println("no records found");
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
			try {
				if(sc!=null)
					sc.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
		
	}//main

}//class
