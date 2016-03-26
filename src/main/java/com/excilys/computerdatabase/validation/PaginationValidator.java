package com.excilys.computerdatabase.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaginationValidator {
  private static final PaginationValidator INSTANCE = new PaginationValidator();

  public static PaginationValidator getInstance() {
    return INSTANCE;
  }

  /**
   * numPage Validator.
   * 
   * @return true iff numPage is valid
   */
  public String numPageIsValid(String numPageReq, int nbPages) {
    // Testing the null
    if (numPageReq == null) {
      return "Page number is null, set to its default value : 1.";
    } else {
      // If not null, must match only integers
      String pattern = "^(\\d+)$";
      Pattern cpattern = Pattern.compile(pattern);
      Matcher match = cpattern.matcher(numPageReq);
      if (!match.find()) {
        return "Page number must be an integer.";
      } else {
        // If matches the pattern, need to check if it isn't out of bounds
        int numPage = Integer.parseInt(numPageReq);
        if (numPage < 0 | numPage > nbPages) {
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
      String pattern = "^(\\d+)$";
      Pattern cpattern = Pattern.compile(pattern);
      Matcher match = cpattern.matcher(pageSizeReq);
      if (!match.find()) {
        return "Page size must be an integer.";
      } else {
        // If matches the pattern, need to check its value
        int pageSize = Integer.parseInt(pageSizeReq);
        // The only values we want for paging
        if (!(pageSize == 10 | pageSize == 50 | pageSize == 100)) {
          return "Page size can be 10, 50, or 100.";
        } else {
          return null;
        }
      }
    }
  }

}
