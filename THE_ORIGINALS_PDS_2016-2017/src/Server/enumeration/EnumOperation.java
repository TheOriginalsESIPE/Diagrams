package Server.enumeration;

/**
 * Created by tearsyu on 23/03/17.
 * This class is just to create a Client.enumeration for 4 operation, they are
 * Update, Delete, Search, Insert.
 * But if we need to create more enum in the future, it's workable in using this enum, too.
 * Just new a EnumOperation in signing the operation's name and it's index.
 * @author tearsyu
 */
public enum  EnumOperation {
    UPDATE("Update", 1), DELETE("Delete", 2), SEARCH("Search", 3),
    INSERT("Insert", 4), RESPONSE("Response", 5);
    private String name;
    private int index;

    /**
     *
     * @param operation the operation name
     * @param i the index
     */
    EnumOperation(String operation, int i) {
        this.name = operation;
        this.index = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static String getNameByIndex(int i) {
        if(i == 1)
            return EnumOperation.UPDATE.getName();
        else if (i == 2)
            return EnumOperation.DELETE.getName();
        else if (i == 3)
            return EnumOperation.SEARCH.getName();
        else if (i == 4)
            return EnumOperation.INSERT.getName();
        else
            return "Index of action you have asked doesn't exist.";
    }
}
