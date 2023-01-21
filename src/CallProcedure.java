
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;


public class CallProcedure {
    static String jdbc = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/toko";
    static String user = "root";
    static String pass = "";
    
    static Connection conn;
    static ResultSet rs;
    static CallableStatement cs;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Isikan kode barang : ");
        String cari = sc.next();
        try{
            Class.forName(jdbc);
            conn = DriverManager.getConnection(url, user, pass);
            String query = "{CALL myprocedure(?)}";
            cs =conn.prepareCall(query);
            cs.setString(1, cari);
            rs = cs.executeQuery();
            if(rs.next()){
                System.out.println("Kode Barang : "+ rs.getString("kode_Barang"));
                System.out.println("Nama Barang : "+ rs.getString("nama_barang"));
                System.out.println("Harga : "+ rs.getInt("harga_barang"));
                System.out.println("Stok :"+rs.getInt("stok_barang"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
    }
    
    
}
