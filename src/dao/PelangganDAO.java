/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.Koneksi;
import model.Pelanggan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganDAO {
    private Connection conn;

    public PelangganDAO() {
        conn = Koneksi.getConnection();
    }

    public void insertPelanggan(Pelanggan p) {
        String sql = "INSERT INTO pelanggan (nama, alamat, kontak) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getAlamat());
            stmt.setString(3, p.getKontak());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Tambahan: Insert dan kembalikan ID pelanggan
    public int insertAndReturnId(Pelanggan p) {
        String sql = "INSERT INTO pelanggan (nama, alamat, kontak) VALUES (?, ?, ?)";
        int generatedId = -1;

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getAlamat());
            stmt.setString(3, p.getKontak());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    public void updatePelanggan(Pelanggan p) {
        String sql = "UPDATE pelanggan SET nama = ?, alamat = ?, kontak = ? WHERE id_pelanggan = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getAlamat());
            stmt.setString(3, p.getKontak());
            stmt.setInt(4, p.getIdPelanggan());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePelanggan(int id) {
        String sql = "DELETE FROM pelanggan WHERE id_pelanggan = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pelanggan> getAllPelanggan() {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pelanggan p = new Pelanggan();
                p.setIdPelanggan(rs.getInt("id_pelanggan"));
                p.setNama(rs.getString("nama"));
                p.setAlamat(rs.getString("alamat"));
                p.setKontak(rs.getString("kontak"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Pelanggan getById(int id) {
        String sql = "SELECT * FROM pelanggan WHERE id_pelanggan = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pelanggan p = new Pelanggan();
                p.setIdPelanggan(rs.getInt("id_pelanggan"));
                p.setNama(rs.getString("nama"));
                p.setAlamat(rs.getString("alamat"));
                p.setKontak(rs.getString("kontak"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Opsional: Insert jika tidak ada
    public int getOrInsertPelanggan(String nama, String alamat, String kontak) {
        String checkSql = "SELECT id_pelanggan FROM pelanggan WHERE nama = ? AND kontak = ?";
        String insertSql = "INSERT INTO pelanggan (nama, alamat, kontak) VALUES (?, ?, ?)";

        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, nama);
            checkStmt.setString(2, kontak);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_pelanggan");
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setString(1, nama);
                insertStmt.setString(2, alamat);
                insertStmt.setString(3, kontak);
                insertStmt.executeUpdate();

                ResultSet keys = insertStmt.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}



