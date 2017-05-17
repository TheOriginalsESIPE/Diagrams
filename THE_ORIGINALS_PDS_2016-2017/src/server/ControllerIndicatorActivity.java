package server;

import view.IndicatorActivityView;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by tearsyu on 26/04/17.
 */
public class ControllerIndicatorActivity {
    IndicatorActivityView indicatorView;
    ActionListener alForIndicatorView;
    Socket socket;
    BufferedReader in;
    PrintStream out;
    public ControllerIndicatorActivity(IndicatorActivityView indicatorView, Socket client){
        this.indicatorView = indicatorView;
        this.socket = client;
        control();
        indicatorView.getbApply().addActionListener(alForIndicatorView);
        indicatorView.getbOk().addActionListener(alForIndicatorView);
    }

    public void control(){
        alForIndicatorView = e->{
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintStream(socket.getOutputStream());

            if(e.getSource() == indicatorView.getbApply()){
                //I use a type of Map to store the data and send it to server.
                //Like json{"from":"2017-02-09",
                //          "to":"2017-03-09",
                //          "vtype":"car",
                //          "btype":"crevaison",
                //          "status": "waiting"
                //          };

                Map indicatorRequest = new HashMap<>(5);
                String timeScale = "";
                String vehicleType = "";
                if(indicatorView.from.getDate() != null && indicatorView.to.getDate() != null){
                    java.util.Date dateUtil = indicatorView.from.getDate();
                    java.sql.Date sqlDateFrom = new java.sql.Date(dateUtil.getTime());
                    dateUtil = indicatorView.to.getDate();
                    java.sql.Date sqlDateTo = new java.sql.Date(dateUtil.getTime());

                    timeScale = "from " + sqlDateFrom.toString() + " to " + sqlDateTo + " " ;

                    indicatorRequest.put("from", sqlDateFrom);
                    indicatorRequest.put("to", sqlDateTo);
                }
                if (indicatorView.bike.isSelected() && !indicatorView.car.isSelected())
                    vehicleType = indicatorView.car.getActionCommand();
                else if(indicatorView.car.isSelected() && !indicatorView.bike.isSelected())
                    vehicleType = indicatorView.car.getActionCommand();
                else
                    vehicleType = "2";
                indicatorRequest.put("vtype", vehicleType);
                indicatorRequest.put("status", indicatorView.status.getSelectedItem().toString());
                indicatorRequest.put("btype", indicatorView.typeBreakdown.getSelectedItem().toString());
                System.out.println(timeScale + " | " + vehicleType + " " + indicatorView.status.getSelectedItem().toString());

            }

                //Export as PDF
                if(e.getSource() == indicatorView.getbOk()){
                    indicatorView.disposeView();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        };
    }


}
