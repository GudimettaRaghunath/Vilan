package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableRSTest {
private static final String STUDENT_SELECT_QUERY="SELECT SID,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {

		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raghunath");
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=st.executeQuery(STUDENT_SELECT_QUERY);
				){
			if(rs!=null) {
				System.out.println("Records top to bottem");
				while(rs.next()) {
						
					System.out.println(rs.getRow()+"------> "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
						
				}//while
				
				/*
				 * //insert operation rs.moveToInsertRow(); rs.updateInt(1, 103);
				 * rs.updateString(2, "Raja"); rs.updateString(3, "Hyd"); rs.updateFloat(4,
				 * 78.00f); rs.insertRow();
				 * 
				 * System.out.println("Record insertion successful.");
				 */
				//update operation
				
				/*
				 * rs.absolute(3); rs.updateString(3, "Bangolore"); rs.updateRow();
				 * System.out.println("Upadte succefully");
				 */
				//delete operation
				
				rs.absolute(3);
				rs.deleteRow();
				
			}//if
		}//try
		
			
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}//main

}//class
