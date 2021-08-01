-- MariaDB dump 10.17  Distrib 10.4.11-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: store
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bird`
--

DROP TABLE IF EXISTS `bird`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bird` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `original_price` decimal(10,2) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `special_item` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bird`
--

LOCK TABLES `bird` WRITE;
/*!40000 ALTER TABLE `bird` DISABLE KEYS */;
INSERT INTO `bird` VALUES (1,'Bird 1',24,'Bird 1 Description',0.00,42.00,0),(2,'Bird 2',8,'Bird 2 Description',0.99,25.00,1),(3,'Bird 3',2,'Bird 3 Description',0.00,12.80,0),(4,'Bird 4',12,'Bird 4 Description',0.00,11.65,0),(5,'Bird 5',74,'Bird 5 Description',0.99,36.65,1),(8,'Bird 8',8,'Bird 8 Description',0.00,7.55,0),(26,'Bird 9',0,'Bird 9 Description',170.00,64.00,1),(35,'Bird 10',9,'Bird 10 Description',168.35,87.15,1),(41,'Bird 11',143,'Bird 11 Description',2468.00,851.99,1);
/*!40000 ALTER TABLE `bird` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) DEFAULT NULL,
  `PHONE` varchar(45) DEFAULT NULL,
  `USERNAME` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `ABOUT` varchar(1000) DEFAULT NULL,
  `money` float DEFAULT NULL,
  `ROLE` varchar(45) DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Administrator','N/A','admin','$2a$10$kf0DT/WAvkvWz/fVDIg7Juk/TvYkam1KzCkfwNj4zTUdmL8B3QJAS','Administrator account',133337,'ROLE_ADMIN',1),(2,'Trần Bảo Phi','090 678 2998','phitb','$2a$10$ee2rk7BgnR5ZNF5k37ttUedzps0WjqvKgd9ybO1hXk/r7O3.NMAVu','User account',2738.42,'ROLE_USER',1),(5,'Chú Báo Hồng','012 8356 1554','chupaohong1209','$2a$10$nKCedGyPazD./LId8qnVte6LTj7Brtpbph0mxYEU44T7I1P8gBgXe','Chú Báo Hồng Description',300,'ROLE_USER',1),(6,'Trần Bảo Phi','090 678 2998','tranbaophi','$2a$10$AwCmrkEcoUwVCTnvbj1iEupau9Na3Z1cz.kb.LtRTkBsLjj12gVB6','Trần Bảo Phi Description',1000,'ROLE_USER',1),(16,'Trần Bảo Phi','','tranphi123','$2a$10$DOyaMRHkYteMsSdi5zfhJ.Diexc4zki9W0CijhXYsY7CzJ6eJymKa','',0,'ROLE_USER',1),(18,'Admin Phi','','phi_admin','$2a$10$96SH36lImE8NICzT/RH5uucm4DUXPrgaEZL1uMpQ5Mqd4K41PHiJ2','',0,'ROLE_ADMIN',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertest`
--

DROP TABLE IF EXISTS `usertest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usertest` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) DEFAULT NULL,
  `PHONE` varchar(45) DEFAULT NULL,
  `USERNAME` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  `ABOUT` varchar(1000) DEFAULT NULL,
  `ROLE` varchar(45) DEFAULT NULL,
  `FAVORITES` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertest`
--

LOCK TABLES `usertest` WRITE;
/*!40000 ALTER TABLE `usertest` DISABLE KEYS */;
INSERT INTO `usertest` VALUES (1,'Administrator',NULL,'admin','admin','Administrator account','FULL PERMISSION',NULL),(2,'Tran Bao Phi','0906782998','phitb','phitb','','Moderator','');
/*!40000 ALTER TABLE `usertest` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-02  0:30:27
