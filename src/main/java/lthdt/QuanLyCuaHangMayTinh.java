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
        if (c == '1') menuDanhSachSanPham();
        else if (c == '2') menuDanhSachNhanVien();
        else if (c == '3') menuDanhSachKhachHang();
        else if (c == '4') menuDanhSachNhaCungCap();
        else if (c == '5') menuDanhSachHoaDon();
        else if (c == '6') menuDanhSachPhieuNhapHang();
        else break;
      } else break;
    }
  }
  private static void loadDatabase() {
    _dsSanPham.readFile();
    _dsNhanVien.readFile();
    _dsKhachHang.readFile();
    _dsNhaCungCap.readFile();
    _dsHoaDon.readFile();
    _dsPhieuNhapHang.readFile();
  }
  private static void saveDatabase() {
    _dsSanPham.writeFile();
    _dsNhanVien.writeFile();
    _dsKhachHang.writeFile();
    _dsNhaCungCap.writeFile();
    _dsHoaDon.writeFile();
    _dsPhieuNhapHang.writeFile();
  }
  public static void processingInternalThrowable(Throwable e) {
    if (_debugMode) {
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      e.printStackTrace();
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
  }

  private static void preload() {
    DanhSachSanPham dssp = new DanhSachSanPham();
    DanhSachNhanVien dsnv = new DanhSachNhanVien();
    DanhSachKhachHang dskh = new DanhSachKhachHang();
    DanhSachNhaCungCap dsncc = new DanhSachNhaCungCap();
    DanhSachHoaDon dshd = new DanhSachHoaDon();
    DanhSachPhieuNhapHang dspnh = new DanhSachPhieuNhapHang();

    SanPham sp = null;
    dssp.add(sp);
    sp = null;
    dssp.add(sp);
    sp = null;
    dssp.add(sp);
    sp = null;
    dssp.add(sp);
    sp = null;
    dssp.add(sp);
    sp = null;
    dssp.add(sp);
    sp = null;
    dssp.add(sp);
    sp = null;
    dssp.add(sp);
    sp = null;
    dssp.add(sp);
    sp = null;
    dssp.add(sp);

    // TODO: other...

    dssp.writeFile();
    dsnv.writeFile();
    dskh.writeFile();
    dsncc.writeFile();
    dshd.writeFile();
    dspnh.writeFile();
  }

  private static void configure(String[] args) {
    int CF_DEBUG = 1;
    int CF_PRELOAD = 2;
    int CF_DB_PATH = 4;
    int configured = 0;
    for (int i = 0, n = args.length ; i < n ; i++) {
      String arg = args[i];
      if (arg.equals("-d") || arg.equals("--debug-mode")) {
        if ((configured & CF_DEBUG) == CF_DEBUG) {
          if (_debugMode) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Cảnh báo: Trùng lặp cờ --debug-mode.");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
          }
        } else {
          configured |= CF_DEBUG;
          _debugMode = true;
        }
        continue;
      } else if (arg.startsWith("-p") || arg.startsWith("--preload-mode")) {
        if ((configured & CF_PRELOAD) == CF_PRELOAD) {
          if (_debugMode) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Cảnh báo: Trùng lặp cờ --preload-mode.");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
          }
        } else {
          configured |= CF_PRELOAD;
          preload();
        }
        continue;
      } else if (arg.startsWith("-d")) {
        arg = arg.substring(2);
      } else if (arg.startsWith("--database-path=")) {
        arg = arg.substring(16);
      } else {
        if (_debugMode) {
          System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
          System.out.println("Cảnh báo: Không nhận dạng được tham số dòng lệnh: " + arg + ".");
          System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        continue;
      }

      if ((configured & CF_DB_PATH) == CF_DB_PATH) {
        if (_debugMode) {
          System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
          System.out.println("Cảnh báo: Trùng lặp cờ --database-path=.");
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
      else {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Cảnh báo: Có vấn đề với cờ --database-path=.");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      }
    }
    Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
      processingInternalThrowable(e);
      System.out.println("Có lỗi xảy ra! Đang thoát...");
      System.exit(-1);
    });
  }
  private QuanLyCuaHangMayTinh() { }
  public static void main(String[] args) {
    configure(args);
    loadDatabase();
    menuQuanLy();
    saveDatabase();
    System.exit(0);
  }
}
