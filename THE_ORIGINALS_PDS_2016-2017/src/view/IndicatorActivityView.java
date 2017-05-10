package view;

import client.Client;
import org.jdesktop.swingx.JXDatePicker;
import server.ControllerIndicatorActivity;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by tearsyu on 26/04/17.
 * "Destiné au chef du dépôt.
 * Celui-ci peut calculer et afficher de nombreux indicateurs tels que le nombre d'opérations, leur durée moyenne,
 * le nombre de pièces consommées, etc.
 * Il pourra les décliner dynamiquement suivant plusieurs axes combinables: par type de véhicule, par manutentionnaire,
 * par période de temps (semaine, mois, année), etc.  Exemples:
 * nombre total de réparations pour crevaison effectuées au cours du mois passé, sur des vélos en libre service
 * évolution semaine par semaine sur l'année en cours du nombre total d'opérations effectuées au sein du dépôt"
 * @author tearsyu
 */
public class IndicatorActivityView extends JFrame{
    private JButton bOk, bApply;
    private JScrollPane scrollp;
    private JLabel lnbOp, lDuring, lConso;
    private JLabel getLnbOp, getlDuring, getlConso;
    public JCheckBox bike, car;
    private Border border;
    private JPanel pRapport, pVehicle, pTimeScale;
    public JComboBox typeBreakdown, status;
    public JXDatePicker from, to;
    public JTable table;

    public JButton getbOk() {
        return bOk;
    }

    public JButton getbApply() {
        return bApply;
    }


    public IndicatorActivityView(){
        super("Operation Indicator");
        setLayout(null);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Rapport area
        pRapport = new JPanel();
        border = BorderFactory.createLineBorder(Color.black);
        pRapport.setBorder(BorderFactory.createTitledBorder(border, "Rapport"));
        pRapport.setLayout(new GridLayout(6,1));
        pRapport.setBounds(430,20,200,300);

        lnbOp = new JLabel("nombre de l'operation:");
        getLnbOp = new JLabel("xx ");
        lConso = new JLabel("piece conso:");
        getlConso = new JLabel("xx              ");
        lDuring = new JLabel("Duration moyenne:");
        getlDuring = new JLabel("xx             ");

        pRapport.add(lnbOp);
        pRapport.add(getLnbOp);
        pRapport.add(lConso);
        pRapport.add(getlConso);
        pRapport.add(lDuring);
        pRapport.add(getlDuring);

        typeBreakdown = new JComboBox();
        status = new JComboBox();
        //Add diff type breakdown and status
        status.addItem("wait for repairing");
        status.addItem("repairing");
        status.addItem("checking");
        typeBreakdown.addItem("windows broken");
        typeBreakdown.addItem("all burst up");
        typeBreakdown.addItem("tyre");
        status.setBounds(430, 350, 200, 30);
        typeBreakdown.setBounds(430, 400, 200, 30);
        this.add(typeBreakdown);
        this.add(status);

        //Vehicle RadioButton Area
        pVehicle = new JPanel();
        pVehicle.setBorder(BorderFactory.createTitledBorder(border, "Vehicle Type"));
        pVehicle.setLayout(new FlowLayout(FlowLayout.LEFT));
        pVehicle.setBounds(20, 450, 200, 50);
        bike = new JCheckBox("Velo");
        bike.setActionCommand("bike");
        car = new JCheckBox("Voiture");
        car.setActionCommand("car");
        pVehicle.add(bike, false);
        pVehicle.add(car, false);


        //Time Scale Radio Button Area
        pTimeScale = new JPanel();
        pTimeScale.setBorder(BorderFactory.createTitledBorder(border, "From/to"));
        pTimeScale.setLayout(null);
        pTimeScale.setBounds(300, 450, 280, 50);
        //canlendar
        from = new JXDatePicker();
        from.setDate(Calendar.getInstance().getTime());
        from.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        from.setBounds(10, 15, 120, 30);
        to = new JXDatePicker();
        to.setDate(Calendar.getInstance().getTime());
        to.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        to.setBounds(140, 15, 120, 30);
        pTimeScale.add(from);
        pTimeScale.add(to);



        //Table
        table = new JTable(new IndicatorTable());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        //Text area
        scrollp = new JScrollPane(table);
        scrollp.setBounds(20, 20, 400, 400);


        //Button
        bApply = new JButton("Apply");
        bApply.setBounds(20, 520, 100, 30);
        bOk = new JButton("Export as PDF");
        bOk.setEnabled(false);
        bOk.setBounds(500, 520, 180, 30);



        this.getContentPane().add(scrollp);
        this.getContentPane().add(pRapport);
        this.getContentPane().add(pVehicle);
        this.getContentPane().add(pTimeScale);
        this.getContentPane().add(bApply);
        this.getContentPane().add(bOk);

    }


