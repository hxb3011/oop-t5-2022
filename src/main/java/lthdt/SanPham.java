package lthdt;

import java.io.*;
import java.util.Scanner;

@SuppressWarnings("unused")
public abstract class SanPham implements IConsoleIO, IConsoleEditable, IStreamIO, IStream2IO {
  private String _ma;
  private String _ten;
  private String _tenNSX;
  private long _donGia;
  private int _soLuong;
  public SanPham(String ma, String ten, String tenNSX, long donGia, int soLuong) {
    _ma = ma;
    _ten = ten;
    _donGia = donGia;
    _soLuong = soLuong;
    _tenNSX = tenNSX;
  }
  public SanPham() { this("", "", "", 0L, 0); }
  public SanPham(SanPham other) { this(other._ma, other._ten, other._tenNSX, other._donGia, other._soLuong); }
  public String getMa() { return _ma; }
  public void setMa(String ma) { _ma = ma; }
  public String getTen() { return _ten; }
  public void setTen(String ten) { _ten = ten; }
  public long getDonGia() { return _donGia; }
  public void setDonGia(long donGia) { _donGia = donGia; }
  public int getSoLuong() { return _soLuong; }
  public void setSoLuong(int soLuong) { _soLuong = soLuong; }
  public abstract ChiTietSanPham getChiTiet();
  public abstract void setChiTiet(ChiTietSanPham chiTiet);
  public String getTenNhaSanXuat() { return _tenNSX; }
  public void setTenNhaSanXuat(String ten) { _tenNSX = ten; }
  public void input() {
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    if (_ma.isEmpty()) {
      while (true) {
        System.out.print("Nhập mã sản phẩm: ");
        String s = in.nextLine();
        if (Validator.validateID(s)) {
          _ma = s;
          break;
        }
        System.out.println("Lỗi!");
      }
    }
    while (true) {
      System.out.print("Nhập tên sản phẩm: ");
      String s = in.nextLine();
      if (Validator.validateName(s)) {
        _ten = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập tên nhà sản xuất: ");
      String s = in.nextLine();
      if (Validator.validateName(s)) {
        _tenNSX = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập đơn giá: ");
      String s = in.nextLine();
      try {
        _donGia = Long.parseLong(s);
        break;
      } catch (Throwable e) {
        QuanLyCuaHangMayTinh.processingInternalThrowable(e);
        System.out.println("Lỗi!");
      }
    }
  }
  public void output() {
    System.out.printf("\tMã: %s\n\tTên: %s\n\tNhà sản xuất: %s\n\tĐơn giá: %d\n\tSố lượng tồn: %d\n", _ma, _ten, _tenNSX, _donGia, _soLuong);
  }
  public void input(Scanner in) {
    if (_ma.isEmpty()) _ma = in.nextLine();
    _ten = in.nextLine();
    _tenNSX = in.nextLine();
    _donGia = Long.parseLong(in.nextLine());
    _soLuong = Integer.parseInt(in.nextLine());
  }
  public void output(OutputStreamWriter out) {
    try {
      String nl = System.lineSeparator();
      out.write(_ma);
      out.write(nl);
      out.write(_ten);
      out.write(nl);
      out.write(_tenNSX);
      out.write(nl);
      out.write(String.valueOf(_donGia));
      out.write(nl);
      out.write(String.valueOf(_soLuong));
      out.write(nl);
      out.flush();
    } catch (Throwable e) { QuanLyCuaHangMayTinh.processingInternalThrowable(e); }
  }
  public void edit() {
    String s;
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    System.out.println(QuanLyCuaHangMayTinh.EDIT_NOTE);
    while (true) {
      System.out.print("Nhập mã sản phẩm: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (!Validator.validateID(s)) {
        System.out.println("Lỗi!");
        continue;
      }
      if (!QuanLyCuaHangMayTinh._dsSanPham.daCo(s)) {
        _ma = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập tên sản phẩm: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (Validator.validateName(s)) {
        _ten = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập tên nhà sản xuất: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (Validator.validateName(s)) {
        _tenNSX = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập đơn giá: ");
      if ((s = in.nextLine()).isEmpty()) break;
      try {
        _donGia = Long.parseLong(s);
        break;
      } catch (Throwable e) {
        QuanLyCuaHangMayTinh.processingInternalThrowable(e);
        System.out.println("Lỗi!");
      }
    }
  }
  public abstract void input(Scanner base, Scanner spec);
  public abstract void output(OutputStreamWriter base, OutputStreamWriter spec);
}
