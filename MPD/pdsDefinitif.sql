CREATE TABLE IF NOT EXISTS `administrator` (
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `adresse` varchar(200),
  `numTel` varchar(10),
  PRIMARY KEY (`login`)
);



CREATE TABLE IF NOT EXISTS `depot` (
  `id_depot` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `volume` int(11) NOT NULL,
  `adresse` varchar(200),
  `numTel` varchar(10),
  `login_directeur` varchar(20) NOT NULL,
  PRIMARY KEY (`id_depot`)
);



CREATE TABLE IF NOT EXISTS `directeur` (
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `adresse` varchar(200),
  `numTel` varchar(10),
  PRIMARY KEY (`login`)
);



CREATE TABLE IF NOT EXISTS `operation` (
  `id_operation` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date,
  `duree` time,
  `degre_urgence` enum('A', 'B', 'C', 'D', 'E'),
  `login_reparateur` varchar(20) NOT NULL,
  `id_panne` int(11) NOT NULL,
  PRIMARY KEY (`id_operation`)
);



CREATE TABLE IF NOT EXISTS `panne` (
  `id_panne` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `numMat` varchar(200) NOT NULL,
  PRIMARY KEY (`id_panne`)
);



CREATE TABLE IF NOT EXISTS `parking` (
  `numPlace` int(11) NOT NULL,
  `id_depot` int(11) NOT NULL,
  PRIMARY KEY (`numPlace`)
);



CREATE TABLE IF NOT EXISTS `piece_detachee` (
  `ref_piece_detachee` varchar(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `marque` varchar(100) NOT NULL,
  `modele` varchar(100) NOT NULL,
  `prix` decimal(7,2) NOT NULL,
  `date_achat` date NOT NULL,
  PRIMARY KEY (`ref_piece_detachee`)
);



CREATE TABLE IF NOT EXISTS `piece_en_stock` (
  `ref_piece_stock` varchar(11) NOT NULL,
  `compteur` int(11) NOT NULL,
  `login_administrateur` varchar(20),
  `date_reception` date NOT NULL,
   PRIMARY KEY (`ref_piece_stock`)
);



CREATE TABLE IF NOT EXISTS `piece_conso` (
  `ref_piece_stock` varchar(100) NOT NULL,
  `nombre` int(11) NOT NULL,
  `id_operation` int(11) NOT NULL
);




CREATE TABLE IF NOT EXISTS `reparateur` (
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `numTel` varchar(10),
  `adresse` varchar(200),
  PRIMARY KEY (`login`)
);



CREATE TABLE IF NOT EXISTS `vehicule` (
  `numMat` varchar(200) NOT NULL,
  `modele` varchar(200) NOT NULL,
  `marque` varchar(200) NOT NULL,
  `vehicule_type` enum('velo', 'voiture') NOT NULL,
  PRIMARY KEY (`numMat`)
);



CREATE TABLE IF NOT EXISTS `vehicule_depot` (
  `numMat` varchar(50) NOT NULL,
  `numPlace` int(11) NOT NULL,
  `date_entree` date NOT NULL,
  `date_sortie` date
);

CREATE TABLE IF NOT EXISTS `piece_achat`(
	`ref_piece_detachee` varchar(11) NOT NULL,
	`prix` decimal(7,2) NOT NULL,
	`number` int(11) NOT NULL,
 	`date_achat` DATE NOT NULL
)

ALTER TABLE `depot`
  ADD CONSTRAINT `FK_directeur` FOREIGN KEY (`login_directeur`) REFERENCES `directeur` (`login`);


ALTER TABLE `panne`
  ADD CONSTRAINT `FK_numMat` FOREIGN KEY (`numMat`) REFERENCES `vehicule_depot` (`numMat`);
  

ALTER TABLE `operation`
  ADD CONSTRAINT `FK_reparateur` FOREIGN KEY (`login_reparateur`) REFERENCES `reparateur` (`login`);


ALTER TABLE `operation`
  ADD CONSTRAINT `FK_panne` FOREIGN KEY (`id_panne`) REFERENCES `panne` (`id_panne`);
  

ALTER TABLE `parking`
  ADD CONSTRAINT `FK_depot2` FOREIGN KEY (`id_depot`) REFERENCES `depot` (`id_depot`);


ALTER TABLE `piece_en_stock`
  ADD CONSTRAINT `FK_administrateur3` FOREIGN KEY (`login_administrateur`) REFERENCES `administrateur` (`login`);


ALTER TABLE `piece_en_stock`
  ADD CONSTRAINT `FK_piece_detachee` FOREIGN KEY (`ref_piece_stock`) REFERENCES `piece_detachee` (`ref_piece_detachee`);


ALTER TABLE `piece_conso`
  ADD CONSTRAINT `FK_ref_piece_stock` FOREIGN KEY (`ref_piece_stock`) REFERENCES `piece_en_stock` (`ref_piece_stock`),
  ADD CONSTRAINT `FK_id_operation2` FOREIGN KEY (`id_operation`) REFERENCES `operation` (`id_operation`);
  

ALTER TABLE `vehicule_depot`
  ADD CONSTRAINT `FK_parking` FOREIGN KEY (`numPlace`) REFERENCES `parking` (`numPlace`),
  ADD CONSTRAINT `FK_vehicule2` FOREIGN KEY (`numMat`) REFERENCES `vehicule` (`numMat`);
