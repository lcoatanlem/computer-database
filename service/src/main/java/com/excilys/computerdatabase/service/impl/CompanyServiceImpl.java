package com.excilys.computerdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.dao.Dao;
import com.excilys.computerdatabase.dao.impl.ComputerDaoImpl;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.IService;
import com.excilys.computerdatabase.utils.Query;

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
  @Override
  public List<Company> list(Query query) {
    return companyDao.findAll(query);
  }

  /**
   * Counts the number of rows in the databases and set it into the page.
   */
  @Override
  public int count(Query query) {
    return companyDao.count(query);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public void delete(Long id) {
    ((ComputerDaoImpl) computerDao).deleteByCompany(id);
    companyDao.delete(id); // returns Runtime ("Not yet")
  }

}
