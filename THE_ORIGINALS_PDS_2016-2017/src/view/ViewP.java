package view;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ViewP extends JFrame {
	
	public static final int W=700;
	public static final int H=700;
	
	JLabel labRef ;
	JLabel labPanne ;
	JLabel labp1;
	JLabel labp2;
	JLabel labp3;
	JLabel labp4;
	JLabel labqte1;
	JLabel labqte2;
	JLabel labqte3; 
	JLabel labqte4;
	
	JButton btnOp;
	JButton btnListOp;
	JButton btnAjoutPiece;
	JButton btnAjoutOp;
	JButton btnInfoVehicle;
	JButton btnFin;
	JButton btnFinOp;

	JTextField texRef ;
	JTextField texPanne ;
	JTextField texp1 ;
	JTextField texp2 ;
	JTextField texp3 ;
	JTextField texp4 ;
	JTextField texqte1 ;
	JTextField texqte2 ;
	JTextField texqte3 ;
	JTextField texqte4 ;
	
	JFrame frame ;
	
	
	 public ViewP() {
		 
		    frame = new JFrame("Reparation-vehicle");
		    frame.setResizable(false);
            Font font = new Font("Arial",Font.BOLD,16);

		    
			frame.getContentPane().setLayout(null);
			frame.setSize(W, H);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			
			 labRef   =new JLabel("Vehicle") ;
			 labRef.setFont(font);
		     labPanne =new JLabel("Motif") ;
		     labPanne.setFont(font);
		     labp1    =new JLabel("Piece N°1");
			 labp2    =new JLabel("Piece N°2");
			 labp3    =new JLabel("Piece N°3");
			 labp4   =new JLabel("Piece N°4");
			 labqte1  =new JLabel("quantite");
			 labqte2  =new JLabel("quantite");
			 labqte3  =new JLabel("quantite");
			 labqte4  =new JLabel("quantite");
			 
		     btnOp         = new JButton("Prendre une Operation");
		     btnListOp     = new JButton("Liste d'operations");
			 btnAjoutOp    = new JButton("Ajouter une operation");
			 btnFin        = new JButton("FIN");
			 btnFinOp        = new JButton("Fin d'operation");

			 
			 texRef   = new JTextField();
			 texRef.setEditable(false);
			 texRef.setBackground(Color.lightGray);
			 
			 texPanne = new JTextField();
			 texPanne.setEditable(false);
			 texPanne.setBackground(Color.lightGray);
			 
		     texp1    = new JTextField();
		     texp1.setEditable(false);
		     texp1.setText("nul");
		     
			 texp2    = new JTextField() ;
			 texp2.setEditable(false);
			 texp2.setText("nul");
			 
		     texp3    = new JTextField() ;
		     texp3.setEditable(false);
		     texp3.setText("nul");
		     
		     texp4    = new JTextField() ;
		     texp4.setEditable(false);
		     texp4.setText("nul");
		     
			 texqte1  = new JTextField();
			 texqte1.setEditable(false);
			 texqte1.setText("nul");
			 
			 texqte2  = new JTextField();
			 texqte2.setEditable(false);
			 texqte2.setText("nul");
			 
			 
		     texqte3  = new JTextField();
		     texqte3.setEditable(false);
		     texqte3.setText("nul");
		     
		     texqte4  = new JTextField();
		     texqte4.setEditable(false);
		     texqte4.setText("nul");
		     
		     frame.getContentPane().add(labRef);
		     frame.getContentPane().add(labPanne);
		     frame.getContentPane().add(labp1);
		     frame.getContentPane().add(labp2);
		     frame.getContentPane().add(labp3);
		     frame.getContentPane().add(labqte1);
		     frame.getContentPane().add(labqte2);
		     frame.getContentPane().add(labqte3);
		     frame.getContentPane().add(labqte4);
		     frame.getContentPane().add(labp4);
		     
		     
		     frame.getContentPane().add(texRef);
		     frame.getContentPane().add(texPanne);
		     frame.getContentPane().add(texp1);
		     frame.getContentPane().add(texp2);
		     frame.getContentPane().add(texp3);
		     frame.getContentPane().add(texqte1);
		     frame.getContentPane().add(texqte2);
		     frame.getContentPane().add(texqte3);
		     frame.getContentPane().add(texqte4);
		     frame.getContentPane().add(texp4);
		     
		     
		     frame.getContentPane().add(btnOp);
		     frame.getContentPane().add(btnAjoutOp);
		     frame.getContentPane().add(btnListOp);
		     frame.getContentPane().add(btnFin);
		     //frame.getContentPane().add(btnFinOp);

		     
		     btnOp.setBounds(10,10,300,50);
		     btnAjoutOp.setBounds(10,600,200,50);
		     btnListOp.setBounds(380,10,300,50);
		     btnFin.setBounds(520,600,150,50);
		     btnFinOp.setBounds(280,600,150,50);

		     
		     
		     labRef.setBounds(10, 70, 60, 50);   ;
		     labPanne.setBounds(10, 130, 60, 50); ;
		     labp1.setBounds(10, 250, 60, 50);   
			 labp2.setBounds(10, 310, 60, 50);   ;
			 labp3.setBounds(10,370,60,50);    ;
			 labqte1.setBounds(330, 250, 60, 50); ; 
			 labqte2.setBounds(330,310,60,50);  
			 labqte3.setBounds(330,370,60,50);  
			 labqte4.setBounds(330,430,60,50);  
			 labp4.setBounds(10,430,60,50); 
			 
			 texRef.setBounds(70,70,240,50);
			 texPanne.setBounds(70,130,240,50);
			 texp1.setBounds(80, 260, 240, 30);
			 texp2.setBounds(80,320,240,30);
			 texp3.setBounds(80,380,240,30);
			 texqte1.setBounds(400, 260, 240, 30);
			 texqte2.setBounds(400,320,240,30);
			 texqte3.setBounds(400,380,240,30);
			 texqte4.setBounds(400,440,240,30);
			 texp4.setBounds(80,440,240,30);
			 
		     
		   }
	 public void showBtn(){
		 btnInfoVehicle= new JButton("Information du véhicule");
	     frame.getContentPane().add(btnInfoVehicle);
	     btnInfoVehicle.setBounds(380, 130,300,50);

		 }
	 
	 public void showbtnPiece(){
		 btnAjoutPiece = new JButton("Ajouter une piece");
	     frame.getContentPane().add(btnAjoutPiece);
	     btnAjoutPiece.setBounds(200, 190, 300, 50);



		 
	 }
	 
	
	 public JButton getbtnOp (){
		 return btnOp;
	 }
	 
	 public JButton getbtnListOp (){
		 return btnListOp;
	 }
	 
	 public JButton getbtnAjoutPiece (){
		 return btnAjoutPiece;
	 }
	 
	 public JButton getbtnAjoutOp (){
		 return btnAjoutOp;
	 }
	 
	 public JButton getbtnInfoVehicle (){
		 return btnInfoVehicle;
	 }
	 
	 public JButton getbtnFin (){
		 return btnFin;
	 }
	 
	 public JButton getbtnFinOp (){
		 return btnFinOp;
	 }

	 
   public JTextField gettexRef (){
	   return texRef ;
   }
   
   public JTextField gettexPanne (){
	   return texPanne ;
   }
   
   public JTextField gettexp1 (){
	   return texp1 ;
   }
   
   public JTextField gettexp2 (){
	   return texp2 ;
   }
   
   public JTextField gettexp3 (){
	   return texp3 ;
   }
   
   public JTextField gettexp4 (){
	   return texp4 ;
   }
   
   public JTextField gettexqte1 (){
	   return texqte1 ;
   }
   
   public JTextField gettexqte2 (){
	   return texqte2 ;
   }
   
   public JTextField gettexqte3 (){
	   return texqte3 ;
   }
   
   public JTextField gettexqte4 (){
	   return texqte4 ;
   }
   public void insertText(String name, String qte){
	   if(this.gettexp1().getText()!=null){
		   
		   this.gettexp1().setText(name);
		   this.gettexqte1().setText(qte);
		   }}
   
   public void insertText2(String name, String qte){
	   if(this.gettexp2().getText()!=null){
		   
		   this.gettexp2().setText(name);
		   this.gettexqte2().setText(qte);
		   }}
   
   public void insertText3(String name, String qte){
	   if(this.gettexp3().getText()!=null){
		   
		   this.gettexp3().setText(name);
		   this.gettexqte3().setText(qte);
		   }}
   
   public void insertText4(String name, String qte){
	   if(this.gettexp4().getText()!=null){
		   
		   this.gettexp4().setText(name);
		   this.gettexqte4().setText(qte);
		   }}
   

   
   
   
   
   
   
   
   
   
   
   
}
