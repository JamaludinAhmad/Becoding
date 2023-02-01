-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 01, 2023 at 05:06 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kursusonline`
--
CREATE DATABASE IF NOT EXISTS `kursusonline` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `kursusonline`;

-- --------------------------------------------------------

--
-- Table structure for table `history_transaksi`
--

CREATE TABLE `history_transaksi` (
  `id` int(10) NOT NULL,
  `tgl_pembelian` date NOT NULL,
  `nama_akun` varchar(50) NOT NULL,
  `nama_kursus` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `history_transaksi`
--

INSERT INTO `history_transaksi` (`id`, `tgl_pembelian`, `nama_akun`, `nama_kursus`) VALUES
(1, '2023-01-23', 'fhr', 'KRS0001'),
(2, '2023-01-23', 'fhr', 'KRS0001'),
(3, '2023-01-23', 'fhr', 'KRS0002'),
(8, '2023-01-23', 'fhr', 'KRS0005'),
(10, '2023-01-23', 'fhr', 'KRS0013'),
(11, '2023-01-23', 'fhr', 'KRS0014'),
(12, '2023-01-23', 'fhr', 'KRS0009'),
(13, '2023-01-23', 'fhr', 'KRS0003'),
(16, '2023-01-23', 'fhr', 'KRS0011'),
(17, '2023-01-23', 'fhr', 'KRS0006'),
(18, '2023-01-23', 'fhr', 'KRS0001'),
(19, '2023-01-23', 'fhr', 'KRS0004'),
(20, '2023-01-23', 'fhr', 'KRS0008'),
(21, '2023-01-23', 'fhr', 'KRS0001'),
(22, '2023-01-23', 'fhr', 'KRS0014'),
(23, '2023-01-28', 'ahmad', 'KRS0001'),
(24, '2023-01-28', 'ahmad', 'KRS0002'),
(49, '2023-01-30', 'fhr', 'KRS0010'),
(51, '2023-01-30', 'fhr', 'KRS0009'),
(52, '2023-01-30', 'fhr', 'KRS0005'),
(53, '2023-01-30', 'ahmad', 'KRS0001'),
(54, '2023-01-30', 'ahmad', 'KRS0001'),
(55, '2023-01-30', 'AHMAD', 'KRS0010'),
(56, '2023-01-30', 'AHMAD', 'KRS0009'),
(57, '2023-01-30', 'ahmad', 'KRS0004'),
(58, '2023-01-30', 'ahmad', 'KRS0006'),
(59, '2023-01-30', 'fhr', 'KRS0004'),
(60, '2023-01-30', 'sumbul', 'KRS0009'),
(61, '2023-01-30', 'khd', 'KRS0010'),
(62, '2023-01-31', 'jml', 'KRS0010'),
(63, '2023-02-01', 'fhr', 'KRS0001'),
(64, '2023-02-01', 'fhr', 'KRS0002');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_akses`
--

