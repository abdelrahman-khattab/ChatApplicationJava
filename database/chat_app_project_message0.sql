-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: chat_app_project
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `MESSAGE_ID` int NOT NULL AUTO_INCREMENT,
  `BODY` varchar(10000) NOT NULL,
  `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATOR_ID` varchar(20) NOT NULL,
  `RECEIPENT_ID` varchar(45) DEFAULT NULL,
  `GROUP_RECEIPENT_ID` int DEFAULT NULL,
  `message_color` varchar(45) NOT NULL,
  `font_familly` varchar(45) NOT NULL,
  `font_posture` varchar(45) NOT NULL,
  `font_weight` varchar(45) NOT NULL,
  `font_size` int NOT NULL,
  `font_underline` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`MESSAGE_ID`),
  KEY `phone_number_idx` (`CREATOR_ID`),
  KEY `receipent_fk_idx` (`RECEIPENT_ID`),
  KEY `group_receipnt_fk_idx` (`GROUP_RECEIPENT_ID`),
  KEY `group_receipnt_forignkey_idx` (`GROUP_RECEIPENT_ID`),
  CONSTRAINT `creator_fk` FOREIGN KEY (`CREATOR_ID`) REFERENCES `user` (`PHONE_NUMBER`),
  CONSTRAINT `group_receipnt_forignkey` FOREIGN KEY (`GROUP_RECEIPENT_ID`) REFERENCES `groupchat` (`GROUP_ID`),
  CONSTRAINT `receipent_fk` FOREIGN KEY (`RECEIPENT_ID`) REFERENCES `user` (`PHONE_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-22  5:03:13