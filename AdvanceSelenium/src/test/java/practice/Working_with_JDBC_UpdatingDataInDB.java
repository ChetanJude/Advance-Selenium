package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class Working_with_JDBC_UpdatingDataInDB {

	public static void main(String[] args) throws SQLException {
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		Statement stmt = conn.createStatement();
		int rest = stmt.executeUpdate("insert into organisation values('105','Testing3','888655551');");
		System.out.println(rest);
		conn.close();
	}

}
