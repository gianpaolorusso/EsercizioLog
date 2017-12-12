-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2017 at 05:41 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `esercizio`
--

-- --------------------------------------------------------

--
-- Table structure for table `appartenenza`
--

CREATE TABLE `appartenenza` (
  `username` varchar(25) DEFAULT NULL,
  `idcomunity` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appartenenza`
--

INSERT INTO `appartenenza` (`username`, `idcomunity`) VALUES
('polli', '1'),
('polli', '2'),
('fra', '3'),
('polli', '4');

-- --------------------------------------------------------

--
-- Table structure for table `commenti_post`
--

CREATE TABLE `commenti_post` (
  `nome_user` varchar(20) NOT NULL,
  `giorno` date NOT NULL,
  `id_post` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `testo` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commenti_post`
--

INSERT INTO `commenti_post` (`nome_user`, `giorno`, `id_post`, `id`, `testo`) VALUES
('polli', '2017-12-11', 24, 3, 'commento 1'),
('polli', '2017-12-11', 24, 4, 'jun'),
('polli', '2017-12-11', 24, 5, 'commento 2'),
('polli', '2017-12-11', 24, 6, 'commento 23'),
('polli', '2017-12-11', 24, 7, 'commento3'),
('polli', '2017-12-11', 24, 8, 'commento3'),
('polli', '2017-12-11', 25, 9, 'mdo'),
('polli', '2017-12-12', 26, 10, 'kdkdnd'),
('polli', '2017-12-12', 27, 11, 'rdrff'),
('polli', '2017-12-12', 29, 12, 'knkk'),
('polli', '2017-12-12', 38, 13, 'kdkdo'),
('polli', '2017-12-12', 24, 14, 'rcyijn'),
('polli', '2017-12-12', 24, 15, 'commentio notifiche'),
('polli', '2017-12-12', 24, 16, 'huh'),
('polli', '2017-12-12', 24, 17, 'usbd'),
('polli', '2017-12-12', 24, 18, 'jjnj'),
('polli', '2017-12-12', 24, 19, 'jsisj'),
('polli', '2017-12-12', 24, 20, 'jsidkd'),
('polli', '2017-12-12', 24, 21, 'hib'),
('polli', '2017-12-12', 24, 22, 'jdidnd'),
('polli', '2017-12-12', 24, 23, 'efvty'),
('polli', '2017-12-12', 24, 24, 'kijhu'),
('polli', '2017-12-12', 24, 25, 'kcofm'),
('polli', '2017-12-12', 24, 26, 'jinhhh'),
('polli', '2017-12-12', 24, 31, 'nddnd'),
('polli', '2017-12-12', 24, 32, 'mfkdkr'),
('polli', '2017-12-12', 24, 33, 'frjxdrcdfbt'),
('polli', '2017-12-12', 24, 34, 'jdidn'),
('polli', '2017-12-12', 24, 35, 'isiwiwut'),
('polli', '2017-12-12', 24, 36, 'krodi'),
('polli', '2017-12-12', 24, 37, 'huh'),
('polli', '2017-12-12', 24, 39, 'fgbjny'),
('polli', '2017-12-12', 24, 40, 'ndknf'),
('polli', '2017-12-12', 24, 41, 'bgn'),
('polli', '2017-12-12', 24, 42, 'bsjsbd'),
('polli', '2017-12-12', 24, 43, 'ndjdn'),
('polli', '2017-12-12', 24, 44, 'jdrj'),
('polli', '2017-12-12', 24, 45, 'nzijdjd'),
('polli', '2017-12-12', 24, 46, 'jdidn'),
('polli', '2017-12-12', 24, 47, 'ftg'),
('polli', '2017-12-12', 24, 48, 'jfif'),
('polli', '2017-12-12', 24, 49, 'bzhdbd'),
('polli', '2017-12-12', 24, 50, 'njny'),
('polli', '2017-12-12', 24, 51, 'jfifu'),
('polli', '2017-12-12', 24, 52, 'vycfd'),
('polli', '2017-12-12', 24, 53, 'vgn'),
('polli', '2017-12-12', 24, 54, 'gtc'),
('polli', '2017-12-12', 24, 55, 'jun'),
('polli', '2017-12-12', 24, 56, 'bdjd'),
('polli', '2017-12-12', 24, 57, 'cjdi'),
('polli', '2017-12-12', 24, 58, 'nxkxm'),
('polli', '2017-12-12', 24, 59, 'bgg'),
('polli', '2017-12-12', 24, 60, 'vgbgghh'),
('polli', '2017-12-12', 24, 61, 'nfkfj');

-- --------------------------------------------------------

--
-- Table structure for table `comunity`
--

CREATE TABLE `comunity` (
  `id` varchar(25) NOT NULL,
  `nome` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comunity`
--

INSERT INTO `comunity` (`id`, `nome`) VALUES
('1', 'sport'),
('2', 'cinema'),
('3', 'sfsf'),
('4', 'java');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `id` int(25) NOT NULL,
  `nome` varchar(25) DEFAULT NULL,
  `giorno` date DEFAULT NULL,
  `titolo` varchar(25) DEFAULT NULL,
  `id_comunity` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `nome`, `giorno`, `titolo`, `id_comunity`) VALUES
(24, 'titolo1', '2017-11-06', 'post1', '1'),
(25, 'titolo3', '2017-12-06', 'post2', '1'),
(26, 'kdkd', '2017-12-06', 'post3', '1'),
(27, 'testo prova ', '2017-12-06', 'post4', '1'),
(28, 'prova5', '2017-12-06', 'post5', '1'),
(29, ' pioggia', '2017-12-11', 'meteo', '1'),
(30, 'neve', '2017-12-11', 'meteo2', '1'),
(31, 'neve', '2017-12-11', 'meteo3', '1'),
(32, 'huiooppp', '2017-12-11', 'ju', '1'),
(33, 'mclxmfm', '2017-12-11', 'pdmdldll', '1'),
(34, 'pppp', '2017-12-11', 'pppp', '1'),
(35, 'jrodke', '2017-12-11', 'kzkzl', '1'),
(36, 'hik', '2017-12-11', '.jl', '1'),
(37, 'kok\n', '2017-12-11', 'joo', '1'),
(38, 'njk', '2017-12-11', 'iop', '1'),
(39, 'mcld5', '2017-12-11', 'post ultimo', '1'),
(40, 'post', '2017-12-12', 'post pubblicato', '1'),
(41, 'kcci', '2017-12-12', 'kdi', '1'),
(42, 'jdjd', '2017-12-12', 'kdjd', '1'),
(43, 'byn', '2017-12-12', 'vyj', '1'),
(44, 'vub', '2017-12-12', 'nkn', '2'),
(45, 'vin', '2017-12-12', 'bkb', '2'),
(46, 'byn', '2017-12-12', 'vyj', '1'),
(47, 'jfuf', '2017-12-12', 'jdjd', '4'),
(48, 'hin', '2017-12-12', 'nl', '1');

-- --------------------------------------------------------

--
-- Table structure for table `registration_device`
--

CREATE TABLE `registration_device` (
  `token` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registration_device`
--

INSERT INTO `registration_device` (`token`) VALUES
(''),
(''),
(''),
('dn5tNdzA9n0:APA91bHmUk5MJpHWNZiZhvRR40jGc8G6u9CfqQQ_uvo-pymVuazG4qr3gMBQfY5NAz1sLAEVSgrJDA4JrSMQHtMAZTeKCGdFh9sxsE3zOr1TPb4h-KaXjQbrf_goE2pEarvLvrzNlUTz'),
('fLbju8-PrS8:APA91bG4hlHi17K73CoA1rsyCLMWfsim4am91MxpgncKLnoNNoEc35FMnp3Ct_NRHLcnudv6dyomd0aZQUx7siQWpjPkp78k-X_C-87KFdJR9SMe_X0wFhIYr82mD7_lUoBe9KB8FSYv');

-- --------------------------------------------------------

--
-- Table structure for table `utenti`
--

CREATE TABLE `utenti` (
  `nome` text,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `utenti`
--

INSERT INTO `utenti` (`nome`, `username`, `password`) VALUES
('fra', 'fra', 'fra'),
('francesco', 'polli', 'ciao');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appartenenza`
--
ALTER TABLE `appartenenza`
  ADD KEY `appartenenza_ibfk_1` (`idcomunity`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `commenti_post`
--
ALTER TABLE `commenti_post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_post` (`id_post`),
  ADD KEY `nome_user` (`nome_user`);

--
-- Indexes for table `comunity`
--
ALTER TABLE `comunity`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_comunity` (`id_comunity`);

--
-- Indexes for table `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `commenti_post`
--
ALTER TABLE `commenti_post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(25) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appartenenza`
--
ALTER TABLE `appartenenza`
  ADD CONSTRAINT `appartenenza_ibfk_1` FOREIGN KEY (`idcomunity`) REFERENCES `comunity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `appartenenza_ibfk_2` FOREIGN KEY (`username`) REFERENCES `utenti` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `commenti_post`
--
ALTER TABLE `commenti_post`
  ADD CONSTRAINT `commenti_post_ibfk_1` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `commenti_post_ibfk_2` FOREIGN KEY (`nome_user`) REFERENCES `utenti` (`username`);

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`id_comunity`) REFERENCES `comunity` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
