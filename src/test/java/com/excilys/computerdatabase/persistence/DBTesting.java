package com.excilys.computerdatabase.persistence;

import java.io.FileInputStream;
import java.nio.charset.Charset;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import com.excilys.computerdatabase.persistence.ConnectionJDBC;

/**
 * This class permits to initiate, and populate the database for each test class on persistence.
 * @author lcoatanlem
 *
 */
public class DBTesting {
	protected final static ConnectionJDBC connJDBC_test = ConnectionJDBC.getInstance();

	/**
	 * Getting the DataSet from the xml file built from the DB.
	 * @return the dataset
	 * @throws Exception
	 */
	protected IDataSet getDataSet() throws Exception {
		// Change for computer-database to run full entries tests.
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/computer-database-lite.xml"));
	}
	
	/**
	 * Before each class, we connect to DBUnit and execute the script for the DB configuration.
	 * @throws Exception
	 */
	@BeforeClass
	public static void createSchema() throws Exception {
		RunScript.execute(connJDBC_test.getUrl(), connJDBC_test.getUser(), connJDBC_test.getPwd(), "baz: enkgjnzlv kjrtntest/config/db-test/4-TESTSCHEMA.sql", Charset.forName("UTF8"), false);
	} 
	
	/**
	 * Before each test, we clean the DB, and populate it again.
	 * @throws Exception
	 */
	@Before
	public void importDataSet() throws Exception {
		IDataSet dataSet = getDataSet();
		cleanlyInsert(dataSet);
	}
	
	/**
	 * Method which permits to clean the DB and populate it again using the DataSet.
	 * @param dataSet
	 * @throws Exception
	 */
	private void cleanlyInsert(IDataSet dataSet) throws Exception {
		IDatabaseTester databaseTester = new JdbcDatabaseTester(connJDBC_test.getDriver(),connJDBC_test.getUrl(), connJDBC_test.getUser(), connJDBC_test.getPwd());
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}
}