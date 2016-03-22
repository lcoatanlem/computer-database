package com.excilys.computerdatabase.persistence;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class is singleton and contains an unique instance, which itself
 * contains a connection to the base. Using properties file to get connection
 * informations, this file exists in two exemplars : one for the normal DB,
 * another one for DBUnit.
 * 
 * @author lcoatanlem
 */
public class ConnectionJdbc {
  private static Logger log = Logger.getLogger(ConnectionJdbc.class);

  private static final ConnectionJdbc INSTANCE;

  private static final String PROPERTIES_FILE = "connection.properties";
  private String url;
  private String user;
  private String pwd;
  private String driver;

  /**
   * Private constructor, to make an instance of the class from all properties.
   * 
   * @param url
   *          of the db
   * @param driver
   *          of the SGBD
   * @param user
   *          for the connection
   * @param pwd
   *          for the connection
   */
  private ConnectionJdbc(String url, String user, String pwd) {
    this.url = url;
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
      throw new RuntimeException("The properties file " + PROPERTIES_FILE + " can't be found");
    }

    try {
      properties.load(propertiesFile);
      url = properties.getProperty("url");
      driver = properties.getProperty("driver");
      user = properties.getProperty("user");
      pwd = properties.getProperty("pwd");
    } catch (IOException exn) {
      log.error("Fail loading the properties file " + PROPERTIES_FILE);
      throw new RuntimeException("FATAL : Fail loading the properties file " + PROPERTIES_FILE);
    }
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException exn) {
      log.error("FATAL : Class " + driver + "was not found.");
      throw new RuntimeException(exn);
    }

    INSTANCE = new ConnectionJdbc(url, user, pwd);
  }

  /**
   * Method returning the instance of the connection using properties file,
   * creating it if it does not exists.
   * 
   * @return Connection
   * @throws FileNotFoundException
   *           if there was an error loading properties file
   */

  public static ConnectionJdbc getInstance() {
    return INSTANCE;
  }

  /**
   * Method to get the connection to the db, raises a Runtime Exception if we
   * can't connect to the db.
   * 
   * @return the connection to the db.
   */
  public Connection getConnection() {
    Connection connDb = null;
    try {
      connDb = DriverManager.getConnection(url, user, pwd);
    } catch (SQLException exn) {
      log.error("FATAL : connection to the db refused.");
      throw new SecurityException("Connection to the db refused.");
    }
    return connDb;
  }

  protected String getUrl() {
    return url;
  }

  protected String getUser() {
    return user;
  }

  protected String getPwd() {
    return pwd;
  }

  protected String getDriver() {
    return driver;
  }
}
