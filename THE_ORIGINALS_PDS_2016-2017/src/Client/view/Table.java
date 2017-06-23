package Client.view;

import javax.swing.table.AbstractTableModel;
import javax.swing.*;

public class Table extends AbstractTableModel{
	
	private String[] nom={"numMat","numPlace","dateEntance","dateWayout","dateBeginOperation","dateEndOperation","status"};
	private String[][] donnees;
	
	public Table(){}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return nom.length;
	}
	@Override
	public int getRowCount() {
		if(donnees==null){
			return 0;
		}
		else return donnees.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return donnees[rowIndex][columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		return nom[column];
	}
	
	public String[][] getDonnees() {
		return donnees;
	}

	public void setDonnees(String[][] donnees) {
		this.donnees = donnees;
	}
	
	

}
