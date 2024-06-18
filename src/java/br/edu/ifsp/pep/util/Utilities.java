package br.edu.ifsp.pep.util;

import java.time.LocalDate;

public class Utilities {
  public static boolean isSameMonthAndYear(java.sql.Date date1, java.sql.Date date2) {
    return date1.toLocalDate().getMonthValue() == date2.toLocalDate().getMonthValue()
        && date1.toLocalDate().getYear() == date2.toLocalDate().getYear();
  }

  public static boolean isAfter(LocalDate date1, LocalDate date2) {
    int year = date1.getYear();
    int month = date1.getMonthValue();
    int day = date1.getDayOfMonth();

    if (year > date2.getYear()) {
      return true;
    } else if (year == date2.getYear()) {
      if (month > date2.getMonthValue()) {
        return true;
      } else if (month == date2.getMonthValue()) {
        if (day >= date2.getDayOfMonth()) {
          return true;
        }
      }
    }

    return false;
  }

  public static boolean isBefore(LocalDate date1, LocalDate date2) {
    int year = date1.getYear();
    int month = date1.getMonthValue();
    int day = date1.getDayOfMonth();

    if (year < date2.getYear()) {
      return true;
    } else if (year == date2.getYear()) {
      if (month < date2.getMonthValue()) {
        return true;
      } else if (month == date2.getMonthValue()) {
        if (day < date2.getDayOfMonth()) {
          return true;
        }
      }
    }

    return false;
  }

  public static String dataFormatter(LocalDate date) {
    return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
  }
}
