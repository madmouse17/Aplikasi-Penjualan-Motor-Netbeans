-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 15, 2019 at 01:30 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 5.6.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbjualmotor`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbjual`
--

CREATE TABLE `tbjual` (
  `no_fak` char(20) NOT NULL,
  `tanggal` varchar(25) NOT NULL,
  `nik` bigint(20) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `alamat` varchar(25) NOT NULL,
  `hp` bigint(30) NOT NULL,
  `id_motor` varchar(20) NOT NULL,
  `type` varchar(40) NOT NULL,
  `merk` varchar(25) NOT NULL,
  `warna` varchar(20) NOT NULL,
  `harga` bigint(30) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `total` bigint(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbjual`
--

INSERT INTO `tbjual` (`no_fak`, `tanggal`, `nik`, `nama`, `alamat`, `hp`, `id_motor`, `type`, `merk`, `warna`, `harga`, `jumlah`, `total`) VALUES
('FA-01', '15/07/2019', 45645646, 'Alan', 'Solo', 8877655665, 'MTR001', 'Beat', 'Honda', 'Hitam', 22000, 4, 88000),
('FA-02', '15/07/2019', 8789, 'gio', 'solo', 857574, 'MTR001', 'Beat', 'Honda', 'Hitam', 22000, 5, 110000);

-- --------------------------------------------------------

--
-- Table structure for table `tblogin`
--

CREATE TABLE `tblogin` (
  `username` varchar(20) NOT NULL,
  `password` varchar(225) NOT NULL,
  `hak_akses` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblogin`
--

INSERT INTO `tblogin` (`username`, `password`, `hak_akses`) VALUES
('admin', '21232f297a57a5a743894a0e4a801fc3', 'admin'),
('user', 'ee11cbb19052e40b07aac0ca060c23ee', 'user');

-- --------------------------------------------------------

--
-- Table structure for table `tbstock`
--

CREATE TABLE `tbstock` (
  `id_motor` char(20) NOT NULL,
  `merk` varchar(25) NOT NULL,
  `type` varchar(20) NOT NULL,
  `warna` varchar(10) NOT NULL,
  `harga` bigint(25) NOT NULL,
  `jumlah` int(30) NOT NULL,
  `id_supplier` varchar(20) NOT NULL,
  `nama` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbstock`
--

INSERT INTO `tbstock` (`id_motor`, `merk`, `type`, `warna`, `harga`, `jumlah`, `id_supplier`, `nama`) VALUES
('MTR001', 'Honda', 'Beat', 'Hitam', 22000, 10, 'SUP01', 'adira'),
('MTR002', 'Honda', 'scopy', 'putih', 2500, 12, 'SUP02', 'Astra');

-- --------------------------------------------------------

--
-- Table structure for table `tbsupplier`
--

CREATE TABLE `tbsupplier` (
  `id_supplier` char(20) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `alamat` varchar(20) NOT NULL,
  `hp` bigint(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbsupplier`
--

INSERT INTO `tbsupplier` (`id_supplier`, `nama`, `alamat`, `hp`) VALUES
('SUP01', 'adira', 'solo', 85746383648),
('SUP02', 'Astra', 'Jakarta', 84756473432),
('SUP03', 'toyota', 'solo', 8575673);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbjual`
--
ALTER TABLE `tbjual`
  ADD PRIMARY KEY (`no_fak`);

--
-- Indexes for table `tblogin`
--
ALTER TABLE `tblogin`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `tbstock`
--
ALTER TABLE `tbstock`
  ADD PRIMARY KEY (`id_motor`);

--
-- Indexes for table `tbsupplier`
--
ALTER TABLE `tbsupplier`
  ADD PRIMARY KEY (`id_supplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
