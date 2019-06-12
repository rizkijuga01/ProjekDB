package rizky.com.mahasiswa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
	 // Menyiapkan paramter JDBC untuk koneksi ke datbase
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/belajardb2?charset=utf8mb4&useLegacyDatetimeCode=false&serverTimezone=Asia/Jakarta";
    static final String USER = "root" ;
    static final String PASS = "password" ;

    // Menyiapkan objek yang diperlukan untuk mengelola database
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
	
	
	
    public static void main( String[] args )
    {
    	try {
            // register driver yang akan dipakai
            Class.forName(JDBC_DRIVER);
            
            // buat koneksi ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            // buat objek statement
            stmt = conn.createStatement();
            
            // buat query ke database
            String sql = "SELECT * FROM Mahasiswa";
            
            // eksekusi query dan simpan hasilnya di obj ResultSet
            rs = stmt.executeQuery(sql);
            
            // tampilkan hasil query
            while(rs.next()){
                System.out.println("NIK: " + rs.getInt("Nik"));
                System.out.println("Nama: " + rs.getString("Nama"));
                System.out.println("Gender: " + rs.getString("Gender"));
                System.out.println("Umur: " + rs.getInt("Umur"));
            }
            
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
