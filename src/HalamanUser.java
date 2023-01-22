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
        System.out.println( "masuk sebagai: " + CrudManagement.getNama(currentuser));
        System.out.println("---------------------------");
        System.out.println("         Be-Coding         ");
        System.out.println("---------------------------");
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
            CrudManagement.tampilkanData();
            HalamanUser.tampilkan();
        }
        else if(pilih == 2){
            //lihat daftar kursus
            try {
                CrudManagement.tampilkanData(currentuser);
                HalamanUser.tampilkan();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } 
        else if(pilih == 3) {
            
            CrudManagement.tampilkanData();
            System.out.println("-------------------------");
            System.out.print("Beli kursus nomor: ");
            int no = sc.nextInt();

            try {
                String id_kursus = CrudManagement.getIdKursus(no);
                CrudManagement.beliKursus(currentuser, id_kursus);
                System.out.println("-----");
                HalamanUser.tampilkan();
                
            } catch (Exception e) {
                System.out.println("kursus yang anda beli telah dibeli sebelumnya");
            }
            
        }
        else if(pilih == 4){
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
