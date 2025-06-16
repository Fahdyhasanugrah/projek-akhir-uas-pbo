/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dao.PelangganDAO;
import model.Pelanggan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FormPelanggan extends JFrame {
    private JTextField tfNama, tfAlamat, tfKontak;
    private JButton btnSimpan, btnReset, btnKembali; // btnHapus dihapus
    private JTable table;
    private DefaultTableModel tableModel;

    private PelangganDAO dao = new PelangganDAO();
    private int selectedId = -1;

    public FormPelanggan() {
        setTitle("Form Pelanggan");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Form Panel
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 5, 5));
        tfNama = new JTextField();
        tfAlamat = new JTextField();
        tfKontak = new JTextField();

        panelForm.add(new JLabel("Nama:"));
        panelForm.add(tfNama);
        panelForm.add(new JLabel("Alamat:"));
        panelForm.add(tfAlamat);
        panelForm.add(new JLabel("Kontak:"));
        panelForm.add(tfKontak);

        // Button Panel
        btnSimpan = new JButton("Simpan");
        btnReset = new JButton("Reset");
        btnKembali = new JButton("Kembali");

        JPanel panelButton = new JPanel();
        panelButton.add(btnSimpan);
        panelButton.add(btnReset);
        panelButton.add(btnKembali);

        // Table
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nama", "Alamat", "Kontak"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Main Layout
        setLayout(new BorderLayout());
        add(panelForm, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        // Event Listeners
        btnSimpan.addActionListener(e -> simpanPelanggan());
        btnReset.addActionListener(e -> resetForm());
        btnKembali.addActionListener(e -> {
            new FormMenuUtama().setVisible(true);
            dispose(); // tutup form ini
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    selectedId = (int) tableModel.getValueAt(row, 0);
                    tfNama.setText(tableModel.getValueAt(row, 1).toString());
                    tfAlamat.setText(tableModel.getValueAt(row, 2).toString());
                    tfKontak.setText(tableModel.getValueAt(row, 3).toString());
                }
            }
        });

        tampilkanData();
    }

    private void tampilkanData() {
        tableModel.setRowCount(0);
        List<Pelanggan> list = dao.getAllPelanggan();
        for (Pelanggan p : list) {
            Object[] row = {
                p.getIdPelanggan(), p.getNama(), p.getAlamat(), p.getKontak()
            };
            tableModel.addRow(row);
        }
    }

    private void simpanPelanggan() {
        String nama = tfNama.getText();
        String alamat = tfAlamat.getText();
        String kontak = tfKontak.getText();

        if (nama.isEmpty() || alamat.isEmpty() || kontak.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;
        }

        Pelanggan p = new Pelanggan();
        p.setNama(nama);
        p.setAlamat(alamat);
        p.setKontak(kontak);

        if (selectedId == -1) {
            dao.insertPelanggan(p);
            JOptionPane.showMessageDialog(this, "Pelanggan ditambahkan!");
        } else {
            p.setIdPelanggan(selectedId);
            dao.updatePelanggan(p);
            JOptionPane.showMessageDialog(this, "Pelanggan diperbarui!");
        }

        tampilkanData();
        resetForm();
    }

    private void resetForm() {
        tfNama.setText("");
        tfAlamat.setText("");
        tfKontak.setText("");
        selectedId = -1;
        table.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPelanggan().setVisible(true));
    }
}


