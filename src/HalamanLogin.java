import java.util.Scanner;

public class HalamanLogin {
    
    public static void jalankan(){
        Scanner sc = new Scanner(System.in);

        System.out.print("masukan user anda: ");
        String user = sc.nextLine();

        System.out.print("masukan password anda: ");
        String pass = sc.nextLine();

        //cek password
        try {
            if(pass.equals(CrudManagement.getPassword(user))){
                HalamanUser.setCurrentUser(user);
                HalamanUser.tampilkan();
            }
            else{
                System.out.println("password tidak match");
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
