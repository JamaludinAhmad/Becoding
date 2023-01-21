
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;


public class InsertData {
    static String jdbc = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/becoding";
    static String user = "root";
    static String pass = "";
    
    static Connection conn;
    static ResultSet rs;
    static CallableStatement cs;
    
    public static void masukan(String id, String username, String pw) {
        
        try{
            Class.forName(jdbc);
            conn = DriverManager.getConnection(url, user, pass);
            String query = "{CALL tambah_akun(?,?,?)}";
            cs =conn.prepareCall(query);
            cs.setString(1, id);
            cs.setString(2, username);
            cs.setString(3, pw);
            rs = cs.executeQuery();
            
            System.out.println("akun " + user + " telah berhasil terdaftar");
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
    }
    
    
}
