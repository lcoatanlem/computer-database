package com.excilys.computerdatabase.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
public class User implements UserDetails {

  private static final long serialVersionUID = -5007720604206767696L;

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

  public User() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((login == null) ? 0 : login.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
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
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
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
    return true;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", login=" + login + ", password=" + password + "]";
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    // Unsupported
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // Unsupported
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // Unsupported
    return true;
  }

  @Override
  public boolean isEnabled() {
    // Unsupported
    return true;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private User user = new User();

    public Builder id(Long id) {
      this.user.id = id;
      return this;
    }

    public Builder login(String login) {
      this.user.login = login;
      return this;
    }

    public Builder password(String password) {
      this.user.password = password;
      return this;
    }

    public User build() {
      return this.user;
    }
  }

}
