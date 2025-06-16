-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2025 at 12:17 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toko_obat`
--

-- --------------------------------------------------------

--
-- Table structure for table `detail_penjualan`
--

CREATE TABLE `detail_penjualan` (
  `id_detail` int(11) NOT NULL,
  `id_penjualan` int(11) DEFAULT NULL,
  `id_obat` int(11) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_penjualan`
--

INSERT INTO `detail_penjualan` (`id_detail`, `id_penjualan`, `id_obat`, `jumlah`, `subtotal`) VALUES
(1, 6, 2, 2, 8000),
(2, 8, 4, 1, 3000),
(3, 9, 2, 1, 4000),
(4, 10, 2, 6, 24000),
(5, 11, 2, 1, 4000),
(6, 12, 2, 40, 160000),
(7, 13, 2, 40, 160000),
(8, 14, 2, 12, 42000),
(9, 14, 4, 2, 6000),
(10, 15, 2, 12, 42000),
(11, 16, 17, 11, 49500),
(12, 16, 11, 20, 44000);

-- --------------------------------------------------------

--
-- Table structure for table `obat`
--

CREATE TABLE `obat` (
  `id_obat` int(11) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `jenis` varchar(50) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `stok` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `obat`
--

INSERT INTO `obat` (`id_obat`, `nama`, `jenis`, `harga`, `stok`) VALUES
(2, 'Amoxicillin 500mg', 'Kapsul', 3500, 75),
(4, 'Antasida DOEN', 'Tablet Kunyah', 3000, 21),
(7, 'paracetamol', 'tablet', 2000, 100),
(8, 'Paracetamol 500mg', 'Tablet', 2000, 100),
(9, 'Amoxicillin 500mg', 'Kapsul', 3500, 80),
(10, 'Vitamin C 500mg', 'Tablet', 1500, 120),
(11, 'Antasida DOEN', 'Tablet Kunyah', 2200, 70),
(12, 'CTM (Chlorpheniramine)', 'Tablet', 1000, 75),
(13, 'Ibuprofen 400mg', 'Tablet', 3000, 60),
(14, 'Salep Kulit 88', 'Salep', 6000, 50),
(15, 'Betadine 30ml', 'Cairan Antiseptik', 8500, 40),
(16, 'Minyak Kayu Putih 60ml', 'Minyak Gosok', 10000, 80),
(17, 'Diapet', 'Kapsul Herbal', 4500, 59);

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int(11) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `kontak` varchar(30) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama`, `kontak`, `alamat`) VALUES
(1, 'fahdy', '123241', 'sukabumi'),
(2, 'dada', '123234232', 'cimahi'),
(3, 'sasa', '122354', 'sese'),
(4, 'fafa', '342132', 'vavs'),
(5, 'sasa', 'qweqeq', 'sadsa'),
(6, 'fufu', '123', 'fafa'),
(7, 'sat', 'we', '123'),
(8, 'cece', '45241123', 'cibudi'),
(9, 'hella', '85453', '231'),
(10, 'sasa', 'saa', 'aaa'),
(11, 'gege', '1332dasda', 'dasdas'),
(12, 'dadai', '1314wqwe', 'sdasdad'),
(13, 'tatang', '1434422312', 'sdadscas'),
(14, 'fefe', 'dada', 'sad12e1'),
(15, 'tobias', '08766351621', 'cimahi'),
(16, 'tatang', '098517217', 'cibolang'),
(17, 'kiki', '0868761871', 'cimahi');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `id_penjualan` int(11) NOT NULL,
  `id_petugas` int(11) DEFAULT NULL,
  `id_pelanggan` int(11) DEFAULT NULL,
  `tanggal` datetime DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`id_penjualan`, `id_petugas`, `id_pelanggan`, `tanggal`, `total`) VALUES
(1, 1, 2, '2025-06-16 00:00:00', 0),
(2, 1, 3, '2025-06-16 00:00:00', 0),
(3, 1, 4, '2025-06-16 00:00:00', 0),
(4, 1, 5, '2025-06-16 00:00:00', 0),
(5, 1, 6, '2025-06-16 00:00:00', 0),
(6, 1, 7, '2025-06-16 00:00:00', 8000),
(7, 1, 8, '2025-06-16 00:00:00', 0),
(8, 1, 9, '2025-06-16 00:00:00', 3000),
(9, 1, 10, '2025-06-16 00:00:00', 4000),
(10, 1, 11, '2025-06-16 00:00:00', 24000),
(11, 1, 12, '2025-06-16 00:00:00', 4000),
(12, 1, 13, '2025-06-16 00:00:00', 160000),
(13, 1, 14, '2025-06-16 00:00:00', 160000),
(14, 1, 15, '2025-06-16 00:00:00', 48000),
(15, 1, 16, '2025-06-16 00:00:00', 42000),
(16, 1, 17, '2025-06-16 00:00:00', 93500);

-- --------------------------------------------------------

--
-- Table structure for table `petugas`
--

CREATE TABLE `petugas` (
  `id_petugas` int(11) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `petugas`
--

INSERT INTO `petugas` (`id_petugas`, `nama`, `username`, `password`) VALUES
(1, 'Fahdy Hasanugrah Lubis', 'Fahdy', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_penjualan` (`id_penjualan`),
  ADD KEY `id_obat` (`id_obat`);

--
-- Indexes for table `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`id_obat`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id_penjualan`),
  ADD KEY `id_petugas` (`id_petugas`),
  ADD KEY `id_pelanggan` (`id_pelanggan`);

--
-- Indexes for table `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `obat`
--
ALTER TABLE `obat`
  MODIFY `id_obat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `id_penjualan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `petugas`
--
ALTER TABLE `petugas`
  MODIFY `id_petugas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD CONSTRAINT `detail_penjualan_ibfk_1` FOREIGN KEY (`id_penjualan`) REFERENCES `penjualan` (`id_penjualan`),
  ADD CONSTRAINT `detail_penjualan_ibfk_2` FOREIGN KEY (`id_obat`) REFERENCES `obat` (`id_obat`);

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`id_petugas`) REFERENCES `petugas` (`id_petugas`),
  ADD CONSTRAINT `penjualan_ibfk_2` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
