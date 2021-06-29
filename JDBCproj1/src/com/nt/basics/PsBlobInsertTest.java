package com.nt.basics;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsBlobInsertTest {
	private static final String INSERT_ARTIST_QUERY="INSERT INTO ARTIST_INFO VALUES(AID_SEQ.NEXTVAL,?,?,?)";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)){//try1
			String name=null,addrs=null,photoLocation=null;
			if(sc!=null) {
				//read inputs
				System.out.println("Enter Artist name::");
				name=sc.next();
				System.out.println("Enter Artist Address::");
				addrs=sc.next();
				System.out.println("Enter Artist photoLocation::");
				photoLocation=sc.next();
			}
			try(InputStream is=new FileInputStream(photoLocation)){//try2
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
					PreparedStatement ps=con.prepareStatement(INSERT_ARTIST_QUERY);){//try3
					
				if(ps!=null) {
					ps.setString(1, name);
					ps.setString(2, addrs);
					ps.setBinaryStream(3,is);
				}
				//execute the query
				int count=0;
				if(ps!=null)
					count=ps.executeUpdate();
				
				if(count==0)
					System.out.println("Records Not inserted");
				else
					System.out.println("Record insert successfully");	
				
		}//try1
		}//try2
		}//try3
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
				
			
	}//main
}//class


