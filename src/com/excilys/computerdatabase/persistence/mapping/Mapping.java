package com.excilys.computerdatabase.persistence.mapping;

import java.sql.ResultSet;

/**
 * This interface permits to abstract the functions mapAll which is both for a Computer and a Company.
 * @author lcoatanlem
 * @param <T> Computer || Company
 */
public interface Mapping<T> {
	public T map(ResultSet rs);
}
