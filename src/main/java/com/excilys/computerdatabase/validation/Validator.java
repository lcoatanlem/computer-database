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
  public String numPageIsValid(String numPageReq, int totalEntries, int pageSize) {
    // Testing the null
    if (numPageReq == null) {
      return "Page number is null, set to its default value : 1.";
    } else {
      // If not null, must match only integers
      String pattern = "^\\d+$";
      Pattern cpattern = Pattern.compile(pattern);
      Matcher match = cpattern.matcher(numPageReq);
      if (!match.find()) {
        return "Page number must be an integer.";
      } else {
        // If matches the pattern, need to check if it isn't out of bounds
        int numPage = Integer.parseInt(numPageReq);
        int maxPages = totalEntries / pageSize + 1;
        if (numPage < 0 | numPage > maxPages) {
          return "Page number is out of bounds.";
        } else {
          return null;
        }
      }
    }
  }

  /**
   * pageSize Validator.
   * 
   * @return true iff pageSize is valid
   */
  public String pageSizeIsValid(String pageSizeReq) {
    // Testing the null
    if (pageSizeReq == null) {
      return "Page size is null, set to its default value : 10.";
    } else {
      // If not null, must match only integers
      String pattern = "^\\d+$";
      Pattern cpattern = Pattern.compile(pattern);
      Matcher match = cpattern.matcher(pageSizeReq);
      if (!match.find()) {
        return "Page size must be an integer.";
      } else {
        // If matches the pattern, need to check its value
        int pageSize = Integer.parseInt(pageSizeReq);
        if (!(pageSize == 10 | pageSize == 50 | pageSize == 100)) {
          return "Page size can be 10, 50, or 100.";
        } else {
          return null;
        }
      }
    }
  }

}
