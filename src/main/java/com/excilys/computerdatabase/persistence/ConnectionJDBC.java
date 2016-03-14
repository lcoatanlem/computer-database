package main.java.com.excilys.computerdatabase.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class is singleton and contains an unique instance, which itself contains an unique connection to the base.
 * Using properties file to get connection informations, this file exists in two exemplars : one for the normal DB, another one for DBUnit.
 *  
 * @author lcoatanlem
 */
public class ConnectionJDBC {
	private static final ConnectionJDBC INSTANCE;
	
	private static final String PROPERTIES_FILE = "connection.properties";
	private String url;
	private String driver;
	private String user;
	private String pwd;

	/**
	 * Private constructor, to make an instance of the class from all properties.
	 * @param url
	 * @param driver
	 * @param user
	 * @param pwd
	 */
	private ConnectionJDBC(String url, String driver, String user, String pwd){
		this.url = url;
		this.driver = driver;
		this.user = user;
		this.pwd = pwd;
	}

	static {
		String url;
		String driver;
		String user;
		String pwd;
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

		if (propertiesFile == null) {
			throw new RuntimeException("The properties file " +  PROPERTIES_FILE + " can't be found");
		}
		try {
			properties.load(propertiesFile);
			url = properties.getProperty("url");
			driver = properties.getProperty("driver");
			user = properties.getProperty("user");
			pwd = properties.getProperty("pwd");
		} catch (IOException e) {
			throw new RuntimeException("Fail loading the properties file " + PROPERTIES_FILE);
		}
		try{
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
		
		INSTANCE = new ConnectionJDBC(url,driver,user,pwd);
	}
	/**
	 * Method returning the instance of the connection using properties file, creating it if it does not exists.
	 * @return Connection
	 * @throws FileNotFoundException if there was an error loading properties file
	 */
	public static ConnectionJDBC getInstance() {
		return INSTANCE;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, pwd);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
