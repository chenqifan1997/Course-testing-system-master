-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: personal
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam` (
  `no` int(11) NOT NULL,
  `content` varchar(100) DEFAULT NULL,
  `option1` varchar(45) DEFAULT NULL,
  `option2` varchar(45) DEFAULT NULL,
  `option3` varchar(45) DEFAULT NULL,
  `option4` varchar(45) DEFAULT NULL,
  `answer` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (1,'目前我们所说的个人台式商用机属于（）。','A.巨型机','B.中型机','C.小型机','D.微型机','D'),(2,'（2000）10化成十六进制数是（）。','A.（7CD）16','B.（7D0）16','C.（7E0）16','D.（7F0）16','B'),(3,'下列数中最大的数是（）。','A.（10011001）2','B.（227）8 ','C.（98）16','D.（152）10 ','A'),(4,'（）表示法主要用于表示浮点数中的阶码。','A.原码','B.补码','C.反码','D.移码','D'),(5,'在小型或微型计算机里，普遍采用的字符编码是（）。','A.BCD码','B.16进制','C.格雷码','D.ASCⅡ码','D'),(6,'下列有关运算器的描述中，（）是正确的。','A.只做算术运算，不做逻辑运算','B.只做加法','C.能暂时存放运算结果','D.既做算术运算，又做逻辑运算','D'),(7,'EPROM是指（）。','A.读写存储器','B.只读存储器','C.可编程的只读存储器','D.光擦除可编程的只读存储器','D'),(8,'Intel80486是32位微处理器，Pentium是（）位微处理器。','A.16','B.32','C.48','D.64','D'),(9,'设［X］补=1.x1x2x3x4,当满足（）时，X > -1/2成立。','A.x1必须为1，x2x3x4至少有一个为1','B.x1必须为1，x2x3x4任意 ','C.x1必须为0，x2x3x4至少有一个为1','D.x1必须为0，x2x3x4任意','A'),(10,'CPU主要包括（）。','A.控制器','B.控制器、 运算器、cache','C.运算器和主存','D.控制器、ALU和主存','B');
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` varchar(20) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `class` varchar(10) DEFAULT NULL,
  `grade` varchar(10) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `major` varchar(20) DEFAULT NULL,
  `number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES ('chenqifan','陈启凡','男','计算机153','大三','111111','计算机科学与技术','201505010317'),('hutao','胡涛','男','计算机153','大三','111111','计算机科学与技术','201505010316'),('tongshengyu','童圣宇','男','计算机153','大三','111111','计算机科学与技术','201505010327'),('wangweicheng','王卫成','男','计算机153','大三','111111','计算机科学与技术','201505010307'),('wuchenghao','吴程昊','男','计算机153','大三','111111','计算机科学与技术','201505010324');
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

-- Dump completed on 2018-04-24 12:11:35
