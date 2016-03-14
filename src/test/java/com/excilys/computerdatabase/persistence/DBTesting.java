package test.java.com.excilys.computerdatabase.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.com.excilys.computerdatabase.model.Computer;
import main.java.com.excilys.computerdatabase.persistence.ConnectionJDBC;
import main.java.com.excilys.computerdatabase.persistence.dao.impl.ComputerDAOImpl;

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
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/computer-database.xml"));
	}
	
	/**
	 * Before each class, we connect to DBUnit and execute the script for the DB configuration.
	 * @throws Exception
	 */
	@BeforeClass
	public static void createSchema() throws Exception {
		RunScript.execute(connJDBC_test.getUrl(), connJDBC_test.getUser(), connJDBC_test.getPwd(), "config/db-test/4-TESTSCHEMA.sql", Charset.forName("UTF8"), false);
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
	
	@Test
	public void testFindAll(){
		List<Computer> liste = new ComputerDAOImpl().findAll(0,10);
		for (Computer comp : liste){
			assertNotNull(comp.getId());
			assertNotNull(comp.getName());
		}
		assertTrue(liste.size() == 576);
	}
}