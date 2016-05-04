package com.excilys.computerdatabase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is the model of the companies, contains its builder. We type the
 * id as a Long, because it corresponds to the mysql BigInt, and can be null
 * (compared to long).
 * 
 * @author lcoatanlem
 */
@Entity
@Table(name = "company")
public class Company {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
    if (!(obj instanceof Company)) {
      return false;
    }
    Company other = (Company) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    // Useless from java 6, but very important before.
    StringBuilder stringBuilder = new StringBuilder("Company [id=").append(id).append(", name=")
        .append(name).append("]");
    return stringBuilder.toString();
  }

  public static Builder builder() {
    return new Company.Builder();
  }

  public static class Builder {
    private Company cpn = new Company();

    public Builder id(Long id) {
      this.cpn.id = id;
      return this;
    }

    public Builder name(String name) {
      this.cpn.name = name;
      return this;
    }

    public Company build() {
      return this.cpn;
		}
	}
}
