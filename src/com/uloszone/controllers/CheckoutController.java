package com.uloszone.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/checkout")
public class CheckoutController extends HttpServlet {

    // Kita menggunakan doPost karena kita MENERIMA data baru untuk disimpan
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        // 1. Membaca aliran data JSON yang dikirim dari JavaScript
        BufferedReader reader = request.getReader();
        StringBuilder jsonString = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonString.append(line);
        }

        // 2. Membongkar (Parse) JSON tersebut
        Gson gson = new Gson();
        JsonObject pesananData = gson.fromJson(jsonString.toString(), JsonObject.class);
        
        // Mengambil variabel dari JSON
        String nama = pesananData.get("nama_pembeli").getAsString();
        // Di aplikasi nyata, kita akan menghitung total harga dengan melooping "items"
        
        // 3. (Simulasi) Memanggil DAO untuk menyimpan ke tabel 'pesanan'
        // PesananDAO pesananDAO = new PesananDAO();
        // int newOrderId = pesananDAO.simpanPesananBaru(nama, ...);

        System.out.println("Pesanan baru masuk dari: " + nama);

        // 4. Mengirim balasan ke Front-End bahwa transaksi sukses
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        // Membuat JSON balasan sederhana
        out.print("{\"status\": \"sukses\", \"id_pesanan\": \"ORD-90210\", \"pesan\": \"Pesanan Ulos sedang diproses.\"}");
        out.flush();
    }
}