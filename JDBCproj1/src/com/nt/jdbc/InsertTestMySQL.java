package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTestMySQL {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String name=null,addrs=null;
		float avg=0.0f;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter student Id::");
				no=sc.nextInt();
				System.out.println("Enter student name::");
				name=sc.next();
				System.out.println("Enter student address::");
				addrs=sc.next();
				System.out.println("Enter student Id::");
				avg=sc.nextFloat();
				
			}
			//converting values
			name="'"+name+"'";
			addrs="'"+addrs+"'";
			//Register DriverClass
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish COnnection 
			con=DriverManager.getConnection("jdbc:mysql:///NTAJ415DB","root","root");
			//create statement object
			if(con!=null)
				st=con.createStatement();
			
			//prepare sql statement
			//insert into student values(101,'Raja','HYD',97.66);
			String query="INSERT INTO STUDENT VALUES("+no+","+name+","+addrs+","+avg+")";
			
			System.out.println(query);
			int count=0;
			if(st!=null)
				 count=st.executeUpdate(query);
			
			if(count==0)
				System.out.println("Not Inserted");
			else
				System.out.println("record inserted"+count);
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
			
	}

}