CREATE TABLE `tbl_akses` (
  `id_akses` char(40) NOT NULL,
  `id_akun` char(13) DEFAULT NULL,
  `id_kursus` char(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_akses`
--

INSERT INTO `tbl_akses` (`id_akses`, `id_akun`, `id_kursus`) VALUES
('ahmadKRS0001', 'ahmad', 'KRS0001'),
('ahmadKRS0004', 'ahmad', 'KRS0004'),
('ahmadKRS0006', 'ahmad', 'KRS0006'),
('AHMADKRS0009', 'AHMAD', 'KRS0009'),
('fhrKRS0001', 'fhr', 'KRS0001'),
('fhrKRS0002', 'fhr', 'KRS0002'),
('fhrKRS0004', 'fhr', 'KRS0004'),
('fhrKRS0005', 'fhr', 'KRS0005'),
('fhrKRS0009', 'fhr', 'KRS0009'),
('fhrKRS0010', 'fhr', 'KRS0010'),
('jmlKRS0010', 'jml', 'KRS0010'),
('sumbulKRS0009', 'sumbul', 'KRS0009');

--
-- Triggers `tbl_akses`
--
DELIMITER $$
CREATE TRIGGER `history_transaksi` AFTER INSERT ON `tbl_akses` FOR EACH ROW BEGIN
	INSERT INTO history_transaksi (nama_akun, nama_kursus, tgl_pembelian) VALUES
    (NEW.id_akun, NEW.id_kursus, CURDATE());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `kurangkan_jumlah_kurs` AFTER DELETE ON `tbl_akses` FOR EACH ROW BEGIN
	UPDATE tbl_akun SET tbl_akun.jumlah_kurs = tbl_akun.jumlah_kurs - 1  WHERE tbl_akun.id_akun = OLD.id_akun;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `kurangkan_uang` AFTER INSERT ON `tbl_akses` FOR EACH ROW BEGIN
	UPDATE tbl_akun, tbl_kursus, tbl_kategori
    SET tbl_akun.uang = tbl_akun.uang - tbl_kategori.harga
    WHERE id_kursus = NEW.id_kursus AND tbl_akun.id_akun = NEW.id_akun AND tbl_kategori.id_kategori = tbl_kursus.kategori;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tambah_jumlah` AFTER INSERT ON `tbl_akses` FOR EACH ROW BEGIN
	UPDATE tbl_akun SET tbl_akun.jumlah_kurs = tbl_akun.jumlah_kurs + 1  WHERE tbl_akun.id_akun = NEW.id_akun;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_akun`
--

CREATE TABLE `tbl_akun` (
  `id_akun` char(13) NOT NULL,
  `nama` varchar(35) DEFAULT NULL,
  `pass` varchar(20) DEFAULT NULL,
  `jumlah_kurs` int(4) DEFAULT NULL,
  `uang` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_akun`
--

INSERT INTO `tbl_akun` (`id_akun`, `nama`, `pass`, `jumlah_kurs`, `uang`) VALUES
('ahmad', 'Jamaludin Ahmad Rifai', '123', 4, 550000),
('fhr', 'FIHRI', '123', 6, 4150000),
('jml', 'jamal', '123', 1, 450000),
('sumbul', 'Muhammad Sumbul', '123', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_kategori`
--

CREATE TABLE `tbl_kategori` (
  `id_kategori` int(3) NOT NULL,
  `harga` int(6) DEFAULT NULL,
  `keterangan` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_kategori`
--

INSERT INTO `tbl_kategori` (`id_kategori`, `harga`, `keterangan`) VALUES
(1, 250000, 'BEGINNER'),
(2, 350000, 'ADVANCED'),
(3, 550000, 'INTERMEDIATE');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_kursus`
--

CREATE TABLE `tbl_kursus` (
  `id_kursus` char(9) NOT NULL,
  `nama_kursus` varchar(35) DEFAULT NULL,
  `id_mentor` char(6) DEFAULT NULL,
  `kategori` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_kursus`
--

INSERT INTO `tbl_kursus` (`id_kursus`, `nama_kursus`, `id_mentor`, `kategori`) VALUES
('KRS0001', 'UNITY3D', 'MNT001', 1),
('KRS0002', 'UNITY3D', 'MNT001', 2),
('KRS0003', 'GODOTENGINE', 'MNT001', 1),
('KRS0004', 'GODOTENGINE', 'MNT001', 2),
('KRS0005', 'Webdev', 'MNT002', 2),
('KRS0006', 'Webdev', 'MNT002', 1),
('KRS0007', 'WEBDS', 'MNT002', 1),
('KRS0008', 'WEBDS', 'MNT002', 2),
('KRS0009', 'FSDEV', 'MNT003', 3),
('KRS0010', 'FSDEV', 'MNT004', 3),
('KRS0011', 'PRAMM', 'MNT005', 1),
('KRS0012', 'PRAMM', 'MNT004', 2),
('KRS0013', 'FREND', 'MNT003', 1),
('KRS0014', 'BACKND', 'MNT004', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_mentor`
--

CREATE TABLE `tbl_mentor` (
  `id_mentor` char(6) NOT NULL,
  `nama_mentor` varchar(30) DEFAULT NULL,
  `telepon_mentor` varchar(15) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_mentor`
--

INSERT INTO `tbl_mentor` (`id_mentor`, `nama_mentor`, `telepon_mentor`, `alamat`) VALUES
('MNT001', 'AGATE', '081234122', 'Bandung'),
('MNT002', 'RISTEK', '08534621463', 'Depok'),
('MNT003', 'JACOB', '083463423', 'Ohio'),
('MNT004', 'TELKOM', '083454367245', 'Bandung'),
('MNT005', 'TRAVELOKA', '0812345362', 'Tangerang');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `history_transaksi`
--
ALTER TABLE `history_transaksi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_akses`
--
ALTER TABLE `tbl_akses`
  ADD PRIMARY KEY (`id_akses`),
  ADD KEY `id_akun` (`id_akun`),
  ADD KEY `id_kursus` (`id_kursus`);

--
-- Indexes for table `tbl_akun`
--
ALTER TABLE `tbl_akun`
  ADD PRIMARY KEY (`id_akun`);

--
-- Indexes for table `tbl_kategori`
--
ALTER TABLE `tbl_kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indexes for table `tbl_kursus`
--
ALTER TABLE `tbl_kursus`
  ADD PRIMARY KEY (`id_kursus`),
  ADD KEY `id_mentor` (`id_mentor`),
  ADD KEY `kategori` (`kategori`);

--
-- Indexes for table `tbl_mentor`
--
ALTER TABLE `tbl_mentor`
  ADD PRIMARY KEY (`id_mentor`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `history_transaksi`
--
ALTER TABLE `history_transaksi`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_akses`
--
ALTER TABLE `tbl_akses`
  ADD CONSTRAINT `tbl_akses_ibfk_1` FOREIGN KEY (`id_akun`) REFERENCES `tbl_akun` (`id_akun`),
  ADD CONSTRAINT `tbl_akses_ibfk_2` FOREIGN KEY (`id_kursus`) REFERENCES `tbl_kursus` (`id_kursus`);

--
-- Constraints for table `tbl_kursus`
--
ALTER TABLE `tbl_kursus`
  ADD CONSTRAINT `tbl_kursus_ibfk_1` FOREIGN KEY (`id_mentor`) REFERENCES `tbl_mentor` (`id_mentor`),
  ADD CONSTRAINT `tbl_kursus_ibfk_2` FOREIGN KEY (`kategori`) REFERENCES `tbl_kategori` (`id_kategori`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
