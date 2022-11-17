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
    if (_ma.isBlank()) {
      System.out.print("Nhập mã sản phẩm: ");
      _ma = in.nextLine();
    }
    System.out.print("Nhập tên sản phẩm: ");
    _ten = in.nextLine();
    System.out.print("Nhập tên nhà sản xuất: ");
    _tenNSX = in.nextLine();
    System.out.print("Nhập đơn giá: ");
    _donGia = Long.parseLong(in.nextLine());
  }
  public void output() {
    System.out.printf("\tMã: %s\n\tTên: %s\n\tNhà sản xuất: %s\n\tĐơn giá: %d\n\tSố lượng tồn: %d\n", _ma, _ten, _tenNSX, _donGia, _soLuong);
  }
  public void input(Scanner in) {
    if (_ma.isBlank()) _ma = in.nextLine();
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
    String v;
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    System.out.println(QuanLyCuaHangMayTinh.EDIT_NOTE);
    while (true) {
      System.out.print("Nhập mã sản phẩm: ");
      if ((v = in.nextLine()).isBlank()) break;
      if (!QuanLyCuaHangMayTinh._dsSanPham.daCo(v)) {
        _ma = v;
        break;
      }
      System.out.println("Lỗi!");
    }
    System.out.print("Nhập tên sản phẩm: ");
    if (!(v = in.nextLine()).isBlank()) _ten = v;
    System.out.print("Nhập tên nhà sản xuất: ");
    if (!(v = in.nextLine()).isBlank()) _tenNSX = v;
    System.out.print("Nhập đơn giá: ");
    if (!(v = in.nextLine()).isBlank()) _donGia = Long.parseLong(v);
  }
  public abstract void input(Scanner base, Scanner spec);
  public abstract void output(OutputStreamWriter base, OutputStreamWriter spec);
}
