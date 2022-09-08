-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Apr 14, 2022 at 06:42 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
CREATE TABLE IF NOT EXISTS `authors` (
  `authors_id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `photo` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'nophoto.webp',
  `biography` mediumtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `born` date DEFAULT NULL,
  `died` date DEFAULT NULL,
  PRIMARY KEY (`authors_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `authors`
--

INSERT INTO `authors` (`authors_id`, `full_name`, `photo`, `biography`, `born`, `died`) VALUES
(1, 'Dan Simmons', 'dansimmons.webp', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `authors_books`
--

DROP TABLE IF EXISTS `authors_books`;
CREATE TABLE IF NOT EXISTS `authors_books` (
  `authors_id` int(11) NOT NULL,
  `books_id` int(11) NOT NULL,
  PRIMARY KEY (`authors_id`,`books_id`),
  KEY `FK_ab_books` (`books_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `authors_books`
--

INSERT INTO `authors_books` (`authors_id`, `books_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
CREATE TABLE IF NOT EXISTS `books` (
  `books_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cover` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'nocover.webp',
  `synopsis` mediumtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `publisher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`books_id`),
  KEY `FK_bp_publishers` (`publisher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`books_id`, `title`, `cover`, `synopsis`, `release_date`, `publisher_id`) VALUES
(1, 'Hyperion', 'hyperion.webp', 'On the world called Hyperion, beyond the law of the Hegemony of Man, there waits the creature called the Shrike. There are those who worship it. There are those who fear it. And there are those who have vowed to destroy it. In the Valley of the Time Tombs, where huge, brooding structures move backward through time, the Shrike waits for them all. On the eve of Armageddon, with the entire galaxy at war, seven pilgrims set forth on a final voyage to Hyperion seeking the answers to the unsolved riddles of their lives. Each carries a desperate hope—and a terrible secret. And one may hold the fate of humanity in his hands.', '1989-05-26', 1);

-- --------------------------------------------------------

--
-- Table structure for table `books_genres`
--

DROP TABLE IF EXISTS `books_genres`;
CREATE TABLE IF NOT EXISTS `books_genres` (
  `books_id` int(11) NOT NULL,
  `genres_id` int(11) NOT NULL,
  PRIMARY KEY (`books_id`,`genres_id`),
  KEY `FK_bg_genres` (`genres_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `books_genres`
--

INSERT INTO `books_genres` (`books_id`, `genres_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
CREATE TABLE IF NOT EXISTS `genres` (
  `genres_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`genres_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `genres`
--

INSERT INTO `genres` (`genres_id`, `name`) VALUES
(1, 'Fantasy'),
(2, 'Sci-Fi'),
(3, 'Adventure'),
(4, 'Mistery'),
(5, 'Tragedy');

-- --------------------------------------------------------

--
-- Table structure for table `publishers`
--

DROP TABLE IF EXISTS `publishers`;
CREATE TABLE IF NOT EXISTS `publishers` (
  `publishers_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `website` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`publishers_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `publishers`
--

INSERT INTO `publishers` (`publishers_id`, `name`, `website`, `address`) VALUES
(1, 'Bantam Books', 'http://www.randomhousebooks.com/', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `users_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_type` enum('customer','administrator','banned','deleted') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'customer',
  PRIMARY KEY (`users_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `authors_books`
--
ALTER TABLE `authors_books`
  ADD CONSTRAINT `FK_ab_authors` FOREIGN KEY (`authors_id`) REFERENCES `authors` (`authors_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_ab_books` FOREIGN KEY (`books_id`) REFERENCES `books` (`books_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `books`
--
ALTER TABLE `books`
  ADD CONSTRAINT `FK_bp_publishers` FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`publishers_id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `books_genres`
--
ALTER TABLE `books_genres`
  ADD CONSTRAINT `FK_bg_books` FOREIGN KEY (`books_id`) REFERENCES `books` (`books_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_bg_genres` FOREIGN KEY (`genres_id`) REFERENCES `genres` (`genres_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
