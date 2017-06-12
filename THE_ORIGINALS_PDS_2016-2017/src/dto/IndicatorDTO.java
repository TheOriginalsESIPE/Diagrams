package dto;
/**
 * Created by tearsyu on 21/05/17.
 */
public class IndicatorDTO {
    private String numMat;
    private String vType;
    private String repairer;
    private String dateB, dateE, breaktype;
    private int id, pieceConso;

    public IndicatorDTO(){}
    public IndicatorDTO(String numMat, String vType, String repairer, String dateB, String dateE, String breaktype,
                        int id, int pieceConso){
        this.numMat = numMat;
        this.vType = vType;
        this.repairer = repairer;
        this.dateB = dateB;
        this.dateE = dateE;
        this.breaktype = breaktype;
        this.id = id;
        this.pieceConso = pieceConso;
    }

    public String getNumMat() {
        return numMat;
    }

    public void setNumMat(String numMat) {
        this.numMat = numMat;
    }

    public String getvType() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType = vType;
    }

    public String getRepairer() {
        return repairer;
    }

    public void setRepairer(String repairer)
    {
        this.repairer = repairer;
    }

    public String getDateB() {
        return dateB;
    }

    public void setDateB(String dateB) {
        this.dateB = dateB;
    }

    public String getDateE() {
        return dateE;
    }

    public void setDateE(String dateE) {
        this.dateE = dateE;
    }

    public String getBreaktype() {
        return breaktype;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getPieceConso(){
        return pieceConso;
    }

    public void setPieceConso(int pieceConso){
        this.pieceConso = pieceConso;
    }


    @Override
    public String toString() {
        return "IndicatorDTO{" +
                "numMat='" + numMat + '\'' +
                ", vType='" + vType + '\'' +
                ", repairer='" + repairer + '\'' +
                ", dateB='" + dateB + '\'' +
                ", dateE='" + dateE + '\'' +
                ", breaktype='" + breaktype + '\'' +
                '}';
    }

    public void setBreaktype(String breaktype) {
        this.breaktype = breaktype;
    }
}
