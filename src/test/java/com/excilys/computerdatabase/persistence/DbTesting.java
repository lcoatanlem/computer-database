package com.excilys.computerdatabase.persistence;

import com.excilys.computerdatabase.persistence.ConnectionJdbc;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.Before;

import org.junit.BeforeClass;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.sql.SQLException;


/**
 * This class permits to initiate, and populate the database for each test class
 * on persistence.
 * 
 * @author lcoatanlem
 *
 */
public class DbTesting {
  protected static final ConnectionJdbc CONNJDBCTEST = ConnectionJdbc.getInstance();

  /**
   * Getting the DataSet from the xml file built from the DB.
   * 
   * @return the dataset
   * @throws DataSetException if the DataSet is incorrect
   * @throws FileNotFoundException if the file doesn't exists
   */
  protected IDataSet getDataSet() throws FileNotFoundException, DataSetException {
    // Change for computer-database to run full entries tests.
    return new FlatXmlDataSetBuilder()
        .build(new FileInputStream("src/test/resources/computer-database-lite.xml"));
  }

  /**
   * Before each class, we connect to DBUnit and execute the script for the DB
   * configuration.
   * @throws SQLException if the script is not correct
   * 
   */
  @BeforeClass
  public static void createSchema() throws SQLException {
    RunScript.execute(CONNJDBCTEST.getUrl(), CONNJDBCTEST.getUser(), CONNJDBCTEST.getPwd(),
        "src/test/config/db-test/4-TESTSCHEMA.sql", Charset.forName("UTF8"), false);
  }

  /**
   * Before each test, we clean the DB, and populate it again.
   * @throws Exception from the method getDataSet and the method cleanlyInsert
   */
  @Before
  public void importDataSet() throws Exception {
    IDataSet dataSet = getDataSet();
    cleanlyInsert(dataSet);
  }

  /**
   * Method which permits to clean the DB and populate it again using the
   * DataSet.
   * 
   * @param dataSet the set of data
   * @throws Exception from method onSetup and IDatabaseTester
   */
  private void cleanlyInsert(IDataSet dataSet) throws Exception {
    IDatabaseTester databaseTester = new JdbcDatabaseTester(CONNJDBCTEST.getDriver(),
        CONNJDBCTEST.getUrl(), CONNJDBCTEST.getUser(), CONNJDBCTEST.getPwd());
    databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();
  }
}