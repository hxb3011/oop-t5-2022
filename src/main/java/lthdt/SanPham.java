package lthdt;

import java.util.Scanner;

public class SanPham 
{
    private String MaSP;
    private String TenSP; 
    private long DonGia;
    private int TonKho;
    private ChiTietSanPham ChiTiet;
    public SanPham() {}
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
        MaSP = sp.MaSP;
        TenSP = sp.TenSP;
        DonGia = sp.DonGia;
        TonKho = sp.TonKho;
        ChiTiet = sp.ChiTiet;
    }
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("\nNhap ma san pham: ");
        MaSP = in.nextLine();
        System.out.print("Nhap ten san pham: ");
        TenSP = in.nextLine();
        System.out.print("Nhap don gia: ");
        DonGia = Long.parseLong(in.nextLine()); 
        System.out.print("Nhap so hang ton kho: ");
        TonKho = Integer.parseInt(in.nextLine());
    }
    public void xuat()
    {
        System.out.printf("\n MaSP %20s TenSP %20s Don gia %20s Ton kho","","","","");
        System.out.printf("\n%s %20s %20d %20d", MaSP, TenSP, DonGia, TonKho);
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
    
}
