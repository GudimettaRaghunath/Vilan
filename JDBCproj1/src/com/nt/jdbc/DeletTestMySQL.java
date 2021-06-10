package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeletTestMySQL {

	public static void main(String[] args) {
		
		Scanner sc=null;
		String addrs=null;
		Connection con=null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter cityName::");
				addrs=sc.next();//gives city name
			}
			addrs="'"+addrs+"'";//gives city name'hyd'
			//register the driver clsss
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establish connection
			con=DriverManager.getConnection("jdbc:mysql:///NTAJ415DB","root","root");
			
			//create statement
			if(con!=null)
				st=con.createStatement();
			//prepare query
			
			String query="DELETE FROM STUDENT WHERE SADD="+addrs;
			
			System.out.println(query);
			
			int count=0;
			
			
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==0) {
				System.out.println("No records found");
			}
			else
				System.out.println("records in student DB:"+count);
			
		}catch(SQLException se) {
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
		
	}//main

}//class
