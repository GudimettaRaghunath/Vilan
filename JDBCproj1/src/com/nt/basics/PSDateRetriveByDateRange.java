package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PSDateRetriveByDateRange {
	private static final String RETRIVE_DATES_QUERY="SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_INFO_DETAILS WHERE DOB>=? AND DOB<=?";
	public static void main(String[] args) {
		Scanner sc=null;
		String sdob=null,edob=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
			System.out.println("Enter Staet range of DOB(dd-MM-yyyy)");
			sdob=sc.next();
			System.out.println("Enter End range of DOB(dd-MM-yyyy)");
			edob=sc.next();
		}
		//convert string date values into java.util.Date clas obj
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.sql.Date ssqdob=new java.sql.Date(sdf.parse(sdob).getTime());
		java.sql.Date esqdob=new java.sql.Date(sdf.parse(edob).getTime());
		
		//register driver clsass
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
		if(con!=null)
			ps=con.prepareStatement(RETRIVE_DATES_QUERY);
		if(ps!=null)
			rs=ps.executeQuery();
		if(ps!=null) {
			ps.setDate(1, ssqdob);
			ps.setDate(2, esqdob);
		}
		//if(rs!=null) {
			//while(rs.next()) {
				//System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
				
			//}//while
		//}//if
		if(rs!=null) {
			while(rs.next()) {
				int pid=rs.getInt(1);
				String name=rs.getString(2);
				java.sql.Date sqdob=rs.getDate(3);
				java.sql.Date sqdoj=rs.getDate(4);
				java.sql.Date sqdom=rs.getDate(5);
				//convert java.sql.Date obj to string class obj
				SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
				String dob=sdf1.format(sqdob);
				String doj=sdf1.format(sqdoj);
				String dom=sdf1.format(sqdom);
						
				System.out.println(pid+" "+name+" "+dob+" "+doj+" "+dom);
			}//while
		}//if

	}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close all connections
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main

}//class
