package com.excilys.computerdatabase.persistence;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;
import java.sql.Connection;

/**
 * Class with a main which permits to create an XML file from the DB, which will
 * be used for DBUnit as an input.
 * 
 * @author lcoatanlem
 */
public class DbExportToXml {
  /**
   * There is only a main in this class, and should be run every time
   * you want to actualize the file computer-database.xml
   */
  public static void main(String[] args) throws Exception {
    // Connection to Database
    Connection conn = ConnectionJdbc.getInstance().getConnection();
    IDatabaseConnection connection = new DatabaseConnection(conn);
    // Database Export
    IDataSet fullDataSet = connection.createDataSet();
    // Writing results in an XML file
    FlatXmlDataSet.write(fullDataSet, 
        new FileOutputStream("src/test/resources/computer-database.xml"));
    conn.close();
  }
}