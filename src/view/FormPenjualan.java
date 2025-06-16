/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dao.*;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class FormPenjualan extends JFrame {

    private JTextField tfNama, tfKontak, tfAlamat, tfJumlah;
    private JComboBox<Obat> cbObat;
    private JButton btnBeli, btnCheckOut, btnReset, btnKembali;
    private JTable tableItem;
    private DefaultTableModel tableModel;

    private List<Obat> daftarObat;
    private ObatDAO obatDAO = new ObatDAO();
    private PelangganDAO pelangganDAO = new PelangganDAO();
    private PenjualanDAO penjualanDAO = new PenjualanDAO();
    private DetailPenjualanDAO detailDAO = new DetailPenjualanDAO();

    public FormPenjualan() {
        setTitle("Form Penjualan Obat");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Data Pelanggan
        JPanel panelPelanggan = new JPanel(new GridLayout(3, 2, 5, 5));
        tfNama = new JTextField();
        tfKontak = new JTextField();
        tfAlamat = new JTextField();
        panelPelanggan.setBorder(BorderFactory.createTitledBorder("Data Pelanggan"));

        panelPelanggan.add(new JLabel("Nama:"));
        panelPelanggan.add(tfNama);
        panelPelanggan.add(new JLabel("Kontak:"));
        panelPelanggan.add(tfKontak);
        panelPelanggan.add(new JLabel("Alamat:"));
        panelPelanggan.add(tfAlamat);

        // Panel Item Obat
        JPanel panelItem = new JPanel();
        panelItem.setBorder(BorderFactory.createTitledBorder("Tambah Obat"));
        daftarObat = obatDAO.getAllObat();
        cbObat = new JComboBox<>(daftarObat.toArray(new Obat[0]));
        tfJumlah = new JTextField(5);
        btnBeli = new JButton("Beli");

        panelItem.add(new JLabel("Obat:"));
        panelItem.add(cbObat);
        panelItem.add(new JLabel("Jumlah:"));
        panelItem.add(tfJumlah);
        panelItem.add(btnBeli);

        // Tabel Item
        tableModel = new DefaultTableModel(new Object[]{"ID Obat", "Nama", "Jumlah", "Harga", "Subtotal"}, 0);
        tableItem = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableItem);

        // Tombol
        JPanel panelButton = new JPanel();
        btnCheckOut = new JButton("Check Out");
        btnReset = new JButton("Reset");
        btnKembali = new JButton("Kembali");

        panelButton.add(btnCheckOut);
        panelButton.add(btnReset);
        panelButton.add(btnKembali);

        // Tambah Komponen ke Frame
        add(panelPelanggan, BorderLayout.NORTH);
        add(panelItem, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        add(panelButton, BorderLayout.PAGE_END);

        // Event Listener
        btnBeli.addActionListener(e -> tambahItem());
        btnCheckOut.addActionListener(e -> simpanPenjualan());
        btnReset.addActionListener(e -> resetForm());
        btnKembali.addActionListener(e -> {
            dispose();
            new FormMenuUtama().setVisible(true);
        });
    }

    private void tambahItem() {
        Obat obat = (Obat) cbObat.getSelectedItem();
        int jumlah;

        try {
            jumlah = Integer.parseInt(tfJumlah.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!");
            return;
        }

        if (jumlah <= 0) {
            JOptionPane.showMessageDialog(this, "Jumlah harus lebih dari 0!");
            return;
        }

        if (jumlah > obat.getStok()) {
            JOptionPane.showMessageDialog(this, "Stok tidak mencukupi!");
            return;
        }

        double subtotal = jumlah * obat.getHarga();
        tableModel.addRow(new Object[]{
                obat.getIdObat(),
                obat.getNama(),
                jumlah,
                obat.getHarga(),
                subtotal
        });

        tfJumlah.setText("");
    }

    private void simpanPenjualan() {
        if (tfNama.getText().isEmpty() || tfKontak.getText().isEmpty() || tfAlamat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua data pelanggan wajib diisi!");
            return;
        }

        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tambahkan minimal satu obat ke tabel!");
            return;
        }

        // Simpan data pelanggan
        Pelanggan pelanggan = new Pelanggan();
        pelanggan.setNama(tfNama.getText());
        pelanggan.setKontak(tfKontak.getText());
        pelanggan.setAlamat(tfAlamat.getText());
        int idPelanggan = pelangganDAO.insertAndReturnId(pelanggan);

        // Simpan penjualan
        Penjualan penjualan = new Penjualan();
        penjualan.setIdPelanggan(idPelanggan);
        penjualan.setTanggal(new Date());
        penjualan.setIdPetugas(1); // sementara hardcode ID petugas
        double total = 0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Object obj = tableModel.getValueAt(i, 4);
            if (obj instanceof Number) {
                total += ((Number) obj).doubleValue();
            }
        }

        penjualan.setTotal(total);
        int idPenjualan = penjualanDAO.insertPenjualan(penjualan);

        // Simpan detail penjualan & update stok
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int idObat = (int) tableModel.getValueAt(i, 0);
            int jumlah = (int) tableModel.getValueAt(i, 2);
            double subtotal = (double) tableModel.getValueAt(i, 4);

            DetailPenjualan detail = new DetailPenjualan();
            detail.setIdPenjualan(idPenjualan);
            detail.setIdObat(idObat);
            detail.setJumlah(jumlah);
            detail.setSubtotal(subtotal);
            detailDAO.insert(detail);

            // Update stok
            Obat obat = obatDAO.getObatById(idObat);
            int stokBaru = obat.getStok() - jumlah;
            obatDAO.updateStok(idObat, stokBaru);
        }

        JOptionPane.showMessageDialog(this, "Penjualan berhasil disimpan!");
        resetForm();
    }

    private void resetForm() {
        tfNama.setText("");
        tfKontak.setText("");
        tfAlamat.setText("");
        tfJumlah.setText("");
        tableModel.setRowCount(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPenjualan().setVisible(true));
    }
}




