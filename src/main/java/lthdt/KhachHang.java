package lthdt;

import java.util.Scanner;

public class KhachHang 
{
    private String MaKH;
    private String Ho;
    private String Ten;
    private String SoDT;
    private String DiaChiKH;
    public KhachHang() {}
    public KhachHang(String makh, String ho, String ten, String sdt, String diachi)
    {
        MaKH = makh;
        Ho = ho;
        Ten = ten;
        SoDT = sdt;
        DiaChiKH = diachi;
    }
    public KhachHang(KhachHang kh)
    {
        MaKH = kh.MaKH;
        Ho = kh.Ho;
        Ten = kh.Ten;
        SoDT = kh.SoDT;
        DiaChiKH = kh.DiaChiKH;
    }
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("\nNhap ma khach hang: ");
        MaKH = in.nextLine();
        System.out.print("Nhap ho khach hang: ");
        Ho = in.nextLine();
        System.out.print("Nhap ten khach hang: ");
        Ten = in.nextLine();
        System.out.print("Nhap so dien thoai khach hang: ");
        SoDT = in.nextLine();
        System.out.print("Nhap dia chi khach hang: ");
        DiaChiKH = in.nextLine();
    }
    public void xuat()
    {
        System.out.printf("\n MaKH %20s Ho %20s Ten %20s SDT %20s Dia chi","","","","");
        System.out.printf("\n%s %20s %20s %20s %20s", MaKH, Ho, Ten, SoDT, DiaChiKH);
    }
    public void setMaKH(String makh)
    {
        MaKH = makh;
    }
    public String getMaKH()
    {
        return MaKH;
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
    public void setDiaChi(String diachi)
    {
        DiaChiKH = diachi;
    }
    public String getDiaChi()
    {
        return DiaChiKH;
    }
    
    
}
