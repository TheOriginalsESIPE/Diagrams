package Client.dto;

public class OperationSortAnais implements Comparable{
	private int id_operation_sort;
	private OperationAnais operation;
	private int note;
	private static int cpt = 1;
	
	public OperationSortAnais(OperationAnais operation, int note) {
		this.id_operation_sort = cpt;
		cpt++;
		this.operation = operation;
		this.note = note;
	}
	
	public int getId() {
		return id_operation_sort;
	}
	
	public OperationAnais getOperation() {
		return operation;
	}
	
	public int getNote() {
		return note;
	}
	
	public void setNote(int value) {
		note = value;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		OperationSortAnais operation_sort = (OperationSortAnais) arg0;
		return ((Integer) note).compareTo((Integer) operation_sort.getNote());
	}
}
