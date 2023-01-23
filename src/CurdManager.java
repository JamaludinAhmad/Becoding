import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.UUID;

public class CurdManager {
    static final String jdbc = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/becoding";
    static String  user = "root";
    static String password = "";

    static Connection conn;
    static ResultSet rs;
    static Statement state;
    static PreparedStatement ps;

    public static ArrayList<String> daftarkursus = new ArrayList<>();

    public static String getIdKursus(int no) throws Exception{

        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);
            
        String query = "select * from tbl_kursus";
        state = conn.createStatement();
        rs = state.executeQuery(query);
        
        
        String id = "";

        int g = 0;
        while(rs.next()){
            g++;
            if(no == g){
                id = rs.getString(1);
                break;  
            } 
        }
        
        System.out.println("ID: " + id);
        return id;
    }

    public static void tampilkanData(){
        try {
            Class.forName(jdbc);
            conn = DriverManager.getConnection(url, user, password);
            
            String query = "select * from tbl_kursus";
            state = conn.createStatement();
            rs = state.executeQuery(query);

            int no = 0;
            System.out.println("----------------------------------------------------------------------------");
            String leftAlignFormat = "| %-3s | %-12s | %-20s | %-10s | %-15s |%n";
            System.out.format(leftAlignFormat, "NO","KODE KURSUS","KURSUS","HARGA","MENTOR");
            System.out.println("----------------------------------------------------------------------------");
            while(rs.next()){
                System.out.format(leftAlignFormat, (++no) ,rs.getString(1) ,rs.getString(2),rs.getString(3),rs.getString(4));
            }
            System.out.println("----------------------------------------------------------------------------");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void beliKursus(String id_akun, String id_kursus) throws Exception{
        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);

        String query = "INSERT INTO tbl_akses VALUES (?,?,?)";
        
        String id_akses = UUID.randomUUID().toString();
        
        ps = conn.prepareStatement(query);

        ps.setString(1, id_akses);
        ps.setString(2, id_akun);
        ps.setString(3, id_kursus);

        if(ps.executeUpdate() > 0){
            System.out.println("pembelian telah berhasil dilakukan");
        } else {
            System.out.println("pembelian gagal dilakukan");
        }
        
    }

    public static void insertAkun(String id_akun, String nama_user, String pass) throws Exception{
        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);

        String query = "INSERT INTO tbl_akun VALUES (?,?,?,0)";
        
        ps = conn.prepareStatement(query);

        ps.setString(1, id_akun);
        ps.setString(2, nama_user);
        ps.setString(3, pass);

        if(ps.executeUpdate() > 0){
            System.out.println("pesan: akun telah berhasil didaftarkan");
        } else {
            System.out.println("pesan: akun telah berhasil didaftarkan");
        }
    }

    
    
}