package lthdt;

import java.io.*;
import java.util.Scanner;

@SuppressWarnings("unused")
public final class ChiTietSanPham implements IConsoleIO, IConsoleEditable, IStreamIO {
  private String _CPU;
  private String _GPU;
  private String _RAM;
  private String _manHinh;
  private String _heDieuHanh;
  private String _namSanXuat;
  private int _thoiGianBaoHanh;
  public ChiTietSanPham(String CPU, String GPU, String RAM, String manHinh, String namSanXuat, String heDieuHanh, int thoiGianBaoHanh) {
    _CPU = CPU;
    _GPU = GPU;
    _RAM = RAM;
    _manHinh = manHinh;
    _namSanXuat = namSanXuat;
    _heDieuHanh = heDieuHanh;
    _thoiGianBaoHanh = thoiGianBaoHanh;
  }
  public ChiTietSanPham() { this("", "", "", "", "", "", 0); }
  public ChiTietSanPham(ChiTietSanPham other) { this(other._CPU, other._GPU, other._RAM, other._manHinh, other._namSanXuat, other._heDieuHanh, other._thoiGianBaoHanh); }
  public String getCPU() { return _CPU; }
  public void setCPU(String CPU) { _CPU = CPU; }
  public String getGPU() { return _GPU; }
  public void setGPU(String GPU) { _GPU = GPU; }
  public String getHeDieuHanh() { return _heDieuHanh; }
  public void setHeDieuHanh(String heDieuHanh) { _heDieuHanh = heDieuHanh; }
  public String getManHinh() { return _manHinh; }
  public void setManHinh(String manHinh) { _manHinh = manHinh; }
  public String getNamSanXuat() { return _namSanXuat; }
  public void setNamSanXuat(String namSanXuat) { _namSanXuat = namSanXuat; }
  public String getRAM() { return _RAM; }
  public void setRAM(String RAM) { _RAM = RAM; }
  public int getThoiGianBaoHanh() { return _thoiGianBaoHanh; }
  public void setThoiGianBaoHanh(int thoiGianBaoHanh) { _thoiGianBaoHanh = thoiGianBaoHanh; }

  public void input() {
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    String s;
    while (true) {
      System.out.print("Nhập thông số CPU: ");
      if (Validator.validateName(s = in.nextLine())) {
        _CPU = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập thông số GPU: ");
      if (Validator.validateName(s = in.nextLine())) {
        _GPU = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập thông số RAM: ");
      if (Validator.validateName(s = in.nextLine())) {
        _RAM = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập thông số màn hình: ");
      if (Validator.validateName(s = in.nextLine())) {
        _manHinh = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập tên hệ điều hành: ");
      if (Validator.validateName(s = in.nextLine())) {
        _heDieuHanh = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập năm sản xuất: ");
      if (Validator.validateName(s = in.nextLine())) {
        _namSanXuat = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập thời gian bảo hành: ");
      try {
        _thoiGianBaoHanh = Integer.parseInt(s);
        break;
      } catch (Throwable e) {
        QuanLyCuaHangMayTinh.processingInternalThrowable(e);
        System.out.println("Lỗi!");
      }
    }
  }
  public void output() {
    System.out.printf("Thông tin sản phẩm: \n\tCPU: %s\n\tGPU: %s\n\tRAM: %s\n\tMàn hình: %s\n\tHệ điều hành: %s\n\tThời gian bảo hành: %d\n\tNăm sản xuất: %s\n",
        _CPU, _GPU, _RAM, _manHinh, _heDieuHanh, _thoiGianBaoHanh, _namSanXuat);
  }
  public void edit() {
    String s;
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    System.out.println(QuanLyCuaHangMayTinh.EDIT_NOTE);
    while (true) {
      System.out.print("Nhập thông số CPU: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (Validator.validateName(s)) {
        _CPU = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập thông số GPU: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (Validator.validateName(s)) {
        _GPU = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập thông số RAM: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (Validator.validateName(s)) {
        _RAM = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập thông số màn hình: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (Validator.validateName(s)) {
        _manHinh = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập tên hệ điều hành: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (Validator.validateName(s)) {
        _heDieuHanh = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập năm sản xuất: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (Validator.validateName(s)) {
        _namSanXuat = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Nhập thời gian bảo hành: ");
      if ((s = in.nextLine()).isEmpty()) break;
      try {
        _thoiGianBaoHanh = Integer.parseInt(s);
        break;
      } catch (Throwable e) {
        QuanLyCuaHangMayTinh.processingInternalThrowable(e);
        System.out.println("Lỗi!");
      }
    }
  }
  public void input(Scanner in) {
    _CPU = in.nextLine();
    _GPU = in.nextLine();
    _RAM = in.nextLine();
    _manHinh = in.nextLine();
    _heDieuHanh = in.nextLine();
    _namSanXuat = in.nextLine();
    _thoiGianBaoHanh = Integer.parseInt(in.nextLine());
  }
  public void output(OutputStreamWriter out) {
    String nl = System.lineSeparator();
    try {
      out.write(_CPU);
      out.write(nl);
      out.write(_GPU);
      out.write(nl);
      out.write(_RAM);
      out.write(nl);
      out.write(_manHinh);
      out.write(nl);
      out.write(_heDieuHanh);
      out.write(nl);
      out.write(_namSanXuat);
      out.write(nl);
      out.write(String.valueOf(_thoiGianBaoHanh));
      out.write(nl);
    } catch (Throwable e) { QuanLyCuaHangMayTinh.processingInternalThrowable(e); }
  }
}