package com.excilys.computerdatabase.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
  private static final Validator INSTANCE = new Validator();

  public static Validator getInstance() {
    return INSTANCE;
  }

  /**
   * numPage Validator.
   * 
   * @return true iff numPage is valid
   */
  public boolean numPageIsValid(String numPageReq, int totalEntries, int pageSize) {
    // Testing the null
    if (numPageReq != null) {
      // If not null, must match only integers
      String pattern = "\\d+";
      Pattern cpattern = Pattern.compile(pattern);
      Matcher match = cpattern.matcher(numPageReq);
      if (match.find()) {
        // If matches the pattern, need to check if it isn't out of bounds
        int numPage = Integer.parseInt(numPageReq);
        int maxPages = totalEntries / pageSize + 1;
        if (numPage > 0 & numPage <= maxPages) {
          return true;
        }
      }
    }
    return false;
  }

  
  /**
   * pageSize Validator.
   * 
   * @return true iff pageSize is valid
   */
  public boolean pageSizeIsValid(String pageSizeReq) {
    // Testing the null
    if (pageSizeReq != null) {
      // If not null, must match only integers
      String pattern = "\\d+";
      Pattern cpattern = Pattern.compile(pattern);
      Matcher match = cpattern.matcher(pageSizeReq);
      if (match.find()) {
        int pageSize = Integer.parseInt(pageSizeReq);
        // If matches the pattern, need to check its value
        if (pageSize == 10 | pageSize == 50 | pageSize == 100) {
          return true;
        }
      }
    }
    return false;

  }
}
