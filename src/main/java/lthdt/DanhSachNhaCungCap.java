package lthdt;

import java.io.*;
import java.util.*;

public final class DanhSachNhaCungCap implements IListConsoleIO, IListFileIO {
  private int _soLuong;
  private NhaCungCap[] _array;
  public DanhSachNhaCungCap(NhaCungCap[] array, int soLuong) {
    _soLuong = soLuong;
    _array = array.clone();
  }
  public DanhSachNhaCungCap() { this(new NhaCungCap[0], 0); }
  public int soLuong() { return _soLuong; }
  public NhaCungCap get(int index) { return (index < 0 || index >= _soLuong) ? null : _array[index]; }
  public void set(int index, NhaCungCap ncc) { if (index >= 0 && index < _soLuong) _array[index] = ncc; }
  public NhaCungCap timTheoMa(String ma) {
    for (int i = 0, n = _soLuong ; i < n ; ++i)
      if (_array[i].getMa().equals(ma)) return _array[i];
    return null;
  }
  public NhaCungCap[] timTheoTen(String ten) {
    ten = ten.toLowerCase();
    int n = _soLuong, k = 0;
    NhaCungCap[] ncc = new NhaCungCap[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getTen().toLowerCase().indexOf(ten) >= 0) ncc[k++] = _array[i];
    return Arrays.copyOf(ncc, k);
  }
  public boolean add(int index, NhaCungCap ncc) {
    if (timTheoMa(ncc.getMa()) != null) return false;

    int newLength = _soLuong + 1;
    _soLuong = newLength;

    NhaCungCap[] arr = new NhaCungCap[newLength];
    int i = 0;
    for ( ; i < index ; i++) arr[i] = _array[i];
    arr[i] = ncc;
    for (int j = i++ ; i < newLength ; j = i++)
      arr[i] = _array[j];
    _array = arr;
    return true;
  }
  public boolean add(NhaCungCap ncc) { return add(_soLuong, ncc); }
  public boolean remove(int index) {
    int newLength = _soLuong;
    if (index < 0 || index >= newLength) return false;
    _soLuong = --newLength;

    NhaCungCap[] arr = new NhaCungCap[newLength];
    int i = 0;
    for ( ; i < index ; i++) arr[i] = _array[i];
    for (int j = i++ ; i < newLength ; j = i++)
      arr[j] = _array[i];
    _array = arr;
    return true;
  }
  public boolean remove(NhaCungCap ncc) {
    int newLength = _soLuong;
    if (timTheoMa(ncc.getMa()) == null) return false;
    _soLuong = --newLength;

    NhaCungCap[] arr = new NhaCungCap[newLength];
    int i = 0;
    for ( ; ncc.getMa().equals(arr[i].getMa()) ; i++) arr[i] = _array[i];
    for (int j = i++ ; i < newLength ; j = i++)
      arr[j] = _array[i];
    _array = arr;
    return true;
  }
  public boolean daCo(String maNcc) { return timTheoMa(maNcc) != null; }
  public boolean daCo(NhaCungCap ncc) { return daCo(ncc.getMa()); }
  public boolean xoaTheoMa(String ma) {
    NhaCungCap kh = timTheoMa(ma);
    if (kh == null)  return false;
    return remove(kh);
  }
  public void readFile() {
    Scanner stream = null;
    try {
      stream = new Scanner(new FileInputStream(QuanLyCuaHangMayTinh._databasePath + "NhaCungCap.txt"));
      while (stream.hasNextLine()) {
        NhaCungCap ncc = new NhaCungCap();
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
      stream = new OutputStreamWriter(new FileOutputStream(QuanLyCuaHangMayTinh._databasePath + "NhaCungCap.txt"));
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
    NhaCungCap[] temp = new NhaCungCap[newLength];
    for (int i = _soLuong ; i < newLength ; i++) {
      String ma;
      check:
      while (true) {
        System.out.print("Nhập mã nhà cung cấp: ");
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
      NhaCungCap ncc = new NhaCungCap();
      ncc.setMa(ma);
      ncc.input();
      temp[i] = ncc;
    }
    System.arraycopy(_array, 0, temp, 0, _soLuong);
    _soLuong = newLength;
    _array = temp;
  }
  public void output() {
    System.out.printf("%-10s %-30s \n", "Mã", "Tên");
    for (int i = 0 ; i < _soLuong ; ++i) System.out.printf("%-10s %-30s \n", _array[i].getMa(), _array[i].getTen());
  }
}
