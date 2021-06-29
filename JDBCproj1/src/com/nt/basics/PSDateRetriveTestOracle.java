package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PSDateRetriveTestOracle {
	private static final String RETRIVE_DATES_QUERY="SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_INFO_DETAILS";

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//register driver clsass
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
			if(con!=null)
				ps=con.prepareStatement(RETRIVE_DATES_QUERY);
			
			if(ps!=null)
				rs=ps.executeQuery();
			
			//if(rs!=null) { 
				//while(rs.next()) { 
					//System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
				//	System.out.println("Record Retrived");
			//	}//while
		//	}//if
			if(rs!=null) {
				while(rs.next()) {
					int pid=rs.getInt(1);
					String name=rs.getString(2);
					java.sql.Date sqdob=rs.getDate(3);
					java.sql.Date sqdoj=rs.getDate(4);
					java.sql.Date sqdom=rs.getDate(5);
					//convert java.sql.Date obj to string class obj
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
					String sdob=sdf.format(sqdob);
					String sdoj=sdf.format(sqdoj);
					String sdom=sdf.format(sqdom);
							
					System.out.println(pid+" "+name+" "+sdob+" "+sdoj+" "+sdom);
				}//while
			}//if)
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
