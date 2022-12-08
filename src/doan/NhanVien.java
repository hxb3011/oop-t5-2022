
package doan;

import java.util.Scanner;


public class NhanVien {
    private String maNV;
    private String hoNV;
    private String tenNV;
    private int sdt;
    private int mucLuong;
    private int namVaoLam;

    public NhanVien(String maNV, String hoNV, String tenNV, int sdt, int mucLuong, int namVaoLam) {
        this.maNV = maNV;
        this.hoNV = hoNV;
        this.tenNV = tenNV;
        this.sdt = sdt;
        this.mucLuong = mucLuong;
        this.namVaoLam = namVaoLam;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoNV() {
        return hoNV;
    }

    public void setHoNV(String hoNV) {
        this.hoNV = hoNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public double getMucLuong() {
        return mucLuong;
    }

    public void setMucLuong(int mucLuong) {
        this.mucLuong = mucLuong;
    }

    public int getNamVaoLam() {
        return namVaoLam;
    }

    public void setNamVaoLam(int namVaoLam) {
        this.namVaoLam = namVaoLam;
    }
    
    public String getFileLine(){
        return ("< " + maNV + " >|< " + hoNV + " " + tenNV + " >|< " + sdt + " >|< " + mucLuong + " >" + " >|< " + namVaoLam + " >\n" );
    }
    
    @Override
    public String toString() {
        return "NhanVien{" + "maNV=" + maNV + ", hoNV=" + hoNV + ", tenNV=" + tenNV + ", sdt=" + sdt + ", mucLuong=" + mucLuong + ", namVaoLam=" + namVaoLam + '}';
    }
    

    
    public void xuatNV(){
        System.out.println("| Ma Nhan Vien | Ho Nhan Vien | Ten Nhan Vien | So Dien Thoai | Muc Luong | Nam Vao Lam");
        System.out.printf("|%-15s| %-15s|%-15s|%15d|%15d|%15d|\n",
                 maNV, hoNV, tenNV, sdt, mucLuong,namVaoLam);
    }
}
