package com.excilys.computerdatabase.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.dao.Dao;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.QComputer;
import com.excilys.computerdatabase.utils.ComputerRowMapper;
import com.excilys.computerdatabase.utils.Query;
import com.excilys.computerdatabase.utils.QueryMapper;
import com.querydsl.jpa.hibernate.HibernateQuery;

/**
 * Class DAO for Computers, every method from interface DAO is implemented.
 * @author lcoatanlem
 */
@Repository("computerDao")
@Transactional
public class ComputerDaoImpl implements Dao<Computer> {

  @Resource(name="jdbcTemplate")
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private ComputerRowMapper computerRowMapper;
  @Autowired
  private SessionFactory sessionFactory;

  public Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  private static final Logger LOGGER = Logger.getLogger(ComputerDaoImpl.class);

  @Override
  public int count(Query query) {
    // There is a filter
    QComputer computer = QComputer.computer;
    HibernateQuery<?> querydsl = new HibernateQuery<Void>(getSession());
    if (query.getFilter() != null) {
      // 1,2,3,4 arguments of the PreparedStatement for filter
      querydsl = querydsl.select().where(computer.name.like(query.getFilter())
          .orAllOf(computer.manufacturer.name.like(query.getFilter())));
    }
    return (int) querydsl.from(computer).fetchCount();
  }

  @Override
  public List<Computer> findAll(Query query) {
    List<Object> args = new ArrayList<>();
    // There is a filter
    if (query.getFilter() != null) {
      // 1,2,3,4 arguments of the PreparedStatement for filter
      args.add(query.getFilter());
      args.add(query.getFilter());
      args.add(query.getFilter());
      args.add(query.getFilter());
      // Limit
      if (query.getLimit() > 0) {
        args.add(query.getLimit());
        // Offset can exist iff Limit exists
        if (query.getOffset() > 0) {
          args.add(query.getOffset());
        }
      }
    } else {
      // Limit
      if (query.getLimit() > 0) {
        args.add(query.getLimit());
        // Offset can exist iff Limit exists
        if (query.getOffset() > 0) {
          args.add(query.getOffset());
        }
      }
    }
    return jdbcTemplate.query(QueryMapper.toComputerFindAll(query), args.toArray(),
        computerRowMapper);
  }

  @Override
  public Computer read(Long id) {
    if (id == null) {
      LOGGER.debug("Trying to read a computer with a null id is forbidden.");
      throw new IllegalArgumentException("Id cannot be null.");
    }
    List<Object> args = new ArrayList<>();
    args.add(id);
    List<Computer> list = jdbcTemplate.query(QueryMapper.toComputerRead(), args.toArray(),
        computerRowMapper);
    if (list.isEmpty()) {
      return null;
    } else {
      return list.get(0);
    }
  }

  @Override
  public void create(Computer computer) {
    List<Object> args = new ArrayList<>();
    // Fill the query
    args.add(computer.getName());
    args.add(computer.getIntroduced() == null ? null : Date.valueOf(computer.getIntroduced()));
    args.add(computer.getDiscontinued() == null ? null : Date.valueOf(computer.getDiscontinued()));
    args.add(computer.getManufacturer() == null ? null : computer.getManufacturer().getId());
    jdbcTemplate.update(QueryMapper.toComputerCreate(), args.toArray());
  }

  @Override
  public void update(Computer computer) {
    List<Object> args = new ArrayList<>();
    // Fill the query
    args.add(computer.getName());
    args.add(computer.getIntroduced() == null ? null : Date.valueOf(computer.getIntroduced()));
    args.add(computer.getDiscontinued() == null ? null : Date.valueOf(computer.getDiscontinued()));
    args.add(computer.getManufacturer() == null ? null : computer.getManufacturer().getId());
    args.add(computer.getId());
    jdbcTemplate.update(QueryMapper.toComputerUpdate(), args.toArray());
  }

  @Override
  public void delete(Long id) {
    if (id == null) {
      LOGGER.debug("Trying to delete a computer with a null id is forbidden.");
      throw new IllegalArgumentException("Id cannot be null.");
    }
    List<Object> args = new ArrayList<>();
    args.add(id);
    jdbcTemplate.update(QueryMapper.toComputerDelete(), args.toArray());
  }

  /**
   * Method to delete computer with their company id.
   */
  public void deleteByCompany(Long id) {
    List<Object> args = new ArrayList<>();
    // Fill the query
    args.add(id);
    jdbcTemplate.update(QueryMapper.toComputerDeleteByCompany(), args.toArray());
  }
}
