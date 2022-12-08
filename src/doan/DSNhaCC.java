
package doan;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DSNhaCC {
    private NhaCungCap arr[] = new NhaCungCap[500];
    private int count;
    private Scanner sc = new Scanner(System.in);
       
    public void nhap(){
        String maNCC, tenNCC;

        System.out.println("Nhap so phan tu: ");
        count = Integer.parseInt(sc.nextLine());
        
        for (int i = 0; i < count; i++) {
            System.out.print("Nhap ma khach hang: ");
            maNCC = sc.nextLine();
        
            System.out.print("Nhap ho khach hang: ");
            tenNCC = sc.nextLine();

            
            arr[i] = new NhaCungCap(maNCC, tenNCC);
            
        }
        
    }
    
    public void them(){
        String maNCC, tenNCC;
        
        System.out.print("Nhap ma nha cung cap: ");
        maNCC = sc.nextLine();

        System.out.print("Nhap ten nha cung cap: ");
        tenNCC = sc.nextLine();
        
        arr[count] = new NhaCungCap(maNCC, tenNCC);
        count++;
    }
    
    public void sua(){
        String maNCC, tenNCC;
        System.out.println("Nhap ma nha cung cap can sua: ");
        maNCC = sc.nextLine();
        boolean timThay = false;
        
        for (int i = 0; i < count; i++) {
            if(arr[i].getMaNCC().equalsIgnoreCase(maNCC) == true){
                System.out.print("Nhap ma nha cung cap: ");
                maNCC = sc.nextLine();

                System.out.print("Nhap ten nha cung cap: ");
                tenNCC = sc.nextLine();
            
                arr[i].setMaNCC(maNCC);
                arr[i].setTenNCC(tenNCC);
                timThay = true;
                break;
            }
            if(timThay = false) System.out.println("Khong tim thay khach hang nao!!");
        }
    }
    
    public void xuatDS(){
        for (int i = 0; i < count; i++) {
            arr[i].xuatNCC();
        }
    }
    
    public void timTheoMaNCC(){
        String maNCC;
        System.out.println("Nhap ma khach hang muon tim kiem: ");
        maNCC = sc.nextLine();
                    
        for (int i = 0; i < count; i++) {
            if(arr[i].getMaNCC().equalsIgnoreCase(maNCC) == true){
                System.out.println("Da tim thay khach hang can tim: ");
                arr[i].xuatNCC();
            } 
            else
                System.out.println("Khong tim thay!"); 
        }                 
    }

    public void timTheoTenNCC(){
        String tenNCC;
        System.out.println("Nhap ten khach hang muon tim kiem: ");
        tenNCC = sc.nextLine();
                    
        for (int i = 0; i < count; i++) {
            if(arr[i].getTenNCC().equalsIgnoreCase(tenNCC) == true){
                System.out.println("Da tim thay khach hang can tim: ");
                arr[i].xuatNCC();
            } 
            else
                System.out.println("Khong tim thay!"); 
        }
    }
    
    public void timKH(){
        int luachon;
 
        do{
            System.out.println("MENU");
            System.out.println("1. Tim kiem theo ma nha cung cap");
            System.out.println("2. Tim kiem theo ten nha cung cap");
            System.out.println("3. Quit");
            System.out.println("Tim kiem theo: ");
            luachon = Integer.parseInt(sc.nextLine());
            switch (luachon) {
                case 1:{
                    timTheoMaNCC();
                    break;
                }
                
                case 2:{
                    timTheoTenNCC();
                    break;
                }

                case 3:
                    break;
                default:
                    System.out.println("Hay chon tu 1 den 3");
                    break;
            }

        }while(true);
    }
    
    public void xoa(){
        String maNCC;
        System.out.println("Nhap ma nha cung cap can xoa: ");
        maNCC = sc.nextLine();
        int d = 0;
        boolean timThay = false;
        for (int i = 0; i < count; i++) {
            if(arr[i].getMaNCC().equalsIgnoreCase(maNCC) == true){
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
            
            for (NhaCungCap NhaCungCap : arr) {
                String line = NhaCungCap.getFileLine();
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
