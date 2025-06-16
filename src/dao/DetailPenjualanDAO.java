/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.Koneksi;
import model.DetailPenjualan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailPenjualanDAO {
    private Connection conn;

    public DetailPenjualanDAO() {
        conn = Koneksi.getConnection();
    }

    // Insert detail penjualan
    public void insert(DetailPenjualan d) {
        String sql = "INSERT INTO detail_penjualan (id_penjualan, id_obat, jumlah, subtotal) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, d.getIdPenjualan());
            ps.setInt(2, d.getIdObat());
            ps.setInt(3, d.getJumlah());
            ps.setDouble(4, d.getSubtotal());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan detail penjualan: " + e.getMessage());
        }
    }

    // Ambil semua detail berdasarkan id_penjualan
    public List<DetailPenjualan> getAllByPenjualan(int idPenjualan) {
        List<DetailPenjualan> list = new ArrayList<>();
        String sql = """
            o.nama AS nama_obat
            FROM detail_penjualan dp 
            JOIN obat o ON dp.id_obat = o.id_obat 
            WHERE dp.id_penjualan = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPenjualan);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetailPenjualan d = new DetailPenjualan();
                d.setIdDetail(rs.getInt("id_detail"));
                d.setIdPenjualan(rs.getInt("id_penjualan"));
                d.setIdObat(rs.getInt("id_obat"));
                d.setJumlah(rs.getInt("jumlah"));
                d.setSubtotal(rs.getDouble("subtotal"));
                d.setNamaObat(rs.getString("nama_obat")); // sesuai alias di SQL
                list.add(d);
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil detail penjualan berdasarkan ID: " + e.getMessage());
        }

        return list;
    }

    // Ambil semua detail penjualan (tanpa filter), untuk FormDetailPenjualan
    public List<DetailPenjualan> getAllDetail() {
        List<DetailPenjualan> list = new ArrayList<>();
        String sql = """
            SELECT dp.*, o.nama_obat 
            FROM detail_penjualan dp 
            JOIN obat o ON dp.id_obat = o.id_obat
        """;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DetailPenjualan d = new DetailPenjualan();
                d.setIdDetail(rs.getInt("id_detail"));
                d.setIdPenjualan(rs.getInt("id_penjualan"));
                d.setIdObat(rs.getInt("id_obat"));
                d.setJumlah(rs.getInt("jumlah"));
                d.setSubtotal(rs.getDouble("subtotal"));
                d.setNamaObat(rs.getString("nama_obat")); // sesuai alias
                list.add(d);
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil semua detail penjualan: " + e.getMessage());
        }

        return list;
    }
}







