import java.util.Scanner;

public class HalamanUser {

    static String currentuser;;

    public static void setCurrentUser(String u){
        currentuser = u;
    }

    public static void tampilkan() throws Exception{

        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println();
        System.out.println( "masuk sebagai: " + Akun.getNama(currentuser));
        System.out.println("---------------------------");
        System.out.println("         Be-Coding         ");
        System.out.println("---------------------------");
        System.out.println("1. lihat daftar kursus");
        System.out.println("2. kursus dibeli");
        System.out.println("3. beli kursus");
        System.out.println("4. hapus kursus yang telah dibeli");
        System.out.println("5. logout");
        System.out.println("---------------------------");
        System.out.print("pilih: ");

        int pilih = sc.nextInt();

        if(pilih == 1){
            //tampilkan daftar kursus
            System.out.println("menampilkan daftar kursus");
            CurdManager.tampilkanData();
            HalamanUser.tampilkan();
        }
        else if(pilih == 2){
            //lihat daftar kursus
            try {
                Akun.kursusDibeli(currentuser);
                HalamanUser.tampilkan();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } 
        else if(pilih == 3) {
            //beli kursus
            CurdManager.tampilkanData();
            System.out.println("-------------------------");
            System.out.print("Beli kursus nomor: ");
            int no = sc.nextInt();

            try {
                String id_kursus = CurdManager.getIdKursus(no);
                CurdManager.beliKursus(currentuser, id_kursus);
                System.out.println("-----");
                HalamanUser.tampilkan();
                
            } catch (Exception e) {
                System.out.println("kursus yang anda beli telah dibeli sebelumnya");
                HalamanUser.tampilkan();
            }
            
        }

        else if(pilih == 4){
            System.out.print("masukan id_kursus yang akan dihapus");
            String id = sc.next();
            Akun.deleteKurs(currentuser, id);
            HalamanUser.tampilkan();
        }
        
        else if(pilih == 5){
            // logout akun
            try {
                HalamanDepan.jalankan();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();

    }
}
