package com.excilys.computerdatabase.model;

/**
 * This class is the model of the companies. It is immutable (for example). There is a builder.
 * 
 * @author lcoatanlem
 */
public class Company {
  private final Long id;
  private final String name;

  private Company(final Long id, final String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
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
    return "Company [id=" + id + ", name=" + name + "]";
  }

  /**
   * Builder for Companies.
   * 
   * @author lcoatanlem
   *
   */
  public static class CompanyBuilder {
    private Long id;
    private String name;

    public CompanyBuilder() {
    }

    public CompanyBuilder id(final Long id) {
      this.id = id;
      return this;
    }

    public CompanyBuilder name(final String name) {
      this.name = name;
      return this;
    }
    
    public Company build() {
      return new Company(id, name);
    }
  }
}
