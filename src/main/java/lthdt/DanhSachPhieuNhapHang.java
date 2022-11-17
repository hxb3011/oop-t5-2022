package lthdt;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class DanhSachPhieuNhapHang implements IListConsoleIO, IListFileIO {
  private int _soLuong;
  private PhieuNhapHang[] _array;
  public DanhSachPhieuNhapHang(PhieuNhapHang[] array, int soLuong) {
    _soLuong = soLuong;
    _array = array.clone();
  }
  public DanhSachPhieuNhapHang() { this(new PhieuNhapHang[0], 0); }
  public int soLuong() { return _soLuong; }
  public PhieuNhapHang get(int index) { return (index < 0 || index >= _soLuong) ? null : _array[index]; }
  public void set(int index, PhieuNhapHang pnh) { if (index >= 0 && index < _soLuong) _array[index] = pnh; }
  public PhieuNhapHang timTheoMa(String ma) {
    for (int i = 0, n = _soLuong ; i < n ; ++i)
      if (_array[i].getMa().equals(ma)) return _array[i];
    return null;
  }
  public PhieuNhapHang[] timTheoNgayLap(String ngayLap) {
    ngayLap = ngayLap.toLowerCase();
    int n = _soLuong, k = 0;
    PhieuNhapHang[] sp = new PhieuNhapHang[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNgayLap().toLowerCase().equals(ngayLap)) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public PhieuNhapHang[] timTheoMaNhanVien(String ma) {
    ma = ma.toLowerCase();
    int n = _soLuong, k = 0;
    PhieuNhapHang[] sp = new PhieuNhapHang[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getMa().toLowerCase().equals(ma)) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public PhieuNhapHang[] timTheoHoNhanVien(String ho) {
    ho = ho.toLowerCase();
    int n = _soLuong, k = 0;
    PhieuNhapHang[] sp = new PhieuNhapHang[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getHo().toLowerCase().indexOf(ho) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public PhieuNhapHang[] timTheoTenNhanVien(String ten) {
    ten = ten.toLowerCase();
    int n = _soLuong, k = 0;
    PhieuNhapHang[] sp = new PhieuNhapHang[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getTen().toLowerCase().indexOf(ten) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public PhieuNhapHang[] timTheoHoTenNhanVien(String hoTen) {
    hoTen = hoTen.toLowerCase();
    int n = _soLuong, k = 0;
    PhieuNhapHang[] sp = new PhieuNhapHang[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getHoTen().toLowerCase().indexOf(hoTen) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public PhieuNhapHang[] timTheoMaNhaCungCap(String ma) {
    ma = ma.toLowerCase();
    int n = _soLuong, k = 0;
    PhieuNhapHang[] sp = new PhieuNhapHang[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getMa().toLowerCase().equals(ma)) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public PhieuNhapHang[] timTheoTenNhaCungCap(String ten) {
    ten = ten.toLowerCase();
    int n = _soLuong, k = 0;
    PhieuNhapHang[] sp = new PhieuNhapHang[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNhanVien().getTen().toLowerCase().indexOf(ten) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public PhieuNhapHang[] timTheoMaSanPham(String ma) {
    int n = _soLuong, k = 0;
    PhieuNhapHang[] sp = new PhieuNhapHang[n];
    for (int i = 0 ; i < n ; ++i) {
      ChiTietPhieuNhapHang[] ct = sp[i].getChiTiet();
      for (ChiTietPhieuNhapHang cti : ct) {
        if (cti.getSanPham().getMa().equals(ma)) {
          sp[k++] = _array[i];
          break;
        }
      }
    }
    return Arrays.copyOf(sp, k);
  }
  public PhieuNhapHang[] timTheoTenSanPham(String ten) {
    int n = _soLuong, k = 0;
    PhieuNhapHang[] sp = new PhieuNhapHang[n];
    for (int i = 0 ; i < n ; ++i) {
      ChiTietPhieuNhapHang[] ct = sp[i].getChiTiet();
      for (ChiTietPhieuNhapHang cti : ct) {
        if (cti.getSanPham().getTen().indexOf(ten) >= 0) {
          sp[k++] = _array[i];
          break;
        }
      }
    }
    return Arrays.copyOf(sp, k);
  }
  public PhieuNhapHang[] timTheoTongTien(long tongTien) {
    int n = _soLuong, k = 0;
    PhieuNhapHang[] sp = new PhieuNhapHang[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getTongTien() == tongTien) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public PhieuNhapHang[] timTheoKhoangTongTien(long tongTienNho, long tongTienLon) {
    int n = _soLuong, k = 0;
    PhieuNhapHang[] sp = new PhieuNhapHang[n];
    for (int i = 0 ; i < n ; ++i) {
      long tongTien = _array[i].getTongTien();
      if (tongTien >= tongTienNho && tongTien <= tongTienLon) sp[k++] = _array[i];
    }
    return Arrays.copyOf(sp, k);
  }
  public boolean add(int index, PhieuNhapHang pnh) {
    if (timTheoMa(pnh.getMa()) != null) return false;

    int newLength = _soLuong + 1;
    _soLuong = newLength;

    PhieuNhapHang[] arr = new PhieuNhapHang[newLength];
    int i = 0;
    for ( ; i < index ; i++) arr[i] = _array[i];
    arr[i] = pnh;
    for (int j = i++ ; i < newLength ; j = i++)
      arr[i] = _array[j];
    _array = arr;
    return true;
  }
  public boolean add(PhieuNhapHang pnh) { return add(_soLuong, pnh); }
  public boolean remove(int index) {
    int newLength = _soLuong;
    if (index < 0 || index >= newLength) return false;
    _soLuong = --newLength;

    PhieuNhapHang[] arr = new PhieuNhapHang[newLength];
    int i = 0;
    for ( ; i < index ; i++) arr[i] = _array[i];
    for (int j = i++ ; i < newLength ; j = i++)
      arr[j] = _array[i];
    _array = arr;
    return true;
  }
  public boolean remove(PhieuNhapHang pnh) {
    int newLength = _soLuong;
    if (timTheoMa(pnh.getMa()) == null) return false;
    _soLuong = --newLength;

    PhieuNhapHang[] arr = new PhieuNhapHang[newLength];
    int i = 0;
    for ( ; pnh.getMa().equals(arr[i].getMa()) ; i++) arr[i] = _array[i];
    for (int j = i++ ; i < newLength ; j = i++)
      arr[j] = _array[i];
    _array = arr;
    return true;
  }
  public boolean daCo(String maHD) { return timTheoMa(maHD) != null; }
  public boolean daCo(PhieuNhapHang pnh) { return daCo(pnh.getMa()); }
  public boolean xoaTheoMa(String ma) {
    PhieuNhapHang pnh = timTheoMa(ma);
    if (pnh == null) return false;
    return remove(pnh);
  }
  public void readFile() {
    Scanner base = null, spec = null;
    try {
      base = new Scanner(new FileInputStream(QuanLyCuaHangMayTinh._databasePath + "PhieuNhapHang.txt"));
      spec = new Scanner(new FileInputStream(QuanLyCuaHangMayTinh._databasePath + "ChiTietPhieuNhapHang.txt"));
      String sMa = null;
      while (base.hasNextLine()) {
        PhieuNhapHang pnh = new PhieuNhapHang();
        pnh.input();
        String ma = pnh.getMa();
        add(pnh);
        while (spec.hasNextLine()) {
          if (sMa == null) sMa = spec.nextLine();
          if (ma.equals(sMa)) {
            ChiTietPhieuNhapHang ct = new ChiTietPhieuNhapHang();
            ct.input(spec);
            pnh.add(ct);
            sMa = null;
          } else break;
        }
        if (pnh.getChiTiet().length == 0) {
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
      base = new OutputStreamWriter(new FileOutputStream(QuanLyCuaHangMayTinh._databasePath + "PhieuNhapHang.txt"));
      spec = new OutputStreamWriter(new FileOutputStream(QuanLyCuaHangMayTinh._databasePath + "ChiTietPhieuNhapHang.txt"));
      for (int i = 0 ; i < _soLuong ; i++) {
        PhieuNhapHang pnh = _array[i];
        pnh.output(base);
        ChiTietPhieuNhapHang[] ct = pnh.getChiTiet();
        for (ChiTietPhieuNhapHang cti : ct) {
          spec.write(pnh.getMa());
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
    PhieuNhapHang[] temp = new PhieuNhapHang[newLength];
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
      PhieuNhapHang pnh = new PhieuNhapHang();
      pnh.setMa(ma);
      pnh.input();
      temp[i] = pnh;
    }
    System.arraycopy(_array, 0, temp, 0, _soLuong);
    _soLuong = newLength;
    _array = temp;
  }
  public void output() {
    System.out.printf("%-10s %-30s %-30s %-20s\n", "Mã", "Tên nhân viên", "Tên nhà cung cấp", "Tổng tiền");
    for (int i = 0 ; i < _soLuong ; ++i) {
      PhieuNhapHang pnh = _array[i];
      System.out.printf("%-10s %-30s %-30s %-20s\n", pnh.getMa(), pnh.getNhanVien().getHoTen(), pnh.getNhaCungCap().getTen(), pnh.getTongTien());
    }
    while (true) {
      System.out.print("\nNhập mã phiếu nhập hàng để xem chi tiết (Bỏ qua để thoát): ");
      String s = QuanLyCuaHangMayTinh.STANDARD_IN.nextLine();
      if (s.isBlank()) break;
      PhieuNhapHang pnh = QuanLyCuaHangMayTinh._dsPhieuNhapHang.timTheoMa(s);
      if (pnh != null) pnh.output();
      else System.out.println("Lỗi!");
    }
  }
}
