/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Obat {
    private int idObat;
    private String nama;
    private String jenis;
    private double harga;
    private int stok;

    // Constructor tanpa parameter
    public Obat() {}

    // Constructor lengkap
    public Obat(int idObat, String nama, String jenis, double harga, int stok) {
        this.idObat = idObat;
        this.nama = nama;
        this.jenis = jenis;
        this.harga = harga;
        this.stok = stok;
    }

    // Getter dan Setter
    public int getIdObat() {
        return idObat;
    }

    public void setIdObat(int idObat) {
        this.idObat = idObat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    // Override toString agar JComboBox menampilkan nama obat
    @Override
    public String toString() {
        return nama;
    }
}

