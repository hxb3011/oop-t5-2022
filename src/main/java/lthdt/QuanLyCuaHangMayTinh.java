package lthdt;

import java.util.Scanner;

public abstract class QuanLyCuaHangMayTinh {
  public static final Scanner STANDARD_IN = new Scanner(System.in);
  public static final String EDIT_NOTE = "<* Bỏ qua những mục không thay đổi *>";
  public static boolean _debugMode = false;
  public static String _databasePath = "";
  public static final DanhSachSanPham _dsSanPham = new DanhSachSanPham();
  public static final DanhSachNhanVien _dsNhanVien = new DanhSachNhanVien();
  public static final DanhSachKhachHang _dsKhachHang = new DanhSachKhachHang();
  public static final DanhSachNhaCungCap _dsNhaCungCap = new DanhSachNhaCungCap();
  public static final DanhSachHoaDon _dsHoaDon = new DanhSachHoaDon();
  // TODO: FINISH
  public static final DanhSachPhieuNhapHang _dsPhieuNhapHang = new DanhSachPhieuNhapHang();

  private static void menuDanhSachPhieuNhapHang() {

  }
  private static void menuDanhSachHoaDon() {

  }
  private static void menuDanhSachNhaCungCap() {

  }
  private static void menuDanhSachKhachHang() {
  }
  private static void menuDanhSachNhanVien() {

  }
  private static void menuDanhSachSanPham() {

  }
  private static void menuQuanLy() {
    while (true) {
      System.out.println();
      System.out.println("+------------------------- QUẢN LÝ CỬA HÀNG MÁY TÍNH --------------------------+");
      System.out.println("|  1:             Danh sách sản phẩm                                           |");
      System.out.println("|  2:             Danh sách nhân viên                                          |");
      System.out.println("|  3:             Danh sách khách hàng                                         |");
      System.out.println("|  4:             Danh sách nhà cung cấp                                       |");
      System.out.println("|  5:             Danh sách hóa đơn                                            |");
      System.out.println("|  6:             Danh sách phiếu nhập hàng                                    |");
      System.out.println("|  *:             Thoát chương trình!                                          |");
      System.out.println("+------------------------------------------------------------------------------+");
      System.out.print("> ");
      String s = STANDARD_IN.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') {
          menuDanhSachSanPham();
        } else if (c == '2') {
          menuDanhSachNhanVien();
        } else if (c == '3') {
          menuDanhSachKhachHang();
        } else if (c == '4') {
          menuDanhSachNhaCungCap();
        } else if (c == '5') {
          menuDanhSachHoaDon();
        } else if (c == '6') {
          menuDanhSachPhieuNhapHang();
        } else break;
      } else break;
    }
  }
  public static void processingInternalThrowable(Throwable e) {
    if (_debugMode) {
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      e.printStackTrace();
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
  }
  private static void configure(String[] args) {
    int CF_DEBUG = 1;
    int CF_DB_PATH = 2;
    int configured = 0;
    for (int i = 0, n = args.length ; i < n ; i++) {
      String arg = args[i];
      if (arg.equals("-d") || arg.equals("--debug-mode")) {
        if ((configured & CF_DEBUG) == CF_DEBUG) {
          if (_debugMode) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Warning: Duplicate flag --debug-mode.");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
          }
        } else configured |= CF_DEBUG;
        _debugMode = true;
        continue;
      } else if (arg.startsWith("-p")) {
        arg = arg.substring(2);
      } else if (arg.startsWith("--database-path=")) {
        arg = arg.substring(16);
      } else {
        if (_debugMode) {
          System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
          System.out.println("Warning: Unknown argument " + arg + ".");
          System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        continue;
      }

      if ((configured & CF_DB_PATH) == CF_DB_PATH) {
        if (_debugMode) {
          System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
          System.out.println("Warning: Duplicate flag --database-path.");
          System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
      } else configured |= CF_DB_PATH;

      char c = arg.charAt(0);
      if (c != '"' && c != '\'') {
        _databasePath = arg;
        continue;
      }
      _databasePath = arg.substring(1);
      i++;
      for (String s = String.valueOf(c) ; i < n && !(arg = args[i]).endsWith(s) ; i++)
        _databasePath += ' ' + arg;
      if (i < n) _databasePath += ' ' + arg.substring(0, arg.length() - 1);
    }
  }
  private QuanLyCuaHangMayTinh() { }
  public static void main(String[] args) {
    configure(args);
    System.out.println(_debugMode);
    System.out.println(_databasePath);
    // menuQuanLy();
  }
}
