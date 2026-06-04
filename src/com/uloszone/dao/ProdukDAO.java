package com.uloszone.dao;

import com.uloszone.config.DatabaseConfig;
import com.uloszone.models.ProdukUlos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdukDAO {

    // Method untuk mengambil semua data Ulos dari database
    public List<ProdukUlos> getAllProduk() {
        List<ProdukUlos> daftarProduk = new ArrayList<>();
        // Query SQL
        String query = "SELECT * FROM produk_ulos";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Looping hasil query dari PostgreSQL
            while (rs.next()) {
                // Mengambil data per kolom
                String idProduk = rs.getString("id_produk");
                String namaUlos = rs.getString("nama_ulos");
                double harga = rs.getDouble("harga");
                String deskripsi = rs.getString("deskripsi");

                // Membuat objek ProdukUlos baru
                ProdukUlos produk = new ProdukUlos(idProduk, namaUlos, harga, deskripsi);
                
                // Memasukkan ke dalam list
                daftarProduk.add(produk);
            }

        } catch (SQLException e) {
            System.out.println("Error: Gagal mengambil data produk dari database.");
            e.printStackTrace();
        }

        return daftarProduk;
    }
}