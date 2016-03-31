package com.excilys.computerdatabase.persistence;

import com.excilys.computerdatabase.persistence.ConnectionJdbc;


/**
 * This class permits to initiate, and populate the database for each test class
 * on persistence.
 * 
 * @author lcoatanlem
 *
 */
public class DbTesting {
  protected static final ConnectionJdbc CONNJDBCTEST = ConnectionJdbc.getInstance();
}