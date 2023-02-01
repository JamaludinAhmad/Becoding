import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;


public class CrudManager {
    static final String jdbc = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/kursusonline";
    static String  user = "root";
    static String password = "";

    static Connection conn;
    static ResultSet rs;
    static PreparedStatement ps;
    
    public static void tampilkanData() throws Exception{
        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);
        
        String query = "SELECT id_kursus, nama_kursus, harga, nama_mentor FROM tbl_kursus INNER JOIN tbl_kategori ON tbl_kursus.kategori = tbl_kategori.id_kategori INNER JOIN tbl_mentor ON tbl_kursus.id_mentor = tbl_mentor.id_mentor ORDER BY tbl_kursus.id_kursus ASC";
        ps = conn.prepareStatement(query);

        rs = ps.executeQuery();
        int no = 0;
        System.out.println("----------------------------------------------------------------------------");
        String leftAlignFormat = "| %-3s | %-12s | %-20s | %-10s | %-15s |%n";
        System.out.format(leftAlignFormat, "NO","KODE KURSUS","KURSUS","HARGA","MENTOR");
        System.out.println("----------------------------------------------------------------------------");
        
        while(rs.next()){
            System.out.format(leftAlignFormat, (++no) ,rs.getString(1) ,rs.getString(2),rs.getString(3),rs.getString(4));
        }

        System.out.println("----------------------------------------------------------------------------");
    }

    
    
    
    //mengambil data akun
    public static String[] ambilDataAkun(String id_akun) throws Exception{
        String[] isiakun = new String[3];

        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);

        String query = "select nama, jumlah_kurs, uang FROM tbl_akun WHERE id_akun = (?)";
        ps = conn.prepareStatement(query);
        ps.setString(1, id_akun);

        rs = ps.executeQuery();
        if(rs.next()){
            isiakun[0] = rs.getString(1).toString();
            isiakun[1] = rs.getString(2).toString();
            isiakun[2] = rs.getString(3).toString();

        };

        return isiakun;
    }

    //check password
    public static boolean checkPassword(String id_akun, String pass) throws Exception{
        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);
        
        String query = "SELECT pass FROM tbl_akun WHERE id_akun = (?)";
        ps = conn.prepareStatement(query);
        ps.setString(1, id_akun);
        rs = ps.executeQuery();
        
        if(rs.next() && rs.getString(1).equals(pass)){
            return true;
        }
        return false;
    }

    public static int getHargaKursus (String id_kursus) throws Exception{
        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);
        
        String query = "SELECT harga FROM tbl_kursus INNER JOIN tbl_kategori ON tbl_kursus.kategori = tbl_kategori.id_kategori WHERE tbl_kursus.id_kursus = (?)";
        ps = conn.prepareStatement(query);
        ps.setString(1, id_kursus);
        rs = ps.executeQuery();

        if(rs.next()){
            return rs.getInt(1);
        }

        return 0;
    }

    public static String getIdKursus(int no) throws Exception{

        if(getJumlahKursus() < no){
            System.out.println("kursus no " + no + " tidak tersedia");
            HalamanUser.tampilkan();
        }

        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);
        
        String query = "select * from tbl_kursus";
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        
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

    public static void insertAkun(String id_akun, String nama_user, String pass) throws SQLIntegrityConstraintViolationException, Exception{
        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);

        String query = "INSERT INTO tbl_akun VALUES (?,?,?,0,0)";
        
        ps = conn.prepareStatement(query);

        ps.setString(1, id_akun);
        ps.setString(2, nama_user);
        ps.setString(3, pass);

        if(ps.executeUpdate() > 0){
            System.out.println("pesan: akun telah berhasil didaftarkan");
        } else {
            System.out.println("pesan: akun tidak berhasil didaftarkan");
        }
    }

    public static int getJumlahKursus() throws Exception{
        Class.forName(jdbc);

        conn = DriverManager.getConnection(url, user, password);
        String query = "SELECT * FROM tbl_kursus";
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();

        int jumlahnya = 0;

        while(rs.next()){
            jumlahnya += 1;
        }

        return jumlahnya;
    }

}
