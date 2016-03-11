package test.java.com.excilys.computerdatabase.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import main.java.com.excilys.computerdatabase.exception.NotSuchCompanyException;
import main.java.com.excilys.computerdatabase.model.Company;
import main.java.com.excilys.computerdatabase.persistence.dao.impl.CompanyDAOImpl;

/**
 * CompanyDAO testing.
 * @author lcoatanlem
 *
 */
public class CompanyDAOTest {

	private static CompanyDAOImpl cDAO;

	@BeforeClass
	public static void initDAO(){
		cDAO = new CompanyDAOImpl();
	}

	@Test
	/**
	 * Tests findAll(), only normal use available.
	 */
	public void testFindAll(){
		List<Company> liste = cDAO.findAll(0,10);
		for (Company comp : liste){
			assertNotNull(comp.getId());
		}
		assertEquals(10,liste.size());
	}

	@Test
	/**
	 * Tests find(Long id) in a normal use.
	 */
	public void testFind(){
		Company comp = new Company();
		assertNull(comp.getId());
		assertNull(comp.getName());
		try {
			comp = cDAO.find(2L);
		} catch (NotSuchCompanyException e) {
			fail();
		}
		assertEquals((Long) 2L, comp.getId());
		assertEquals("Thinking Machines", comp.getName());
	}

	@Test
	/**
	 * Tests find(Long id) in an abnormal use (id not in DB).
	 */
	public void testFindExc(){
		Company comp = new Company();
		Company comptmp = comp;
		try {
			comptmp = cDAO.find(21L);
			fail();
		} catch (NotSuchCompanyException e) {
			assertEquals(comp,comptmp);
		}
	}

}
