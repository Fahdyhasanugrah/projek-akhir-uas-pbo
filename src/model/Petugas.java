/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Petugas {
    private int idPetugas;
    private String nama;
    private String username;
    private String password;

    public Petugas() {
    }

    public Petugas(int idPetugas, String nama, String username, String password) {
        this.idPetugas = idPetugas;
        this.nama = nama;
        this.username = username;
        this.password = password;
    }

    public int getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(int idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


