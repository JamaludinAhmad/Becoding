import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class HalamanUser {

    static Akun akun;

    public static void inisialisasiAkun(String id_akun) throws Exception{

        String[] data = CrudManager.ambilDataAkun(id_akun);
        akun = new Akun(id_akun,data[0], data[1], data[2]);

    }

    public static void tampilkan() throws Exception{

        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println();
        System.out.println( "masuk sebagai: " + akun.getNama());
        System.out.println("---------------------------");
        System.out.println("         Be-Coding         ");
        System.out.println("---------------------------");
        System.out.println("1. lihat daftar kursus");
        System.out.println("2. kursus dibeli");
        System.out.println("3. beli kursus");
        System.out.println("4. hapus kursus yang telah dibeli");
        System.out.println("5. Top Up");
        System.out.println("6. Info Akun");
        System.out.println("7. logout");
        System.out.println("---------------------------");
        System.out.print("pilih: ");

        int pilih = sc.nextInt();

        if(pilih == 1){
            //tampilkan daftar kursus
            System.out.println("menampilkan daftar kursus");
            CrudManager.tampilkanData();
            HalamanUser.tampilkan();
        }

        else if(pilih == 2){
            //lihat daftar kursus
            try {
                akun.tampilkanDataDibeli();
                HalamanUser.tampilkan();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } 

        else if(pilih == 3) {
            //beli kursus
            CrudManager.tampilkanData();
            System.out.println("-------------------------");
            System.out.print("Beli kursus nomor: ");
            int no = sc.nextInt();

            String id_kursus = CrudManager.getIdKursus(no);
            int harga = CrudManager.getHargaKursus(id_kursus);
            try {
                if(akun.getUang() < harga){
                    System.out.println("uang tidak cukup");
                    System.out.println("uang anda: " + akun.getUang());
                    HalamanUser.tampilkan();
                    
                }
                else{
                    akun.beliKursus(id_kursus);
                    akun.setUang(akun.getUang() - harga);
                    akun.setJumlahKurs(akun.getJumlahKurs() + 1);
                    System.out.println("-----");
                    HalamanUser.tampilkan();
                    

                }
                
            }
            catch (SQLIntegrityConstraintViolationException e){
                System.out.println("kursus sudah dibeli");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

            finally{
                HalamanUser.tampilkan();
            }
            
        }

        else if(pilih == 4){
            akun.tampilkanDataDibeli();
            if(akun.getJumlahKurs() == 0){
                System.out.println("tidak ada kursus");
            }

            else{
                System.out.print("masukan id_kursus yang akan dihapus: ");
                String id = sc.next();
                akun.deleteKurs(id);
                akun.setJumlahKurs(akun.getJumlahKurs() - 1);
            }

            HalamanUser.tampilkan();
        }
        else if(pilih == 5){
            try {
                System.out.print("masukan jumlah uang: ");
                int jumlah = sc.nextInt();
                akun.topUp(jumlah);
                akun.setUang(akun.getUang() + jumlah);
                HalamanUser.tampilkan();

            } catch (Exception e) {
                System.out.println("error: " + e.getLocalizedMessage());
                HalamanUser.tampilkan();
            }
        }

        if(pilih == 6){
            System.out.println("---------------------------");
            System.out.println("         Be-Coding         ");
            System.out.println("---------------------------");
            akun.infoAkun();
            HalamanUser.tampilkan();
        }
        else if(pilih == 7){
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
