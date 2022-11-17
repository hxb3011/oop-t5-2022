package lthdt;

import java.io.*;
import java.util.Scanner;

@SuppressWarnings("unused")
public final class NhanVien implements IConsoleIO, IConsoleEditable, IStreamIO {
  private String _ma;
  private String _ho;
  private String _ten;
  private String _soDienThoai;
  private long _mucLuong;
  private int _namVaoLam;
  public NhanVien(String ma, String ho, String ten, String soDienThoai, long mucLuong, int namVaoLam) {
    _ma = ma;
    _ho = ho;
    _ten = ten;
    _soDienThoai = soDienThoai;
    _mucLuong = mucLuong;
    _namVaoLam = namVaoLam;
  }
  public NhanVien() { this("", "", "", "", 0L, 0); }
  public NhanVien(NhanVien o) { this(o._ma, o._ho, o._ten, o._soDienThoai, o._mucLuong, o._namVaoLam); }
  public String getMa() { return _ma; }
  public void setMa(String ma) { _ma = ma; }
  public String getHo() { return _ho; }
  public void setHo(String ho) { _ho = ho; }
  public String getTen() { return _ten; }
  public void setTen(String ten) { _ten = ten; }
  public String getHoTen() { return _ho + ' ' + _ten; }
  public String getSoDienThoai() { return _soDienThoai; }
  public void setSoDienThoai(String soDienThoai) { _soDienThoai = soDienThoai; }
  public long getMucLuong() { return _mucLuong; }
  public void setMucLuong(long mucLuong) { _mucLuong = mucLuong; }
  public int getNamVaoLam() { return _namVaoLam; }
  public void setNamVaoLam(int namVaoLam) { _namVaoLam = namVaoLam; }
  public void input() {
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    if (_ma.isBlank()) {
      System.out.print("Nhập mã nhân viên: ");
      _ma = in.nextLine();
    }
    System.out.print("Nhập họ: ");
    _ho = in.nextLine();
    System.out.print("Nhập tên: ");
    _ten = in.nextLine();
    System.out.print("Nhập số điện thoại: ");
    _soDienThoai = in.nextLine();
    System.out.print("Nhập mức lương: ");
    _mucLuong = Long.parseLong(in.nextLine());
    System.out.print("Nhập năm vào làm: ");
    _namVaoLam = Integer.parseInt(in.nextLine());
  }
  public void output() {
    System.out.printf("\tMã: %s\n\tHọ tên: %s %s\n\tSố điện thoại: %s\n\tMức lương: %d\n\tNăm vào làm: %d\n", _ma, _ho, _ten, _soDienThoai, _mucLuong, _namVaoLam);
  }
  public void edit() {
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    String s;
    System.out.println(QuanLyCuaHangMayTinh.EDIT_NOTE);
    DanhSachNhanVien dsnv = QuanLyCuaHangMayTinh._dsNhanVien;
    while (true) {
      System.out.print("Nhập mã nhân viên: ");
      if ((s = in.nextLine()).isBlank()) break;
      if (!dsnv.daCo(s)) {
        _ma = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    System.out.print("Nhập họ: ");
    if (!(s = in.nextLine()).isBlank()) _ho = s;
    System.out.print("Nhập tên: ");
    if (!(s = in.nextLine()).isBlank()) _ten = s;
    System.out.print("Nhập số điện thoại: ");
    if (!(s = in.nextLine()).isBlank()) _soDienThoai = s;
    System.out.print("Nhập mức lương: ");
    if (!(s = in.nextLine()).isBlank()) _mucLuong = Long.parseLong(s);
    System.out.print("Nhập năm vào làm: ");
    if (!(s = in.nextLine()).isBlank()) _namVaoLam = Integer.parseInt(s);
  }
  public void input(Scanner in) {
    if (_ma.isBlank()) _ma = in.nextLine();
    _ho = in.nextLine();
    _ten = in.nextLine();
    _soDienThoai = in.nextLine();
    _mucLuong = Long.parseLong(in.nextLine());
    _namVaoLam = Integer.parseInt(in.nextLine());
  }
  public void output(OutputStreamWriter out) {
    try {
      String nl = System.lineSeparator();
      out.write(_ma);
      out.write(nl);
      out.write(_ho);
      out.write(nl);
      out.write(_ten);
      out.write(nl);
      out.write(_soDienThoai);
      out.write(nl);
      out.write(String.valueOf(_mucLuong));
      out.write(nl);
      out.write(String.valueOf(_namVaoLam));
      out.write(nl);
    } catch (Throwable e) {
      QuanLyCuaHangMayTinh.processingInternalThrowable(e);
      System.out.println("Ghi không thành công.");
    }
  }
}
