package com.excilys.computerdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dao.Dao;
import com.excilys.computerdatabase.dao.impl.UserDaoImpl;
import com.excilys.computerdatabase.exception.DaoException;
import com.excilys.computerdatabase.exception.IllegalMethodException;
import com.excilys.computerdatabase.model.User;
import com.excilys.computerdatabase.service.IService;
import com.excilys.computerdatabase.utils.Query;

@Service("userService")
public class UserServiceImpl implements IService<User>, UserDetailsService {

  @Autowired
  @Qualifier("userDao")
  private Dao<User> userDao;

  @Override
  public List<User> list(Query query) {
    throw new IllegalMethodException();
  }

  @Override
  public int count(Query query) {
    throw new IllegalMethodException();
  }

  @Override
  public void delete(Long id) {
    throw new IllegalMethodException();
  }

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user;
    try {
      user = ((UserDaoImpl) userDao).getByLogin(login);
      if (user == null)
        throw new UsernameNotFoundException("User name not found.");

    } catch (DaoException e) {
      throw new UsernameNotFoundException("Database error.");
    }
    return user;
  }
}