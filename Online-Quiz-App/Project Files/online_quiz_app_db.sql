-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: quiz_app_db
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` varchar(10) NOT NULL,
  `hashed_password` varchar(100) DEFAULT NULL,
  `salt` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin_01','DsbhqQB2iQ6uwHEExm3DNMoPduz6ZQmBvVazGfqmOkQ=','UcQdElqevCrfdnOFrW5Iog==');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `quiz_id` int NOT NULL,
  `question` varchar(255) NOT NULL,
  `option_1` varchar(255) NOT NULL,
  `option_2` varchar(255) NOT NULL,
  `option_3` varchar(255) NOT NULL,
  `option_4` varchar(255) NOT NULL,
  `correct_answer` int NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `quiz_id` (`quiz_id`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`quiz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (141,17,'What is the chemical symbol for water?','H2O','O2','CO2','H2',1),(142,17,'What planet is closest to the sun?','Earth','Mars','Mercury','Venus',3),(143,17,'What gas do plants absorb from the atmosphere?','Oxygen','Nitrogen','Carbon Dioxide','Hydrogen',3),(144,17,'What is the powerhouse of the cell?','Nucleus','Ribosome','Mitochondria','Golgi Apparatus',3),(145,17,'What is the study of fungi called?','Botany','Mycology','Zoology','Ecology',2),(146,17,'What is the most abundant gas in Earth\'s atmosphere?','Oxygen','Nitrogen','Carbon Dioxide','Hydrogen',2),(147,17,'What is the main gas found in the air we breathe?','Oxygen','Nitrogen','Carbon Dioxide','Helium',2),(148,17,'What force keeps us on the ground?','Magnetism','Gravity','Friction','Electromagnetism',2),(149,17,'What is the chemical formula for table salt?','NaCl','KCl','MgCl2','CaCl2',1),(150,17,'What is the smallest unit of life?','Cell','Atom','Molecule','Organ',1),(151,18,'Who was the first President of the United States?','George Washington','Thomas Jefferson','Abraham Lincoln','John Adams',1),(152,18,'In what year did World War II end?','1940','1945','1950','1955',2),(153,18,'Who was the ancient Greek god of the sun?','Zeus','Apollo','Hades','Poseidon',2),(154,18,'What was the name of the ship on which the Pilgrims traveled to America in 1620?','Santa Maria','Mayflower','Pinta','Nina',2),(155,18,'Who was the first man to step on the moon?','Buzz Aldrin','Neil Armstrong','Yuri Gagarin','John Glenn',2),(156,18,'What was the main language of the Roman Empire?','Greek','Latin','Italian','French',2),(157,18,'Who was known as the \"Maid of Orleans\"?','Elizabeth I','Cleopatra','Joan of Arc','Marie Antoinette',3),(158,18,'What empire did Genghis Khan found?','Roman Empire','Ottoman Empire','Mongol Empire','Persian Empire',3),(159,18,'In which year did the Berlin Wall fall?','1985','1989','1991','1995',2),(160,18,'Who wrote the Declaration of Independence?','Benjamin Franklin','John Adams','Thomas Jefferson','George Washington',3),(171,20,'Who wrote \"Pride and Prejudice\"?','Jane Austen','Emily Brontë','Charlotte Brontë','George Eliot',1),(172,20,'Who is the author of \"1984\"?','George Orwell','Aldous Huxley','Ray Bradbury','J.K. Rowling',1),(173,20,'Who wrote \"Moby-Dick\"?','Mark Twain','Herman Melville','Nathaniel Hawthorne','J.D. Salinger',2),(174,20,'In which Shakespeare play does the character of Iago appear?','Macbeth','Hamlet','Othello','King Lear',3),(175,20,'Who is the author of \"The Great Gatsby\"?','F. Scott Fitzgerald','Ernest Hemingway','John Steinbeck','William Faulkner',1),(176,20,'Who wrote \"The Catcher in the Rye\"?','Harper Lee','J.D. Salinger','Truman Capote','John Steinbeck',2),(177,20,'Who wrote \"Brave New World\"?','George Orwell','Aldous Huxley','Ray Bradbury','H.G. Wells',2),(178,20,'Who is the author of \"To Kill a Mockingbird\"?','Harper Lee','Mark Twain','F. Scott Fitzgerald','Ernest Hemingway',1),(179,20,'In which book series does the character Katniss Everdeen appear?','Harry Potter','The Hunger Games','Twilight','Divergent',2),(180,20,'Who wrote \"The Road\"?','Cormac McCarthy','Stephen King','J.K. Rowling','James Patterson',1),(181,21,'What is the capital of France?','Berlin','Madrid','Paris','Rome',3),(182,21,'Which planet is known as the Red Planet?','Earth','Mars','Jupiter','Saturn',2),(183,21,'Who wrote \"To Kill a Mockingbird\"?','Harper Lee','Jane Austen','Mark Twain','Ernest Hemingway',1),(184,21,'What is the largest ocean on Earth?','Atlantic','Indian','Arctic','Pacific',4),(185,21,'Who painted the Mona Lisa?','Vincent Van Gogh','Leonardo da Vinci','Pablo Picasso','Claude Monet',2),(186,21,'What is the smallest country in the world?','Monaco','Vatican City','San Marino','Liechtenstein',2),(187,21,'Which element has the chemical symbol O?','Oxygen','Gold','Silver','Iron',1),(188,21,'What year did the Titanic sink?','1912','1905','1915','1920',1),(189,21,'Who discovered penicillin?','Marie Curie','Isaac Newton','Alexander Fleming','Louis Pasteur',3),(190,21,'What is the hardest natural substance on Earth?','Gold','Diamond','Iron','Platinum',2),(201,23,'What is the value of pi (π) to two decimal places?','3.12','3.14','3.16','3.18',2),(202,23,'What is the square root of 64?','6','7','8','9',3),(203,23,'What is 25% of 200?','50','25','75','100',1),(204,23,'What is the formula for the area of a circle?','πr^2','2πr','πr','2πr^2',1),(205,23,'What is 7 times 8?','54','56','58','60',2),(206,23,'What is the hypotenuse of a right triangle with sides 3 and 4?','5','6','7','8',1),(207,23,'What is the next prime number after 7?','9','10','11','12',3),(208,23,'What is the sum of the angles in a triangle?','90 degrees','180 degrees','270 degrees','360 degrees',2),(209,23,'What is 13 squared?','26','169','81','144',2),(210,23,'What is the value of x in the equation 2x + 3 = 11?','2','3','4','5',3);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz` (
  `quiz_id` int NOT NULL AUTO_INCREMENT,
  `quiz_title` varchar(255) NOT NULL,
  PRIMARY KEY (`quiz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` VALUES (17,'Science Quiz'),(18,'History Quiz'),(20,'Literature Quiz'),(21,'General Knowledge(G.K.) Quiz'),(23,'Math Quiz');
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_archive`
--

