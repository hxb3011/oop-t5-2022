package lthdt;

import java.io.*;
import java.util.Scanner;

@SuppressWarnings("unused")
public final class KhachHang implements IConsoleIO, IConsoleEditable, IStreamIO {
  private String _ma;
  private String _ho;
  private String _ten;
  private String _soDienThoai;
  private String _diaChi;
  public KhachHang(String ma, String ho, String ten, String soDienThoai, String diaChi) {
    _ma = ma;
    _ho = ho;
    _ten = ten;
    _soDienThoai = soDienThoai;
    _diaChi = diaChi;
  }
  public KhachHang() { this("", "", "", "", ""); }
  public KhachHang(KhachHang o) { this(o._ma, o._ho, o._ten, o._soDienThoai, o._diaChi); }
  public String getMa() { return _ma; }
  public void setMa(String ma) { _ma = ma; }
  public String getHo() { return _ho; }
  public void setHo(String ho) { _ho = ho; }
  public String getTen() { return _ten; }
  public void setTen(String ten) { _ten = ten; }
  public String getHoTen() { return _ho + ' ' + _ten; }
  public String getSoDienThoai() { return _soDienThoai; }
  public void setSoDienThoai(String soDienThoai) { _soDienThoai = soDienThoai; }
  public String getDiaChi() { return _diaChi; }
  public void setDiaChi(String diaChi) { _diaChi = diaChi; }
  public void input() {
    String s;
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    if (_ma.isEmpty()) {
      while (true) {
        System.out.print("Nhập mã khách hàng: ");
        if (Validator.validateID(s = in.nextLine())) {
          _ma = s;
          break;
        }
        System.out.println("Lỗi!");
      }
    }
    while (true) {
      System.out.print("Nhập họ: ");
      if (Validator.validateName(s = in.nextLine())) {
        _ho = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập tên: ");
      if (Validator.validateName(s = in.nextLine())) {
        _ten = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập số điện thoại: ");
      if (Validator.validatePhoneNumber(s = in.nextLine())) {
        _soDienThoai = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập địa chỉ: ");
      if (Validator.validateName(s = in.nextLine())) {
        _diaChi = s;
        break;
      }
      System.out.println("Lỗi!");
    }
  }
  public void output() {
    System.out.printf("\tMã: %s\n\tHọ tên: %s %s\n\tSố điện thoại: %s\n\tĐịa chỉ: %s\n", _ma, _ho, _ten, _soDienThoai, _diaChi);
  }
  public void edit() {
    String s;
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    System.out.println(QuanLyCuaHangMayTinh.EDIT_NOTE);
    while (true) {
      System.out.print("Nhập mã khách hàng: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (!Validator.validateID(s)) {
        System.out.println("Lỗi!");
        continue;
      }
      if (!QuanLyCuaHangMayTinh._dsKhachHang.daCo(s)) {
        _ma = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập họ: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (Validator.validateName(s)) {
        _ho = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập tên: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (Validator.validateName(s)) {
        _ten = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập số điện thoại: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (Validator.validatePhoneNumber(s)) {
        _soDienThoai = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập địa chỉ: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (Validator.validateName(s)) {
        _diaChi = s;
        break;
      }
      System.out.println("Lỗi!");
    }
  }
  public void input(Scanner in) {
    if (_ma.isBlank()) _ma = in.nextLine();
    _ho = in.nextLine();
    _ten = in.nextLine();
    _soDienThoai = in.nextLine();
    _diaChi = in.nextLine();
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
      out.write(_diaChi);
      out.write(nl);
    } catch (Throwable e) {
      QuanLyCuaHangMayTinh.processingInternalThrowable(e);
      System.out.println("Ghi không thành công.");
    }
  }
}
