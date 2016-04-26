package com.excilys.computerdatabase.service.impl;

import com.excilys.computerdatabase.mapping.query.Query;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.Dao;
import com.excilys.computerdatabase.persistence.dao.impl.ComputerDaoImpl;
import com.excilys.computerdatabase.service.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("companyService")
public class CompanyServiceImpl implements IService<Company> {

  @Autowired
  @Qualifier("companyDao")
  private Dao<Company> companyDao;

  @Autowired
  @Qualifier("computerDao")
  private Dao<Computer> computerDao;

  /**
   * Method to list all companies. To paginate, it starts with an index and a
   * number of instances we want.
   */
  public List<Company> list(Query query) {
    return companyDao.findAll(query);
  }

  /**
   * Counts the number of rows in the databases and set it into the page.
   */
  public int count(Query query) {
    return companyDao.count(query);
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public void delete(Long id) {
    ((ComputerDaoImpl) computerDao).deleteByCompany(id);
    companyDao.delete(id); // returns Runtime ("Not yet")
  }

}
