import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;


public class ShowData {
    static final String jdbc = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/becoding";
    static String  user = "root";
    static String password = "";

    static Connection conn;
    static ResultSet rs;
    static Statement state;

    public static void run() {
        try {
            Class.forName(jdbc);
            conn = DriverManager.getConnection(url, user, password);
            
            String query = "select * from tbl_kursus";
            state = conn.createStatement();
            rs = state.executeQuery(query);

            while(rs.next()){
                System.out.println("id_kursus: " + rs.getString(1));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
