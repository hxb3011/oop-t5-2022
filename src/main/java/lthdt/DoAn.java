package lthdt;

public class DoAn {

    public static void main(String[] args) 
    {
        MayTinhBan sp1 = new MayTinhBan();
        sp1.nhap();
        sp1.xuat();
        
        Laptop sp2 = new Laptop();
        sp2.nhap();
        sp2.xuat();
        
        KhachHang kh = new KhachHang();
        kh.nhap();
        kh.xuat();
        
        NhanVien nv = new NhanVien();
        nv.nhap();
        nv.xuat();
    }
}
