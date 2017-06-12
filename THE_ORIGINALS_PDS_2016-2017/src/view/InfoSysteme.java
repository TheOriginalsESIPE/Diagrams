package view;

public class InfoSysteme {
	  private String numPlaceParcking, duree;

	  public InfoSysteme(){}
	  public InfoSysteme(String numPlaceParcking, String duree){
	    this.numPlaceParcking = numPlaceParcking;
	    this.duree = duree;
	  }

	  public String toString(){
	    String str;
	    if(this.numPlaceParcking != null && this.duree != null){
	      str = "Les information sur le vehicle";
	      str += "Numéro de place dans le Parcking : " + this.numPlaceParcking + "\n";
	      str += "Durée de reparation : " + this.duree + " munites\n";
	    }
	    else{
	      str = "Aucune information !";
	    }
	    return str;
	  }
}