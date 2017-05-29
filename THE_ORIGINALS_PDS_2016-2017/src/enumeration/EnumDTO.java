package enumeration;

/**
 * Created by tearsyu on 12/04/17.
 */
public enum EnumDTO {
    ADMIN("AdministratorDTO",1), BREAKDOWN("BreakdownDTO", 2), DIRECTOR("DirectorDTO", 3), PARKING("ParkingDTO", 4),
    PIECE_COMSO("Piece_consumptionDTO", 5), PIECE_DETACHED("Piece_detachedDTO",6), PIECE_PURCH("Piece_purchaseDTO",6),
    PIECE_STK("Piece_stockDTO", 7), REPAIRER("Repairer", 8), VEHICLE_WH("Vehicle_warehouse", 9), VEHICLE("Vehicle", 10),
    WH("WarehouseDTO", 11), INDICATOR("Indicator", 12);

    int i;
    String nameDTO;

    EnumDTO(String nameDTO, int i) {
        this.i = i;
        this.nameDTO = nameDTO;
    }


    public String getName(){
        return nameDTO;
    }

    public int getIndex(EnumDTO enumdto){
        return enumdto.i;
    }
}
