Perencanaan: Berikut merupakan perencanaan dan gambaran dari sistem yang akan di buat sebelum ke codingannya

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
1. Menambah Data Barang: Pengguna dapat menambahkan data barang baru ke dalam sistem, seperti kode barang, nama barang, stok, harga beli, dan harga jual.  
2. Mengedit Data Barang: Pengguna dapat memperbarui informasi barang, seperti jumlah stok atau harga.  
3. Menghapus Data Barang: Pengguna dapat menghapus data barang yang sudah tidak diperlukan.  
4. Menampilkan Data Barang: Sistem menampilkan seluruh data barang yang tersimpan dalam database dalam bentuk tabel.  
Aplikasi ini terintegrasi dengan database FreeDB Tech menggunakan koneksi JDBC untuk memastikan data tersimpan dan dapat diakses secara real-time. Dengan tampilan antarmuka yang sederhana, sistem ini mudah digunakan oleh siapa saja, termasuk pengguna yang tidak memiliki latar belakang teknis.  
Penjelasan 4 Pilar OOP dalam Studi Kasus

1. Inheritance
Pewarisan diterapkan dengan membuat kelas induk Barang yang berisi atribut umum seperti kode barang, nama barang, stok, harga beli, dan harga jual. Jika dibutuhkan kategori khusus seperti barang elektronik atau barang makanan, kelas turunan dapat dibuat untuk menambahkan atribut atau metode spesifik yang sesuai.  
2. Encapsulation
Untuk menjaga integritas data, semua atribut pada kelas Barang dibuat private. Akses dan modifikasi data hanya dapat dilakukan melalui metode getter dan setter. Misalnya, jumlah stok hanya dapat diubah melalui metode yang memvalidasi input untuk menghindari nilai negatif.  
3. Polymorphism
Polimorfisme diterapkan melalui metode override, seperti metode hitungHarga atau tampilkanDetail. Jika ada kategori barang khusus, metode ini dapat disesuaikan untuk memberikan hasil yang berbeda berdasarkan jenis barangnya.  
4. Abstract
   Abstract diterapkan dengan mendesain operasi dasar yang harus dimiliki setiap kelas turunan, seperti metode simpan atau hapus. Metode ini dideklarasikan di kelas abstrak dan diimplementasikan oleh kelas-kelas yang membutuhkan.  

Struktur Tabel Aplikasi

Tabel barang digunakan untuk menyimpan semua informasi terkait barang yang dikelola dalam gudang. Kolom kode_barang digunakan sebagai kunci utama untuk mengidentifikasi barang secara unik. Kolom nama_barang menyimpan nama barang, sedangkan kolom stok mencatat jumlah barang yang tersedia. harga_beli digunakan untuk mencatat harga barang dari pemasok, sedangkan harga_jual digunakan untuk mencatat harga yang dijual kepada konsumen. Struktur ini dirancang sederhana namun mencakup semua kebutuhan operasional gudang.  

Tampilan Aplikasi
Antarmuka aplikasi dirancang untuk kemudahan penggunaan. Pengguna dapat mengisi form untuk menambahkan data barang baru atau mengedit data barang yang sudah ada. Data yang tersimpan ditampilkan dalam tabel yang mudah dibaca. Tombol-tombol seperti "Simpan", "Tambah", dan "Hapus" memberikan kontrol penuh kepada pengguna untuk memanipulasi data barang. 

Demo Proyek
Github: Github

Youtube: Youtube
