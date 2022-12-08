package lthdt;

import java.io.*;
import java.util.*;

public final class DanhSachNhanVien implements IListConsoleIO, IListFileIO {
  private int _soLuong;
  private NhanVien[] _array;
  public DanhSachNhanVien(NhanVien[] array, int soLuong) {
    _soLuong = soLuong;
    _array = array.clone();
  }
  public DanhSachNhanVien() { this(new NhanVien[0], 0); }
  public int soLuong() { return _soLuong; }
  public NhanVien get(int index) { return (index < 0 || index >= _soLuong) ? null : _array[index]; }
  public void set(int index, NhanVien ncc) { if (index >= 0 && index < _soLuong) _array[index] = ncc; }
  public NhanVien timTheoMa(String ma) {
    for (int i = 0, n = _soLuong ; i < n ; ++i)
      if (_array[i].getMa().equals(ma)) return _array[i];
    return null;
  }
  public NhanVien[] timTheoHo(String ho) {
    ho = ho.toLowerCase();
    int n = _soLuong, k = 0;
    NhanVien[] ncc = new NhanVien[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getHo().toLowerCase().indexOf(ho) >= 0) ncc[k++] = _array[i];
    return Arrays.copyOf(ncc, k);
  }
  public NhanVien[] timTheoTen(String ten) {
    ten = ten.toLowerCase();
    int n = _soLuong, k = 0;
    NhanVien[] ncc = new NhanVien[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getTen().toLowerCase().indexOf(ten) >= 0) ncc[k++] = _array[i];
    return Arrays.copyOf(ncc, k);
  }
  public NhanVien[] timTheoHoTen(String hoTen) {
    hoTen = hoTen.toLowerCase();
    int n = _soLuong, k = 0;
    NhanVien[] ncc = new NhanVien[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getHoTen().toLowerCase().indexOf(hoTen) >= 0) ncc[k++] = _array[i];
    return Arrays.copyOf(ncc, k);
  }
  public NhanVien[] timTheoSoDienThoai(String sdt) {
    int n = _soLuong, k = 0;
    NhanVien[] ncc = new NhanVien[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getSoDienThoai().indexOf(sdt) >= 0) ncc[k++] = _array[i];
    return Arrays.copyOf(ncc, k);
  }
  public NhanVien[] timTheoMucLuong(long mucLuong) {
    int n = _soLuong, k = 0;
    NhanVien[] ncc = new NhanVien[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getMucLuong() == mucLuong) ncc[k++] = _array[i];
    return Arrays.copyOf(ncc, k);
  }
  public NhanVien[] timTheoKhoangLuong(long mucLuongNho, long mucLuongLon) {
    int n = _soLuong, k = 0;
    NhanVien[] ncc = new NhanVien[n];
    for (int i = 0 ; i < n ; ++i) {
      long luong = _array[i].getMucLuong();
      if (mucLuongNho <= luong && luong <= mucLuongLon) ncc[k++] = _array[i];
    }
    return Arrays.copyOf(ncc, k);
  }
  public NhanVien[] timTheoNamVaoLam(int nam) {
    int n = _soLuong, k = 0;
    NhanVien[] ncc = new NhanVien[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getNamVaoLam() == nam) ncc[k++] = _array[i];
    return Arrays.copyOf(ncc, k);
  }
  public NhanVien[] timTheoKhoangNamVaoLam(int namNho, int namLon) {
    int n = _soLuong, k = 0;
    NhanVien[] ncc = new NhanVien[n];
    for (int i = 0 ; i < n ; ++i) {
      int nam = _array[i].getNamVaoLam();
      if (namNho <= nam && nam <= namLon) ncc[k++] = _array[i];
    }
    return Arrays.copyOf(ncc, k);
  }
  public void thongKeSoNhanVienTheoMucLuong() {
    NhanVien[] cp = Arrays.copyOf(_array, _soLuong);
    for (int i = 0 ; i < _soLuong ; i++) {
      if (cp[i] != null) {
        long luong = cp[i].getMucLuong();
        int dem = 1;
        cp[i] = null;
        for (int j = i + 1 ; j < _soLuong ; j++) {
          if (cp[j].getMucLuong() == luong) {
            ++dem;
            cp[j] = null;
          }
        }
        System.out.printf("Có %d nhân viên có mức lương %d\n", dem, luong);
      }
    }
  }
  public void thongKeSoNhanVienTheoNamVaoLam() {
    NhanVien[] cp = Arrays.copyOf(_array, _soLuong);
    for (int i = 0 ; i < _soLuong ; i++) {
      if (cp[i] != null) {
        int nam = cp[i].getNamVaoLam();
        int dem = 1;
        cp[i] = null;
        for (int j = i + 1 ; j < _soLuong ; j++) {
          if (cp[j].getNamVaoLam() == nam) {
            ++dem;
            cp[j] = null;
          }
        }
        System.out.printf("Có %d nhân viên vào làm năm: %s\n", dem, nam);
      }
    }
  }
  public boolean add(int index, NhanVien ncc) {
    if (timTheoMa(ncc.getMa()) != null) return false;

    int newLength = _soLuong + 1;
    _soLuong = newLength;

    NhanVien[] arr = new NhanVien[newLength];
    int i = 0;
    for ( ; i < index ; i++) arr[i] = _array[i];
    arr[i] = ncc;
    for (int j = i++ ; i < newLength ; j = i++)
      arr[i] = _array[j];
    _array = arr;
    return true;
  }
  public boolean add(NhanVien ncc) { return add(_soLuong, ncc); }
  public boolean remove(int index) {
    int newLength = _soLuong;
    if (index < 0 || index >= newLength) return false;
    _soLuong = --newLength;

    NhanVien[] arr = new NhanVien[newLength];
    int i = 0;
    for ( ; i < index ; i++) arr[i] = _array[i];
    for (int j = i++ ; i < newLength ; j = i++)
      arr[j] = _array[i];
    _array = arr;
    return true;
  }
  public boolean remove(NhanVien ncc) {
    int newLength = _soLuong;
    if (timTheoMa(ncc.getMa()) == null) return false;
    _soLuong = --newLength;

    NhanVien[] arr = new NhanVien[newLength];
    int i = 0;
    for ( ; ncc.getMa().equals(arr[i].getMa()) ; i++) arr[i] = _array[i];
    for (int j = i++ ; i < newLength ; j = i++)
      arr[j] = _array[i];
    _array = arr;
    return true;
  }
  public boolean daCo(String maNv) { return timTheoMa(maNv) != null; }
  public boolean daCo(NhanVien nv) { return daCo(nv.getMa()); }
  public boolean xoaTheoMa(String ma) {
    NhanVien kh = timTheoMa(ma);
    if (kh == null) return false;
    return remove(kh);
  }
  public void readFile() {
    Scanner stream = null;
    try {
      stream = new Scanner(new FileInputStream(QuanLyCuaHangMayTinh._databasePath + "NhanVien.txt"));
      while (stream.hasNextLine()) {
        NhanVien nv = new NhanVien();
        nv.input(stream);
        add(nv);
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
      stream = new OutputStreamWriter(new FileOutputStream(QuanLyCuaHangMayTinh._databasePath + "NhanVien.txt"));
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
    NhanVien[] temp = new NhanVien[newLength];
    for (int i = _soLuong ; i < newLength ; i++) {
      String ma;
      check:
      while (true) {
        System.out.print("Nhập mã nhân viên: ");
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
      NhanVien nv = new NhanVien();
      nv.setMa(ma);
      nv.input();
      temp[i] = nv;
    }
    System.arraycopy(_array, 0, temp, 0, _soLuong);
    _soLuong = newLength;
    _array = temp;
  }
  public void output() {
    System.out.printf("%-10s %-30s %-11s %-15s %-10s \n", "Mã", "Họ tên", "Số điện thoại", "Mức lương", "Năm vào làm");
    for (int i = 0 ; i < _soLuong ; ++i)
      System.out.printf("%-10s %-30s %-11s %-15d %-10d \n", _array[i].getMa(), _array[i].getHoTen(), _array[i].getSoDienThoai(), _array[i].getMucLuong(), _array[i].getNamVaoLam());
  }
}
