
 
CREATE TABLE IF NOT EXISTS `repairer` ( 
   `login` varchar(100) NOT NULL, 
   `password` varchar(100) NOT NULL, 
   `firstName` varchar(100) NOT NULL, 
   `lastName` varchar(100) NOT NULL, 
   `numTel` varchar(10), 
   `adress` varchar(200), 
   PRIMARY KEY (`login`) 
); 

 

CREATE TABLE IF NOT EXISTS `piece_detached` ( 
   `ref_piece_detached` varchar(11) NOT NULL, 
   `name` varchar(100) NOT NULL, 
   `mark` varchar(100) NOT NULL, 
   `model` varchar(100) NOT NULL, 
   `price` decimal(7,2) NOT NULL, 
   PRIMARY KEY (`ref_piece_detached`) 
); 
 






INSERT INTO piece_detached (`ref_piece_detached`, `name`, `mark`, `model`, `price`) VALUES
('DG909DG', 'moteur', 'Renault', 'XXXX', 545.02);

