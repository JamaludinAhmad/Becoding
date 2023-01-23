import java.sql.DriverManager;

public class Akun extends CurdManager{

    public static void deleteKurs(String id_akun, String id_kursus) throws Exception{
        try {
                                
            Class.forName(jdbc);
            conn = DriverManager.getConnection(url, user, password);
    
            String query = "delete from tbl_akses where id_akun = (?) and id_kursus = (?)";
            
            ps = conn.prepareStatement(query);
            ps.setString(1, id_akun);
            ps.setString(2, id_kursus);
            ps.executeUpdate();

            System.out.println("berhasil di delete");;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String getNama(String id_akun) throws Exception {
        
        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);
        
        String query = "SELECT nama FROM tbl_akun WHERE id_akun = (?)";
        ps = conn.prepareStatement(query);
        ps.setString(1, id_akun);
        rs = ps.executeQuery();

        if(!rs.next()){
            return null;
        }
        else{
            return rs.getString(1).toString();
        }
    }

    public static String getPassword(String id_akun) throws Exception {
        
        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);
        
        String query = "SELECT pass FROM tbl_akun WHERE id_akun = (?)";
        ps = conn.prepareStatement(query);
        ps.setString(1, id_akun);
        rs = ps.executeQuery();

        if(!rs.next()){
            return null;
        }
        else{
            return rs.getString(1).toString();
        }

    }

    public static void kursusDibeli(String id_akun) throws Exception{
        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);
        
        String query = "SELECT * FROM tbl_kursus INNER JOIN tbl_akses ON tbl_akses.id_kursus = tbl_kursus.id_kursus WHERE tbl_akses.id_akun = " + "'"+ id_akun + "'";
        
        state = conn.createStatement();
        rs = state.executeQuery(query);
        
        System.out.println("kursus yang telah dibeli");
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
    
}
