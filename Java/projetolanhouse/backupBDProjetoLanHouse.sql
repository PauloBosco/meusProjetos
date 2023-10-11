CREATE DATABASE  IF NOT EXISTS `bdprojetolanhouse` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bdprojetolanhouse`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: bdprojetolanhouse
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `computador`
--

DROP TABLE IF EXISTS `computador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `computador` (
  `id_computador` int NOT NULL AUTO_INCREMENT,
  `processador` varchar(15) NOT NULL,
  `memoriaRam` int NOT NULL,
  `polegadasMonitor` int NOT NULL,
  PRIMARY KEY (`id_computador`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computador`
--

LOCK TABLES `computador` WRITE;
/*!40000 ALTER TABLE `computador` DISABLE KEYS */;
INSERT INTO `computador` VALUES (1,'i5',16,24),(2,'i7',8,24),(3,'i5',8,17);
/*!40000 ALTER TABLE `computador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `impressoes`
--

DROP TABLE IF EXISTS `impressoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `impressoes` (
  `id_impressoes` int NOT NULL AUTO_INCREMENT,
  `id_servico` int NOT NULL,
  `nomeDocumento` varchar(45) NOT NULL,
  `preco` double NOT NULL,
  PRIMARY KEY (`id_impressoes`),
  KEY `impressoes_FK_idx` (`id_servico`),
  CONSTRAINT `impressoes_FK` FOREIGN KEY (`id_servico`) REFERENCES `servico` (`id_servico`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `impressoes`
--

LOCK TABLES `impressoes` WRITE;
/*!40000 ALTER TABLE `impressoes` DISABLE KEYS */;
INSERT INTO `impressoes` VALUES (4,2,'Documento1',2),(5,2,'Documento2',3),(6,20,'DocumentoFulano1',0),(7,20,'DocumentoFulano2',3),(8,21,'DocumentoCiclano1',1.5),(9,21,'DocumentoCiclano2',2);
/*!40000 ALTER TABLE `impressoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `id_produto` int NOT NULL AUTO_INCREMENT,
  `id_servico` int NOT NULL,
  `tipoProduto` enum('COCA','PEPSI','SALGADO') NOT NULL,
  PRIMARY KEY (`id_produto`),
  KEY `produto_FK_idx` (`id_servico`),
  CONSTRAINT `produto_FK` FOREIGN KEY (`id_servico`) REFERENCES `servico` (`id_servico`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,2,'COCA'),(2,2,'PEPSI'),(3,2,'SALGADO'),(4,2,'PEPSI'),(13,19,'PEPSI'),(14,19,'COCA'),(15,21,'PEPSI');
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico`
--

DROP TABLE IF EXISTS `servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servico` (
  `id_servico` int NOT NULL AUTO_INCREMENT,
  `cod_servico` varchar(15) NOT NULL,
  PRIMARY KEY (`id_servico`),
  UNIQUE KEY `cod_servico_UNIQUE` (`cod_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico`
--

LOCK TABLES `servico` WRITE;
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` VALUES (15,'1233'),(4,'1555'),(6,'1571'),(8,'2524'),(5,'295'),(14,'323'),(19,'3755'),(17,'3777'),(20,'4741'),(7,'5055'),(10,'5091'),(2,'555442'),(12,'8120'),(21,'8477'),(3,'8961');
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uso`
--

DROP TABLE IF EXISTS `uso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uso` (
  `id_uso` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `id_computador` int NOT NULL,
  `id_servico` int DEFAULT NULL,
  `valorHora` double NOT NULL,
  `diaLogin` timestamp NOT NULL,
  `qtidadeMinutosTempo` int NOT NULL,
  PRIMARY KEY (`id_uso`),
  KEY `uso_FK_2_idx` (`id_usuario`),
  KEY `uso_FK_idx` (`id_computador`),
  KEY `uso_FK_1_idx` (`id_servico`),
  CONSTRAINT `uso_FK` FOREIGN KEY (`id_computador`) REFERENCES `computador` (`id_computador`),
  CONSTRAINT `uso_FK_1` FOREIGN KEY (`id_servico`) REFERENCES `servico` (`id_servico`),
  CONSTRAINT `uso_FK_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_Usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uso`
--

LOCK TABLES `uso` WRITE;
/*!40000 ALTER TABLE `uso` DISABLE KEYS */;
INSERT INTO `uso` VALUES (3,2,1,2,3,'2023-06-03 03:00:00',60),(8,3,3,NULL,4,'2023-06-26 04:53:14',60),(9,3,2,NULL,4.5,'2023-06-26 13:00:30',77),(10,11,2,NULL,3,'2023-06-26 14:45:36',60),(11,2,1,10,5,'2023-06-26 14:46:25',17),(12,11,3,14,6,'2023-06-26 14:46:59',55),(13,2,2,19,6,'2023-06-29 06:29:31',60),(14,12,1,20,4,'2023-06-30 07:30:06',60),(15,13,2,21,3,'2023-06-30 09:18:25',120);
/*!40000 ALTER TABLE `uso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_Usuario` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(30) NOT NULL,
  `tipo` enum('C','A') DEFAULT 'C',
  PRIMARY KEY (`id_Usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Paulo Bosco','PB123','1234','A'),(2,'Ana Carolina','AC123','4321','C'),(3,'Zezinho ZZZZ','PB01','senha123','C'),(11,'Andrei Barbuto','Andrei','123','C'),(12,'Fulano Silva','Fulano','123','C'),(13,'Ciclano Raiz','Ciclano','123','C');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-30 20:53:28
