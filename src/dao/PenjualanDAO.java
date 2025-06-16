/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.Koneksi;
import model.Penjualan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PenjualanDAO {
    private Connection conn;

    public PenjualanDAO() {
        conn = Koneksi.getConnection();
    }

    // ✅ Menyimpan data penjualan dan mengembalikan id_penjualan
    public int insertPenjualan(Penjualan p) {
        String sql = "INSERT INTO penjualan (id_petugas, id_pelanggan, tanggal, total) VALUES (?, ?, ?, ?)";
        int generatedId = -1;

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, p.getIdPetugas());
            ps.setInt(2, p.getIdPelanggan());
            ps.setDate(3, new java.sql.Date(p.getTanggal().getTime()));
            ps.setDouble(4, p.getTotal());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Gagal menyimpan penjualan: " + e.getMessage());
        }

        return generatedId;
    }

    // ✅ Ambil semua data penjualan
    public List<Penjualan> getAllPenjualan() {
        List<Penjualan> list = new ArrayList<>();
        String sql = "SELECT * FROM penjualan";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Penjualan p = new Penjualan();
                p.setIdPenjualan(rs.getInt("id_penjualan"));
                p.setIdPetugas(rs.getInt("id_petugas"));
                p.setIdPelanggan(rs.getInt("id_pelanggan"));
                p.setTanggal(rs.getDate("tanggal"));
                p.setTotal(rs.getDouble("total"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil daftar penjualan: " + e.getMessage());
        }

        return list;
    }

    // ✅ Ambil data penjualan berdasarkan ID
    public Penjualan getById(int idPenjualan) {
        String sql = "SELECT * FROM penjualan WHERE id_penjualan = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPenjualan);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Penjualan p = new Penjualan();
                p.setIdPenjualan(rs.getInt("id_penjualan"));
                p.setIdPetugas(rs.getInt("id_petugas"));
                p.setIdPelanggan(rs.getInt("id_pelanggan"));
                p.setTanggal(rs.getDate("tanggal"));
                p.setTotal(rs.getDouble("total"));
                return p;
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil penjualan by ID: " + e.getMessage());
        }
        return null;
    }
}


