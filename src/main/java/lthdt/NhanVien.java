package lthdt;

import java.util.Scanner;

public class NhanVien 
{
    private String MaNV;
    private String Ho;
    private String Ten;
    private String SoDT;
    private long MucLuong;
    private int NamVaoLam;
    public NhanVien() {}
    public NhanVien(String manv, String ho, String ten, String sdt, long luong, int nvl)
    {
        MaNV = manv;
        Ho = ho;
        Ten = ten;
        SoDT = sdt;
        MucLuong = luong;
        NamVaoLam = nvl;
    }
    public NhanVien(NhanVien nv)
    {
        MaNV = nv.MaNV;
        Ho = nv.Ho;
        Ten = nv.Ten;
        SoDT = nv.SoDT;
        MucLuong = nv.MucLuong;
        NamVaoLam = nv.NamVaoLam;
    }
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("\nNhap ma nhan vien: ");
        MaNV = in.nextLine();
        System.out.print("Nhap ho nhan vien: ");
        Ho = in.nextLine();
        System.out.print("Nhap ten nhan vien: ");
        Ten = in.nextLine();
        System.out.print("Nhap so dien thoai nhan vien: ");
        SoDT = in.nextLine();
        System.out.print("Nhap muc luong hang thang cua nhan vien: ");
        MucLuong = Long.parseLong(in.nextLine());
        System.out.print("Nhap nam vao lam cua nhan vien: ");
        NamVaoLam = Integer.parseInt(in.nextLine());
    }
    public void xuat()
    {
        System.out.printf("\n MaNV %20s Ho %20s Ten %20s SDT %20s Muc luong %20s, Nam vao lam","","","","","");
        System.out.printf("\n%s %20s %20s %20s %20d %20d", MaNV, Ho, Ten, SoDT, MucLuong, NamVaoLam);
    }
    public void setMaNV(String manv)
    {
        MaNV = manv;
    }
    public String getMaNV()
    {
        return MaNV;
    }
    public void setHo(String ho)
    {
        Ho = ho;
    }
    public String getHo()
    {
        return Ho;
    }
    public void setTen(String ten)
    {
        Ten = ten;
    }
    public String getTen()
    {
        return Ten;
    }
    public void setSDT(String sdt)
    {
        SoDT = sdt;
    }
    public String getSDT()
    {
        return SoDT;
    }
    public void setLuong(long luong)
    {
        MucLuong = luong;
    }
    public long getLuong()
    {
        return MucLuong;
    }
    public void setNVL(int nvl)
    {
        NamVaoLam = nvl;
    }
    public int getNVL()
    {
        return NamVaoLam;
    }
    
}
