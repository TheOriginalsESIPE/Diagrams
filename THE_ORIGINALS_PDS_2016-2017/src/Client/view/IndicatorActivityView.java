package Client.view;

import Client.client.Client;
import Client.dto.IndicatorDTO;
import Client.enumeration.BreakdownType;
import Client.controller.ControllerIndicatorActivity;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by tearsyu on 26/04/17.
 * "Destiné au chef du dépôt.
 * Celui-ci peut calculer et afficher de nombreux indicateurs tels que le nombre d'opérations, leur durée moyenne,
 * le nombre de pièces consommées, etc.
 * Il pourra les décliner dynamiquement suivant plusieurs axes combinables: par type de véhicule, par statu,
 * par période de temps (semaine, mois, année), etc.  Exemples:
 * nombre total de réparations pour crevaison effectuées au cours du mois passé, sur des vélos en libre Server.service
 * évolution semaine par semaine sur l'année en cours du nombre total d'opérations effectuées au sein du dépôt"
 * @author tearsyu
 */
public class IndicatorActivityView extends JFrame{
    private JButton bOk, bApply, bExit;
    public JScrollPane scrollp, scrollText;
    public JTextArea rapportArea;
    private JLabel lnbOp, lDuring, lConso;
    private JLabel getLnbOp, getlDuring, getlConso;
    public JCheckBox bike, car;
    private Border border;
    private JPanel pRapport, pVehicle, pTimeChooser, pTimeScale;
    public JComboBox typeBreakdown, statu;
    public JXDatePicker from, to;
    private JTable table;
    public IndicatorTable itable;
    private DecimalFormat df; // To define the double number

    private JRadioButton week, month, year;
    public ButtonGroup timeScale;

    public JButton getbOk() {
        return bOk;
    }

    public JButton getbApply() {
        return bApply;
    }

    public JButton getbExit() {
        return bExit;
    }

