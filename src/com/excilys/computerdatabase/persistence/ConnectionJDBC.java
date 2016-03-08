package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class instantiates the connection if it does not exists, else it returns the one existing.
 * Use the pattern Singleton to avoid multiple connections to the DB.
 * @author lcoatanlem
 */
public class ConnectionJDBC {
	private static String url = "jdbc:mysql://localhost:3306/computer-database-db";
	private static String user = "admincdb";
	private static String pwd = "qwerty1234";
	private static Connection conn;
	
	/**
	 * Method returning the instance of the connection, creating it if it does not exists.
	 * @return Connection
	 */
	public static Connection getInstance(){
		if (conn == null){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, pwd);
			}catch(SQLException e){
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
