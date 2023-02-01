import java.util.Scanner;

public class HalamanLogin {
    
    public static void jalankan() throws Exception{
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println();
        System.out.println("----------------------------");
        System.out.println("            LOGIN           ");
        System.out.println("----------------------------");
        System.out.print("masukan user anda: ");
        String user = sc.nextLine();
        System.out.print("masukan password anda: ");
        String pass = sc.nextLine();
        System.out.println("----------------------------");

        if(CrudManager.checkPassword(user, pass)){
            HalamanUser.inisialisasiAkun(user);
            HalamanUser.tampilkan();
        }

        else{
            System.out.println("password tidak match");
        }
        sc.close();
    }
}
