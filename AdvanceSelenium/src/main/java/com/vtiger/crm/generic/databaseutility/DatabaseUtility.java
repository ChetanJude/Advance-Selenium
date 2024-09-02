package com.vtiger.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	Connection conn;
	public void getConnection() throws Throwable {
		try{
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		}catch(Exception e) {

		}
	}
	public void closeConnection() {
		try {
			conn.close();
		}catch(Exception e){

		}
	}
	public ResultSet executeSelectQuery(String query) throws SQLException {
		ResultSet result =null;
		try{
			Statement stmt = conn.createStatement();
			result = stmt.executeQuery(query);
		}catch(Exception e) {
		}
		return result;
	}
	public int executeNonSelectQuery(String query) {
		int result=0;
		try {
			Statement stmt = conn.createStatement();
			result=stmt.executeUpdate(query);
		}catch(Exception e) {

		}
		return result;
	}

}
