INSERT INTO `administrateur` (`login`,`password`, `nom`, `prenom`, `adresse`, `numTel`) VALUES
('paul','paul', 'CLAVA', 'Paul', '50 rue des Framboisiers 75006 Paris', '0678451298');
INSERT INTO `directeur` (`login`,`password`,`nom`, `prenom`, `adresse`, `numTel`) VALUES
('fabio', 'fabio', 'DUPONT', 'Fabio', '40 rue des Lilas Choisy Le Roi', '0625836547');
INSERT INTO `directeur` (`login`,`password`,`nom`, `prenom`, `adresse`, `numTel`) VALUES
('DFabio0', 'fabio', 'DUPONT', 'Fabio', '40 rue des Lilas Choisy Le Roi', '0625836547');
INSERT INTO `reparateur` (`login`, `password`, `nom`, `prenom`, `numTel`, `adresse`) VALUES
('henri', 'henri', 'PAUL', 'Henri', '0633552112', '77 rue des champs 72012 Paris');
INSERT INTO `depot` (`nom`, `volume`, `adresse`, `numTel`, `login_directeur`) VALUES
('Fleur de Lys', 400 , '207 rue de Bercy 75012 Paris', '0148995118', 'fabio');
INSERT INTO `parking` (`numPlace`, `id_depot`) VALUES
(1, 1);
INSERT INTO `parking` (`numPlace`, `id_depot`) VALUES
(2, 1);
INSERT INTO `parking` (`numPlace`, `id_depot`) VALUES
(3, 1);
INSERT INTO `parking` (`numPlace`, `id_depot`) VALUES
(4, 1);
INSERT INTO `parking` (`numPlace`, `id_depot`) VALUES
(5, 1);
INSERT INTO `parking` (`numPlace`, `id_depot`) VALUES
(6, 1);
INSERT INTO `parking` (`numPlace`, `id_depot`) VALUES
(7, 1);
INSERT INTO `parking` (`numPlace`, `id_depot`) VALUES
(8, 1);
INSERT INTO `parking` (`numPlace`, `id_depot`) VALUES
(9, 1);
INSERT INTO `vehicule` (`numMat`, `modele`, `marque`, `vehicule_type`) VALUES
('DG 909 DG', 'Clio 4', 'Renault', 'voiture');
INSERT INTO `vehicule` (`numMat`, `modele`, `marque`, `vehicule_type`) VALUES
('DG 909 XG', 'Clio 4', 'Renault', 'voiture');
INSERT INTO `vehicule` (`numMat`, `modele`, `marque`, `vehicule_type`) VALUES
('DG 809 DG', 'Clio 4', 'Renault', 'voiture');
INSERT INTO `vehicule` (`numMat`, `modele`, `marque`, `vehicule_type`) VALUES
('FG 409 DG', 'Clio 4', 'Renault', 'voiture');
INSERT INTO `vehicule` (`numMat`, `modele`, `marque`, `vehicule_type`) VALUES
('CG 129 DX', 'Clio 4', 'Renault', 'voiture');
INSERT INTO `vehicule` (`numMat`, `modele`, `marque`, `vehicule_type`) VALUES
('FFF 208', 'VTT', 'Rock rider', 'velo');
INSERT INTO `vehicule` (`numMat`, `modele`, `marque`, `vehicule_type`) VALUES
('FFF 209', 'VTT', 'Rock rider', 'velo');
INSERT INTO `vehicule_depot` (`numMat`,`numPlace`, `date_entree`, `date_sortie`) VALUES
('DG 909 DG', 1, '17-02-13', '17-02-14');
INSERT INTO `vehicule_depot` (`numMat`,`numPlace`, `date_entree`, `date_sortie`) VALUES
('FFF 208', 2, '17-02-11', '17-02-11');
INSERT INTO `panne` (`id_panne`, `nom`, `numMat`) VALUES
(1, 'filtre a air defaillant', 'DG 909 DG');
INSERT INTO `panne` (`id_panne`, `nom`, `numMat`) VALUES
(2, 'pneu avant crevé', 'FFF 208');
INSERT INTO `piece_detachee` (`ref_piece_detachee`, `nom`, `marque`, `modele`,`prix`) VALUES
('F026400343', 'Filtre a air ', 'Bosch', 'H4B 400',13.28);
INSERT INTO `piece_detachee` (`ref_piece_detachee`, `nom`, `marque`, `modele`,`prix`) VALUES
('FFF 208 RJ', 'pneu Rock rider', 'Rock rider', 'GTH HYI',30.12);
INSERT INTO `piece_en_stock` (`ref_piece_stock`, `compteur`, `login_administrateur`, `date_reception`) VALUES
('F026400343', 2, 'paul', '17-02-13');
INSERT INTO `piece_en_stock` (`ref_piece_stock`, `compteur`, `login_administrateur`, `date_reception`) VALUES
('FFF 208 RJ', 2, 'paul','17-02-13');
INSERT INTO `operation` (`nom`, `date_debut`, `date_fin`, `duree`, `degre_urgence`, `login_reparateur`, `id_panne`) VALUES
('changement filtre a air', '2017-02-12', '2017-02-12', '01:00:00', 3, 'henri', 1);
INSERT INTO `piece_conso` (`ref_piece_stock`, `nombre`, `id_operation`) VALUES
('F026400343', 1, 1);
