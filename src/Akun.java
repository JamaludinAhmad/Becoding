import java.sql.DriverManager;

public class Akun extends CrudManager{
    private String id_akun;
    private String nama;
    private int jumlahKurs;
    private int uang;
    
    Akun(String id_akun, String nama, String jumlahKurs, String uang){
        this.id_akun = id_akun;
        this.nama = nama;
        this.jumlahKurs = Integer.parseInt(jumlahKurs);
        this.uang = Integer.parseInt(uang);
    }
    
    public void setJumlahKurs(int jumlahKurs) {
        this.jumlahKurs = jumlahKurs;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }

    public String getId_akun() {
        return id_akun;
    }

    public String getNama() {
        return nama;
    }

    public int getJumlahKurs() {
        return jumlahKurs;
    }

    public int getUang() {
        return uang;
    }

    public void infoAkun(){
        System.out.println("Nama: " + getNama());
        System.out.println("Jumlah kursus: " + getJumlahKurs());
        System.out.println("uang: " + getUang());
    }

    public void tampilkanDataDibeli() throws Exception{
        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);
        
        String injoin1 = " INNER JOIN tbl_kategori ON tbl_kursus.kategori = tbl_kategori.id_kategori ";
        String injoin2 = "INNER JOIN tbl_mentor ON tbl_kursus.id_mentor = tbl_mentor.id_mentor ";
        String injoin3 = "INNER JOIN tbl_akses ON tbl_kursus.id_kursus = tbl_akses.id_kursus WHERE tbl_akses.id_akun = (?) ";
        String query = "SELECT tbl_kursus.id_kursus, nama_kursus, nama_mentor FROM tbl_kursus" + injoin1 + injoin2 + injoin3;
        
        ps = conn.prepareStatement(query);
        ps.setString(1, getId_akun());
        rs = ps.executeQuery();
        int no = 0;
        System.out.println("-----------------------------------------------------------");
        String leftAlignFormat = "| %-3s | %-12s | %-20s | %-10s | %n";
        System.out.format(leftAlignFormat, "NO","KODE KURSUS","KURSUS","MENTOR");
        System.out.println("-----------------------------------------------------------");
        
        while(rs.next()){
            System.out.format(leftAlignFormat, (++no) ,rs.getString(1) ,rs.getString(2),rs.getString(3));
        }

        System.out.println("-----------------------------------------------------------");
    }

    public void beliKursus(String id_kursus) throws Exception{

        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);

        String query = "INSERT INTO tbl_akses VALUES (CONCAT(?,?),?,?)";
        
        ps = conn.prepareStatement(query);

        ps.setString(1, getId_akun());
        ps.setString(2, id_kursus);
        ps.setString(3, getId_akun());
        ps.setString(4, id_kursus);

        if(ps.executeUpdate() > 0){
            System.out.println("pembelian telah berhasil");
        }
        else{
            System.out.println("pembelian gagal");
        }
    }

    public void deleteKurs(String id_kursus) throws Exception{
                                
        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);

        String query = "delete from tbl_akses where id_akun = (?) and id_kursus = (?)";
        
        ps = conn.prepareStatement(query);
        ps.setString(1, getId_akun());
        ps.setString(2, id_kursus);
        ps.executeUpdate();

        System.out.println("berhasil di delete: " );
    }

    public void topUp(int jumlah) throws Exception {
        Class.forName(jdbc);
        conn = DriverManager.getConnection(url, user, password);

        String query = "UPDATE tbl_akun SET uang = uang + (?) WHERE id_akun = (?)";
        
        ps = conn.prepareStatement(query);
        ps.setInt(1, jumlah);
        ps.setString(2, getId_akun());
        ps.executeUpdate();

    }
}
