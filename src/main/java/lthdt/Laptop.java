package lthdt;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;


class Laptop extends SanPham
{
    private boolean CamUng;
    private String BanPhim;
    private boolean LedBanPhim;
    private String Loa;
    public Laptop() {}
    public Laptop(String masp, String tensp, long dongia, int tonkho, ChiTietSanPham chitiet, boolean camung, String banphim, boolean led, String loa)
    {
        super(masp, tensp, dongia, tonkho, chitiet);
        CamUng = camung;
        LedBanPhim = led;
        BanPhim = banphim;  
        Loa = loa;
    }
    public Laptop(Laptop sp)
    {
        super(sp);
        CamUng = sp.getCamUng();
        BanPhim = sp.getBP();
        LedBanPhim = sp.getLed();
        Loa = sp.getLoa();
    }
    @Override public void docFile(Scanner in)
    {
        super.docFile(in);
        CamUng = Boolean.parseBoolean(in.nextLine());
        BanPhim = in.nextLine();
        LedBanPhim = Boolean.parseBoolean(in.nextLine());
        Loa = in.nextLine();       
    }
    @Override public void ghiFile(DataOutputStream output) throws IOException
    {
        super.ghiFile(output);
        output.writeBytes(Boolean.toString(CamUng));
        output.writeBytes(System.lineSeparator());
        output.writeBytes(BanPhim);
        output.writeBytes(System.lineSeparator());
        output.writeBytes(Boolean.toString(LedBanPhim));
        output.writeBytes(System.lineSeparator());
        output.writeBytes(Loa);
        output.writeBytes(System.lineSeparator());
    }
    @Override public void nhap()
    {
        super.nhap();
        nhapCamUng();
        nhapBanPhim();
        nhapLed();
        nhapLoa();   
    }
    public void nhapCamUng()
    {
        Scanner in = new Scanner(System.in);
        int flag = 0;
        while( flag == 0 )
        {
            System.out.print("San pham co man hinh cam ung khong?\n[1]Co\n[2]Khong \n");
            byte lc = Byte.parseByte(in.nextLine());
            switch(lc)
            {
                case 1:
                {
                    CamUng = true;
                    flag = 1;
                    break;
                }
                case 2:
                { 
                    CamUng = false;
                    flag = 2;
                    break;
                }
                default:
                {
                    System.out.println("Nhap sai lua chon, vui long nhap lai");
                }
            }           
        }
    }
    public void nhapBanPhim()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap ten ban phim: ");
        BanPhim = in.nextLine();  
    }
    public void nhapLed()
    {
        Scanner in = new Scanner(System.in);
        int flag = 0;
        while( flag == 0 )
        {
            System.out.print("San pham co Led ban phim khong?\n[1]Co\n[2]Khong \n");
            byte lc = Byte.parseByte(in.nextLine());
            switch(lc)
            {
                case 1:
                {
                    LedBanPhim = true;
                    flag = 1;
                    break;
                }
                case 2:
                { 
                    LedBanPhim = false;
                    flag = 2;
                    break;
                }
                default:
                {
                    System.out.println("Nhap sai lua chon, vui long nhap lai");
                }
            }           
        }
    }
    public void nhapLoa()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap chat luong loa: ");
        Loa = in.nextLine();
    }
    @Override public void xuat()
    {
        super.xuat();
    }
    @Override public void xuatChiTiet()
    {
        super.xuatChiTiet();
        if( CamUng )
        {
            if( LedBanPhim )
            {
                System.out.printf("\n%s: %s \n%s: %s \n%s: %s \n%s: %s","Cam ung",  "Co", "Ban phim:",BanPhim, "Led ban phim","Co", "Chat luong loa",Loa);
            }
            else
            {
                System.out.printf("\n%s: %s \n%s: %s \n%s: %s \n%s: %s","Cam ung",  "Co", "Ban phim:",BanPhim, "Led ban phim","Khong", "Chat luong loa",Loa);
            }
        }
        else
        {
            if( LedBanPhim )
            {
                System.out.printf("\n%s: %s \n%s: %s \n%s: %s \n%s: %s","Cam ung",  "Khong", "Ban phim:",BanPhim, "Led ban phim","Co", "Chat luong loa",Loa);
            }
            else
            {
                System.out.printf("\n%s: %s \n%s: %s \n%s: %s \n%s: %s","Cam ung",  "Khong", "Ban phim:",BanPhim, "Led ban phim","Khong", "Chat luong loa",Loa);
            }          
        }
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
    public void setLed(boolean led)
    {
        LedBanPhim = led;
    }
    public boolean getLed()
    {
        return LedBanPhim;
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
