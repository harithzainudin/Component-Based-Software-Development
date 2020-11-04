-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 18, 2020 at 12:11 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project_wa`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `name`, `description`, `user_id`) VALUES
(3, 'Maybank', 'Starter Account', 8),
(4, 'Bank Islam', 'JPA Acc', 8);

-- --------------------------------------------------------

--
-- Table structure for table `incomexpense`
--

CREATE TABLE `incomexpense` (
  `id` int(11) NOT NULL,
  `type` varchar(250) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `bank_id` int(11) NOT NULL,
  `category` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  `acc_id` int(11) DEFAULT NULL,
  `type` varchar(15) NOT NULL,
  `transdate` date NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id`, `amount`, `category`, `acc_id`, `type`, `transdate`, `user_id`) VALUES
(3, 300, 1, 3, 'expense', '2019-12-15', NULL),
(4, 300, 2, 4, 'expense', '2019-12-09', 8),
(5, 300, 5, 3, 'expense', '2019-12-11', 8),
(6, 300, 2, 4, 'income', '2019-12-12', 8),
(7, 300, 3, 4, 'income', '2019-12-18', 8),
(8, 300, 1, 3, 'expense', '2019-12-03', 8),
(9, 500, 1, 3, 'expense', '2019-12-17', 8),
(10, 1000, 3, 3, 'expense', '2019-12-05', 8),
(11, 1000, 7, 4, 'income', '2019-12-04', 8),
(12, 1100, 9, 4, 'income', '2019-12-02', 8),
(13, 1300, 8, 4, 'income', '2019-12-04', 8),
(14, 200, 7, 4, 'income', '2019-12-05', 8);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_num` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `phone_num`) VALUES
(8, 'www', '47bce5c74f589f4867dbd57e9ca9f808', 'shakir_1998@yahoo.com.my', '0133081510'),
(9, 'qqq', 'b2ca678b4c936f905fb82f2733f5297f', 'ikerepeks1998@gmail.com', '0133081510'),
(10, 'kerepek', '81dc9bdb52d04dc20036dbd8313ed055', 'shakir_1998@yahoo.com.my', '0133081510');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_accid` (`user_id`);

--
-- Indexes for table `incomexpense`
--
ALTER TABLE `incomexpense`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_bankid` (`bank_id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_bank` (`acc_id`),
  ADD KEY `fk_userid` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `incomexpense`
--
ALTER TABLE `incomexpense`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `fk_accid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `incomexpense`
--
ALTER TABLE `incomexpense`
  ADD CONSTRAINT `fk_bankid` FOREIGN KEY (`bank_id`) REFERENCES `account` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `fk_bank` FOREIGN KEY (`acc_id`) REFERENCES `account` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
