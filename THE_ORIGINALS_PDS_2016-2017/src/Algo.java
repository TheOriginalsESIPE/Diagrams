import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import dto.*;
import repository.*;
import server.*;

public class Algo {
	public static int cpt = 0;
	private LinkedList<OperationSortAnais> sort;
	private ControllerAnais controller;
	private ModelAnais model;
	
	public Algo() {
		sort = new LinkedList<OperationSortAnais>();
		this.model = new ModelAnais();
		this.controller = new ControllerAnais(model);
	}
	
	public ControllerAnais getController() {
		return controller;
	}
	
	public ModelAnais getModel() {
		return model;
	}
	
	public LinkedList<OperationSortAnais> getSort() {
		return sort;
	}
	
	public void sortAlgorithm(boolean appel) throws SQLException {
		
		// On vide la table operation_sort au depart (on utilise un boolean dont on va se servir a la fin)
		boolean delete = controller.deleteOperationSortTable();
		
		// Si l'appel de l'algo est fait apres une prise en charge alors on incremente le compteur (pour le modulo)
		// Si c'est apres un ajout de piece dans la base ou l'ajout d'une operation alors on incremente pas (appel de l'algo avec appel=false)
		
		if(appel == true)
			cpt++;
		else
			cpt = 1; // car si cpt = 0 alors cpt % 20 = 0 et donc mise en priorite d'une grosse operation 
		
		LinkedList<OperationAnais> tmp = new LinkedList<OperationAnais>(); // la liste qui contient les operations a trier
		
		// criteresValues[0] -> critere piece stock | criteresValues[1] -> critere emergency_level | criteresValues[2] -> critere date_entrance | criteresValues[3] -> critere duree
		int [] criteresValues = controller.selectCriteres();
		tmp = controller.selectAll();
		
		// On commence le traitement pour chaque operation
		
		for(int i = 0 ; i < tmp.size() ; i++) {
			
			int note = 0; // note à 0 au debut et on l'incremente au fur et a mesure
			int done = tmp.get(i).getDone();
			
			// Si done=2 alors on ajoute a la note la valeur du critere piece stock sinon non (pour que les oprations en manque de pieces soient en fin de liste)
			
			if(done == 2) {
				note += criteresValues[0];
			}
			
			// Traitement des dates d'entrees des vehicules
			
			String date_str = controller.selectDate(tmp.get(i).getId_operation());
			Timestamp date = Timestamp.valueOf(date_str+" 00:00:00"); // date d'entree du vehicule
			Instant now = Instant.now();
			Timestamp today = Timestamp.from(now); // date actuelle
			long difference = Math.round((today.getTime() - date.getTime()) * 1.16 * Math.pow(10, -8));
			if(difference <= 90)
				note += criteresValues[2];
			else if(difference > 90 && difference <= 110)
				note += criteresValues[2] + 2;
			else if(difference > 110 && difference <= 120)
				note += criteresValues[2] + 4;
			else
				note += criteresValues[2] + 6;
			
			// Traitement des degrés d'urgence des opérations
			
			int emergency_level = controller.selectEmergencyLevel(tmp.get(i).getId_breakdown());
			if(emergency_level <= 2)
				note += criteresValues[1]/2;
			else if(emergency_level > 2 && emergency_level <= 4) 
				note += criteresValues[1];
			else if(emergency_level > 4 && emergency_level <= 6)
				note += criteresValues[1] + 2;
			else if(emergency_level > 6 && emergency_level <= 8)
				note += criteresValues[1] + 4;
			else
				note += criteresValues[1] + 6;
			
			// Traitement de la duree de l'operation
			
			Time seuil0 = Time.valueOf("00:10:00");
			Time seuil1 = Time.valueOf("00:25:00");
			Time seuil2 = Time.valueOf("01:00:00");
			Time seuil3 = Time.valueOf("01:30:00");
			Time seuil4 = Time.valueOf("02:00:00");
			Time duree = controller.selectDuree(tmp.get(i));
			if(duree.compareTo(seuil0) <= 0) 
				note += criteresValues[3] + 10;
			else if(duree.compareTo(seuil0) >= 0 && duree.compareTo(seuil1) < 0)
				note += criteresValues[3] + 6;
			else if(duree.compareTo(seuil1) >= 0 && duree.compareTo(seuil2) < 0)
				note += criteresValues[3] + 2;
			else if(duree.compareTo(seuil2) >= 0 && duree.compareTo(seuil3) < 0)
				note += criteresValues[3] + 1;
			else if(duree.compareTo(seuil3) >= 0 && duree.compareTo(seuil4) < 0)
				note += criteresValues[3];
			else
				note += 0;
			
			// On cree un objet OperationSort qui contient la note calculee
			
			OperationSortAnais oper = new OperationSortAnais(tmp.get(i), note);
			
			//On ajoute cet objet a notre liste d'operation qu'on va trier
			
			sort.add(oper);
		}
		
		// On trie selon la note (en sens decroissant car plus la note est elevee plus l'operation est importante)
		
		Collections.sort((List) sort, Collections.reverseOrder());
		
		// Toutes les 20 operations effectuees, on prend en charge une grosse operation en terme de duree (on la met en premiere position)
		
		if(cpt%20 == 0) {
			for(int i = 0 ; i < sort.size(); i++) {
				if(controller.selectDuree(sort.get(i).getOperation()).compareTo(Time.valueOf("01:10:00")) > 0) {
					OperationSortAnais op = sort.remove(i);
					sort.addFirst(op);
					break;
				}
			}
		}
		
		// On insert la nouvelle liste priorisee en verifiant que la table a bien ete videe avant
		if(delete == true) {
			for(int i = 0 ; i < sort.size() ; i++) {
				controller.insertOperationSort(sort.get(i), i+1);
			}
		}
		sort.clear();
		
	}

}
