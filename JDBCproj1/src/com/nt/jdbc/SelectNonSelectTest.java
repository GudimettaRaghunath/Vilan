package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectTest {

	public static void main(String[] args) {
		Scanner sc=null;
		String query=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count;
		
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter the Query::");
				query=sc.nextLine();
				
			}
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
			
			if(con!=null)
				st=con.createStatement();
			
			if(st!=null) {
				boolean flag=st.execute(query);
				System.out.println(flag);
				if(flag==true) {
					System.out.println("Select query executed");
					rs=st.getResultSet();
					
					if(rs!=null) {
						while(rs.next()) {
							System.out.println(rs.getString(1)+","+rs.getString(2)+" "+rs.getString(3));
						}//while
					}//if
					
				}//if
				else {
					System.out.println("Non select Query excuted");
					count=st.getUpdateCount();
					System.out.println("No of records effected::"+count);
					
				}//else
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
