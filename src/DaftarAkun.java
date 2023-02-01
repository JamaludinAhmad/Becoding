import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class DaftarAkun {
    public static void jalankan() throws Exception{
        Scanner sc = new Scanner(System.in);
        
        System.out.println();
        System.out.println("-----------------------------");        
        System.out.println("         Daftar akun         ");
        System.out.println("-----------------------------");

        System.out.print("masukan nama anda: ");
        String nama = sc.nextLine();
        System.out.print("masukan user: ");
        String user = sc.nextLine();
        System.out.print("masukan password: ");
        String password = sc.nextLine();


        try {
            CrudManager.insertAkun(user, nama, password);
        }
        catch(SQLIntegrityConstraintViolationException e){
            System.out.println("user telah terpakai");
        }
        catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        finally{
            HalamanDepan.jalankan();
        }

        

        sc.close();
    }
}
