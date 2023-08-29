-- MySQL dump 10.13  Distrib 8.0.32, for macos13 (x86_64)
--
-- Host: localhost    Database: evotinghub
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Candidate`
--

DROP TABLE IF EXISTS `Candidate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Candidate` (
  `id` int NOT NULL,
  `agenda` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Candidate`
--

LOCK TABLES `Candidate` WRITE;
/*!40000 ALTER TABLE `Candidate` DISABLE KEYS */;
/*!40000 ALTER TABLE `Candidate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Candidate_SEQ`
--

DROP TABLE IF EXISTS `Candidate_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Candidate_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Candidate_SEQ`
--

LOCK TABLES `Candidate_SEQ` WRITE;
/*!40000 ALTER TABLE `Candidate_SEQ` DISABLE KEYS */;
INSERT INTO `Candidate_SEQ` VALUES (1);
/*!40000 ALTER TABLE `Candidate_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Election`
--

DROP TABLE IF EXISTS `Election`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Election` (
  `electionID` int NOT NULL,
  `electionName` varchar(255) DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  PRIMARY KEY (`electionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Election`
--

LOCK TABLES `Election` WRITE;
/*!40000 ALTER TABLE `Election` DISABLE KEYS */;
/*!40000 ALTER TABLE `Election` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Election_Candidate`
--

DROP TABLE IF EXISTS `Election_Candidate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Election_Candidate` (
  `Election_electionID` int NOT NULL,
  `candidates_id` int NOT NULL,
  UNIQUE KEY `UK_lu4fbx3yf5e93gt996wis12s0` (`candidates_id`),
  KEY `FK49953hvhr37ou0axhdyp6ms55` (`Election_electionID`),
  CONSTRAINT `FK49953hvhr37ou0axhdyp6ms55` FOREIGN KEY (`Election_electionID`) REFERENCES `Election` (`electionID`),
  CONSTRAINT `FKpoux2n9tsd0ponp343x8umdom` FOREIGN KEY (`candidates_id`) REFERENCES `Candidate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Election_Candidate`
--

LOCK TABLES `Election_Candidate` WRITE;
/*!40000 ALTER TABLE `Election_Candidate` DISABLE KEYS */;
/*!40000 ALTER TABLE `Election_Candidate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Election_SEQ`
--

DROP TABLE IF EXISTS `Election_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Election_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Election_SEQ`
--

LOCK TABLES `Election_SEQ` WRITE;
/*!40000 ALTER TABLE `Election_SEQ` DISABLE KEYS */;
INSERT INTO `Election_SEQ` VALUES (1);
/*!40000 ALTER TABLE `Election_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ElectionResult`
--

DROP TABLE IF EXISTS `ElectionResult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ElectionResult` (
  `electionId` int NOT NULL,
  `candidateId` int NOT NULL,
  `voteCount` int NOT NULL,
  PRIMARY KEY (`electionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ElectionResult`
--

LOCK TABLES `ElectionResult` WRITE;
/*!40000 ALTER TABLE `ElectionResult` DISABLE KEYS */;
/*!40000 ALTER TABLE `ElectionResult` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Vote`
--

DROP TABLE IF EXISTS `Vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Vote` (
  `id` int NOT NULL,
  `candidateId` int NOT NULL,
  `electionId` int NOT NULL,
  `voterEmail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Vote`
--

LOCK TABLES `Vote` WRITE;
/*!40000 ALTER TABLE `Vote` DISABLE KEYS */;
/*!40000 ALTER TABLE `Vote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Vote_SEQ`
--

DROP TABLE IF EXISTS `Vote_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Vote_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Vote_SEQ`
--

LOCK TABLES `Vote_SEQ` WRITE;
/*!40000 ALTER TABLE `Vote_SEQ` DISABLE KEYS */;
INSERT INTO `Vote_SEQ` VALUES (1);
/*!40000 ALTER TABLE `Vote_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Voter`
--

DROP TABLE IF EXISTS `Voter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Voter` (
  `email` varchar(255) NOT NULL,
  `age` int NOT NULL,
  `firsttName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Voter`
--

LOCK TABLES `Voter` WRITE;
/*!40000 ALTER TABLE `Voter` DISABLE KEYS */;
INSERT INTO `Voter` VALUES ('akhtar@gmail.com',29,'akhtar','ansari','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');
/*!40000 ALTER TABLE `Voter` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-29 13:11:52
