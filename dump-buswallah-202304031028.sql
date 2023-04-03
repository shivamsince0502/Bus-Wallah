-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: buswallah
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `user_mob` varchar(655) DEFAULT NULL,
  `user_email` varchar(655) DEFAULT NULL,
  `route_name` varchar(300) NOT NULL,
  `bus_name` varchar(300) NOT NULL,
  `bus_number` varchar(300) NOT NULL,
  `start_id` int NOT NULL,
  `end_id` int NOT NULL,
  `date_of_booking` datetime NOT NULL,
  `fare` int NOT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `start_id` (`start_id`),
  KEY `end_id` (`end_id`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`start_id`) REFERENCES `location` (`location_id`),
  CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`end_id`) REFERENCES `location` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'8764495635','','RJH01','Shaktipunj','JHO9XYZ',31,32,'2023-04-02 20:08:27',30),(2,'8765434765','','RJH02','Purushotam','JH09PQR',32,36,'2023-04-02 23:09:16',54),(3,'8789595982','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 02:11:49',54),(4,'8789595982','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 02:22:56',54),(5,'8789595982','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 02:28:00',54),(6,'8789595982','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 02:35:19',54),(7,'8789595982','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 02:37:08',54),(8,'','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 02:38:06',54),(9,'','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 02:39:10',54),(10,'','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 02:41:18',54),(11,'','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 02:42:32',54),(12,'','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 02:45:40',54),(13,'','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 02:51:57',54),(14,'','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 02:52:42',54),(15,'','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 03:01:04',54),(16,'','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 03:09:55',54),(17,'','shivam.kumar@wavemaker.com','RJH02','Purushotam','JH09PQR',32,36,'2023-04-03 03:18:44',54);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus` (
  `bus_id` int NOT NULL AUTO_INCREMENT,
  `bus_name` varchar(300) NOT NULL,
  `bus_number` varchar(300) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `route_id` int NOT NULL,
  `no_of_seats` int NOT NULL,
  PRIMARY KEY (`bus_id`),
  UNIQUE KEY `bus_name` (`bus_name`),
  UNIQUE KEY `bus_number` (`bus_number`),
  UNIQUE KEY `bus_name_2` (`bus_name`,`bus_number`,`route_id`),
  KEY `route_id` (`route_id`),
  CONSTRAINT `bus_ibfk_1` FOREIGN KEY (`route_id`) REFERENCES `route` (`route_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` VALUES (1,'HYD2XJ','TS09GHJK',1,1,30),(2,'HYD3PN','TS09PQJK',1,2,30),(3,'HYD4GH','TS09RTYU',1,3,30),(40,'HYD2AQ','TS09GHCD',1,4,30),(41,'HYD3WS','TS09PHJK',1,5,30),(42,'HYD4SH','TS09RTOI',1,6,30),(43,'HYD1GH','TS09GHYU',1,7,30),(44,'HYD4HJ','TS09RTNM',1,8,30),(45,'HYD2DPK','TS09VCAS',1,9,30),(46,'Shaktipunj','JHO9XYZ',1,10,30),(47,'Purushotam','JH09PQR',1,11,30),(48,'Shaktiman','JH09STU',1,16,30),(49,'Shekhar','JH09TRS',1,15,30),(50,'Shatabdi','JHO9STX',1,25,30),(51,'HYD43TR','TS01PXC',1,26,30),(52,'HYD3TY','TS07PTY',1,27,30),(53,'HYD4MQ','TS03MQ',1,28,20),(54,'HYD2WE','TS04NT',1,29,30);
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driver` (
  `driver_id` int NOT NULL AUTO_INCREMENT,
  `driver_mob` varchar(600) NOT NULL,
  `driver_name` varchar(600) NOT NULL,
  `driver_email` varchar(600) NOT NULL,
  `bus_id` int NOT NULL,
  PRIMARY KEY (`driver_id`),
  UNIQUE KEY `driver_un` (`driver_mob`),
  UNIQUE KEY `driver_un1` (`driver_email`),
  KEY `bus_id` (`bus_id`),
  CONSTRAINT `driver_ibfk_1` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`bus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (1,'8785467824','Satyam Kumar','Satyam@gmail.com',45),(2,'8725467824','Satyam Kumar','Satyym@gmail.com',45),(3,'9865434567','Praveen Kumar','Praveen@gmail.com',50),(8,'8789595982','Shivam Kumar','shivam.kumar.cse@gmail.com',47),(9,'8765675434','Vaibhav singh','vaibhav@gmail.com',51),(10,'9876545643','Ranjeet Singh','Ranjeet@gmail.com',52),(11,'9872545643','Praneet Singh','praneet@gmail.com',53),(12,'9872545676','Puneet Singh','puneet@gmail.com',54);
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edge`
--

DROP TABLE IF EXISTS `edge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edge` (
  `edge_id` int NOT NULL AUTO_INCREMENT,
  `from_id` int NOT NULL,
  `to_id` int NOT NULL,
  `distance` int NOT NULL,
  PRIMARY KEY (`edge_id`),
  UNIQUE KEY `from_id` (`from_id`,`to_id`),
  UNIQUE KEY `to_id` (`to_id`,`from_id`),
  CONSTRAINT `edge_ibfk_1` FOREIGN KEY (`from_id`) REFERENCES `location` (`location_id`),
  CONSTRAINT `edge_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `location` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edge`
--

LOCK TABLES `edge` WRITE;
/*!40000 ALTER TABLE `edge` DISABLE KEYS */;
INSERT INTO `edge` VALUES (1,1,2,4),(2,1,3,5),(3,1,4,6),(4,1,5,3),(5,2,7,9),(6,2,6,3),(7,2,5,10),(8,2,4,23),(9,2,3,12),(10,3,6,4),(11,3,4,4),(12,3,5,11),(13,4,6,20),(14,4,13,18),(15,4,11,12),(16,4,5,11),(17,4,7,8),(18,4,9,1),(19,4,14,12),(20,7,5,2),(21,7,1,5),(22,7,3,9),(23,12,15,5),(24,15,16,3),(25,16,17,1),(26,17,21,11),(27,21,20,4),(28,20,19,2),(29,19,23,3),(30,20,24,6),(31,20,28,5),(32,22,28,5),(33,22,18,6),(34,18,12,11),(45,13,12,12),(46,4,12,12),(49,15,12,5),(50,26,22,15),(51,22,1,23),(54,28,12,5),(55,28,8,5),(56,29,21,12),(57,27,29,5),(58,26,27,9),(59,34,31,3),(60,32,34,2),(61,33,32,6),(62,36,33,3),(63,35,34,8),(64,36,35,7),(65,38,36,2),(66,37,38,3),(67,39,37,11),(69,31,40,13),(70,39,40,20),(71,42,41,3),(72,47,44,4),(73,48,47,1),(74,11,44,5),(75,43,49,1),(76,11,43,1),(77,44,11,5),(78,45,11,1),(79,49,45,1);
/*!40000 ALTER TABLE `edge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `location_id` int NOT NULL AUTO_INCREMENT,
  `location_name` varchar(600) NOT NULL,
  PRIMARY KEY (`location_id`),
  UNIQUE KEY `location_name` (`location_name`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (18,'Ameerpet'),(28,'Bagh Lingampally'),(44,'Bahadurpally'),(23,'Balangar'),(8,'Banjara Hills'),(27,'Basheer BAgh'),(38,'Bdo Office'),(35,'Bengali Pada'),(32,'Bermo Sim'),(39,'Chandrapura'),(43,'Charminar'),(26,'Chilkalguda'),(30,'Edi Bazaar'),(1,'Gachibowli'),(34,'Gandhi Nagar'),(6,'Habsiguda'),(5,'Hi-Tech City'),(13,'Hyderabad'),(7,'Jubilee Hills'),(36,'Kargali Bazar'),(4,'Kukatpally'),(29,'Laad Bazar'),(19,'Lalaguda'),(24,'Malakjgiri'),(3,'Mallapur'),(9,'Manikonda'),(17,'Miyapur'),(47,'Nampally'),(37,'Phushro'),(11,'Quthbullapur'),(46,'Qutubullapar'),(42,'Rajeev Nagar'),(45,'Ram Nagar'),(40,'Ramnagar'),(49,'Risala Bazar'),(15,'Saroornagar'),(41,'Seccunderabad'),(14,'Secunderabad'),(12,'Shamirpet'),(2,'Shamshabad'),(31,'Shubash Nagar'),(16,'Sindhi Colony'),(33,'Teen Number'),(22,'Toli Chowki'),(48,'Uday Nagar'),(10,'Uppal Kalan'),(20,'Uppuguda'),(21,'Yousufguda');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route` (
  `route_id` int NOT NULL AUTO_INCREMENT,
  `route_name` varchar(600) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  PRIMARY KEY (`route_id`),
  UNIQUE KEY `route_name` (`route_name`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,'HYD2XY',1),(2,'HYD2ZY',1),(3,'HYD2CR',1),(4,'HYD2NB',1),(5,'HYD2PW',1),(6,'HYD2MN',1),(7,'HYD2LK',1),(8,'HYD2RT',1),(9,'HYD2CK',1),(10,'RJH01',1),(11,'RJH02',1),(15,'RJH03',1),(16,'RJH04',1),(25,'RJH05',1),(26,'HYD3PX',1),(27,'HYD3TY',1),(28,'HYD9TS',1),(29,'HYD6QT',1);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route_path`
--

DROP TABLE IF EXISTS `route_path`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route_path` (
  `path_id` int NOT NULL AUTO_INCREMENT,
  `route_id` int NOT NULL,
  `location_id` int NOT NULL,
  `seq_no` int NOT NULL,
  PRIMARY KEY (`path_id`),
  UNIQUE KEY `route_id` (`route_id`,`location_id`,`seq_no`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `route_path_ibfk_1` FOREIGN KEY (`route_id`) REFERENCES `route` (`route_id`),
  CONSTRAINT `route_path_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route_path`
--

LOCK TABLES `route_path` WRITE;
/*!40000 ALTER TABLE `route_path` DISABLE KEYS */;
INSERT INTO `route_path` VALUES (1,1,1,1),(2,1,2,2),(3,1,7,3),(6,2,3,3),(5,2,5,2),(4,2,7,1),(7,3,1,1),(8,3,4,2),(9,3,14,3),(10,4,12,1),(11,4,15,2),(12,4,16,3),(13,4,17,4),(14,5,17,1),(16,5,20,3),(15,5,21,2),(18,6,19,2),(17,6,20,1),(19,6,23,3),(20,7,12,1),(21,7,18,2),(22,7,22,3),(25,8,20,3),(23,8,22,1),(24,8,28,2),(26,9,21,1),(29,9,26,4),(28,9,27,3),(27,9,29,2),(39,10,31,1),(41,10,32,3),(40,10,34,2),(42,11,32,1),(43,11,33,2),(44,11,36,3),(45,15,34,1),(46,15,35,2),(47,15,36,3),(48,16,36,1),(50,16,37,3),(49,16,38,2),(51,16,39,4),(52,25,31,1),(54,25,39,3),(53,25,40,2),(55,26,41,1);
/*!40000 ALTER TABLE `route_path` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'buswallah'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-03 10:28:56
