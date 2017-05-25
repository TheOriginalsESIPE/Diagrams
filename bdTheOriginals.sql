CREATE TABLE IF NOT EXISTS repairer ( 
   login varchar(20) NOT NULL, 
   password varchar(20) NOT NULL, 
   lastName varchar(50) NOT NULL, 
   firstName varchar(50) NOT NULL, 
   adress varchar(200), 
   numTel varchar(10), 
  PRIMARY KEY (login)  
);
CREATE TABLE IF NOT EXISTS manutentionaire( 
   login varchar(20) NOT NULL, 
   password varchar(20) NOT NULL, 
   lastName varchar(50) NOT NULL, 
   firstName varchar(50) NOT NULL, 
   adress varchar(200), 
   numTel varchar(10), 
  PRIMARY KEY (login)  
);
CREATE TABLE IF NOT EXISTS administrator ( 
   login varchar(20) NOT NULL, 
   password varchar(20) NOT NULL, 
   lastName varchar(50) NOT NULL, 
   firstName varchar(50) NOT NULL, 
   adress varchar(200), 
   numTel varchar(10), 
  PRIMARY KEY (login)  
); 

 CREATE TABLE IF NOT EXISTS operation ( 
   id_operation int(11) NOT NULL AUTO_INCREMENT, 
   id_breakdown varchar(100) NOT NULL,
   time_begin time, 
   time_end time, 
   date_begin date,
   date_end date,
   done enum("1","2","3"),
   login_repairer varchar(20) NOT NULL, 
   numMat varchar(200) NOT NULL,    
   PRIMARY KEY (id_operation)
); 
CREATE TABLE IF NOT EXISTS operation_sort ( 
   id_operation_sort int(11) NOT NULL AUTO_INCREMENT, 
   id_breakdown varchar(100) NOT NULL,
   numMat varchar(200) NOT NULL,
   emergency_degree int(11) NOT NULL,   
   PRIMARY KEY (id_operation_sort) 
); 

CREATE TABLE IF NOT EXISTS breakdown ( 
   id_breakdown int(11) NOT NULL AUTO_INCREMENT,
   name varchar(100) NOT NULL, 
   description varchar(300) NOT NULL, 
   duree time,
   emergency_level enum("1","2","3","4","5","6","7","8","9","10"),
   PRIMARY KEY (id_breakdown) 
);  
 
CREATE TABLE IF NOT EXISTS vehicle ( 
   numMat varchar(200) NOT NULL, 
   model varchar(200) NOT NULL, 
   mark varchar(200) NOT NULL, 
   vehicle_type enum('velo', 'voiture') NOT NULL, 
   PRIMARY KEY (numMat) 
); 

 
CREATE TABLE IF NOT EXISTS vehicle_warehouse ( 
	numMat varchar(50) NOT NULL, 
	numPlace int(11) NOT NULL, 
	date_entrance date NOT NULL, 
	date_wayout date,
	status enum("repare","verifie","en circulation","attente pieces","en reparation","en attente"),
	PRIMARY KEY (numMat)  	
); 

CREATE TABLE IF NOT EXISTS piece_stock ( 
   ref_piece_stock varchar(11) NOT NULL, 
   counter int(11) NOT NULL,
   seuil int(11) NOT NULL, 
   login_administrator varchar(20), 
   date_reception date NOT NULL, 
   PRIMARY KEY (ref_piece_stock) 
); 

 
CREATE TABLE IF NOT EXISTS piece_consumption ( 
   ref_piece_stock varchar(100) NOT NULL, 
   nomber int(11) NOT NULL, 
   id_operation int(11) NOT NULL 
);

CREATE TABLE IF NOT EXISTS piece_detached( 
 	ref_piece_detached varchar(11) NOT NULL, 
 	price decimal(7,2) NOT NULL, 
 	number int(11) NOT NULL, 
  	date_purchase DATE NOT NULL,
    PRIMARY KEY (ref_piece_detached) 	
); 

CREATE TABLE IF NOT EXISTS critere ( 
   piece_stock int(11) NOT NULL, 
   emergency_level int(11) NOT NULL, 
   date_entrance int(11) NOT NULL,
   id_operation_sort int(11) NOT NULL,
   PRIMARY KEY (id_operation_sort) 
); 
CREATE TABLE IF NOT EXISTS parking ( 
   numPlace int(11) NOT NULL, 
   id_warehouse int(11) NOT NULL, 
   PRIMARY KEY (numPlace) 
); 
CREATE TABLE IF NOT EXISTS warehouse ( 
   id_warehouse int(11) NOT NULL AUTO_INCREMENT, 
   name varchar(100) NOT NULL, 
   volum int(11) NOT NULL, 
   adress varchar(200), 
   numTel varchar(10), 
   login_director varchar(20) NOT NULL, 
   PRIMARY KEY (id_warehouse) 
); 

