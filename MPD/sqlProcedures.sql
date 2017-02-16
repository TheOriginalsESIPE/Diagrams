DELIMITER //
DROP PROCEDURE IF EXISTS add_piece;
CREATE PROCEDURE add_piece(IN ref_piece varchar(11), IN nb_piece int, OUT info int)
BEGIN
  SELECT 0 INTO info;
  IF EXISTS (SELECT ref_piece_stock
      FROM piece_en_stock WHERE ref_piece = ref_piece_stock)
  THEN
      UPDATE piece_en_stock SET compteur = compteur + nb_piece
      WHERE ref_piece = ref_piece_stock;
  ELSEIF EXISTS (SELECT ref_piece_stock FROM piece_en_stock
      WHERE ref_piece = ref_piece_stock)
  THEN
      INSERT INTO ref_piece_stock (ref_piece_stock, compteur, data_reception)
      VALUES(ref_piece, nb_piece, CURDATE());
  ELSE
      SELECT 1 INTO info;
  END IF;
END; //
DELIMITER ;

DELIMITER //
DROP FUNCTION IF EXISTS gen_login;
CREATE FUNCTION gen_login(nom varchar(50), prenom varchar(50))
RETURNS varchar(20) DETERMINISTIC
BEGIN
  DECLARE newlogin varchar(20);
  DECLARE nb int;
  SELECT COUNT(*) INTO nb FROM directeur, reparateur, administrateur
     WHERE (directeur.nom = nom AND directeur.prenom = prenom)
     OR (reparateur.nom=nom AND reparateur.prenom=prenom)
     OR (administrateur.nom = administrateur.prenom);
  SELECT CONCAT(SUBSTRING(nom, 1,1), prenom, nb) INTO newlogin;
RETURN(newlogin);
END; //
DELIMITER ;



DELIMITER //
DROP PROCEDURE IF EXISTS insert_achat;
CREATE PROCEDURE insert_achat(IN ref_piece varchar(11), IN nb int)
BEGIN
  DECLARE tarif DECIMAL(7,2);
  SELECT prix INTO tarif FROM piece_detachee WHERE ref_piece_detachee = ref_piece;
  INSERT INTO piece_achat(ref_piece_detachee, prix, number, date_achat)
  VALUES (ref_piece, tarif, nb, CURDATE());
END; //
DELIMITER ;


DELIMITER //
DROP PROCEDURE IF EXISTS add_role;
CREATE PROCEDURE add_role(IN role varchar(20), IN prenom varchar(20),
    IN nom varchar(20), IN password varchar(20), IN numTel varchar(11), IN addr varchar(200))
BEGIN
  DECLARE newlogin varchar(20);
  SELECT gen_login(nom, prenom) INTO newlogin;
  IF role = 'administrateur' THEN
    INSERT INTO  administrateur(login, password, nom, prenom, adresse, numTel)
    VALUES (newlogin, password, nom, prenom, addr, numTel);
  END IF;
  IF role = 'reparateur' THEN
    INSERT INTO reparateur(login, password, nom, prenom, adresse, numTel)
    VALUES (newlogin, password, nom, prenom, addr, numTel);
  END IF;
  IF role = 'directeur' THEN
    INSERT INTO directeur(login, password, nom, prenom, adresse, numTel)
    VALUES (newlogin, password, nom, prenom, addr, numTel);
  END IF;
END; //
DELIMITER ;
