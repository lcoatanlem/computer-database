package com.excilys.computerdatabase.validation;

import com.excilys.computerdatabase.persistence.dto.ComputerDto;

import java.time.LocalDate;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComputerValidator {

  private static final String KEY_NAME = "name";
  private static final String KEY_INTRODUCED = "introduced";
  private static final String KEY_DISCONTINUED = "discontinued";

  /**
   * Method to validate a computer, returns a map of errors.
   * 
   * @param cpuDto
   *          the computer to validate
   * @param errors
   *          the map to fill
   * @return the map errors filled
   */
  public static Map<String, String> validate(ComputerDto cpuDto, Map<String, String> errors) {
    // Name Validation
    String nameVal = nameIsValid(cpuDto.getName());
    if (nameVal != null) {
      errors.put(KEY_NAME, nameVal);
    }
    // Introduced date Validation
    String introducedVal = introducedIsValid(cpuDto.getIntroduced());
    if (introducedVal != null) {
      errors.put(KEY_INTRODUCED, introducedVal);
    }
    // Discontinued date Validation
    String discontinuedVal = discontinuedIsValid(cpuDto.getDiscontinued(), cpuDto.getIntroduced());
    if (discontinuedVal != null) {
      errors.put(KEY_DISCONTINUED, discontinuedVal);
    }
    // Return the map of errors
    return errors;
  }

  /**
   * name Validator.
   * 
   * @return null if the name is valid, or the error
   */
  private static String nameIsValid(String nameReq) {
    // Name cannot be null
    if (nameReq == null || nameReq.isEmpty()) {
      return "Name is required.";
    } else {
      // Name must 2 chars at least
      String pattern = "^(.(.+))$";
      Pattern cpattern = Pattern.compile(pattern);
      Matcher match = cpattern.matcher(nameReq);
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
  private static String introducedIsValid(String introducedReq) {
    // Can be null or empty
    if (introducedReq == null || introducedReq.isEmpty()) {
      return null;
    } else {
      // Else must be a parseable local date string
      String pattern = "^(\\d{4})-(\\d{2})-(\\d{2})$";
      Pattern cpattern = Pattern.compile(pattern);
      Matcher match = cpattern.matcher(introducedReq);
      if (match.matches()) {
        String[] results = introducedReq.split("-");
        int year = Integer.parseInt(results[0]);
        int month = Integer.parseInt(results[1]);
        int day = Integer.parseInt(results[2]);
        // Testing the validity of the date
        // February of leap years
        if (year % 4 == 0 && month == 2 && day == 29) {
          return null;
        } else if (month == 2 && day > 28) {
          // February of other years
          return "Please enter a valid date.";
        } else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8
            || month == 10 || month == 12) && day > 31) {
          // Months with 31 days
          return "Please enter a valid date.";
        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
          // Months with 30 days
          return "Please enter a valid date.";
        } else if (month > 12) {
          // Month cannot be > 12
          return "Please enter a valid date.";
        } else if (LocalDate.parse(introducedReq).isAfter(LocalDate.parse("1970-01-01"))) {
          // Is after 1970-01-01
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
  private static String discontinuedIsValid(String discontinuedRes, String introducedRes) {
    // Can be null or empty
    if (discontinuedRes == null || discontinuedRes.isEmpty()) {
      return null;
    } else {
      // Must be a parseable to local date string
      String pattern = "^(\\d{4})-(\\d{2})-(\\d{2})$";
      Pattern cpattern = Pattern.compile(pattern);
      Matcher match = cpattern.matcher(discontinuedRes);
      if (match.matches()) {
        String[] results = discontinuedRes.split("-");
        int year = Integer.parseInt(results[0]);
        int month = Integer.parseInt(results[1]);
        int day = Integer.parseInt(results[2]);
        // Testing the validity of the date
        // February of leap years
        if (year % 4 == 0 && month == 2 && day == 29) {
          return null;
        } else if (month == 2 && day > 28) {
          // February of other years
          return "Please enter a valid date.";
        } else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8
            || month == 10 || month == 12) && day > 31) {
          // Months with 31 days
          return "Please enter a valid date.";
        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
          // Months with 30 days
          return "Please enter a valid date.";
        } else if (month > 12) {
          // Month cannot be > 12
          return "Please enter a valid date.";
        } else if (LocalDate.parse(discontinuedRes).isAfter(LocalDate.parse("1970-01-01"))) {
          // Is after 1970
          if (introducedIsValid(introducedRes) == null && (introducedRes != null)) {
            // Introduced is valid, not null
            if (LocalDate.parse(discontinuedRes).isAfter(LocalDate.parse(introducedRes))) {
              // The discontinued date is after introduced
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
