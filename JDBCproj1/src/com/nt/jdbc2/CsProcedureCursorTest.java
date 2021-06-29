package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*CREATE OR REPLACE PROCEDURE P_GET_EMPS_DETAILS_INITCHAR 
(
  INITCHAR IN VARCHAR2 
, DETAILS OUT SYS_REFCURSOR 
) AS 

BEGIN
  OPEN DETAILS FOR
    SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE ENAME LIKE INITCHAR;
END P_GET_EMPS_DETAILS_INITCHAR;
*/
public class CsProcedureCursorTest {
	
private static final String RETRIVE_CURSOR_QUERY="{CALL P_GET_EMPS_DETAILS_INITCHAR(?,?) }";

	public static void main(String[] args) {
		String initChars=null;
		try(Scanner sc=new Scanner(System.in)){
			if(sc!=null) {
				System.out.println("Enter the inital character::");
				initChars=sc.next().toUpperCase()+"%";
				
			}
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
					CallableStatement cs=con.prepareCall( RETRIVE_CURSOR_QUERY);
					){
				if(cs!=null)
					cs.registerOutParameter(2, OracleTypes.CURSOR);
				if(cs!=null)
					cs.setString(1,initChars);
				if(cs!=null)
					cs.execute();
				if(cs!=null) {
					ResultSet rs=(ResultSet)cs.getObject(2);
					boolean flag=false;
					while(rs.next()){
						flag=true;
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4)+" "+rs.getInt(5));
					}//while
					if(flag==false)
						System.out.println("Record not available");
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
