package com.nt.jdbc4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Type5DriverTest {
	private static final String SELECT_STUDENT_QUERY="SELECT SID,SNAME,SADD,AVG FROM STUDENT ";

	public static void main(String[] args) {
		
		try {
			Class.forName("com.ddtek.jdbc.oracle.OracleDriver");	
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		
		try(Connection con=DriverManager.getConnection("jdbc:datadirect:oracle://localhost:1521;serviceName=xe",
																						"system","raghunath");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(SELECT_STUDENT_QUERY);){
			
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}//while
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main

}//class
