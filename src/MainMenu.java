import java.util.Scanner;

public class MainMenu {
    
    public static void main(String[] args) {
        
        try {
            loginForm();
        } catch (Exception e) {
            System.out.println("ada sebuah kesalahan");
            System.out.println("Error message: " + e.getMessage());
            e.printStackTrace();
        }
        
    }

    static void loginForm() throws Exception{
        Scanner sc = new Scanner(System.in);
        
        System.out.println("         Be-Coding");
        System.out.println("---------------------------");
        System.out.println("1. Login ");
        System.out.println("2. Daftar");
        System.out.println("3. Out");
        System.out.println("---------------------------");
        System.out.print("pilih: ");
        int pilih = sc.nextInt();

        if(pilih == 1){
            //masuk menu utama
            System.out.println("masuk menu utama");
        }
        else if(pilih == 2){
            //daftar akun
            System.out.println("mendaftar akun");
        } else if(pilih == 3) {
            //keluar dari program
            System.out.println("keluar dari program");
        }

        sc.close();
    }
}
