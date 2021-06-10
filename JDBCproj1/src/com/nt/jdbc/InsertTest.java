package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
		
		String name=null,city=null;
		int no=0;
		float avg=0.0f;
		
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter name::");
				name=sc.nextLine();
			
				System.out.println("Enter cityName::");
				city=sc.next();//gives city name

				System.out.println("Enter avg");
				avg=sc.nextFloat();
				
				System.out.println("Enter sno:");
				no=sc.nextInt();
				
			}
			name="'"+name+"'";
			city="'"+city+"'";//gives city name'hyd'
			//register the driver clsss
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
			
			//create statement
			if(con!=null)
				st=con.createStatement();
			//prepare query
			// insert into student6 values(105,'yshu','kakinada',66)
			String query=" INSERT INTO STUDENT6 VALUES("+no+","+name+","+city+","+avg+")";
			
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
