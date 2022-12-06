package lthdt;

import java.io.OutputStreamWriter;
import java.util.Scanner;

@SuppressWarnings("unused")
public class ChiTietPhieuNhapHang implements IConsoleIO, IConsoleEditable, IStreamIO {
  private SanPham _sp;
  private int _donGia;
  private int _soLuong;
  private long _thanhTien;
  public ChiTietPhieuNhapHang(SanPham sp, int donGia, int soLuong) {
    _sp = sp;
    _donGia = donGia;
    _soLuong = soLuong;
    _thanhTien = sp != null ? sp.getDonGia() * soLuong : 0L;
  }
  public ChiTietPhieuNhapHang() { this(null, 0, 1); }
  public ChiTietPhieuNhapHang(ChiTietPhieuNhapHang ct) { this(ct._sp, ct._donGia, ct._soLuong); }
  public SanPham getSanPham() { return _sp; }
  public void setSanPham(SanPham sp) { _sp = sp; }
  public int getDonGia() { return _donGia; }
  public void setDonGia(int donGia) {
    _donGia = donGia;
    _thanhTien = ((long) donGia) * _soLuong;
  }
  public int getSoLuong() { return _soLuong; }
  public void setSoLuong(int soLuong) {
    _soLuong = soLuong;
    _thanhTien = ((long) soLuong) * _donGia;
  }
  public long getThanhTien() { return _thanhTien; }
  public void setThanhTien(long thanhTien) { _thanhTien = thanhTien; }
  public void input() {
    DanhSachSanPham dssp = QuanLyCuaHangMayTinh._dsSanPham;
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    if (_sp == null) {
      while (true) {
        System.out.print("Nhập mã sản phẩm: ");
        String s = in.nextLine();
        if (!Validator.validateID(s)) {
          System.out.println("Lỗi!");
          continue;
        }
        _sp = dssp.timTheoMa(s);
        if (_sp == null) {
          System.out.println("Thêm sản phẩm mới? (*. Không / 1. Phải): ");
          String ans = in.nextLine();
          if (ans.length() != 1 || ans.charAt(0) != '1') {
            System.out.println("Lỗi!");
            continue;
          }
          while (true) {
            System.out.println("Nhập loại sản phẩm cần thêm (1. Laptop, 2. Máy Tính Để Bàn): ");
            String type = in.nextLine();
            if (type.length() == 1) {
              char c = type.charAt(0);
              if (c == '1') {
                _sp = new Laptop();
                break;
              } else if (c == '2') {
                _sp = new MayTinhBan();
                break;
              }
            }
            System.out.println("Lỗi!");
          }
          _sp.setMa(s);
          _sp.input();
          dssp.add(_sp);
        }
        break;
      }
    }
    while (true) {
      System.out.println("Nhập đơn giá nhập hàng của sản phẩm: ");
      try {
        _donGia = Integer.parseInt(in.nextLine());
        break;
      } catch (Throwable e) {
        QuanLyCuaHangMayTinh.processingInternalThrowable(e);
        System.out.println("Lỗi!");
      }
    }
    while (true) {
      System.out.println("Nhập số lượng sản phẩm: ");
      try {
        _soLuong = Integer.parseInt(in.nextLine());
        break;
      } catch (Throwable e) {
        QuanLyCuaHangMayTinh.processingInternalThrowable(e);
        System.out.println("Lỗi!");
      }
    }
    _sp.setSoLuong(_sp.getSoLuong() + _soLuong);
    _thanhTien = ((long) _donGia) * _soLuong;
  }
  public void output() { System.out.printf("%-30s %-10s %-20s\n", _sp.getTen(), _soLuong, _thanhTien + "VND"); }
  public void input(Scanner in) {
    _sp = QuanLyCuaHangMayTinh._dsSanPham.timTheoMa(in.nextLine());
    _donGia = Integer.parseInt(in.nextLine());
    _soLuong = Integer.parseInt(in.nextLine());
    _thanhTien = Long.parseLong(in.nextLine());
  }
  public void output(OutputStreamWriter out) {
    String nl = System.lineSeparator();
    try {
      out.write(_sp.getMa());
      out.write(nl);
      out.write(String.valueOf(_donGia));
      out.write(nl);
      out.write(String.valueOf(_soLuong));
      out.write(nl);
      out.write(String.valueOf(_thanhTien));
      out.write(nl);
    } catch (Throwable e) { QuanLyCuaHangMayTinh.processingInternalThrowable(e); }
  }
  public void edit() {
    DanhSachSanPham dssp = QuanLyCuaHangMayTinh._dsSanPham;
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    System.out.println(QuanLyCuaHangMayTinh.EDIT_NOTE);
    while (true) {
      System.out.print("Nhập mã sản phẩm: ");
      String s = in.nextLine();
      if (s.isEmpty()) break;
      if (!Validator.validateID(s)) {
        System.out.println("Lỗi!");
        continue;
      }
      SanPham sp = dssp.timTheoMa(s);
      if (sp == null) {
        System.out.println("Thêm sản phẩm mới? (*. Không / 1. Phải): ");
        String ans = in.nextLine();
        if (ans.length() != 1 || ans.charAt(0) != '1') {
          System.out.println("Lỗi!");
          continue;
        }
        while (true) {
          System.out.println("Nhập loại sản phẩm cần thêm (1. Laptop, 2. Máy Tính Để Bàn): ");
          String type = in.nextLine();
          if (type.length() == 1) {
            char c = type.charAt(0);
            if (c == '1') {
              sp = new Laptop();
              break;
            } else if (c == '2') {
              sp = new MayTinhBan();
              break;
            }
          }
          System.out.println("Lỗi!");
        }
        sp.setMa(s);
        sp.input();
        dssp.add(sp);
      }
      _sp.setSoLuong(_sp.getSoLuong() - _soLuong);
      sp.setSoLuong(sp.getSoLuong() + _soLuong);
      _sp = sp;
      break;
    }
    String s;
    while (true) {
      System.out.println("Nhập đơn giá nhập hàng của sản phẩm: ");
      if ((s = in.nextLine()).isEmpty()) break;
      int dg;
      try {
        dg = Integer.parseInt(s);
      } catch (Throwable e) {
        QuanLyCuaHangMayTinh.processingInternalThrowable(e);
        System.out.println("Lỗi!");
        continue;
      }
      setDonGia(dg);
      break;
    }
    while (true) {
      System.out.println("Nhập số lượng sản phẩm: ");
      if ((s = in.nextLine()).isEmpty()) break;
      int soLuongTon = _sp.getSoLuong() - _soLuong, soLuong;
      try {
        soLuong = Integer.parseInt(s);
      } catch (Throwable e) {
        QuanLyCuaHangMayTinh.processingInternalThrowable(e);
        System.out.println("Lỗi!");
        continue;
      }
      setSoLuong(soLuong);
      _sp.setSoLuong(soLuongTon + soLuong);
      break;
    }
  }
}
