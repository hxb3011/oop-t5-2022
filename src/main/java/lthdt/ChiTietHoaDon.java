package lthdt;

import java.io.*;
import java.util.Scanner;

@SuppressWarnings("unused")
public class ChiTietHoaDon implements IConsoleIO, IConsoleEditable, IStreamIO {
  private SanPham _sp;
  private int _soLuong;
  private long _thanhTien;
  public ChiTietHoaDon(SanPham sp, int soLuong) {
    _sp = sp;
    _soLuong = soLuong;
    _thanhTien = sp != null ? sp.getDonGia() * soLuong : 0L;
  }
  public ChiTietHoaDon() { this(null, 1); }
  public ChiTietHoaDon(ChiTietHoaDon ct) { this(ct._sp, ct._soLuong); }
  public SanPham getSanPham() { return _sp; }
  public void setSanPham(SanPham sp) {
    _sp = sp;
    _thanhTien = sp.getDonGia() * _soLuong;
  }
  public long getDonGia() { return _sp.getDonGia(); }
  public int getSoLuong() { return _soLuong; }
  public void setSoLuong(int soLuong) {
    _soLuong = soLuong;
    _thanhTien = _sp != null ? _sp.getDonGia() * soLuong : 0L;
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
        if (Validator.validateID(s)) {
          _sp = dssp.timTheoMa(s);
          if (_sp != null && _sp.getSoLuong() > 0) break;
        }
        System.out.println("Lỗi!");
      }
    }
    while (true) {
      System.out.print("Nhập số lượng sản phẩm: ");
      try {
        _soLuong = Integer.parseInt(in.nextLine());
      } catch (Throwable e) {
        QuanLyCuaHangMayTinh.processingInternalThrowable(e);
        System.out.println("Lỗi!");
        continue;
      }
      if (_soLuong >= 0 && _sp.getSoLuong() >= _soLuong) {
        _sp.setSoLuong(_sp.getSoLuong() - _soLuong);
        break;
      }
      System.out.println("Lỗi!");
    }
    _thanhTien = _sp.getDonGia() * _soLuong;
  }
  public void output() { System.out.printf("%-30s %-10s %-20s\n", _sp.getTen(), _soLuong, _thanhTien + "VND"); }
  public void input(Scanner in) {
    _sp = QuanLyCuaHangMayTinh._dsSanPham.timTheoMa(in.nextLine());
    _soLuong = Integer.parseInt(in.nextLine());
    _thanhTien = Long.parseLong(in.nextLine());
  }
  public void output(OutputStreamWriter out) {
    String nl = System.lineSeparator();
    try {
      out.write(_sp.getMa());
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
      if (Validator.validateID(s)) {
        SanPham sp = dssp.timTheoMa(s);
        if (sp != null && sp.getSoLuong() >= _soLuong) {
          _sp.setSoLuong(_sp.getSoLuong() + _soLuong);
          sp.setSoLuong(sp.getSoLuong() - _soLuong);
          _sp = sp;
          break;
        }
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập số lượng sản phẩm: ");
      String s = in.nextLine();
      if (s.isEmpty()) break;
      int soLuong;
      try {
        soLuong = Integer.parseInt(s);
      } catch (Throwable e) {
        QuanLyCuaHangMayTinh.processingInternalThrowable(e);
        System.out.println("Lỗi!");
        continue;
      }
      int soLuongTon = _sp.getSoLuong() + _soLuong;
      if (soLuong > 0 && soLuongTon >= soLuong) {
        _soLuong = soLuong;
        _sp.setSoLuong(soLuongTon - soLuong);
        break;
      }
      System.out.println("Lỗi!");
    }
    _thanhTien = _sp.getDonGia() * _soLuong;
  }
}
