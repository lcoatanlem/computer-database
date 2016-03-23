package com.excilys.computerdatabase.validation;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComputerValidator {
  private static final ComputerValidator INSTANCE = new ComputerValidator();

  public static ComputerValidator getInstance() {
    return INSTANCE;
  }

  /**
   * name Validator.
   * 
   * @return true iff name is valid
   */
  public String nameIsValid(String nameReq) {
    // Testing the null or empty (white spaces doesn't count)
    if (nameReq == null || nameReq.trim().isEmpty()) {
      return "Name is required.";
    } else {
      // If not null, must 2 chars at least
      String pattern = "^(.(.+))$";
      Pattern cpattern = Pattern.compile(pattern);
      Matcher match = cpattern.matcher(nameReq.trim());
      if (!match.matches()) {
        return "At least two chars.";
      } else {
        return null;
      }
    }
  }

  /**
   * introduced Validator.
   * 
   * @return true iff introduced is valid
   */
  public String introducedIsValid(String introducedReq) {
    // Can be null or empty
    if (introducedReq == null || introducedReq.trim().isEmpty()) {
      return null;
    } else {
      // Else must be a parseable local date string
      String pattern = "^(\\d{4})-(\\d{2})-(\\d{2})$";
      Pattern cpattern = Pattern.compile(pattern);
      Matcher match = cpattern.matcher(introducedReq.trim());
      if (match.matches()) {
        String[] results = introducedReq.trim().split("-");
        // Testing the validity of months and days (not total)
        if (Integer.parseInt(results[1]) > 12 || Integer.parseInt(results[2]) > 31) {
          return "Please enter a valid date.";
        }
        // Must be after 1970
        if (LocalDate.parse(introducedReq).isAfter(LocalDate.parse("1970-01-01"))) {
          return null;
        } else {
          return "Must be after 1970-01-02.";
        }
      } else {
        return "Invalid date format.";
      }
    }

  }

  /**
   * discontinued Validator.
   * 
   * @return true iff discontinued is valid
   */
  public String discontinuedIsValid(String discontinuedRes, String introducedRes) {
    // Can be null or empty
    if (discontinuedRes == null || discontinuedRes.trim().isEmpty()) {
      return null;
    } else {
      // Must be a parseable to local date string
      String pattern = "^(\\d{4})-(\\d{2})-(\\d{2})$";
      Pattern cpattern = Pattern.compile(pattern);
      Matcher match = cpattern.matcher(discontinuedRes.trim());
      if (match.matches()) {
        String[] results = discontinuedRes.trim().split("-");
        // Testing the validity of months and days (not total)
        if (Integer.parseInt(results[1]) > 12 || Integer.parseInt(results[2]) > 31) {
          return "Please enter a valid date.";
        }
        // Must be after 1970
        if (LocalDate.parse(discontinuedRes.trim()).isAfter(LocalDate.parse("1970-01-01"))) {
          // Introduced is valid, not null
          if (introducedIsValid(introducedRes) == null && (introducedRes != null)) {
            // Check if the discontinued date is after introduced
            if (LocalDate.parse(discontinuedRes.trim())
                .isAfter(LocalDate.parse(introducedRes.trim()))) {
              return null;
            } else {
              return "Discontinued date must be after introduced date.";
            }
          } else {
            return null;
          }
        } else {
          return "Must be after 1970-01-02.";
        }
      } else {
        return "Invalid date format.";
      }
    }
  }
}