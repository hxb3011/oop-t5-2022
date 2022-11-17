package lthdt;

import java.io.*;
import java.util.Scanner;

public final class NhaCungCap implements IConsoleIO, IConsoleEditable, IStreamIO {
  private String _ma;
  private String _ten;
  public NhaCungCap(String ma, String ten) {
    _ma = ma;
    _ten = ten;
  }
  public NhaCungCap() { this("", ""); }
  public NhaCungCap(NhaCungCap o) { this(o._ma, o._ten); }

  public String getMa() { return _ma; }
  public void setMa(String ma) { _ma = ma; }
  public String getTen() { return _ten; }
  public void setTen(String ten) { _ten = ten; }

  public void input() {
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    if (_ma.isBlank()) {
      System.out.print("Nhập mã nhà cung cấp: ");
      _ma = in.nextLine();
    }
    System.out.print("Nhập tên nhà cung cấp: ");
    _ten = in.nextLine();
  }
  public void output() {
    System.out.printf("\tMã: %s\n\tTên: %s\n", _ma, _ten);
  }
  public void edit() {
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    String s;
    System.out.println(QuanLyCuaHangMayTinh.EDIT_NOTE);
    while (true) {
      System.out.print("Nhập mã nhà cung cấp: ");
      if ((s = in.nextLine()).isBlank()) break;
      if (!QuanLyCuaHangMayTinh._dsNhaCungCap.daCo(s)) {
        _ma = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    System.out.print("Nhập tên nhà cung cấp: ");
    if (!(s = in.nextLine()).isBlank()) _ten = s;
  }
  public void input(Scanner in) {
    if (_ma.isBlank()) _ma = in.nextLine();
    _ten = in.nextLine();
  }
  public void output(OutputStreamWriter out) {
    try {
      String nl = System.lineSeparator();
      out.write(_ma);
      out.write(nl);
      out.write(_ten);
      out.write(nl);
    } catch (Throwable e) {
      QuanLyCuaHangMayTinh.processingInternalThrowable(e);
      System.out.println("Ghi không thành công.");
    }
  }
}
