package lthdt;

import java.io.File;
import java.util.Scanner;

public abstract class QuanLyCuaHangMayTinh {
  private QuanLyCuaHangMayTinh() { }
  public static final Scanner STANDARD_IN = new Scanner(System.in);
  public static final String EDIT_NOTE = "<* Bỏ qua những mục không thay đổi *>";
  public static boolean _debugMode = false;
  public static String _databasePath = "database" + File.separator;
  public static final DanhSachSanPham _dsSanPham = new DanhSachSanPham();
  public static final DanhSachNhanVien _dsNhanVien = new DanhSachNhanVien();
  public static final DanhSachKhachHang _dsKhachHang = new DanhSachKhachHang();
  public static final DanhSachNhaCungCap _dsNhaCungCap = new DanhSachNhaCungCap();
  public static final DanhSachHoaDon _dsHoaDon = new DanhSachHoaDon();
  public static final DanhSachPhieuNhapHang _dsPhieuNhapHang = new DanhSachPhieuNhapHang();
  private static void xuatTimKiemDanhSachPhieuNhapHang(PhieuNhapHang[] pnh) {
    if (pnh.length != 0) {
      if (pnh.length == 1) {
        pnh[0].output();
      } else {
        System.out.printf("%-10s %-30s %-30s %-20s\n", "Mã", "Tên nhân viên", "Tên nhà cung cấp", "Tổng tiền");
        for (PhieuNhapHang hdi : pnh)
          System.out.printf("%-10s %-30s %-30s %-20s\n", hdi.getMa(), hdi.getNhanVien().getHoTen(), hdi.getNhaCungCap().getTen(), hdi.getTongTien());

        outer:
        while (true) {
          System.out.print("\nNhập mã phiếu nhập hàng để xem chi tiết (Bỏ qua để thoát): ");
          String s = QuanLyCuaHangMayTinh.STANDARD_IN.nextLine();
          if (s.isEmpty()) break;
          if (!Validator.validateID(s)) {
            System.out.println("Lỗi!");
            continue;
          }
          for (PhieuNhapHang hdi : pnh) {
            if (hdi.getMa().equals(s)) {
              hdi.output();
              continue outer;
            }
          }
          System.out.println("Lỗi!");
        }
      }
    }
  }
  private static void menuTimKiemDanhSachPhieuNhapHang() {
    while (true) {
      System.out.println();
      System.out.println("+-------------------- DANH SÁCH PHIẾU NHẬP HÀNG > TÌM KIẾM --------------------+");
      System.out.println("|  1:             Theo mã phiếu nhập hàng                                      |");
      System.out.println("|  2:             Theo ngày lập                                                |");
      System.out.println("|  3:             Theo mã nhà cung cấp                                         |");
      System.out.println("|  4:             Theo mã nhân viên                                            |");
      System.out.println("|  5:             Theo mã sản phẩm                                             |");
      System.out.println("|  6:             Theo tổng tiền                                               |");
      System.out.println("|  7:             Theo khoảng tổng tiền                                        |");
      System.out.println("|  *:             Trở về menu danh sách                                        |");
      System.out.println("+------------------------------------------------------------------------------+");
      System.out.print("> ");
      String s = STANDARD_IN.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') {
          while (true) {
            System.out.print("Nhập mã phiếu nhập hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              PhieuNhapHang hd = _dsPhieuNhapHang.timTheoMa(s);
              if (hd == null) {
                System.out.println("Không tìm thấy phiếu nhập hàng có mã: " + s);
              } else {
                hd.output();
              }
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '2') {
          while (true) {
            System.out.print("Nhập ngày lập phiếu nhập hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateDate(s)) {
              PhieuNhapHang[] hd = _dsPhieuNhapHang.timTheoNgayLap(s);
              if (hd.length == 0) {
                System.out.println("Không tìm thấy phiếu nhập hàng có ngày lập: " + s);
              } else xuatTimKiemDanhSachPhieuNhapHang(hd);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '3') {
          while (true) {
            System.out.print("Nhập mã nhà cung cấp: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              PhieuNhapHang[] hd = _dsPhieuNhapHang.timTheoMaNhaCungCap(s);
              if (hd.length == 0) {
                System.out.println("Không tìm thấy phiếu nhập hàng có mã nhà cung cấp: " + s);
              } else xuatTimKiemDanhSachPhieuNhapHang(hd);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '4') {
          while (true) {
            System.out.print("Nhập mã nhân viên: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              PhieuNhapHang[] hd = _dsPhieuNhapHang.timTheoMaNhanVien(s);
              if (hd.length == 0) {
                System.out.println("Không tìm thấy phiếu nhập hàng có mã nhân viên: " + s);
              } else xuatTimKiemDanhSachPhieuNhapHang(hd);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '5') {
          while (true) {
            System.out.print("Nhập mã sản phẩm: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              PhieuNhapHang[] hd = _dsPhieuNhapHang.timTheoMaSanPham(s);
              if (hd.length == 0) {
                System.out.println("Không tìm thấy phiếu nhập hàng có mã sản phẩm: " + s);
              } else xuatTimKiemDanhSachPhieuNhapHang(hd);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '6') {
          while (true) {
            System.out.print("Nhập tổng tiền của phiếu nhập hàng: ");
            int tt;
            try {
              tt = Integer.parseInt(STANDARD_IN.nextLine());
            } catch (Throwable e) {
              processingInternalThrowable(e);
              System.out.println("Lỗi!");
              continue;
            }
            PhieuNhapHang[] hd = _dsPhieuNhapHang.timTheoTongTien(tt);
            if (hd.length == 0) {
              System.out.println("Không tìm thấy phiếu nhập hàng có tổng tiền: " + tt);
            } else xuatTimKiemDanhSachPhieuNhapHang(hd);
            break;
          }
        } else if (c == '7') {
          while (true) {
            System.out.print("Nhập giới hạn dưới khoảng tổng tiền của phiếu nhập hàng: ");
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
            System.out.print("Nhập giới hạn trên khoảng tổng tiền của phiếu nhập hàng: ");
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
            PhieuNhapHang[] hd = _dsPhieuNhapHang.timTheoKhoangTongTien(duoi, tren);
            if (hd.length == 0) {
              System.out.println("Không tìm thấy phiếu nhập hàng có khoảng tổng tiền: " + duoi + "-" + tren);
            } else xuatTimKiemDanhSachPhieuNhapHang(hd);
            break;
          }
        } else break;
      } else break;
    }
  }
  private static void menuThongKeDanhSachPhieuNhapHang() {
    while (true) {
      System.out.println();
      System.out.println("+-------------------- DANH SÁCH PHIẾU NHẬP HÀNG > THỐNG KÊ --------------------+");
      System.out.println("|  1:             Tổng tiền bán theo khách hàng                                |");
      System.out.println("|  2:             Tổng tiền bán theo nhân viên                                 |");
      System.out.println("|  3:             Tổng tiền bán theo sản phẩm                                  |");
      System.out.println("|  *:             Trở về menu danh sách                                        |");
      System.out.println("+------------------------------------------------------------------------------+");
      System.out.print("> ");
      String s = STANDARD_IN.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') _dsPhieuNhapHang.thongKeTongTienNhapTheoNhaCungCap();
        else if (c == '2') _dsPhieuNhapHang.thongKeTongTienNhapTheoNhanVien();
        else if (c == '3') _dsPhieuNhapHang.thongKeTongTienNhapTheoSanPham();
        else break;
      } else break;
    }
  }
  private static void menuDanhSachPhieuNhapHang() {
    while (true) {
      System.out.println();
      System.out.println("+------------------------- DANH SÁCH PHIẾU NHẬP HÀNG --------------------------+");
      System.out.println("|  1:             Thêm                                                         |");
      System.out.println("|  2:             Sửa                                                          |");
      System.out.println("|  3:             Xoá                                                          |");
      System.out.println("|  4:             Tìm kiếm                                                     |");
      System.out.println("|  5:             Thống kê                                                     |");
      if (_dsPhieuNhapHang.soLuong() > 0)
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
            System.out.print("Nhập mã phiếu nhập hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              if (!_dsHoaDon.daCo(s)) {
                PhieuNhapHang pnh = new PhieuNhapHang();
                pnh.setMa(s);
                pnh.input();
                _dsPhieuNhapHang.add(pnh);
                break;
              }
            }
            System.out.println("Lỗi!");
          }
          saveDatabase();
        } else if (c == '2') {
          while (true) {
            System.out.print("Nhập mã phiếu nhập hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              PhieuNhapHang pnh = _dsPhieuNhapHang.timTheoMa(s);
              if (pnh != null) {
                pnh.edit();
                break;
              }
            }
            System.out.println("Lỗi!");
          }
          saveDatabase();
        } else if (c == '3') {
          while (true) {
            System.out.print("Nhập mã phiếu nhập hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              if (_dsPhieuNhapHang.xoaTheoMa(s)) break;
            }
            System.out.println("Lỗi!");
          }
          saveDatabase();
        } else if (c == '4') {
          menuTimKiemDanhSachPhieuNhapHang();
        } else if (c == '5') {
          menuThongKeDanhSachPhieuNhapHang();
        } else if (c == '6') {
          if (_dsPhieuNhapHang.soLuong() > 0) {
            _dsPhieuNhapHang.output();
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
              _dsPhieuNhapHang.input(n);
              break;
            }
            saveDatabase();
          }
        } else break;
      } else break;
    }
  }
  private static void xuatTimKiemDanhSachHoaDon(HoaDon[] hd) {
    if (hd.length != 0) {
      if (hd.length == 1) {
        hd[0].output();
      } else {
        System.out.printf("%-10s %-30s %-30s %-20s\n", "Mã", "Tên nhân viên", "Tên khách hàng", "Tổng tiền");
        for (HoaDon hdi : hd)
          System.out.printf("%-10s %-30s %-30s %-20s\n", hdi.getMa(), hdi.getNhanVien().getHoTen(), hdi.getKhachHang().getHoTen(), hdi.getTongTien());

        outer:
        while (true) {
          System.out.print("\nNhập mã hoá đơn để xem chi tiết (Bỏ qua để thoát): ");
          String s = QuanLyCuaHangMayTinh.STANDARD_IN.nextLine();
          if (s.isEmpty()) break;
          if (!Validator.validateID(s)) {
            System.out.println("Lỗi!");
            continue;
          }
          for (HoaDon hdi : hd) {
            if (hdi.getMa().equals(s)) {
              hdi.output();
              continue outer;
            }
          }
          System.out.println("Lỗi!");
        }
      }
    }
  }
  private static void menuTimKiemDanhSachHoaDon() {
    while (true) {
      System.out.println();
      System.out.println("+------------------------ DANH SÁCH HOÁ ĐƠN > TÌM KIẾM ------------------------+");
      System.out.println("|  1:             Theo mã hoá đơn                                              |");
      System.out.println("|  2:             Theo ngày lập                                                |");
      System.out.println("|  3:             Theo mã khách hàng                                           |");
      System.out.println("|  4:             Theo mã nhân viên                                            |");
      System.out.println("|  5:             Theo mã sản phẩm                                             |");
      System.out.println("|  6:             Theo tổng tiền                                               |");
      System.out.println("|  7:             Theo khoảng tổng tiền                                        |");
      System.out.println("|  *:             Trở về menu danh sách                                        |");
      System.out.println("+------------------------------------------------------------------------------+");
      System.out.print("> ");
      String s = STANDARD_IN.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') {
          while (true) {
            System.out.print("Nhập mã hoá đơn: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              HoaDon hd = _dsHoaDon.timTheoMa(s);
              if (hd == null) {
                System.out.println("Không tìm thấy hoá đơn có mã: " + s);
              } else {
                hd.output();
              }
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '2') {
          while (true) {
            System.out.print("Nhập ngày lập hoá đơn: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateDate(s)) {
              HoaDon[] hd = _dsHoaDon.timTheoNgayLap(s);
              if (hd.length == 0) {
                System.out.println("Không tìm thấy hoá đơn có ngày lập: " + s);
              } else xuatTimKiemDanhSachHoaDon(hd);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '3') {
          while (true) {
            System.out.print("Nhập mã khách hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              HoaDon[] hd = _dsHoaDon.timTheoMaKhachHang(s);
              if (hd.length == 0) {
                System.out.println("Không tìm thấy hoá đơn có mã khách hàng: " + s);
              } else xuatTimKiemDanhSachHoaDon(hd);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '4') {
          while (true) {
            System.out.print("Nhập mã nhân viên: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              HoaDon[] hd = _dsHoaDon.timTheoMaNhanVien(s);
              if (hd.length == 0) {
                System.out.println("Không tìm thấy hoá đơn có mã nhân viên: " + s);
              } else xuatTimKiemDanhSachHoaDon(hd);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '5') {
          while (true) {
            System.out.print("Nhập mã sản phẩm: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              HoaDon[] hd = _dsHoaDon.timTheoMaSanPham(s);
              if (hd.length == 0) {
                System.out.println("Không tìm thấy hoá đơn có mã sản phẩm: " + s);
              } else xuatTimKiemDanhSachHoaDon(hd);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '6') {
          while (true) {
            System.out.print("Nhập tổng tiền của hoá đơn: ");
            int tt;
            try {
              tt = Integer.parseInt(STANDARD_IN.nextLine());
            } catch (Throwable e) {
              processingInternalThrowable(e);
              System.out.println("Lỗi!");
              continue;
            }
            HoaDon[] hd = _dsHoaDon.timTheoTongTien(tt);
            if (hd.length == 0) {
              System.out.println("Không tìm thấy hoá đơn có tổng tiền: " + tt);
            } else xuatTimKiemDanhSachHoaDon(hd);
            break;
          }
        } else if (c == '7') {
          while (true) {
            System.out.print("Nhập giới hạn dưới khoảng tổng tiền của hoá đơn: ");
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
            System.out.print("Nhập giới hạn trên khoảng tổng tiền của hoá đơn: ");
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
            HoaDon[] hd = _dsHoaDon.timTheoKhoangTongTien(duoi, tren);
            if (hd.length == 0) {
              System.out.println("Không tìm thấy hoá đơn có khoảng tổng tiền: " + duoi + "-" + tren);
            } else xuatTimKiemDanhSachHoaDon(hd);
            break;
          }
        } else break;
      } else break;
    }
  }
  private static void menuThongKeDanhSachHoaDon() {
    while (true) {
      System.out.println();
      System.out.println("+------------------------ DANH SÁCH HOÁ ĐƠN > THỐNG KÊ ------------------------+");
      System.out.println("|  1:             Tổng tiền bán theo khách hàng                                |");
      System.out.println("|  2:             Tổng tiền bán theo nhân viên                                 |");
      System.out.println("|  3:             Tổng tiền bán theo sản phẩm                                  |");
      System.out.println("|  *:             Trở về menu danh sách                                        |");
      System.out.println("+------------------------------------------------------------------------------+");
      System.out.print("> ");
      String s = STANDARD_IN.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') _dsHoaDon.thongKeTongTienBanTheoKhachHang();
        else if (c == '2') _dsHoaDon.thongKeTongTienBanTheoNhanVien();
        else if (c == '3') _dsHoaDon.thongKeTongTienBanTheoSanPham();
        else break;
      } else break;
    }
  }
  private static void menuDanhSachHoaDon() {
    while (true) {
      System.out.println();
      System.out.println("+----------------------------- DANH SÁCH HOÁ ĐƠN ------------------------------+");
      System.out.println("|  1:             Thêm                                                         |");
      System.out.println("|  2:             Sửa                                                          |");
      System.out.println("|  3:             Xoá                                                          |");
      System.out.println("|  4:             Tìm kiếm                                                     |");
      System.out.println("|  5:             Thống kê                                                     |");
      if (_dsHoaDon.soLuong() > 0)
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
            System.out.print("Nhập mã hoá đơn: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              if (!_dsHoaDon.daCo(s)) {
                HoaDon hd = new HoaDon();
                hd.setMa(s);
                hd.input();
                _dsHoaDon.add(hd);
                break;
              }
            }
            System.out.println("Lỗi!");
          }
          saveDatabase();
        } else if (c == '2') {
          while (true) {
            System.out.print("Nhập mã hoá đơn: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              HoaDon hd = _dsHoaDon.timTheoMa(s);
              if (hd != null) {
                hd.edit();
                break;
              }
            }
            System.out.println("Lỗi!");
          }
          saveDatabase();
        } else if (c == '3') {
          while (true) {
            System.out.print("Nhập mã hóa đơn: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              if (_dsHoaDon.xoaTheoMa(s)) break;
            }
            System.out.println("Lỗi!");
          }
          saveDatabase();
        } else if (c == '4') {
          menuTimKiemDanhSachHoaDon();
        } else if (c == '5') {
          menuThongKeDanhSachHoaDon();
        } else if (c == '6') {
          if (_dsHoaDon.soLuong() > 0) {
            _dsHoaDon.output();
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
              _dsHoaDon.input(n);
              break;
            }
            saveDatabase();
          }
        } else break;
      } else break;
    }
  }
  private static void xuatTimKiemDanhSachNhaCungCap(NhaCungCap[] ncc) {
    if (ncc.length != 0) {
      if (ncc.length == 1) {
        ncc[0].output();
      } else {
        System.out.printf("%-10s %-30s \n", "Mã", "Tên");
        for (NhaCungCap ncci : ncc)
          System.out.printf("%-10s %-30s \n", ncci.getMa(), ncci.getTen());
      }
    }
  }
  private static void menuDanhSachNhaCungCap() {
    while (true) {
      System.out.println();
      System.out.println("+--------------------------- DANH SÁCH NHÀ CUNG CẤP ---------------------------+");
      System.out.println("|  1:             Sửa                                                          |");
      System.out.println("|  2:             Tìm kiếm theo mã                                             |");
      System.out.println("|  3:             Tìm kiếm theo tên                                            |");
      System.out.println("|  4:             Xem                                                          |");
      System.out.println("|  *:             Trở về menu quản lý                                          |");
      System.out.println("+------------------------------------------------------------------------------+");
      System.out.print("> ");
      String s = STANDARD_IN.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') {
          while (true) {
            System.out.print("Nhập mã nhà cung cấp: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              NhaCungCap ncc = _dsNhaCungCap.timTheoMa(s);
              if (ncc != null) {
                ncc.edit();
                break;
              }
            }
            System.out.println("Lỗi!");
          }
          saveDatabase();
        } else if (c == '2') {
          while (true) {
            System.out.print("Nhập mã nhà cung cấp: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              NhaCungCap ncc = _dsNhaCungCap.timTheoMa(s);
              if (ncc == null) {
                System.out.println("Không tìm thấy nhà cung cấp có mã: " + s);
              } else {
                ncc.output();
              }
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '3') {
          while (true) {
            System.out.print("Nhập tên nhà cung cấp: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              NhaCungCap[] ncc = _dsNhaCungCap.timTheoTen(s);
              if (ncc.length == 0) {
                System.out.println("Không tìm thấy nhà cung cấp có tên chứa: " + s);
              } else xuatTimKiemDanhSachNhaCungCap(ncc);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '4') {
          _dsNhaCungCap.output();
        } else break;
      } else break;
    }
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
              KhachHang kh = _dsKhachHang.timTheoMa(s);
              if (kh != null) {
                kh.edit();
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
              KhachHang kh = _dsKhachHang.timTheoMa(s);
              if (kh == null) {
                System.out.println("Không tìm thấy khách hàng có mã: " + s);
              } else {
                kh.output();
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
              KhachHang[] kh = _dsKhachHang.timTheoHo(s);
              if (kh.length == 0) {
                System.out.println("Không tìm thấy khách hàng có họ chứa: " + s);
              } else xuatTimKiemDanhSachKhachHang(kh);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '4') {
          while (true) {
            System.out.print("Nhập tên khách hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              KhachHang[] kh = _dsKhachHang.timTheoTen(s);
              if (kh.length == 0) {
                System.out.println("Không tìm thấy khách hàng có tên chứa: " + s);
              } else xuatTimKiemDanhSachKhachHang(kh);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '5') {
          while (true) {
            System.out.print("Nhập họ và tên khách hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              KhachHang[] kh = _dsKhachHang.timTheoHoTen(s);
              if (kh.length == 0) {
                System.out.println("Không tìm thấy khách hàng có họ và tên chứa: " + s);
              } else xuatTimKiemDanhSachKhachHang(kh);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '6') {
          while (true) {
            System.out.print("Nhập số điện thoại của khách hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              KhachHang[] kh = _dsKhachHang.timTheoSoDienThoai(s);
              if (kh.length == 0) {
                System.out.println("Không tìm thấy khách hàng có số điện thoại chứa: " + s);
              } else xuatTimKiemDanhSachKhachHang(kh);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '7') {
          while (true) {
            System.out.print("Nhập địa chỉ của khách hàng: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              KhachHang[] kh = _dsKhachHang.timTheoDiaChi(s);
              if (kh.length == 0) {
                System.out.println("Không tìm thấy khách hàng có địa chỉ chứa: " + s);
              } else xuatTimKiemDanhSachKhachHang(kh);
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
      System.out.println("|  4:             Theo họ và tên                                               |");
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
            NhanVien[] nv = _dsNhanVien.timTheoKhoangNamVaoLam(duoi, tren);
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
  private static void xuatTimKiemDanhSachSanPham(SanPham[] sp) {
    if (sp.length != 0) {
      if (sp.length == 1) {
        sp[0].output();
      } else {
        System.out.printf("%-10s %-30s %-20s %-20s %-20s \n", "Mã", "Tên", "Nhà sản xuất", "Đơn giá", "Số lượng tồn");
        for (SanPham spi : sp)
          System.out.printf("%-10s %-30sps %-20s %-20s %-20s \n", spi.getMa(), spi.getTen(), spi.getTenNhaSanXuat(), spi.getDonGia(), spi.getSoLuong());

        outer:
        while (true) {
          System.out.print("\nNhập mã sản phẩm để xem chi tiết (Bỏ qua để thoát): ");
          String s = QuanLyCuaHangMayTinh.STANDARD_IN.nextLine();
          if (s.isEmpty()) break;
          if (!Validator.validateID(s)) {
            System.out.println("Lỗi!");
            continue;
          }

          for (SanPham spi : sp) {
            if (spi.getMa().equals(s)) {
              if (spi instanceof MayTinhBan) {
                System.out.println("Máy tính để bàn: ");
              } else if (spi instanceof Laptop) {
                System.out.println("Laptop: ");
              } else {
                System.out.println("Lỗi!");
                continue;
              }
              spi.output();
              continue outer;
            }
          }
          System.out.println("Lỗi!");
        }
      }
    }
  }
  private static void menuTimKiemDanhSachSanPham() {
    while (true) {
      System.out.println();
      System.out.println("+----------------------- DANH SÁCH SẢN PHẨM > TÌM KIẾM ------------------------+");
      System.out.println("|  1:             Theo đơn giá                                                 |");
      System.out.println("|  2:             Theo khoảng đơn giá                                          |");
      System.out.println("|  3:             Theo số lượng tồn                                            |");
      System.out.println("|  4:             Theo khoảng số lượng tồn                                     |");
      System.out.println("|  5:             Theo phụ kiện tặng kèm                                       |");
      System.out.println("|  6:             Theo chất lượng loa                                          |");
      System.out.println("|  7:             Theo màn hình cảm ứng                                        |");
      System.out.println("|  8:             Theo LED bàn phím                                            |");
      System.out.println("|  *:             Trở về menu danh sách                                        |");
      System.out.println("+------------------------------------------------------------------------------+");
      System.out.print("> ");
      String s = STANDARD_IN.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') {
          while (true) {
            System.out.print("Nhập đơn giá của sản phẩm: ");
            int dg;
            try {
              dg = Integer.parseInt(STANDARD_IN.nextLine());
            } catch (Throwable e) {
              processingInternalThrowable(e);
              System.out.println("Lỗi!");
              continue;
            }
            SanPham[] sp = _dsSanPham.timTheoDonGia(dg);
            if (sp.length == 0) {
              System.out.println("Không tìm thấy sản phẩm có đơn giá: " + dg);
            } else xuatTimKiemDanhSachSanPham(sp);
            break;
          }
        } else if (c == '2') {
          while (true) {
            System.out.print("Nhập giới hạn dưới khoảng đơn giá của sản phẩm: ");
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
            System.out.print("Nhập giới hạn trên khoảng đơn giá của sản phẩm: ");
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
            SanPham[] sp = _dsSanPham.timTheoKhoangDonGia(duoi, tren);
            if (sp.length == 0) {
              System.out.println("Không tìm thấy sản phẩm có khoảng đơn giá: " + duoi + "-" + tren);
            } else xuatTimKiemDanhSachSanPham(sp);
            break;
          }
        } else if (c == '3') {
          while (true) {
            System.out.print("Nhập số lượng tồn của sản phẩm: ");
            int ton;
            try {
              ton = Integer.parseInt(STANDARD_IN.nextLine());
            } catch (Throwable e) {
              processingInternalThrowable(e);
              System.out.println("Lỗi!");
              continue;
            }
            SanPham[] sp = _dsSanPham.timTheoSoLuongTon(ton);
            if (sp.length == 0) {
              System.out.println("Không tìm thấy sản phẩm có số lượng tồn: " + ton);
            } else xuatTimKiemDanhSachSanPham(sp);
            break;
          }
        } else if (c == '4') {
          while (true) {
            System.out.print("Nhập giới hạn dưới khoảng số lượng tồn của sản phẩm: ");
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
            System.out.print("Nhập giới hạn trên khoảng số lượng tồn của sản phẩm: ");
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
            SanPham[] sp = _dsSanPham.timTheoKhoangSoLuongTon(duoi, tren);
            if (sp.length == 0) {
              System.out.println("Không tìm thấy sản phẩm có khoảng số lượng tồn: " + duoi + "-" + tren);
            } else xuatTimKiemDanhSachSanPham(sp);
            break;
          }
        } else if (c == '5') {
          while (true) {
            System.out.print("Nhập phụ kiện tặng kèm của máy tính bàn: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              MayTinhBan[] sp = _dsSanPham.timTheoPhuKien(s);
              if (sp.length == 0) {
                System.out.println("Không tìm thấy máy tính bàn có phụ kiện tặng kèm chứa: " + s);
              } else xuatTimKiemDanhSachSanPham(sp);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '6') {
          while (true) {
            System.out.print("Nhập chất lượng loa của laptop: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              Laptop[] sp = _dsSanPham.timTheoChatLuongLoa(s);
              if (sp.length == 0) {
                System.out.println("Không tìm thấy laptop có chất lượng loa chứa: " + s);
              } else xuatTimKiemDanhSachSanPham(sp);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '7') {
          boolean cu;
          while (true) {
            System.out.print("Laptop có màn hình cảm ứng không? (0. Không, 1. Có): ");
            s = STANDARD_IN.nextLine();
            if (s.length() == 1) {
              c = s.charAt(0);
              if (c == '0') {
                cu = false;
                break;
              } else if (c == '1') {
                cu = true;
                break;
              }
            }
            System.out.println("Lỗi!");
          }
          SanPham[] sp = _dsSanPham.timTheoManHinhCamUng(cu);
          if (sp.length == 0) {
            System.out.println(cu ? "Không tìm thấy sản phẩm có màn hình cảm ứng." : "Không tìm thấy sản phẩm không có màn hình cảm ứng.");
          } else xuatTimKiemDanhSachSanPham(sp);
        } else if (c == '8') {
          boolean led;
          while (true) {
            System.out.print("Laptop có LED bàn phím không? (0. Không, 1. Có): ");
            s = STANDARD_IN.nextLine();
            if (s.length() == 1) {
              c = s.charAt(0);
              if (c == '0') {
                led = false;
                break;
              } else if (c == '1') {
                led = true;
                break;
              }
            }
            System.out.println("Lỗi!");
          }
          SanPham[] sp = _dsSanPham.timTheoLedBanPhim(led);
          if (sp.length == 0) {
            System.out.println(led ? "Không tìm thấy sản phẩm có LED bàn phím." : "Không tìm thấy sản phẩm không có LED bàn phím.");
          } else xuatTimKiemDanhSachSanPham(sp);
        } else break;
      } else break;
    }
  }
  private static void menuTimKiemChiTietDanhSachSanPham() {
    while (true) {
      System.out.println();
      System.out.println("+------------------- DANH SÁCH SẢN PHẨM > TÌM KIẾM CHI TIẾT -------------------+");
      System.out.println("|  1:             Theo CPU                                                     |");
      System.out.println("|  2:             Theo GPU                                                     |");
      System.out.println("|  3:             Theo RAM                                                     |");
      System.out.println("|  4:             Theo màn hình                                                |");
      System.out.println("|  5:             Theo hệ điều hành                                            |");
      System.out.println("|  6:             Theo năm sản xuất                                            |");
      System.out.println("|  7:             Theo thời gian bảo hành                                      |");
      System.out.println("|  8:             Theo khoảng thời gian bảo hành                               |");
      System.out.println("|  *:             Trở về menu danh sách                                        |");
      System.out.println("+------------------------------------------------------------------------------+");
      System.out.print("> ");
      String s = STANDARD_IN.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') {
          while (true) {
            System.out.print("Nhập CPU sản phẩm: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              SanPham[] sp = _dsSanPham.timTheoCPU(s);
              if (sp.length == 0) {
                System.out.println("Không tìm thấy sản phẩm có CPU chứa: " + s);
              } else xuatTimKiemDanhSachSanPham(sp);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '2') {
          while (true) {
            System.out.print("Nhập GPU sản phẩm: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              SanPham[] sp = _dsSanPham.timTheoGPU(s);
              if (sp.length == 0) {
                System.out.println("Không tìm thấy sản phẩm có GPU chứa: " + s);
              } else xuatTimKiemDanhSachSanPham(sp);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '3') {
          while (true) {
            System.out.print("Nhập RAM sản phẩm: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              SanPham[] sp = _dsSanPham.timTheoRAM(s);
              if (sp.length == 0) {
                System.out.println("Không tìm thấy sản phẩm có RAM chứa: " + s);
              } else xuatTimKiemDanhSachSanPham(sp);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '4') {
          while (true) {
            System.out.print("Nhập màn hình sản phẩm: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              SanPham[] sp = _dsSanPham.timTheoManHinh(s);
              if (sp.length == 0) {
                System.out.println("Không tìm thấy sản phẩm có màn hình chứa: " + s);
              } else xuatTimKiemDanhSachSanPham(sp);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '5') {
          while (true) {
            System.out.print("Nhập hệ điều hành sản phẩm: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              SanPham[] sp = _dsSanPham.timTheoHeDieuHanh(s);
              if (sp.length == 0) {
                System.out.println("Không tìm thấy sản phẩm có hệ điều hành chứa: " + s);
              } else xuatTimKiemDanhSachSanPham(sp);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '6') {
          while (true) {
            System.out.print("Nhập năm sản xuất sản phẩm: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              SanPham[] sp = _dsSanPham.timTheoNamSanXuat(s);
              if (sp.length == 0) {
                System.out.println("Không tìm thấy sản phẩm có năm sản xuất chứa: " + s);
              } else xuatTimKiemDanhSachSanPham(sp);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '7') {
          while (true) {
            System.out.print("Nhập thời gian bảo hành của sản phẩm: ");
            int bh;
            try {
              bh = Integer.parseInt(STANDARD_IN.nextLine());
            } catch (Throwable e) {
              processingInternalThrowable(e);
              System.out.println("Lỗi!");
              continue;
            }
            SanPham[] sp = _dsSanPham.timTheoThoiGianBaoHanh(bh);
            if (sp.length == 0) {
              System.out.println("Không tìm thấy sản phẩm có thời gian bảo hành: " + bh);
            } else xuatTimKiemDanhSachSanPham(sp);
            break;
          }
        } else if (c == '8') {
          while (true) {
            System.out.print("Nhập giới hạn dưới khoảng thời gian bảo hành của sản phẩm: ");
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
            System.out.print("Nhập giới hạn trên khoảng thời gian bảo hành của sản phẩm: ");
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
            SanPham[] sp = _dsSanPham.timTheoKhoangThoiGianBaoHanh(duoi, tren);
            if (sp.length == 0) {
              System.out.println("Không tìm thấy sản phẩm có khoảng thời gian bảo hành: " + duoi + "-" + tren);
            } else xuatTimKiemDanhSachSanPham(sp);
            break;
          }
        } else break;
      } else break;
    }
  }
  private static void menuDanhSachSanPham() {
    while (true) {
      System.out.println();
      System.out.println("+----------------------------- DANH SÁCH SẢN PHẨM -----------------------------+");
      System.out.println("|  1:             Sửa                                                          |");
      System.out.println("|  2:             Tìm kiếm theo mã                                             |");
      System.out.println("|  3:             Tìm kiếm máy tính bàn                                        |");
      System.out.println("|  4:             Tìm kiếm laptop                                              |");
      System.out.println("|  5:             Tìm kiếm theo tên                                            |");
      System.out.println("|  6:             Tìm kiếm theo tên nhà sản xuất                               |");
      System.out.println("|  7:             Tìm kiếm chi tiết                                            |");
      System.out.println("|  8:             Tìm kiếm khác                                                |");
      System.out.println("|  9:             Xem                                                          |");
      System.out.println("|  *:             Trở về menu quản lý                                          |");
      System.out.println("+------------------------------------------------------------------------------+");
      System.out.print("> ");
      String s = STANDARD_IN.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') {
          while (true) {
            System.out.print("Nhập mã sản phẩm: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              SanPham sp = _dsSanPham.timTheoMa(s);
              if (sp != null) {
                sp.edit();
                break;
              }
            }
            System.out.println("Lỗi!");
          }
          saveDatabase();
        } else if (c == '2') {
          while (true) {
            System.out.print("Nhập mã sản phẩm: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateID(s)) {
              SanPham sp = _dsSanPham.timTheoMa(s);
              if (sp == null) {
                System.out.println("Không tìm thấy sản phẩm có mã: " + s);
              } else {
                sp.output();
              }
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '3') {
          MayTinhBan[] sp = _dsSanPham.timMayTinhBan();
          if (sp.length == 0) {
            System.out.println("Không tìm thấy máy tính bàn.");
          } else xuatTimKiemDanhSachSanPham(sp);
        } else if (c == '4') {
          Laptop[] sp = _dsSanPham.timLaptop();
          if (sp.length == 0) {
            System.out.println("Không tìm thấy laptop.");
          } else xuatTimKiemDanhSachSanPham(sp);
        } else if (c == '5') {
          while (true) {
            System.out.print("Nhập tên sản phẩm: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              SanPham[] sp = _dsSanPham.timTheoTen(s);
              if (sp.length == 0) {
                System.out.println("Không tìm thấy sản phẩm có tên chứa: " + s);
              } else xuatTimKiemDanhSachSanPham(sp);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '6') {
          while (true) {
            System.out.print("Nhập tên nhà sản xuất sản phẩm: ");
            s = STANDARD_IN.nextLine();
            if (Validator.validateName(s)) {
              SanPham[] sp = _dsSanPham.timTheoTenNhaSanXuat(s);
              if (sp.length == 0) {
                System.out.println("Không tìm thấy nhà sản xuất sản phẩm có tên chứa: " + s);
              } else xuatTimKiemDanhSachSanPham(sp);
              break;
            }
            System.out.println("Lỗi!");
          }
        } else if (c == '7') {
          menuTimKiemChiTietDanhSachSanPham();
        } else if (c == '8') {
          menuTimKiemDanhSachSanPham();
        } else if (c == '9') {
          _dsSanPham.output();
        } else break;
      } else break;
    }
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
    if (new File(_databasePath).exists()) {
      _dsSanPham.readFile();
      _dsNhanVien.readFile();
      _dsKhachHang.readFile();
      _dsNhaCungCap.readFile();
      _dsHoaDon.readFile();
      _dsPhieuNhapHang.readFile();
    }
  }
  private static void saveDatabase() {
    File dir = new File(_databasePath);
    if (!dir.exists()) dir.mkdirs();
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
  //
  // private static void preload() {
  //   DanhSachSanPham dssp = new DanhSachSanPham();
  //   DanhSachNhanVien dsnv = new DanhSachNhanVien();
  //   DanhSachKhachHang dskh = new DanhSachKhachHang();
  //   DanhSachNhaCungCap dsncc = new DanhSachNhaCungCap();
  //   DanhSachHoaDon dshd = new DanhSachHoaDon();
  //   DanhSachPhieuNhapHang dspnh = new DanhSachPhieuNhapHang();
  //
  //   SanPham sp = null;
  //   dssp.add(sp);
  //   sp = null;
  //   dssp.add(sp);
  //   sp = null;
  //   dssp.add(sp);
  //   sp = null;
  //   dssp.add(sp);
  //   sp = null;
  //   dssp.add(sp);
  //   sp = null;
  //   dssp.add(sp);
  //   sp = null;
  //   dssp.add(sp);
  //   sp = null;
  //   dssp.add(sp);
  //   sp = null;
  //   dssp.add(sp);
  //   sp = null;
  //   dssp.add(sp);
  //
  //   // TODO: other...
  //
  //   dssp.writeFile();
  //   dsnv.writeFile();
  //   dskh.writeFile();
  //   dsncc.writeFile();
  //   dshd.writeFile();
  //   dspnh.writeFile();
  // }

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
      } else /*if (arg.startsWith("-p") || arg.startsWith("--preload-mode")) {
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
      } else*/ if (arg.startsWith("-b")) {
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
