package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static final String URL =
            "jdbc:mysql://localhost:3306/db_inventori";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getKoneksi() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(
                    URL, USER, PASSWORD);

            System.out.println("Koneksi Berhasil!");

        } catch (SQLException e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }

        return conn;
    }
    
    public static void main(String[] args) {
    getKoneksi();
}
}