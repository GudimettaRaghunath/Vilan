package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginTest {

	public static void main(String[] args) {
		Scanner sc=null;
		String user=null,pwd=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter UserName::");
				user=sc.nextLine();//gives Raja Bathini
				System.out.println("Enter PassWord::");
				pwd=sc.nextLine();//gives Rani Gudimetta
			}
			//convert string to char
			user="'"+user+"'";
			pwd="'"+pwd+"'";
			
			//load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
			//create statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQl query
			// select count(*)from IRCTC_APP where uname='raja' and pwd='rani';
			String query="SELECT COUNT(*)FROM IRCTC_APP WHERE UNAME="+user+"AND PWD="+pwd;
			
			System.out.println(query);
			
			if(st!=null) {
				rs=st.executeQuery(query);
			if(rs!=null)
				rs.next();
				int count=rs.getInt(1);
				if(count==0) 
					System.out.println("Invalid Credentials");
				else
					System.out.println("valid Credentials");
			}//if	
					
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
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
		
		
	}

}
