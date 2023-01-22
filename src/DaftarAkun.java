import java.util.Scanner;
import java.util.UUID;

public class DaftarAkun {
    
    public static void jalankan(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println();
        System.out.println("-----------------------------");        
        System.out.println("         Daftar akun         ");
        System.out.println("-----------------------------");

        System.out.print("masukan nama: ");
        String user = sc.nextLine();
        System.out.print("masukan password: ");
        String password = sc.nextLine();

        //insert data
        String id = UUID.randomUUID().toString();
        String newid = "";
        for (int i = 0; i < 13; i++) {
            newid += id.charAt(i);
        }

        try {
            CrudManagement.insertAkun(newid, user, password);
            System.out.println("tolong diingat user anda untuk login" );
            System.out.println("user anda: " + newid);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        

        sc.close();
    }
}
