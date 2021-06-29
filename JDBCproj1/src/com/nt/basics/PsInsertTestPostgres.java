package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTestPostgres {
	private static final String PRODUCT_INSERT_QUERY="INSERT INTO PRODUCT VALUES(NEXTVAL('PID_SEQ'),?,?,?,?)";

	public static void main(String[] args) {
		
		
		try(Scanner sc=new Scanner(System.in)) {//try1
			int count=0;
			if(sc!=null) {
				System.out.println("Enter product count::");
				count=sc.nextInt();
			}//if
			//Establish COnnection 
			try(Connection con=DriverManager.getConnection("jdbc:postgresql:NTAJ415DB","postgres","tiger");
					PreparedStatement ps=con.prepareStatement(PRODUCT_INSERT_QUERY);){//try2
					
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;++i) {
					System.out.println("Enter"+i+" product details");

						System.out.println("Enter product name::");
						String name=sc.next();
						System.out.println("Enter product price::");
						float price=sc.nextFloat();
						System.out.println("Enter product quantity::");
						int qty=sc.nextInt();
						System.out.println("Enter product status::");
						String status=sc.next();
						
						//set param values
						ps.setString(1, name);
						ps.setFloat(2, price);
						ps.setInt(3, qty);
						ps.setString(4, status);
						
						int result=ps.executeUpdate();
						
						if(result==0)
							System.out.println("produt detsils are not inserted");
						else
							System.out.println("Product details are inserted");
						
				}//for
			
			}//if
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
