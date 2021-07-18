-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 03 Jul 2021 pada 06.48
-- Versi server: 10.4.11-MariaDB
-- Versi PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kost`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id` int(200) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Alamat` varchar(100) NOT NULL,
  `No_telp` varchar(100) NOT NULL,
  `Jenis_kelamin` varchar(100) NOT NULL,
  `Username` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id`, `Nama`, `Alamat`, `No_telp`, `Jenis_kelamin`, `Username`, `Password`, `Role_id`) VALUES
(5, 'KOMANG SATRYA', 'JL TAMAN GIRI, PERUM BINA MUMBUL PERMAI, GANG MELATI Q2', '085858767113', 'Pria', 'admin', '$2y$10$Wg8P/YEE/.W85b3LUQ9a3.W1rukteEeOmhKbw/oy0Ulpkv4VxSqPu', 1),
(13, 'I GD AGUS RYAN KURNIANTARA', 'JL TAMAN GIRI, PERUM BINA MUMBUL PERMAI, GANG MELATI Q2', '085858767113', 'Pria', 'ryankur', '$2y$10$CoZPtCXv4fxfgsX4XIhskOxAXnVtZ8YwM8BqG8HoJYNHakUGo2aTG', 2),
(15, 'Dodik Asmara', 'JL TAMAN GIRI, PERUM BINA MUMBUL PERMAI, GANG MELATI Q2', '085858767113', 'Pria', 'dodikap', '$2y$10$ltts6zcvEFwLO9OFw48NReM2.3hW/UxIIxYyRzZZYiktjqpO8u.BC', 2),
(16, 'admin saya', 'pondok labu', '089876543213', 'pria', 'admin12', 'admin', 1),
(17, 'adalah', 'jember', '089765432123', 'wanita', 'admin11', '123', 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin_access_menu`
--

CREATE TABLE `admin_access_menu` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin_access_menu`
--

INSERT INTO `admin_access_menu` (`id`, `role_id`, `menu_id`) VALUES
(1, 1, 1),
(2, 2, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin_menu`
--

CREATE TABLE `admin_menu` (
  `id` int(11) NOT NULL,
  `menu` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin_menu`
--

INSERT INTO `admin_menu` (`id`, `menu`) VALUES
(1, 'superadmin'),
(2, 'adminkos');

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin_role`
--

CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `Keterangan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin_role`
--

INSERT INTO `admin_role` (`id`, `role_id`, `Keterangan`) VALUES
(1, 1, 'Super Admin'),
(2, 2, 'Admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin_sub_menu`
--

CREATE TABLE `admin_sub_menu` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `tittle` varchar(128) NOT NULL,
  `url` varchar(128) NOT NULL,
  `icon` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin_sub_menu`
--

INSERT INTO `admin_sub_menu` (`id`, `menu_id`, `tittle`, `url`, `icon`) VALUES
(1, 1, 'Dashboard', 'superadmin', 'fas fa-fw fa-tachometer-alt'),
(2, 1, 'Data Pemilik Kos', 'superadmin/datapemilik', 'fas fa-fw fa-table'),
(3, 1, 'Data Penyewa Kos', 'superadmin/datapenyewa', 'fas fa-fw fa-table'),
(4, 1, 'Data Kos Belum Terverivikasi', 'superadmin/datakos', 'fas fa-fw fa-table'),
(5, 1, 'Data Kos Terverivikasi ', 'superadmin/datakosbadung', 'fas fa-fw fa-table'),
(8, 2, 'Dashboard', 'adminkos', 'fas fa-fw fa-tachometer-alt'),
(9, 2, 'Data Kos', 'adminkos/datakos', 'fas fa-fw fa-table'),
(13, 2, 'Data Kamar Kos', 'adminkos/datakamarkos', 'fas fa-fw fa-table'),
(14, 1, 'Data Kamar Kos', 'superadmin/datakamarkos', 'fas fa-fw fa-table'),
(15, 2, 'Data Transaksi ', 'adminkos/datatransaksi', 'fas fa-fw fa-table'),
(16, 1, 'Data Transaksi', 'superadmin/datatransaksi', 'fas fa-fw fa-table'),
(17, 1, 'Riwayat Transaksi', 'superadmin/riwayattransaksi', 'fas fa-fw fa-table');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kamarkos`
--

CREATE TABLE `kamarkos` (
  `id` int(11) NOT NULL,
  `Namakamar` varchar(128) NOT NULL,
  `id_kos` int(11) NOT NULL,
  `Namakos` varchar(128) NOT NULL,
  `id_pemilik` int(250) NOT NULL,
  `Namapemilik` varchar(128) NOT NULL,
  `Foto` varchar(128) NOT NULL,
  `Fasilitaskamar` varchar(128) NOT NULL,
  `Jumlahkamar` varchar(100) NOT NULL,
  `Hargakamar` varchar(200) NOT NULL,
  `Aktif` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `kamarkos`
--

INSERT INTO `kamarkos` (`id`, `Namakamar`, `id_kos`, `Namakos`, `id_pemilik`, `Namapemilik`, `Foto`, `Fasilitaskamar`, `Jumlahkamar`, `Hargakamar`, `Aktif`) VALUES
(31, 'kamar A1', 65, 'mawar', 17, 'adalah', '1625207017-51-IMG_5656x.jpg', 'AC, Jeding', '2', '400000', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kos`
--

CREATE TABLE `kos` (
  `id` int(200) NOT NULL,
  `Namakos` varchar(100) NOT NULL,
  `Namapemilik` varchar(100) NOT NULL,
  `Latitude` varchar(100) NOT NULL,
  `Longtitude` varchar(100) NOT NULL,
  `foto1` varchar(100) NOT NULL,
  `foto2` varchar(100) NOT NULL,
  `foto3` varchar(100) NOT NULL,
  `foto4` varchar(100) NOT NULL,
  `Deskripsi` varchar(100) NOT NULL,
  `Kecamatan` varchar(100) NOT NULL,
  `Status` varchar(100) NOT NULL,
  `Aktif` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `kos`
--

INSERT INTO `kos` (`id`, `Namakos`, `Namapemilik`, `Latitude`, `Longtitude`, `foto1`, `foto2`, `foto3`, `foto4`, `Deskripsi`, `Kecamatan`, `Status`, `Aktif`) VALUES
(41, 'test', 'I GD AGUS RYAN KURNIANTARA ', '2323132321', '1123321321', '2.jpeg', '3.jpeg', '4.jpg', '2.jpeg', 'rwrwrewr', 'Kuta Utara', 'Sukses', 0),
(64, 'Kos Manik Sari', 'I GD AGUS RYAN KURNIANTARA', '2323132321', '1123321321', 'rumahkos1.jpg', 'rumahkos.jpg', 'Desain Kos kosan.jpg', 'bisnis-kos-kosan.jpg', 'dsfdsfdsfdsfd', 'Kuta', 'Sukses', 0),
(65, 'mawar', 'adalah', '-8.568189842576915', '115.19475993652345', '1625205232-89-IMG_20190602_094254.jpg', '1625205232-54-kamen.jpg', '1625205232-56-kocheng.jpg', '1625205232-21-wp3746439-jung-chae-yeon-wallpapers.jpg', 'ada', 'Kuta Selatan', 'Sukses', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(15) NOT NULL,
  `id_namakos` int(100) NOT NULL,
  `Namakos` varchar(100) NOT NULL,
  `id_namapemilik` int(100) NOT NULL,
  `Namapemilik` varchar(100) NOT NULL,
  `id_namauser` int(100) NOT NULL,
  `Namauser` varchar(100) NOT NULL,
  `id_namakamar` int(100) NOT NULL,
  `Namakamar` varchar(200) NOT NULL,
  `Buktipembayaran` varchar(100) NOT NULL,
  `Tglmulai` varchar(100) NOT NULL,
  `Lamasewa` varchar(100) NOT NULL,
  `Jumlahkamar` varchar(100) NOT NULL,
  `Totalharga` varchar(100) NOT NULL,
  `Status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id`, `id_namakos`, `Namakos`, `id_namapemilik`, `Namapemilik`, `id_namauser`, `Namauser`, `id_namakamar`, `Namakamar`, `Buktipembayaran`, `Tglmulai`, `Lamasewa`, `Jumlahkamar`, `Totalharga`, `Status`) VALUES
(1, 0, 'Kos Bulakan Sari', 0, 'I GD AGUS RYAN KURNIANTARA', 0, 'ryank', 0, 'Kamar Cantik', 'bri.jpg\r\n', '1/12/2020', '2', '1', '20000000', 'Sukses');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int(200) NOT NULL,
  `Nama` varchar(100) NOT NULL,
  `Alamat` varchar(100) NOT NULL,
  `No_telp` varchar(100) NOT NULL,
  `Jenis_kelamin` varchar(100) NOT NULL,
  `Username` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `Nama`, `Alamat`, `No_telp`, `Jenis_kelamin`, `Username`, `Password`) VALUES
(1, 'I Gd Agus ryan Kurniantara', 'Taman Giri Perum Bina Mumbul ', '085858767113', 'Wanita', 'ryank', '123'),
(5, 'Dodik', 'Klevek', '089998877', 'Wanita', 'dodik', '123');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `admin_access_menu`
--
ALTER TABLE `admin_access_menu`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `admin_menu`
--
ALTER TABLE `admin_menu`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `admin_role`
--
ALTER TABLE `admin_role`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `admin_sub_menu`
--
ALTER TABLE `admin_sub_menu`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `kamarkos`
--
ALTER TABLE `kamarkos`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `kos`
--
ALTER TABLE `kos`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT untuk tabel `admin_access_menu`
--
ALTER TABLE `admin_access_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `admin_menu`
--
ALTER TABLE `admin_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `admin_role`
--
ALTER TABLE `admin_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `admin_sub_menu`
--
ALTER TABLE `admin_sub_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT untuk tabel `kamarkos`
--
ALTER TABLE `kamarkos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT untuk tabel `kos`
--
ALTER TABLE `kos`
  MODIFY `id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
