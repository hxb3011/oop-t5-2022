package lthdt;

import java.util.Scanner;

class MayTinhBan extends SanPham
{
    private String PhuKien;
    public MayTinhBan() {}
    public MayTinhBan(String masp, String tensp, long dongia, int tonkho, ChiTietSanPham chitiet, String phukien)
    {
        super(masp, tensp, dongia, tonkho, chitiet);
        PhuKien = phukien;
    }
    public MayTinhBan(SanPham sp, String phukien)
    {
        super(sp);
        PhuKien = phukien;       
    }
    @Override public void nhap()
    {
        Scanner in = new Scanner(System.in);
        super.nhap();
        System.out.print("Nhap cac phu kien di kem: ");
        PhuKien = in.nextLine();       
    }
    @Override public void xuat()
    {
        super.xuat();
        System.out.printf("\nPhuKien \n %s", PhuKien);
    }
    public void setPK(String phukien)
    {
        PhuKien = phukien;
    }
    public String getPK()
    {
        return PhuKien;
    }
}
