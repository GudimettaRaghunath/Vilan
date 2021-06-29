package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMYSQLDataTransferTest {
	private static final String ORACLE_SELECT_STUDENT="SELECT SID,SNAME,SADD,AVG FROM STUDENT6";
	private static final String MYSQL_INSERT_STUDENT="INSERT INTO STUDENT(SID,SNAME,SADD,AVG)VALUES(?,?,?,?)";
	

	public static void main(String[] args) {
		Connection oraCon=null;
		Connection mysqlCon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establish connection 
			oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
			mysqlCon=DriverManager.getConnection("jdbc:mysql:///NTAJ415DB","root","root");
			
			//create statement objs
			if(oraCon!=null)
				st=oraCon.createStatement();
			if(mysqlCon!=null)
				ps=mysqlCon.prepareStatement(MYSQL_INSERT_STUDENT);
			//send and execute query in oracle db s/w  and get resultset obj
			if(st!=null)
				rs=st.executeQuery(ORACLE_SELECT_STUDENT);
			
			if(rs!=null) {
				while(rs.next()) {
				//gather each record from rs
					int no=rs.getInt(1);
					String name=rs.getString(2);
					String addrs=rs.getString(3);
					float avg=rs.getFloat(4);
				//set each record value as insert query param values to insert into mysql
					ps.setInt(1, no);
					ps.setString(2, name);
					ps.setString(3, addrs);
					ps.setFloat(4, avg);
					
					ps.executeUpdate();
					
				}//while
				System.out.println("records are inserted successfully::");
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
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(mysqlCon!=null)
					mysqlCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(oraCon!=null)
					oraCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		
		}//finally
	}//main

}//class
