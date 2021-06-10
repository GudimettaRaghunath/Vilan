package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Scanner;

public class PsInsertTest {
	private static final String STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			sc=new Scanner(System.in);
			int count=0;
			if(sc!=null) {
				System.out.println("Enter number of students::");
				count=sc.nextInt();
				
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection("jdbc:mysql:///NTAJ415DB","root","root");
			
			if(con!=null) 
				ps=con.prepareStatement(STUDENT_INSERT_QUERY);
				
				if(ps!=null && sc!=null) {
					for(int i=1;i<=count;i++) {
						System.out.println("Enter Student no::");
						int sid=sc.nextInt();
						System.out.println("Enter Student name::");
						String sname=sc.next();
						System.out.println("Enter Student address::");
						String sadd=sc.next();
						System.out.println("Enter Student avg::");
						float avg=sc.nextFloat();
						
						ps.setInt(1, sid); 
						ps.setString(2,sname);
						ps.setString(3, sadd);
						ps.setFloat(4, avg);
						
						int result=ps.executeUpdate();
						
						if(result==0)
							System.out.println("NO records inserted");
						else
							System.out.println(" records are inserted");
						
					}//for
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
