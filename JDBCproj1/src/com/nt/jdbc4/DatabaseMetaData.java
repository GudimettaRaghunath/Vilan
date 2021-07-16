package com.nt.jdbc4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMetaData {

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath")){
			
			java.sql.DatabaseMetaData dbmd=con.getMetaData();
			if(dbmd!=null) {
				
				System.out.println("db s/w name::"+dbmd.getDatabaseProductName());
				System.out.println("db s/w version::"+dbmd.getDatabaseProductVersion());
				System.out.println("db s/w driver name::"+dbmd.getDriverName());
				System.out.println("db s/w driver version::"+dbmd.getDriverVersion());

				System.out.println("db sql keywords::"+dbmd.getSQLKeywords());

				System.out.println("all numeric functions::"+dbmd.getNumericFunctions());
			}
			
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
