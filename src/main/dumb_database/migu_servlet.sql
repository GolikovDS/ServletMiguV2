-- MySQL dump 10.13  Distrib 5.1.51, for Win32 (ia32)
--
-- Host: localhost    Database: migu_servlet
-- ------------------------------------------------------
-- Server version	5.1.51-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `message_answer`
--

DROP TABLE IF EXISTS `message_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_user` varchar(250) DEFAULT NULL,
  `text_answer` varchar(50000) NOT NULL,
  `date` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_answer`
--

LOCK TABLES `message_answer` WRITE;
/*!40000 ALTER TABLE `message_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages_to_user`
--

DROP TABLE IF EXISTS `messages_to_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages_to_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_user` varchar(255) DEFAULT NULL,
  `date` varchar(15) NOT NULL,
  `text_message` varchar(30000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages_to_user`
--

LOCK TABLES `messages_to_user` WRITE;
/*!40000 ALTER TABLE `messages_to_user` DISABLE KEYS */;
INSERT INTO `messages_to_user` VALUES (5,'rmu','28.06.2017','asdsfsd fsdfsdfsdfsd fs'),(11,NULL,'10.07.2017','we'),(12,NULL,'10.07.2017','ds'),(13,'rmu','10.07.2017','ds'),(14,'migu','10.07.2017','Вопрос 1'),(15,'migu','10.07.2017','Weee'),(16,'rmu','10.07.2017','121'),(17,'migu','10.07.2017','awww'),(18,'migu','10.07.2017','ssss'),(19,'migu','10.07.2017','sssss'),(20,'rmu','10.07.2017','Очень сложно'),(21,'rmu','10.07.2017','Не возможно'),(22,'rmu','13.07.2017','123'),(23,'rmu','13.07.2017','123');
/*!40000 ALTER TABLE `messages_to_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `migu`
--

DROP TABLE IF EXISTS `migu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `migu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `migu`
--

LOCK TABLES `migu` WRITE;
/*!40000 ALTER TABLE `migu` DISABLE KEYS */;
INSERT INTO `migu` VALUES (31,'129'),(35,'132'),(33,'145'),(32,'225'),(30,'323'),(29,'333'),(34,'425');
/*!40000 ALTER TABLE `migu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u_m`
--

DROP TABLE IF EXISTS `u_m`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_m` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_migu` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `u_m_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user_migu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `u_m`
--

LOCK TABLES `u_m` WRITE;
/*!40000 ALTER TABLE `u_m` DISABLE KEYS */;
INSERT INTO `u_m` VALUES (4,18,31),(5,17,29),(6,17,30),(8,17,33),(9,18,29),(10,18,32);
/*!40000 ALTER TABLE `u_m` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_migu`
--

DROP TABLE IF EXISTS `user_migu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_migu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(200) DEFAULT NULL,
  `password` varchar(200) NOT NULL,
  `jsession` varchar(200) NOT NULL,
  `organization` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_migu`
--

LOCK TABLES `user_migu` WRITE;
/*!40000 ALTER TABLE `user_migu` DISABLE KEYS */;
INSERT INTO `user_migu` VALUES (17,'rmu','2','',''),(18,'migu','3','',''),(20,'artsok','1','','');
/*!40000 ALTER TABLE `user_migu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-09 12:56:02
