package com.excilys.computerdatabase.persistence.dao.impl;

import com.excilys.computerdatabase.mapping.dao.CompanyRowMapper;
import com.excilys.computerdatabase.mapping.query.Query;
import com.excilys.computerdatabase.mapping.query.QueryMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.dao.Dao;
import com.jolbox.bonecp.BoneCPDataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Class DAO for Companies, methods findAll, count and find are defined, all
 * others methods from interface DAO (CRUD) will raise IllegalMethodException.
 * 
 * @author lcoatanlem
 */
@Repository("companyDao")
public class CompanyDaoImpl implements Dao<Company> {

  private JdbcTemplate jdbcTemplateObject;

  @Autowired
  private CompanyRowMapper companyRowMapper;

  @Autowired
  public void setDataSource(BoneCPDataSource dataSource) {
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }

  private static final Logger LOGGER = Logger.getLogger(CompanyDaoImpl.class);

  @Override
  public int count(Query query) {
    List<Object> args = new ArrayList<>();
    // Limit is defined
    if (query.getLimit() > 0) {
      args.add(query.getLimit());
      // Offset is defined
      if (query.getOffset() > 0) {
        args.add(query.getOffset());
      }
    }
    return jdbcTemplateObject.queryForObject(QueryMapper.toCompanyCount(query), Integer.class,
        args.toArray());
  }

  @Override
  public List<Company> findAll(Query query) {
    List<Object> args = new ArrayList<>();
    // Limit is defined
    if (query.getLimit() > 0) {
      args.add(query.getLimit());
      // Offset is defined
      if (query.getOffset() > 0) {
        args.add(query.getOffset());
      }
    }
    return jdbcTemplateObject.query(QueryMapper.toCompanyFindAll(query), args.toArray(),
        companyRowMapper);
  }

  @Override
  public Company read(Long id) {
    if (id == null) {
      LOGGER.debug("Trying to read a company with a null id is forbidden.");
      throw new IllegalArgumentException("Id cannot be null.");
    }
    List<Object> args = new ArrayList<>();
    args.add(id);
    List<Company> list = jdbcTemplateObject.query(QueryMapper.toCompanyRead(), args.toArray(),
          companyRowMapper);
    if (list.isEmpty()) {
      return null;
    } else {
      return list.get(0);
    }

  }
}
