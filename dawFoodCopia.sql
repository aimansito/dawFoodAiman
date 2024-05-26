-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 192.168.1.170    Database: dawFoodAiman
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.20.04.1

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Producto`
--

LOCK TABLES `Producto` WRITE;
/*!40000 ALTER TABLE `Producto` DISABLE KEYS */;
INSERT INTO `Producto` VALUES (10,'10',10.00,50,'Cus-Cus',1),(13,'21',3.99,20,'Patatas Fritas',1),(14,'10',2.99,50,'Pastel',3),(15,'21',10.99,60,'Tacos Victor',1),(16,'21',1.99,20,'Poms',2),(17,'10',1.99,20,'Hawai',2);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ticket`
--

LOCK TABLES `Ticket` WRITE;
/*!40000 ALTER TABLE `Ticket` DISABLE KEYS */;
INSERT INTO `Ticket` VALUES (1,1001,150.75,'2024-05-24 12:00:00',1),(2,1002,89.50,'2024-05-24 12:30:00',2);
/*!40000 ALTER TABLE `Ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleTicket`
--

DROP TABLE IF EXISTS `detalleTicket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleTicket` (
  `cantidadProducto` int NOT NULL AUTO_INCREMENT,
  `idTicket` int NOT NULL,
  `idProducto` int NOT NULL,
  PRIMARY KEY (`cantidadProducto`),
  KEY `fk_detalleTicket_ticket` (`idTicket`),
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

-- Dump completed on 2024-05-27  1:10:51
