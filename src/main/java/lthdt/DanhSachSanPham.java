package lthdt;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public final class DanhSachSanPham implements IListConsoleIO, IListFileIO {
  private int _soLuong;
  private SanPham[] _array;
  public DanhSachSanPham(SanPham[] array, int soLuong) {
    _soLuong = soLuong;
    _array = array.clone();
  }
  public DanhSachSanPham() { this(new SanPham[0], 0); }
  public int soLuong() { return _soLuong; }
  public SanPham get(int index) { return (index < 0 || index >= _soLuong) ? null : _array[index]; }
  public void set(int index, SanPham sp) { if (index >= 0 && index < _soLuong) _array[index] = sp; }
  public SanPham timTheoMa(String ma) {
    for (int i = 0, n = _soLuong ; i < n ; ++i)
      if (_array[i].getMa().equals(ma)) return _array[i];
    return null;
  }
  public boolean laMayTinhBan(String ma) { return timTheoMa(ma) instanceof MayTinhBan; }
  public boolean laLaptop(String ma) { return timTheoMa(ma) instanceof Laptop; }
  public SanPham[] timTheoTen(String ten) {
    ten = ten.toLowerCase();
    int n = _soLuong, k = 0;
    SanPham[] sp = new SanPham[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getTen().toLowerCase().indexOf(ten) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public SanPham[] timTheoTenNhaSanXuat(String tenNSX) {
    tenNSX = tenNSX.toLowerCase();
    int n = _soLuong, k = 0;
    SanPham[] sp = new SanPham[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getTenNhaSanXuat().toLowerCase().indexOf(tenNSX) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public SanPham[] timTheoDonGia(long donGia) {
    int n = _soLuong, k = 0;
    SanPham[] sp = new SanPham[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getDonGia() == donGia) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public SanPham[] timTheoKhoangDonGia(long donGiaNho, long donGiaLon) {
    int n = _soLuong, k = 0;
    SanPham[] sp = new SanPham[n];
    for (int i = 0 ; i < n ; ++i) {
      long donGia = _array[i].getDonGia();
      if (donGia >= donGiaNho && donGia <= donGiaLon) sp[k++] = _array[i];
    }
    return Arrays.copyOf(sp, k);
  }
  public SanPham[] timTheoSoLuongTon(int soLuongTon) {
    int n = _soLuong, k = 0;
    SanPham[] sp = new SanPham[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getSoLuong() == soLuongTon) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public SanPham[] timTheoKhoangSoLuongTon(int soLuongTonNho, int soLuongTonLon) {
    int n = _soLuong, k = 0;
    SanPham[] sp = new SanPham[n];
    for (int i = 0 ; i < n ; ++i) {
      int soLuong = _array[i].getSoLuong();
      if (soLuong >= soLuongTonNho && soLuong <= soLuongTonLon) sp[k++] = _array[i];
    }
    return Arrays.copyOf(sp, k);
  }
  public SanPham[] timTheoCPU(String cpu) {
    cpu = cpu.toLowerCase();
    int n = _soLuong, k = 0;
    SanPham[] sp = new SanPham[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getChiTiet().getCPU().toLowerCase().indexOf(cpu) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public SanPham[] timTheoGPU(String gpu) {
    gpu = gpu.toLowerCase();
    int n = _soLuong, k = 0;
    SanPham[] sp = new SanPham[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getChiTiet().getGPU().toLowerCase().indexOf(gpu) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public SanPham[] timTheoRAM(String ram) {
    ram = ram.toLowerCase();
    int n = _soLuong, k = 0;
    SanPham[] sp = new SanPham[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getChiTiet().getRAM().toLowerCase().indexOf(ram) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public SanPham[] timTheoManHinh(String manHinh) {
    manHinh = manHinh.toLowerCase();
    int n = _soLuong, k = 0;
    SanPham[] sp = new SanPham[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getChiTiet().getManHinh().toLowerCase().indexOf(manHinh) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public SanPham[] timTheoNamSanXuat(String namSanXuat) {
    namSanXuat = namSanXuat.toLowerCase();
    int n = _soLuong, k = 0;
    SanPham[] sp = new SanPham[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getChiTiet().getNamSanXuat().toLowerCase().equals(namSanXuat)) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public SanPham[] timTheoHeDieuHanh(String heDieuHanh) {
    heDieuHanh = heDieuHanh.toLowerCase();
    int n = _soLuong, k = 0;
    SanPham[] sp = new SanPham[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getChiTiet().getHeDieuHanh().toLowerCase().indexOf(heDieuHanh) >= 0) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public SanPham[] timTheoThoiGianBaoHanh(int thoiGianBaoHanh) {
    int n = _soLuong, k = 0;
    SanPham[] sp = new SanPham[n];
    for (int i = 0 ; i < n ; ++i)
      if (_array[i].getChiTiet().getThoiGianBaoHanh() == thoiGianBaoHanh) sp[k++] = _array[i];
    return Arrays.copyOf(sp, k);
  }
  public SanPham[] timTheoKhoangThoiGianBaoHanh(int thoiGianBaoHanhNho, int thoiGianBaoHanhLon) {
    int n = _soLuong, k = 0;
    SanPham[] sp = new SanPham[n];
    for (int i = 0 ; i < n ; ++i) {
      int thoiGianBaoHanh = _array[i].getChiTiet().getThoiGianBaoHanh();
      if (thoiGianBaoHanh >= thoiGianBaoHanhNho && thoiGianBaoHanh <= thoiGianBaoHanhLon) sp[k++] = _array[i];
    }
    return Arrays.copyOf(sp, k);
  }
  public MayTinhBan[] timTheoPhuKien(String phuKien) {
    phuKien = phuKien.toLowerCase();
    int n = _soLuong, k = 0;
    MayTinhBan[] sp = new MayTinhBan[n];
    for (int i = 0 ; i < n ; ++i) {
      SanPham sanPham = _array[i];
      if (sanPham instanceof MayTinhBan) {
        MayTinhBan mayTinhBan = (MayTinhBan) sanPham;
        if (mayTinhBan.getPhuKien().toLowerCase().indexOf(phuKien) >= 0) sp[k++] = mayTinhBan;
      }
    }
    return Arrays.copyOf(sp, k);
  }
  public Laptop[] timTheoChatLuongLoa(String chatLuongLoa) {
    chatLuongLoa = chatLuongLoa.toLowerCase();
    int n = _soLuong, k = 0;
    Laptop[] sp = new Laptop[n];
    for (int i = 0 ; i < n ; ++i) {
      SanPham sanPham = _array[i];
      if (sanPham instanceof Laptop) {
        Laptop laptop = (Laptop) sanPham;
        if (laptop.getChatLuongLoa().toLowerCase().indexOf(chatLuongLoa) >= 0) sp[k++] = laptop;
      }
    }
    return Arrays.copyOf(sp, k);
  }
  public Laptop[] timTheoManHinhCamUng(boolean camUng) {
    int n = _soLuong, k = 0;
    Laptop[] sp = new Laptop[n];
    for (int i = 0 ; i < n ; ++i) {
      SanPham sanPham = _array[i];
      if (sanPham instanceof Laptop) {
        Laptop laptop = (Laptop) sanPham;
        if (laptop.getManHinhCamUng() == camUng) sp[k++] = laptop;
      }
    }
    return Arrays.copyOf(sp, k);
  }
  public Laptop[] timTheoLedBanPhim(boolean ledBanPhim) {
    int n = _soLuong, k = 0;
    Laptop[] sp = new Laptop[n];
    for (int i = 0 ; i < n ; ++i) {
      SanPham sanPham = _array[i];
      if (sanPham instanceof Laptop) {
        Laptop laptop = (Laptop) sanPham;
        if (laptop.getLedBanPhim() == ledBanPhim) sp[k++] = laptop;
      }
    }
    return Arrays.copyOf(sp, k);
  }
  public boolean add(int index, SanPham sp) {
    if (timTheoMa(sp.getMa()) != null) return false;

    int newLength = _soLuong + 1;
    _soLuong = newLength;

    SanPham[] arr = new SanPham[newLength];
    int i = 0;
    for ( ; i < index ; i++) arr[i] = _array[i];
    arr[i] = sp;
    for (int j = i++ ; i < newLength ; j = i++)
      arr[i] = _array[j];
    _array = arr;
    return true;
  }
  public boolean add(SanPham sp) { return add(_soLuong, sp); }
  public boolean remove(int index) {
    int newLength = _soLuong;
    if (index < 0 || index >= newLength) return false;
    _soLuong = --newLength;

    SanPham[] arr = new SanPham[newLength];
    int i = 0;
    for ( ; i < index ; i++) arr[i] = _array[i];
    for (int j = i++ ; i < newLength ; j = i++)
      arr[j] = _array[i];
    _array = arr;
    return true;
  }
  public boolean remove(SanPham sp) {
    int newLength = _soLuong;
    if (timTheoMa(sp.getMa()) == null) return false;
    _soLuong = --newLength;

    SanPham[] arr = new SanPham[newLength];
    int i = 0;
    for ( ; sp.getMa().equals(arr[i].getMa()) ; i++) arr[i] = _array[i];
    for (int j = i++ ; i < newLength ; j = i++)
      arr[j] = _array[i];
    _array = arr;
    return true;
  }
  public boolean daCo(String masp) { return timTheoMa(masp) != null; }
  public boolean daCo(SanPham sp) { return daCo(sp.getMa()); }
  public boolean xoaTheoMa(String ma) {
    SanPham kh = timTheoMa(ma);
    if (kh == null) return false;
    return remove(kh);
  }
  public void readFile() {
    Scanner base = null, specC = null, specL = null;
    try {
      base = new Scanner(new FileInputStream(QuanLyCuaHangMayTinh._databasePath + "SanPham.txt"));
      specC = new Scanner(new FileInputStream(QuanLyCuaHangMayTinh._databasePath + "MayTinhBan.txt"));
      specL = new Scanner(new FileInputStream(QuanLyCuaHangMayTinh._databasePath + "Laptop.txt"));
      String ma, cMa = null, lMa = null;
      while (base.hasNextLine()) {
        ma = base.nextLine();
        if (ma != null) {
          if (cMa == null && specC.hasNextLine()) cMa = specC.nextLine();
          if (lMa == null && specL.hasNextLine()) lMa = specL.nextLine();
          SanPham sp;
          if (ma.equals(cMa)) {
            sp = new MayTinhBan();
            sp.setMa(ma);
            sp.input(base, specC);
            cMa = null;
            add(sp);
            continue;
          } else if (ma.equals(lMa)) {
            sp = new Laptop();
            sp.setMa(ma);
            sp.input(base, specL);
            lMa = null;
            add(sp);
            continue;
          }
        }
        System.out.println("File bị lỗi!");
        break;
      }
      base.close();
      specC.close();
      specL.close();
    } catch (Throwable e) {
      if (base != null) try { base.close(); } catch (Throwable ce) { e.addSuppressed(ce); }
      if (specC != null) try { specC.close(); } catch (Throwable ce) { e.addSuppressed(ce); }
      if (specL != null) try { specL.close(); } catch (Throwable ce) { e.addSuppressed(ce); }
      QuanLyCuaHangMayTinh.processingInternalThrowable(e);
      System.out.println("Không đọc được file!");
    }
  }
  public void writeFile() {
    OutputStreamWriter base = null, specC = null, specL = null;
    try {
      base = new OutputStreamWriter(new FileOutputStream(QuanLyCuaHangMayTinh._databasePath + "SanPham.txt"));
      specC = new OutputStreamWriter(new FileOutputStream(QuanLyCuaHangMayTinh._databasePath + "MayTinhBan.txt"));
      specL = new OutputStreamWriter(new FileOutputStream(QuanLyCuaHangMayTinh._databasePath + "Laptop.txt"));
      for (int i = 0 ; i < _soLuong ; i++) {
        SanPham sp = _array[i];
        if (sp instanceof MayTinhBan) sp.output(base, specC);
        else if (sp instanceof Laptop) sp.output(base, specL);
      }
      base.close();
      specC.close();
      specL.close();
    } catch (Throwable e) {
      if (base != null) try { base.close(); } catch (Throwable ce) { e.addSuppressed(ce); }
      if (specC != null) try { specC.close(); } catch (Throwable ce) { e.addSuppressed(ce); }
      if (specL != null) try { specL.close(); } catch (Throwable ce) { e.addSuppressed(ce); }
      QuanLyCuaHangMayTinh.processingInternalThrowable(e);
      System.out.println("Không ghi được file!");
    }
  }
  public void input(int n) {
    Scanner in = QuanLyCuaHangMayTinh.STANDARD_IN;
    int newLength = _soLuong + n;
    SanPham[] temp = new SanPham[newLength];
    for (int i = _soLuong ; i < newLength ; i++) {
      byte b;
      while (true) {
        System.out.println("Nhập loại sản phẩm cần thêm (1. Laptop, 2. Máy tính để bàn): ");
        String s = in.nextLine();
        if (s.length() == 1) {
          char c = s.charAt(0);
          if (c == '1') {
            b = 1;
            break;
          } else if (c == '2') {
            b = 2;
            break;
          }
        }
        System.out.println("Lỗi!");
      }
      String ma;
      check:
      while (true) {
        System.out.print("Nhập mã sản phẩm: ");
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
      SanPham sp;
      if (b == 1) {
        sp = new MayTinhBan();
      } else if (b == 2) {
        sp = new Laptop();
      } else {
        // throw new UnsupportedOperationException("Impossible!");
        System.out.println("Lỗi không xác định");
        return;
      }
      sp.setMa(ma);
      sp.input();
      temp[i] = sp;
    }
    System.arraycopy(_array, 0, temp, 0, _soLuong);
    _soLuong = newLength;
    _array = temp;
  }
  public void output() {
    System.out.printf("%-10s %-30s %-20s %-20s %-20s \n", "Mã", "Tên", "Nhà sản xuất", "Đơn giá", "Số lượng tồn");
    for (int i = 0 ; i < _soLuong ; ++i) {
      SanPham sanPham = _array[i];
      System.out.printf("%-10s %-30s %-20s %-20s %-20s \n", sanPham.getMa(), sanPham.getTen(), sanPham.getTenNhaSanXuat(), sanPham.getDonGia(), sanPham.getSoLuong());
    }
    while (true) {
      System.out.print("\nNhập mã sản phẩm để xem chi tiết (Bỏ qua để thoát): ");
      String s = QuanLyCuaHangMayTinh.STANDARD_IN.nextLine();
      if (s.isBlank()) break;
      SanPham sp = QuanLyCuaHangMayTinh._dsSanPham.timTheoMa(s);
      if (sp instanceof MayTinhBan) {
        System.out.println("Máy tính để bàn: ");
      } else if (sp instanceof Laptop) {
        System.out.println("Laptop: ");
      } else {
        System.out.println("Lỗi!");
        continue;
      }
      sp.output();
    }
  }
}
