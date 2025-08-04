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
  `contrasena` varchar(35) NOT NULL,
  `estado` bit(1) DEFAULT NULL,
  `persona_persona_id` bigint DEFAULT NULL,
  PRIMARY KEY (`cliente_id`),
  UNIQUE KEY `UKki7gl6vvhtv67rjvl8bir2olk` (`persona_persona_id`),
  CONSTRAINT `FK3esf59jce7iphpi2oy5lat3j1` FOREIGN KEY (`persona_persona_id`) REFERENCES `personas` (`persona_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'1234',_binary '',1),(2,'5678',_binary '',2),(3,'1245',_binary '',5);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentas` (
  `cuenta_id` bigint NOT NULL AUTO_INCREMENT,
  `estado` bit(1) DEFAULT NULL,
  `numero_cuenta` varchar(30) NOT NULL,
  `saldo_inicial` decimal(38,2) NOT NULL,
  `tipo_cuenta` enum('AHORROS','CORRIENTE') NOT NULL,
  `cliente_id` bigint NOT NULL,
  PRIMARY KEY (`cuenta_id`),
  UNIQUE KEY `UK7h7mqvcau3mcl0mbrkdrt7fnh` (`numero_cuenta`),
  KEY `FK65yk2321jpusl3fk96lqehrli` (`cliente_id`),
  CONSTRAINT `FK65yk2321jpusl3fk96lqehrli` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`cliente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES (1,_binary '','478758',1425.00,'AHORROS',1),(2,_binary '','225487',200.00,'CORRIENTE',2),(3,_binary '','495878',150.00,'AHORROS',3),(4,_binary '','496825',0.00,'AHORROS',2),(5,_binary '','585545',1000.00,'CORRIENTE',1);
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
  `cuenta_id` bigint DEFAULT NULL,
  PRIMARY KEY (`movimiento_id`),
  KEY `FK4moe88hxuohcysas5h70mdc09` (`cuenta_id`),
  CONSTRAINT `FK4moe88hxuohcysas5h70mdc09` FOREIGN KEY (`cuenta_id`) REFERENCES `cuentas` (`cuenta_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos`
--

LOCK TABLES `movimientos` WRITE;
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
INSERT INTO `movimientos` VALUES (1,'2025-08-03',2000.00,'Retiro de 575',-575.00,1),(2,'2025-08-03',100.00,'Deposito de 600',100.00,2),(3,'2025-08-03',0.00,'Deposito de 150',150.00,3),(4,'2025-08-03',540.00,'Retiro de 540',-540.00,4);
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personas` (
  `persona_id` bigint NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `genero` enum('FEMENINO','MASCULINO') NOT NULL,
  `identificacion` varchar(25) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `telefono` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`persona_id`),
  UNIQUE KEY `UKdpxdn543sbyt8xkvsqha0l1li` (`identificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (1,'Otavalo sn y principal',35,'MASCULINO','1234567890','Jose Lema','098254785'),(2,'Amazonas y NNUU',33,'FEMENINO','0987654321','Marianela Montalvo','097548965'),(5,'13 junio y Equinoccial',19,'MASCULINO','33112333123','Juan Osorio','098874587');
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-03 23:24:57
