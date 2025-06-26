-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ecom_db_local
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
-- Dumping data for table `email_verification_token`
--

LOCK TABLES `email_verification_token` WRITE;
/*!40000 ALTER TABLE `email_verification_token` DISABLE KEYS */;
INSERT INTO `email_verification_token` VALUES (1,'rifasmhd3333@gmail.com','2025-06-12 01:59:49','714891');
/*!40000 ALTER TABLE `email_verification_token` ENABLE KEYS */;
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
INSERT INTO `password_reset_token` VALUES (1,'2025-06-12 15:46:02','4XZQ3O',1),(2,'2025-06-12 15:46:19','ILUL6K',4),(3,'2025-06-12 15:57:29','MOWOET',4),(4,'2025-06-12 15:57:59','JDL37I',4),(5,'2025-06-12 16:24:22','O0ZJIK',4);
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
INSERT INTO `product` VALUES (1,'Blue','2025-05-16 20:18:15','Running shoes','Photo',_binary '',99.99,100,'S','Sneaker','2025-06-03 18:00:00',1,1,1,1),(2,'White','2025-05-16 20:21:24','Cotton button-down shirt','ShirtPhoto',_binary '',49.99,50,'M','Casual Shirt','2025-05-16 20:21:24',2,1,1,1),(3,'Black ','2025-05-20 19:40:22','Gaming Mouse with RGB','Photo',_binary '',9.99,50,'','Mouse','2025-05-20 19:40:22',1,1,1,2),(4,'White','2025-05-20 23:24:37','LED Light','Photo',_binary '',5.9,100,'','LED Light','2025-05-20 23:24:37',7,1,1,2),(5,'White','2025-05-20 23:26:16','White Sneaker','Photo',_binary '',100,100,'7','Sneaker','2025-05-20 23:26:16',1,1,1,3),(6,'Green','2025-05-20 23:28:02',' Alovera For oily Skin','Photo',_binary '',10,100,'','FaceWash','2025-05-20 23:28:02',10,1,1,1),(7,'Yellow','2025-05-20 23:29:37',' Boot','Photo',_binary '',100,100,'8','Football Boots','2025-05-20 23:29:37',2,1,1,4),(8,'Green','2025-05-20 23:34:40',' Kids Kit','Photo',_binary '',50,100,'m','Boys Baseball Kit','2025-05-20 23:34:40',9,1,1,4),(9,'White','2025-05-20 23:37:55','Cotton Short Summer Style','Photo',_binary '',20,100,'L','Short','2025-05-20 23:37:55',8,1,1,2),(10,NULL,'2025-06-03 18:03:04','8GB DDR4 RAM','Ram Image',_binary '',12999,2,NULL,'DDR4 RAM','2025-06-03 18:03:04',1,1,1,146);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,_binary '','Clothing',1,NULL),(2,_binary '','Jewels',1,NULL),(3,_binary '','Kids',1,NULL),(4,_binary '','Electronics',1,NULL),(5,_binary '','Home',1,NULL),(6,_binary '','Entertainment',1,NULL),(7,_binary '','Sports',1,NULL),(8,_binary '','Beauty Items',1,NULL),(9,_binary '','Books & Stationery',1,NULL),(10,_binary '','Others',1,NULL),(11,_binary '','Women',2,1),(12,_binary '','Men',2,1),(13,_binary '','Unisex',2,1),(14,_binary '','Others',2,1),(15,_binary '','Tops',3,11),(16,_binary '','T-Shirts',4,15),(17,_binary '','Blouses',4,15),(18,_binary '','Crop Tops',4,15),(19,_binary '','Kurtis',4,15),(20,_binary '','Bottoms',3,11),(21,_binary '','Jeans',4,20),(22,_binary '','Skirts',4,20),(23,_binary '','Leggings',4,20),(24,_binary '','Saree Petticoats',4,20),(25,_binary '','Dresses',3,11),(26,_binary '','Maxi Dresses',4,25),(27,_binary '','Midi Dresses',4,25),(28,_binary '','Frocks',4,25),(29,_binary '','Ethnic Wear',3,11),(30,_binary '','Sarees',4,29),(31,_binary '','Salwars',4,29),(32,_binary '','Lehenga',4,29),(33,_binary '','Churidars',4,29),(34,_binary '','Outerwear',3,11),(35,_binary '','Sweaters',4,34),(36,_binary '','Jackets',4,34),(37,_binary '','Hoodies',4,34),(38,_binary '','Lingerie & Sleepwear',3,11),(39,_binary '','Bras',4,38),(40,_binary '','Nightwear',4,38),(41,_binary '','Innerwear',4,38),(42,_binary '','Shoes',3,11),(43,_binary '','Sandals',4,42),(44,_binary '','Heels',4,42),(45,_binary '','Flats',4,42),(46,_binary '','Sneakers',4,42),(47,_binary '','Accessories',3,11),(48,_binary '','Scarves',4,47),(49,_binary '','Handbags',4,47),(50,_binary '','Belts',4,47),(51,_binary '','Tops',3,12),(52,_binary '','T-Shirts',4,51),(53,_binary '','Shirts',4,51),(54,_binary '','Polos',4,51),(55,_binary '','Bottoms',3,12),(56,_binary '','Jeans',4,55),(57,_binary '','Trousers',4,55),(58,_binary '','Shorts',4,55),(59,_binary '','Sarongs',4,55),(60,_binary '','Outerwear',3,12),(61,_binary '','Jackets',4,60),(62,_binary '','Blazers',4,60),(63,_binary '','Hoodies',4,60),(64,_binary '','Traditional Wear',3,12),(65,_binary '','Sarongs',4,64),(66,_binary '','Kurtas',4,64),(67,_binary '','Vettis',4,64),(68,_binary '','Thalappa',4,64),(69,_binary '','Shoes',3,12),(70,_binary '','Formal',4,69),(71,_binary '','Casual',4,69),(72,_binary '','Sports',4,69),(73,_binary '','Innerwear & Sleepwear',3,12),(74,_binary '','Vests',4,73),(75,_binary '','Boxers',4,73),(76,_binary '','Pyjamas',4,73),(77,_binary '','School Uniforms',3,13),(78,_binary '','Religious Wear',3,13),(79,_binary '','Sportswear',3,13),(80,_binary '','Gold Jewelry',2,2),(81,_binary '','Rings',3,80),(82,_binary '','Necklaces',3,80),(83,_binary '','Bangles',3,80),(84,_binary '','Earrings',3,80),(85,_binary '','Silver Jewelry',2,2),(86,_binary '','Necklaces',3,85),(87,_binary '','Anklets',3,85),(88,_binary '','Toe Rings',3,85),(89,_binary '','Costume Jewelry',2,2),(90,_binary '','Artificial Sets',3,89),(91,_binary '','Imitation',3,89),(92,_binary '','Clip-ons',3,89),(93,_binary '','Others',2,2),(94,_binary '','Baby (0–3 years)',2,3),(95,_binary '','Clothing',3,94),(96,_binary '','Onesies',4,95),(97,_binary '','Rompers',4,95),(98,_binary '','Dresses',4,95),(99,_binary '','Essentials',3,94),(100,_binary '','Diapers',4,99),(101,_binary '','Feeding Bottles',4,99),(102,_binary '','Bibs',4,99),(103,_binary '','Gear',3,94),(104,_binary '','Cribs',4,103),(105,_binary '','Walkers',4,103),(106,_binary '','Car Seats',4,103),(107,_binary '','Kids (3–12 years)',2,3),(108,_binary '','Boys',3,107),(109,_binary '','T-Shirts',4,108),(110,_binary '','Shorts',4,108),(111,_binary '','Jeans',4,108),(112,_binary '','Girls',3,107),(113,_binary '','Frocks',4,112),(114,_binary '','Tops',4,112),(115,_binary '','Skirts',4,112),(116,_binary '','School',3,107),(117,_binary '','Uniforms',4,116),(118,_binary '','Bags',4,116),(119,_binary '','Shoes',4,116),(120,_binary '','Toys',3,107),(121,_binary '','Educational Toys',4,120),(122,_binary '','Soft Toys',4,120),(123,_binary '','Action Figures',4,120),(124,_binary '','Accessories',2,3),(125,_binary '','Hair Bands',3,124),(126,_binary '','Socks',3,124),(127,_binary '','Caps',3,124),(128,_binary '','Others',2,3),(129,_binary '','Mobile',2,4),(130,_binary '','Smartphones',3,129),(131,_binary '','Samsung',4,130),(132,_binary '','iPhone',4,130),(133,_binary '','Oppo',4,130),(134,_binary '','Xiaomi',4,130),(135,_binary '','Huawei',4,130),(136,_binary '','Accessories',3,129),(137,_binary '','Chargers',4,136),(138,_binary '','Covers',4,136),(139,_binary '','Earphones',4,136),(140,_binary '','Computers',2,4),(141,_binary '','Laptops',3,140),(142,_binary '','Monitors',3,140),(143,_binary '','Keyboards',3,140),(144,_binary '','Mice',3,140),(145,_binary '','Parts',3,140),(146,_binary '','RAM',4,145),(147,_binary '','SSD',4,145),(148,_binary '','Graphics Cards',4,145),(149,_binary '','TVs & Appliances',2,4),(150,_binary '','TVs',3,149),(151,_binary '','Radios',3,149),(152,_binary '','Fridges',3,149),(153,_binary '','Washing Machines',3,149),(154,_binary '','Fans',3,149),(155,_binary '','Others',2,4),(156,_binary '','Furniture',2,5),(157,_binary '','Beds',3,156),(158,_binary '','Sofas',3,156),(159,_binary '','Cupboards',3,156),(160,_binary '','Tables',3,156),(161,_binary '','Kitchen',2,5),(162,_binary '','Cookware',3,161),(163,_binary '','Pots',4,162),(164,_binary '','Pans',4,162),(165,_binary '','Pressure Cookers',4,162),(166,_binary '','Utensils',3,161),(167,_binary '','Plates',4,166),(168,_binary '','Spoons',4,166),(169,_binary '','Serving Bowls',4,166),(170,_binary '','Storage',3,161),(171,_binary '','Tupperware',4,170),(172,_binary '','Jars',4,170),(173,_binary '','Decor',2,5),(174,_binary '','Curtains',3,173),(175,_binary '','Rugs',3,173),(176,_binary '','Wall Art',3,173),(177,_binary '','Lamps',3,173),(178,_binary '','Tools',2,5),(179,_binary '','Brooms',3,178),(180,_binary '','Buckets',3,178),(181,_binary '','Mops',3,178),(182,_binary '','Cleaning Kits',3,178),(183,_binary '','Others',2,5),(184,_binary '','Books',2,6),(185,_binary '','Novels',3,184),(186,_binary '','Sinhala Novels',4,185),(187,_binary '','Tamil Novels',4,185),(188,_binary '','English Novels',4,185),(189,_binary '','Religious Books',3,184),(190,_binary '','Bible',4,189),(191,_binary '','Quran',4,189),(192,_binary '','Bhagavad Gita',4,189),(193,_binary '','Comics',3,184),(194,_binary '','Magazines',3,184),(195,_binary '','Music',2,6),(196,_binary '','Audio CDs',3,195),(197,_binary '','Cassette Tapes',3,195),(198,_binary '','Instruments',3,195),(199,_binary '','Guitar',4,198),(200,_binary '','Keyboard',4,198),(201,_binary '','Tabla',4,198),(202,_binary '','Movies',2,6),(203,_binary '','DVDs',3,202),(204,_binary '','Blu-rays',3,202),(205,_binary '','USB Collections',3,202),(206,_binary '','Games',2,6),(207,_binary '','Console Games',3,206),(208,_binary '','PS4',4,207),(209,_binary '','Xbox',4,207),(210,_binary '','PC Games',3,206),(211,_binary '','Board Games',3,206),(212,_binary '','Others',2,6),(213,_binary '','Equipment',2,7),(214,_binary '','Cricket',3,213),(215,_binary '','Bats',4,214),(216,_binary '','Pads',4,214),(217,_binary '','Balls',4,214),(218,_binary '','Badminton',3,213),(219,_binary '','Rackets',4,218),(220,_binary '','Shuttlecocks',4,218),(221,_binary '','Football',3,213),(222,_binary '','Balls',4,221),(223,_binary '','Goal Nets',4,221),(224,_binary '','Fitness',3,213),(225,_binary '','Dumbbells',4,224),(226,_binary '','Resistance Bands',4,224),(227,_binary '','Clothing',2,7),(228,_binary '','Jerseys',3,227),(229,_binary '','Shorts',3,227),(230,_binary '','Tracksuits',3,227),(231,_binary '','Footwear',2,7),(232,_binary '','Studs',3,231),(233,_binary '','Trainers',3,231),(234,_binary '','Spikes',3,231),(235,_binary '','Outdoor Gear',2,7),(236,_binary '','Tents',3,235),(237,_binary '','Water Bottles',3,235),(238,_binary '','Camping Bags',3,235),(239,_binary '','Others',2,7),(240,_binary '','Skincare',2,8),(241,_binary '','Face Wash',3,240),(242,_binary '','Moisturizers',3,240),(243,_binary '','Serums',3,240),(244,_binary '','Scrubs',3,240),(245,_binary '','Makeup',2,8),(246,_binary '','Foundation',3,245),(247,_binary '','Lipstick',3,245),(248,_binary '','Eyeliner',3,245),(249,_binary '','Compact Powder',3,245),(250,_binary '','Haircare',2,8),(251,_binary '','Shampoo',3,250),(252,_binary '','Conditioner',3,250),(253,_binary '','Hair Oil',3,250),(254,_binary '','Serum',3,250),(255,_binary '','Grooming Tools',2,8),(256,_binary '','Trimmers',3,255),(257,_binary '','Straighteners',3,255),(258,_binary '','Hair Dryers',3,255),(259,_binary '','Fragrance',2,8),(260,_binary '','Perfume',3,259),(261,_binary '','Deodorants',3,259),(262,_binary '','Body Mists',3,259),(263,_binary '','Others',2,8),(264,_binary '','Academic',2,9),(265,_binary '','School Books',3,264),(266,_binary '','University Books',3,264),(267,_binary '','Past Papers',3,264),(268,_binary '','Reading',2,9),(269,_binary '','Novels',3,268),(270,_binary '','Magazines',3,268),(271,_binary '','Religious Texts',3,268),(272,_binary '','Stationery',2,9),(273,_binary '','Notebooks',3,272),(274,_binary '','Pens',3,272),(275,_binary '','Files',3,272),(276,_binary '','Art Supplies',3,272),(277,_binary '','Office Supplies',2,9),(278,_binary '','Calculators',3,277),(279,_binary '','Folders',3,277),(280,_binary '','Whiteboards',3,277),(281,_binary '','Others',2,9),(282,_binary '','Festival & Gift Items',2,10),(283,_binary '','Diyas',3,282),(284,_binary '','Party Decorations',3,282),(285,_binary '','Gift Packs',3,282),(286,_binary '','Collectibles',2,10),(287,_binary '','Coins',3,286),(288,_binary '','Stamps',3,286),(289,_binary '','Vintage Items',3,286),(290,_binary '','Handmade Items',2,10),(291,_binary '','Crafts',3,290),(292,_binary '','Handwoven Items',3,290),(293,_binary '','Homemade Decor',3,290),(294,_binary '','Others',2,10);
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_category_level1`
--

LOCK TABLES `product_category_level1` WRITE;
/*!40000 ALTER TABLE `product_category_level1` DISABLE KEYS */;
INSERT INTO `product_category_level1` VALUES (1,_binary '','Clothing'),(2,_binary '','Jewels'),(3,_binary '','Kids'),(4,_binary '','Electronics'),(5,_binary '','Home'),(6,_binary '','Entertainment'),(7,_binary '','Sports'),(8,_binary '','Beauty Items'),(9,_binary '','Books & Stationery'),(10,_binary '','Others');
/*!40000 ALTER TABLE `product_category_level1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_category_level2`
--

LOCK TABLES `product_category_level2` WRITE;
/*!40000 ALTER TABLE `product_category_level2` DISABLE KEYS */;
INSERT INTO `product_category_level2` VALUES (11,_binary '','Women',1),(12,_binary '','Men',1),(13,_binary '','Unisex',1),(14,_binary '','Others',1),(80,_binary '','Gold Jewelry',2),(85,_binary '','Silver Jewelry',2),(89,_binary '','Costume Jewelry',2),(93,_binary '','Others',2),(94,_binary '','Baby (0–3 years)',3),(107,_binary '','Kids (3–12 years)',3),(124,_binary '','Accessories',3),(128,_binary '','Others',3),(129,_binary '','Mobile',4),(140,_binary '','Computers',4),(149,_binary '','TVs & Appliances',4),(155,_binary '','Others',4),(156,_binary '','Furniture',5),(161,_binary '','Kitchen',5),(173,_binary '','Decor',5),(178,_binary '','Tools',5),(183,_binary '','Others',5),(184,_binary '','Books',6),(195,_binary '','Music',6),(202,_binary '','Movies',6),(206,_binary '','Games',6),(212,_binary '','Others',6),(213,_binary '','Equipment',7),(227,_binary '','Clothing',7),(231,_binary '','Footwear',7),(235,_binary '','Outdoor Gear',7),(239,_binary '','Others',7),(240,_binary '','Skincare',8),(245,_binary '','Makeup',8),(250,_binary '','Haircare',8),(255,_binary '','Grooming Tools',8),(259,_binary '','Fragrance',8),(263,_binary '','Others',8),(264,_binary '','Academic',9),(268,_binary '','Reading',9),(272,_binary '','Stationery',9),(277,_binary '','Office Supplies',9),(281,_binary '','Others',9),(282,_binary '','Festival & Gift Items',10),(286,_binary '','Collectibles',10),(290,_binary '','Handmade Items',10),(294,_binary '','Others',10);
/*!40000 ALTER TABLE `product_category_level2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_category_level3`
--

LOCK TABLES `product_category_level3` WRITE;
/*!40000 ALTER TABLE `product_category_level3` DISABLE KEYS */;
INSERT INTO `product_category_level3` VALUES (15,_binary '','Tops',11),(20,_binary '','Bottoms',11),(25,_binary '','Dresses',11),(29,_binary '','Ethnic Wear',11),(34,_binary '','Outerwear',11),(38,_binary '','Lingerie & Sleepwear',11),(42,_binary '','Shoes',11),(47,_binary '','Accessories',11),(51,_binary '','Tops',12),(55,_binary '','Bottoms',12),(60,_binary '','Outerwear',12),(64,_binary '','Traditional Wear',12),(69,_binary '','Shoes',12),(73,_binary '','Innerwear & Sleepwear',12),(77,_binary '','School Uniforms',13),(78,_binary '','Religious Wear',13),(79,_binary '','Sportswear',13),(81,_binary '','Rings',80),(82,_binary '','Necklaces',80),(83,_binary '','Bangles',80),(84,_binary '','Earrings',80),(86,_binary '','Necklaces',85),(87,_binary '','Anklets',85),(88,_binary '','Toe Rings',85),(90,_binary '','Artificial Sets',89),(91,_binary '','Imitation',89),(92,_binary '','Clip-ons',89),(95,_binary '','Clothing',94),(99,_binary '','Essentials',94),(103,_binary '','Gear',94),(108,_binary '','Boys',107),(112,_binary '','Girls',107),(116,_binary '','School',107),(120,_binary '','Toys',107),(125,_binary '','Hair Bands',124),(126,_binary '','Socks',124),(127,_binary '','Caps',124),(130,_binary '','Smartphones',129),(136,_binary '','Accessories',129),(141,_binary '','Laptops',140),(142,_binary '','Monitors',140),(143,_binary '','Keyboards',140),(144,_binary '','Mice',140),(145,_binary '','Parts',140),(150,_binary '','TVs',149),(151,_binary '','Radios',149),(152,_binary '','Fridges',149),(153,_binary '','Washing Machines',149),(154,_binary '','Fans',149),(157,_binary '','Beds',156),(158,_binary '','Sofas',156),(159,_binary '','Cupboards',156),(160,_binary '','Tables',156),(162,_binary '','Cookware',161),(166,_binary '','Utensils',161),(170,_binary '','Storage',161),(174,_binary '','Curtains',173),(175,_binary '','Rugs',173),(176,_binary '','Wall Art',173),(177,_binary '','Lamps',173),(179,_binary '','Brooms',178),(180,_binary '','Buckets',178),(181,_binary '','Mops',178),(182,_binary '','Cleaning Kits',178),(185,_binary '','Novels',184),(189,_binary '','Religious Books',184),(193,_binary '','Comics',184),(194,_binary '','Magazines',184),(196,_binary '','Audio CDs',195),(197,_binary '','Cassette Tapes',195),(198,_binary '','Instruments',195),(203,_binary '','DVDs',202),(204,_binary '','Blu-rays',202),(205,_binary '','USB Collections',202),(207,_binary '','Console Games',206),(210,_binary '','PC Games',206),(211,_binary '','Board Games',206),(214,_binary '','Cricket',213),(218,_binary '','Badminton',213),(221,_binary '','Football',213),(224,_binary '','Fitness',213),(228,_binary '','Jerseys',227),(229,_binary '','Shorts',227),(230,_binary '','Tracksuits',227),(232,_binary '','Studs',231),(233,_binary '','Trainers',231),(234,_binary '','Spikes',231),(236,_binary '','Tents',235),(237,_binary '','Water Bottles',235),(238,_binary '','Camping Bags',235),(241,_binary '','Face Wash',240),(242,_binary '','Moisturizers',240),(243,_binary '','Serums',240),(244,_binary '','Scrubs',240),(246,_binary '','Foundation',245),(247,_binary '','Lipstick',245),(248,_binary '','Eyeliner',245),(249,_binary '','Compact Powder',245),(251,_binary '','Shampoo',250),(252,_binary '','Conditioner',250),(253,_binary '','Hair Oil',250),(254,_binary '','Serum',250),(256,_binary '','Trimmers',255),(257,_binary '','Straighteners',255),(258,_binary '','Hair Dryers',255),(260,_binary '','Perfume',259),(261,_binary '','Deodorants',259),(262,_binary '','Body Mists',259),(265,_binary '','School Books',264),(266,_binary '','University Books',264),(267,_binary '','Past Papers',264),(269,_binary '','Novels',268),(270,_binary '','Magazines',268),(271,_binary '','Religious Texts',268),(273,_binary '','Notebooks',272),(274,_binary '','Pens',272),(275,_binary '','Files',272),(276,_binary '','Art Supplies',272),(278,_binary '','Calculators',277),(279,_binary '','Folders',277),(280,_binary '','Whiteboards',277),(283,_binary '','Diyas',282),(284,_binary '','Party Decorations',282),(285,_binary '','Gift Packs',282),(287,_binary '','Coins',286),(288,_binary '','Stamps',286),(289,_binary '','Vintage Items',286),(291,_binary '','Crafts',290),(292,_binary '','Handwoven Items',290),(293,_binary '','Homemade Decor',290);
/*!40000 ALTER TABLE `product_category_level3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_category_level4`
--

LOCK TABLES `product_category_level4` WRITE;
/*!40000 ALTER TABLE `product_category_level4` DISABLE KEYS */;
INSERT INTO `product_category_level4` VALUES (16,_binary '','T-Shirts',15),(17,_binary '','Blouses',15),(18,_binary '','Crop Tops',15),(19,_binary '','Kurtis',15),(21,_binary '','Jeans',20),(22,_binary '','Skirts',20),(23,_binary '','Leggings',20),(24,_binary '','Saree Petticoats',20),(26,_binary '','Maxi Dresses',25),(27,_binary '','Midi Dresses',25),(28,_binary '','Frocks',25),(30,_binary '','Sarees',29),(31,_binary '','Salwars',29),(32,_binary '','Lehenga',29),(33,_binary '','Churidars',29),(35,_binary '','Sweaters',34),(36,_binary '','Jackets',34),(37,_binary '','Hoodies',34),(39,_binary '','Bras',38),(40,_binary '','Nightwear',38),(41,_binary '','Innerwear',38),(43,_binary '','Sandals',42),(44,_binary '','Heels',42),(45,_binary '','Flats',42),(46,_binary '','Sneakers',42),(48,_binary '','Scarves',47),(49,_binary '','Handbags',47),(50,_binary '','Belts',47),(52,_binary '','T-Shirts',51),(53,_binary '','Shirts',51),(54,_binary '','Polos',51),(56,_binary '','Jeans',55),(57,_binary '','Trousers',55),(58,_binary '','Shorts',55),(59,_binary '','Sarongs',55),(61,_binary '','Jackets',60),(62,_binary '','Blazers',60),(63,_binary '','Hoodies',60),(65,_binary '','Sarongs',64),(66,_binary '','Kurtas',64),(67,_binary '','Vettis',64),(68,_binary '','Thalappa',64),(70,_binary '','Formal',69),(71,_binary '','Casual',69),(72,_binary '','Sports',69),(74,_binary '','Vests',73),(75,_binary '','Boxers',73),(76,_binary '','Pyjamas',73),(96,_binary '','Onesies',95),(97,_binary '','Rompers',95),(98,_binary '','Dresses',95),(100,_binary '','Diapers',99),(101,_binary '','Feeding Bottles',99),(102,_binary '','Bibs',99),(104,_binary '','Cribs',103),(105,_binary '','Walkers',103),(106,_binary '','Car Seats',103),(109,_binary '','T-Shirts',108),(110,_binary '','Shorts',108),(111,_binary '','Jeans',108),(113,_binary '','Frocks',112),(114,_binary '','Tops',112),(115,_binary '','Skirts',112),(117,_binary '','Uniforms',116),(118,_binary '','Bags',116),(119,_binary '','Shoes',116),(121,_binary '','Educational Toys',120),(122,_binary '','Soft Toys',120),(123,_binary '','Action Figures',120),(131,_binary '','Samsung',130),(132,_binary '','iPhone',130),(133,_binary '','Oppo',130),(134,_binary '','Xiaomi',130),(135,_binary '','Huawei',130),(137,_binary '','Chargers',136),(138,_binary '','Covers',136),(139,_binary '','Earphones',136),(146,_binary '','RAM',145),(147,_binary '','SSD',145),(148,_binary '','Graphics Cards',145),(163,_binary '','Pots',162),(164,_binary '','Pans',162),(165,_binary '','Pressure Cookers',162),(167,_binary '','Plates',166),(168,_binary '','Spoons',166),(169,_binary '','Serving Bowls',166),(171,_binary '','Tupperware',170),(172,_binary '','Jars',170),(186,_binary '','Sinhala Novels',185),(187,_binary '','Tamil Novels',185),(188,_binary '','English Novels',185),(190,_binary '','Bible',189),(191,_binary '','Quran',189),(192,_binary '','Bhagavad Gita',189),(199,_binary '','Guitar',198),(200,_binary '','Keyboard',198),(201,_binary '','Tabla',198),(208,_binary '','PS4',207),(209,_binary '','Xbox',207),(215,_binary '','Bats',214),(216,_binary '','Pads',214),(217,_binary '','Balls',214),(219,_binary '','Rackets',218),(220,_binary '','Shuttlecocks',218),(222,_binary '','Balls',221),(223,_binary '','Goal Nets',221),(225,_binary '','Dumbbells',224),(226,_binary '','Resistance Bands',224);
/*!40000 ALTER TABLE `product_category_level4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
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
-- Dumping data for table `two_step_verification`
--

