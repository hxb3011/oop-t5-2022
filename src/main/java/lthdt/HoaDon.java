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
    String s;
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    if (_ma.isEmpty()) {
      while (true) {
        System.out.print("Nh???p m?? ho?? ????n: ");
        if (Validator.validateID(s = in.nextLine())) {
          _ma = s;
          break;
        }
        System.out.println("L???i!");
      }
    }
    while (true) {
      System.out.print("Nh???p ng??y l???p: ");
      _ngayLap = DateUtil.parseDate(in.nextLine());
      if (_ngayLap != DateUtil.DATE_ERROR_DATE) break;
      System.out.println("L???i!");
    }
    DanhSachNhanVien dsnv = QuanLyCuaHangMayTinh._dsNhanVien;
    System.out.println("Danh s??ch nh??n vi??n:");
    dsnv.output();
    while (true) {
      System.out.print("Nh???p m?? nh??n vi??n: ");
      String ma = in.nextLine();
      if (!Validator.validateID(ma)) {
        System.out.println("L???i!");
        continue;
      }
      NhanVien nv = dsnv.timTheoMa(ma);
      if (nv != null) {
        _nv = nv;
        break;
      }
      System.out.println("L???i!");
    }
    DanhSachKhachHang dskh = QuanLyCuaHangMayTinh._dsKhachHang;
    System.out.println("Danh s??ch kh??ch h??ng: ");
    dskh.output();
    while (true) {
      System.out.print("Nh???p m?? kh??ch h??ng: ");
      String ma = in.nextLine();
      if (!Validator.validateID(ma)) {
        System.out.println("L???i!");
        continue;
      }
      _kh = dskh.timTheoMa(ma);
      if (_kh != null) break;
      System.out.print("Th??m kh??ch h??ng m???i? (*. Kh??ng / 1. Ph???i): ");
      String ans = in.nextLine();
      if (ans.length() == 1 && ans.charAt(0) == '1') {
        _kh = new KhachHang();
        _kh.setMa(ma);
        _kh.input();
        dskh.add(_kh);
        break;
      }
      System.out.println("L???i!");
    }
    DanhSachSanPham dssp = QuanLyCuaHangMayTinh._dsSanPham;
    dssp.output();
    ChiTietHoaDon ct = new ChiTietHoaDon();
    ct.input();
    add(ct);
    while (true) {
      System.out.println("Th??m chi ti???t ho?? ????n? (*. Kh??ng / 1. Ph???i): ");
      String ans = in.nextLine();
      if (ans.length() != 1 || ans.charAt(0) != '1') break;
      SanPham sp;
      while (true) {
        System.out.print("Nh???p m?? s???n ph???m: ");
        if (!Validator.validateID(s = in.nextLine())) {
          System.out.println("L???i!");
          continue;
        }
        if (contain(s)) {
          System.out.println("L???i!");
          continue;
        }
        sp = dssp.timTheoMa(s);
        if (sp != null && sp.getSoLuong() > 0) break;
        System.out.println("L???i!");
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
    System.out.println("+----------------------------------- H??A ????N ----------------------------------+");
    System.out.printf("|  M?? h??a ????n: %s\n", _ma);
    System.out.printf("|  Ng??y l???p: %s\n", getChuoiNgayLap());
    System.out.printf("|  Nh??n vi??n: %s %s\n", _nv.getHo(), _nv.getTen());
    System.out.printf("|  Kh??ch h??ng: %s %s\n", _kh.getHo(), _kh.getTen());
    System.out.printf("|  %-10s %-30s %-10s %-20s\n", "STT", "T??n s???n ph???m", "S??? l?????ng mua", "Th??nh ti???n");
    for (int i = 0, n = _chiTiet.length ; i < n ; i++) {
      System.out.printf("|  %-10s ", i + 1);
      _chiTiet[i].output();
    }
    System.out.printf("|  %73s\n", "T???ng ti???n c???a h??a ????n: " + _tongTien + "VND");
    System.out.println("+------------------------------------------------------------------------------+");
  }
  public void edit() {
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    System.out.println(QuanLyCuaHangMayTinh.EDIT_NOTE);
    String s;
    while (true) {
      System.out.print("Nh???p m?? ho?? ????n: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (!Validator.validateID(s)) {
        System.out.println("L???i!");
        continue;
      }
      HoaDon hd = QuanLyCuaHangMayTinh._dsHoaDon.timTheoMa(s);
      if (hd == null) {
        _ma = s;
        break;
      }
      System.out.println("L???i!");
    }
    while (true) {
      System.out.print("Nh???p ng??y l???p: ");
      if ((s = in.nextLine()).isEmpty()) break;
      _ngayLap = DateUtil.parseDate(s);
      if (_ngayLap != DateUtil.DATE_ERROR_DATE) break;
      System.out.println("L???i!");
    }
    DanhSachNhanVien dsnv = QuanLyCuaHangMayTinh._dsNhanVien;
    System.out.println("Danh s??ch nh??n vi??n: ");
    dsnv.output();
    while (true) {
      System.out.print("Nh???p m?? nh??n vi??n: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (!Validator.validateID(s)) {
        System.out.println("L???i!");
        continue;
      }
      NhanVien nv = dsnv.timTheoMa(s);
      if (nv != null) {
        _nv = nv;
        break;
      }
      System.out.println("L???i!");
    }
    DanhSachKhachHang dskh = QuanLyCuaHangMayTinh._dsKhachHang;
    System.out.println("Danh s??ch kh??ch h??ng: ");
    dskh.output();
    while (true) {
      System.out.print("Nh???p m?? kh??ch h??ng: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (!Validator.validateID(s)) {
        System.out.println("L???i!");
        continue;
      }
      KhachHang kh = dskh.timTheoMa(s);
      if (kh != null) {
        _kh = kh;
        break;
      }
      System.out.println("Th??m kh??ch h??ng m???i? (*. Kh??ng / 1. Ph???i): ");
      s = in.nextLine();
      if (s.length() == 1 && s.charAt(0) == '1') {
        _kh = new KhachHang();
        _kh.input();
        dskh.add(_kh);
        break;
      }
      System.out.println("L???i!");
    }
    DanhSachSanPham dssp = QuanLyCuaHangMayTinh._dsSanPham;
    while (true) {
      System.out.println("Thao t??c chi ti???t ho?? ????n (*. Ho??n t???t / 1. Th??m / 2. S???a / 3. Xo?? / 4. Xem): ");
      s = in.nextLine();
      if (s.length() == 1) {
        char c = s.charAt(0);
        if (c == '1') {
          SanPham sp;
          while (true) {
            System.out.print("Nh???p m?? s???n ph???m: ");
            if (!Validator.validateID(s = in.nextLine())) {
              System.out.println("L???i!");
              continue;
            }
            if (contain(s)) {
              System.out.println("L???i!");
              continue;
            }
            sp = dssp.timTheoMa(s);
            if (sp != null && sp.getSoLuong() > 0) break;
            System.out.println("L???i!");
          }
          ChiTietHoaDon ct = new ChiTietHoaDon();
          ct.setSanPham(sp);
          ct.input();
          add(ct);
          continue;
        } else if (c == '2') {
          while (true) {
            System.out.print("Nh???p STT: ");
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
              System.out.println("L???i!");
              continue;
            }
            break;
          }
          continue;
        } else if (c == '3') {
          while (true) {
            System.out.print("Nh???p STT: ");
            try {
              int i = Integer.parseInt(in.nextLine()) - 1;
              ChiTietHoaDon ct = _chiTiet[i];
              if (_chiTiet.length > 1 && remove(i)) {
                SanPham sp = ct.getSanPham();
                sp.setSoLuong(sp.getSoLuong() + ct.getSoLuong());
                break;
              }
            } catch (Throwable e) { QuanLyCuaHangMayTinh.processingInternalThrowable(e); }
            System.out.println("L???i!");
          }
          continue;
        } else if (c == '4') {
          System.out.printf("%-10s %-30s %-10s %-20s\n", "STT", "T??n s???n ph???m", "S??? l?????ng mua", "T???ng ti???n");
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
