-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 06, 2020 at 01:38 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cekgizianak`
--

-- --------------------------------------------------------

--
-- Table structure for table `hasil`
--

CREATE TABLE `hasil` (
  `nama_anak` varchar(255) NOT NULL,
  `jenis_kelamin_anak` varchar(255) NOT NULL,
  `berat_anak` int(5) NOT NULL,
  `tinggi_anak` int(5) NOT NULL,
  `umur_anak` int(5) NOT NULL,
  `hasil_cek_anak` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hasil`
--

INSERT INTO `hasil` (`nama_anak`, `jenis_kelamin_anak`, `berat_anak`, `tinggi_anak`, `umur_anak`, `hasil_cek_anak`) VALUES
('Flo', 'Wanita', 9, 52, 7, 'Ini adalah test hasil 2'),
('Gunawan', 'Pria', 8, 48, 5, 'Ini adalah aplikasi cek gizi anak'),
('Test', 'Perempuan', 8, 56, 12, 'Berat Badan Anak Normal. Tinggi Badan Anak Diatas Normal. Anak Normal. ');

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `nama_ortu` varchar(255) NOT NULL,
  `alamat_ortu` varchar(255) NOT NULL,
  `no_hp_ortu` varchar(13) NOT NULL,
  `nama_anak` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`nama_ortu`, `alamat_ortu`, `no_hp_ortu`, `nama_anak`) VALUES
('Ayah', 'Jl. Rumah', '081313131313', 'Flo'),
('Ibu', 'Jl. Rumah', '081244078282', 'Gunawan'),
('Test', 'Rumah', '081244444444', 'Test');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hasil`
--
ALTER TABLE `hasil`
  ADD PRIMARY KEY (`nama_anak`),
  ADD KEY `FOREIGN` (`nama_anak`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`nama_anak`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hasil`
--
ALTER TABLE `hasil`
  ADD CONSTRAINT `hasil_ibfk_1` FOREIGN KEY (`nama_anak`) REFERENCES `pengguna` (`nama_anak`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
