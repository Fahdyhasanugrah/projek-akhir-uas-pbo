/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package view;

import dao.ObatDAO;
import model.Obat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Formobat extends JFrame {

    private JTextField tfNama, tfJenis, tfHarga, tfStok;
    private JButton btnSimpan, btnHapus, btnReset, btnKembali;
    private JTable table;
    private DefaultTableModel tableModel;

    private ObatDAO dao = new ObatDAO();
    private int selectedId = -1;

    public Formobat() {
        setTitle("Form Obat - Toko Obat");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel Input
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        tfNama = new JTextField();
        tfJenis = new JTextField();
        tfHarga = new JTextField();
        tfStok = new JTextField();

        panelForm.add(new JLabel("Nama Obat:"));
        panelForm.add(tfNama);
        panelForm.add(new JLabel("Jenis:"));
        panelForm.add(tfJenis);
        panelForm.add(new JLabel("Harga:"));
        panelForm.add(tfHarga);
        panelForm.add(new JLabel("Stok:"));
        panelForm.add(tfStok);

        // Tombol
        btnSimpan = new JButton("Simpan");
        btnHapus = new JButton("Hapus");
        btnReset = new JButton("Reset");
        btnKembali = new JButton("Kembali");

        JPanel panelButton = new JPanel();
        panelButton.add(btnSimpan);
        panelButton.add(btnHapus);
        panelButton.add(btnReset);
        panelButton.add(btnKembali);

        // Tabel
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nama", "Jenis", "Harga", "Stok"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Layout utama
        setLayout(new BorderLayout());
        add(panelForm, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        // Event
        btnSimpan.addActionListener(e -> simpanObat());
        btnHapus.addActionListener(e -> hapusObat());
        btnReset.addActionListener(e -> resetForm());
        btnKembali.addActionListener(e -> {
            new FormMenuUtama().setVisible(true);
            dispose();
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int baris = table.getSelectedRow();
                if (baris != -1) {
                    selectedId = (int) tableModel.getValueAt(baris, 0);
                    tfNama.setText(tableModel.getValueAt(baris, 1).toString());
                    tfJenis.setText(tableModel.getValueAt(baris, 2).toString());
                    tfHarga.setText(tableModel.getValueAt(baris, 3).toString());
                    tfStok.setText(tableModel.getValueAt(baris, 4).toString());
                }
            }
        });

        tampilkanData();
    }

    private void tampilkanData() {
        tableModel.setRowCount(0); // clear table
        List<Obat> list = dao.getAllObat();
        for (Obat o : list) {
            Object[] row = { o.getIdObat(), o.getNama(), o.getJenis(), o.getHarga(), o.getStok() };
            tableModel.addRow(row);
        }
    }

    private void simpanObat() {
        try {
            String nama = tfNama.getText();
            String jenis = tfJenis.getText();
            double harga = Double.parseDouble(tfHarga.getText());
            int stok = Integer.parseInt(tfStok.getText());

            Obat o = new Obat();
            o.setNama(nama);
            o.setJenis(jenis);
            o.setHarga(harga);
            o.setStok(stok);

            if (selectedId == -1) {
                dao.insertObat(o);
                JOptionPane.showMessageDialog(this, "Obat ditambahkan!");
            } else {
                o.setIdObat(selectedId);
                dao.updateObat(o);
                JOptionPane.showMessageDialog(this, "Obat diperbarui!");
            }

            tampilkanData();
            resetForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga dan stok harus angka!");
        }
    }

    private void hapusObat() {
        if (selectedId != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dao.deleteObat(selectedId);
                tampilkanData();
                resetForm();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data dulu!");
        }
    }

    private void resetForm() {
        tfNama.setText("");
        tfJenis.setText("");
        tfHarga.setText("");
        tfStok.setText("");
        selectedId = -1;
        table.clearSelection();
    }

    // MAIN METHOD
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Formobat().setVisible(true);
        });
    }
}

