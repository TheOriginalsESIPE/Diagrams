--CODE REALISE PAR ANAIS HEMICI ET LAETITIA ZADI



-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 03 Février 2017 à 02:48
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `pdsr2`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE IF NOT EXISTS `administrateur` (
  `id_ad` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  `numTel` varchar(10) NOT NULL,
  PRIMARY KEY (`id_ad`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `depot`
--

CREATE TABLE IF NOT EXISTS `depot` (
  `id_de` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  `numTel` varchar(10) NOT NULL,
  PRIMARY KEY (`id_de`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `directeur`
--

CREATE TABLE IF NOT EXISTS `directeur` (
  `id_dir` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  `numTel` varchar(10) NOT NULL,
  PRIMARY KEY (`id_dir`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `historique operation`
--

CREATE TABLE IF NOT EXISTS `historique operation` (
  `id_hisO` int(11) NOT NULL AUTO_INCREMENT,
  `date_operation` date NOT NULL,
  PRIMARY KEY (`id_hisO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `historique_pannes`
--

CREATE TABLE IF NOT EXISTS `historique_pannes` (
  `id_hisPan` int(11) NOT NULL AUTO_INCREMENT,
  `date_panne` date NOT NULL,
  PRIMARY KEY (`id_hisPan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `historique_vehicule`
--

CREATE TABLE IF NOT EXISTS `historique_vehicule` (
  `id_hisVe` int(11) NOT NULL AUTO_INCREMENT,
  `date_entree` date NOT NULL,
  `date_sortie` date NOT NULL,
  PRIMARY KEY (`id_hisVe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE IF NOT EXISTS `operation` (
  `id_op` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `duree` time NOT NULL,
  `degre` int(11) NOT NULL,
  PRIMARY KEY (`id_op`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `panne`
--

CREATE TABLE IF NOT EXISTS `panne` (
  `id_pan` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  PRIMARY KEY (`id_pan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `parking`
--

CREATE TABLE IF NOT EXISTS `parking` (
  `id_pa` int(11) NOT NULL AUTO_INCREMENT,
  `numPlace` int(11) NOT NULL,
  PRIMARY KEY (`id_pa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `piece_detachee`
--

CREATE TABLE IF NOT EXISTS `piece_detachee` (
  `id_p` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `marque` varchar(100) NOT NULL,
  `modele` varchar(100) NOT NULL,
  PRIMARY KEY (`id_p`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `piece_en_stock`
--

CREATE TABLE IF NOT EXISTS `piece_en_stock` (
  `id_ps` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `modele` varchar(100) NOT NULL,
  `marque` varchar(100) NOT NULL,
  PRIMARY KEY (`id_ps`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `reparateur`
--

CREATE TABLE IF NOT EXISTS `reparateur` (
  `id_re` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `numTel` varchar(10) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  PRIMARY KEY (`id_re`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

CREATE TABLE IF NOT EXISTS `vehicule` (
  `id_ve` int(11) NOT NULL AUTO_INCREMENT,
  `numMat` varchar(200) NOT NULL,
  `modele` varchar(200) NOT NULL,
  `marque` varchar(200) NOT NULL,
  PRIMARY KEY (`id_ve`),
  KEY `id_ve` (`id_ve`),
  KEY `id_ve_2` (`id_ve`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `depot`
--
ALTER TABLE `depot`
  ADD CONSTRAINT `fk_dir` FOREIGN KEY (`id_de`) REFERENCES `directeur` (`id_dir`);

--
-- Contraintes pour la table `directeur`
--
ALTER TABLE `directeur`
  ADD CONSTRAINT `fk_dep` FOREIGN KEY (`id_dir`) REFERENCES `depot` (`id_de`);

--
-- Contraintes pour la table `historique_pannes`
--
ALTER TABLE `historique_pannes`
  ADD CONSTRAINT `FK_vehicule` FOREIGN KEY (`id_hisPan`) REFERENCES `vehicule` (`id_ve`);

--
-- Contraintes pour la table `historique_vehicule`
--
ALTER TABLE `historique_vehicule`
  ADD CONSTRAINT `FK_veh` FOREIGN KEY (`id_hisVe`) REFERENCES `vehicule` (`id_ve`);

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `FK_ve` FOREIGN KEY (`id_op`) REFERENCES `vehicule` (`id_ve`);

--
-- Contraintes pour la table `parking`
--
ALTER TABLE `parking`
  ADD CONSTRAINT `FK_de` FOREIGN KEY (`id_pa`) REFERENCES `depot` (`id_de`);

--
-- Contraintes pour la table `piece_detachee`
--
ALTER TABLE `piece_detachee`
  ADD CONSTRAINT `FK_ad` FOREIGN KEY (`id_p`) REFERENCES `administrateur` (`id_ad`);

--
-- Contraintes pour la table `piece_en_stock`
--
ALTER TABLE `piece_en_stock`
  ADD CONSTRAINT `FK_op` FOREIGN KEY (`id_ps`) REFERENCES `operation` (`id_op`);

--
-- Contraintes pour la table `piece_en_stock`
--
ALTER TABLE `piece_en_stock`
  ADD CONSTRAINT `FK_adm` FOREIGN KEY (`id_ps`) REFERENCES `operation` (`id_ad`);

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `FK_pa` FOREIGN KEY (`id_ve`) REFERENCES `parking` (`id_pa`);
 
--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `FK_rer` FOREIGN KEY (`id_ve`) REFERENCES `reparateur` (`id_re`);

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `FK_hisOp` FOREIGN KEY (`id_ve`) REFERENCES `historique operation` (`id_hisO`);

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `FK_hisVeh` FOREIGN KEY (`id_ve`) REFERENCES `historique_vehicule` (`id_hisVe`);

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `FK_pannes` FOREIGN KEY (`id_ve`) REFERENCES `historique_pannes` (`id_hisPan`);
  
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
