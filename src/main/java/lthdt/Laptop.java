package lthdt;

import java.io.*;
import java.util.Scanner;

@SuppressWarnings("unused")
public final class Laptop extends SanPham {
  private ChiTietSanPham _chiTiet;
  private String _chatLuongLoa;
  private boolean _camUng, _ledBanPhim;
  private void initRemaining(ChiTietSanPham chiTiet, String chatLuongLoa, boolean camUng, boolean ledBanPhim) {
    _chiTiet = chiTiet;
    _chatLuongLoa = chatLuongLoa;
    _camUng = camUng;
    _ledBanPhim = ledBanPhim;
  }
  public Laptop(String ma, String ten, String tenNSX, long donGia, int soLuong, ChiTietSanPham chiTiet, String chatLuongLoa, boolean camUng, boolean ledBanPhim) {
    super(ma, ten, tenNSX, donGia, soLuong);
    initRemaining(chiTiet, chatLuongLoa, camUng, ledBanPhim);
  }
  public Laptop() {
    initRemaining(new ChiTietSanPham(), "", false, false);
  }
  public Laptop(Laptop other) {
    super(other);
    initRemaining(new ChiTietSanPham(other._chiTiet), other._chatLuongLoa, other._camUng, other._ledBanPhim);
  }
  public ChiTietSanPham getChiTiet() { return _chiTiet; }
  public void setChiTiet(ChiTietSanPham chiTiet) { _chiTiet = chiTiet; }
  public String getChatLuongLoa() { return _chatLuongLoa; }
  public void setChatLuongLoa(String chatLuongLoa) { _chatLuongLoa = chatLuongLoa; }
  public boolean getManHinhCamUng() { return _camUng; }
  public void setManHinhCamUng(boolean camUng) { _camUng = camUng; }
  public boolean getLedBanPhim() { return _ledBanPhim; }
  public void setLedBanPhim(boolean ledBanPhim) { _ledBanPhim = ledBanPhim; }
  public void input() {
    super.input();
    _chiTiet.input();
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    System.out.print("Nhập chất lượng loa: ");
    _chatLuongLoa = in.nextLine();
    while (true) {
      System.out.print("Sản phẩm có màn hình cảm ứng không? (0. Không, 1. Có): ");
      String s = in.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '0') {
          _camUng = false;
          break;
        } else if (c == '1') {
          _camUng = true;
          break;
        }
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Sản phẩm có LED bàn phím không? (0. Không, 1. Có): ");
      String s = in.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '0') {
          _ledBanPhim = false;
          break;
        } else if (c == '1') {
          _ledBanPhim = true;
          break;
        }
      }
      System.out.println("Lỗi!");
    }
  }
  public void output() {
    super.output();
    _chiTiet.output();
    System.out.printf("\tChất lượng loa: %s\n", _chatLuongLoa);
    if (_camUng) System.out.println("\tMàn hình cảm ứng.");
    if (_ledBanPhim) System.out.println("\tCó LED bàn phím.");
  }
  public void edit() {
    super.edit();
    _chiTiet.edit();
    String s;
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    System.out.println(QuanLyCuaHangMayTinh.EDIT_NOTE);
    System.out.print("Nhập chất lượng loa: ");
    if (!(s = in.nextLine()).isBlank()) _chatLuongLoa = s;
    while (true) {
      System.out.print("Sản phẩm có màn hình cảm ứng không? (0. Không, 1. Có): ");
      if ((s = in.nextLine()).isBlank()) break;
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '0') {
          _camUng = false;
          break;
        } else if (c == '1') {
          _camUng = true;
          break;
        }
      }
      System.out.println("Lỗi!");
    }
    while (true) {
      System.out.print("Sản phẩm có LED bàn phím không? (0. Không, 1. Có): ");
      if ((s = in.nextLine()).isBlank()) break;
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '0') {
          _ledBanPhim = false;
          break;
        } else if (c == '1') {
          _ledBanPhim = true;
          break;
        }
      }
      System.out.println("Lỗi!");
    }
  }
  private void inputSpec(Scanner in) {
    _chatLuongLoa = in.nextLine();
    _camUng = in.nextLine().charAt(0) == '1';
    _ledBanPhim = in.nextLine().charAt(0) == '1';
  }
  private void outputSpec(OutputStreamWriter out) throws IOException {
    String nl = System.lineSeparator();
    out.write(_chatLuongLoa);
    out.write(nl);
    out.write(_camUng ? "1" : "0");
    out.write(nl);
    out.write(_ledBanPhim ? "1" : "0");
    out.write(nl);
  }
  public void input(Scanner in) {
    super.input(in);
    _chiTiet.input(in);

    inputSpec(in);
  }
  public void output(OutputStreamWriter out) {
    super.output(out);
    _chiTiet.output(out);

    try {
      outputSpec(out);
    } catch (Throwable e) {
      QuanLyCuaHangMayTinh.processingInternalThrowable(e);
      System.out.println("Ghi không thành công.");
    }
  }
  public void input(Scanner base, Scanner spec) {
    super.input(base);
    _chiTiet.input(base);

    String s = spec.nextLine();
    if (getMa().isBlank()) setMa(s);
    inputSpec(spec);
  }
  public void output(OutputStreamWriter base, OutputStreamWriter spec) {
    super.output(base);
    _chiTiet.output(base);

    try {
      spec.write(getMa());
      spec.write(System.lineSeparator());

      outputSpec(spec);
    }  catch (Throwable e) {
      QuanLyCuaHangMayTinh.processingInternalThrowable(e);
      System.out.println("Ghi không thành công.");
    }
  }
}
