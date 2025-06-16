/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = Koneksi.getConnection();
        if (conn != null) {
            System.out.println("Tes koneksi berhasil!");
        } else {
            System.out.println("Tes koneksi gagal!");
        }
    }
}