DROP TABLE IF EXISTS `quiz_archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_archive` (
  `quiz_id` int NOT NULL,
  `quiz_title` varchar(255) NOT NULL,
  PRIMARY KEY (`quiz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_archive`
--

LOCK TABLES `quiz_archive` WRITE;
/*!40000 ALTER TABLE `quiz_archive` DISABLE KEYS */;
INSERT INTO `quiz_archive` VALUES (16,'General Knowledge Quiz'),(22,'Math Quiz');
/*!40000 ALTER TABLE `quiz_archive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_attempts`
--

DROP TABLE IF EXISTS `quiz_attempts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_attempts` (
  `quiz_id` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `attempt_date_time` datetime NOT NULL,
  `score` int NOT NULL,
  PRIMARY KEY (`quiz_id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_attempts`
--

LOCK TABLES `quiz_attempts` WRITE;
/*!40000 ALTER TABLE `quiz_attempts` DISABLE KEYS */;
INSERT INTO `quiz_attempts` VALUES (17,'dahazu','2024-07-02 00:00:00',90),(17,'Ram_Agr','2024-07-03 00:00:00',90),(18,'dahazu','2024-07-02 00:00:00',60),(23,'Ram_Agr','2024-07-02 00:00:00',100);
/*!40000 ALTER TABLE `quiz_attempts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_attempts_archive`
--

DROP TABLE IF EXISTS `quiz_attempts_archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_attempts_archive` (
  `quiz_id` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `attempt_date_time` datetime NOT NULL,
  `score` int NOT NULL,
  PRIMARY KEY (`quiz_id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_attempts_archive`
--

LOCK TABLES `quiz_attempts_archive` WRITE;
/*!40000 ALTER TABLE `quiz_attempts_archive` DISABLE KEYS */;
INSERT INTO `quiz_attempts_archive` VALUES (22,'dahazu','2024-07-02 00:00:00',90);
/*!40000 ALTER TABLE `quiz_attempts_archive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `hashed_password` varchar(100) NOT NULL,
  `salt` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('dahazu','JJfCvSlvzCrvT7YF9XT8jdCfaYWDyvwupXkpzFFRV70=','zHvj3Xj6LPNc+hDaDuCRJA=='),('Ram_Agr','GFNWlcQBZWq+xI7EK1vx2zv7eTVBF2qCV0hSaej7H2Q=','Xx1tws7Rhf5nP/Lh2u/MLw==');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-03 17:15:39
