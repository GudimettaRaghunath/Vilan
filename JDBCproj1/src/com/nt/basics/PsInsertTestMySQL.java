package com.nt.basics;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTestMySQL {
private static final String INSERT_JOBSEEKER_QUERY="INSERT INTO JOBSEEKER_INFO(JSNAME,JSADDRS,RESUME,PHOTO) VALUES(?,?,?,?)";
	public static void main(String[] args) {
		String name=null,addrs=null,resumeLocation=null,photoLoc=null;
		try(Scanner sc=new Scanner(System.in)){//try1
			if(sc!=null) {
				//read inputs
				System.out.println("Enter Jobseeker Name::");
				name=sc.next();
				System.out.println("Enter Jobseeker Addrs::");
				addrs=sc.next();
				System.out.println("Enter Jobseeker resumeLocation::");
				resumeLocation=sc.next().replace("?"," ");
				System.out.println("Enter jobseeker photo location");
				photoLoc=sc.next().replace("?"," ");
			}//if
			try(Reader reader=new FileReader(resumeLocation);
					InputStream is=new FileInputStream(photoLoc);){//try2
				try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj415db1","root","root");
						PreparedStatement ps=con.prepareStatement(INSERT_JOBSEEKER_QUERY);){//try3
					if(ps!=null) {
						ps.setString(1, name);
						ps.setString(2, addrs);
						ps.setCharacterStream(3, reader);
						ps.setBinaryStream(4,is);
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
