package br.edu.ifsp.pep.util;

public class Utilities {
  public static boolean isSameMonthAndYear(java.sql.Date date1, java.sql.Date date2) {
    return date1.toLocalDate().getMonthValue() == date2.toLocalDate().getMonthValue()
        && date1.toLocalDate().getYear() == date2.toLocalDate().getYear();
  }
}
