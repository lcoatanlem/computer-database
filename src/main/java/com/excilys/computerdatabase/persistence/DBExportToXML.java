package main.java.com.excilys.computerdatabase.persistence;

import java.io.FileOutputStream;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/** 
 * Class with a static instance which permits to create an XML file from the DB.
 * @author lcoatanlem
 */
public class DBExportToXML {
	public static void main(String[] args) throws Exception {
		// Connection to Database
		IDatabaseConnection connection = new DatabaseConnection(ConnectionJDBC.getInstance());
		// Database Export
		IDataSet fullDataSet = connection.createDataSet();
		// Writing results in an XML file
		FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));
	} 
}