    public JTable getTable(){return table;}
    public IndicatorActivityView(){
        super("Operation Indicator");
        setLayout(null);
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        df = new DecimalFormat("#.##");

        //Rapport area
        pRapport = new JPanel();
        rapportArea = new JTextArea();
        scrollText = new JScrollPane(rapportArea);
        scrollText.setBounds(5,20, 390, 370);
        scrollText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        border = BorderFactory.createLineBorder(Color.gray);
        pRapport.setBorder(BorderFactory.createTitledBorder(border, "Rapport"));
        pRapport.setLayout(null);
        pRapport.setBounds(430,20,400,400);
        rapportArea.setEditable(false);

        lnbOp = new JLabel("nombre de l'opération: ");
        getLnbOp = new JLabel();
        lConso = new JLabel("pieces consomées en totale: ");
        getlConso = new JLabel();
        lDuring = new JLabel("Durée moyenne: ");
        getlDuring = new JLabel();


        pRapport.add(scrollText);


        // Constraint statu, type, time, timeScale, and type vehicle

        //Time scale radio button
        pTimeScale = new JPanel();
        pTimeScale.setLayout(new FlowLayout(FlowLayout.LEFT));
        pTimeScale.setBorder(BorderFactory.createTitledBorder(border, "Echèlle du temps"));
        pTimeScale.setBounds(20, 440, 240, 50);
        timeScale= new ButtonGroup();
        week = new JRadioButton("Week");
        week.setActionCommand("week");
        month = new JRadioButton("Month");
        month.setActionCommand("month");
        year = new JRadioButton("Year");
        year.setActionCommand("year");
        timeScale.add(week);
        timeScale.add(month);
        timeScale.add(year);

        pTimeScale.add(week);
        pTimeScale.add(month);
        pTimeScale.add(year);



        typeBreakdown = new JComboBox();
        statu = new JComboBox();
        //Add diff type breakdown and statu
        statu.addItem("operation finie");
        statu.addItem("operation non finie");

        for(BreakdownType ele : BreakdownType.values()){
            typeBreakdown.addItem(ele.name().toLowerCase());
        }

        statu.setBounds(400, 500, 200, 30);
        typeBreakdown.setBounds(400, 550, 200, 30);
        this.add(typeBreakdown);
        this.add(statu);

        //Vehicle RadioButton Area
        pVehicle = new JPanel();
        pVehicle.setBorder(BorderFactory.createTitledBorder(border, "Type de Véhicule"));
        pVehicle.setLayout(new FlowLayout(FlowLayout.LEFT));
        pVehicle.setBounds(20, 560, 200, 50);
        bike = new JCheckBox("Velo");
        bike.setActionCommand("bike");
        car = new JCheckBox("Voiture");
        car.setActionCommand("car");
        pVehicle.add(bike, false);
        pVehicle.add(car, false);


        //Time choose Area
        pTimeChooser = new JPanel();
        pTimeChooser.setBorder(BorderFactory.createTitledBorder(border, "De/à"));
        pTimeChooser.setLayout(new FlowLayout(FlowLayout.LEFT));
        pTimeChooser.setBounds(20, 500, 280, 50);
        //canlendar
        from = new JXDatePicker();
        from.setDate(Calendar.getInstance().getTime());
        from.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        from.setBounds(10, 15, 120, 30);
        to = new JXDatePicker();
        to.setDate(Calendar.getInstance().getTime());
        to.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        to.setBounds(140, 15, 120, 30);
        pTimeChooser.add(from);
        pTimeChooser.add(to);



        //Table
        itable = new IndicatorTable();
        table = new JTable(itable);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        //Text area
        scrollp = new JScrollPane(table);
        scrollp.setBounds(20, 20, 400, 400);


        //Button
        bApply = new JButton("Confirmer");
        bApply.setBounds(20, 630, 150, 30);
        bOk = new JButton("Exporter en PDF");
        bOk.setEnabled(false);
        bOk.setBounds(250, 630, 180, 30);
        bExit = new JButton("Quitter");
        bExit.setBounds(500, 630, 180, 30);


        this.getContentPane().add(scrollp);
        this.getContentPane().add(pRapport);
        this.getContentPane().add(pVehicle);
        this.getContentPane().add(pTimeScale);
        this.getContentPane().add(pTimeChooser);
        this.getContentPane().add(bApply);
        this.getContentPane().add(bOk);
        this.getContentPane().add(bExit);

    }

    /**
     * Show error info
     * @param errorCode 1-> date end < date begin 2-> week, date end - date begin > 8 weeks
     *                  3-> month, date end - date begin > 12 month, 4-> year, date end - date begin > 5 years
     */
    public void msgDialog(int errorCode){
        String msg = "";
        switch(errorCode) {
            case 1:
                msg = "Les dates que vous avez choisi ne sont pas logiques.\n" +
                        "Rechoisir des bonne dates SVP."; break;
            case 2:
                msg = "La distance des date que vous avez choisi est superieur a 8 semaines! \n" +
                        "Veuillez vous choisir les dates tel que date end - date begin <= 8 semaines."; break;
            case 3:
                msg = "La distance des date que vous avez choisi est superieur a 12 mois! \n" +
                        "Veuillez vous choisir les dates tel que date end - date begin <= 12 mois"; break;
            case 4:
                msg = "La distance des date que vous avez choisi est superieur a 5 years! \n" +
                        "Veuillez vous choisir les dates tel que date end - date begin <= 5 years"; break;
            case 5:
                msg = "La date que vous avez choisi est une duree magnifique, il n'y a aucune operation."; break;
            case 6:
                msg = "Le fichier de rapport a ete cree sous resutls/Rapport_Indicator.pdf"; break;
        }
        JOptionPane.showMessageDialog(null, msg);
    }


