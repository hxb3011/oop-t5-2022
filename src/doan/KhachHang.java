
package doan;

import java.util.Scanner;


public class KhachHang {
    private String maKH;
    private String hoKH;
    private String tenKH;
    private String diaChi;
    private int sdt;

    public KhachHang(String maKH, String hoKH, String tenKH, String diaChi, int sdt) {
        this.maKH = maKH;
        this.hoKH = hoKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoKH() {
        return hoKH;
    }

    public void setHoKH(String hoKH) {
        this.hoKH = hoKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getFileLine(){
        return ("< " + maKH + " >|< " + hoKH + " " + tenKH + " >|< " + diaChi + " >|< " + sdt + " >" + "\n");
    }
    
    @Override
    public String toString() {
        return "KhachHang{" + "maKH=" + maKH + ", hoKH=" + hoKH + ", tenKH=" + tenKH + ", diaChi=" + diaChi + ", sdt=" + sdt + '}';
    }
    
    public void xuatKH(){
        System.out.println("| Ma Khach Hang | Ho Khach Hang | Ten Khach Hang | Dia Chi | So Dien Thoai | ");
        System.out.printf("|%-15s| %-15s|%-15s|%-9s|%15d|\n",
                 maKH, hoKH, tenKH, diaChi, sdt);
    }

}
