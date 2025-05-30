-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ecom_db_local
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES (1,_binary '','People\'s Bank'),(2,_binary '','BOC Bank'),(3,_binary '','HNB Bank'),(4,_binary '','Commercial Bank'),(5,_binary '','Sampath Bank'),(6,_binary '','NSB Bank'),(7,_binary '','Amana Bank');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'Jaffna',_binary ''),(2,'Colombo',_binary '\0'),(3,'Colombo',_binary ''),(4,'Puttalam',_binary ''),(5,'Kurunagala',_binary ''),(6,'polanaruwa',_binary '');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Nike',_binary ''),(2,'Adidas',_binary ''),(3,'Puma',_binary ''),(4,'Skechers',_binary '\0'),(5,'Reebok',_binary ''),(6,'HP',_binary ''),(7,'Samsung',_binary ''),(8,'Moose',_binary ''),(9,'Carnage',_binary ''),(10,'Dr.Rashel',_binary ''),(11,'Vasline',_binary '');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `conditions`
--

LOCK TABLES `conditions` WRITE;
/*!40000 ALTER TABLE `conditions` DISABLE KEYS */;
INSERT INTO `conditions` VALUES (1,'New',_binary ''),(2,'Used',_binary ''),(3,'Refurbished',_binary ''),(4,'Used - Like New',_binary ''),(5,'Used - Very Good',_binary '');
/*!40000 ALTER TABLE `conditions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_tracking`
--

LOCK TABLES `order_tracking` WRITE;
/*!40000 ALTER TABLE `order_tracking` DISABLE KEYS */;
INSERT INTO `order_tracking` VALUES (1,_binary '\0','1Z999AA10123456784',1,1,3,1),(2,_binary '','1Z888WW1012345678',1,1,5,2),(3,_binary '\0','1Z777RR10123456778',3,2,4,1),(4,_binary '','1Z000TT10123456756',3,2,4,1);
/*!40000 ALTER TABLE `order_tracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2025-05-16 23:05:41','2025-05-20 12:00:00',_binary '\0',2,'2025-05-16 23:07:54',1,1,1,1,1),(2,'2025-05-16 23:06:19','2025-06-20 12:00:00',_binary '',1,'2025-05-16 23:07:03',2,2,2,2,2),(3,'2025-05-16 23:17:56','2025-07-20 12:00:00',_binary '',10,'2025-05-16 23:17:56',2,2,2,2,2),(5,'2025-05-20 21:25:18','2025-07-20 12:00:00',_binary '',5,'2025-05-20 21:25:18',5,3,1,2,1),(6,'2025-05-20 23:46:55','2025-07-20 12:00:00',_binary '',5,'2025-05-20 23:46:55',6,5,3,3,2),(7,'2025-05-20 23:47:49','2025-07-20 12:00:00',_binary '',5,'2025-05-20 23:47:49',9,8,2,1,1),(8,'2025-05-20 23:48:14','2025-07-20 12:00:00',_binary '',5,'2025-05-20 23:48:14',1,9,4,3,2),(11,'2025-05-20 23:49:18','2025-07-20 12:00:00',_binary '',5,'2025-05-20 23:49:18',5,5,1,3,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_reset_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,2590,'Pending',1),(2,290,'Paid',2),(3,5000,'Paid',3),(4,450,'Failed',3),(8,4700,'Paid',7);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `postage_partner`
--

LOCK TABLES `postage_partner` WRITE;
/*!40000 ALTER TABLE `postage_partner` DISABLE KEYS */;
INSERT INTO `postage_partner` VALUES (1,_binary '','FastShip',1),(2,_binary '\0','EcoFreight',1),(3,_binary '','GlobalPost',1),(4,_binary '','SpeedyLogistics',1),(5,_binary '\0','MailRiser',1),(6,_binary '','Pronto Lanka',1),(7,_binary '','DHL',1),(8,_binary '','Lanka Post',2),(9,_binary '','Domex',1),(10,_binary '','Farder Express',2);
/*!40000 ALTER TABLE `postage_partner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Blue','2025-05-16 20:18:15','Running shoes','Photo',_binary '',99.99,100,'S','Sneaker','2025-05-16 23:12:16',1,1,1,1),(2,'White','2025-05-16 20:21:24','Cotton button-down shirt','ShirtPhoto',_binary '',49.99,50,'M','Casual Shirt','2025-05-16 20:21:24',2,1,1,1),(3,'Black ','2025-05-20 19:40:22','Gaming Mouse with RGB','Photo',_binary '',9.99,50,'','Mouse','2025-05-20 19:40:22',1,1,1,2),(4,'White','2025-05-20 23:24:37','LED Light','Photo',_binary '',5.9,100,'','LED Light','2025-05-20 23:24:37',7,1,1,2),(5,'White','2025-05-20 23:26:16','White Sneaker','Photo',_binary '',100,100,'7','Sneaker','2025-05-20 23:26:16',1,1,1,3),(6,'Green','2025-05-20 23:28:02',' Alovera For oily Skin','Photo',_binary '',10,100,'','FaceWash','2025-05-20 23:28:02',10,1,1,1),(7,'Yellow','2025-05-20 23:29:37',' Boot','Photo',_binary '',100,100,'8','Football Boots','2025-05-20 23:29:37',2,1,1,4),(8,'Green','2025-05-20 23:34:40',' Kids Kit','Photo',_binary '',50,100,'m','Boys Baseball Kit','2025-05-20 23:34:40',9,1,1,4),(9,'White','2025-05-20 23:37:55','Cotton Short Summer Style','Photo',_binary '',20,100,'L','Short','2025-05-20 23:37:55',8,1,1,2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,_binary '','Clothing',1,NULL),(2,_binary '','Jewels',1,NULL),(3,_binary '','Kids',1,NULL),(4,_binary '','Electronics',1,NULL),(5,_binary '','Home',1,NULL),(6,_binary '','Entertainment',1,NULL),(7,_binary '','Sports',1,NULL),(8,_binary '','Beauty Items',1,NULL),(9,_binary '','Books & Stationery',1,NULL),(10,_binary '','Others',1,NULL),(11,_binary '','Women',2,1),(12,_binary '','Men',2,1),(13,_binary '','Unisex',2,1),(14,_binary '','Others',2,1);
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_sub_category`
--

LOCK TABLES `product_sub_category` WRITE;
/*!40000 ALTER TABLE `product_sub_category` DISABLE KEYS */;
INSERT INTO `product_sub_category` VALUES (1,_binary '','Clothing',1),(2,_binary '','Electronics',1),(3,_binary '\0','Footwear',1),(4,_binary '','Lights',4),(5,_binary '','Shoes',3),(6,_binary '','Boys',4),(7,_binary '','Skin Care',8),(8,_binary '','Sport Shoes',7),(9,_binary '','Shorts',6),(10,_binary '','Casual Short',5);
/*!40000 ALTER TABLE `product_sub_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shipping_address`
--

LOCK TABLES `shipping_address` WRITE;
/*!40000 ALTER TABLE `shipping_address` DISABLE KEYS */;
INSERT INTO `shipping_address` VALUES (1,'456 Elm St, Springfield',_binary '\0','9876543210',1,'',NULL,'','',''),(2,'123 Super St, London',_binary '','0765947337',1,'',NULL,'','',''),(3,'jaffna',_binary '','0765947337',1,'',NULL,'','',''),(4,'Colombo',_binary '','0764545761',2,'',NULL,'','',''),(5,'Puttalam',_binary '','0767489247',2,'',NULL,'','',''),(6,'Kurunagala',_binary '','0762778957',1,'',NULL,'','',''),(7,'Polanaruwa',_binary '','0762235657',1,'',NULL,'','','');
/*!40000 ALTER TABLE `shipping_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,_binary '','Available'),(2,_binary '','Out of Stock'),(3,_binary '','Discontinued'),(4,_binary '','Pre-Order'),(5,_binary '','Sold Out');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Jaffna','2025-05-09 18:05:19','skbavi61@gmail.com','Bavithragithan',_binary '','Kuganesan','0756089312',NULL,'$2a$10$lQ4C/Vuj5UNRUZTLU9TrIuNqrq/S1TBKmRJpvMB.c9BqDYpjadf.W',1,'0756089312'),(2,'Jaffna','2025-05-09 18:06:37','lathusanthurairajah@codearson.com','Lathusan',_binary '','Thurairajah','+447440641608',NULL,'$2a$10$vGkEHcuxK49rIaDk9SUHWu74OlevG8JBv0Vz9RQaomFJ7xmLIUVk6',1,'+447440641608');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_bank_details`
--

LOCK TABLES `user_bank_details` WRITE;
/*!40000 ALTER TABLE `user_bank_details` DISABLE KEYS */;
INSERT INTO `user_bank_details` VALUES (3,'Bavithragithan','7475895000520155','2025-05-15 18:48:39',_binary '',1,1,1),(4,'lathusanthurairajah','747589500015736','2025-05-20 19:25:39',_binary '',2,1,2),(5,'Asjath','7475568300526834','2025-05-18 18:48:39',_binary '',5,3,1),(6,'Rifas','7475895245683615','2025-05-15 18:48:39',_binary '',1,1,1),(7,'Wazeem','7475895215738563','2025-05-15 18:48:39',_binary '',1,1,1);
/*!40000 ALTER TABLE `user_bank_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_logs`
--

LOCK TABLES `user_logs` WRITE;
/*!40000 ALTER TABLE `user_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,_binary '','ADMIN'),(2,_binary '','MANAGER'),(3,_binary '','USER');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-30 16:23:17