    /**
     * Called by controllerIndicatorActivity when it reforms the data which comes from the Server.server.
     * This method just show the information in according to the time scale that user choose.
     * The field of info is: number operation done, average of duration per operation, piece consumption,
     *                       operation which use max days, and the vehicle, repairer of this operation.
     * @param data a linkedHashMap contains the operation info sorted by time scale
     * @param scaleChosen key word of time scale
     */
    public void showInfoAnalyse(Map<java.util.Date, java.util.List<IndicatorDTO>> data, String scaleChosen){
        //Translate English cmd in French
        if(scaleChosen.equals("week"))
            scaleChosen = "semaine";
        else if (scaleChosen.equals("month"))
            scaleChosen = "mois";
        else if (scaleChosen.equals("year"))
            scaleChosen = "an";

        rapportArea.append("Les données sont analysées par " + scaleChosen + " : \n");
        rapportArea.append("--------------------------------------------------------------\n");
        for(Map.Entry<java.util.Date, List<IndicatorDTO>> entry : data.entrySet()){
            rapportArea.append(entry.getKey().toString() + ": \n");
            int opEach = 0;
            double durationEach = 0;
            int pConsoEach = 0;
            long days = 0;
            long maxDay = 0;
            String maxBreak = "", maxNumMat = "", maxRepairer = "";
            if (entry.getValue() == null) {
                rapportArea.append("    Il n'y a aucune d'opération sont effectuée.\n");
                rapportArea.append("-----------------------------------------------------------------\n");
            } else {
                rapportArea.append("Les véhicules qui sont réparés dans cette échelle : " + "\n");
                for (IndicatorDTO ele : entry.getValue()) {
                    rapportArea.append(ele.getNumMat() + " | ");
                    opEach++;
                    pConsoEach += ele.getPieceConso();
                    java.sql.Date end = java.sql.Date.valueOf(ele.getDateE());
                    java.sql.Date begin = java.sql.Date.valueOf(ele.getDateB());
                    days = (end.getTime() - begin.getTime()) /(24*60*60*1000);

                    if(maxDay < days) {
                        maxDay = days;
                        maxBreak = ele.getBreaktype();
                        maxNumMat = ele.getNumMat();
                        maxRepairer = ele.getRepairer();
                    }
                }
                rapportArea.append("\n");
                durationEach = days/opEach;
                rapportArea.append("    Opérations effectuées: " + opEach + "\n");
                rapportArea.append("    Pièces consomées: " + pConsoEach + "\n");
                rapportArea.append("    Durée moyenne par l'opération: " + df.format(durationEach) + "\n");
                rapportArea.append("    Durée maximale pour une opération est " + maxDay + " jours. " + "\n");
                rapportArea.append("    Cette opération est éffectuée par " + maxRepairer + " avec le véhicule "
                        + maxNumMat + " en dommage " + maxBreak + "\n");
                rapportArea.append("-------------------------------------------------------------------\n");
            }
        }

    }

    /**
     * This method is called in controller when indicator controller finish the collecting the data.
     * And it show the total information which have been collected by controller at rapport panel.
     * @param numOp number of operation found
     * @param pConso number of piece consumption
     * @param sumDuring all the days which have been used for repairing
     */
    public void showTotalInfo(int numOp, int pConso, long sumDuring, long maxDay, String[] maxInfo){
        rapportArea.setText("\n");

        double averg = Double.valueOf(sumDuring) / numOp;
        //Show these data into table, and refresh the table.
        getLnbOp.setText(String.valueOf(numOp));
        getlConso.setText(String.valueOf(pConso));
        getlDuring.setText(String.valueOf(df.format(averg)));
        rapportArea.append(lnbOp.getText() + getLnbOp.getText() + "\n");
        rapportArea.append(lConso.getText() + getlConso.getText()+ "\n");
        rapportArea.append(lDuring.getText() + getlDuring.getText() + " jours. \n");
        rapportArea.append("Durée maximale pour une opération est " + maxDay + " jours. " + "\n");
        rapportArea.append("Cette opération est éffectuée par " + maxInfo[2] + " avec le véhicule "
                + maxInfo[1] + " en dommage " + maxInfo[0] + "\n");
        rapportArea.append("--------------------------------------------------------\n");
    }

    public void disposeView(){
        this.dispose();
    }

    public static void main(String[] args){
        IndicatorActivityView ia = new IndicatorActivityView();

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


}
