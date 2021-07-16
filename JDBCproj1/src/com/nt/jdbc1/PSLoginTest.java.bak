package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PSLoginTest {
	private static final String IRCTC_APP="SELECT COUNT(*) FROM IRCTC_APP WHERE UNAME=? AND PWD=?";


	public static void main(String[] args) {
		
		Scanner sc=null;
		String uname=null,pwd=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter username::");
				uname=sc.next();
				System.out.println("Enter password::");
				pwd=sc.next();
			}
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
			
			if(con!=null)
				ps=con.prepareStatement(IRCTC_APP);
				
			if(ps!=null)
				ps.setString(1, uname);
				ps.setString(2, pwd);
				
			
			 rs=ps.executeQuery();
			if(rs!=null) {
				rs.next();
				int count=rs.getInt(1);
				if(count==0)
					System.out.println("INVALID CREDENTIALS");
				else

					System.out.println("VALID CREDENTIALS");
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close connections
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
