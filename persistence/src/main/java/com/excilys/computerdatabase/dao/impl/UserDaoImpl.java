package com.excilys.computerdatabase.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.dao.Dao;
import com.excilys.computerdatabase.model.User;
import com.excilys.computerdatabase.utils.QueryMapper;
import com.excilys.computerdatabase.utils.UserRowMapper;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements Dao<User> {
  @Resource(name = "jdbcTemplate")
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private UserRowMapper userRowMapper;
  @Autowired
  private SessionFactory sessionFactory;

  public Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  public User getByLogin(String login) {
    List<Object> args = new ArrayList<>();
    args.add(login);
    List<User> list = jdbcTemplate.query(QueryMapper.toUserGetByLogin(), args.toArray(),
        userRowMapper);
    if (list.isEmpty() || list.size() > 1) {
      return null;
    } else {
      return list.get(0);
    }
  }
}
