package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_BY_ID 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, DESG OUT VARCHAR2 
, SALARY OUT VARCHAR2 
) AS 
BEGIN
  SELECT ENAME,JOB,SAL INTO NAME,DESG,SALARY FROM EMP WHERE EMPNO=NO;
END P_GET_EMP_DETAILS_BY_ID;

*/

public class CsCallebaleTest2 {
	private static final String CALL_PROCEDURE="{CALL P_GET_EMP_DETAILS_BY_ID(?,?,?,?)}";

	public static void main(String[] args) {//try1
		int eno=0;
		//read inputs
		try(Scanner sc=new Scanner(System.in)){
			if(sc!=null) {
				System.out.println("Enter Employee no::");
				eno=sc.nextInt();
			}
			//register driver and establish the connection 
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
					//create callable statement and call the pl/sql procedure
					CallableStatement cs=con.prepareCall(CALL_PROCEDURE);){//try2
				//register the OUT param vales with JDBC data types	
				if(cs!=null) {
					cs.registerOutParameter(2, Types.VARCHAR);
				    cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.FLOAT);
				}
				//set IN param value
				if(cs!=null)
					cs.setInt(1, eno);
				//execute and call procedure	
				if(cs!=null)
				cs.execute();
				
				
				//gather results from out params
				if(cs!=null) {
					String name=cs.getString(2);
					String desg=cs.getString(3);
					float salary=cs.getFloat(4);
					System.out.println("name::"+name+"desg::"+desg+"salary::"+salary);
				}
				
					
				
			}//try2
		}//try1
		catch(SQLException se) {
			System.out.println("Invalid details::");
			
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main

}//class
