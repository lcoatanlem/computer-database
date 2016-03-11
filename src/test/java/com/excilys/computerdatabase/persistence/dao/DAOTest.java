package test.java.com.excilys.computerdatabase.persistence.dao;

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
import org.junit.Test;

import main.java.com.excilys.computerdatabase.persistence.ConnectionJDBC;

/**
 * CompanyDAO testing.
 * @author lcoatanlem
 *
 */
public class DAOTest {
	protected static ConnectionJDBC connJDBC_test;
	
	public DAOTest(){
		connJDBC_test = ConnectionJDBC.getInstance();
	}

	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/computer-database.xml"));
	}
	
	@BeforeClass
	public static void createSchema() throws Exception {
		RunScript.execute(connJDBC_test.getUrl(), connJDBC_test.getUser(), connJDBC_test.getPwd(), "src/test/resources/4-TESTBASE.sql", Charset.forName("UTF8"), false);
	} 
	
	@Before
	public void importDataSet() throws Exception {
		IDataSet dataSet = getDataSet();
		cleanlyInsert(dataSet);
	}
	
	private void cleanlyInsert(IDataSet dataSet) throws Exception {
		Class.forName(connJDBC_test.getUrl());
		IDatabaseTester databaseTester = new JdbcDatabaseTester(connJDBC_test.getDriver(),connJDBC_test.getUrl(), connJDBC_test.getUser(), connJDBC_test.getPwd());
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	@Test
	/**
	 * Tests findAll(), only normal use available.
	 */
	public void testFindAll(){
	}

}