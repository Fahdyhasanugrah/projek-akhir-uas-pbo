/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;

public class FormMenuUtama extends JFrame {

    private JButton btnObat, btnPetugas, btnPenjualan, btnPelanggan, btnLogout;

    public FormMenuUtama() {
        setTitle("Menu Utama");
        setSize(300, 300); // Kembali ke tinggi untuk 5 tombol
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        btnObat = new JButton("daftar Obat");
        btnObat.setBounds(80, 20, 130, 30);
        add(btnObat);

        btnPetugas = new JButton("daftar Petugas");
        btnPetugas.setBounds(80, 60, 130, 30);
        add(btnPetugas);

        btnPenjualan = new JButton("jual obat");
        btnPenjualan.setBounds(80, 100, 130, 30);
        add(btnPenjualan);

        btnPelanggan = new JButton("daftar Pelanggan");
        btnPelanggan.setBounds(80, 140, 130, 30);
        add(btnPelanggan);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(80, 180, 130, 30);
        add(btnLogout);

        // Aksi tombol
        btnObat.addActionListener(e -> {
            new Formobat().setVisible(true);
            dispose();
        });

        btnPetugas.addActionListener(e -> {
            new FormPetugas().setVisible(true);
            dispose();
        });

        btnPenjualan.addActionListener(e -> {
            new FormPenjualan().setVisible(true);
            dispose();
        });

        btnPelanggan.addActionListener(e -> {
            new FormPelanggan().setVisible(true);
            dispose();
        });

        btnLogout.addActionListener(e -> {
            int konfirmasi = JOptionPane.showConfirmDialog(
                    this,
                    "Apakah kamu yakin ingin logout?",
                    "Konfirmasi Logout",
                    JOptionPane.YES_NO_OPTION
            );
            if (konfirmasi == JOptionPane.YES_OPTION) {
                new FormLogin().setVisible(true);
                dispose();
            }
        });
    }

    // === Tambahkan main method di bawah ini ===
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormMenuUtama().setVisible(true));
    }
}






