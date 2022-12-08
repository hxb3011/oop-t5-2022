package lthdt;

// lớp hỗ trợ cho cấu trúc dữ liệu dạng trường bit (bit field)
// cấu trúc đũ liệu được cài đặt thông qua kiểu dữ liệu int
// phạm vi: 01/01/1800 đến 31/12/8390407
public abstract class DateUtil {
  private DateUtil() { }
  public static final int DATE_ERROR_DATE = -1;
  public static final int DATE_DAY_MASK = 0x0000001F;
  public static final int DATE_DAY_SHIFT = 0;
  public static final int DATE_MONTH_MASK = 0x000001E0;
  public static final int DATE_MONTH_SHIFT = 5;
  public static final int DATE_YEAR_MASK = 0xFFFFFE00;
  public static final int DATE_YEAR_SHIFT = 9;
  public static final int DATE_MAX_YEAR = 0x007FFFFF + 1800;
  public static final int DATE_MIN_YEAR = 1800;
  public static final char DATE_SEPARATOR = '/';
  public static int getDayOfDate(int date) { return ((date & DATE_DAY_MASK) >>> DATE_DAY_SHIFT) + 1; }
  public static int getMonthOfDate(int date) { return ((date & DATE_MONTH_MASK) >>> DATE_MONTH_SHIFT) + 1; }
  public static int getYearOfDate(int date) { return ((date & DATE_YEAR_MASK) >>> DATE_YEAR_SHIFT) + 1800; }
  public static boolean isLeapYear(int year) { return (year % 4) == 0 && ((year % 100) != 0 || (year % 400) == 0); }

  public static int getDate(int day, int month, int year) {
    if (year < DATE_MIN_YEAR || year > DATE_MAX_YEAR) return DATE_ERROR_DATE;
    if (month < 1 || month > 12) return DATE_ERROR_DATE;
    if (day < 1) return DATE_ERROR_DATE;
    if (month == 4 || month == 6 || month == 9 || month == 11) {
      if (day > 30)
        return DATE_ERROR_DATE;
    } else if (month == 2) {
      if (day > (isLeapYear(year) ? 29 : 28))
        return DATE_ERROR_DATE;
    } else {
      if (day > 31)
        return DATE_ERROR_DATE;
    }

    return (((day - 1) << DATE_DAY_SHIFT) & DATE_DAY_MASK)
        | (((month - 1) << DATE_MONTH_SHIFT) & DATE_MONTH_MASK)
        | (((year - 1800) << DATE_YEAR_SHIFT) & DATE_YEAR_MASK);
  }
  public static int getVerifiedDate(int date) {
    return getDate(getDayOfDate(date), getMonthOfDate(date), getYearOfDate(date));
  }
  public static String toDateString(int date) {
    date = getVerifiedDate(date);
    if (date == DATE_ERROR_DATE) return "INVALID";
    else
      return String.format("%02d" + DATE_SEPARATOR + "%02d" + DATE_SEPARATOR + "%d", getDayOfDate(date), getMonthOfDate(date), getYearOfDate(date));
  }
  public static int parseDate(String s) {
    try {
      String[] ss = s.split(String.valueOf(DATE_SEPARATOR));
      return getDate(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]), Integer.parseInt(ss[2]));
    } catch (Throwable e) {
      return DATE_ERROR_DATE;
    }
  }
}