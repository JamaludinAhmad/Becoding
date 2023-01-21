import java.util.Scanner;

public class UserMenu {
    
    public static void main(String[] args) {
        
        try {
            menu();
        } catch (Exception e) {
            // System.out.println("ada kesalahan");
        }
    }

    static void menu() throws Exception {
        while(true){
            Scanner sc = new Scanner(System.in);

            System.out.println("         Be-Coding");
            System.out.println("1. lihat daftar kursus");
            System.out.println("2. kursus dibeli");
            System.out.println("3. beli kursus");
            System.out.println("4. logout");

            System.out.println("---------------------------");
            System.out.print("pilih: ");
            int pilih = sc.nextInt();

            if(pilih == 1){
                //tampilkan daftar kursus
                System.out.println("menampilkan daftar kursus");
                ShowData.run();
                
            }
            else if(pilih == 2){
                //lihat daftar kursus
                
            } 
            else if(pilih == 3) {
                //beli kursus
                
            }
            else if(pilih == 4){
                MainMenu.loginForm();
            }

            sc.close();
        }
    }
}
