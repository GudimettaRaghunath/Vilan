package com.nt.basics;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsBlobPhotoRetriveMySQL {
	private static final String PHOTO_RETRIVE_QUERY="SELECT AID,NAME,ADDRS,PHOTO FROM ARTIST_INFO WHERE AID=?";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)){//try1
			int id=0;
			if(sc!=null) {
				System.out.println("Enter AID:: ");
				id=sc.nextInt();
			}
			try(Connection con=DriverManager.getConnection("jdbc:mysql:///NTAJ415DB1","root","root");
				PreparedStatement ps=con.prepareStatement(PHOTO_RETRIVE_QUERY);	){//try2
				
				if(ps!=null)
					ps.setInt(1, id);
				try(ResultSet rs=ps.executeQuery()){//try3
					if(rs!=null) {
						if(rs.next()) {
							int aid=rs.getInt(1);
						String name=rs.getString(2);
						String addrs=rs.getString(3);
							System.out.println(aid+" "+name+" "+addrs);
							try(InputStream is=rs.getBinaryStream(4);
								OutputStream os=new FileOutputStream("retrive_img.jpg");	
									){//try4
								IOUtils.copy(is,os);
								System.out.println("BOLB value Retrived Successfully");
							}//try4
						}//if
						else
							System.out.println("Record not Retrived");
						
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
	

}
