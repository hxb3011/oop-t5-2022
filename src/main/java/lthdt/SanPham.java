package lthdt;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;


public abstract class SanPham 
{
    private String MaSP;
    private String TenSP; 
    private long DonGia;
    private int TonKho;
    private ChiTietSanPham ChiTiet;
    public SanPham() { ChiTiet = new ChiTietSanPham(); }
    public SanPham(String masp, String tensp, long dongia, int tonkho, ChiTietSanPham chitiet)
    {
        MaSP = masp;
        TenSP = tensp;
        DonGia = dongia;
        TonKho = tonkho;
        ChiTiet = chitiet;
    }
    public SanPham(SanPham sp)
    {
        this(sp.MaSP, sp.TenSP, sp.DonGia, sp.TonKho, sp.ChiTiet);
    }
    public void docFile(Scanner in)
    {
        MaSP = in.nextLine();
        TenSP = in.nextLine();
        DonGia = Long.parseLong(in.nextLine());
        TonKho = Integer.parseInt(in.nextLine());
        ChiTiet.docFile(in);
    }
    public void ghiFile(DataOutputStream output) throws IOException
    {
        output.writeBytes(MaSP);
        output.writeBytes(System.lineSeparator());
        output.writeBytes(TenSP);
        output.writeBytes(System.lineSeparator());
        output.writeBytes(Long.toString(DonGia));
        output.writeBytes(System.lineSeparator());
        output.writeBytes(Integer.toString(TonKho));
        output.writeBytes(System.lineSeparator());
        ChiTiet.ghiFile(output);
    }
    public void nhap()
    {
        nhapMaSP();
        nhapTenSP();
        nhapDonGia();
        nhapTonKho();
        nhapChiTiet();
    }
    public void nhapMaSP()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("\nNhap ma san pham: ");
        MaSP = in.nextLine();
    }
    public void nhapTenSP()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap ten san pham: ");
        TenSP = in.nextLine();
    }
    public void nhapDonGia()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap don gia: ");
        DonGia = Long.parseLong(in.nextLine()); 
    }
    public void nhapTonKho()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap so hang ton kho: ");
        TonKho = Integer.parseInt(in.nextLine());
    }
    public void nhapChiTiet()
    {
        ChiTiet.nhap();
    }
    public void xuat()
    {
        System.out.printf("\n%-10s %-20s %-10d %-20d", MaSP, TenSP, DonGia, TonKho);
    }
    public void xuatChiTiet()
    {
        ChiTiet.xuat();
    }
    public void setMaSP(String masp)
    {
        MaSP = masp;
    }
    public String getMaSP()
    {
        return MaSP;
    }
    public void setTenSP(String tensp)
    {
        TenSP = tensp;
    }
    public String getTenSP()
    {
        return TenSP;
    }
    public void setDG(long dongia)
    {
        DonGia = dongia;
    }
    public long getDG()
    {
        return DonGia;
    }
    public void setTK(int tonkho)
    {
        TonKho = tonkho;
    }
    public int getTK()
    {
        return TonKho;
    }
    public void setCTSP(ChiTietSanPham ct)
    {
        ChiTiet = new ChiTietSanPham(ct);
    }
    public ChiTietSanPham getCTSP()
    {
        return ChiTiet;
    }
    
}
