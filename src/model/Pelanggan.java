/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Pelanggan {
    private int idPelanggan;
    private String nama;
    private String alamat;
    private String kontak;

    // Constructor kosong
    public Pelanggan() {
    }

    // Constructor lengkap
    public Pelanggan(int idPelanggan, String nama, String alamat, String kontak) {
        this.idPelanggan = idPelanggan;
        this.nama = nama;
        this.alamat = alamat;
        this.kontak = kontak;
    }

    // Getter dan Setter
    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }
}

