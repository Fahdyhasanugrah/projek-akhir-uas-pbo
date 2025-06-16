/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// === File: FormLogin.java ===
package view;

import dao.PetugasDAO;
import model.Petugas;

import javax.swing.*;

public class FormLogin extends JFrame {

    private JTextField tfUsername;
    private JPasswordField tfPassword;
    private JButton btnLogin, btnBatal;

    public FormLogin() {
        setTitle("Login Petugas");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(30, 30, 80, 25);
        add(lblUsername);

        tfUsername = new JTextField();
        tfUsername.setBounds(120, 30, 180, 25);
        add(tfUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(30, 70, 80, 25);
        add(lblPassword);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(120, 70, 180, 25);
        add(tfPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(60, 110, 100, 30);
        add(btnLogin);

        btnBatal = new JButton("Batal");
        btnBatal.setBounds(180, 110, 100, 30);
        add(btnBatal);

        btnLogin.addActionListener(e -> prosesLogin());
        btnBatal.addActionListener(e -> System.exit(0));
    }

    private void prosesLogin() {
        String username = tfUsername.getText();
        String password = String.valueOf(tfPassword.getPassword());

        PetugasDAO dao = new PetugasDAO();
        Petugas petugas = dao.getLoginPetugas(username, password);

        if (petugas != null) {
            JOptionPane.showMessageDialog(this, "Login berhasil!\nSelamat datang, " + petugas.getNama());
            new FormMenuUtama().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Login gagal!\nUsername atau password salah.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormLogin().setVisible(true));
    }
}






