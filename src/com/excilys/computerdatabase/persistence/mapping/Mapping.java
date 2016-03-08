package com.excilys.computerdatabase.persistence.mapping;

import java.sql.ResultSet;
import java.util.Set;

/**
 * This interface permits to abstract the functions mapAll which is both for a Computer and a Company
 * @author lcoatanlem
 * @param <T> Computer || Company
 */
public interface Mapping<T> {
	public Set<T> mapAll(ResultSet rs);
}