CREATE TABLE IF NOT EXISTS director ( 
   login varchar(20) NOT NULL, 
   password varchar(20) NOT NULL, 
   lastName varchar(50) NOT NULL, 
   firstName varchar(50) NOT NULL, 
   adress varchar(200), 
   numTel varchar(10), 
   PRIMARY KEY (login) 
);
ALTER TABLE operation
ADD CONSTRAINT FK_numMat FOREIGN KEY (numMat) REFERENCES vehicle_warehouse (numMat) ON DELETE CASCADE;

ALTER TABLE operation
ADD CONSTRAINT FK_repairer FOREIGN KEY (login_repairer) REFERENCES repairer (login) ON DELETE CASCADE;

ALTER TABLE piece_stock
ADD CONSTRAINT FK_administrator FOREIGN KEY (login_administrator) REFERENCES administrator(login) ON DELETE CASCADE;

ALTER TABLE vehicle_warehouse
ADD CONSTRAINT FK_vehicle FOREIGN KEY (numMat) REFERENCES vehicle (numMat) ON DELETE CASCADE; 

ALTER TABLE piece_stock 
ADD CONSTRAINT FK_piece_detached FOREIGN KEY (ref_piece_stock) REFERENCES piece_detached (ref_piece_detached) ON DELETE CASCADE; 

 
ALTER TABLE piece_consumption
ADD CONSTRAINT FK_ref_piece_stock FOREIGN KEY (ref_piece_stock) REFERENCES piece_stock (ref_piece_stock); 

ALTER TABLE critere
ADD CONSTRAINT FK_id_operation_sort FOREIGN KEY (id_operation_sort) REFERENCES operation_sort (id_operation_sort); 

ALTER TABLE warehouse
ADD CONSTRAINT FK_director FOREIGN KEY (login_director) REFERENCES director (login) ON DELETE CASCADE;

ALTER TABLE parking 
ADD CONSTRAINT FK_warehouse2 FOREIGN KEY (id_warehouse) REFERENCES warehouse (id_warehouse) ON DELETE CASCADE; 
 

INSERT INTO vehicle VALUES ('AAA','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('BBB','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('CCC','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('DDD','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('EEE','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('FFF','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('GGG','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('HHH','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('III','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('JJJ','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('KKK','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('LLL','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('MMM','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('NNN','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('OOO','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('PPP','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('QQQ','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('RRR','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('SSS','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('TTT','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('UUU','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('VVV','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('WWW','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('XXX','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('YYY','XXX', 'XXX', 'velo');
INSERT INTO vehicle VALUES ('ZZZ','XXX', 'XXX', 'velo');



INSERT INTO repairer VALUES ('henri', 'henri', 'henri', 'henri','5 allee des boutons 78000', '0108995118');
INSERT INTO administrator VALUES ('jack', 'jack', 'jack', 'jack','13 allee des bourgeons 86000', '0108995177');
INSERT INTO director VALUES ('paul', 'paul', 'dupont', 'paul','5 allee des boutons 95000', '0148995118');

INSERT INTO warehouse VALUES (1,'depot1', 500, '5 allee des boutons 95000', '0148995118', 'paul');

INSERT INTO parking VALUES (1, 1);
INSERT INTO parking VALUES (2, 1);
INSERT INTO parking VALUES (3, 1);
INSERT INTO parking VALUES (4, 1);
INSERT INTO parking VALUES (5, 1);
INSERT INTO parking VALUES (6, 1);
INSERT INTO parking VALUES (7, 1);

INSERT INTO vehicle_warehouse VALUES ('AAA',2, '2017/02/09', null, 'en attente');
INSERT INTO vehicle_warehouse VALUES ('BBB',1, '2017/02/17', '2017/05/30', 'en attente');
INSERT INTO vehicle_warehouse VALUES ('CCC',3, '2017/02/13', null, 'en attente');
INSERT INTO vehicle_warehouse VALUES ('DDD',4, '2017/02/28', null, 'en attente');
INSERT INTO vehicle_warehouse VALUES ('EEE',5, '2017/02/12', null, 'en attente');



INSERT INTO breakdown VALUES (1, 'XXX', 'GGGYFYFFYF', '00:10:00', '1');
INSERT INTO breakdown VALUES (2, 'AAAAAA', 'DDEDFEF', '00:30:00', '5');
INSERT INTO breakdown VALUES (3, 'FGDFF', 'SDDDF', '01:00:00', '10');


INSERT INTO piece_detached VALUES ('FFFF4545', 50.12, 50, 2017/02/25);
INSERT INTO piece_detached VALUES ('FFFF0005', 4.20, 10, 2017/02/20);
INSERT INTO piece_detached VALUES ('FFFF5678', 0.20, 100, 2017/04/26);
INSERT INTO piece_detached VALUES ('FFFF5412', 30, 20, 2017/12/15);

INSERT INTO piece_stock VALUES ('FFFF4545', 50, 15, 'jack', 2017/02/28);
INSERT INTO piece_stock VALUES ('FFFF0005', 5, 1, 'jack', 2017/02/23);
INSERT INTO piece_stock VALUES ('FFFF5678', 100, 100, 'jack', 2017/04/28);
INSERT INTO piece_stock VALUES ('FFFF5412', 30, 20, 'jack', 2017/12/20);

