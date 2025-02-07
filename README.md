Final Proyek Pemrograman Berorientasi Obyek 1
* Mata Kuliah: Pemrograman Berorientasi Obyek 1
* Dosen Pengampu: Muhammad Ikhwan Fathulloh

Kelompok

Kelompok: {AFJ} 

Proyek: {Sistem Manajemen Gudang) 

Anggota:

Ketua: Aldi Rizkiansyah 

Anggota 1: Muhamad Farhan Rizki 

Anggota 2: Jujun Sahroni

Judul Studi Kasus

Pengembangan Sistem Informasi Manajemen Gudang untuk Pengelolaan Data Barang

Penjelasan Studi Kasus

Sistem Manajemen Gudang adalah sebuah aplikasi berbasis desktop yang berfungsi untuk mengelola data barang di gudang. Fitur utama aplikasi ini meliputi:  
A. Menambah Data Barang: Pengguna dapat menambahkan data barang baru ke dalam sistem, seperti kode barang, nama barang, stok, harga beli, dan harga jual.  
B. Mengedit Data Barang: Pengguna dapat memperbarui informasi barang, seperti jumlah stok atau harga.  
C. Menghapus Data Barang: Pengguna dapat menghapus data barang yang sudah tidak diperlukan.  
D. Menampilkan Data Barang: Sistem menampilkan seluruh data barang yang tersimpan dalam database dalam bentuk tabel. 

Aplikasi ini terintegrasi dengan database menggunakan koneksi JDBC untuk memastikan data tersimpan dan dapat diakses secara real-time. Dengan tampilan antarmuka yang sederhana, sistem ini mudah digunakan oleh siapa saja, termasuk pengguna yang tidak memiliki latar belakang teknis.  

Penjelasan 4 Pilar OOP dalam Studi Kasus

1. Inheritance
Kelas ItemOperations mewarisi sifat dari DatabaseConnection, sehingga semua operasi database dapat diakses dengan mudah.
2. Encapsulation
Variabel dalam kelas Item dibuat private dan hanya bisa diakses melalui metode getter dan setter untuk menjaga keamanan data.
3. Polymorphism
Metode addItem() dan updateItem() dalam ItemOperations menggunakan parameter yang sama tetapi memiliki perilaku berbeda berdasarkan kondisi barang baru atau update.
4. Abstract
   Kelas DatabaseConnection menyembunyikan detail koneksi ke database, sehingga pengguna hanya perlu memanggil fungsi koneksi tanpa perlu tahu cara kerjanya di belakang layar.
   
Struktur Tabel Aplikasi

Tabel items digunakan untuk menyimpan data barang yang ada di dalam gudang. Setiap barang memiliki ID unik yang dihasilkan secara otomatis oleh sistem (Auto Increment). Barang juga memiliki nama, kategori, dan jumlah stok yang tersedia. Selain itu, terdapat kolom created_at yang otomatis mencatat waktu saat barang tersebut ditambahkan ke dalam sistem. Dengan struktur ini, admin dapat dengan mudah menambah, memperbarui, atau menghapus barang, sementara petugas hanya dapat melihat daftar barang tanpa mengeditnya.

Sementara itu, tabel users digunakan untuk menyimpan informasi pengguna yang memiliki akses ke sistem gudang. Setiap pengguna memiliki ID unik, username yang harus bersifat unik, serta password yang sebaiknya dienkripsi untuk keamanan. Selain itu, terdapat kolom role yang menentukan hak akses pengguna, yaitu admin atau petugas. Admin dan petugas/users memiliki wewenang penuh untuk mengelola barang dalam gudang.

Tampilan Aplikasi

📌 LoginView – Halaman login dengan tampilan biru putih.
📌 RegisterView – Halaman registrasi untuk pengguna baru.
📌 DashboardView – Halaman utama serta menampilkan peraturan gudang .
📌 tabelbarang – Menampilkan tabel barang serta fitur tambah dan hapus barang.
📌 logout – keluar dari aplikasi.

Demo Proyek

Github: Github

Youtube: Youtube
