/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.Koneksi;
import model.Obat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObatDAO {

    private Connection conn;

    public ObatDAO() {
        conn = Koneksi.getConnection();
    }

    // Ambil semua data obat
    public List<Obat> getAllObat() {
        List<Obat> list = new ArrayList<>();
        String sql = "SELECT * FROM obat";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Obat o = new Obat();
                o.setIdObat(rs.getInt("id_obat"));
                o.setNama(rs.getString("nama"));
                o.setJenis(rs.getString("jenis"));
                o.setHarga(rs.getDouble("harga"));
                o.setStok(rs.getInt("stok"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data obat: " + e.getMessage());
        }

        return list;
    }

    // Tambah obat
    public void insertObat(Obat o) {
        String sql = "INSERT INTO obat (nama, jenis, harga, stok) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, o.getNama());
            ps.setString(2, o.getJenis());
            ps.setDouble(3, o.getHarga());
            ps.setInt(4, o.getStok());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan obat: " + e.getMessage());
        }
    }

    // Update obat
    public void updateObat(Obat o) {
        String sql = "UPDATE obat SET nama=?, jenis=?, harga=?, stok=? WHERE id_obat=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, o.getNama());
            ps.setString(2, o.getJenis());
            ps.setDouble(3, o.getHarga());
            ps.setInt(4, o.getStok());
            ps.setInt(5, o.getIdObat());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal mengubah data obat: " + e.getMessage());
        }
    }

    // Hapus obat
    public void deleteObat(int id) {
        String sql = "DELETE FROM obat WHERE id_obat=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal menghapus obat: " + e.getMessage());
        }
    }

    // ✅ Ambil data obat berdasarkan ID
    public Obat getObatById(int id) {
        String sql = "SELECT * FROM obat WHERE id_obat=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Obat o = new Obat();
                o.setIdObat(rs.getInt("id_obat"));
                o.setNama(rs.getString("nama"));
                o.setJenis(rs.getString("jenis"));
                o.setHarga(rs.getDouble("harga"));
                o.setStok(rs.getInt("stok"));
                return o;
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil obat by ID: " + e.getMessage());
        }

        return null;
    }

    // ✅ Update stok obat
    public void updateStok(int idObat, int stokBaru) {
        String sql = "UPDATE obat SET stok = ? WHERE id_obat = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, stokBaru);
            ps.setInt(2, idObat);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal update stok: " + e.getMessage());
        }
    }

    // ✅ Kurangi stok obat
    public void kurangiStok(int idObat, int jumlahDikurangi) {
        Obat o = getObatById(idObat);
        if (o != null) {
            int stokBaru = o.getStok() - jumlahDikurangi;
            if (stokBaru < 0) stokBaru = 0; // Hindari stok negatif
            updateStok(idObat, stokBaru);
        }
    }
}


