-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ptecnica
-- ------------------------------------------------------
-- Server version	8.4.5

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `cliente_id` bigint NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `genero` enum('FEMENINO','MASCULINO') NOT NULL,
  `identificacion` varchar(25) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `telefono` varchar(25) DEFAULT NULL,
  `contrasena` varchar(35) NOT NULL,
  `estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`cliente_id`),
  UNIQUE KEY `UKmvqpaay4xno9pnlalro0awadi` (`identificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Otavalo sn y principal',30,'MASCULINO','1234567893','Jose Lema','098254785','1234',_binary ''),(2,'Amazonas y NNUU',38,'FEMENINO','0987665421','Marianela Montalvo','097548965','5678',_binary ''),(3,'13 junio y Equinoccial',22,'MASCULINO','0977642212','Juan Osorio','097548965','1245',_binary '');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentas` (
  `numero_cuenta` varchar(30) NOT NULL,
  `estado` bit(1) DEFAULT NULL,
  `saldo_inicial` decimal(38,2) NOT NULL,
  `tipo_cuenta` enum('AHORROS','CORRIENTE') NOT NULL,
  `cliente_id` bigint NOT NULL,
  PRIMARY KEY (`numero_cuenta`),
  KEY `FK65yk2321jpusl3fk96lqehrli` (`cliente_id`),
  CONSTRAINT `FK65yk2321jpusl3fk96lqehrli` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`cliente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES ('225487',_binary '',1300.00,'CORRIENTE',2),('478758',_binary '',1425.00,'AHORROS',1),('495878',_binary '',150.00,'AHORROS',3),('496825',_binary '',0.00,'AHORROS',2),('585545',_binary '',1000.00,'CORRIENTE',1);
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimientos` (
  `movimiento_id` bigint NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `saldo` decimal(38,2) NOT NULL,
  `tipo_movimiento` varchar(255) NOT NULL,
  `valor` decimal(38,2) NOT NULL,
  `numero_cuenta` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`movimiento_id`),
  KEY `FKo5koqjuegcyto02t8ytlf3y80` (`numero_cuenta`),
  CONSTRAINT `FKo5koqjuegcyto02t8ytlf3y80` FOREIGN KEY (`numero_cuenta`) REFERENCES `cuentas` (`numero_cuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos`
--

LOCK TABLES `movimientos` WRITE;
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
INSERT INTO `movimientos` VALUES (1,'2025-11-22',1425.00,'Retiro',-575.00,'478758'),(2,'2025-11-22',700.00,'Deposito',600.00,'225487'),(3,'2025-11-22',150.00,'Deposito',150.00,'495878'),(4,'2025-11-22',0.00,'Retiro',-540.00,'496825'),(5,'2025-11-22',1300.00,'Deposito',600.00,'225487');
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-22 21:56:03
