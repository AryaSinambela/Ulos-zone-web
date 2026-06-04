# Ulos Zone - E-Commerce Tradisional & Pelestarian Budaya

Ulos Zone adalah platform aplikasi web e-commerce *Full-Stack* yang didedikasikan untuk melestarikan dan mendistribusikan kain tenun Ulos tradisional. Proyek ini dibangun menggunakan arsitektur MVC (Model-View-Controller) murni untuk memisahkan logika bisnis, antarmuka pengguna, dan manajemen basis data.

## 🌟 Fitur Utama
* **Katalog Dinamis:** Antarmuka responsif yang menampilkan produk kain Ulos beserta nilai filosofisnya.
* **Keranjang Belanja Interaktif:** Menggunakan JavaScript (DOM Manipulation & Fetch API) untuk menambahkan item ke keranjang dan menghitung total harga secara *real-time* tanpa memuat ulang halaman.
* **Sistem Checkout API:** Data pesanan dikirim dalam format JSON melalui metode HTTP POST ke server untuk diproses.
* **Integrasi Database:** Menyimpan data produk dan riwayat transaksi secara terstruktur menggunakan PostgreSQL.

## 🛠️ Teknologi yang Digunakan (Tech Stack)
* **Front-End:** HTML5, CSS3, Vanilla JavaScript (ES6+).
* **Back-End:** Java, Java Servlets (API Endpoint), Gson (JSON Parser).
* **Database:** PostgreSQL & JDBC (Java Database Connectivity).
* **UI/UX & Desain:** Figma (Prototyping), CSS Animasi (SVG).

## 📂 Struktur Direktori Proyek
```text
Ulos-Zone-Web/
├── frontend/
│   ├── css/style.css       # Desain antarmuka & animasi UI
│   ├── js/script.js        # Logika keranjang & Fetch API
│   └── index.html          # Struktur halaman utama
├── src/com/uloszone/
│   ├── config/             # Konfigurasi koneksi JDBC (DatabaseConfig.java)
│   ├── models/             # Kelas entitas OOP (ProdukUlos.java)
│   ├── dao/                # Data Access Object untuk Query SQL
│   └── controllers/        # Java Servlet untuk melayani HTTP GET & POST
├── database/
