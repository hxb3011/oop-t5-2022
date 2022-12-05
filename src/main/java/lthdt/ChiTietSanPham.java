package lthdt;

import java.util.Scanner;
import java.io.DataOutputStream;
import java.io.IOException;

public class ChiTietSanPham 
{
    private String CPU;
    private String GPU;
    private int RAM;
    private String ManHinh;
    private String HDH;
    private int NamSX;
    public ChiTietSanPham() {}
    public ChiTietSanPham(String cpu, String gpu, int ram, String manhinh, String hdh, int namsx)
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
        GPU = ct.GPU;
        RAM = ct.RAM;
        ManHinh = ct.ManHinh;
        HDH = ct.HDH;
        NamSX = ct.NamSX;
    }
    public void ghiFile(DataOutputStream output) throws IOException
    {
        output.writeBytes(CPU);
        output.writeBytes(System.lineSeparator());
        output.writeBytes(GPU);
        output.writeBytes(System.lineSeparator());
        output.writeBytes(Integer.toString(RAM));
        output.writeBytes(System.lineSeparator());
        output.writeBytes(ManHinh);
        output.writeBytes(System.lineSeparator());
        output.writeBytes(HDH);
        output.writeBytes(System.lineSeparator());
        output.writeBytes(Integer.toString(NamSX));
        output.writeBytes(System.lineSeparator());
    }
            
    public void docFile(Scanner in)
    {
        CPU = in.nextLine();
        GPU = in.nextLine();
        RAM = Integer.parseInt(in.nextLine());
        ManHinh = in.nextLine();
        HDH = in.nextLine();
        NamSX = Integer.parseInt(in.nextLine());
    }
    public void nhap()
    {
        nhapCPU();
        nhapGPU();
        nhapRAM();
        nhapManHinh();
        nhapHDH();
        nhapNamSX();
    }
    public void nhapCPU()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap CPU: ");
        CPU = in.nextLine();
    }
    public void nhapGPU()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap GPU: ");
        GPU = in.nextLine();
    }
    public void nhapRAM()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap RAM(GB): ");
        RAM = Integer.parseInt(in.nextLine());
    }
    public void nhapManHinh()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap ten man hinh: ");
        ManHinh = in.nextLine();
    }
    public void nhapHDH()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap he dieu hanh: ");
        HDH = in.nextLine();
    }
    public void nhapNamSX()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap nam san xuat: ");
        NamSX = Integer.parseInt(in.nextLine());
    }
    public void xuat()
    {
        System.out.printf("\n%s: %s \n%s: %s \n%s: %dGB \n%s: %s \n%s: %s \n%s: %d", "CPU",CPU,"GPU",GPU, "RAM", RAM,"Man Hinh",ManHinh ,"HDH",HDH, "NamSX",NamSX);        
    }
    public void setCPU(String cpu)
    {
        CPU = cpu;
    }
    public String getCPU()
    {
        return CPU;
    }
    public void setGPU(String gpu)
    {
        GPU = gpu;
    }
    public String getGPU()
    {
        return GPU;
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