LOCK TABLES `two_step_verification` WRITE;
/*!40000 ALTER TABLE `two_step_verification` DISABLE KEYS */;
INSERT INTO `two_step_verification` VALUES (41,'2025-06-12 19:45:25','208102',4);
/*!40000 ALTER TABLE `two_step_verification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Jaffna','2025-05-09 18:05:19','skbavi61@gmail.com','Bavithragithan',_binary '','Kuganesan','0756089312',NULL,'$2a$10$lQ4C/Vuj5UNRUZTLU9TrIuNqrq/S1TBKmRJpvMB.c9BqDYpjadf.W',1,'0756089312',NULL,NULL,NULL,NULL,NULL),(2,'Jaffna','2025-05-09 18:06:37','lathusanthurairajah@codearson.com','Lathusan',_binary '','Thurairajah','+447440641608',NULL,'$2a$10$vGkEHcuxK49rIaDk9SUHWu74OlevG8JBv0Vz9RQaomFJ7xmLIUVk6',1,'+447440641608',NULL,NULL,NULL,NULL,NULL),(4,'polonnaruwa','2025-06-10 19:31:03','rifasmhd3333@gmail.com','rifas',_binary '','mhd','+94755158190',NULL,'$2a$10$IUiiLLoeGy6gq8NLMFzMcuV.5WFzUh5QipJrrmK/tddc1Z/sX/Q/.',1,'+94755158190',NULL,NULL,NULL,NULL,NULL),(5,'','2025-06-12 14:18:05','rfsmarsh12@gmail.com','rifas',_binary '','mhd','07561782009',NULL,'$2a$10$0Cg60.rVC2aSexZQI01Rqu4WF2xXhW1uL1dDkCp/D9UgzL/DtsSEK',3,'07561782009',NULL,NULL,NULL,NULL,NULL);
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
INSERT INTO `user_logs` VALUES (1,'Initiated 2-step verification. OTP sent.','2025-06-11 00:49:02',NULL,_binary '\0',4),(2,'Initiated 2-step verification. OTP sent.','2025-06-11 00:51:43',NULL,_binary '\0',4),(3,'Initiated 2-step verification. OTP sent.','2025-06-11 00:55:00',NULL,_binary '\0',4),(4,'Initiated 2-step verification. OTP sent.','2025-06-11 00:59:01',NULL,_binary '\0',4),(5,'Initiated 2-step verification. OTP sent.','2025-06-11 01:01:45',NULL,_binary '\0',4),(6,'Initiated 2-step verification. OTP sent.','2025-06-11 01:07:51',NULL,_binary '\0',4),(7,'Initiated 2-step verification. OTP sent.','2025-06-11 01:13:59',NULL,_binary '\0',4),(8,'Initiated 2-step verification. OTP sent.','2025-06-11 01:14:29',NULL,_binary '\0',4),(9,'Initiated 2-step verification. OTP sent.','2025-06-11 01:26:53',NULL,_binary '\0',4),(10,'Initiated 2-step verification. OTP sent.','2025-06-11 16:51:16',NULL,_binary '\0',4),(11,'Initiated 2-step verification. OTP sent.','2025-06-11 16:52:53',NULL,_binary '\0',4),(12,'Password reset successfully','2025-06-12 15:01:16',NULL,_binary '\0',4);
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

-- Dump completed on 2025-06-25 21:36:23
