package com.nt.basics;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsClobInsertOracleTest {
private static final String INSERT_JOBSEEKER_QUERY="INSERT INTO JOBSEEKER_INFO VALUES(JSID_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		String name=null,addrs=null,resumeLocation=null;
		try(Scanner sc=new Scanner(System.in)){//try1
			if(sc!=null) {
				//read inputs
				System.out.println("Enter Jobseeker Name::");
				name=sc.next();
				System.out.println("Enter Jobseeker Addrs::");
				addrs=sc.next();
				System.out.println("Enter Jobseeker resumeLocation::");
				resumeLocation=sc.next();
			}//if
			try(Reader reader=new FileReader(resumeLocation)){//try2
				try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
						PreparedStatement ps=con.prepareStatement(INSERT_JOBSEEKER_QUERY);){//try3
					if(ps!=null) {
						ps.setString(1, name);
						ps.setString(2, addrs);
						ps.setCharacterStream(3, reader);
					}//if
				int count=0;
				if(ps!=null)
					count=ps.executeUpdate();
				if(count==0)
					System.out.println("Record not inserted");
				else
					System.out.println("Records inserted");
				}//try3
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main

}//class
