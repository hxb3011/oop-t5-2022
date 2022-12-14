package lthdt;

import java.io.OutputStreamWriter;
import java.util.*;

@SuppressWarnings("unused")
public class PhieuNhapHang implements IConsoleIO, IConsoleEditable, IStreamIO {
  private String _ma;
  private int _ngayLap;
  private NhanVien _nv;
  private NhaCungCap _ncc;
  private ChiTietPhieuNhapHang[] _chiTiet;
  private long _tongTien;
  public PhieuNhapHang(String ma, int ngayLap, NhanVien nv, NhaCungCap ncc, ChiTietPhieuNhapHang[] chiTiet, long tongTien) {
    _ma = ma;
    _ngayLap = ngayLap;
    _nv = nv;
    _ncc = ncc;
    _chiTiet = chiTiet;
    _tongTien = tongTien;
  }
  public PhieuNhapHang(String ma, String ngayLap, NhanVien nv, NhaCungCap ncc, ChiTietPhieuNhapHang[] chiTiet, long tongTien) {
    this(ma, DateUtil.parseDate(ngayLap), nv, ncc, chiTiet, tongTien);
  }
  public PhieuNhapHang() { this("", "", new NhanVien(), new NhaCungCap(), new ChiTietPhieuNhapHang[0], 0L); }
  public PhieuNhapHang(PhieuNhapHang pnh) { this(pnh._ma, pnh._ngayLap, pnh._nv, pnh._ncc, pnh._chiTiet.clone(), pnh._tongTien); }
  public String getMa() { return _ma; }
  public void setMa(String ma) { _ma = ma; }
  public int getNgayLap() { return _ngayLap; }
  public void setNgayLap(int ngayLap) { _ngayLap = ngayLap; }
  public String getChuoiNgayLap() { return DateUtil.toDateString(_ngayLap); }
  public void setChuoiNgayLap(String ngayLap) { _ngayLap = DateUtil.parseDate(ngayLap); }
  public NhanVien getNhanVien() { return _nv; }
  public void setNhanVien(NhanVien nv) { _nv = nv; }
  public NhaCungCap getNhaCungCap() { return _ncc; }
  public void setNhaCungCap(NhaCungCap ncc) { _ncc = ncc; }
  public ChiTietPhieuNhapHang[] getChiTiet() { return _chiTiet; }
  public void setChiTiet(ChiTietPhieuNhapHang[] chiTiet) { _chiTiet = chiTiet; }
  public long getTongTien() { return _tongTien; }
  public void setTongTien(long tongTien) { _tongTien = tongTien; }
  public boolean contain(String maSP) {
    for (ChiTietPhieuNhapHang cti : _chiTiet)
      if (cti.getSanPham().getMa().equals(maSP))
        return true;
    return false;
  }
  public boolean add(ChiTietPhieuNhapHang ncc) {
    int lastIndex = _chiTiet.length;
    ChiTietPhieuNhapHang[] arr = Arrays.copyOf(_chiTiet, lastIndex + 1);
    arr[lastIndex] = ncc;
    _chiTiet = arr;
    return true;
  }
  public boolean remove(int index) {
    int oldLength = _chiTiet.length;
    if (oldLength == 0) return false;
    ChiTietPhieuNhapHang[] arr = new ChiTietPhieuNhapHang[oldLength - 1];
    System.arraycopy(_chiTiet, 0, arr, 0, index);
    System.arraycopy(_chiTiet, index + 1, arr, index, oldLength - 1 - index);
    _chiTiet = arr;
    return true;
  }
  public void input() {
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    if (_ma.isEmpty()) {
      while (true) {
        System.out.print("Nh???p m?? phi???u nh???p h??ng: ");
        String s = in.nextLine();
        if (Validator.validateID(s)) {
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
    System.out.println("Danh s??ch nh??n vi??n: ");
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
    DanhSachNhaCungCap dsncc = QuanLyCuaHangMayTinh._dsNhaCungCap;
    System.out.println("Danh s??ch nh?? cung c???p: ");
    dsncc.output();
    while (true) {
      System.out.print("Nh???p m?? nh?? cung c???p: ");
      String ma = in.nextLine();
      _ncc = dsncc.timTheoMa(ma);
      if (!Validator.validateID(ma)) {
        System.out.println("L???i!");
        continue;
      }
      if (_ncc != null) break;
      System.out.print("Th??m nh?? cung c???p m???i? (*. Kh??ng / 1. Ph???i): ");
      String ans = in.nextLine();
      if (ans.length() == 1 && ans.charAt(0) == '1') {
        _ncc = new NhaCungCap();
        _ncc.setMa(ma);
        _ncc.input();
        dsncc.add(_ncc);
        break;
      }
      System.out.println("L???i!");
    }
    DanhSachSanPham dssp = QuanLyCuaHangMayTinh._dsSanPham;
    dssp.output();
    ChiTietPhieuNhapHang ct = new ChiTietPhieuNhapHang();
    ct.input();
    add(ct);
    while (true) {
      System.out.print("Th??m chi ti???t phi???u nh???p h??ng? (*. Kh??ng / 1. Ph???i): ");
      String ans = in.nextLine();
      if (ans.length() != 1 || ans.charAt(0) != '1') break;
      SanPham sp;
      while (true) {
        System.out.print("Nh???p m?? s???n ph???m: ");
        String s = in.nextLine();
        if (!Validator.validateID(s)) {
          System.out.println("L???i!");
          continue;
        }
        if (contain(s)) {
          System.out.println("L???i!");
          continue;
        }
        sp = dssp.timTheoMa(s);
        if (sp == null) {
          System.out.print("Th??m s???n ph???m m???i? (*. Kh??ng / 1. Ph???i): ");
          ans = in.nextLine();
          if (ans.length() != 1 || ans.charAt(0) != '1') {
            System.out.println("L???i!");
            continue;
          }
          while (true) {
            System.out.print("Nh???p lo???i s???n ph???m c???n th??m (1. Laptop, 2. M??y T??nh ????? B??n): ");
            String type = in.nextLine();
            if (type.length() == 1) {
              char c = type.charAt(0);
              if (c == '1') {
                sp = new Laptop();
                break;
              } else if (c == '2') {
                sp = new MayTinhBan();
                break;
              }
            }
            System.out.println("L???i!");
          }
          sp.setMa(s);
          sp.input();
          dssp.add(sp);
        }
        break;
      }
      ct = new ChiTietPhieuNhapHang();
      ct.setSanPham(sp);
      ct.input();
      add(ct);
    }
    _tongTien = 0;
    for (ChiTietPhieuNhapHang cti : _chiTiet)
      _tongTien += cti.getThanhTien();
  }
  public void output() {
    System.out.println("+------------------------------- PHI???U NH???P H??NG ------------------------------+");
    System.out.printf("|  M?? phi???u nh???p h??ng: %s\n", _ma);
    System.out.printf("|  Ng??y l???p: %s\n", getChuoiNgayLap());
    System.out.printf("|  Nh??n vi??n: %s %s\n", _nv.getHo(), _nv.getTen());
    System.out.printf("|  Nh?? cung c???p: %s\n", _ncc.getTen());
    System.out.printf("|  %-10s %-30s %-10s %-20s\n", "STT", "T??n s???n ph???m", "S??? l?????ng", "Th??nh ti???n");
    for (int i = 0, n = _chiTiet.length ; i < n ; i++) {
      System.out.printf("|  %-10s ", i + 1);
      _chiTiet[i].output();
    }
    System.out.printf("|  %73s\n", "T???ng ti???n c???a phi???u nh???p h??ng: " + _tongTien + "VND");
    System.out.println("+------------------------------------------------------------------------------+");
  }
  public void edit() {
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    System.out.println(QuanLyCuaHangMayTinh.EDIT_NOTE);
    String s;
    while (true) {
      System.out.print("Nh???p m?? phi???u nh???p h??ng: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (!Validator.validateID(s)) {
        System.out.println("L???i!");
        continue;
      }
      PhieuNhapHang pnh = QuanLyCuaHangMayTinh._dsPhieuNhapHang.timTheoMa(s);
      if (pnh == null) {
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
    DanhSachNhaCungCap dsncc = QuanLyCuaHangMayTinh._dsNhaCungCap;
    System.out.println("Danh s??ch nh?? cung c???p: ");
    dsncc.output();
    while (true) {
      System.out.print("Nh???p m?? nh?? cung c???p: ");
      if ((s = in.nextLine()).isEmpty()) break;
      if (!Validator.validateID(s)) {
        System.out.println("L???i!");
        continue;
      }
      NhaCungCap ncc = dsncc.timTheoMa(s);
      if (ncc != null) {
        _ncc = ncc;
        break;
      }
      System.out.print("Th??m nh?? cung c???p m???i? (*. Kh??ng / 1. Ph???i): ");
      s = in.nextLine();
      if (s.length() == 1 && s.charAt(0) == '1') {
        _ncc = new NhaCungCap();
        _ncc.input();
        dsncc.add(_ncc);
        break;
      }
      System.out.println("L???i!");
    }
    DanhSachSanPham dssp = QuanLyCuaHangMayTinh._dsSanPham;
    while (true) {
      System.out.print("Thao t??c chi ti???t phi???u nh???p h??ng (*. Ho??n t???t / 1. Th??m / 2. S???a / 3. Xo?? / 4. Xem): ");
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
            if (sp == null) {
              System.out.print("Th??m s???n ph???m m???i? (*. Kh??ng / 1. Ph???i): ");
              String ans = in.nextLine();
              if (ans.length() != 1 || ans.charAt(0) != '1') {
                System.out.println("L???i!");
                continue;
              }
              while (true) {
                System.out.println("Nh???p lo???i s???n ph???m c???n th??m (1. Laptop, 2. M??y T??nh ????? B??n): ");
                String type = in.nextLine();
                if (type.length() == 1) {
                  c = type.charAt(0);
                  if (c == '1') {
                    sp = new Laptop();
                    break;
                  } else if (c == '2') {
                    sp = new MayTinhBan();
                    break;
                  }
                }
                System.out.println("L???i!");
              }
              sp.setMa(s);
              sp.input();
              dssp.add(sp);
            }
            break;
          }
          ChiTietPhieuNhapHang ct = new ChiTietPhieuNhapHang();
          ct.setSanPham(sp);
          ct.input();
          add(ct);
          continue;
        } else if (c == '2') {
          while (true) {
            System.out.print("Nh???p STT: ");
            try {
              int i = Integer.parseInt(in.nextLine()) - 1;
              ChiTietPhieuNhapHang ct = _chiTiet[i];
              ct.edit();
              String ma = ct.getSanPham().getMa();
              for (int j = 0, n = _chiTiet.length ; j < n ; j++) {
                if (i == j) continue;
                ChiTietPhieuNhapHang cti = _chiTiet[j];
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
              ChiTietPhieuNhapHang ct = _chiTiet[i];
              if (_chiTiet.length > 1 && remove(i)) {
                SanPham sp = ct.getSanPham();
                sp.setSoLuong(sp.getSoLuong() - ct.getSoLuong());
                break;
              }
            } catch (Throwable e) { QuanLyCuaHangMayTinh.processingInternalThrowable(e); }
            System.out.println("L???i!");
          }
          continue;
        } else if (c == '4') {
          System.out.printf("%-10s %-30s %-10s %-20s\n", "STT", "T??n s???n ph???m", "S??? l?????ng nh???p", "T???ng ti???n");
          for (int i = 0, chiTietLength = _chiTiet.length ; i < chiTietLength ; i++) {
            System.out.printf("%-10s ", i + 1);
            _chiTiet[i].output();
          }
          continue;
        }
      }
      break;
    }
    _tongTien = 0;
    for (ChiTietPhieuNhapHang cti : _chiTiet)
      _tongTien += cti.getThanhTien();
  }
  public void input(Scanner stream) {
    _ma = stream.nextLine();
    _ngayLap = DateUtil.parseDate(stream.nextLine());
    _nv = QuanLyCuaHangMayTinh._dsNhanVien.timTheoMa(stream.nextLine());
    _ncc = QuanLyCuaHangMayTinh._dsNhaCungCap.timTheoMa(stream.nextLine());
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
      stream.write(_ncc.getMa());
      stream.write(nl);
      stream.write(String.valueOf(_tongTien));
      stream.write(nl);
    } catch (Throwable e) { QuanLyCuaHangMayTinh.processingInternalThrowable(e); }
  }
}
