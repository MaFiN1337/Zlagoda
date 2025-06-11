-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: zlagoda
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_number` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL,
  PRIMARY KEY (`category_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_table`
--

DROP TABLE IF EXISTS `check_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check_table` (
  `check_number` varchar(10) NOT NULL,
  `id_employee` varchar(10) NOT NULL,
  `card_number` varchar(13) DEFAULT NULL,
  `print_date` datetime NOT NULL,
  `sum_total` decimal(13,4) NOT NULL,
  `vat` decimal(13,4) NOT NULL,
  PRIMARY KEY (`check_number`),
  KEY `FK_Check_Employee` (`id_employee`),
  KEY `FK_Check_Customer_Card` (`card_number`),
  CONSTRAINT `FK_Check_Customer_Card` FOREIGN KEY (`card_number`) REFERENCES `customer_card` (`card_number`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_Check_Employee` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id_employee`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `CHK_Check_Sum_Positive` CHECK ((`sum_total` >= 0)),
  CONSTRAINT `CHK_Check_VAT_Positive` CHECK ((`vat` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_table`
--

LOCK TABLES `check_table` WRITE;
/*!40000 ALTER TABLE `check_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `check_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_card`
--

DROP TABLE IF EXISTS `customer_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_card` (
  `card_number` varchar(13) NOT NULL,
  `cust_surname` varchar(50) NOT NULL,
  `cust_name` varchar(50) NOT NULL,
  `cust_patronymic` varchar(13) DEFAULT NULL,
  `phone_number` varchar(13) NOT NULL,
  `city` varchar(50) DEFAULT NULL,
  `street` varchar(50) DEFAULT NULL,
  `zip_code` varchar(9) DEFAULT NULL,
  `percent` int NOT NULL,
  PRIMARY KEY (`card_number`),
  CONSTRAINT `CHK_Customer_Percent_Positive` CHECK ((`percent` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_card`
--

LOCK TABLES `customer_card` WRITE;
/*!40000 ALTER TABLE `customer_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id_employee` varchar(10) NOT NULL,
  `empl_surname` varchar(50) NOT NULL,
  `empl_name` varchar(50) NOT NULL,
  `empl_patronymic` varchar(13) DEFAULT NULL,
  `empl_role` varchar(10) NOT NULL,
  `salary` decimal(13,4) NOT NULL,
  `date_of_birth` date NOT NULL,
  `date_of_start` date NOT NULL,
  `phone_number` varchar(13) NOT NULL,
  `city` varchar(50) DEFAULT NULL,
  `street` varchar(50) DEFAULT NULL,
  `zip_code` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`id_employee`),
  CONSTRAINT `CHK_Employee_Salary_Positive` CHECK ((`salary` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id_product` int NOT NULL AUTO_INCREMENT,
  `category_number` int NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `characteristics` varchar(100) NOT NULL,
  PRIMARY KEY (`id_product`),
  KEY `FK_Product_Category` (`category_number`),
  CONSTRAINT `FK_Product_Category` FOREIGN KEY (`category_number`) REFERENCES `category` (`category_number`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `UPC` varchar(12) NOT NULL,
  `check_number` varchar(10) NOT NULL,
  `product_number` int NOT NULL,
  `selling_price` decimal(13,4) NOT NULL,
  PRIMARY KEY (`UPC`,`check_number`),
  KEY `FK_Sale_Check` (`check_number`),
  CONSTRAINT `FK_Sale_Check` FOREIGN KEY (`check_number`) REFERENCES `check_table` (`check_number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Sale_Store_Product` FOREIGN KEY (`UPC`) REFERENCES `store_product` (`UPC`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CHK_Sale_Number_Positive` CHECK ((`product_number` >= 0)),
  CONSTRAINT `CHK_Sale_Price_Positive` CHECK ((`selling_price` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_product`
--

DROP TABLE IF EXISTS `store_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_product` (
  `UPC` varchar(12) NOT NULL,
  `UPC_prom` varchar(12) DEFAULT NULL,
  `id_product` int NOT NULL,
  `selling_price` decimal(13,4) NOT NULL,
  `products_number` int NOT NULL,
  `promotional_product` tinyint(1) NOT NULL,
  PRIMARY KEY (`UPC`),
  KEY `FK_Store_Product_Product` (`id_product`),
  KEY `FK_Store_Product_UPC_Prom` (`UPC_prom`),
  CONSTRAINT `FK_Store_Product_Product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_Store_Product_UPC_Prom` FOREIGN KEY (`UPC_prom`) REFERENCES `store_product` (`UPC`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `CHK_Store_Product_Number_Positive` CHECK ((`products_number` >= 0)),
  CONSTRAINT `CHK_Store_Product_Price_Positive` CHECK ((`selling_price` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_product`
--

LOCK TABLES `store_product` WRITE;
/*!40000 ALTER TABLE `store_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `store_product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-11 22:34:55
