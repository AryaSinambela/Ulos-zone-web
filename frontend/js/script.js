// Array untuk menyimpan data keranjang
let keranjangBelanja = []; 

// Modifikasi fungsi klik tombol beli (di dalam attachBuyEvents)
button.addEventListener('click', function(e) {
    const productCard = e.target.closest('.product-card');
    const namaProduk = productCard.querySelector('h3').innerText;
    // Mengambil harga, menghilangkan "Rp " dan titik, lalu ubah ke angka
    const hargaText = productCard.querySelector('.price').innerText;
    const hargaAngka = parseInt(hargaText.replace(/[^0-9]/g, '')); 
    
    // Masukkan ke array keranjang
    keranjangBelanja.push({
        nama_ulos: namaProduk,
        harga: hargaAngka,
        jumlah: 1
    });

    alert(`${namaProduk} ditambahkan! Total item: ${keranjangBelanja.length}`);
});

// --- FUNGSI BARU: Proses Checkout ---
async function prosesCheckout() {
    if (keranjangBelanja.length === 0) {
        alert("Keranjang masih kosong!");
        return;
    }

    // Menyiapkan paket data (Payload) untuk dikirim ke Java
    const dataTransaksi = {
        nama_pembeli: "Pelanggan Guest",
        email_pembeli: "guest@email.com",
        items: keranjangBelanja
    };

    try {
        // Mengirim data ke API Java menggunakan metode POST
        const response = await fetch('http://localhost:8080/UlosZoneWeb/api/checkout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dataTransaksi) // Ubah objek JS menjadi string JSON
        });

        const result = await response.json();
        
        if (result.status === "sukses") {
            alert(`Pesanan Berhasil! ID Pesanan Anda: ${result.id_pesanan}`);
            keranjangBelanja = []; // Kosongkan keranjang setelah sukses
        } else {
            alert("Gagal memproses pesanan.");
        }

    } catch (error) {
        console.error("Error saat checkout:", error);
    }

    document.addEventListener('DOMContentLoaded', () => {
    // ... (Kode fetchProdukUlos biarkan sama seperti sebelumnya) ...

    let keranjangBelanja = [];
    
    // Elemen Modal
    const cartModal = document.getElementById('cartModal');
    const closeCart = document.getElementById('closeCart');
    const cartItemsList = document.getElementById('cartItemsList');
    const cartTotal = document.getElementById('cartTotal');
    const btnProsesCheckout = document.getElementById('btnProsesCheckout');

    // Menutup Modal Keranjang
    closeCart.addEventListener('click', () => {
        cartModal.style.display = 'none';
    });

    // Fungsi untuk memperbarui tampilan dalam modal keranjang
    function updateCartUI() {
        cartItemsList.innerHTML = '';
        let total = 0;

        keranjangBelanja.forEach(item => {
            total += item.harga;
            cartItemsList.insertAdjacentHTML('beforeend', `
                <div class="cart-item">
                    <span>${item.nama_ulos}</span>
                    <span>Rp ${item.harga.toLocaleString('id-ID')}</span>
                </div>
            `);
        });

        cartTotal.innerText = `Rp ${total.toLocaleString('id-ID')}`;
        cartModal.style.display = 'flex'; // Tampilkan modal
    }

    // Fungsi untuk tombol beli (dimodifikasi)
    function attachBuyEvents() {
        const buyButtons = document.querySelectorAll('.buy-btn');
        buyButtons.forEach(button => {
            button.addEventListener('click', function(e) {
                const productCard = e.target.closest('.product-card');
                const namaProduk = productCard.querySelector('h3').innerText;
                const hargaText = productCard.querySelector('.price').innerText;
                const hargaAngka = parseInt(hargaText.replace(/[^0-9]/g, '')); 
                
                // Tambahkan ke array keranjang
                keranjangBelanja.push({
                    nama_ulos: namaProduk,
                    harga: hargaAngka
                });

                // Perbarui UI dan munculkan modal keranjang
                updateCartUI();
            });
        });
    }

    // Fungsi Checkout mengirim data ke API Java
    btnProsesCheckout.addEventListener('click', async () => {
        if (keranjangBelanja.length === 0) return;

        const metodePembayaran = document.getElementById('paymentMethod').value;
        btnProsesCheckout.innerText = "Memproses...";

        const payload = {
            nama_pembeli: "Guest",
            metode_pembayaran: metodePembayaran,
            items: keranjangBelanja
        };

        try {
            // Memanggil Endpoint API Java yang sudah kita buat sebelumnya
            const response = await fetch('http://localhost:8080/UlosZoneWeb/api/checkout', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });

            const result = await response.json();
            
            if (result.status === "sukses") {
                // Beri instruksi sesuai metode pembayaran
                alert(`Pesanan Berhasil!\nID: ${result.id_pesanan}\nSilakan lanjutkan pembayaran menggunakan aplikasi ${metodePembayaran.toUpperCase()} Anda.`);
                
                keranjangBelanja = []; // Kosongkan keranjang
                cartModal.style.display = 'none'; // Tutup modal
                btnProsesCheckout.innerText = "Bayar Sekarang";
            }
        } catch (error) {
            console.error("Error checkout:", error);
            alert("Maaf, terjadi kesalahan pada server.");
            btnProsesCheckout.innerText = "Bayar Sekarang";
        }
    });

});
}