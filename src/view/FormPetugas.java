/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dao.PetugasDAO;
import model.Petugas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FormPetugas extends JFrame {
    private JTextField tfNama, tfUsername, tfPassword;
    private JButton btnSimpan, btnHapus, btnReset, btnKembali;
    private JTable table;
    private DefaultTableModel tableModel;

    private PetugasDAO dao = new PetugasDAO();
    private int selectedId = -1;

    public FormPetugas() {
        setTitle("Form Petugas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Form input
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 5, 5));
        tfNama = new JTextField();
        tfUsername = new JTextField();
        tfPassword = new JTextField();

        panelForm.add(new JLabel("Nama:"));
        panelForm.add(tfNama);
        panelForm.add(new JLabel("Username:"));
        panelForm.add(tfUsername);
        panelForm.add(new JLabel("Password:"));
        panelForm.add(tfPassword);

        // Tombol
        btnSimpan = new JButton("Simpan");
        btnHapus = new JButton("Hapus");
        btnReset = new JButton("Reset");
        btnKembali = new JButton("Kembali");

        JPanel panelButton = new JPanel();
        panelButton.add(btnSimpan);
        panelButton.add(btnHapus);
        panelButton.add(btnReset);
        panelButton.add(btnKembali); // Tambahkan tombol kembali

        // Tabel
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nama", "Username", "Password"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Layout
        setLayout(new BorderLayout());
        add(panelForm, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        // Event
        btnSimpan.addActionListener(e -> simpanPetugas());
        btnHapus.addActionListener(e -> hapusPetugas());
        btnReset.addActionListener(e -> resetForm());
        btnKembali.addActionListener(e -> {
            new FormMenuUtama().setVisible(true); // Kembali ke menu utama
            dispose();
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int baris = table.getSelectedRow();
                if (baris != -1) {
                    selectedId = (int) tableModel.getValueAt(baris, 0);
                    tfNama.setText(tableModel.getValueAt(baris, 1).toString());
                    tfUsername.setText(tableModel.getValueAt(baris, 2).toString());
                    tfPassword.setText(tableModel.getValueAt(baris, 3).toString());
                }
            }
        });

        tampilkanData();
    }

    private void tampilkanData() {
        tableModel.setRowCount(0);
        List<Petugas> list = dao.getAllPetugas();
        for (Petugas p : list) {
            Object[] row = {
                p.getIdPetugas(),
                p.getNama(),
                p.getUsername(),
                p.getPassword()
            };
            tableModel.addRow(row);
        }
    }

    private void simpanPetugas() {
        String nama = tfNama.getText();
        String username = tfUsername.getText();
        String password = tfPassword.getText();

        if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Isi semua kolom!");
            return;
        }

        Petugas p = new Petugas();
        p.setNama(nama);
        p.setUsername(username);
        p.setPassword(password);

        if (selectedId == -1) {
            dao.insertPetugas(p);
            JOptionPane.showMessageDialog(this, "Petugas ditambahkan.");
        } else {
            p.setIdPetugas(selectedId);
            dao.updatePetugas(p);
            JOptionPane.showMessageDialog(this, "Petugas diperbarui.");
        }

        tampilkanData();
        resetForm();
    }

    private void hapusPetugas() {
        if (selectedId != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dao.deletePetugas(selectedId);
                tampilkanData();
                resetForm();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data dulu!");
        }
    }

    private void resetForm() {
        tfNama.setText("");
        tfUsername.setText("");
        tfPassword.setText("");
        selectedId = -1;
        table.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPetugas().setVisible(true));
    }
}


