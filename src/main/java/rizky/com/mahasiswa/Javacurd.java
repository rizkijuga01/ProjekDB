package rizky.com.mahasiswa;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Javacurd {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/belajardb2?charset=utf8mb4&useLegacyDatetimeCode=false&serverTimezone=Asia/Jakarta";
    static final String USER = "root" ;
    static final String PASS = "password" ;

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            // register driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                showMenu();
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void showMenu() {
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Insert Data");
        System.out.println("2. Show Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.print("PILIHAN> ");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertMahasiswa();
                    break;
                case 2:
                    showMahasiswa();
                    break;
                case 3:
                    updateMahasiswa();
                    break;
                case 4:
                    deleteMahasiswa();
                    break;
                default:
                    System.out.println("Pilihan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void showMahasiswa() {
        String sql = "SELECT * FROM Mahasiswa";

        try {
            rs = stmt.executeQuery(sql);
            
            System.out.println("+--------------------------------+");
            System.out.println("|    DATA NAMA MAHASISWA	   	 |");
            System.out.println("+--------------------------------+");

            while (rs.next()) {
                int Nik = rs.getInt("Nik");
                String Nama = rs.getString("Nama");	
                String Gender = rs.getString("Gender");
                
                
                System.out.println(String.format("%s %s %s ", Nik, Nama, Gender));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void insertMahasiswa() {
        try {
            // ambil input dari user
            System.out.print("Nama: ");
            String Nama = input.readLine().trim();
            System.out.print("Gender: ");
            String Gender = input.readLine().trim();
 
            // query simpan
            String sql = "INSERT INTO Mahasiswa (Nama,Gender) VALUE('%s', '%s')";
            sql = String.format(sql, Nama, Gender);

            // simpan buku
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void updateMahasiswa() {
        try {
            
            // ambil input dari user
            System.out.print("NIK yang mau diedit: ");
            int Nik = Integer.parseInt(input.readLine());
            System.out.print("Nama: ");
            String Nama = input.readLine().trim();
            System.out.print("Gender: ");
            String Gender = input.readLine().trim();

            // query update
            String sql = "UPDATE Mahasiswa SET Nama='%s', Gender='%s' WHERE Nik=%d";
            sql = String.format(sql, Nama, Gender, Nik);

            // update data buku
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteMahasiswa() {
        try {
            
            // ambil input dari user
            System.out.print("Nik yang mau dihapus: ");
            int Nik = Integer.parseInt(input.readLine());
            
            // buat query hapus
            String sql = String.format("DELETE FROM Mahasiswa WHERE Nik=%d", Nik);

            // hapus data
            stmt.execute(sql);
            
            System.out.println("Data telah terhapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}