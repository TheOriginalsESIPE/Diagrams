package Client.controller;

import Client.serialization.DeserializationGson;
import Client.serialization.Serialization;
import Client.dto.IndicatorDTO;
import Client.enumeration.EnumService;
import Client.view.IndicatorActivityView;
import Client.view.IndicatorRapportPDF;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.util.*;


/**
 * Created by tearsyu on 26/04/17.
 */
public class ControllerIndicatorActivity {
    private IndicatorActivityView indicatorView;
    private ActionListener alForIndicatorView;
    private Socket socket;
    private BufferedReader in;
    private PrintStream out;
    private Map<java.util.Date, List<IndicatorDTO>> mapdata;
    private Map<String, String> mapIndict;
    public ControllerIndicatorActivity(IndicatorActivityView indicatorView, Socket client){
        this.indicatorView = indicatorView;
        this.socket = client;
        control();
        indicatorView.getbApply().addActionListener(alForIndicatorView);
        indicatorView.getbOk().addActionListener(alForIndicatorView);
        indicatorView.getbExit().addActionListener(alForIndicatorView);
    }

    /**
     * This method control() make reactions with the 3 buttons in Client.view.
     * Confirme -> transfer the request map to the Server.server, then receive the data from the Server.server, analysing
     * these data, sending the data reformed and calling the method in IndicatorActivityView to show the info.
     *
     * Quitter-> exit the app.
     *
     * Exporter en pdf -> exporter ces info as pdf.
     */
    public void control(){
        alForIndicatorView = e->{
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintStream(socket.getOutputStream());

            if(e.getSource() == indicatorView.getbApply()){
                //I use a type of Map to store the data and send it to Server.server.
                //Like json{"from":"2017-02-09",
                //          "to":"2017-03-09",
                //          "vtype":"car",
                //          "btype":"crevaison",
                //          "statu": "waiting"
                //          };

                Map indicatorRequest = new HashMap<String,String>(5);
                ButtonModel bm = indicatorView.timeScale.getSelection();
               
                String vehicleType = "";
                if(indicatorView.from.getDate() != null && indicatorView.to.getDate() != null){
                    //two dates are different! Pay attention to cast them.
                    java.util.Date dateUtil = indicatorView.from.getDate();
                    java.sql.Date sqlDateFrom = new java.sql.Date(dateUtil.getTime());
                    dateUtil = indicatorView.to.getDate();
                    java.sql.Date sqlDateTo = new java.sql.Date(dateUtil.getTime());

                    //get time scale and check the time chosen is logic
                    if(sqlDateFrom.after(sqlDateTo)){
                        indicatorView.msgDialog(1);
                        return;
                    } else if (!isGoodDate(bm.getActionCommand(), sqlDateFrom, sqlDateTo)){
                        return;
                    }

                    indicatorRequest.put("from", sqlDateFrom.toString());
                    indicatorRequest.put("to", sqlDateTo.toString());
                }
                if (indicatorView.bike.isSelected() && !indicatorView.car.isSelected())
                    vehicleType = indicatorView.car.getActionCommand();
                else if(indicatorView.car.isSelected() && !indicatorView.bike.isSelected())
                    vehicleType = indicatorView.car.getActionCommand();
                else
                    vehicleType = "all";
                indicatorRequest.put("vtype", vehicleType);
                indicatorRequest.put("statu", indicatorView.statu.getSelectedItem().toString());
                indicatorRequest.put("btype", indicatorView.typeBreakdown.getSelectedItem().toString());
                this.mapIndict = indicatorRequest;
                //serialize the map to json
                Serialization s = new Serialization();
                String mapJson = s.serialToStr(s.serialMap(indicatorRequest));
                System.out.println("to json: " + mapJson);

                //send json info of map
                out.println(EnumService.INDICATOR.name());
                out.flush();
                out.println(mapJson);
                out.flush();


                String resultOprt = in.readLine();
                //If there is no operation ... between the date chosen
                if(resultOprt.equals("None")){
                    indicatorView.msgDialog(5);
                     return;
                }
                //get response from Server.server(arraylist)
                DeserializationGson dg = new DeserializationGson();
                List<IndicatorDTO> arrIndicator = dg.deIndicatorDTO(resultOprt);
                int pieceConso = 0;
                long sumDuring = 0;
                long maxDay = 0;
                String maxBreak = "", maxNumMat = "", maxRepairer = "";
                for(IndicatorDTO ele : arrIndicator) {
                    System.out.println(ele.toString());
                    pieceConso += ele.getPieceConso();
                    java.util.Date beginEach = Date.valueOf(ele.getDateB());
                    java.util.Date endEach = Date.valueOf(ele.getDateE());
                    long days = (endEach.getTime() - beginEach.getTime())/(24*60*60*1000);
                    sumDuring += days;
                    if(maxDay < days) {
                        maxDay = days;
                        maxBreak = ele.getBreaktype();
                        maxNumMat = ele.getNumMat();
                        maxRepairer = ele.getRepairer();
                    }
                }
                String[] maxInfo = new String[3];
                maxInfo[0] = maxBreak;
                maxInfo[1] = maxNumMat;
                maxInfo[2] = maxRepairer;
                sumDuring = sumDuring > 0 ? sumDuring : (-1 * sumDuring);
                indicatorView.showTotalInfo(arrIndicator.size(), pieceConso, sumDuring, maxDay, maxInfo);

                int row = indicatorView.itable.getRowCount();
                System.out.println(row);
                String[][] data = new String[arrIndicator.size()][5];
                for(int i = 0; i < arrIndicator.size(); ++i){
                    IndicatorDTO ele = arrIndicator.get(i);
                    data[i][0] = ele.getvType();
                    data[i][1] = ele.getNumMat();
                    data[i][2] = ele.getBreaktype();
                    data[i][3] = ele.getRepairer();
                    data[i][4] = String.valueOf(ele.getPieceConso());
                }

                indicatorView.itable.data = data;
                indicatorView.itable.fireTableDataChanged();
                indicatorView.getTable().repaint();
                indicatorView.getTable().updateUI();

                //show data analysed here
                Map<java.util.Date, List<IndicatorDTO>> dataRe = analyseData(bm.getActionCommand(), arrIndicator);
                indicatorView.showInfoAnalyse(dataRe, bm.getActionCommand());
                this.mapdata = dataRe;
                //enable to click export as pdf
                indicatorView.getbOk().setEnabled(true);
            }

                //Export as PDF
                if(e.getSource() == indicatorView.getbOk()){
                    System.out.println("Create pdf");
                    ButtonModel bm = indicatorView.timeScale.getSelection();
                    IndicatorRapportPDF exportPDF = new IndicatorRapportPDF("results/Rapport_Indicator", bm.getActionCommand());
                    exportPDF.createPDF(mapdata, mapIndict);
                    indicatorView.msgDialog(6);
                }

                if(e.getSource() == indicatorView.getbExit()){
                    System.out.println("[DEBUG] go out");
                    socket.close();
                    indicatorView.disposeView();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        };
    }


    /**
     * This method is called to analyse the date. It show the info of operation in according to "timeScale" chosen.
     * Each operation has a start date and an end date, here I take the start date as the date which need be
     * analysed. The data analysed will be put in a LinkedHashMap<Date, List<IndicatorDTO>>
     *     For "week" : number maximum to analyse is 8
     *     For "month" : number maximum to analyse is 12
     *     For "year" : number maximum to analyse is 5
     * @param timeScale
     * @param arrIndicator
     */
    public Map<java.util.Date, List<IndicatorDTO>> analyseData(String timeScale, List<IndicatorDTO> arrIndicator){
       // System.out.println("Start to analyse the data in " + timeScale);
        Map<java.util.Date, List<IndicatorDTO>> data = new LinkedHashMap<>();
        Calendar calendar = Calendar.getInstance();
        java.util.Date from = indicatorView.from.getDate();
        java.util.Date to = indicatorView.to.getDate();


        if(timeScale.equals("week")){
            System.out.println("week analyse");
            java.util.Date dateTmp = from;
            java.util.Date[] weeks = new Date[8];
            int i = 0;
            while(!dateTmp.after(to)){
                //Manipulate weeks
                calendar.setTime(dateTmp);
                calendar.add(Calendar.WEEK_OF_MONTH, 1);
                dateTmp = calendar.getTime();
                //create date key
                weeks[i] = dateTmp;
                i++;
                data.put(dateTmp, null);
            }

            for(IndicatorDTO ele: arrIndicator) {
                Date fromEach = Date.valueOf(ele.getDateB());
                if(data.get(getKeyDate(weeks, fromEach)) != null) {
                    List<IndicatorDTO> arrIndiEach = new ArrayList<>();
                    arrIndiEach.add(ele);
                    data.put(getKeyDate(weeks, fromEach), arrIndiEach);
                } else {
                    data.get(getKeyDate(weeks, fromEach)).add(ele);
                }
            }

        } else if (timeScale.equals("month")){
            System.out.println("month analyse");
            java.util.Date monthTmp = from;
            java.util.Date[] months = new java.util.Date[12];
            int i = 0;
            while(!monthTmp.after(to)) {
                calendar.setTime(monthTmp);
                calendar.add(Calendar.MONTH, 1);
                monthTmp = calendar.getTime();
                months[i] = monthTmp;
                i++;
                data.put(monthTmp, null);
            }

            for(IndicatorDTO ele : arrIndicator){
                Date fromEach = Date.valueOf(ele.getDateB());
                java.util.Date key = getKeyDate(months, fromEach);
                System.out.print("[DEBUG]" + ele.getDateB()+ " : key is :" +key);
                if(data.get(key) == null){
                    List<IndicatorDTO> listIndict = new ArrayList<>();
                    listIndict.add(ele);
                    data.put(key, listIndict);
                    System.out.println("-> create list");
                } else {
                    data.get(key).add(ele);
                    System.out.println("-> update key");
                }
            }
        } else if (timeScale.equals("year")){
            System.out.println("year analyse.");
            calendar.setTime(from);
            int yearB = calendar.get(Calendar.YEAR);
            calendar.setTime(to);
            int yearE = calendar.get(Calendar.YEAR);
            System.out.println(yearB + " " + yearE);
            int i = 0;
            Date[] years = new Date[5];
            while(yearE - yearB >= 0){
                data.put(Date.valueOf(yearB+"-01-01"), null);
                years[i] = Date.valueOf(yearB+"-01-01");
                yearB++;
                System.out.println("[DEBUG] years:" + years[i]);
            }

            for(IndicatorDTO ele : arrIndicator){
                Date fromEach = Date.valueOf(ele.getDateB());
                calendar.setTime(fromEach);
                fromEach = Date.valueOf(calendar.get(Calendar.YEAR) + "-01-01");
                java.util.Date key = getKeyDate(years,fromEach);
                if(data.get(key) == null){
                    List<IndicatorDTO> listIndict = new ArrayList<>();
                    listIndict.add(ele);
                    data.put(key, listIndict);
                } else {
                    data.get(key).add(ele);
                }
            }
        }
        return data;
    }

    public java.util.Date getKeyDate(java.util.Date[] dates, Date from) {
        boolean b = true;
        int i = 0;
        java.util.Date key = null;
        while(b && i < dates.length){
            key = dates[i];
            if(from.before(key) || from.compareTo(key) == 0){
                b = false;
            } else
                i++;
        }
        return key;
    }

    public boolean isGoodDate(String timeScale, Date begin, Date end){
        boolean b = true;
        long day = (end.getTime() - begin.getTime())/(24*60*60*1000);
        System.out.println("Day scale:" + day);
        if (timeScale.equals("week")){
            if (day > 56) {
                indicatorView.msgDialog(2);
                b = false;
            }
        }  else if (timeScale.equals("month")) {
            if (day > 365){
                b = false;
                indicatorView.msgDialog(3);
            }
        } else if (timeScale.equals("year")){
            if (end.getYear() - end.getYear() > 5){
                b = false;
                indicatorView.msgDialog(4);
            }
        }
        return b;
    }

    public String showMap(Map<java.util.Date, List<IndicatorDTO>> map){
        String re = "";
        for(Map.Entry<java.util.Date, List<IndicatorDTO>> entry : map.entrySet()){
            re += entry.getKey().toString() + ": \n";
            if(entry.getValue() != null) {
                System.out.println("[DEBUG] size" + entry.getValue().size());
                for (IndicatorDTO idc : entry.getValue()) {
                    System.out.println("idc " + idc.getNumMat());
                    re += idc.getDateB() + idc.getBreaktype() + "\r";
                }
            }
        }
        return re;
    }

}