    public void disposeView(){
        this.dispose();
    }

    public static void main(String[] args){
        IndicatorActivityView ia = new IndicatorActivityView();
        ia.setVisible(true);
        try {
            Client c = new Client();
            if (c.connectToServer()) {
                ControllerIndicatorActivity ci = new ControllerIndicatorActivity(ia, c.getClient());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ci.control();


    }

    private class IndicatorTable extends AbstractTableModel {

        private String[] columnNames = {"Vehicle Type", "Number Vehicle", "Status", "Responsible"};
        /**
         * Returns the number of rows in the model. A
         * <code>JTable</code> uses this method to determine how many rows it
         * should display.  This method should be quick, as it
         * is called frequently during rendering.
         *
         * @return the number of rows in the model
         * @see #getColumnCount
         */
        @Override
        public int getRowCount() {
            return 0;
        }

        /**
         * Returns the number of columns in the model. A
         * <code>JTable</code> uses this method to determine how many columns it
         * should create and display by default.
         *
         * @return the number of columns in the model
         * @see #getRowCount
         */
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        /**
         * Returns the name of the column at <code>columnIndex</code>.  This is used
         * to initialize the table's column header name.  Note: this name does
         * not need to be unique; two columns in a table can have the same name.
         *
         * @param columnIndex the index of the column
         * @return the name of the column
         */
        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        /**
         * Returns the most specific superclass for all the cell values
         * in the column.  This is used by the <code>JTable</code> to set up a
         * default renderer and editor for the column.
         *
         * @param columnIndex the index of the column
         * @return the common ancestor class of the object values in the model.
         */
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return null;
        }

        /**
         * Returns true if the cell at <code>rowIndex</code> and
         * <code>columnIndex</code>
         * is editable.  Otherwise, <code>setValueAt</code> on the cell will not
         * change the value of that cell.
         *
         * @param rowIndex    the row whose value to be queried
         * @param columnIndex the column whose value to be queried
         * @return true if the cell is editable
         * @see #setValueAt
         */
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        /**
         * Returns the value for the cell at <code>columnIndex</code> and
         * <code>rowIndex</code>.
         *
         * @param rowIndex    the row whose value is to be queried
         * @param columnIndex the column whose value is to be queried
         * @return the value Object at the specified cell
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return null;
        }

        /**
         * Sets the value in the cell at <code>columnIndex</code> and
         * <code>rowIndex</code> to <code>aValue</code>.
         *
         * @param aValue      the new value
         * @param rowIndex    the row whose value is to be changed
         * @param columnIndex the column whose value is to be changed
         * @see #getValueAt
         * @see #isCellEditable
         */
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        }

        /**
         * Adds a listener to the list that is notified each time a change
         * to the data model occurs.
         *
         * @param l the TableModelListener
         */
        @Override
        public void addTableModelListener(TableModelListener l) {

        }

        /**
         * Removes a listener from the list that is notified each time a
         * change to the data model occurs.
         *
         * @param l the TableModelListener
         */
        @Override
        public void removeTableModelListener(TableModelListener l) {

        }
    }
}
