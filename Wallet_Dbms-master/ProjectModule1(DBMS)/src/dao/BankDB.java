package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankDB {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		String driverName = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driverName);
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sanskar","sans123");
		
		return con;
	}
}
