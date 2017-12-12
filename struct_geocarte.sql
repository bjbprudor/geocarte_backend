CREATE DATABASE IF NOT EXISTS `geotest` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `geotest`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: geotest
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `anciennom`
--

DROP TABLE IF EXISTS `anciennom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `anciennom` (
  `id` int(11) NOT NULL,
  `article` varchar(255) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `commune_insee` varchar(6) NOT NULL,
  PRIMARY KEY (`commune_insee`,`id`),
  CONSTRAINT `anciennom_commune` FOREIGN KEY (`commune_insee`) REFERENCES `commune` (`insee`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cartepostale`
--

DROP TABLE IF EXISTS `cartepostale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cartepostale` (
  `id` int(20) NOT NULL auto_increment,
  `codeEditeur` int(20) DEFAULT NULL,
  `commune_insee` varchar(6) DEFAULT NULL,
  `editeur_id` int(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cartepostale_commune` (`commune_insee`),
  KEY `cartepostale_editeur` (`editeur_id`),
  CONSTRAINT `cartepostale_commune` FOREIGN KEY (`commune_insee`) REFERENCES `commune` (`insee`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `cartepostale_editeur` FOREIGN KEY (`editeur_id`) REFERENCES `editeur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `carteutilisateur`
--

DROP TABLE IF EXISTS `carteutilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carteutilisateur` (
  `nombreExemplaires` int(11) NOT NULL,
  `utilisateur_id` int(11) NOT NULL,
  `varianteCarte_cartePostale_id` int(20) NOT NULL,
  `varianteCarte_id` int(11) NOT NULL,
  PRIMARY KEY (`utilisateur_id`,`varianteCarte_cartePostale_id`,`varianteCarte_id`),
  KEY `carteutilisateur_variante` (`varianteCarte_cartePostale_id`,`varianteCarte_id`),
  CONSTRAINT `carteutilisateur_utilisateur` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `carteutilisateur_variante` FOREIGN KEY (`varianteCarte_cartePostale_id`, `varianteCarte_id`) REFERENCES `variantecarte` (`cartePostale_id`, `id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `commune`
--

DROP TABLE IF EXISTS `commune`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commune` (
  `insee` varchar(6) NOT NULL,
  `article` varchar(255) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `departement_numero` varchar(3) NOT NULL,
  `fusion_insee` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`insee`),
  KEY `commune_departement` (`departement_numero`),
  KEY `commune_fusion` (`fusion_insee`),
  CONSTRAINT `commune_departement` FOREIGN KEY (`departement_numero`) REFERENCES `departement` (`numero`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `commune_fusion` FOREIGN KEY (`fusion_insee`) REFERENCES `commune` (`insee`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `departement`
--

DROP TABLE IF EXISTS `departement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departement` (
  `numero` varchar(3) NOT NULL,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `editeur`
--

DROP TABLE IF EXISTS `editeur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `editeur` (
  `id` int(20) NOT NULL auto_increment,
  `code` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `editeurcommune`
--

DROP TABLE IF EXISTS `editeurcommune`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `editeurcommune` (
  `editeur` int(20) NOT NULL,
  `commune` varchar(6) NOT NULL,
  KEY `editeurcommune_commune` (`commune`),
  KEY `editeurcommune_editeur` (`editeur`),
  CONSTRAINT `editeurcommune_commune` FOREIGN KEY (`commune`) REFERENCES `commune` (`insee`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `editeurcommune_editeur` FOREIGN KEY (`editeur`) REFERENCES `editeur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TypeMonument`
--

DROP TABLE IF EXISTS `typeMonument`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `typeMonument` (
  `id` int(11) NOT NULL auto_increment,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `monument`
--

DROP TABLE IF EXISTS `monument`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monument` (
  `id` int(11) NOT NULL auto_increment,
  `anneeConstruction` int(11) DEFAULT NULL,
  `divers` varchar(255) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `commune_insee` varchar(6) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `monument_commune` (`commune_insee`),
  KEY `monument_type` (`type`),
  CONSTRAINT `monument_commune` FOREIGN KEY (`commune_insee`) REFERENCES `commune` (`insee`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `monument_type` FOREIGN KEY (`type`) REFERENCES `typeMonument` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `monumentcarte`
--

DROP TABLE IF EXISTS `monumentcarte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monumentcarte` (
  `monument` int(11) NOT NULL,
  `cartePostale` int(20) NOT NULL,
  KEY `monumentcarte_carte` (`cartePostale`),
  KEY `monumentcarte_monument` (`monument`),
  CONSTRAINT `monumentcarte_carte` FOREIGN KEY (`cartePostale`) REFERENCES `cartepostale` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `monumentcarte_monument` FOREIGN KEY (`monument`) REFERENCES `monument` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL auto_increment,
  `email` varchar(255) NOT NULL,
  `motdepasse` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `variantecarte`
--

DROP TABLE IF EXISTS `variantecarte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `variantecarte` (
  `id` int(11) NOT NULL,
  `dos` varchar(255) DEFAULT NULL,
  `face` varchar(255) DEFAULT NULL,
  `legende` varchar(255) NOT NULL,
  `lengende_2` varchar(255) DEFAULT NULL,
  `cartePostale_id` int(20) NOT NULL,
  PRIMARY KEY (`cartePostale_id`,`id`),
  CONSTRAINT `variantecarte_carte` FOREIGN KEY (`cartePostale_id`) REFERENCES `cartepostale` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-24 14:55:22
