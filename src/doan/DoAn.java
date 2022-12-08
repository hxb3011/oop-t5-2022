
package doan;

import java.util.Scanner;


public class DoAn {

    
    
    public static void main(String[] args) {
        DSKhachHang ds = new DSKhachHang();
        Scanner sc = new Scanner(System.in);
        int choice;
        
        do{
            System.out.println("1. nhap");
            System.out.println("2. xuat");
            System.out.println("3. xuat file");
            System.out.println("4. xoa");
            System.out.println("5. quit");
            System.out.println("Please choose 1 to 5: ");
            choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                    ds.nhapKH();
                    break;
                case 2:
                    ds.xuatDS();
                    break;
                case 3:
                    ds.xuatFile();
                    break;
                case 4:
                    ds.xoaKH();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please choice only 1 to 6");
                    break;
                }
            }while(choice != 5);
    }

}
