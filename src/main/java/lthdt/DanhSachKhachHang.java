package lthdt;

import java.io.*;
import java.util.*;

public final class DanhSachKhachHang implements IListConsoleIO, IListFileIO {
  private int _soLuong;
  private KhachHang[] _array;
  public DanhSachKhachHang(KhachHang[] array, int soLuong) {
    _soLuong = soLuong;
    _array = array.clone();
  }
  public DanhSachKhachHang() { this(new KhachHang[0], 0); }
  public int soLuong() { return _soLuong; }
  public KhachHang get(int index) { return (index < 0 || index >= _soLuong) ? null : _array[index]; }
  public void set(int index, KhachHang ncc) { if (index >= 0 && index < _soLuong) _array[index] = ncc; }
  public KhachHang timTheoMa(String ma) {
    for (int i = 0, n = _soLuong ; i < n ; ++i)
      if (_array[i].getMa().equals(ma)) return _array[i];
    return null;
  }
  public KhachHang[] timTheoHo(String ho) {
    ho = ho.toLowerCase();
    int n = _soLuong, k = 0;
    KhachHang[] ncc = new KhachHang[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getHo().toLowerCase().indexOf(ho) >= 0) ncc[k++] = _array[i];
    return Arrays.copyOf(ncc, k);
  }
  public KhachHang[] timTheoTen(String ten) {
    ten = ten.toLowerCase();
    int n = _soLuong, k = 0;
    KhachHang[] ncc = new KhachHang[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getTen().toLowerCase().indexOf(ten) >= 0) ncc[k++] = _array[i];
    return Arrays.copyOf(ncc, k);
  }
  public KhachHang[] timTheoHoTen(String hoTen) {
    hoTen = hoTen.toLowerCase();
    int n = _soLuong, k = 0;
    KhachHang[] ncc = new KhachHang[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getHoTen().toLowerCase().indexOf(hoTen) >= 0) ncc[k++] = _array[i];
    return Arrays.copyOf(ncc, k);
  }
  public KhachHang[] timTheoSoDienThoai(String sdt) {
    int n = _soLuong, k = 0;
    KhachHang[] ncc = new KhachHang[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getSoDienThoai().indexOf(sdt) >= 0) ncc[k++] = _array[i];
    return Arrays.copyOf(ncc, k);
  }
  public KhachHang[] timTheoDiaChi(String dc) {
    dc = dc.toLowerCase();
    int n = _soLuong, k = 0;
    KhachHang[] ncc = new KhachHang[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getDiaChi().toLowerCase().indexOf(dc) >= 0) ncc[k++] = _array[i];
    return Arrays.copyOf(ncc, k);
  }
  public void thongKeSoKhachHangTheoDiaChi() {
    KhachHang[] cp = Arrays.copyOf(_array, _soLuong);
    for (int i = 0 ; i < _soLuong ; i++) {
      if (cp[i] != null) {
        String _dc = cp[i].getDiaChi();
        String dc = _dc.toLowerCase();
        int dem = 1;
        cp[i] = null;
        for (int j = i + 1 ; j < _soLuong ; j++) {
          if (cp[j].getDiaChi().toLowerCase().equals(dc)) {
            ++dem;
            cp[j] = null;
          }
        }
        System.out.printf("Có %d khách hàng ở %s\n", dem, _dc);
      }
    }
  }
  public boolean add(int index, KhachHang ncc) {
    if (timTheoMa(ncc.getMa()) != null) return false;

    int newLength = _soLuong + 1;
    _soLuong = newLength;

    KhachHang[] arr = new KhachHang[newLength];
    int i = 0;
    for ( ; i < index ; i++) arr[i] = _array[i];
    arr[i] = ncc;
    for (int j = i++ ; i < newLength ; j = i++)
      arr[i] = _array[j];
    _array = arr;
    return true;
  }
  public boolean add(KhachHang ncc) { return add(_soLuong, ncc); }
  public boolean remove(int index) {
    int newLength = _soLuong;
    if (index < 0 || index >= newLength) return false;
    _soLuong = --newLength;

    KhachHang[] arr = new KhachHang[newLength];
    int i = 0;
    for ( ; i < index ; i++) arr[i] = _array[i];
    for (int j = i++ ; i < newLength ; j = i++)
      arr[j] = _array[i];
    _array = arr;
    return true;
  }
  public boolean remove(KhachHang ncc) {
    int newLength = _soLuong;
    if (timTheoMa(ncc.getMa()) == null) return false;
    _soLuong = --newLength;

    KhachHang[] arr = new KhachHang[newLength];
    int i = 0;
    for ( ; ncc.getMa().equals(arr[i].getMa()) ; i++) arr[i] = _array[i];
    for (int j = i++ ; i < newLength ; j = i++)
      arr[j] = _array[i];
    _array = arr;
    return true;
  }
  public boolean daCo(String maKh) { return timTheoMa(maKh) != null; }
  public boolean daCo(KhachHang kh) { return daCo(kh.getMa()); }
  public boolean xoaTheoMa(String ma) {
    KhachHang kh = timTheoMa(ma);
    if (kh == null) return false;
    return remove(kh);
  }
  public void readFile() {
    Scanner stream = null;
    try {
      stream = new Scanner(new FileInputStream(QuanLyCuaHangMayTinh._databasePath + "KhachHang.txt"));
      while (stream.hasNextLine()) {
        KhachHang ncc = new KhachHang();
        ncc.input(stream);
        add(ncc);
      }
      stream.close();
    } catch (Throwable e) {
      if (stream != null) try { stream.close(); } catch (Throwable ce) { e.addSuppressed(ce); }
      QuanLyCuaHangMayTinh.processingInternalThrowable(e);
      System.out.println("Không đọc được file!");
    }
  }
  public void writeFile() {
    OutputStreamWriter stream = null;
    try {
      stream = new OutputStreamWriter(new FileOutputStream(QuanLyCuaHangMayTinh._databasePath + "KhachHang.txt"));
      for (int i = 0 ; i < _soLuong ; i++) _array[i].output(stream);
      stream.close();
    } catch (Throwable e) {
      if (stream != null) try { stream.close(); } catch (Throwable ce) { e.addSuppressed(ce); }
      QuanLyCuaHangMayTinh.processingInternalThrowable(e);
      System.out.println("Không ghi được file!");
    }
  }

  public void input(int n) {
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    int newLength = _soLuong + n;
    KhachHang[] temp = new KhachHang[newLength];
    for (int i = _soLuong ; i < newLength ; i++) {
      String ma;
      check:
      while (true) {
        System.out.print("Nhập mã khách hàng: ");
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
      KhachHang kh = new KhachHang();
      kh.setMa(ma);
      kh.input();
      temp[i] = kh;
    }
    System.arraycopy(_array, 0, temp, 0, _soLuong);
    _soLuong = newLength;
    _array = temp;
  }
  public void output() {
    System.out.printf("%-10s %-30s %-11s %30s \n", "Mã", "Họ tên", "Số điện thoại", "Địa chỉ");
    for (int i = 0 ; i < _soLuong ; ++i)
      System.out.printf("%-10s %-30s %-11s %30s \n", _array[i].getMa(), _array[i].getHoTen(), _array[i].getSoDienThoai(), _array[i].getDiaChi());
  }
}
