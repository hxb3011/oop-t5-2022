
package doan;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DSNhanVien {
    private NhanVien arr[] = new NhanVien[500];
    private int count;
    private Scanner sc = new Scanner(System.in);
       
    public void nhap(){
        String maNV, hoNV, tenNV;
        int sdt, mucLuong, namVaoLam;
        System.out.println("Nhap co phan tu: ");
        count = Integer.parseInt(sc.nextLine());
        
        for (int i = 0; i < count; i++) {
            System.out.print("Nhap ma nhan vien: ");
            maNV = sc.nextLine();
        
            System.out.print("Nhap ho nhan vien: ");
            hoNV = sc.nextLine();
        
            System.out.print("Nhap ten nhan vien: ");
            tenNV = sc.nextLine();
        
            System.out.println("Nhap so dien thoai: ");
            sdt = Integer.parseInt(sc.nextLine());
            
            System.out.println("Nhap muc luong: ");
            mucLuong = Integer.parseInt(sc.nextLine());
            
            System.out.println("Nhap nam vao lam: ");
            namVaoLam = Integer.parseInt(sc.nextLine());
            
            arr[i] = new NhanVien(maNV, hoNV, tenNV, sdt, mucLuong, namVaoLam);
            
        }
        
    }
    
    public void them(){
        String maNV, hoNV, tenNV;
        int sdt, mucLuong, namVaoLam;
        
        System.out.print("Nhap ma nhan vien: ");
        maNV = sc.nextLine();
        
        System.out.print("Nhap ho nhan vien: ");
        hoNV = sc.nextLine();
        
        System.out.print("Nhap ten nhan vien: ");
        tenNV = sc.nextLine();
        
        System.out.println("Nhap so dien thoai: ");
        sdt = Integer.parseInt(sc.nextLine());
            
        System.out.println("Nhap muc luong: ");
        mucLuong = Integer.parseInt(sc.nextLine());
            
        System.out.println("Nhap nam vao lam: ");
        namVaoLam = Integer.parseInt(sc.nextLine());
            
        arr[count] = new NhanVien(maNV, hoNV, tenNV, sdt, mucLuong, namVaoLam);
        count++;
    }
    
    public void sua(){
        String maNV, hoNV, tenNV;
        int sdt, mucLuong, namVaoLam;
        System.out.println("Nhap ma nhan vien ma ban muon sua: ");
        maNV = sc.nextLine();
        boolean timThay = false;
        
        for (int i = 0; i < count; i++) {
            if(arr[i].getMaNV().equalsIgnoreCase(maNV) == true){
                System.out.print("Nhap ma nhan vien: ");
                maNV = sc.nextLine();
        
                System.out.print("Nhap ho nhan vien: ");
                hoNV = sc.nextLine();
        
                System.out.print("Nhap ten nhan vien: ");
                tenNV = sc.nextLine();
        
                System.out.println("Nhap so dien thoai: ");
                sdt = Integer.parseInt(sc.nextLine());
            
                System.out.println("Nhap muc luong: ");
                mucLuong = Integer.parseInt(sc.nextLine());
            
                System.out.println("Nhap nam vao lam: ");
                namVaoLam = Integer.parseInt(sc.nextLine());
            
                arr[i].setMaNV(maNV);
                arr[i].setHoNV(hoNV);
                arr[i].setTenNV(tenNV);
                arr[i].setSdt(sdt);
                arr[i].setMucLuong(mucLuong);
                arr[i].setNamVaoLam(namVaoLam);
                timThay = true;
                break;
            }
            if(timThay = false) System.out.println("Khong tim thay khach hang nao!!");
        }
    }
    
    public void xuat(){
        for (int i = 0; i < count; i++) {
            arr[i].xuatNV();
        }
    }
    
    public void timTheoMaNV(){
        String maNV;
        System.out.println("Nhap ma nhan vien muon tim kiem: ");
        maNV = sc.nextLine();
                    
        for (int i = 0; i < count; i++) {
            if(arr[i].getMaNV().equalsIgnoreCase(maNV) == true){
                System.out.println("Da tim thay khach hang can tim: ");
                arr[i].xuatNV();
            } 
            else
                System.out.println("Khong tim thay!"); 
        }                 
    }
    
    public void timTheoHoNV(){
        String hoNV;
        System.out.println("Nhap ho khach hang muon tim kiem: ");
        hoNV = sc.nextLine();
        
        for (int i = 0; i < count; i++) {
            if(arr[i].getHoNV().equalsIgnoreCase(hoNV) == true){
                System.out.println("Da tim thay khach hang can tim: ");
                arr[i].xuatNV();
            } 
            else
                System.out.println("Khong tim thay!"); 
        }
    }
    
    public void timTheoTenNV(){
        String tenNV;
        System.out.println("Nhap ten nhan vien muon tim kiem: ");
        tenNV = sc.nextLine();
                    
        for (int i = 0; i < count; i++) {
            if(arr[i].getMaNV().equalsIgnoreCase(tenNV) == true){
                System.out.println("Da tim thay khach hang can tim: ");
                arr[i].xuatNV();
            } 
            else
                System.out.println("Khong tim thay!"); 
        }
    }
    
    public void tim(){
        int luachon;
 
        do{
            System.out.println("MENU");
            System.out.println("1. Tim kiem theo ma khach hang");
            System.out.println("2. Tim kiem theo ho khach hang");
            System.out.println("3. Tim kiem theo ten khach hang");
            System.out.println("4. Quit");
            System.out.println("Tim kiem theo: ");
            luachon = Integer.parseInt(sc.nextLine());
            switch (luachon) {
                case 1:{
                    timTheoMaNV();
                    break;
                }
                
                case 2:{
                    timTheoHoNV();
                    break;
                }
                
                case 3:{
                    timTheoTenNV();
                    break;
                }
                case 4:
                    break;
                default:
                    System.out.println("Hay chon tu 1 den 4");
                    break;
            }

        }while(true);
    }
    
    public void xoa(){
        String maNV;
        System.out.println("Nhap ma khach hang can xoa: ");
        maNV = sc.nextLine();
        int d = 0;
        boolean timThay = false;
        for (int i = 0; i < count; i++) {
            if(arr[i].getMaNV().equalsIgnoreCase(maNV) == true){
                timThay = true;
                d = i;
                break;
            }  
        }
        if(timThay = false){
            System.out.println("Khong tim thay khach hang nao!!");
        }
        else{
            XoaPhanTu(d);
        }
    }
    
    public void xuatFile(){
 
        System.out.println("Bat dau luu: ");
        FileOutputStream fos = null;
        
        try {
            fos = new FileOutputStream("KhachHang.txt");
            
            for (NhanVien NhanVien : arr) {
                String line = NhanVien.getFileLine();
                byte[] b = line.getBytes("utf8");
                fos.write(b);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DSKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DSKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DSKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(DSKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
        
    private void XoaPhanTu(int pos){
        int n = arr.length;
        if(n <= 0){
            return;
        }
        if(pos < 0){
            pos = 0;
        }
        else if(pos >= n){
            pos = n-1;
        }
        for(int i = pos; i < n - 1; i++){
            arr[i] = arr[i+1];
        }
        --n;
    }
}
