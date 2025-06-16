/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.Koneksi;
import model.Petugas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetugasDAO {
    private Connection conn;

    public PetugasDAO() {
        conn = Koneksi.getConnection();
    }

    public void insertPetugas(Petugas p) {
        String sql = "INSERT INTO petugas (nama, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getUsername());
            stmt.setString(3, p.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePetugas(Petugas p) {
        String sql = "UPDATE petugas SET nama = ?, username = ?, password = ? WHERE id_petugas = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getUsername());
            stmt.setString(3, p.getPassword());
            stmt.setInt(4, p.getIdPetugas());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePetugas(int id) {
        String sql = "DELETE FROM petugas WHERE id_petugas = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Petugas> getAllPetugas() {
        List<Petugas> list = new ArrayList<>();
        String sql = "SELECT * FROM petugas";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Petugas p = new Petugas();
                p.setIdPetugas(rs.getInt("id_petugas"));
                p.setNama(rs.getString("nama"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Petugas getLoginPetugas(String username, String password) {
        String sql = "SELECT * FROM petugas WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Petugas p = new Petugas();
                p.setIdPetugas(rs.getInt("id_petugas"));
                p.setNama(rs.getString("nama"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

