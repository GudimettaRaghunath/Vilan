package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_AUTHUNTICATE 
(
  USERNAME IN VARCHAR2 
, PASSWORD IN VARCHAR2 
, RESULT OUT VARCHAR2 
) AS 
CNT NUMBER(5);
BEGIN
  SELECT COUNT(*) INTO CNT FROM IRCTC_TAB WHERE UNAME=USERNAME AND PWD=PASSWORD;
  IF(CNT<>0)THEN 
    RESULT:='VALID CREDENTIALS';
  ELSE
    RESULT:='INVALID CREDENTIALS';
  END IF;
END P_AUTHUNTICATE;
*/
public class CsProcedureTest_Auth {
private static final String CALL_PROCEDURE="{CALL P_AUTHUNTICATE(?,?,?) }";
	public static void main(String[] args) {
		String uname=null,pwd=null;
		//read inputs
		try(Scanner sc=new Scanner(System.in)){//try1
			if(sc!=null) {
				System.out.println("Enter user name::");
				uname=sc.next();
				System.out.println("Enter password::");
				pwd=sc.next();
			}
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
					CallableStatement cs=con.prepareCall(CALL_PROCEDURE);){//try2
				if(cs!=null)
					cs.registerOutParameter(3, Types.VARCHAR);
				
				if(cs!=null)
					cs.setString(1, uname);
					cs.setString(2,pwd);
			
					if(cs!=null)
						cs.execute();
					
					String result=null;
					if(cs!=null)
						result=cs.getString(3);
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
