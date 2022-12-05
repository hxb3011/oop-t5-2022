package lthdt;

import java.io.DataOutputStream;
import java.io.IOException;
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
    public MayTinhBan(MayTinhBan sp)
    {
        super(sp);
        PhuKien = sp.getPK();
    }
    @Override public void docFile(Scanner in)
    {
        super.docFile(in);
        PhuKien = in.nextLine();
    }
    @Override public void ghiFile(DataOutputStream output) throws IOException
    {
        super.ghiFile(output);
        output.writeBytes(PhuKien);
        output.writeBytes(System.lineSeparator());
    }
    @Override public void nhap()
    {
        super.nhap();
        nhapPhuKien();     
    }
    public void nhapPhuKien()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap cac phu kien di kem: ");
        PhuKien = in.nextLine();  
    }
    @Override public void xuat()
    {
        super.xuat();
    }
    public void setPK(String phukien)
    {
        PhuKien = phukien;
    }
    public String getPK()
    {
        return PhuKien;
    }
    @Override public void xuatChiTiet()
    {
        super.xuatChiTiet();
        System.out.printf("\n%s: %s","Phu kien", PhuKien);
    }
}
