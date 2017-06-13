package Client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.SwingUtilities;

import enumeration.EnumService;
import view.ViewInfoVehicle;
import view.ViewListOp;
import view.ViewP;


public class ControllerInfoVehicle {
	ViewInfoVehicle vinfo ;
	ViewP bigView ;
	Socket socket ;
	BufferedReader in;
	PrintStream out;
	
	public ControllerInfoVehicle(ViewInfoVehicle vinfo , ViewP bigView, Socket socket){
		
		this.vinfo=vinfo;
		this.bigView=bigView;
		this.socket=socket ;
	}
	
	public void control(){
		vinfo.getbtnFerm().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==vinfo.getbtnFerm()){
					SwingUtilities.invokeLater(new Runnable() {
			            public void run() {
	                        vinfo.getframe().setVisible(false);
	                        vinfo.getframe().dispose();
			            }
					

				});
					
						
				}
				
			}
		});
		
		
		
		
		try{
			
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintStream(socket.getOutputStream());
		out.println(EnumService.SHOW_INFO.name());
		out.flush();
		String duree =in.readLine();
		System.out.println(duree);
		vinfo.gettexdure().setText(duree);
		
		String model=in.readLine();
		vinfo.gettexmodel().setText(model);
		
		String mark=in.readLine();
		vinfo.gettexmark().setText(mark);
		
		String type=in.readLine();
		vinfo.gettextype().setText(type);
		
		String numplace =in.readLine();
		vinfo.gettexnump().setText(numplace);
		
		String date =in.readLine();
		vinfo.gettexdate().setText(date);
		
		
		
		}catch(IOException ex){
			
		ex.printStackTrace();
		}

	}

}
