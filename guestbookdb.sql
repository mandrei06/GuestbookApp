-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.21-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.7.0.6850
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for guestbook
DROP DATABASE IF EXISTS `guestbook`;
CREATE DATABASE IF NOT EXISTS `guestbook` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `guestbook`;

-- Dumping structure for table guestbook.guestbook_entry
DROP TABLE IF EXISTS `guestbook_entry`;
CREATE TABLE IF NOT EXISTS `guestbook_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approved` bit(1) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `text_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgqd4188euyuak6smakiy0hrwt` (`user_id`),
  CONSTRAINT `FKgqd4188euyuak6smakiy0hrwt` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table guestbook.guestbook_entry: ~3 rows (approximately)
INSERT INTO `guestbook_entry` (`id`, `approved`, `image_url`, `user_id`, `text_content`) VALUES
	(12, b'0', '/images/background.png', 14, 'text input ex'),
	(15, b'0', '/images/429.jpg', 16, 'image1'),
	(17, b'1', '/images/418.jpg', 16, 'text 232');

-- Dumping structure for table guestbook.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table guestbook.users: ~9 rows (approximately)
INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`, `phone_number`, `role`, `username`) VALUES
	(6, 'ic@yahoo.ic', 'ic', 'ic', '$2a$10$7Q3TQnkfXPLA399/u/RmZuK0pwtQv2hEQ.WogGl.Alrm6mJ3dl6wG', '07543623189', 'GUEST', 'ic'),
	(7, 'lin@gmail.co', 'lin', 'lin', '$2a$10$GOtumidPs6OWRadlj.lFweePGjJEcWkjp00H3yb8rWQkC1zOYgqZ.', '07543623189', 'GUEST', 'lin'),
	(8, 'iop@outlook.com', 'iop', 'iop', '$2a$10$KA50JgYZIACHwFhlaCMuNucBBNQMWUkzP4GoUNdOw258THj97GtpW', '07543623189', 'GUEST', 'iop'),
	(9, 'pic@random.uk', 'pic', 'pic', '$2a$10$z7QIaGCsi6nQrH888XfhV.oF5/5DvE5o7LxEwBK7CBBoLzi0J/fE6', '07543623189', 'GUEST', 'pic'),
	(11, 'ioan@hotmail.com', 'ioan', 'ioan', '$2a$10$dZSy769Zv.sMod2Rezz8YeoWhyKurYvLDseEqvHlDPH1lMtRdyCtG', '07543623189', 'GUEST', 'ioan'),
	(13, 'a@lsa.c', 'andreea', 'andreea', '$2a$10$Yu7E.NMxICZNFQHn21XOUOtNo2IdZVMiEhkNgUJHArDjyXrzCKgEu', '07543623189', 'ADMIN', 'pe'),
	(14, 'as@l.co', 'as', 'as', '$2a$10$waMdCJxZHThxoHo3sGeWmeG2LrlZZNlfXSmaofB7KyF3kA4lEVXpy', '07543623189', 'GUEST', 'as'),
	(15, 'dsasa@yahoo.com', 'kim', 'kim', '$2a$10$sA07JhCPTZ0sjFtwPUSY5u3grB85s3pVoOnQtlYVcg/O2zdj4psGu', '0743623512', 'GUEST', 'kim'),
	(16, 'john@google.com', 'John', 'Doe', '$2a$10$EUF4DXZoi3jknJDPjoRzJeD94qeahNQBRd2Ps1npUcousVe8L32ya', '0746352643', 'GUEST', 'john');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
