-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.6.7-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table adres_boek.bedrijf
DROP TABLE IF EXISTS `bedrijf`;
CREATE TABLE IF NOT EXISTS `bedrijf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(55) NOT NULL,
  `adres` varchar(55) NOT NULL,
  `land_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bedrijf_ibfk_1` (`land_id`),
  CONSTRAINT `bedrijf_ibfk_1` FOREIGN KEY (`land_id`) REFERENCES `land` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table adres_boek.contact_informatie
DROP TABLE IF EXISTS `contact_informatie`;
CREATE TABLE IF NOT EXISTS `contact_informatie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adres` varchar(50) NOT NULL,
  `telefoon_nummer` varchar(25) NOT NULL,
  `persoon_id` int(11) NOT NULL DEFAULT 1,
  `land_id` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `persoon_fk_idx` (`persoon_id`),
  KEY `land_fk_idx` (`land_id`),
  CONSTRAINT `land_fk` FOREIGN KEY (`land_id`) REFERENCES `land` (`id`) ON DELETE CASCADE,
  CONSTRAINT `persoon_fk` FOREIGN KEY (`persoon_id`) REFERENCES `persoon` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table adres_boek.land
DROP TABLE IF EXISTS `land`;
CREATE TABLE IF NOT EXISTS `land` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `naam_uk` (`naam`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table adres_boek.persoon
DROP TABLE IF EXISTS `persoon`;
CREATE TABLE IF NOT EXISTS `persoon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `naam` (`naam`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table adres_boek.persoon_bedrijf
DROP TABLE IF EXISTS `persoon_bedrijf`;
CREATE TABLE IF NOT EXISTS `persoon_bedrijf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bedrijf_id` int(11) NOT NULL,
  `persoon_id` int(11) NOT NULL,
  `positie` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bedrijf_id` (`bedrijf_id`),
  KEY `persoon_id` (`persoon_id`),
  CONSTRAINT `persoon_bedrijf_ibfk_1` FOREIGN KEY (`bedrijf_id`) REFERENCES `bedrijf` (`id`) ON DELETE CASCADE,
  CONSTRAINT `persoon_bedrijf_ibfk_2` FOREIGN KEY (`persoon_id`) REFERENCES `persoon` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
