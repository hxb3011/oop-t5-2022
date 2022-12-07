package lthdt;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class DanhSachHoaDon implements IListConsoleIO, IListFileIO {
  private int _soLuong;
  private HoaDon[] _array;
  public DanhSachHoaDon(HoaDon[] array, int soLuong) {
    _soLuong = soLuong;
    _array = array.clone();
  }
  public DanhSachHoaDon() { this(new HoaDon[0], 0); }
  public int soLuong() { return _soLuong; }
  public HoaDon get(int index) { return (index < 0 || index >= _soLuong) ? null : _array[index]; }
  public void set(int index, HoaDon hd) { if (index >= 0 && index < _soLuong) _array[index] = hd; }
  public HoaDon timTheoMa(String ma) {
    for (int i = 0, n = _soLuong ; i < n ; ++i)
      if (_array[i].getMa().equals(ma)) return _array[i];
    return null;
  }
  public HoaDon[] timTheoNgayLap(String ngayLap) {
    ngayLap = ngayLap.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] hd = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getChuoiNgayLap().toLowerCase().equals(ngayLap)) hd[k++] = _array[i];
    return Arrays.copyOf(hd, k);
  }
  public HoaDon[] timTheoNgayLap(int ngay, int thang, int nam) {
    int ntn = DateUtil.getDate(ngay, thang, nam);
    int n = _soLuong, k = 0;
    HoaDon[] hd = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNgayLap() == ntn) hd[k++] = _array[i];
    return Arrays.copyOf(hd, k);
  }
  public HoaDon[] timTheoMaNhanVien(String ma) {
    ma = ma.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] hd = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getMa().toLowerCase().equals(ma)) hd[k++] = _array[i];
    return Arrays.copyOf(hd, k);
  }
  public HoaDon[] timTheoHoNhanVien(String ho) {
    ho = ho.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] hd = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getHo().toLowerCase().indexOf(ho) >= 0) hd[k++] = _array[i];
    return Arrays.copyOf(hd, k);
  }
  public HoaDon[] timTheoTenNhanVien(String ten) {
    ten = ten.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] hd = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getTen().toLowerCase().indexOf(ten) >= 0) hd[k++] = _array[i];
    return Arrays.copyOf(hd, k);
  }
  public HoaDon[] timTheoHoTenNhanVien(String hoTen) {
    hoTen = hoTen.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] hd = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getHoTen().toLowerCase().indexOf(hoTen) >= 0) hd[k++] = _array[i];
    return Arrays.copyOf(hd, k);
  }
  public HoaDon[] timTheoMaKhachHang(String ma) {
    ma = ma.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] hd = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getMa().toLowerCase().equals(ma)) hd[k++] = _array[i];
    return Arrays.copyOf(hd, k);
  }
  public HoaDon[] timTheoHoKhachHang(String ho) {
    ho = ho.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] hd = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getHo().toLowerCase().indexOf(ho) >= 0) hd[k++] = _array[i];
    return Arrays.copyOf(hd, k);
  }
  public HoaDon[] timTheoTenKhachHang(String ten) {
    ten = ten.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] hd = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getTen().toLowerCase().indexOf(ten) >= 0) hd[k++] = _array[i];
    return Arrays.copyOf(hd, k);
  }
  public HoaDon[] timTheoHoTenKhachHang(String hoTen) {
    hoTen = hoTen.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] hd = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getHoTen().toLowerCase().indexOf(hoTen) >= 0) hd[k++] = _array[i];
    return Arrays.copyOf(hd, k);
  }
  public HoaDon[] timTheoMaSanPham(String ma) {
    int n = _soLuong, k = 0;
    HoaDon[] hd = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i) {
      ChiTietHoaDon[] ct = hd[i].getChiTiet();
      for (ChiTietHoaDon chiTietHoaDon : ct) {
        if (chiTietHoaDon.getSanPham().getMa().equals(ma)) {
          hd[k++] = _array[i];
          break;
        }
      }
    }
    return Arrays.copyOf(hd, k);
  }
  public HoaDon[] timTheoTenSanPham(String ten) {
    int n = _soLuong, k = 0;
    HoaDon[] hd = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i) {
      ChiTietHoaDon[] ct = hd[i].getChiTiet();
      for (ChiTietHoaDon chiTietHoaDon : ct) {
        if (chiTietHoaDon.getSanPham().getTen().indexOf(ten) >= 0) {
          hd[k++] = _array[i];
          break;
        }
      }
    }
    return Arrays.copyOf(hd, k);
  }
  public HoaDon[] timTheoTongTien(long tongTien) {
    int n = _soLuong, k = 0;
    HoaDon[] hd = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getTongTien() == tongTien) hd[k++] = _array[i];
    return Arrays.copyOf(hd, k);
  }
  public HoaDon[] timTheoKhoangTongTien(long tongTienNho, long tongTienLon) {
    int n = _soLuong, k = 0;
    HoaDon[] hd = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i) {
      long tongTien = _array[i].getTongTien();
      if (tongTien >= tongTienNho && tongTien <= tongTienLon) hd[k++] = _array[i];
    }
    return Arrays.copyOf(hd, k);
  }
  public boolean add(int index, HoaDon hd) {
    if (timTheoMa(hd.getMa()) != null) return false;

    int newLength = _soLuong + 1;
    _soLuong = newLength;

    HoaDon[] arr = new HoaDon[newLength];
    int i = 0;
    for ( ; i < index ; i++) arr[i] = _array[i];
    arr[i] = hd;
    for (int j = i++ ; i < newLength ; j = i++)
      arr[i] = _array[j];
    _array = arr;
    return true;
  }
  public boolean add(HoaDon hd) { return add(_soLuong, hd); }
  public boolean remove(int index) {
    int newLength = _soLuong;
    if (index < 0 || index >= newLength) return false;
    if (!tryRemoveConstraint(_array[index]))
      return false;
    _soLuong = --newLength;

    HoaDon[] arr = new HoaDon[newLength];
    int i = 0;
    for ( ; i < index ; i++) arr[i] = _array[i];
    for (int j = i++ ; i < newLength ; j = i++)
      arr[j] = _array[i];
    _array = arr;
    return true;
  }
  public boolean remove(HoaDon hd) {
    int newLength = _soLuong;
    hd = timTheoMa(hd.getMa());
    if (hd == null) return false;
    if (!tryRemoveConstraint(hd))
      return false;
    _soLuong = --newLength;

    HoaDon[] arr = new HoaDon[newLength];
    int i = 0;
    for ( ; hd.getMa().equals(arr[i].getMa()) ; i++) arr[i] = _array[i];
    for (int j = i++ ; i < newLength ; j = i++)
      arr[j] = _array[i];
    _array = arr;
    return true;
  }
  private boolean tryRemoveConstraint(HoaDon hd) {
    ChiTietHoaDon[] ct = hd.getChiTiet();
    for (ChiTietHoaDon cti : ct) {
      SanPham sp = cti.getSanPham();
      sp.setSoLuong(sp.getSoLuong() + cti.getSoLuong());
    }
    KhachHang kh = hd.getKhachHang();
    if (timTheoMaKhachHang(kh.getMa()).length <= 1) {
      QuanLyCuaHangMayTinh._dsKhachHang.remove(kh);
    }
    return true;
  }
  public boolean daCo(String maHD) { return timTheoMa(maHD) != null; }
  public boolean daCo(HoaDon hd) { return daCo(hd.getMa()); }
  public boolean xoaTheoMa(String ma) {
    HoaDon hd = timTheoMa(ma);
    if (hd == null) return false;
    return remove(hd);
  }
  public void thongKeTongTienBanTheoKhachHang() {
    DanhSachKhachHang dskh = QuanLyCuaHangMayTinh._dsKhachHang;
    if (dskh.soLuong() <= 0) {
      System.out.println("Không có dữ liệu để thống kê.");
      return;
    }
    long[] tongTienBan = new long[dskh.soLuong()];
    for (int i = 0 ; i < _soLuong ; i++) {
      for (int j = 0 ; j < dskh.soLuong() ; j++) {
        if (dskh.get(j) == _array[i].getKhachHang()) {
          tongTienBan[j] += _array[i].getTongTien();
          break;
        }
      }
    }
    long tong = 0, tong2 = 0;
    System.out.println("Tổng tiền bán hàng theo khách hàng: ");
    for (int i = 0 ; i < tongTienBan.length ; i++) {
      long tban = tongTienBan[i];
      System.out.println(dskh.get(i).getHoTen() + " : " + tban + "VND");
      tong += tban;
      tong2 += tban * tban;
    }
    System.out.println("Tổng tiền bán hàng của cửa hàng: " + tong + "VND");
    double kyVong = ((double) tong) / tongTienBan.length;
    System.out.println("Kỳ vọng của tổng tiền bán hàng theo khách hàng: " + kyVong + "VND");
    System.out.println("Độ lệch chuẩn của tổng tiền bán hàng theo khách hàng: "
        + Math.sqrt((((double) tong2) / tongTienBan.length) - (kyVong * kyVong)) + "VND");
  }
  public void thongKeTongTienBanTheoNhanVien() {
    DanhSachNhanVien dsnv = QuanLyCuaHangMayTinh._dsNhanVien;
    if (dsnv.soLuong() <= 0) {
      System.out.println("Không có dữ liệu để thống kê.");
      return;
    }
    long[] tongTienBan = new long[dsnv.soLuong()];
    for (int i = 0 ; i < _soLuong ; i++) {
      for (int j = 0 ; j < dsnv.soLuong() ; j++) {
        if (dsnv.get(j) == _array[i].getNhanVien()) {
          tongTienBan[j] += _array[i].getTongTien();
          break;
        }
      }
    }
    long tong = 0, tong2 = 0;
    System.out.println("Tổng tiền bán hàng theo nhân viên: ");
    for (int i = 0 ; i < tongTienBan.length ; i++) {
      long tban = tongTienBan[i];
      System.out.println(dsnv.get(i).getHoTen() + " : " + tban + "VND");
      tong += tban;
      tong2 += tban * tban;
    }
    System.out.println("Tổng tiền bán hàng của cửa hàng: " + tong + "VND");
    double kyVong = ((double) tong) / tongTienBan.length;
    System.out.println("Kỳ vọng của tổng tiền bán hàng theo nhân viên: " + kyVong + "VND");
    System.out.println("Độ lệch chuẩn của tổng tiền bán hàng theo nhân viên: "
        + Math.sqrt((((double) tong2) / tongTienBan.length) - (kyVong * kyVong)) + "VND");
  }
  public void thongKeTongTienBanTheoSanPham() {
    DanhSachSanPham dssp = QuanLyCuaHangMayTinh._dsSanPham;
    if (dssp.soLuong() <= 0) {
      System.out.println("Không có dữ liệu để thống kê.");
      return;
    }
    long[] tongTienBan = new long[dssp.soLuong()];
    long[] soLuongBan = new long[dssp.soLuong()];
    for (int i = 0 ; i < _soLuong ; i++) {
      ChiTietHoaDon[] ct = _array[i].getChiTiet();
      for (ChiTietHoaDon cti : ct) {
        for (int j = 0 ; j < dssp.soLuong() ; j++) {
          if (dssp.get(j) == cti.getSanPham()) {
            tongTienBan[j] += cti.getThanhTien();
            soLuongBan[j] += cti.getSoLuong();
            break;
          }
        }
      }
    }
    long tong = 0, tong2 = 0, soLuong = 0;
    System.out.println("Số lượng, tổng tiền bán hàng theo sản phẩm: ");
    for (int i = 0 ; i < tongTienBan.length ; i++) {
      long tban = tongTienBan[i];
      long sban = soLuongBan[i];
      System.out.println(dssp.get(i).getTen() + " : " + sban + " : " + tban + "VND");
      tong += tban;
      tong2 += tban * tban;
      soLuong += sban;
    }
    System.out.println("Tổng tiền bán hàng của cửa hàng: " + tong + "VND");
    double kyVong = ((double) tong) / soLuong;
    System.out.println("Kỳ vọng của tổng tiền bán hàng theo sản phẩm: " + kyVong + "VND");
    System.out.println("Độ lệch chuẩn của tổng tiền bán hàng theo sản phẩm: "
        + Math.sqrt((((double) tong2) / soLuong) - (kyVong * kyVong)) + "VND");
  }
  public void readFile() {
    Scanner base = null, spec = null;
    try {
      base = new Scanner(new FileInputStream(QuanLyCuaHangMayTinh._databasePath + "HoaDon.txt"));
      spec = new Scanner(new FileInputStream(QuanLyCuaHangMayTinh._databasePath + "ChiTietHoaDon.txt"));
      String sMa = null;
      while (base.hasNextLine()) {
        HoaDon hd = new HoaDon();
        hd.input();
        String ma = hd.getMa();
        add(hd);
        while (spec.hasNextLine()) {
          if (sMa == null) sMa = spec.nextLine();
          if (ma.equals(sMa)) {
            ChiTietHoaDon ct = new ChiTietHoaDon();
            ct.input(spec);
            hd.add(ct);
            sMa = null;
          } else break;
        }
        if (hd.getChiTiet().length == 0) {
          System.out.println("File bị lỗi!");
          break;
        }
      }
      base.close();
      spec.close();
    } catch (Throwable e) {
      if (base != null) try { base.close(); } catch (Throwable ce) { e.addSuppressed(ce); }
      if (spec != null) try { spec.close(); } catch (Throwable ce) { e.addSuppressed(ce); }
      QuanLyCuaHangMayTinh.processingInternalThrowable(e);
      System.out.println("Không đọc được file!");
    }
  }
  public void writeFile() {
    OutputStreamWriter base = null, spec = null;
    try {
      base = new OutputStreamWriter(new FileOutputStream(QuanLyCuaHangMayTinh._databasePath + "HoaDon.txt"));
      spec = new OutputStreamWriter(new FileOutputStream(QuanLyCuaHangMayTinh._databasePath + "ChiTietHoaDon.txt"));
      for (int i = 0 ; i < _soLuong ; i++) {
        HoaDon hd = _array[i];
        hd.output(base);
        ChiTietHoaDon[] ct = hd.getChiTiet();
        for (ChiTietHoaDon cti : ct) {
          spec.write(hd.getMa());
          spec.write(System.lineSeparator());
          cti.output(spec);
        }
      }
      base.close();
      spec.close();
    } catch (Throwable e) {
      if (base != null) try { base.close(); } catch (Throwable ce) { e.addSuppressed(ce); }
      if (spec != null) try { spec.close(); } catch (Throwable ce) { e.addSuppressed(ce); }
      QuanLyCuaHangMayTinh.processingInternalThrowable(e);
      System.out.println("Không ghi được file!");
    }
  }
  public void input(int n) {
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    int newLength = _soLuong + n;
    HoaDon[] temp = new HoaDon[newLength];
    for (int i = _soLuong ; i < newLength ; i++) {
      String ma;
      check:
      while (true) {
        System.out.print("Nhập mã hoá đơn: ");
        ma = in.nextLine();
        for (int j = 0 ; j < i ; ++j) {
          if (ma.equals(temp[i].getMa())) {
            System.out.println("Lỗi!");
            continue check;
          }
        }
        if (!daCo(ma)) break;
        System.out.println("Lỗi!");
      }
      HoaDon hd = new HoaDon();
      hd.setMa(ma);
      hd.input();
      temp[i] = hd;
    }
    System.arraycopy(_array, 0, temp, 0, _soLuong);
    _soLuong = newLength;
    _array = temp;
  }
  public void output() {
    System.out.printf("%-10s %-30s %-30s %-20s\n", "Mã", "Tên nhân viên", "Tên khách hàng", "Tổng tiền");
    for (int i = 0 ; i < _soLuong ; ++i) {
      HoaDon hd = _array[i];
      System.out.printf("%-10s %-30s %-30s %-20s\n", hd.getMa(), hd.getNhanVien().getHoTen(), hd.getKhachHang().getHoTen(), hd.getTongTien());
    }
    while (true) {
      System.out.print("\nNhập mã hoá đơn để xem chi tiết (Bỏ qua để thoát): ");
      String s = QuanLyCuaHangMayTinh.STANDARD_IN.nextLine();
      if (s.isEmpty()) break;
      if (!Validator.validateID(s)) {
        System.out.println("Lỗi!");
        continue;
      }
      HoaDon hd = QuanLyCuaHangMayTinh._dsHoaDon.timTheoMa(s);
      if (hd != null) hd.output();
      else System.out.println("Lỗi!");
    }
  }
}
