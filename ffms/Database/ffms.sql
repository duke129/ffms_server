-- MySQL dump 10.13  Distrib 5.6.40, for Linux (x86_64)
--
-- Host: localhost    Database: ffms
-- ------------------------------------------------------
-- Server version	5.6.40

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
-- Table structure for table `Activity`
--

DROP TABLE IF EXISTS `Activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activityName` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Activity`
--

LOCK TABLES `Activity` WRITE;
/*!40000 ALTER TABLE `Activity` DISABLE KEYS */;
INSERT INTO `Activity` VALUES (1,'BasicInfo Update'),(2,'Demo'),(3,'Order');
/*!40000 ALTER TABLE `Activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ActivityTicketTypeMapping`
--

DROP TABLE IF EXISTS `ActivityTicketTypeMapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ActivityTicketTypeMapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activityId` int(11) DEFAULT NULL,
  `ticketTypeId` int(11) DEFAULT NULL,
  `levelId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ActivityTicketTypeMapping_1_idx` (`activityId`),
  KEY `fk_ActivityTicketTypeMapping_2_idx` (`ticketTypeId`),
  CONSTRAINT `fk_ActivityTicketTypeMapping_1` FOREIGN KEY (`activityId`) REFERENCES `Activity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ActivityTicketTypeMapping_2` FOREIGN KEY (`ticketTypeId`) REFERENCES `TicketType` (`idTicketType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ActivityTicketTypeMapping`
--

LOCK TABLES `ActivityTicketTypeMapping` WRITE;
/*!40000 ALTER TABLE `ActivityTicketTypeMapping` DISABLE KEYS */;
INSERT INTO `ActivityTicketTypeMapping` VALUES (1,1,1,NULL),(2,2,1,NULL),(3,3,1,NULL);
/*!40000 ALTER TABLE `ActivityTicketTypeMapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Area`
--

DROP TABLE IF EXISTS `Area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Area` (
  `idArea` bigint(20) NOT NULL,
  `areaName` varchar(100) NOT NULL,
  `branchId` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `createdByUserId` bigint(20) DEFAULT NULL,
  `createdOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifiedByUserId` bigint(20) DEFAULT NULL,
  `modifiedOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idArea`),
  KEY `fk_Area_1_idx` (`createdByUserId`),
  KEY `fk_Area_2_idx` (`modifiedByUserId`),
  KEY `fk_Area_3_idx` (`branchId`),
  KEY `fk_Area_4_idx` (`status`),
  CONSTRAINT `fk_Area_1` FOREIGN KEY (`createdByUserId`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Area_2` FOREIGN KEY (`modifiedByUserId`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Area_3` FOREIGN KEY (`branchId`) REFERENCES `Branch` (`idBranch`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Area_4` FOREIGN KEY (`status`) REFERENCES `Status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Area`
--

LOCK TABLES `Area` WRITE;
/*!40000 ALTER TABLE `Area` DISABLE KEYS */;
INSERT INTO `Area` VALUES (1,'hosapaliya',1,1,1,'2018-05-09 16:34:25',1,'2018-05-09 16:34:25',NULL);
/*!40000 ALTER TABLE `Area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Asset`
--

DROP TABLE IF EXISTS `Asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Asset` (
  `idAsset` bigint(20) NOT NULL AUTO_INCREMENT,
  `assetDescription` text,
  `serialNo` varchar(45) DEFAULT NULL,
  `createdDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `createdByUserId` bigint(20) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifiedByUserId` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `installationLat` varchar(45) DEFAULT NULL,
  `installationLong` varchar(45) DEFAULT NULL,
  `idAssetType` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAsset`),
  KEY `fk_Asset_1_idx` (`createdByUserId`),
  KEY `fk_Asset_2_idx` (`modifiedByUserId`),
  KEY `fk_Asset_3_idx` (`status`),
  KEY `fk_Asset_4_idx` (`idAssetType`),
  CONSTRAINT `fk_Asset_1` FOREIGN KEY (`createdByUserId`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Asset_2` FOREIGN KEY (`modifiedByUserId`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Asset_3` FOREIGN KEY (`status`) REFERENCES `Status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Asset_4` FOREIGN KEY (`idAssetType`) REFERENCES `AssetType` (`idAssetType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Asset`
--

LOCK TABLES `Asset` WRITE;
/*!40000 ALTER TABLE `Asset` DISABLE KEYS */;
INSERT INTO `Asset` VALUES (1,'Referigertor',NULL,'2018-05-09 16:34:25',1,'2018-05-09 16:34:25',1,1,'12.895073','77.644610',1);
/*!40000 ALTER TABLE `Asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AssetType`
--

DROP TABLE IF EXISTS `AssetType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AssetType` (
  `idAssetType` int(11) NOT NULL,
  `assetTypeDescription` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAssetType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AssetType`
--

LOCK TABLES `AssetType` WRITE;
/*!40000 ALTER TABLE `AssetType` DISABLE KEYS */;
INSERT INTO `AssetType` VALUES (1,' Single Door Referigertor'),(2,'Double Door Referigertor'),(3,'Split AC'),(4,'Normal AC');
/*!40000 ALTER TABLE `AssetType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Branch`
--

DROP TABLE IF EXISTS `Branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Branch` (
  `idBranch` bigint(20) NOT NULL,
  `branchName` varchar(100) NOT NULL,
  `cityId` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `createdByUserId` bigint(20) DEFAULT NULL,
  `createdOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifiedByUserId` bigint(20) DEFAULT NULL,
  `modifiedOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idBranch`),
  KEY `fk_Branch_1_idx` (`createdByUserId`),
  KEY `fk_Branch_2_idx` (`modifiedByUserId`),
  KEY `fk_Branch_4_idx` (`status`),
  KEY `fk_Branch_3_idx` (`cityId`),
  CONSTRAINT `fk_Branch_1` FOREIGN KEY (`createdByUserId`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Branch_2` FOREIGN KEY (`modifiedByUserId`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Branch_3` FOREIGN KEY (`cityId`) REFERENCES `City` (`idCity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Branch_4` FOREIGN KEY (`status`) REFERENCES `Status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Branch`
--

LOCK TABLES `Branch` WRITE;
/*!40000 ALTER TABLE `Branch` DISABLE KEYS */;
INSERT INTO `Branch` VALUES (1,'kodulu gate',1,1,1,'2018-05-09 16:34:25',1,'2018-05-09 16:34:25',NULL);
/*!40000 ALTER TABLE `Branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `City`
--

DROP TABLE IF EXISTS `City`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `City` (
  `idCity` bigint(20) NOT NULL,
  `cityName` varchar(100) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `createdByUserId` bigint(20) DEFAULT NULL,
  `createdOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifiedByUserId` bigint(20) DEFAULT NULL,
  `modifiedOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `code` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCity`),
  KEY `fk_City_1_idx` (`createdByUserId`),
  KEY `fk_City_2_idx` (`modifiedByUserId`),
  KEY `fk_City_3_idx` (`status`),
  CONSTRAINT `fk_City_1` FOREIGN KEY (`createdByUserId`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_City_2` FOREIGN KEY (`modifiedByUserId`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_City_3` FOREIGN KEY (`status`) REFERENCES `Status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `City`
--

LOCK TABLES `City` WRITE;
/*!40000 ALTER TABLE `City` DISABLE KEYS */;
INSERT INTO `City` VALUES (1,'Banglore',1,1,'2018-05-09 16:34:25',1,'2018-05-09 16:34:25',NULL,NULL);
/*!40000 ALTER TABLE `City` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `firstName` varchar(100) NOT NULL,
  `middleName` varchar(45) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `customerType` varchar(45) DEFAULT NULL,
  `mobileNumber` varchar(45) DEFAULT NULL,
  `alternativeMobileNo` varchar(45) DEFAULT NULL,
  `emailId` varchar(100) DEFAULT NULL,
  `communicationAdderss` longtext,
  `currentAddress` longtext,
  `branchId` bigint(20) DEFAULT NULL,
  `areaId` bigint(20) DEFAULT NULL,
  `cityId` bigint(20) DEFAULT NULL,
  `createdByUserId` bigint(20) DEFAULT NULL,
  `createdOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifiedByUserId` bigint(20) DEFAULT NULL,
  `modifiedOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Customer_1_idx` (`createdByUserId`),
  KEY `fk_Customer_2_idx` (`modifiedByUserId`),
  KEY `fk_Customer_3_idx` (`areaId`),
  KEY `fk_Customer_4_idx` (`branchId`),
  KEY `fk_Customer_6_idx` (`status`),
  KEY `fk_Customer_5_idx` (`cityId`),
  CONSTRAINT `fk_Customer_1` FOREIGN KEY (`createdByUserId`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_2` FOREIGN KEY (`modifiedByUserId`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_3` FOREIGN KEY (`areaId`) REFERENCES `Area` (`idArea`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_4` FOREIGN KEY (`branchId`) REFERENCES `Branch` (`idBranch`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_5` FOREIGN KEY (`cityId`) REFERENCES `City` (`idCity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_6` FOREIGN KEY (`status`) REFERENCES `Status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'Mr.','adr',NULL,'malla','sales','9738434213','9440009236','mallasrinivaskiran@gmail.com','{\"address1\":\"hsr layout\",\"address2\":\"\",\"landmark\":\"\",\"latitude\":\"\",\"longitude\":\"\",\"pincode\":\"\",\"apartmentName\":\"\"}',NULL,1,1,1,1,'2018-05-09 16:34:25',1,'2018-05-09 16:34:25',1),(2,'Mr.','noor',NULL,'basha',NULL,'9090909090','9090909090','noor@g.com','{\"address1\":\"hsr layout\",\"address2\":\"\",\"landmark\":\"\",\"latitude\":\"\",\"longitude\":\"\",\"pincode\":\"\",\"apartmentName\":\"\"}',NULL,1,1,1,1,NULL,1,NULL,1),(3,'Mr.','noor',NULL,'basha',NULL,'9090909090','9090909090','noor@g.com','{\"address1\":\"hsr layout\",\"address2\":\"\",\"landmark\":\"\",\"latitude\":\"\",\"longitude\":\"\",\"pincode\":\"\",\"apartmentName\":\"\"}',NULL,1,1,1,1,NULL,1,NULL,1),(4,'Mr.','swapnil',NULL,'swap',NULL,'9090909090','9090909090','swapnil@g.com','{\"address1\":\"hsr layout\",\"address2\":\"\",\"landmark\":\"\",\"latitude\":\"\",\"longitude\":\"\",\"pincode\":\"\",\"apartmentName\":\"\"}',NULL,1,1,1,1,NULL,1,NULL,1),(5,'Mr.','pawan',NULL,'paw',NULL,'8098909890','8098909890','8098909890@g.com','{\"address1\":\"vijetha gardinea hosapaliya kudulu gate 560012\",\"address2\":\"\",\"landmark\":\"\",\"latitude\":\"\",\"longitude\":\"\",\"pincode\":\"\",\"apartmentName\":\"\"}',NULL,1,1,1,1,'2018-06-08 16:28:53',1,'2018-06-08 16:28:53',1),(6,'Mr.','suraj',NULL,'singh',NULL,'8888888888','9999999999999','suraj@gamail.com','{\"address1\":\"patna patna1 patna3 789098\",\"address2\":\"\",\"landmark\":\"\",\"latitude\":\"\",\"longitude\":\"\",\"pincode\":\"\",\"apartmentName\":\"\"}',NULL,1,1,1,1,'2018-06-08 17:34:33',1,'2018-06-08 17:34:33',1),(7,'Mr.','rajulu',NULL,'m',NULL,'9390032077','944009236','rajulu@g.com','{\"address1\":\"vishaka A colony 6 th line dola building 560013\",\"address2\":\"\",\"landmark\":\"\",\"latitude\":\"\",\"longitude\":\"\",\"pincode\":\"\",\"apartmentName\":\"\"}',NULL,1,1,1,1,'2018-06-08 17:37:15',1,'2018-06-08 17:37:15',1),(8,'Mr.','test',NULL,'tester',NULL,'1234567890','098754321','test@g.com','{\"address1\":\"happiest minds madiwala hosur road 560068\",\"address2\":\"\",\"landmark\":\"\",\"latitude\":\"\",\"longitude\":\"\",\"pincode\":\"\",\"apartmentName\":\"\"}',NULL,1,1,1,1,'2018-06-08 17:43:48',1,'2018-06-08 17:43:48',1),(9,'Mr.','hjj',NULL,'jhjh',NULL,'9897867654','9887655467','jkfh@g.com','{\"address1\":\"ccdcdscs cdcdsc cdcdsccdcdscdc 560069\",\"address2\":\"\",\"landmark\":\"\",\"latitude\":\"\",\"longitude\":\"\",\"pincode\":\"\",\"apartmentName\":\"\"}',NULL,1,1,1,1,'2018-06-08 17:56:09',1,'2018-06-08 17:56:09',1),(10,'Mr.','pranav',NULL,'pran',NULL,'3435476687','3435476687','pranav@g.com','{\"address1\":\"fees fedf fefe 560013\",\"address2\":\"\",\"landmark\":\"\",\"latitude\":\"\",\"longitude\":\"\",\"pincode\":\"\",\"apartmentName\":\"\"}',NULL,1,1,1,1,'2018-06-08 19:07:37',1,'2018-06-08 19:07:37',1),(11,'Mrs','A',NULL,'A',NULL,'9876543210','9876543210','a@a.com','{\"address1\":\"Happiest Minds, Madiwala\",\"address2\":\"\",\"landmark\":\"\",\"latitude\":\"\",\"longitude\":\"\",\"pincode\":\"\",\"apartmentName\":\"\"}',NULL,1,1,1,1,'2018-06-11 17:34:07',1,'2018-06-11 17:34:07',1);
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CustomerAssetMapping`
--

DROP TABLE IF EXISTS `CustomerAssetMapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CustomerAssetMapping` (
  `idCustomer` bigint(20) NOT NULL,
  `idAsset` bigint(20) NOT NULL,
  PRIMARY KEY (`idCustomer`,`idAsset`),
  KEY `fk_CustomerAssetMapping_1_idx` (`idAsset`),
  CONSTRAINT `fk_CustomerAssetMapping_1` FOREIGN KEY (`idAsset`) REFERENCES `Asset` (`idAsset`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_CustomerAssetMapping_2` FOREIGN KEY (`idCustomer`) REFERENCES `Customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomerAssetMapping`
--

LOCK TABLES `CustomerAssetMapping` WRITE;
/*!40000 ALTER TABLE `CustomerAssetMapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `CustomerAssetMapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Group`
--

DROP TABLE IF EXISTS `Group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Group` (
  `idUserGroup` bigint(20) NOT NULL,
  `UserGroupDescription` varchar(45) DEFAULT NULL,
  `createdDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `createdByUserId` bigint(20) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifiedByUserId` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUserGroup`),
  KEY `fk_Group_1_idx` (`status`),
  CONSTRAINT `fk_Group_1` FOREIGN KEY (`status`) REFERENCES `Status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Group`
--

LOCK TABLES `Group` WRITE;
/*!40000 ALTER TABLE `Group` DISABLE KEYS */;
INSERT INTO `Group` VALUES (1,'Administrator','2018-05-09 16:34:25',NULL,'2018-05-09 16:34:25',NULL,NULL),(2,'Manager','2018-05-09 16:34:25',NULL,'2018-05-09 16:34:25',NULL,NULL),(3,'Lead','2018-05-09 16:34:25',NULL,'2018-05-09 16:34:25',NULL,NULL),(4,'ServiceEngineer','2018-05-09 16:34:25',NULL,'2018-05-09 16:34:25',NULL,NULL);
/*!40000 ALTER TABLE `Group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GroupRoleMapping`
--

DROP TABLE IF EXISTS `GroupRoleMapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GroupRoleMapping` (
  `idGroup` bigint(20) NOT NULL,
  `idRole` bigint(20) NOT NULL,
  PRIMARY KEY (`idGroup`,`idRole`),
  KEY `fk_GroupRoleMapping_2_idx` (`idRole`),
  CONSTRAINT `fk_GroupRoleMapping_1` FOREIGN KEY (`idGroup`) REFERENCES `Group` (`idUserGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_GroupRoleMapping_2` FOREIGN KEY (`idRole`) REFERENCES `Role` (`idRole`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GroupRoleMapping`
--

LOCK TABLES `GroupRoleMapping` WRITE;
/*!40000 ALTER TABLE `GroupRoleMapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `GroupRoleMapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `idOrder` bigint(20) NOT NULL,
  `ticketId` bigint(20) DEFAULT NULL,
  `productId` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idOrder`),
  KEY `fk_Order_1_idx` (`ticketId`),
  KEY `fk_Order_2_idx` (`productId`),
  CONSTRAINT `fk_Order_1` FOREIGN KEY (`ticketId`) REFERENCES `Ticket` (`idTicket`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_2` FOREIGN KEY (`productId`) REFERENCES `Product` (`idProduct`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `idProduct` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `imgPath` varchar(45) DEFAULT NULL,
  `assetId` bigint(20) DEFAULT NULL,
  `videoPath` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idProduct`),
  KEY `fk_Product_1_idx` (`assetId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductSpecification`
--

DROP TABLE IF EXISTS `ProductSpecification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductSpecification` (
  `idProductSpecification` bigint(20) NOT NULL,
  `productId` bigint(20) DEFAULT NULL,
  `propertyName` varchar(45) DEFAULT NULL,
  `propertyValue` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idProductSpecification`),
  KEY `fk_ProductSpecification_1_idx` (`productId`),
  CONSTRAINT `fk_ProductSpecification_1` FOREIGN KEY (`productId`) REFERENCES `Product` (`idProduct`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductSpecification`
--

LOCK TABLES `ProductSpecification` WRITE;
/*!40000 ALTER TABLE `ProductSpecification` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProductSpecification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role` (
  `idRole` bigint(20) NOT NULL,
  `RoleDescription` varchar(45) DEFAULT NULL,
  `createdDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `createdByUserId` bigint(20) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifiedByUserId` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRole`),
  UNIQUE KEY `idRole_UNIQUE` (`idRole`),
  KEY `fk_Role_1_idx` (`status`),
  CONSTRAINT `fk_Role_1` FOREIGN KEY (`status`) REFERENCES `Status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'Admin','2018-05-09 16:29:01',NULL,'2018-05-09 16:32:41',NULL,NULL),(2,'ServiceEngineer','2018-05-09 16:29:01',NULL,'2018-05-09 16:32:41',NULL,NULL),(3,'Manager','2018-05-09 16:29:01',NULL,'2018-05-09 16:32:41',NULL,NULL),(4,'Lead','2018-05-09 16:29:01',NULL,'2018-05-09 16:32:41',NULL,NULL),(5,'Electrician','2018-05-09 16:29:01',NULL,'2018-05-09 16:32:41',NULL,NULL),(6,'Technician','2018-05-09 16:29:01',NULL,'2018-05-09 16:32:41',NULL,NULL);
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Status`
--

DROP TABLE IF EXISTS `Status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Status` (
  `idStatus` int(11) NOT NULL,
  `statusDescription` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idStatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Status`
--

LOCK TABLES `Status` WRITE;
/*!40000 ALTER TABLE `Status` DISABLE KEYS */;
INSERT INTO `Status` VALUES (0,'Disabled'),(1,'Enabled');
/*!40000 ALTER TABLE `Status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ticket`
--

DROP TABLE IF EXISTS `Ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ticket` (
  `idTicket` bigint(20) NOT NULL AUTO_INCREMENT,
  `idCustomer` bigint(20) DEFAULT NULL,
  `tickectNo` varchar(45) DEFAULT NULL,
  `idAsset` bigint(20) DEFAULT NULL,
  `createdByUserId` bigint(20) DEFAULT NULL,
  `createdOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifiedByUserId` bigint(20) DEFAULT NULL,
  `modifiedOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `assignedTo` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `ticketTypeId` int(11) DEFAULT NULL,
  `ticketDescription` varchar(45) DEFAULT NULL,
  `preferedCallDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ticketSource` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTicket`),
  KEY `fk_Ticket_1_idx` (`idCustomer`),
  KEY `fk_Ticket_2_idx` (`idAsset`),
  KEY `fk_Ticket_3_idx` (`createdByUserId`),
  KEY `fk_Ticket_4_idx` (`modifiedByUserId`),
  KEY `fk_Ticket_5_idx` (`assignedTo`),
  KEY `fk_Ticket_6_idx` (`status`),
  KEY `fk_Ticket_7_idx` (`ticketTypeId`),
  CONSTRAINT `fk_Ticket_1` FOREIGN KEY (`idCustomer`) REFERENCES `Customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_2` FOREIGN KEY (`idAsset`) REFERENCES `Asset` (`idAsset`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_3` FOREIGN KEY (`createdByUserId`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_4` FOREIGN KEY (`modifiedByUserId`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_5` FOREIGN KEY (`assignedTo`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_6` FOREIGN KEY (`status`) REFERENCES `Status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_7` FOREIGN KEY (`ticketTypeId`) REFERENCES `TicketType` (`idTicketType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ticket`
--

LOCK TABLES `Ticket` WRITE;
/*!40000 ALTER TABLE `Ticket` DISABLE KEYS */;
INSERT INTO `Ticket` VALUES (1,1,NULL,1,1,'2018-05-09 16:34:25',1,'2018-05-09 16:34:25',1,1,2,'test repair','2018-07-03 17:49:12',NULL),(2,1,NULL,NULL,1,'2018-05-09 16:34:25',1,'2018-05-09 16:34:25',1,1,1,'test sales','2018-07-03 17:49:12',NULL),(3,3,NULL,NULL,1,'2018-05-09 16:34:25',1,'2018-05-09 16:34:25',1,1,1,'testing 3','2018-07-03 17:49:12',NULL),(4,4,NULL,NULL,1,'2018-05-09 16:34:25',1,'2018-05-09 16:34:25',1,1,1,'testing 4','2018-07-03 17:49:12',NULL),(5,5,NULL,NULL,1,'2018-06-08 16:28:53',1,'2018-06-08 16:28:53',1,1,1,'testing 5','2018-07-03 17:49:12',NULL),(6,6,NULL,NULL,1,'2018-06-08 17:34:33',1,'2018-06-08 17:34:33',1,1,1,'testing 6','2018-07-03 17:49:12',NULL),(7,7,NULL,NULL,1,'2018-06-08 17:37:15',1,'2018-06-08 17:37:15',1,1,1,'testing 7','2018-07-03 17:49:12',NULL),(8,8,NULL,NULL,1,'2018-06-08 17:43:48',1,'2018-06-08 17:43:48',1,1,1,'testing 8','2018-07-03 17:49:12',NULL),(9,9,NULL,NULL,1,'2018-06-08 17:56:09',1,'2018-06-08 17:56:09',1,1,1,'testing 9','2018-07-03 17:49:12',NULL),(10,10,NULL,NULL,1,'2018-06-08 19:07:37',1,'2018-06-08 19:07:37',1,1,1,'testing 10','2018-07-03 17:49:12',NULL),(11,11,NULL,NULL,1,'2018-06-11 17:34:07',1,'2018-06-11 17:34:07',1,1,1,'testing 11','2018-07-03 17:49:12',NULL);
/*!40000 ALTER TABLE `Ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TicketActivityLog`
--

DROP TABLE IF EXISTS `TicketActivityLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TicketActivityLog` (
  `id` bigint(20) NOT NULL,
  `ticketId` bigint(20) DEFAULT NULL,
  `activityId` int(11) DEFAULT NULL,
  `activityCreatedOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `activityDoneBy` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_TicketActivityLog_1_idx` (`ticketId`),
  KEY `fk_TicketActivityLog_2_idx` (`activityId`),
  KEY `fk_TicketActivityLog_3_idx` (`activityDoneBy`),
  CONSTRAINT `fk_TicketActivityLog_1` FOREIGN KEY (`ticketId`) REFERENCES `Ticket` (`idTicket`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TicketActivityLog_2` FOREIGN KEY (`activityId`) REFERENCES `Activity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TicketActivityLog_3` FOREIGN KEY (`activityDoneBy`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TicketActivityLog`
--

LOCK TABLES `TicketActivityLog` WRITE;
/*!40000 ALTER TABLE `TicketActivityLog` DISABLE KEYS */;
/*!40000 ALTER TABLE `TicketActivityLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TicketType`
--

DROP TABLE IF EXISTS `TicketType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TicketType` (
  `idTicketType` int(11) NOT NULL AUTO_INCREMENT,
  `ticketType` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTicketType`),
  KEY `fk_TicketType_1_idx` (`status`),
  CONSTRAINT `fk_TicketType_1` FOREIGN KEY (`status`) REFERENCES `Status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TicketType`
--

LOCK TABLES `TicketType` WRITE;
/*!40000 ALTER TABLE `TicketType` DISABLE KEYS */;
INSERT INTO `TicketType` VALUES (1,'Sales',1),(2,'Service Request',1);
/*!40000 ALTER TABLE `TicketType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `idUser` bigint(40) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `middleName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `password` varchar(200) NOT NULL,
  `status` int(11) NOT NULL,
  `createdByUserId` bigint(40) DEFAULT NULL,
  `createdDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifiedByUserId` bigint(40) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `idUser_UNIQUE` (`idUser`),
  KEY `fk_User_1_idx` (`status`),
  CONSTRAINT `fk_User_1` FOREIGN KEY (`status`) REFERENCES `Status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'user','user','user','user',1,1,'2018-05-09 16:34:25',1,'2018-05-09 16:34:25');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserGroupMapping`
--

DROP TABLE IF EXISTS `UserGroupMapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserGroupMapping` (
  `idUser` bigint(20) NOT NULL,
  `idGroup` bigint(20) NOT NULL,
  PRIMARY KEY (`idUser`,`idGroup`),
  KEY `fk_UserGroupMapping_2_idx` (`idGroup`),
  CONSTRAINT `fk_UserGroupMapping_1` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserGroupMapping_2` FOREIGN KEY (`idGroup`) REFERENCES `Group` (`idUserGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserGroupMapping`
--

LOCK TABLES `UserGroupMapping` WRITE;
/*!40000 ALTER TABLE `UserGroupMapping` DISABLE KEYS */;
INSERT INTO `UserGroupMapping` VALUES (1,1);
/*!40000 ALTER TABLE `UserGroupMapping` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-04 10:32:01
