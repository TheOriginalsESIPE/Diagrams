DELIMITER //
DROP TRIGGER IF EXISTS conso;
CREATE TRIGGER conso AFTER INSERT ON piece_conso
FOR EACH ROW
BEGIN
  UPDATE piece_en_stock SET compteur = compteur - NEW.nombre
  WHERE piece_en_stock.ref_piece_stock = NEW.ref_piece_stock;
END; //
DELIMITER ;



DELIMITER //
DROP TRIGGER IF EXISTS audit_piece_ins;
CREATE TRIGGER audit_piece_ins AFTER INSERT ON piece_en_stock
FOR EACH ROW
BEGIN
  CALL insert_achat(NEW.ref_piece_stock, NEW.compteur);
END; //
DELIMITER ;

DELIMITER //
DROP TRIGGER IF EXISTS audit_piece_up;
CREATE TRIGGER audit_piece_up AFTER UPDATE ON  piece_en_stock
FOR EACH ROW
BEGIN
  DECLARE nb int;
  IF NEW.compteur <> OLD.compteur THEN
    SELECT (NEW.compteur - OLD.compteur) INTO nb;
    CALL insert_achat(NEW.ref_piece_stock, nb);
  END IF;
END; //
DELIMITER ;
