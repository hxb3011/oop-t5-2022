package lthdt;

import java.util.Scanner;

class Laptop extends SanPham
{
    private boolean CamUng;
    private String BanPhim;
    private String Loa;
    public Laptop() {}
    public Laptop(String masp, String tensp, long dongia, int tonkho, ChiTietSanPham chitiet, boolean camung, String banphim, String loa)
    {
        super(masp, tensp, dongia, tonkho, chitiet);
        CamUng = camung;
        BanPhim = banphim;
        Loa = loa;
    }
    public Laptop(SanPham sp, boolean camung, String banphim, String loa)
    {
        super(sp);
        CamUng = camung;
        BanPhim = banphim;
        Loa = loa;
    }
    @Override public void nhap()
    {
        Scanner in = new Scanner(System.in);
        super.nhap();
        System.out.print("Co cam ung?(True/False): ");
        CamUng = Boolean.parseBoolean(in.nextLine());
        System.out.print("Nhap ten ban phim: ");
        BanPhim = in.nextLine();    
        System.out.print("Nhap ten loa: ");
        Loa = in.nextLine();    
    }
    @Override public void xuat()
    {
        super.xuat();
        System.out.printf("\nCam ung %20s Ban phim %20s Loa","","","");
        System.out.printf("\n%b %20s %20s", CamUng, BanPhim, Loa);
    }
    public void setCamUng(boolean camung)
    {
        CamUng = camung;
    }
    public boolean getCamUng()
    {
        return CamUng;
    }
    public void setBP(String banphim)
    {
        BanPhim = banphim;
    }
    public String getBP()
    {
        return BanPhim;
    }
    public void setLoa(String loa)
    {
        Loa = loa;
    }
    public String getLoa()
    {
        return Loa;
    }
}
