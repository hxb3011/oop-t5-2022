
package lthdt;

import java.util.Scanner;
import java.util.Arrays;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class DSSP 
{
    private SanPham[] dssp;
    private int n;
    public DSSP() { dssp = new SanPham[0]; n = 0;};
    public DSSP(SanPham[] dssp2, int n)
    {
        this.n = n;
        dssp = dssp2;
    }
    public DSSP(DSSP dssp2)
    {
        dssp = dssp2.dssp;
        n = dssp2.n;
    }
    public SanPham[] getDSSP()
    {
        return dssp;
    }
    public void setDSSP(SanPham[] dssp)
    {
        this.dssp = dssp;  
    }
    public int getN()
    {
        return n;
    }
    public void setN(int n)
    {
        this.n = n;  
    }
    public void ghiFile() throws IOException
    {
        try
        {
        DataOutputStream output = new DataOutputStream (new FileOutputStream("/Users/homeuser/Downloads/oop-t5-2022/src/main/java/SanPham.txt"));
        for(int i = 0; i < n; i++)
        {
            if( dssp[i] instanceof MayTinhBan )
            {
                MayTinhBan mt = (MayTinhBan) dssp[i];
                output.writeBytes("1");
                output.writeBytes(System.lineSeparator());
                mt.ghiFile(output);
            }
            else
            {
                Laptop lt = (Laptop) dssp[i];
                output.writeBytes("2");
                output.writeBytes(System.lineSeparator());
                lt.ghiFile(output);       
            }
        }
        output.close();
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public void ghithem() throws IOException
    {
        try
        {
        DataOutputStream output = new DataOutputStream (new FileOutputStream("/Users/homeuser/Downloads/oop-t5-2022/src/main/java/SanPham.txt", Boolean.TRUE));
        for(int i = 0; i < n; i++)
        {
            if( dssp[i] instanceof MayTinhBan )
            {
                MayTinhBan mt = (MayTinhBan) dssp[i];
                output.writeBytes("1");
                output.writeBytes(System.lineSeparator());
                mt.ghiFile(output);
                output.flush();
            }
            else
            {
                Laptop lt = (Laptop) dssp[i];
                output.writeBytes("2");
                output.writeBytes(System.lineSeparator());
                lt.ghiFile(output);
                output.flush();
            }
        }
        output.close();
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public void docFile() throws IOException
    {
        dssp = new SanPham[0];
        int i = 0;
        try
        {      
            FileInputStream f = new FileInputStream("/Users/homeuser/Downloads/oop-t5-2022/src/main/java/SanPham.txt");
            Scanner in = new Scanner (f);
            while(in.hasNextLine())
            {
                dssp = Arrays.copyOf(dssp, dssp.length+1);
                int loaisp = Integer.parseInt(in.nextLine());
                switch( loaisp )
                {
                    case 1:
                    {
                        MayTinhBan mt = new MayTinhBan();
                        mt.docFile(in);
                        dssp[i] = new MayTinhBan(mt);
                        i++;
                        break;
                    }
                    case 2:
                    {
     
                        Laptop lt = new Laptop();
                        lt.docFile(in);
                        dssp[i] = new Laptop(lt);
                        i++;
                        break;
                    }
                    default:
                    {
                        System.out.print("\nLoi!");
                        break;
                    }
                }
            }
            in.close();
        }catch(FileNotFoundException e) 
        { 
            e.printStackTrace();
        }
        finally
        {
            n = i;
        }         
    }
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("\nNhap so san pham trong danh sach: ");
        n = Integer.parseInt(in.nextLine());
        dssp = new SanPham[n];
        for (int i = 0; i < n; i++)
        {
            byte flag;
            do
            {
                System.out.print("\nLoai san pham muon them la:");
                System.out.print("\n[1] May tinh ban");
                System.out.print("\n[2] Laptop");
                System.out.print("\nNhap lua chon: ");
                byte lc = Byte.parseByte(in.nextLine());
                switch(lc)
                {
                    case 1:
                    {
                        flag = 1;
                        dssp[i] = new MayTinhBan();
                        dssp[i].nhap();
                        while( check(dssp[i].getMaSP()) )
                        {
                            System.out.print("\nMa san pham nay da ton tai, vui long nhap lai ma san pham: ");
                            String ma =in.nextLine();
                            dssp[i].setMaSP(ma);
                        }                       
                        break;
                    }
                    case 2:
                    {
                        flag = 1;
                        dssp[i] = new Laptop();
                        dssp[i].nhap();
                        while( check(dssp[i].getMaSP()) )
                        {
                            System.out.print("\nMa san pham nay da ton tai, vui long nhap lai ma san pham: ");
                            String ma =in.nextLine();
                            dssp[i].setMaSP(ma);
                        }       
                        break;
                    }
                    default:
                    {
                        System.out.print("\nNhap sai lua chon, vui long nhap lai ");
                        flag = 0;
                        break;
                    }
                }
            }while( flag == 0 );
                
        }

    }
    public boolean check(String masp)
    {
        int i = 0;
        while( i < dssp.length - 1)
        {
            if( dssp[i+1] != null )
            {
                if( dssp[i].getMaSP().equals(masp) )
                {
                    return true;
                }
            }
            i++;
        }
        return false;
    }
    public void xuat()
    {
            Scanner in = new Scanner(System.in);
            byte flag;
            do
            {
                System.out.print("\nXem danh sach:");
                System.out.print("\n[1] May tinh ban");
                System.out.print("\n[2] Laptop");
                System.out.print("\n[3] Tat ca san pham");
                System.out.print("\nNhap lua chon: ");
                byte lc = Byte.parseByte(in.nextLine());
                switch(lc)
                {
                    case 1:
                    {
                        flag = 1;
                        System.out.printf("%-10s %-20s %-10s %-10s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho");
                        for(int i = 0; i < n; i++)
                        {
                            if( dssp[i] instanceof MayTinhBan )
                            {
                                MayTinhBan mtb = (MayTinhBan) dssp[i];
                                mtb.xuat();
                            }
                        }
                        break;
                    }
                    case 2:
                    {
                        flag = 1;
                        System.out.printf("%-10s %-20s %-10s %-5s", "MaSP", "TenSP", "Don gia", "SL ton kho");                     
                        for(int i = 0; i < n; i++)
                        {
                            if( dssp[i] instanceof Laptop )
                            {
                                Laptop lt = (Laptop) dssp[i];
                                lt.xuat();
                            }
                        }
                        break;
                    }
                    case 3:
                    {
                        flag = 1;
                        System.out.printf("%-10s %-20s %-10s %-5s", "MaSP", "TenSP", "Don gia", "SL ton kho");
                        for(int i = 0; i < n; i++)
                        {
                            dssp[i].xuat();
                        }
                        break;
                    }
                    default:
                    {
                        System.out.print("\nNhap sai lua chon, vui long nhap lai ");
                        flag = 0;
                        break;
                    }
                }
            }while( flag == 0 );
    }
    public void them()
    {
        Scanner in = new Scanner(System.in);
        dssp = Arrays.copyOf(dssp, n+1);
        byte flag;
        do
        {
            System.out.print("\nLoai san pham can them la:");
            System.out.print("\n1.May tinh ban");
            System.out.print("\n2.Laptop");
            System.out.print("\nNhap lua chon: ");
            byte lc = Byte.parseByte(in.nextLine());
            switch(lc)
            {
                case 1:
                    {
                        flag = 1;
                        dssp[n] = new MayTinhBan();
                        dssp[n].nhap();
                        while( check(dssp[n].getMaSP()) )
                        {
                            System.out.print("\nMa san pham nay da ton tai, vui long nhap lai ma san pham: ");
                            String ma =in.nextLine();
                            dssp[n].setMaSP(ma);
                        }   
                    }
                case 2:
                    {
                        flag = 1;
                        dssp[n] = new Laptop();
                        dssp[n].nhap();
                        if( check(dssp[n].getMaSP()) )
                        {
                            System.out.print("\nMa san pham nay da ton tai, vui long nhap lai ma san pham: ");
                            String ma =in.nextLine();
                            dssp[n].setMaSP(ma);
                        }   
                    }
                default:
                    {
                        System.out.print("\nNhap sai lua chon, vui long nhap lai ");
                        flag = 0;
                    }
            }
        }while( flag == 0 );
        n++;
        System.out.print("\nThem thanh cong");
    }
    public void them(MayTinhBan sp)
    {
        Scanner in = new Scanner(System.in);
        dssp = Arrays.copyOf(dssp, n+1);
        while( check(sp.getMaSP()) )
        {
            System.out.print("\nMa san pham nay da ton tai, vui long nhap lai ma san pham: ");
            String ma = in.nextLine();
            sp.setMaSP(ma);
        }
        dssp[n] = new MayTinhBan(sp);
    }
    public void them(Laptop sp)
    {
        Scanner in = new Scanner(System.in);
        dssp = Arrays.copyOf(dssp, n+1);
        while( check(sp.getMaSP()) )
        {
            System.out.print("\nMa san pham nay da ton tai, vui long nhap lai ma san pham: ");
            String ma = in.nextLine();
            sp.setMaSP(ma);
        }
        dssp[n] = new Laptop(sp);
    }
    public void themk()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("\nNhap so san pham can them: ");
        byte k = Byte.parseByte(in.nextLine());
        for(int i = 0; i < k; i++)
        {
            them();
        }        
    }
    public void themk(SanPham[] sp)
    {
        for (SanPham sp1 : sp) {
            if (sp1 instanceof MayTinhBan) {
                MayTinhBan mtb = (MayTinhBan) sp1;
                them(mtb);
            }
            if (sp1 instanceof Laptop) {
                Laptop lt = (Laptop) sp1;
                them(lt);
            }
        }        
    }
    public void xoa()
    {
        Scanner in = new Scanner(System.in);
        byte flag;
        do
        {
            System.out.print("\nNhap vi tri can xoa: ");       
            byte vitri = Byte.parseByte(in.nextLine());   
            if( vitri < n && vitri >= 0 )
            {
                for(int i = vitri; i < n-1; i++)
                {
                    dssp[i] = dssp[i+1];
                }
                n--;            
                System.out.print("\nXoa thanh cong"); 
                flag = 1;
            }
            else
            {
                System.out.print("\nVi tri duoc nhap khong hop le, vui long nhap lai");
                flag = 0;    
            }
        }while( flag == 0 );
    }
    public void xoa(String ma)
    {
        int flag = 0;
        for(int i = 0; i < n; i++)
        {
            if( ma.equals(dssp[i].getMaSP() ) )
            {
                
                for(int j = i; j < n-1; j++)
                {
                    dssp[j] = dssp[j+1];
                }
                flag = 1;                
                n--;               
                System.out.print("\nXoa thanh cong");
                break;
            }
        }
        if(flag == 0)
        {
            System.out.print("\nKhong tim thay san pham can xoa:");
        }
        
    }
    public void sua()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("\nNhap ma san pham cua san pham can sua thong tin: ");
        String ma = in.nextLine();
        int flag = 0;
        int vitri = 0;
        for(int i = 0; i < n; i++)
        {
            if( ma.equals(dssp[i].getMaSP() ) )
            {
                vitri = i;
                flag = 1;             
                break;
            }
        }
        if(flag == 1)
        {
            int flag2 = 0;                
            if( dssp[vitri] instanceof MayTinhBan)
            {
                do
                {
                    System.out.print("\n[1] Ma san pham"); 
                    System.out.print("\n[2] Ten san pham");
                    System.out.print("\n[3] Don gia");
                    System.out.print("\n[4] So luong ton kho");
                    System.out.print("\n[5] Chi tiet");
                    System.out.print("\n[6] Phu kien");
                    System.out.print("\nNhap phan can sua: ");
                    int lc = Integer.parseInt(in.nextLine());
                    in = new Scanner(System.in);
                    switch(lc)
                    {
                        case 1:
                        {                    
                            flag2 = 1;
                            System.out.print("\nNhap ma san pham moi:");
                            String mamoi = in.nextLine();
                            while( check(mamoi) )
                            {
                                System.out.print("\nMa san pham nay da ton tai, vui long nhap lai moi: ");
                                mamoi = in.nextLine();
                            }    
                            dssp[vitri].setMaSP(mamoi);           
                            break;
                        }
                        case 2:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap ten san pham moi:");
                            String tenmoi = in.nextLine();
                            dssp[vitri].setTenSP(tenmoi);         
                            break;
                        }
                        case 3:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap don gia moi:");
                            long dgmoi = Long.parseLong(in.nextLine());
                            dssp[vitri].setDG(dgmoi);            
                            break;
                        }
                        case 4:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap so luong ton kho moi:");
                            int tkmoi = Integer.parseInt(in.nextLine());
                            dssp[vitri].setTK(tkmoi);            
                            break;
                        }
                        case 5:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap chi tiet moi:");
                            ChiTietSanPham ctmoi = new ChiTietSanPham();
                            ctmoi.nhap();
                            dssp[vitri].setCTSP(ctmoi);  
                            break;
                        }
                        case 6:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap cac phu kien moi:");
                            String pkmoi = in.nextLine();
                            MayTinhBan mt = (MayTinhBan)dssp[vitri];
                            mt.setPK(pkmoi);     
                            break;
                        }
                        default:
                        {
                            System.out.print("\nNhap sai lua chon, vui long nhap lai ");
                        }  
                    }                                  
                }while( flag2 == 0 );
                System.out.print("\nSua thanh cong");
            }
            else
            {
                do
                {
                    System.out.print("\n[1] Ma san pham"); 
                    System.out.print("\n[2] Ten san pham");
                    System.out.print("\n[3] Don gia");
                    System.out.print("\n[4] So luong ton kho");
                    System.out.print("\n[5] Chi tiet");
                    System.out.print("\n[6] Cam ung");
                    System.out.print("\n[7] Ten ban phim");
                    System.out.print("\n[8] Led ban phim");
                    System.out.print("\n[9] Ten loa");
                    System.out.print("\nNhap phan can sua: ");
                    int lc = Integer.parseInt(in.nextLine());
                    in = new Scanner(System.in);
                    switch(lc)
                    {
                        case 1:
                        {                    
                            flag2 = 1;
                            System.out.print("\nNhap ma san pham moi:");
                            String mamoi = in.nextLine();
                            while( check(mamoi) )
                            {
                                System.out.print("\nMa san pham nay da ton tai, vui long nhap lai moi: ");
                                mamoi = in.nextLine();
                            }    
                            dssp[vitri].setMaSP(mamoi);           
                            break;
                        }
                        case 2:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap ten san pham moi:");
                            String tenmoi = in.nextLine();
                            dssp[vitri].setTenSP(tenmoi);         
                            break;
                        }
                        case 3:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap don gia moi:");
                            long dgmoi = Long.parseLong(in.nextLine());
                            dssp[vitri].setDG(dgmoi);            
                            break;
                        }
                        case 4:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap so luong ton kho moi:");
                            int tkmoi = Integer.parseInt(in.nextLine());
                            dssp[vitri].setTK(tkmoi);            
                            break;
                        }
                        case 5:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap chi tiet moi:");
                            ChiTietSanPham ctmoi = new ChiTietSanPham();
                            ctmoi.nhap();
                            dssp[vitri].setCTSP(ctmoi);  
                            break;
                        }
                        case 6:
                        {
                            flag2 = 1;
                            Laptop lt = (Laptop)dssp[vitri];
                            lt.nhapCamUng(); 
                            break;
                        }
                        case 7:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap ten ban phim moi:");
                            String bpmoi = in.nextLine();
                            Laptop lt = (Laptop)dssp[vitri];
                            lt.setBP(bpmoi);  
                            break;
                        }
                        case 8:
                        {
                            flag2 = 1;
                            Laptop lt = (Laptop)dssp[vitri];
                            lt.nhapLed(); 
                            break;
                        }
                        case 9:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap chat luong loa moi:");
                            String loamoi = in.nextLine();
                            Laptop lt = (Laptop)dssp[vitri]; 
                            lt.setLoa(loamoi);  
                            break;
                        }
                        default:
                        {
                            System.out.print("\nNhap sai lua chon, vui long nhap lai ");
                        }  
                    }                                  
                }while( flag2 == 0 );
                System.out.print("\nSua thanh cong");
            }
        }
        else
        {
            System.out.print("\nKhong tim thay san pham can sua");
        }   
    }
    public void sua(String ma)
    {
        Scanner in = new Scanner(System.in);
        int flag = 0;
        int vitri = 0;
        for(int i = 0; i < n; i++)
        {
            if( ma.equals(dssp[i].getMaSP() ) )
            {
                vitri = i;
                flag = 1;             
                break;
            }
        }
        if(flag == 1)
        {
            int flag2 = 0;                
            if( dssp[vitri] instanceof MayTinhBan)
            {
                do
                {
                    System.out.print("\n1. Ma san pham"); 
                    System.out.print("\n2. Ten san pham");
                    System.out.print("\n3. Don gia");
                    System.out.print("\n4. So luong ton kho");
                    System.out.print("\n5. Chi tiet");
                    System.out.print("\n6. Phu kien");
                    System.out.print("\nNhap phan can sua: ");
                    int lc = Integer.parseInt(in.nextLine());
                    in = new Scanner(System.in);
                    switch(lc)
                    {
                        case 1:
                        {                    
                            flag2 = 1;
                            System.out.print("\nNhap ma san pham moi:");
                            String mamoi = in.nextLine();
                            while( check(mamoi) )
                            {
                                System.out.print("\nMa san pham nay da ton tai, vui long nhap lai moi: ");
                                mamoi = in.nextLine();
                            }    
                            dssp[vitri].setMaSP(mamoi);           
                            break;
                        }
                        case 2:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap ten san pham moi:");
                            String tenmoi = in.nextLine();
                            dssp[vitri].setTenSP(tenmoi);         
                            break;
                        }
                        case 3:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap don gia moi:");
                            long dgmoi = Long.parseLong(in.nextLine());
                            dssp[vitri].setDG(dgmoi);            
                            break;
                        }
                        case 4:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap so luong ton kho moi:");
                            int tkmoi = Integer.parseInt(in.nextLine());
                            dssp[vitri].setTK(tkmoi);            
                            break;
                        }
                        case 5:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap chi tiet moi:");
                            ChiTietSanPham ctmoi = new ChiTietSanPham();
                            ctmoi.nhap();
                            dssp[vitri].setCTSP(ctmoi);  
                            break;
                        }
                        case 6:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap cac phu kien moi:");
                            String pkmoi = in.nextLine();
                            MayTinhBan mt = (MayTinhBan)dssp[vitri];
                            mt.setPK(pkmoi);     
                            break;
                        }
                        default:
                        {
                            System.out.print("\nNhap sai lua chon, vui long nhap lai ");
                        }  
                    }                                  
                }while( flag2 == 0 );
                System.out.print("\nSua thanh cong");
            }
            else
            {
                do
                {
                    System.out.print("\n[1] Ma san pham"); 
                    System.out.print("\n[2] Ten san pham");
                    System.out.print("\n[3] Don gia");
                    System.out.print("\n[4] So luong ton kho");
                    System.out.print("\n[5] Chi tiet");
                    System.out.print("\n[6] Cam ung");
                    System.out.print("\n[7] Ten ban phim");
                    System.out.print("\n[8] Led ban phim");
                    System.out.print("\n[9] Ten loa");
                    System.out.print("\nNhap phan can sua: ");
                    int lc = Integer.parseInt(in.nextLine());
                    in = new Scanner(System.in);
                    switch(lc)
                    {
                        case 1:
                        {                    
                            flag2 = 1;
                            System.out.print("\nNhap ma san pham moi:");
                            String mamoi = in.nextLine();
                            while( check(mamoi) )
                            {
                                System.out.print("\nMa san pham nay da ton tai, vui long nhap lai moi: ");
                                mamoi = in.nextLine();
                            }           
                            dssp[vitri].setMaSP(mamoi);
                            break;
                        }
                        case 2:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap ten san pham moi:");
                            String tenmoi = in.nextLine();
                            dssp[vitri].setTenSP(tenmoi);         
                            break;
                        }
                        case 3:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap don gia moi:");
                            long dgmoi = Long.parseLong(in.nextLine());
                            dssp[vitri].setDG(dgmoi);            
                            break;
                        }
                        case 4:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap so luong ton kho moi:");
                            int tkmoi = Integer.parseInt(in.nextLine());
                            dssp[vitri].setTK(tkmoi);            
                            break;
                        }
                        case 5:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap chi tiet moi:");
                            ChiTietSanPham ctmoi = new ChiTietSanPham();
                            ctmoi.nhap();
                            dssp[vitri].setCTSP(ctmoi);  
                            break;
                        }
                        case 6:
                        {
                            flag2 = 1;
                            Laptop lt = (Laptop)dssp[vitri];
                            lt.nhapCamUng(); 
                            break;
                        }
                        case 7:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap ten ban phim moi:");
                            String bpmoi = in.nextLine();
                            Laptop lt = (Laptop)dssp[vitri];
                            lt.setBP(bpmoi);  
                            break;
                        }
                        case 8:
                        {
                            flag2 = 1;
                            Laptop lt = (Laptop)dssp[vitri];
                            lt.nhapLed(); 
                            break;
                        }
                        case 9:
                        {
                            flag2 = 1;
                            System.out.print("\nNhap chat luong loa moi:");
                            String loamoi = in.nextLine();
                            Laptop lt = (Laptop)dssp[vitri]; 
                            lt.setLoa(loamoi);  
                            break;
                        }
                        default:
                        {
                            System.out.print("\nNhap sai lua chon, vui long nhap lai ");
                        }  
                    }                                  
                }while( flag2 == 0 );
                System.out.print("\nSua thanh cong");
            }
        }
        else
        {
            System.out.print("\nKhong tim thay san pham can sua");
        }
    }   

    public void timkiem()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("\n 1.Tim theo ma san pham"); 
        System.out.print("\n 2.Tim theo ten san pham");
        System.out.print("\n 3.Tim theo don gia");
        System.out.print("\n 4.Tim theo so hang ton kho");
        System.out.print("\n 5.Tim theo chi tiet san pham");
        System.out.print("\n 6.Tim theo ten phu kien cua may tinh ban");
        System.out.print("\n 7.Tim laptop co/khong co cam ung");
        System.out.print("\n 8.Tim ten ban phim cua laptop");
        System.out.print("\n 9.Tim laptop co/khong co led ban phim");
        System.out.print("\n 10.Tim chat luong loa cua laptop");
        System.out.print("\nNhap lua chon: ");
        int flag = 0;
        do
        {
            byte lc = Byte.parseByte(in.nextLine());
            in = new Scanner(System.in);
            switch(lc)
            {
                case 1:
                {
                    System.out.print("\nNhap ma san pham can tim: ");
                    String ma = in.nextLine();
                    timma(ma);
                    flag = 1;
                    break;
                }
                case 2:
                {
                    System.out.print("\nNhap ten san pham can tim: ");
                    String ten = in.nextLine();
                    timten(ten);
                    flag = 1;
                    break;
                }
                case 3:
                {
                    System.out.print("\nNhap don gia can tim: ");
                    long dongia = Long.parseLong(in.nextLine());
                    timdongia(dongia);
                    flag = 1;
                    break;
                }
                case 4:
                {
                    System.out.print("\nNhap so luong ton kho can tim: ");
                    int tonkho = Integer.parseInt(in.nextLine());
                    timtonkho(tonkho);
                    flag = 1;
                    break;
                }
                case 5:
                {
                    do
                    {
                        System.out.print("\n[1]. Tim theo CPU"); 
                        System.out.print("\n[2]. Tim theo GPU");
                        System.out.print("\n[3]. Tim theo so RAM");
                        System.out.print("\n[4]. Tim theo he dieu hanh");
                        System.out.print("\n[5]. Tim theo ten man hinh");
                        System.out.print("\n[6]. Tim theo nam san xuat");
                        System.out.print("\nNhap lua chon: ");
                        byte luachon = Byte.parseByte(in.nextLine());
                        switch(luachon)
                        {
                            case 1:
                            {
                                System.out.print("\nNhap CPU can tim: ");
                                String cpu = in.nextLine();
                                timma(cpu);
                                flag = 1;
                                break;
                            }
                            case 2:
                            {
                                System.out.print("\nNhap GPU can tim: ");
                                String gpu = in.nextLine();
                                timgpu(gpu);
                                flag = 1;
                                break;
                            }
                            case 3:
                            {
                                System.out.print("\nNhap so RAM can tim: ");
                                int ram = Integer.parseInt(in.nextLine());
                                timram(ram);
                                flag = 1;
                                break;
                            }
                            case 4:
                            {
                                System.out.print("\nNhap ten man hinh can tim: ");
                                String manhinh = in.nextLine();
                                timmanhinh(manhinh);
                                flag = 1;
                                break;
                            }
                            case 5:
                            {
                                System.out.print("\nNhap he dieu hanh can tim: ");
                                String hdh = in.nextLine();
                                timhdh(hdh);
                                flag = 1;
                                break;
                            }
                            case 6:
                            {
                                System.out.print("\nNhap ten man hinh can tim: ");
                                int namsx = Integer.parseInt(in.nextLine());
                                timnamsx(namsx);
                                flag = 1;
                                break;
                            }
                            default:
                            {
                                System.out.print("\nNhap sai lua chon, vui long nhap lai");
                                flag = 0;                                       
                            }
                        }
                    }while(flag == 0);
                }
                case 6:
                {
                    System.out.print("\nNhap phu kien may tinh ban can tim: ");
                    String phukien = in.nextLine();
                    timphukien(phukien);
                    flag = 1;
                    break;
                }
                case 7:
                {
                    System.out.print("\nNhap lua chon: \n[1] Co \n[2] Khong");
                    boolean camung = false;
                    byte luachon;
                    while( flag == 0 )
                    {
                        System.out.print("\nNhap lua chon: \n[1] Co \n[2] Khong");
                        luachon = Byte.parseByte(in.nextLine());
                        switch(luachon)
                        {
                            case 1:
                            {
                                camung = true;
                                flag = 1;
                            }
                            case 2:
                            {
                                camung = false;
                                flag = 1;
                            }
                            default:
                            {
                                System.out.print("Nhap sai lua chon, vui long nhap lai");
                                flag = 0;
                            }
                        }
                    }
                    timcamung(camung);
                    break;
                }
                case 8:
                {
                    System.out.print("\nNhap ten ban phim can tim: ");
                    String banphim = in.nextLine();
                    timbanphim(banphim);
                    flag = 1;
                    break;
                }
                case 9:
                {
                    System.out.print("\nNhap lua chon: \n[1] Co \n[2] Khong");
                    boolean led = false;
                    byte luachon;
                    while( flag == 0 )
                    {
                        System.out.print("\nNhap lua chon: \n[1] Co \n[2] Khong");
                        luachon = Byte.parseByte(in.nextLine());
                        switch(luachon)
                        {
                            case 1:
                            {
                                led = true;
                                flag = 1;
                            }
                            case 2:
                            {
                                led = false;
                                flag = 1;
                            }
                            default:
                            {
                                System.out.print("Nhap sai lua chon, vui long nhap lai");
                                flag = 0;
                            }
                        }
                    }
                    timledbanphim(led);
                    break;                    
                }
                case 10:
                {
                    System.out.print("\nNhap chat luong loa can tim: ");
                    String loa = in.nextLine();
                    timchatluongloa(loa);
                    flag = 1;
                    break;
                }
                default:
                {
                    System.out.print("Nhap sai lua chon, vui long nhap lai");
                    flag = 0;
                }
            }            
        }while( flag == 0 );
        
    }
    public Laptop timsanpham(String ma)
    {
        for(int i = 0; i < n; i++)
        {
            if( ma.equals(dssp[i].getMaSP()) )
            {
                System.out.print("\nDa tim thay san pham:\n");
                System.out.printf("%-10s %-20s %-10s %-10s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho");
                dssp[i].xuat();
                Laptop lt = new Laptop( (Laptop)dssp[i] );
                return lt;
            }
        }
        System.out.print("\nKhong tim thay san pham");
        return null;
    }
    public SanPham timma(String ma)
    {
        for(int i = 0; i < n; i++)
        {
            if( ma.equals(dssp[i].getMaSP()) )
            {
                System.out.print("\nDa tim thay san pham:\n");
                System.out.printf("%-10s %-20s %-10s %-10s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho");
                dssp[i].xuat();
                return dssp[i];
            }
        }
        System.out.print("\nKhong tim thay san pham");
        return null;
    }
    public void timten(String ten)
    {
        SanPham[] sp = new SanPham[0];
        int sl = 0;
        for(int i = 0; i < n; i++)
        {
            if( dssp[i].getTenSP().toLowerCase().contains(ten.toLowerCase()) )
            {
                sp = Arrays.copyOf(sp, sl+1);
                if( dssp[i] instanceof MayTinhBan)
                {
                    sp[sl] = new MayTinhBan();
                    sp[sl] = (MayTinhBan) dssp[i];
                }
                else
                {
                    sp[sl] = new Laptop();
                    sp[sl] = (Laptop) dssp[i];
                }
                sl++;
            }
        }
        System.out.printf("\nSo ket qua tim thay: %d \n", sl);
        System.out.printf("%-10s %-20s %-10s %-10s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho");
        for(int i = 0; i < sl; i++)
        {
            sp[i].xuat();
        }
    }
    public void timdongia(long gia)
    {
        SanPham[] sp = new SanPham[0];
        int sl = 0;
        for(int i = 0; i < n; i++)
        {
            if( dssp[i].getDG() == gia )
            {
                sp = Arrays.copyOf(sp, sl+1);
                if( dssp[i] instanceof MayTinhBan)
                {
                    sp[sl] = new MayTinhBan();
                    sp[sl] = (MayTinhBan) dssp[i];
                }
                else
                {
                    sp[sl] = new Laptop();
                    sp[sl] = (Laptop) dssp[i];
                }
                sl++;
            }
        }
        System.out.printf("\nSo ket qua tim thay: %d \n", sl);
        System.out.printf("%-10s %-20s %-10s %-10s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho");
        for(int i = 0; i < sl; i++)
        {
            sp[i].xuat();
        }
    }
    public void timtonkho(int tonkho)
    {
        SanPham[] sp = new SanPham[0];
        int sl = 0;
        for(int i = 0; i < n; i++)
        {
            if( dssp[i].getTK() == tonkho )
            {
                sp = Arrays.copyOf(sp, sl+1);
                if( dssp[i] instanceof MayTinhBan)
                {
                    sp[sl] = new MayTinhBan();
                    sp[sl] = (MayTinhBan) dssp[i];
                }
                else
                {
                    sp[sl] = new Laptop();
                    sp[sl] = (Laptop) dssp[i];
                }
                sl++;
            }
        }
        System.out.printf("\nSo ket qua tim thay: %d \n", sl);
        System.out.printf("%-10s %-20s %-10s %-10s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho");
        for(int i = 0; i < sl; i++)
        {
            sp[i].xuat();
        }
    }
    public void timphukien(String phukien)
    {
        MayTinhBan[] sp = new MayTinhBan[0];
        int sl = 0;
        for(int i = 0; i < n; i++)
        {
            if( dssp[i] instanceof MayTinhBan )
            {
                MayTinhBan mt = (MayTinhBan) dssp[i];
                if( mt.getPK().toLowerCase().contains(phukien.toLowerCase()) )
                {
                    sp = Arrays.copyOf(sp, sl+1);
                    sp[sl] = mt;
                    sl++;
                }
            }
        }
        System.out.printf("\nSo ket qua tim thay: %d \n", sl);
        System.out.printf("%-10s %-20s %-10s %-20s %-20s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho", "Phu Kien");
        for(int i = 0; i < sl; i++)
        {
            sp[i].xuat();
            System.out.printf(" %-20s", sp[i].getPK());
        }
    }
    public void timcamung(boolean camung)
    {
        Laptop[] sp = new Laptop[0];
        int sl = 0;
        for(int i = 0; i < n; i++)
        {
            if( dssp[i] instanceof Laptop )
            {
                Laptop lt = (Laptop) dssp[i];
                if( lt.getCamUng() == camung )
                {
                    sp = Arrays.copyOf(sp, sl+1);
                    sp[sl] = lt;
                    sl++;
                }
            }
        }
        System.out.printf("\nSo ket qua tim thay: %d \n", sl);
        System.out.printf("%-10s %-20s %-10s %-20s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho");
        for(int i = 0; i < sl; i++)
        {
            sp[i].xuat();
        }
    }
    public void timbanphim(String banphim)
    {
        Laptop[] sp = new Laptop[0];
        int sl = 0;
        for(int i = 0; i < n; i++)
        {
            if( dssp[i] instanceof Laptop )
            {
                Laptop lt = (Laptop) dssp[i];
                if( lt.getBP().toLowerCase().contains(banphim.toLowerCase()) )
                {
                    sp = Arrays.copyOf(sp, sl+1);
                    sp[sl] = lt;
                    sl++;
                }
            }
        }
        System.out.printf("\nSo ket qua tim thay: %d \n", sl);
        System.out.printf("%-10s %-20s %-10s %-20s %-20s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho", "Ban Phim");
        for(int i = 0; i < sl; i++)
        {
            sp[i].xuat();
            System.out.printf(" %-20s", sp[i].getBP());
        }
    }
    public void timledbanphim(boolean led)
    {
        Laptop[] sp = new Laptop[0];
        int sl = 0;
        for(int i = 0; i < n; i++)
        {
            if( dssp[i] instanceof Laptop )
            {
                Laptop lt = (Laptop) dssp[i];
                if( lt.getLed() == led )
                {
                    sp = Arrays.copyOf(sp, sl+1);
                    sp[sl] = lt;
                    sl++;
                }
            }
        }
        System.out.printf("\nSo ket qua tim thay: %d \n", sl);
        System.out.printf("%-10s %-20s %-10s %-20s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho");
        for(int i = 0; i < sl; i++)
        {
            sp[i].xuat();
        }
    }
    public void timchatluongloa(String loa)
    {
        Laptop[] sp = new Laptop[0];
        int sl = 0;
        for(int i = 0; i < n; i++)
        {
            if( dssp[i] instanceof Laptop )
            {
                Laptop lt = (Laptop) dssp[i];
                if( lt.getLoa().toLowerCase().contains(loa.toLowerCase()) )
                {
                    sp = Arrays.copyOf(sp, sl+1);
                    sp[sl] = lt;
                    sl++;
                }
            }
        }
        System.out.printf("\nSo ket qua tim thay: %d \n", sl);
        System.out.printf("%-10s %-20s %-10s %-20s %-20s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho", "Loa");
        for(int i = 0; i < sl; i++)
        {
            sp[i].xuat();
            System.out.printf(" %-20s", sp[i].getLoa());
        }
    }
    public void timcpu(String cpu)
    {
        SanPham[] sp = new SanPham[0];
        int sl = 0;
        for(int i = 0; i < n; i++)
        {
            if( dssp[i].getCTSP().getCPU().toLowerCase().contains(cpu.toLowerCase()) )
            {
                sp = Arrays.copyOf(sp, sl+1);
                if( dssp[i] instanceof MayTinhBan)
                {
                    sp[sl] = new MayTinhBan();
                    sp[sl] = (MayTinhBan) dssp[i];
                }
                else
                {
                    sp[sl] = new Laptop();
                    sp[sl] = (Laptop) dssp[i];
                }
                sl++;
            }
        }
        System.out.printf("\nSo ket qua tim thay: %d \n", sl);
        System.out.printf("%-10s %-20s %-10s %-20s %-20s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho", "CPU");
        for(int i = 0; i < sl; i++)
        {
            sp[i].xuat();
            System.out.printf(" %-20s", sp[i].getCTSP().getCPU());
        }
    }
    public void timgpu(String gpu)
    {
        SanPham[] sp = new SanPham[0];
        int sl = 0;
        for(int i = 0; i < n; i++)
        {
            if( dssp[i].getCTSP().getGPU().toLowerCase().contains(gpu.toLowerCase()) )
            {
                sp = Arrays.copyOf(sp, sl+1);
                if( dssp[i] instanceof MayTinhBan)
                {
                    sp[sl] = new MayTinhBan();
                    sp[sl] = (MayTinhBan) dssp[i];
                }
                else
                {
                    sp[sl] = new Laptop();
                    sp[sl] = (Laptop) dssp[i];
                }
                sl++;
            }
        }
        System.out.printf("\nSo ket qua tim thay: %d \n", sl);
        System.out.printf("%-10s %-20s %-10s %-20s %-20s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho", "GPU");
        for(int i = 0; i < sl; i++)
        {
            sp[i].xuat();
            System.out.printf(" %-20s", sp[i].getCTSP().getGPU());
        }
    }
    public void timram(int ram)
    {
        SanPham[] sp = new SanPham[0];
        int sl = 0;
        for(int i = 0; i < n; i++)
        {
            if( dssp[i].getCTSP().getRAM() == ram )
            {
                sp = Arrays.copyOf(sp, sl+1);
                if( dssp[i] instanceof MayTinhBan)
                {
                    sp[sl] = new MayTinhBan();
                    sp[sl] = (MayTinhBan) dssp[i];
                }
                else
                {
                    sp[sl] = new Laptop();
                    sp[sl] = (Laptop) dssp[i];
                }
                sl++;
            }
        }
        System.out.printf("\nSo ket qua tim thay: %d \n", sl);
        System.out.printf("%-10s %-20s %-10s %-20s %-20s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho","RAM");
        for(int i = 0; i < sl; i++)
        {
            sp[i].xuat();
            System.out.printf(" -%20d",sp[i].getCTSP().getRAM());
        }
    }
    public void timmanhinh(String manhinh)
    {
        SanPham[] sp = new SanPham[0];
        int sl = 0;
        for(int i = 0; i < n; i++)
        {
            if( dssp[i].getCTSP().getMH().toLowerCase().contains(manhinh.toLowerCase()) )
            {
                sp = Arrays.copyOf(sp, sl+1);
                if( dssp[i] instanceof MayTinhBan)
                {
                    sp[sl] = new MayTinhBan();
                    sp[sl] = (MayTinhBan) dssp[i];
                }
                else
                {
                    sp[sl] = new Laptop();
                    sp[sl] = (Laptop) dssp[i];
                }
                sl++;
            }
        }
        System.out.printf("\nSo ket qua tim thay: %d \n", sl);
        System.out.printf("%-10s %-20s %-10s %-20s %-20s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho", "Man Hinh");
        for(int i = 0; i < sl; i++)
        {
            sp[i].xuat();
            System.out.printf(" %-20s", sp[i].getCTSP().getMH());
        }
    }
    public void timhdh(String hdh)
    {
        SanPham[] sp = new SanPham[0];
        int sl = 0;
        for(int i = 0; i < n; i++)
        {
            if( dssp[i].getCTSP().getHDH().toLowerCase().contains(hdh.toLowerCase()) )
            {
                sp = Arrays.copyOf(sp, sl+1);
                if( dssp[i] instanceof MayTinhBan)
                {
                    sp[sl] = new MayTinhBan();
                    sp[sl] = (MayTinhBan) dssp[i];
                }
                else
                {
                    sp[sl] = new Laptop();
                    sp[sl] = (Laptop) dssp[i];
                }
                sl++;
            }
        }
        System.out.printf("\nSo ket qua tim thay: %d \n", sl);
        System.out.printf("%-10s %-20s %-10s %-20s %-20s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho", "He Dieu Hanh");
        for(int i = 0; i < sl; i++)
        {
            sp[i].xuat();
            System.out.printf(" %-20s", sp[i].getCTSP().getHDH());
        }
    }
    public void timnamsx(int namsx)
    {
        SanPham[] sp = new SanPham[0];
        int sl = 0;
        for(int i = 0; i < n; i++)
        {
            if( dssp[i].getCTSP().getNSX()== namsx )
            {
                sp = Arrays.copyOf(sp, sl+1);
                if( dssp[i] instanceof MayTinhBan)
                {
                    sp[sl] = new MayTinhBan();
                    sp[sl] = (MayTinhBan) dssp[i];
                }
                else
                {
                    sp[sl] = new Laptop();
                    sp[sl] = (Laptop) dssp[i];
                }
                sl++;
            }
        }
        System.out.printf("\nSo ket qua tim thay: %d \n", sl);
        System.out.printf("%-10s %-20s %-10s %-20s %-20s", "MaSP", "TenSP", "Don Gia", "SL Ton Kho","RAM");
        for(int i = 0; i < sl; i++)
        {
            sp[i].xuat();
            System.out.printf(" -%20d",sp[i].getCTSP().getNSX());
        }
    }
/*
    public void thongke()
    {
        int soluong = 0;
        for(int i = 0; i < n; i++)
        {
            soluong++;
        }
        System.out.print("\n");
    }
*/   

}
