package lthdt;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class HoaDon implements IConsoleIO, IConsoleEditable, IStreamIO {
  private String _ma;
  private int _ngayLap;
  private NhanVien _nv;
  private KhachHang _kh;
  private ChiTietHoaDon[] _chiTiet;
  private long _tongTien;
  public HoaDon(String ma, int ngayLap, NhanVien nv, KhachHang kh, ChiTietHoaDon[] chiTiet, long tongTien) {
    _ma = ma;
    _ngayLap = ngayLap;
    _nv = nv;
    _kh = kh;
    _chiTiet = chiTiet;
    _tongTien = tongTien;
  }
  public HoaDon(String ma, String ngayLap, NhanVien nv, KhachHang kh, ChiTietHoaDon[] chiTiet, long tongTien) {
    this(ma, DateUtil.parseDate(ngayLap), nv, kh, chiTiet, tongTien);
  }
  public HoaDon() { this("", DateUtil.DATE_ERROR_DATE, new NhanVien(), new KhachHang(), new ChiTietHoaDon[0], 0L); }
  public HoaDon(HoaDon hd) { this(hd._ma, hd._ngayLap, hd._nv, hd._kh, hd._chiTiet.clone(), hd._tongTien); }
  public String getMa() { return _ma; }
  public void setMa(String ma) { _ma = ma; }
  public int getNgayLap() { return _ngayLap; }
  public void setNgayLap(int ngayLap) { _ngayLap = ngayLap; }
  public String getChuoiNgayLap() { return DateUtil.toDateString(_ngayLap); }
  public void setChuoiNgayLap(String ngayLap) { _ngayLap = DateUtil.parseDate(ngayLap); }
  public NhanVien getNhanVien() { return _nv; }
  public void setNhanVien(NhanVien nv) { _nv = nv; }
  public KhachHang getKhachHang() { return _kh; }
  public void setKhachHang(KhachHang kh) { _kh = kh; }
  public ChiTietHoaDon[] getChiTiet() { return _chiTiet; }
  public void setChiTiet(ChiTietHoaDon[] chiTiet) { _chiTiet = chiTiet; }
  public long getTongTien() { return _tongTien; }
  public void setTongTien(long tongTien) { _tongTien = tongTien; }
  public boolean contain(String maSP) {
    for (ChiTietHoaDon cti : _chiTiet)
      if (cti.getSanPham().getMa().equals(maSP))
        return true;
    return false;
  }
  public boolean add(ChiTietHoaDon ncc) {
    int lastIndex = _chiTiet.length;
    ChiTietHoaDon[] arr = Arrays.copyOf(_chiTiet, lastIndex + 1);
    arr[lastIndex] = ncc;
    _chiTiet = arr;
    return true;
  }
  public boolean remove(int index) {
    int oldLength = _chiTiet.length;
    if (index < 0 || index >= oldLength) return false;
    ChiTietHoaDon[] arr = new ChiTietHoaDon[oldLength - 1];
    System.arraycopy(_chiTiet, 0, arr, 0, index);
    System.arraycopy(_chiTiet, index + 1, arr, index, oldLength - 1 - index);
    _chiTiet = arr;
    return true;
  }
  public void input() {
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    if (_ma.isBlank()) {
      System.out.print("Nhập mã hoá đơn: ");
      _ma = in.nextLine();
    }
    System.out.print("Nhập ngày lập: ");
    _ngayLap = DateUtil.parseDate(in.nextLine());
    DanhSachNhanVien dsnv = QuanLyCuaHangMayTinh._dsNhanVien;
    System.out.println("Danh sách nhân viên:");
    dsnv.output();
    while (true) {
      System.out.print("Nhập mã nhân viên: ");
      String ma = in.nextLine();
      _nv = dsnv.timTheoMa(ma);
      if (_nv != null) break;
      System.out.println("Lỗi!");
    }
    DanhSachKhachHang dskh = QuanLyCuaHangMayTinh._dsKhachHang;
    System.out.println("Danh sách khách hàng: ");
    dskh.output();
    while (true) {
      System.out.print("Nhập mã khách hàng: ");
      String ma = in.nextLine();
      _kh = dskh.timTheoMa(ma);
      if (_kh != null) break;
      System.out.println("Thêm khách hàng mới? (*. Không / 1. Phải): ");
      String ans = in.nextLine();
      if (ans.length() == 1 && ans.charAt(0) == '1') {
        _kh = new KhachHang();
        _kh.setMa(ma);
        _kh.input();
        dskh.add(_kh);
        break;
      }
      System.out.println("Lỗi!");
    }
    DanhSachSanPham dssp = QuanLyCuaHangMayTinh._dsSanPham;
    ChiTietHoaDon ct = new ChiTietHoaDon();
    ct.input();
    while (true) {
      System.out.println("Thêm chi tiết hoá đơn? (*. Không / 1. Phải): ");
      String ans = in.nextLine();
      if (ans.length() != 1 || ans.charAt(0) != '1') break;
      SanPham sp;
      while (true) {
        System.out.print("Nhập mã sản phẩm: ");
        String s = in.nextLine();
        sp = dssp.timTheoMa(s);
        if (!contain(s) && sp != null && sp.getSoLuong() > 0) break;
        System.out.println("Lỗi!");
      }
      ct = new ChiTietHoaDon();
      ct.setSanPham(sp);
      ct.input();
      add(ct);
    }
    _tongTien = 0;
    for (ChiTietHoaDon cti : _chiTiet)
      _tongTien += cti.getThanhTien();
  }
  public void output() {
    System.out.println("+----------------------------------- HÓA ĐƠN ----------------------------------+");
    System.out.printf("|  Mã hóa đơn: %s                                                              |\n", _ma);
    System.out.printf("|  Ngày lập: %s                                                                |\n", getChuoiNgayLap());
    System.out.printf("|  Nhân viên: %s %s                                                            |\n", _nv.getHo(), _nv.getTen());
    System.out.printf("|  Khách hàng: %s %s                                                           |\n", _kh.getHo(), _kh.getTen());
    System.out.printf("|  %-10s %-30s %-10s %-20s   |\n", "STT", "Tên sản phẩm", "Số lượng mua", "Thành tiền");
    for (int i = 0, n = _chiTiet.length ; i < n ; i++) {
      System.out.printf("|  %-10s ", i + 1);
      _chiTiet[i].output();
      System.out.println("   |");
    }
    System.out.printf("|  %73s   |\n", "Tổng tiền của hóa đơn: " + _tongTien + "VND");
    System.out.println("+------------------------------------------------------------------------------+");
  }
  public void edit() {
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    System.out.println(QuanLyCuaHangMayTinh.EDIT_NOTE);
    String s;
    while (true) {
      System.out.print("Nhập mã hoá đơn: ");
      if ((s = in.nextLine()).isBlank()) break;
      HoaDon hd = QuanLyCuaHangMayTinh._dsHoaDon.timTheoMa(s);
      if (hd == null) {
        _ma = s;
        break;
      }
      System.out.println("Lỗi!");
    }
    System.out.print("Nhập ngày lập: ");
    if (!(s = in.nextLine()).isBlank()) _ngayLap = DateUtil.parseDate(s);
    DanhSachNhanVien dsnv = QuanLyCuaHangMayTinh._dsNhanVien;
    System.out.println("Danh sách nhân viên: ");
    dsnv.output();
    while (true) {
      System.out.print("Nhập mã nhân viên: ");
      if ((s = in.nextLine()).isBlank()) break;
      NhanVien nv = dsnv.timTheoMa(s);
      if (nv != null) {
        _nv = nv;
        break;
      }
      System.out.println("Lỗi!");
    }
    DanhSachKhachHang dskh = QuanLyCuaHangMayTinh._dsKhachHang;
    System.out.println("Danh sách khách hàng: ");
    dskh.output();
    while (true) {
      System.out.print("Nhập mã khách hàng: ");
      if ((s = in.nextLine()).isBlank()) break;
      KhachHang kh = dskh.timTheoMa(s);
      if (kh != null) {
        _kh = kh;
        break;
      }
      System.out.println("Thêm khách hàng mới? (*. Không / 1. Phải): ");
      s = in.nextLine();
      if (s.length() == 1 && s.charAt(0) == '1') {
        _kh = new KhachHang();
        _kh.input();
        dskh.add(_kh);
        break;
      }
      System.out.println("Lỗi!");
    }
    DanhSachSanPham dssp = QuanLyCuaHangMayTinh._dsSanPham;
    while (true) {
      System.out.println("Thao tác chi tiết hoá đơn (*. Hoàn tất / 1. Thêm / 2. Sửa / 3. Xoá / 4. Xem): ");
      s = in.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') {
          SanPham sp;
          while (true) {
            System.out.print("Nhập mã sản phẩm: ");
            s = in.nextLine();
            sp = dssp.timTheoMa(s);
            if (!contain(s) && sp != null && sp.getSoLuong() > 0) break;
            System.out.println("Lỗi!");
          }
          ChiTietHoaDon ct = new ChiTietHoaDon();
          ct.setSanPham(sp);
          ct.input();
          add(ct);
          continue;
        } else if (c == '2') {
          while (true) {
            System.out.print("Nhập STT: ");
            try {
              int i = Integer.parseInt(in.nextLine()) - 1;
              ChiTietHoaDon ct = _chiTiet[i];
              ct.edit();
              String ma = ct.getSanPham().getMa();
              for (int j = 0, n = _chiTiet.length ; j < n ; j++) {
                if (i == j) continue;
                ChiTietHoaDon cti = _chiTiet[j];
                SanPham sp = cti.getSanPham();
                if (sp.getMa().equals(ma)) {
                  cti.setSoLuong(cti.getSoLuong() + ct.getSoLuong());
                  remove(i);
                }
              }
            } catch (Throwable e) {
              QuanLyCuaHangMayTinh.processingInternalThrowable(e);
              System.out.println("Lỗi!");
              continue;
            }
            break;
          }
          continue;
        } else if (c == '3') {
          while (true) {
            System.out.print("Nhập STT: ");
            try {
              int i = Integer.parseInt(in.nextLine()) - 1;
              ChiTietHoaDon ct = _chiTiet[i];
              if (_chiTiet.length > 1 && remove(i)) {
                SanPham sp = ct.getSanPham();
                sp.setSoLuong(sp.getSoLuong() + ct.getSoLuong());
                break;
              }
            } catch (Throwable e) { QuanLyCuaHangMayTinh.processingInternalThrowable(e); }
            System.out.println("Lỗi!");
          }
          continue;
        } else if (c == '4') {
          System.out.printf("%-10s %-30s %-10s %-20s\n", "STT", "Tên sản phẩm", "Số lượng mua", "Tổng tiền");
          for (int i = 0, n = _chiTiet.length ; i < n ; i++) {
            System.out.printf("%-10s ", i + 1);
            _chiTiet[i].output();
          }
          continue;
        }
      }
      break;
    }
    _tongTien = 0;
    for (ChiTietHoaDon cti : _chiTiet)
      _tongTien += cti.getThanhTien();
  }
  public void input(Scanner stream) {
    _ma = stream.nextLine();
    _ngayLap = DateUtil.parseDate(stream.nextLine());
    _nv = QuanLyCuaHangMayTinh._dsNhanVien.timTheoMa(stream.nextLine());
    _kh = QuanLyCuaHangMayTinh._dsKhachHang.timTheoMa(stream.nextLine());
    _tongTien = Long.parseLong(stream.nextLine());
  }
  public void output(OutputStreamWriter stream) {
    try {
      String nl = System.lineSeparator();
      stream.write(_ma);
      stream.write(nl);
      stream.write(DateUtil.toDateString(_ngayLap));
      stream.write(nl);
      stream.write(_nv.getMa());
      stream.write(nl);
      stream.write(_kh.getMa());
      stream.write(nl);
      stream.write(String.valueOf(_tongTien));
      stream.write(nl);
    } catch (Throwable e) { QuanLyCuaHangMayTinh.processingInternalThrowable(e); }
  }
}
