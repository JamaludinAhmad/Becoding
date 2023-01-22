import java.util.Scanner;

public class HalamanUser {
    static  String currentuser = "1126444c-9143";

    public static void setCurrentUser(String u){
        currentuser = u;
    }

    public static void tampilkan(){
        //if(currentuser == "") return;

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
            CrudManagement.tampilkanData();
        }
        else if(pilih == 2){
            //lihat daftar kursus
            try {
                CrudManagement.tampilkanData(currentuser);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } 
        else if(pilih == 3) {
            //beli kursus get id_kursus
            CrudManagement.tampilkanData();
            System.out.println("-------------------------");
            System.out.print("Beli kursus nomor: ");
            int no = sc.nextInt();
            System.out.println(no);

            try {
                String id_kursus = CrudManagement.getIdKursus(no);
                CrudManagement.beliKursus(currentuser, id_kursus);
                System.out.println("-----");
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
        }
        else if(pilih == 4){
            //
        }

        sc.close();

    }
}
