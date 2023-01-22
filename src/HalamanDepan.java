import java.util.Scanner;

public class HalamanDepan {
    
    public static void main(String[] args) {
        try {
            jalankan();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //halaman depan 
    public static void jalankan() throws Exception {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------");
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
            HalamanLogin.jalankan();
                        
        }
        else if(pilih == 2){
            //daftar akun
            DaftarAkun.jalankan();
        } else if(pilih == 3) {
            //keluar dari program
            System.out.println("keluar dari program");
        }

        sc.close();
    }

}
