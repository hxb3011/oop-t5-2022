package lthdt;

import java.util.regex.Pattern;

public abstract class Validator {
  private Validator() { }
  // REP: Regular Expression Pattern
  // private static final Pattern REP_ID = Pattern.compile("\\w{2}\\d{10}"); --or--
  private static final Pattern REP_ID = Pattern.compile("[A-Za-z]{2}[0-9]{10}");
  // private static final Pattern REP_ID = Pattern.compile("0\\d{9}"); --or--
  private static final Pattern REP_PHONE_NUMBER = Pattern.compile("0[0-9]{9}");
  public static boolean validateID(String s) { return REP_ID.matcher(s).matches(); }
  public static boolean validateName(String s) { return s != null && !s.isBlank(); }
  public static boolean validatePhoneNumber(String s) { return REP_PHONE_NUMBER.matcher(s).matches(); }
  public static boolean validateDate(String s) { return DateUtil.parseDate(s) != DateUtil.DATE_ERROR_DATE; }
}
