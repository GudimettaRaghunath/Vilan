package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PSDateInsetTestMySQL {
	private static final String INSERT_DATE_QUERY="INSERT INTO PERSON_INFO_DETAILS(PNAME,DOB,DOJ,DOM) VALUES(?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			sc=new Scanner(System.in);
			String name=null,sdob=null,sdoj=null,sdom=null;
			
			if(sc!=null) {
				System.out.println("Enter person name::");
				name=sc.next();
				System.out.println("Enter person DOB(dd-MM-yyyy)::");
				sdob=sc.next();
				System.out.println("Enter person DOJ(yyyy-MM-dd)::");
				sdoj=sc.next();
				System.out.println("Enter person DOM(MMM-dd-yyyy)::");
				sdom=sc.next();
				
			}
			//converting above date values into java.sql.date values
			//for DOB
			//converting string date value into util date 
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob=sdf1.parse(sdob);
			//convert java.util.Date value to java.sql.date value
			long ms=udob.getTime();
			java.sql.Date sqdob=new java.sql.Date(ms);
			
			//for DOJ(yyyy-MM-dd)
			//here we convert directly
			java.sql.Date sqdoj=java.sql.Date.valueOf(sdoj);
			
			//for DOM(dd-MMM-yy)
			//converting string date value into util date 
			SimpleDateFormat sdf2=new SimpleDateFormat("MMM-dd-yyyy");
			java.util.Date udom=sdf2.parse(sdom);
			//convert java.util.Date value to java.sql.date value
			 ms=udom.getTime();
			java.sql.Date sqdom=new java.sql.Date(ms);
			
			//load jdbc driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establish connection 
			
			con=DriverManager.getConnection("jdbc:mysql:///NTAJ415DB1","root","root");
			
			//prepare statement obj
			if(con!=null)
				ps=con.prepareStatement(INSERT_DATE_QUERY);
				
			//SET VALUES TO QUERY PARAMS
			
			if(ps!=null) {
				ps.setString(1,name);
				ps.setDate(2, sqdob);
				ps.setDate(3, sqdoj);
				ps.setDate(4, sqdom);
			}
			int count=0;
			if(ps!=null) 
				count=ps.executeUpdate();
				
			if(count==0)
				System.out.println("Records are not inserted::");
			else
				System.out.println("Records are inserted::");
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close all connections
			try {
				if(ps!=null)
					ps.close();
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
