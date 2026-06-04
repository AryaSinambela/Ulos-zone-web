package com.uloszone.controllers;

import com.uloszone.dao.ProdukDAO;
import com.uloszone.models.ProdukUlos;
import com.google.gson.Gson; // Library untuk mengubah Java Object ke JSON

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// Endpoint API yang akan dipanggil oleh Frontend
@WebServlet("/api/produk")
public class ProdukController extends HttpServlet {
    
    private ProdukDAO produkDAO;

    public void init() {
        produkDAO = new ProdukDAO(); // Inisialisasi koneksi database
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Mengatur format balasan menjadi JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 1. Ambil data dari PostgreSQL menggunakan DAO
        List<ProdukUlos> daftarUlos = produkDAO.getAllProduk();

        // 2. Ubah data List Java menjadi format string JSON
        Gson gson = new Gson();
        String jsonResult = gson.toJson(daftarUlos);

        // 3. Kirim JSON ke Frontend
        PrintWriter out = response.getWriter();
        out.print(jsonResult);
        out.flush();
    }
}