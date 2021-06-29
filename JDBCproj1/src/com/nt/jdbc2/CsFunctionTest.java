package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*  CREATE OR REPLACE FUNCTION FX_GET_STUDENT_DETAILS_BY_NO 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, ADDRS OUT VARCHAR2 
) RETURN FLOAT AS 
PERSENTAGE FLOAT;
BEGIN
  SELECT SNAME,SADD,AVG INTO NAME,ADDRS,PERSENTAGE FROM STUDENT WHERE SID=NO;
  RETURN PERSENTAGE;
END FX_GET_STUDENT_DETAILS_BY_NO;
*/
public class CsFunctionTest {
	private static final String CALL_FX_QUERY="{?=call FX_GET_STUDENT_DETAILS_BY_NO(?,?,?) }"	;

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)){
			int sno=0;
			if(sc!=null) {
				System.out.println("Enter student number::");
				sno=sc.nextInt();
			}//if
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
					CallableStatement cs=con.prepareCall(CALL_FX_QUERY);){
		
				if(cs!=null)
					cs.registerOutParameter(1, Types.FLOAT);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.VARCHAR);
				if(cs!=null) 
					cs.setInt(2, sno);
				if(cs!=null)
					cs.execute();
				if(cs!=null) {
					System.out.println("student name::"+cs.getString(3));
					System.out.println("student addrs::"+cs.getString(4));
					System.out.println("student avg::"+cs.getFloat(1));
					
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
