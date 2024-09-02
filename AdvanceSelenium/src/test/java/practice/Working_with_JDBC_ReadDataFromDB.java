package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class Working_with_JDBC_ReadDataFromDB {

	@Test
	public void readData() throws SQLException {
		

		//step 1: Load /register the database driver
		Driver driverref= new Driver();
		DriverManager.registerDriver(driverref);

		//step 2: connect to database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");

		//step 3: create a statement
		Statement stat = conn.createStatement();

		//step 4:execute select query and get result
		ResultSet rest = stat.executeQuery("select * from organisation");
		while(rest.next()) {
			System.out.println(rest.getString(1)+" "+rest.getString(2)+" "+rest.getInt(3));
		}
		conn.close();

	}
}
