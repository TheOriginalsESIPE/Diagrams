package Client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ZDialogVehicleInfo extends JFrame {

	private Socket socket = null;
	
	public static JLabel imageLabel, registerLabel, vehicleTypeLabel, brandLabel, modelLabel, visitingMotifLabel, breakdownLabel;
	public static JButton buttonFindVehicle, mainButton1, mainButton2, buttonAddBreakdown, buttonVisitingMotif, buttonAddVisitingMotif, buttonDownloadBreakdown;
	public static JTextArea brandText, modelText,  vehicleTypeText;
	public static JComboBox<String> breakdownText, visitingMotifText;
	public static TextArea listBreakdown, listVisitingMotif;
	public static JTextField registerText;
	
	public ZDialogVehicleInfo(){
		this.socket = socket;
		this.setTitle("Information du voiture");
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.initComponeInfo();
		this.setDefaultLookAndFeelDecorated(false);    
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.pack();
		this.setVisible(true);
	}

	public void initComponeInfo(){

		//Information for vehicle image
		imageLabel = new JLabel(new ImageIcon("C:/Users/LM/Desktop/Image/voitureIcon.png"));
		JPanel panVoitureIcon = new JPanel();
		panVoitureIcon.setBackground(Color.white);
		panVoitureIcon.setLayout(new BorderLayout());
		panVoitureIcon.add(imageLabel);

		//Information for vehicle characteristic
		JPanel panIV = new JPanel();
		panIV.setBackground(Color.white);
		panIV.setBorder(BorderFactory.createTitledBorder("Information du vehicule"));
		panIV.setPreferredSize(new Dimension(240, 100));
		panIV.setLayout(null);

		registerLabel = new JLabel("Matricule");
		registerLabel.setBounds(50, 50, 200, 20);

		registerText = new JTextField();
		registerText.setBounds(250, 50, 200, 20);

		buttonFindVehicle = new JButton("Cherher");
		buttonFindVehicle.setBounds(500, 50, 100, 20);

		modelLabel = new JLabel("Modele");
		modelLabel.setBounds(50, 150, 200, 20);

		modelText = new JTextArea("model");
		modelText.setBorder(BorderFactory.createLineBorder(Color.black));
		modelText.setBounds(250, 150, 200, 20);
		

		brandLabel = new JLabel("Marque");
		brandLabel.setBounds(50, 250, 200, 20);
		
		brandText = new JTextArea("marque");
		brandText.setBorder(BorderFactory.createLineBorder(Color.black));
		brandText.setBounds(250, 250, 200, 20);
	
		vehicleTypeLabel = new JLabel("Type vehicule");
		vehicleTypeLabel.setBounds(50, 350, 200, 20);

		vehicleTypeText = new JTextArea("type vehicule");
		vehicleTypeText.setBorder(BorderFactory.createLineBorder(Color.black));
		vehicleTypeText.setBounds(250, 350, 200, 20);


		panIV.add(registerLabel);			panIV.add(registerText);			panIV.add(buttonFindVehicle);		
		panIV.add(brandLabel);				panIV.add(brandText);				panIV.add(modelLabel);				
		panIV.add(modelText);				panIV.add(vehicleTypeLabel);		panIV.add(vehicleTypeText);
	
		//Information for visiting motif
		JPanel panVM = new JPanel();
		panVM.setBackground(Color.white);
		panVM.setBorder(BorderFactory.createTitledBorder("Motif visite"));
		panVM.setPreferredSize(new Dimension(450, 400));
		panVM.setLayout(null);
		visitingMotifLabel = new JLabel("Motif");
		visitingMotifLabel.setPreferredSize(new Dimension(200, 70));
		visitingMotifLabel.setBounds(30, 30, 100, 20);
		visitingMotifText = new JComboBox();
		visitingMotifText.setBounds(100, 30, 120, 20);
		visitingMotifText.setPreferredSize(new Dimension(220, 25));
		buttonVisitingMotif = new JButton("charger");
		buttonVisitingMotif.setBounds(270, 30, 120, 20);
		buttonAddVisitingMotif = new JButton("Ajouter");
		buttonAddVisitingMotif.setBounds(450, 30, 120, 20);
		listVisitingMotif = new TextArea("LISTE DE MOTIFS: \n");
		listVisitingMotif.setBounds(30, 70, 660, 260);
		panVM.add(visitingMotifLabel);		panVM.add(visitingMotifText);		
		panVM.add(buttonVisitingMotif);		panVM.add(listVisitingMotif);		panVM.add(buttonAddVisitingMotif);
		
		

		//Information for breakdown
		JPanel panB = new JPanel();
		panB.setBackground(Color.white);
		panB.setBorder(BorderFactory.createTitledBorder("Pannes"));
		panB.setPreferredSize(new Dimension(450, 400));
		panB.setLayout(null);
		breakdownLabel = new JLabel("Panne");
		breakdownLabel.setBounds(30, 30, 100, 20);
		breakdownText = new JComboBox();
		breakdownText.setBounds(100, 30, 120, 20);
		buttonDownloadBreakdown = new JButton("charger");
		buttonDownloadBreakdown.setBounds(270, 30, 120, 20);
		buttonAddBreakdown = new JButton("Ajouter");
		buttonAddBreakdown.setBounds(450, 30, 120, 20);
		listBreakdown = new TextArea("LISTE DE PANNES: \n");
		listBreakdown.setBounds(30, 70, 660, 260);
		//((JComponent) listBreakdown).setBorder(BorderFactory.createLineBorder(Color.black));
		panB.add(breakdownLabel);		panB.add(breakdownText);		
		panB.add(buttonAddBreakdown);	panB.add(buttonDownloadBreakdown);		panB.add(listBreakdown);		

		//A main button 
		JPanel panMB = new JPanel();
		panMB.setBackground(Color.white);
		panMB.setPreferredSize(new Dimension(900, 400));
		panMB.setLayout(null);
		mainButton1 = new JButton("Enregistrer");
		mainButton1.setBounds(700, 99, 150, 20);
		mainButton2 = new JButton("Annuler");
		mainButton2.setBounds(900, 99, 150, 20);
		panMB.add(mainButton1);		panMB.add(mainButton2);

		JPanel jpanel = new JPanel();
		jpanel.setBackground(Color.white);
		jpanel.setBorder(BorderFactory.createTitledBorder("Motif de visite et panne constate"));
		jpanel.setLayout(new GridLayout(2, 1));
		jpanel.add(panVM);			jpanel.add(panB);

		JPanel pan1 = new JPanel();
		pan1.setPreferredSize(new Dimension(150, 100));
		pan1.setLayout(new GridLayout(1, 1));
		pan1.add(panVoitureIcon);

		JPanel pan2 = new JPanel();
		pan2.setPreferredSize(new Dimension(450, 400));
		pan2.setLayout(new GridLayout(1, 2));
		pan2.add(panIV);
		pan2.add(jpanel);

		JPanel pan3 = new JPanel();
		pan3.setPreferredSize(new Dimension(200, 300));
		pan3.setLayout(new BorderLayout());
		pan3.add(panMB);

		JPanel panMain = new JPanel();
		panMain.setLayout(new BorderLayout());
		panMain.setPreferredSize(new Dimension(800, 800));
		panMain.setBackground(Color.white);
		//panMain.add(pan1, BorderLayout.WEST);
		panMain.add(pan2, BorderLayout.CENTER);
		panMain.add(pan3, BorderLayout.SOUTH);

		this.getContentPane().add(panMain);
	}

	
	/**
	 * @return the buttonFindVehicle
	 */
	public static JButton getButtonFindVehicle() {
		return buttonFindVehicle;
	}

	/**
	 * @param buttonFindVehicle the buttonFindVehicle to set
	 */
	public static void setButtonFindVehicle(JButton buttonFindVehicle) {
		ZDialogVehicleInfo.buttonFindVehicle = buttonFindVehicle;
	}

	/**
	 * @return the mainButton1
	 */
	public static JButton getMainButton1() {
		return mainButton1;
	}

	/**
	 * @param mainButton1 the mainButton1 to set
	 */
	public static void setMainButton1(JButton mainButton1) {
		ZDialogVehicleInfo.mainButton1 = mainButton1;
	}

	/**
	 * @return the mainButton2
	 */
	public static JButton getMainButton2() {
		return mainButton2;
	}

	/**
	 * @param mainButton2 the mainButton2 to set
	 */
	public static void setMainButton2(JButton mainButton2) {
		ZDialogVehicleInfo.mainButton2 = mainButton2;
	}

	/**
	 * @return the buttonAddBreakdown
	 */
	public static JButton getButtonAddBreakdown() {
		return buttonAddBreakdown;
	}

	/**
	 * @param buttonAddBreakdown the buttonAddBreakdown to set
	 */
	public static void setButtonAddBreakdown(JButton buttonAddBreakdown) {
		ZDialogVehicleInfo.buttonAddBreakdown = buttonAddBreakdown;
	}

	/**
	 * @return the buttonVisitingMotif
	 */
	public static JButton getButtonVisitingMotif() {
		return buttonVisitingMotif;
	}

	/**
	 * @param buttonVisitingMotif the buttonVisitingMotif to set
	 */
	public static void setButtonVisitingMotif(JButton buttonVisitingMotif) {
		ZDialogVehicleInfo.buttonVisitingMotif = buttonVisitingMotif;
	}

	/**
	 * @return the buttonAddVisitingMotif
	 */
	public static JButton getButtonAddVisitingMotif() {
		return buttonAddVisitingMotif;
	}

	/**
	 * @param buttonAddVisitingMotif the buttonAddVisitingMotif to set
	 */
	public static void setButtonAddVisitingMotif(JButton buttonAddVisitingMotif) {
		ZDialogVehicleInfo.buttonAddVisitingMotif = buttonAddVisitingMotif;
	}

	/**
	 * @return the buttonDownloadBreakdown
	 */
	public static JButton getButtonDownloadBreakdown() {
		return buttonDownloadBreakdown;
	}

	/**
	 * @param buttonDownloadBreakdown the buttonDownloadBreakdown to set
	 */
	public static void setButtonDownloadBreakdown(JButton buttonDownloadBreakdown) {
		ZDialogVehicleInfo.buttonDownloadBreakdown = buttonDownloadBreakdown;
	}

	/**
	 * @return the brandText
	 */
	public static JTextArea getBrandText() {
		return brandText;
	}

	/**
	 * @param brandText the brandText to set
	 */
	public static void setBrandText(JTextArea brandText) {
		ZDialogVehicleInfo.brandText = brandText;
	}

	/**
	 * @return the modelText
	 */
	public static JTextArea getModelText() {
		return modelText;
	}

	/**
	 * @param modelText the modelText to set
	 */
	public static void setModelText(JTextArea modelText) {
		ZDialogVehicleInfo.modelText = modelText;
	}

	/**
	 * @return the vehicleTypeText
	 */
	public static JTextArea getVehicleTypeText() {
		return vehicleTypeText;
	}

	/**
	 * @param vehicleTypeText the vehicleTypeText to set
	 */
	public static void setVehicleTypeText(JTextArea vehicleTypeText) {
		ZDialogVehicleInfo.vehicleTypeText = vehicleTypeText;
	}

	/**
	 * @return the breakdownText
	 */
	public static JComboBox<String> getBreakdownText() {
		return breakdownText;
	}

	/**
	 * @param breakdownText the breakdownText to set
	 */
	public static void setBreakdownText(JComboBox<String> breakdownText) {
		ZDialogVehicleInfo.breakdownText = breakdownText;
	}

	/**
	 * @return the visitingMotifText
	 */
	public static JComboBox<String> getVisitingMotifText() {
		return visitingMotifText;
	}

	/**
	 * @param visitingMotifText the visitingMotifText to set
	 */
	public static void setVisitingMotifText(JComboBox<String> visitingMotifText) {
		ZDialogVehicleInfo.visitingMotifText = visitingMotifText;
	}

	/**
	 * @return the listBreakdown
	 */
	public static TextArea getListBreakdown() {
		return listBreakdown;
	}

	/**
	 * @param listBreakdown the listBreakdown to set
	 */
	public static void setListBreakdown(TextArea listBreakdown) {
		ZDialogVehicleInfo.listBreakdown = listBreakdown;
	}

	/**
	 * @return the listVisitingMotif
	 */
	public static TextArea getListVisitingMotif() {
		return listVisitingMotif;
	}

	/**
	 * @param listVisitingMotif the listVisitingMotif to set
	 */
	public static void setListVisitingMotif(TextArea listVisitingMotif) {
		ZDialogVehicleInfo.listVisitingMotif = listVisitingMotif;
	}

	/**
	 * @return the registerText
	 */
	public static JTextField getRegisterText() {
		return registerText;
	}

	/**
	 * @param registerText the registerText to set
	 */
	public static void setRegisterText(JTextField registerText) {
		ZDialogVehicleInfo.registerText = registerText;
	}

	public static void main(String[] args){
		
		new ZDialogVehicleInfo();
	}
}