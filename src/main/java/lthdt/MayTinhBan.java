package lthdt;

import java.io.*;
import java.util.Scanner;

@SuppressWarnings("unused")
public final class MayTinhBan extends SanPham {
  private ChiTietSanPham _chiTiet;
  private String _phuKien;
  private void initRemaining(ChiTietSanPham chiTiet, String phuKien) {
    _chiTiet = chiTiet;
    _phuKien = phuKien;
  }
  public MayTinhBan(String ma, String ten, String tenNSX, long donGia, int soLuong, ChiTietSanPham chiTiet, String phuKien) {
    super(ma, ten, tenNSX, donGia, soLuong);
    initRemaining(chiTiet, phuKien);
  }
  public MayTinhBan() {
    initRemaining(new ChiTietSanPham(), "");
  }
  public MayTinhBan(MayTinhBan other) {
    super(other);
    initRemaining(other._chiTiet, other._phuKien);
  }
  public ChiTietSanPham getChiTiet() { return _chiTiet; }
  public void setChiTiet(ChiTietSanPham chiTiet) { _chiTiet = chiTiet; }
  public String getPhuKien() { return _phuKien; }
  public void setPhuKien(String phuKien) { _phuKien = phuKien; }
  public void input() {
    super.input();
    _chiTiet.input();

    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    while (true) {
      System.out.print("Nhập phụ kiện: ");
      String s = in.nextLine();
      if (Validator.validateName(s)) {
        _phuKien = s;
        break;
      }
      System.out.println("Lỗi!");
    }
  }
  public void output() {
    super.output();
    _chiTiet.output();

    System.out.printf("\tPhụ kiện: %s\n", _phuKien);
  }
  public void edit() {
    super.edit();
    _chiTiet.edit();
    String s;
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    System.out.println(QuanLyCuaHangMayTinh.EDIT_NOTE);
    while (true) {
      System.out.print("Nhập phụ kiện: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (Validator.validateName(s)) {
        _phuKien = s;
        break;
      }
      System.out.println("Lỗi!");
    }
  }
  private void inputSpec(Scanner in) {
    _phuKien = in.nextLine();
  }
  private void outputSpec(OutputStreamWriter out) throws IOException {
    String nl = System.lineSeparator();
    out.write(_phuKien);
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
    } catch (Throwable e) {
      QuanLyCuaHangMayTinh.processingInternalThrowable(e);
      System.out.println("Ghi không thành công.");
    }
  }
}
