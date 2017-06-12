package Server.repository;

import Server.serialization.Deserialization;
import Server.serialization.Serialization;
import org.json.simple.JSONObject;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by tearsyu on 27/04/17.
 */
public class ModelIndicatorActivity {
    private String vtype, from, to, btype, statu;
    public ModelIndicatorActivity(Map<String, String> indocatorMap){
        if (indocatorMap.get("vtype").equals("all"))
            this.vtype = "velo' or vehicle.vehicle_type = 'voiture";
        else if (indocatorMap.get("vtype").equals("car"))
            this.vtype = "voiture";
        else if (indocatorMap.get("vtype").equals("bike"))
            this.vtype = "velo";

        this.from = indocatorMap.get("from");
        this.to = indocatorMap.get("to");
        this.btype = indocatorMap.get("btype");
        this.statu = indocatorMap.get("statu");
        if(statu.equals("operation finie"))
            statu = "operation.done = 1";
        else statu = "operation.done = 2";

        if(btype.equals("ralenti"))
            btype = "and (breakdown.name = 'ralenti irregulier')";
        else if (btype.equals("conso"))
            btype = "and (breakdown.name = 'conso. huile' or breakdown.name = 'surconsommation')";
        else if (btype.equals("roulement"))
            btype = "(and breakdown.name = 'roulement' or breakdown.name = 'vitesse difficile a passer')" ;
        else if (btype.equals("all"))
            btype = "";

    }
    public ModelIndicatorActivity(){}

    /**
     * Example:select count(*) from operation inner join panne
     * on operation.id_panne = panne.id_panne inner join vehicule
     * on vehicule.numMat = panne.numMat where vehicule.vehicule_type = 'velo';
     * @return Example
     */
    public String getOperation(){
        return "SELECT vehicle_warehouse.numMat, vehicle.vehicle_type, breakdown.name, operation.date_begin, operation.date_end," +
                " concat(repairer.firstName, ' ', repairer.lastName) as repairer, id_operation" +
                " FROM operation INNER JOIN breakdown" +
                " ON operation.id_breakdown = breakdown.id_breakdown INNER JOIN vehicle_warehouse ON" +
                " vehicle_warehouse.numMat = operation.numMat INNER JOIN vehicle" +
                " ON vehicle.numMat = vehicle_warehouse.numMat" +
                " INNER JOIN repairer ON operation.login_repairer = repairer.login" +
                " WHERE "+ statu + " and (vehicle.vehicle_type='" + vtype + "') and (operation.date_begin >= '"+ from +
                "' and operation.date_end <= '" + to +"') " + btype + ";";
    }

    //SQL MODEL IS A LITTLE STRANGE AT THIS PART....
    public String pieceConso(){
        return "SELECT id_operation, sum(nomber) as conso FROM piece_consumption GROUP BY id_operation;";
    }

    public static void main(String[] args){
        Map<String, String> map = new HashMap();
        map.put("from", "2016-01-08");
        map.put("to", "2017-08-09");
        map.put("vtype", "all");
        map.put("btype", "all");
        map.put("statu", "operation finie");
        ModelIndicatorActivity m = new ModelIndicatorActivity(map);
        System.out.println(m.getOperation());
        System.out.println(m.pieceConso());
        Serialization s= new Serialization();
        String str = s.serialToStr(s.serialMap(map));
        System.out.println(str);
        Deserialization d = new Deserialization();
        JSONObject j = d.deserialGeneric(str);
        HashMap map2 = j;
        System.out.println(map2.get("vtype"));
    }


}
