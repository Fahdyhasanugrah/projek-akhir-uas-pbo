/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/toko_obat?useSSL=false&serverTimezone=UTC";
                String user = "root";
                String password = "";

                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);

                System.out.println("Koneksi ke database berhasil.");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Koneksi Gagal: " + e.getMessage());
            }
        }
        return conn;
    }
}
