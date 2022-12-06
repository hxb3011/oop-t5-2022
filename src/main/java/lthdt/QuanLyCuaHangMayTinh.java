package lthdt;

import java.util.Scanner;

public abstract class QuanLyCuaHangMayTinh {
  private QuanLyCuaHangMayTinh() { }
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
  private static void xuatTimKiemDanhSachKhachHang(KhachHang[] kh) {
    if (kh.length != 0) {
      if (kh.length == 1) {
        kh[0].output();
      } else {
        System.out.printf("%-10s %-30s %-11s %30s \n", "Mã", "Họ tên", "Số điện thoại", "Địa chỉ");
        for (KhachHang khi : kh)
          System.out.printf("%-10s %-30s %-11s %30s \n", khi.getMa(), khi.getHoTen(), khi.getSoDienThoai(), khi.getDiaChi());
      }
    }
  }
  private static void menuDanhSachKhachHang() {
    while (true) {
      System.out.println();
      System.out.println("+---------------------------- DANH SÁCH KHÁCH HÀNG ----------------------------+");
      System.out.println("|  1:             Sửa                                                          |");
      System.out.println("|  2:             Tìm kiếm theo mã                                             |");
      System.out.println("|  3:             Tìm kiếm theo họ                                             |");
      System.out.println("|  4:             Tìm kiếm theo tên                                            |");
      System.out.println("|  5:             Tìm kiếm theo họ và tên                                      |");
      System.out.println("|  6:             Tìm kiếm theo số điện thoại                                  |");
      System.out.println("|  7:             Tìm kiếm theo địa chỉ                                        |");
      System.out.println("|  8:             Thống kê số khách hàng theo địa chỉ                          |");
      System.out.println("|  9:             Xem                                                          |");
      System.out.println("|  *:             Trở về menu quản lý                                          |");
      System.out.println("+------------------------------------------------------------------------------+");
      System.out.print("> ");
      String s = STANDARD_IN.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') {
          while (true) {
            System.out.print("Nhập mã khách hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              KhachHang nv = _dsKhachHang.timTheoMa(s);
              if (nv != null) {
                nv.edit();
                break;
              }
            }
            System.out.println("Lỗi!");
          }
          saveDatabase();
        } else if (c == '2') {
          while (true) {
            System.out.print("Nhập mã khách hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              KhachHang nv = _dsKhachHang.timTheoMa(s);
              if (nv == null) {
                System.out.println("Không tìm thấy khách hàng có mã: " + s);
              } else {
                nv.output();
              }
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '3') {
          while (true) {
            System.out.print("Nhập họ khách hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              KhachHang[] nv = _dsKhachHang.timTheoHo(s);
              if (nv.length == 0) {
                System.out.println("Không tìm thấy khách hàng có họ chứa: " + s);
              } else xuatTimKiemDanhSachKhachHang(nv);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '4') {
          while (true) {
            System.out.print("Nhập tên khách hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              KhachHang[] nv = _dsKhachHang.timTheoTen(s);
              if (nv.length == 0) {
                System.out.println("Không tìm thấy khách hàng có tên chứa: " + s);
              } else xuatTimKiemDanhSachKhachHang(nv);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '5') {
          while (true) {
            System.out.print("Nhập họ và tên khách hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              KhachHang[] nv = _dsKhachHang.timTheoHoTen(s);
              if (nv.length == 0) {
                System.out.println("Không tìm thấy khách hàng có họ và tên chứa: " + s);
              } else xuatTimKiemDanhSachKhachHang(nv);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '6') {
          while (true) {
            System.out.print("Nhập số điện thoại của khách hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              KhachHang[] nv = _dsKhachHang.timTheoSoDienThoai(s);
              if (nv.length == 0) {
                System.out.println("Không tìm thấy khách hàng có số điện thoại chứa: " + s);
              } else xuatTimKiemDanhSachKhachHang(nv);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '7') {
          while (true) {
            System.out.print("Nhập địa chỉ của khách hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              KhachHang[] nv = _dsKhachHang.timTheoDiaChi(s);
              if (nv.length == 0) {
                System.out.println("Không tìm thấy khách hàng có địa chỉ chứa: " + s);
              } else xuatTimKiemDanhSachKhachHang(nv);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '8') {
          _dsKhachHang.thongKeSoKhachHangTheoDiaChi();
        } else if (c == '9') {
          _dsKhachHang.output();
        } else break;
      } else break;
    }
  }
  private static void xuatTimKiemDanhSachNhanVien(NhanVien[] nv) {
    if (nv.length != 0) {
      if (nv.length == 1) {
        nv[0].output();
      } else {
        System.out.printf("%-10s %-30s %-11s %-15s %-10s \n", "Mã", "Họ tên", "Số điện thoại", "Mức lương", "Năm vào làm");
        for (NhanVien nvi : nv)
          System.out.printf("%-10s %-30s %-11s %-15d %-10d \n", nvi.getMa(), nvi.getHoTen(), nvi.getSoDienThoai(), nvi.getMucLuong(), nvi.getNamVaoLam());
      }
    }
  }
  private static void menuTimKiemDanhSachNhanVien() {
    while (true) {
      System.out.println();
      System.out.println("+----------------------- DANH SÁCH NHÂN VIÊN > TÌM KIẾM -----------------------+");
      System.out.println("|  1:             Theo mã                                                      |");
      System.out.println("|  2:             Theo họ                                                      |");
      System.out.println("|  3:             Theo tên                                                     |");
      System.out.println("|  4:             Theo họ và tên                                                  |");
      System.out.println("|  5:             Theo số điện thoại                                           |");
      System.out.println("|  6:             Theo mức lương                                               |");
      System.out.println("|  7:             Theo khoảng lương                                            |");
      System.out.println("|  8:             Theo năm vào làm                                             |");
      System.out.println("|  9:             Theo khoảng năm vào làm                                      |");
      System.out.println("|  *:             Trở về menu danh sách                                        |");
      System.out.println("+------------------------------------------------------------------------------+");
      System.out.print("> ");
      String s = STANDARD_IN.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') {
          while (true) {
            System.out.print("Nhập mã nhân viên: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              NhanVien nv = _dsNhanVien.timTheoMa(s);
              if (nv == null) {
                System.out.println("Không tìm thấy nhân viên có mã: " + s);
              } else {
                nv.output();
              }
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '2') {
          while (true) {
            System.out.print("Nhập họ nhân viên: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              NhanVien[] nv = _dsNhanVien.timTheoHo(s);
              if (nv.length == 0) {
                System.out.println("Không tìm thấy nhân viên có họ chứa: " + s);
              } else xuatTimKiemDanhSachNhanVien(nv);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '3') {
          while (true) {
            System.out.print("Nhập tên nhân viên: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              NhanVien[] nv = _dsNhanVien.timTheoTen(s);
              if (nv.length == 0) {
                System.out.println("Không tìm thấy nhân viên có tên chứa: " + s);
              } else xuatTimKiemDanhSachNhanVien(nv);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '4') {
          while (true) {
            System.out.print("Nhập họ và tên nhân viên: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              NhanVien[] nv = _dsNhanVien.timTheoHoTen(s);
              if (nv.length == 0) {
                System.out.println("Không tìm thấy nhân viên có họ và tên chứa: " + s);
              } else xuatTimKiemDanhSachNhanVien(nv);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '5') {
          while (true) {
            System.out.print("Nhập số điện thoại của nhân viên: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              NhanVien[] nv = _dsNhanVien.timTheoSoDienThoai(s);
              if (nv.length == 0) {
                System.out.println("Không tìm thấy nhân viên có số điện thoại chứa: " + s);
              } else xuatTimKiemDanhSachNhanVien(nv);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '6') {
          while (true) {
            System.out.print("Nhập mức lương của nhân viên: ");
            int luong;
            try {
              luong = Integer.parseInt(STANDARD_IN.nextLine());
            } catch (Throwable e) {
              processingInternalThrowable(e);
              System.out.println("Lỗi!");
              continue;
            }
            NhanVien[] nv = _dsNhanVien.timTheoMucLuong(luong);
            if (nv.length == 0) {
              System.out.println("Không tìm thấy nhân viên có mức lương: " + luong);
            } else xuatTimKiemDanhSachNhanVien(nv);
            break;
          }
        } else if (c == '7') {
          while (true) {
            System.out.print("Nhập giới hạn dưới khoảng lương của nhân viên: ");
            int duoi;
            try {
              duoi = Integer.parseInt(STANDARD_IN.nextLine());
            } catch (Throwable e) {
              processingInternalThrowable(e);
              System.out.println("Lỗi!");
              continue;
            }
            if (duoi < 0) {
              System.out.println("Lỗi!");
              continue;
            }
            System.out.print("Nhập giới hạn trên khoảng lương của nhân viên: ");
            int tren;
            try {
              tren = Integer.parseInt(STANDARD_IN.nextLine());
            } catch (Throwable e) {
              processingInternalThrowable(e);
              System.out.println("Lỗi!");
              continue;
            }
            if (tren < 0) {
              System.out.println("Lỗi!");
              continue;
            }
            NhanVien[] nv = _dsNhanVien.timTheoKhoangLuong(duoi, tren);
            if (nv.length == 0) {
              System.out.println("Không tìm thấy nhân viên có khoảng lương: " + duoi + "-" + tren);
            } else xuatTimKiemDanhSachNhanVien(nv);
            break;
          }
        } else if (c == '8') {
          while (true) {
            System.out.print("Nhập năm vào làm của nhân viên: ");
            int nam;
            try {
              nam = Integer.parseInt(STANDARD_IN.nextLine());
            } catch (Throwable e) {
              processingInternalThrowable(e);
              System.out.println("Lỗi!");
              continue;
            }
            NhanVien[] nv = _dsNhanVien.timTheoNamVaoLam(nam);
            if (nv.length == 0) {
              System.out.println("Không tìm thấy nhân viên có năm vào làm: " + nam);
            } else xuatTimKiemDanhSachNhanVien(nv);
            break;
          }
        } else if (c == '9') {
          while (true) {
            System.out.print("Nhập giới hạn dưới khoảng năm vào làm của nhân viên: ");
            int duoi;
            try {
              duoi = Integer.parseInt(STANDARD_IN.nextLine());
            } catch (Throwable e) {
              processingInternalThrowable(e);
              System.out.println("Lỗi!");
              continue;
            }
            if (duoi < 0) {
              System.out.println("Lỗi!");
              continue;
            }
            System.out.print("Nhập giới hạn trên khoảng năm vào làm của nhân viên: ");
            int tren;
            try {
              tren = Integer.parseInt(STANDARD_IN.nextLine());
            } catch (Throwable e) {
              processingInternalThrowable(e);
              System.out.println("Lỗi!");
              continue;
            }
            if (tren < 0) {
              System.out.println("Lỗi!");
              continue;
            }
            NhanVien[] nv = _dsNhanVien.timTheoKhoangLuong(duoi, tren);
            if (nv.length == 0) {
              System.out.println("Không tìm thấy nhân viên có khoảng năm vào làm: " + duoi + "-" + tren);
            } else xuatTimKiemDanhSachNhanVien(nv);
            break;
          }
        } else break;
      } else break;
    }
  }
  private static void menuThongKeDanhSachNhanVien() {
    while (true) {
      System.out.println();
      System.out.println("+----------------------- DANH SÁCH NHÂN VIÊN > THỐNG KÊ -----------------------+");
      System.out.println("|  1:             Số nhân viên theo mức lương                                  |");
      System.out.println("|  2:             Số nhân viên theo năm vào làm                                |");
      System.out.println("|  *:             Trở về menu danh sách                                        |");
      System.out.println("+------------------------------------------------------------------------------+");
      System.out.print("> ");
      String s = STANDARD_IN.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') {
          _dsNhanVien.thongKeSoNhanVienTheoMucLuong();
        } else if (c == '2') {
          _dsNhanVien.thongKeSoNhanVienTheoNamVaoLam();
        } else break;
      } else break;
    }
  }
  private static void menuDanhSachNhanVien() {
    while (true) {
      System.out.println();
      System.out.println("+---------------------------- DANH SÁCH NHÂN VIÊN -----------------------------+");
      System.out.println("|  1:             Thêm                                                         |");
      System.out.println("|  2:             Sửa                                                          |");
      System.out.println("|  3:             Xoá                                                          |");
      System.out.println("|  4:             Tìm kiếm                                                     |");
      System.out.println("|  5:             Thống kê                                                     |");
      if (_dsNhanVien.soLuong() > 0)
        System.out.println("|  6:             Xem                                                          |");
      else
        System.out.println("|  6:             Nhập nhiều                                                   |");
      System.out.println("|  *:             Trở về menu quản lý                                          |");
      System.out.println("+------------------------------------------------------------------------------+");
      System.out.print("> ");
      String s = STANDARD_IN.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') {
          while (true) {
            System.out.print("Nhập mã nhân viên: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              if (!_dsNhanVien.daCo(s)) {
                NhanVien nv = new NhanVien();
                nv.setMa(s);
                nv.input();
                _dsNhanVien.add(nv);
                break;
              }
            }
            System.out.println("Lỗi!");
          }
          saveDatabase();
        } else if (c == '2') {
          while (true) {
            System.out.print("Nhập mã nhân viên: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              NhanVien nv = _dsNhanVien.timTheoMa(s);
              if (nv != null) {
                nv.edit();
                break;
              }
            }
            System.out.println("Lỗi!");
          }
          saveDatabase();
        } else if (c == '3') {
          while (true) {
            System.out.print("Nhập mã nhân viên: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              if (_dsNhanVien.xoaTheoMa(s)) break;
            }
            System.out.println("Lỗi!");
          }
          saveDatabase();
        } else if (c == '4') {
          menuTimKiemDanhSachNhanVien();
        } else if (c == '5') {
          menuThongKeDanhSachNhanVien();
        } else if (c == '6') {
          if (_dsNhanVien.soLuong() > 0) {
            _dsNhanVien.output();
          } else {
            int n;
            while (true) {
              try {
                n = Integer.parseInt(STANDARD_IN.nextLine());
              } catch (Throwable e) {
                processingInternalThrowable(e);
                System.out.println("Lỗi!");
                continue;
              }
              if (n < 0) {
                System.out.println("Lỗi!");
                continue;
              }
              _dsNhanVien.input(n);
              break;
            }
            saveDatabase();
          }
        } else break;
      } else break;
    }
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
        saveDatabase();
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
  public static void main(String[] args) {
    configure(args);
    loadDatabase();
    menuQuanLy();
    saveDatabase();
    System.exit(0);
  }
}
