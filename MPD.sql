
CREATE TABLE IF NOT EXISTS `administrator` ( 
   `login` varchar(20) NOT NULL, 
   `password` varchar(20) NOT NULL, 
   `lastName` varchar(50) NOT NULL, 
   `firstName` varchar(50) NOT NULL, 
   `adress` varchar(200), 
   `numTel` varchar(10), 
  PRIMARY KEY (`login`)  
); 


CREATE TABLE IF NOT EXISTS `warehouse` ( 
   `id_warehouse` int(11) NOT NULL AUTO_INCREMENT, 
   `name` varchar(100) NOT NULL, 
   `volum` int(11) NOT NULL, 
   `adress` varchar(200), 
   `numTel` varchar(10), 
   `login_director` varchar(20) NOT NULL, 
   PRIMARY KEY (`id_wirehouse`) 
); 

CREATE TABLE IF NOT EXISTS `director` ( 
   `login` varchar(20) NOT NULL, 
   `password` varchar(20) NOT NULL, 
   `lastName` varchar(50) NOT NULL, 
   `firstName` varchar(50) NOT NULL, 
   `adress` varchar(200), 
   `numTel` varchar(10), 
   PRIMARY KEY (`login`) 
); 

CREATE TABLE IF NOT EXISTS `operation` ( 
   `id_operation` int(11) NOT NULL AUTO_INCREMENT, 
   `name` varchar(100) NOT NULL, 
   `date_begin` date NOT NULL, 
   `date_end` date, 
   `time` time, 
   `degre_emergency` enum('A', 'B', 'C', 'D', 'E'), 
   `login_repairer` varchar(20) NOT NULL, 
   `id_breakdown` int(11) NOT NULL, 
   PRIMARY KEY (`id_operation`) 
); 
   
 
CREATE TABLE IF NOT EXISTS `breakdown` ( 
   `id_breakdown` int(11) NOT NULL AUTO_INCREMENT, 
   `name` varchar(100) NOT NULL, 
   `numMat` varchar(200) NOT NULL, 
   PRIMARY KEY (`id_breakdown`) 
); 

 
CREATE TABLE IF NOT EXISTS `parking` ( 
   `numPlace` int(11) NOT NULL, 
   `id_warehouse` int(11) NOT NULL, 
   PRIMARY KEY (`numPlace`) 
); 

 
CREATE TABLE IF NOT EXISTS `piece_detached` ( 
   `ref_piece_detached` varchar(11) NOT NULL, 
   `name` varchar(100) NOT NULL, 
   `mark` varchar(100) NOT NULL, 
   `model` varchar(100) NOT NULL, 
   `price` decimal(7,2) NOT NULL, 
   `date_purchase` date NOT NULL, 
   PRIMARY KEY (`ref_piece_detached`) 
); 

 
CREATE TABLE IF NOT EXISTS `piece_stock` ( 
   `ref_piece_stock` varchar(11) NOT NULL, 
   `counter` int(11) NOT NULL, 
   `login_administrator` varchar(20), 
   `date_reception` date NOT NULL, 
    PRIMARY KEY (`ref_piece_stock`) 
); 

 
CREATE TABLE IF NOT EXISTS `piece_consumption` ( 
   `ref_piece_stock` varchar(100) NOT NULL, 
   `nomber` int(11) NOT NULL, 
   `id_operation` int(11) NOT NULL 
); 

 
CREATE TABLE IF NOT EXISTS `repairer` ( 
   `login` varchar(100) NOT NULL, 
   `password` varchar(100) NOT NULL, 
   `firstName` varchar(100) NOT NULL, 
   `lastName` varchar(100) NOT NULL, 
   `numTel` varchar(10), 
   `adress` varchar(200), 
   PRIMARY KEY (`login`) 
); 

 
CREATE TABLE IF NOT EXISTS `vehicle` ( 
   `numMat` varchar(200) NOT NULL, 
   `model` varchar(200) NOT NULL, 
   `mark` varchar(200) NOT NULL, 
   `vehicle_type` enum('velo', 'voiture') NOT NULL, 
   PRIMARY KEY (`numMat`) 
); 

 
CREATE TABLE IF NOT EXISTS `vehicule_warehouse` ( 
   `numMat` varchar(50) NOT NULL, 
   `numPlace` int(11) NOT NULL, 
   `date_entrance` date NOT NULL, 
   `date_wayout` date 
); 

 
CREATE TABLE IF NOT EXISTS `piece_purchase`( 
 	`ref_piece_detached` varchar(11) NOT NULL, 
 	`price` decimal(7,2) NOT NULL, 
 	`number` int(11) NOT NULL, 
  	`date_purchase` DATE NOT NULL 
); 
 
ALTER TABLE `warehouse` 
ADD CONSTRAINT `FK_director` FOREIGN KEY (`login_director`) REFERENCES `director` (`login`) ON DELETE CASCADE; 


ALTER TABLE `breakdown` 
ADD CONSTRAINT `FK_numMat` FOREIGN KEY (`numMat`) REFERENCES `vehicle_warehouse` (`numMat`) ON DELETE CASCADE; 

ALTER TABLE `operation` 
ADD CONSTRAINT `FK_repairer` FOREIGN KEY (`login_repairer`) REFERENCES `repairer` (`login`) ON DELETE CASCADE; 


ALTER TABLE `operation` 
ADD CONSTRAINT `FK_breakdown` FOREIGN KEY (`id_breakdown`) REFERENCES `breakdown` (`id_breakdown`) ON DELETE CASCADE; 
 
ALTER TABLE `parking` 
ADD CONSTRAINT `FK_warehouse2` FOREIGN KEY (`id_warehouse`) REFERENCES `warehouse` (`id_warehouse`) ON DELETE CASCADE; 
 
 
ALTER TABLE `piece_stock` 
ADD CONSTRAINT `FK_administrator3` FOREIGN KEY (`login_administrator`) REFERENCES `administrator` (`login`) ON DELETE CASCADE; 
 
 
ALTER TABLE `piece_stock` 
ADD CONSTRAINT `FK_piece_detached` FOREIGN KEY (`ref_piece_stock`) REFERENCES `piece_detached` (`ref_piece_detached`) ON DELETE CASCADE; 

 
ALTER TABLE `piece_consumption` 
ADD CONSTRAINT `FK_ref_piece_stock` FOREIGN KEY (`ref_piece_stock`) REFERENCES `piece_stock` (`ref_piece_stock`), 
ADD CONSTRAINT `FK_id_operation2` FOREIGN KEY (`id_operation`) REFERENCES `operation` (`id_operation`) ON DELETE CASCADE; 

 
ALTER TABLE `vehicle_warehouse` 
ADD CONSTRAINT `FK_parking` FOREIGN KEY (`numPlace`) REFERENCES `parking` (`numPlace`), 
ADD CONSTRAINT `FK_vehicle2` FOREIGN KEY (`numMat`) REFERENCES `vehicle` (`numMat`) ON DELETE CASCADE; 
