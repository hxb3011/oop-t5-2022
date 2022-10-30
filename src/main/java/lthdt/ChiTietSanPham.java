package lthdt;

import java.util.Scanner;

public class ChiTietSanPham 
{
    private String CPU;
    private int RAM;
    private String ManHinh;
    private String HDH;
    private int NamSX;
    public ChiTietSanPham() {}
    public ChiTietSanPham(String cpu, int ram, String manhinh, String hdh, int namsx)
    {
        CPU = cpu;
        RAM = ram;
        ManHinh = manhinh;
        HDH = hdh;
        NamSX = namsx;
    }
    public ChiTietSanPham(ChiTietSanPham ct)
    {
        CPU = ct.CPU;
        RAM = ct.RAM;
        ManHinh = ct.ManHinh;
        HDH = ct.HDH;
        NamSX = ct.NamSX;
    }
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("\nNhap CPU: ");
        CPU = in.nextLine();
        System.out.print("Nhap RAM: ");
        RAM = Integer.parseInt(in.nextLine());
        System.out.print("Nhap ten man hinh: ");
        ManHinh = in.nextLine();
        System.out.print("Nhap he dieu hanh: ");
        HDH = in.nextLine();
        System.out.print("Nhap nam san xuat: ");
        NamSX = Integer.parseInt(in.nextLine());       
    }
    public void xuat()
    {
        System.out.printf("\n  CPU %20s RAM %20s Man Hinh %20s HDH %20s NamSX", "", "", "", "");
        System.out.printf("\n%s  %20dGB  %20s  %20s  %20d", CPU, RAM, ManHinh, HDH, NamSX);
    }
    public void setCPU(String cpu)
    {
        CPU = cpu;
    }
    public String getCPU()
    {
        return CPU;
    }
    public void setRAM(int ram)
    {
        RAM = ram;
    }
    public int getRAM()
    {
        return RAM;
    }
    public void setMH(String manhinh)
    {
        ManHinh = manhinh;
    }
    public String getMH()
    {
        return ManHinh;
    }
    public void setHDH(String hdh)
    {
        HDH = hdh;
    }
    public String getHDH()
    {
        return HDH;
    }
    public void setNSX(int namsx)
    {
        NamSX = namsx;
    }
    public int getNSX()
    {
        return NamSX;
    }
}
