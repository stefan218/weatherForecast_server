/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.25-MariaDB : Database - prognoza
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prognoza` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `prognoza`;

/*Table structure for table `meteorolog` */

DROP TABLE IF EXISTS `meteorolog`;

CREATE TABLE `meteorolog` (
  `MeteorologID` int(10) NOT NULL,
  `Ime` varchar(50) DEFAULT NULL,
  `Prezime` varchar(50) DEFAULT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MeteorologID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `meteorolog` */

insert  into `meteorolog`(`MeteorologID`,`Ime`,`Prezime`,`Username`,`Password`) values 
(1,'Ana','Anic','ana','ana'),
(2,'Iva','Ivic','iva','iva'),
(3,'Marko','Markovic','mare','mare');

/*Table structure for table `prognoza` */

DROP TABLE IF EXISTS `prognoza`;

CREATE TABLE `prognoza` (
  `PrognozaID` int(20) NOT NULL,
  `Dan` date DEFAULT NULL,
  `Opis` varchar(50) DEFAULT NULL,
  `MeteorologID` int(50) DEFAULT NULL,
  PRIMARY KEY (`PrognozaID`),
  KEY `fk_mid` (`MeteorologID`),
  CONSTRAINT `fk_mid` FOREIGN KEY (`MeteorologID`) REFERENCES `meteorolog` (`MeteorologID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `prognoza` */

insert  into `prognoza`(`PrognozaID`,`Dan`,`Opis`,`MeteorologID`) values 
(1,'2022-01-28','Prognoza za dan 28.01.2022',1),
(2,'2022-02-01','Prognoza za dan 01.02.2022',1),
(3,'2022-02-01','Prognoza za dan 01.02.2022',3);

/*Table structure for table `prognozaregion` */

DROP TABLE IF EXISTS `prognozaregion`;

CREATE TABLE `prognozaregion` (
  `PrognozaID` int(50) NOT NULL,
  `RB` int(50) NOT NULL,
  `Temperatura` decimal(10,2) DEFAULT NULL,
  `MeteoAlarm` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `Pojava` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `RegionID` int(50) DEFAULT NULL,
  PRIMARY KEY (`PrognozaID`,`RB`),
  KEY `fk_regionID` (`RegionID`),
  CONSTRAINT `fk_regionID` FOREIGN KEY (`RegionID`) REFERENCES `region` (`RegionID`),
  CONSTRAINT `prognozaregion_fk_1` FOREIGN KEY (`PrognozaID`) REFERENCES `prognoza` (`PrognozaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `prognozaregion` */

insert  into `prognozaregion`(`PrognozaID`,`RB`,`Temperatura`,`MeteoAlarm`,`Pojava`,`RegionID`) values 
(1,1,25.00,'Zeleni','Nema',1),
(1,2,25.00,'Zeleni','Nema',2),
(1,3,25.00,'Zeleni','Nema',3),
(1,4,25.00,'Zeleni','Nema',4),
(1,5,25.00,'Zeleni','Nema',5),
(2,1,20.00,'Zeleni','Nema',1),
(2,2,20.00,'Zeleni','Nema',2),
(2,3,20.00,'Zeleni','Nema',4),
(2,4,20.00,'Zeleni','Nema',5),
(2,5,20.00,'Zeleni','Nema',3),
(3,1,25.00,'Zeleni','Nema',1),
(3,2,25.00,'Zeleni','Vetar',2),
(3,3,23.00,'Zuti','Vetar',3),
(3,4,20.00,'Zuti','Vetar',4),
(3,5,27.00,'Narandzasti','Grmljavina',5);

/*Table structure for table `region` */

DROP TABLE IF EXISTS `region`;

CREATE TABLE `region` (
  `RegionID` int(30) NOT NULL,
  `Naziv` varchar(50) DEFAULT NULL,
  `Opis` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`RegionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `region` */

insert  into `region`(`RegionID`,`Naziv`,`Opis`) values 
(1,'Severna Srbija','Obuhvata Banat, Backu i Srem'),
(2,'Beograd','Region koji obuhvata grad Beograd i centralnu Srbiju'),
(3,'Istocna Srbija','Obuhvata istocnu Srbiju'),
(4,'Zapadna Srbija','Obuhvata zapadnu Srbiju'),
(5,'Juzna Srbija','Obuhvata juznu Srbiju');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
