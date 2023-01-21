import java.util.Scanner;
import java.util.UUID;

public class DaftarAkun {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("         Daftar akun ");
        System.out.println("-----------------------------");

        System.out.print("masukan username: ");
        String user = sc.nextLine();
        System.out.print("masukan password: ");
        String password = sc.nextLine();

        //insert data
        String id = UUID.randomUUID().toString();
        String newid = "";
        for (int i = 0; i < 13; i++) {
            newid += id.charAt(i);
        }
        InsertData.masukan(newid, user, password);

        sc.close();
    }
}
