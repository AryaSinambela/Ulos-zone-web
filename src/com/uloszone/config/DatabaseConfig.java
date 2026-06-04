package com.uloszone.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    
    // Sesuaikan kredensial ini dengan pengaturan PostgreSQL di pgAdmin kamu
    private static final String URL = "jdbc:postgresql://localhost:5432/ulos_db";
    private static final String USER = "postgres"; // username default PostgreSQL
    private static final String PASSWORD = "password_kamu_di_sini"; // ganti dengan password aslimu

    private static Connection connection = null;

    // Method untuk mendapatkan koneksi (Menggunakan pola Singleton sederhana)
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Memuat driver PostgreSQL (opsional untuk versi JDBC terbaru, tapi baik untuk memastikan)
                Class.forName("org.postgresql.Driver");
                
                // Membuka koneksi
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Status: Berhasil terhubung ke database PostgreSQL!");
                
            } catch (ClassNotFoundException e) {
                System.out.println("Error: PostgreSQL JDBC Driver tidak ditemukan. Pastikan file .jar sudah ada di folder lib.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Error: Gagal terhubung ke database. Periksa URL, USER, dan PASSWORD.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Method untuk menutup koneksi (dipanggil saat aplikasi dimatikan)
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Status: Koneksi ke database ditutup.");
            } catch (SQLException e) {
                System.out.println("Error: Gagal menutup koneksi database.");
                e.printStackTrace();
            }
        }
    }
}