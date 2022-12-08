
package doan;


public class NhaCungCap {
    private String maNCC;
    private String tenNCC;

    public NhaCungCap(String maNCC, String tenNCC) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }
    
    public String getFileLine(){
        return ("< " + maNCC + " >|< " + tenNCC + " >" + "\n");
    }
    
    @Override
    public String toString() {
        return "NhaCungCap{" + "maNCC=" + maNCC + ", tenNCC=" + tenNCC + '}';
    }
    
    public void xuatNCC(){
        System.out.println("| Ma Nha Cung Cap | Ten Nha Cung Cap |");
         System.out.printf("|%-15s| %-15s|\n",
                 maNCC, tenNCC);
    }
}
