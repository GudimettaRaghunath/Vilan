package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		String newName=null,newAdd=null;
		float avg=0.0f;
		int sno=0;
		Scanner sc=null;
		String cityName=null;
		Connection con=null;
		Statement st=null;
		try {
			
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter name::");
				newName=sc.nextLine();//gives name
				
				System.out.println("Enter cityName::");
				newAdd=sc.next();//gives city name
				
				System.out.println("Enter newAvg::");
				avg=sc.nextFloat();
				 System.out.println("Enter sid::");
				 sno=sc.nextInt();
				
			}
			newName="'"+newName+"'";//gives name as 'raghunath'
			newAdd="'"+newAdd+"'";//gives city name'hyd'
			
			
			
			//register the driver clsss
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
			
			//create statement
			if(con!=null)
				st=con.createStatement();
			//prepare query
			// update student6 set sname='Naresh',sadd='chennai',avg=92.66 where sid=102;
			String query="UPDATE STUDENT6 SET SNAME="+newName+",SADD="+newAdd+",AVG="+avg+"WHERE SID="+sno;
			
			System.out.println(query);
			
			int count=0;
			
			
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==0) 
				System.out.println("No records found");
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
