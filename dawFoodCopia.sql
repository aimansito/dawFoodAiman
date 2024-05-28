-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: dawFoodAiman
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1

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
-- Table structure for table `Producto`
--

DROP TABLE IF EXISTS `Producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Producto` (
  `idProducto` int NOT NULL AUTO_INCREMENT,
  `IVA` enum('21','10') NOT NULL,
  `precio` decimal(6,2) NOT NULL,
  `stock` int DEFAULT '0',
  `descripcion` varchar(100) NOT NULL,
  `codTipoProducto` int NOT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `fk_prod_tipoProd` (`codTipoProducto`),
  CONSTRAINT `fk_prod_tipoProd` FOREIGN KEY (`codTipoProducto`) REFERENCES `tipoProducto` (`codTipoProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Producto`
--

LOCK TABLES `Producto` WRITE;
/*!40000 ALTER TABLE `Producto` DISABLE KEYS */;
INSERT INTO `Producto` VALUES (10,'10',10.00,44,'Cus-Cus',1),(13,'21',3.99,20,'Patatas Fritas',1),(14,'10',2.99,70,'Pastel',3),(15,'21',10.99,56,'Tacos Victor',1),(16,'21',1.99,50,'Poms',2),(17,'10',1.99,18,'Hawai',2),(18,'21',1.99,37,'Poms',2),(21,'10',1.00,33,'Pistacho',1);
/*!40000 ALTER TABLE `Producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TPV`
--

DROP TABLE IF EXISTS `TPV`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TPV` (
  `idTPV` int NOT NULL AUTO_INCREMENT,
  `ubicacion` varchar(100) NOT NULL,
  `fechaHora` datetime NOT NULL,
  `contraseña` varchar(8) NOT NULL,
  PRIMARY KEY (`idTPV`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TPV`
--

LOCK TABLES `TPV` WRITE;
/*!40000 ALTER TABLE `TPV` DISABLE KEYS */;
INSERT INTO `TPV` VALUES (1,'Estepona','2024-05-24 00:00:00','1234'),(2,'Marbella','2024-05-24 00:30:00','5678'),(3,'Pamplona','2024-05-24 00:00:00','1234'),(4,'España','2024-05-24 00:00:00','1234'),(5,'España','2024-05-24 00:00:00','1234'),(7,'Marruecos','2024-05-24 00:00:00','1234');
/*!40000 ALTER TABLE `TPV` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ticket`
--

DROP TABLE IF EXISTS `Ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ticket` (
  `idTicket` int NOT NULL AUTO_INCREMENT,
  `numPedido` int NOT NULL,
  `importeTotal` decimal(5,2) NOT NULL,
  `fechaHora` datetime NOT NULL,
  `idTPV` int NOT NULL,
  PRIMARY KEY (`idTicket`),
  KEY `fk_ticket_tpv` (`idTPV`),
  CONSTRAINT `fk_ticket_tpv` FOREIGN KEY (`idTPV`) REFERENCES `TPV` (`idTPV`)
) ENGINE=InnoDB AUTO_INCREMENT=1868 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ticket`
--

LOCK TABLES `Ticket` WRITE;
/*!40000 ALTER TABLE `Ticket` DISABLE KEYS */;
INSERT INTO `Ticket` VALUES (1,1001,150.75,'2024-05-24 12:00:00',1),(2,1002,89.50,'2024-05-24 12:30:00',2),(3,1003,5.98,'2024-05-27 17:24:54',1),(4,1004,25.97,'2024-05-27 19:59:51',1),(5,1005,29.85,'2024-05-27 21:21:47',1),(6,1006,22.00,'2024-05-27 21:33:11',1),(7,1007,4.38,'2024-05-27 21:35:26',1),(8,1008,13.13,'2024-05-27 21:38:12',1),(9,1009,4.82,'2024-05-27 21:51:54',1),(10,1010,4.82,'2024-05-27 21:55:41',1),(11,1011,132.98,'2024-05-27 23:49:55',1),(12,1012,132.98,'2024-05-27 23:54:48',1),(13,1013,4.82,'2024-05-27 23:56:18',1),(14,1014,132.98,'2024-05-27 23:58:38',1),(15,1015,44.00,'2024-05-28 00:04:20',1),(16,1016,4.82,'2024-05-28 01:22:07',1),(17,1017,4.82,'2024-05-28 01:24:11',1),(18,1018,4.82,'2024-05-28 01:24:11',1),(19,1019,4.82,'2024-05-28 01:24:11',1),(1850,1020,74.75,'2024-05-28 01:43:06',1),(1851,1021,5.50,'2024-05-28 01:45:51',1),(1852,1022,3.30,'2024-05-28 01:49:30',1),(1853,1023,7.22,'2024-05-28 02:02:53',1),(1854,1024,2.20,'2024-05-28 02:11:27',1),(1855,1025,7.22,'2024-05-28 02:18:21',1),(1856,1026,7.22,'2024-05-28 02:18:21',1),(1857,1027,66.00,'2024-05-28 02:21:16',1),(1858,1028,3.30,'2024-05-28 02:28:23',1),(1859,1029,3.30,'2024-05-28 02:28:23',1),(1860,1030,1.10,'2024-05-28 02:30:11',1),(1861,1031,3.30,'2024-05-28 02:33:40',1),(1862,1032,7.22,'2024-05-28 02:36:43',1),(1863,1033,53.19,'2024-05-28 02:38:50',1),(1864,1034,2.41,'2024-05-28 02:46:23',1),(1865,1035,3.30,'2024-05-28 02:55:35',1),(1866,1036,2.20,'2024-05-28 10:53:02',1),(1867,1037,4.38,'2024-05-28 11:11:45',1);
/*!40000 ALTER TABLE `Ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleTicket`
--

DROP TABLE IF EXISTS `detalleTicket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleTicket` (
  `idTicket` int NOT NULL,
  `idProducto` int NOT NULL,
  `cantidadProducto` int NOT NULL,
  PRIMARY KEY (`idTicket`,`idProducto`),
  KEY `fk_detalleTicket_prod` (`idProducto`),
  CONSTRAINT `fk_detalleTicket_prod` FOREIGN KEY (`idProducto`) REFERENCES `Producto` (`idProducto`),
  CONSTRAINT `fk_detalleTicket_ticket` FOREIGN KEY (`idTicket`) REFERENCES `Ticket` (`idTicket`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleTicket`
--

LOCK TABLES `detalleTicket` WRITE;
/*!40000 ALTER TABLE `detalleTicket` DISABLE KEYS */;
INSERT INTO `detalleTicket` VALUES (1865,21,3),(1866,21,2),(1867,17,2);
/*!40000 ALTER TABLE `detalleTicket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoProducto`
--

DROP TABLE IF EXISTS `tipoProducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipoProducto` (
  `codTipoProducto` int NOT NULL AUTO_INCREMENT,
  `nomCat` enum('COMIDAS','BEBIDAS','POSTRES') NOT NULL,
  `tipoProdDescripcion` varchar(100) NOT NULL,
  PRIMARY KEY (`codTipoProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoProducto`
--

LOCK TABLES `tipoProducto` WRITE;
/*!40000 ALTER TABLE `tipoProducto` DISABLE KEYS */;
INSERT INTO `tipoProducto` VALUES (1,'COMIDAS','Pasta'),(2,'BEBIDAS','Refrescos'),(3,'POSTRES','Pasteles');
/*!40000 ALTER TABLE `tipoProducto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-28 12:46:34
