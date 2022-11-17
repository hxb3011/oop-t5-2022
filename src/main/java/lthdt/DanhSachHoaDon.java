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
  public void set(int index, HoaDon sp) { if (index >= 0 && index < _soLuong) _array[index] = sp; }
  public HoaDon timTheoMa(String ma) {
    for (int i = 0, n = _soLuong ; i < n ; ++i)
      if (_array[i].getMa().equals(ma)) return _array[i];
    return null;
  }
  public HoaDon[] timTheoNgayLap(String ngayLap) {
    ngayLap = ngayLap.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] sp = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNgayLap().toLowerCase().equals(ngayLap)) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public HoaDon[] timTheoMaNhanVien(String ma) {
    ma = ma.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] sp = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getMa().toLowerCase().equals(ma)) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public HoaDon[] timTheoHoNhanVien(String ho) {
    ho = ho.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] sp = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getHo().toLowerCase().indexOf(ho) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public HoaDon[] timTheoTenNhanVien(String ten) {
    ten = ten.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] sp = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getTen().toLowerCase().indexOf(ten) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public HoaDon[] timTheoHoTenNhanVien(String hoTen) {
    hoTen = hoTen.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] sp = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getHoTen().toLowerCase().indexOf(hoTen) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public HoaDon[] timTheoMaKhachHang(String ma) {
    ma = ma.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] sp = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getMa().toLowerCase().equals(ma)) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public HoaDon[] timTheoHoKhachHang(String ho) {
    ho = ho.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] sp = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getHo().toLowerCase().indexOf(ho) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public HoaDon[] timTheoTenKhachHang(String ten) {
    ten = ten.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] sp = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getTen().toLowerCase().indexOf(ten) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public HoaDon[] timTheoHoTenKhachHang(String hoTen) {
    hoTen = hoTen.toLowerCase();
    int n = _soLuong, k = 0;
    HoaDon[] sp = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getHoTen().toLowerCase().indexOf(hoTen) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public HoaDon[] timTheoMaSanPham(String ma) {
    int n = _soLuong, k = 0;
    HoaDon[] sp = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i) {
      ChiTietHoaDon[] ct = sp[i].getChiTiet();
      for (ChiTietHoaDon chiTietHoaDon : ct) {
        if (chiTietHoaDon.getSanPham().getMa().equals(ma)) {
          sp[k++] = _array[i];
          break;
        }
      }
    }
    return Arrays.copyOf(sp, k);
  }
  public HoaDon[] timTheoTenSanPham(String ten) {
    int n = _soLuong, k = 0;
    HoaDon[] sp = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i) {
      ChiTietHoaDon[] ct = sp[i].getChiTiet();
      for (ChiTietHoaDon chiTietHoaDon : ct) {
        if (chiTietHoaDon.getSanPham().getTen().indexOf(ten) >= 0) {
          sp[k++] = _array[i];
          break;
        }
      }
    }
    return Arrays.copyOf(sp, k);
  }
  public HoaDon[] timTheoTongTien(long tongTien) {
    int n = _soLuong, k = 0;
    HoaDon[] sp = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getTongTien() == tongTien) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public HoaDon[] timTheoKhoangTongTien(long tongTienNho, long tongTienLon) {
    int n = _soLuong, k = 0;
    HoaDon[] sp = new HoaDon[n];
    for (int i = 0 ; i < n ; ++i) {
      long tongTien = _array[i].getTongTien();
      if (tongTien >= tongTienNho && tongTien <= tongTienLon) sp[k++] = _array[i];
    }
    return Arrays.copyOf(sp, k);
  }
  public boolean add(int index, HoaDon sp) {
    if (timTheoMa(sp.getMa()) != null) return false;

    int newLength = _soLuong + 1;
    _soLuong = newLength;

    HoaDon[] arr = new HoaDon[newLength];
    int i = 0;
    for ( ; i < index ; i++) arr[i] = _array[i];
    arr[i] = sp;
    for (int j = i++ ; i < newLength ; j = i++)
      arr[i] = _array[j];
    _array = arr;
    return true;
  }
  public boolean add(HoaDon sp) { return add(_soLuong, sp); }
  public boolean remove(int index) {
    int newLength = _soLuong;
    if (index < 0 || index >= newLength) return false;
    _soLuong = --newLength;

    HoaDon[] arr = new HoaDon[newLength];
    int i = 0;
    for ( ; i < index ; i++) arr[i] = _array[i];
    for (int j = i++ ; i < newLength ; j = i++)
      arr[j] = _array[i];
    _array = arr;
    return true;
  }
  public boolean remove(HoaDon sp) {
    int newLength = _soLuong;
    if (timTheoMa(sp.getMa()) == null) return false;
    _soLuong = --newLength;

    HoaDon[] arr = new HoaDon[newLength];
    int i = 0;
    for ( ; sp.getMa().equals(arr[i].getMa()) ; i++) arr[i] = _array[i];
    for (int j = i++ ; i < newLength ; j = i++)
      arr[j] = _array[i];
    _array = arr;
    return true;
  }
  public boolean daCo(String maHD) { return timTheoMa(maHD) != null; }
  public boolean daCo(HoaDon hd) { return daCo(hd.getMa()); }
  public boolean xoaTheoMa(String ma) {
    HoaDon kh = timTheoMa(ma);
    if (kh == null) return false;
    return remove(kh);
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
      HoaDon sp = new HoaDon();
      sp.setMa(ma);
      sp.input();
      temp[i] = sp;
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
      if (s.isBlank()) break;
      HoaDon hd = QuanLyCuaHangMayTinh._dsHoaDon.timTheoMa(s);
      if (hd != null) hd.output();
      else System.out.println("Lỗi!");
    }
  }
}
