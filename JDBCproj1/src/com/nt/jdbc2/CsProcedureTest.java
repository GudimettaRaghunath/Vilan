package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureTest {
	private static final String CALL_PROCEDURE="{CALL FIRST_PRO(?,?,?)}";

	public static void main(String[] args) {
		int first=0,second=0;
		try(Scanner sc=new Scanner(System.in)){//try1
			if(sc!=null) {
				System.out.println("Enter first number::");
				first=sc.nextInt();
				System.out.println("Enter second number::");
				second=sc.nextInt();
				
			}//if
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
					CallableStatement cs=con.prepareCall( CALL_PROCEDURE);){//TRY2
		
				if(cs!=null)
					cs.registerOutParameter(3,Types.INTEGER);
				//set IN param values
				if(cs!=null) 
					cs.setInt(1, first);
					cs.setInt(2, second);
				
				if(cs!=null)
					cs.execute();
				
				int result=0;
				if(cs!=null)
					result=cs.getInt(3);
					System.out.println("Result::"+result);
							
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
