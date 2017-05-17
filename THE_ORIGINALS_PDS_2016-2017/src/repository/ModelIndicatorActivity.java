package repository;

/**
 * Created by tearsyu on 27/04/17.
 */
public class ModelIndicatorActivity {
    private String type, timeScale;
    ModelIndicatorActivity(String type, String timeScale){
        this.type = type;
        this.timeScale = timeScale;
    }
    ModelIndicatorActivity(){}

    /**
     * Example:select count(*) from operation inner join panne
     * on operation.id_panne = panne.id_panne inner join vehicule
     * on vehicule.numMat = panne.numMat where vehicule.vehicule_type = 'velo';
     * @return Example
     */
    public String getNumOperation(){
        return "SELECT count(*) FROM operation INNER JOIN panne " +
                "ON operation.id_panne = panne.id_panne INNER JOIN vehicule ON" +
                " vehicule.numMat = panne.numMat WHERE vehicule.vehicule_type='"
                + type + "';";
    }


}
