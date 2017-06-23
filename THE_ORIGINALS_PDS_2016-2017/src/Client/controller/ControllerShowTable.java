package Client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JTable;

import Client.dto.Vehicle_warehouseDTO;
import Client.serialization.Deserialization;
import Client.serialization.DeserializationGson;
import Client.serialization.Serialization;
import Client.view.Table;
import Client.view.ViewShowTable;
import Client.view.ViewVehicleRepaired;
import Server.dto.Vehicle_warehouseDTOL;
import Client.enumeration.EnumService;

public class ControllerShowTable {
	
	private ViewShowTable vst;
	private ActionListener ac;
	private Socket socket;
	private BufferedReader in;
    private PrintStream out;
    
   
    
    public ControllerShowTable(Socket socket, ViewShowTable vst){
    	this.socket=socket;
    	this.vst=vst;
    }
    
    public void control(){
    	
    	ac=new ActionListener(){
    		
    		public void actionPerformed(ActionEvent e){
    			try{
    				in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        			out=new PrintStream(socket.getOutputStream());
        			
        			if((JButton)e.getSource()==vst.getOk()){
        				
        				
        				out.println(EnumService.TABLE.name());
        				out.flush();
        				
        				
        				String reponse=in.readLine();
        				
        				DeserializationGson d = new DeserializationGson();
        				List<Client.dto.Vehicle_warehouseDTOL> list1 = d.desVehicle_warehouseDTOL(reponse);
        				System.out.println(list1+"bleu");
        			
        				
        				int row=vst.getT().getRowCount();
        				System.out.println(row+1);
        				String[][] donnees=new String[list1.size()][7];
        				for(int i=0; i<list1.size(); i++){
        					Client.dto.Vehicle_warehouseDTOL vh=list1.get(i);
        					donnees[i][0]=vh.getNumMat();
        					donnees[i][1]=vh.getNumPlace();
        					donnees[i][2]=vh.getDate_entrance();
        					donnees[i][3]=vh.getDate_wayout();
        					donnees[i][4]=vh.getDateBeginOperation();
        					donnees[i][5]=vh.getDateEndOperation();
        					donnees[i][6]=vh.getStatus();
        				}
        				vst.getT().setDonnees(donnees);
        				vst.getT().fireTableDataChanged();
        				vst.getTable().repaint();
        				vst.getTable().updateUI();
        				
        			}
    			}catch(IOException e1){}
    		
    		}
    	};vst.getOk().addActionListener(ac);
    }

}
