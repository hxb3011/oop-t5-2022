package lthdt;
import java.io.IOException;
public class DoAn {

    public static void main(String[] args) throws IOException
    {
       DSSP ds = new DSSP();
//       ds.nhap();
//       ds.ghithem();
       ds.docFile();
       ds.xuat();
       Laptop lt = ds.timsanpham("LT001");
       lt.xuat();
    }
}