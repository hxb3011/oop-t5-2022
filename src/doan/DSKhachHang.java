
package doan;

import java.io.*;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DSKhachHang {
    
    private KhachHang arr[] = new KhachHang[500];
    private int count;
    private Scanner sc = new Scanner(System.in);
       
    public void nhapKH(){
        String maKH, hoKH, tenKH,diaChi;
        int sdt;
        System.out.println("Nhap co phan tu: ");
        count = Integer.parseInt(sc.nextLine());
        
        for (int i = 0; i < count; i++) {
            System.out.print("Nhap ma khach hang: ");
            maKH = sc.nextLine();
        
            System.out.print("Nhap ho khach hang: ");
            hoKH = sc.nextLine();
        
            System.out.print("Nhap ten khach hang: ");
            tenKH = sc.nextLine();
        
            System.out.print("Nhap dia chi: ");
            diaChi = sc.nextLine();
        
            System.out.println("Nhap so dien thoai: ");
            sdt = Integer.parseInt(sc.nextLine());
            
            arr[i] = new KhachHang(maKH, hoKH, tenKH, diaChi, sdt);
            
        }
        
    }
    
    public void themKH(){
        String maKH, hoKH, tenKH,diaChi;
        int sdt;
        
        System.out.print("Nhap ma khach hang: ");
        maKH = sc.nextLine();
        
        System.out.print("Nhap ho khach hang: ");
        hoKH = sc.nextLine();

        System.out.print("Nhap ten khach hang: ");
        tenKH = sc.nextLine();

        System.out.print("Nhap dia chi: ");
        diaChi = sc.nextLine();

        System.out.println("Nhap so dien thoai: ");
        sdt = Integer.parseInt(sc.nextLine());
        
        arr[count] = new KhachHang(maKH, hoKH, tenKH, diaChi, sdt);
        count++;
    }
    
    public void suaKH(){
        String maKH, hoKH, tenKH,diaChi;
        int sdt;
        System.out.println("Nhap ma khach hang can sua: ");
        maKH = sc.nextLine();
        boolean timThay = false;
        
        for (int i = 0; i < count; i++) {
            if(arr[i].getMaKH().equalsIgnoreCase(maKH) == true){
                System.out.print("Nhap ma khach hang: ");
                maKH = sc.nextLine();
        
                System.out.print("Nhap ho khach hang: ");
                hoKH = sc.nextLine();
        
                System.out.print("Nhap ten khach hang: ");
                tenKH = sc.nextLine();
        
                System.out.print("Nhap dia chi: ");
                diaChi = sc.nextLine();
        
                System.out.println("Nhap so dien thoai: ");
                sdt = sc.nextInt();
            
                arr[i].setMaKH(maKH);
                arr[i].setHoKH(hoKH);
                arr[i].setTenKH(tenKH);
                arr[i].setDiaChi(diaChi);
                arr[i].setSdt(sdt);
                timThay = true;
                break;
            }
            if(timThay = false) System.out.println("Khong tim thay khach hang nao!!");
        }
    }
    
    public void xuatDS(){
        for (int i = 0; i < count; i++) {
            arr[i].xuatKH();
        }
    }
    
    public void timTheoMaKH(){
        String maKH;
        System.out.println("Nhap ma khach hang muon tim kiem: ");
        maKH = sc.nextLine();
                    
        for (int i = 0; i < count; i++) {
            if(arr[i].getMaKH().equalsIgnoreCase(maKH) == true){
                System.out.println("Da tim thay khach hang can tim: ");
                arr[i].xuatKH();
            } 
            else
                System.out.println("Khong tim thay!"); 
        }                 
    }
    
    public void timTheoHoKH(){
        String hoKH;
        System.out.println("Nhap ho khach hang muon tim kiem: ");
        hoKH = sc.nextLine();
        
        for (int i = 0; i < count; i++) {
            if(arr[i].getHoKH().equalsIgnoreCase(hoKH) == true){
                System.out.println("Da tim thay khach hang can tim: ");
                arr[i].xuatKH();
            } 
            else
                System.out.println("Khong tim thay!"); 
        }
    }
    
    public void timTheoTenKH(){
        String tenKH;
        System.out.println("Nhap ten khach hang muon tim kiem: ");
        tenKH = sc.nextLine();
                    //KhachHang x = new KhachHang(maKH, hoKH, tenKH, diaChi, sdt);
        for (int i = 0; i < count; i++) {
            if(arr[i].getTenKH().equalsIgnoreCase(tenKH) == true){
                System.out.println("Da tim thay khach hang can tim: ");
                arr[i].xuatKH();
            } 
            else
                System.out.println("Khong tim thay!"); 
        }
    }
    
    public void timKH(){
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
                    timTheoMaKH();
                    break;
                }
                
                case 2:{
                    timTheoHoKH();
                    break;
                }
                
                case 3:{
                    timTheoTenKH();
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
    
    public void xoaKH(){
        String maKH;
        System.out.println("Nhap ma khach hang can xoa: ");
        maKH = sc.nextLine();
        int d = 0;
        boolean timThay = false;
        for (int i = 0; i < count; i++) {
            if(arr[i].getMaKH().equalsIgnoreCase(maKH) == true){
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
            
            for (KhachHang khachHang : arr) {
                String line = khachHang.getFileLine();
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