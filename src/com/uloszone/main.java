package com.uloszone;

import com.uloszone.dao.ProdukDAO;
import com.uloszone.models.ProdukUlos;
import java.util.List;

public class main {
    public static void main(String[] args) {
        System.out.println("=== Memulai Aplikasi Ulos Zone ===");

        // Inisialisasi DAO
        ProdukDAO produkDAO = new ProdukDAO();

        // Memanggil method untuk mengambil data dari PostgreSQL
        System.out.println("\nMengambil data dari database...\n");
        List<ProdukUlos> daftarUlos = produkDAO.getAllProduk();

        // Menampilkan hasilnya
        if (daftarUlos.isEmpty()) {
            System.out.println("Katalog masih kosong atau gagal terhubung ke database.");
        } else {
            System.out.println("--- Katalog Produk Ulos ---");
            for (ProdukUlos ulos : daftarUlos) {
                ulos.cetakInfoProduk(); // Method yang kita buat di Class ProdukUlos sebelumnya
                System.out.println("---------------------------");
            }
        }
    }
}