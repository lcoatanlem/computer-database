package com.excilys.computerdatabase.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
  private String login;
  private String password;
  private String role;

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getrole() {
    return role;
  }

  public void setrole(String role) {
    this.role = role;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((login == null) ? 0 : login.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((role == null) ? 0 : role.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof User)) {
      return false;
    }
    User other = (User) obj;
    if (login == null) {
      if (other.login != null) {
        return false;
      }
    } else if (!login.equals(other.login)) {
      return false;
    }
    if (password == null) {
      if (other.password != null) {
        return false;
      }
    } else if (!password.equals(other.password)) {
      return false;
    }
    if (role != other.role) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "User [login=" + login + ", password=" + password + ", role=" + role + "]";
  }

  public static Builder builder() {
    return new Builder();
  }

  private static class Builder {
    private User user = new User();

    public Builder login(String login) {
      this.user.login = login;
      return this;
    }

    public Builder password(String password) {
      this.user.password = password;
      return this;
    }

    public Builder role(String role) {
      this.user.role = role;
      return this;
    }

    public User build() {
      return this.user;
    }
  }

}
