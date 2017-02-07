import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.google.gson.JsonElement;

public class Authentification extends JFrame {
	private static final long serialVersionUID = 1L;
	JButton loginButton;
	JButton recupereButton;
	JLabel userLabel;
	JTextField userText;
	 JLabel passwordLabel;
	 JTextField passwordText;
	 JLabel agelabel;
	 JTextField agetext;
	 String user;
	 String password;
	 String Age;
    public Authentification() {
        super();
        this.setSize(800, 400);
        this.setTitle("Enregistrer/recupérer d'un fichier Json");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(null);
        loginButton = new JButton("Enregistrer dans un fichier Json");
        recupereButton = new JButton("recuperer d'un fichier Json ");
        loginButton.setBounds(10, 200, 200, 25);
        panel.add(loginButton);
        recupereButton.setBounds(300,200,200,25);
        panel.add(recupereButton);
        Ecouteur2 ec = new  Ecouteur2(this);
        this.loginButton.addActionListener(ec);
        this.recupereButton.addActionListener(ec);
        agelabel = new JLabel("age");
        agelabel.setBounds(10, 80, 80, 25);
        panel.add(agelabel);
        agetext = new JTextField(20);
        agetext.setBounds(100, 80, 160, 25);
        panel.add(agetext);
        userLabel = new JLabel("Prenom");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);
        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);
        passwordLabel = new JLabel("Nom");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);
        passwordText = new JTextField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);
        this.getContentPane().add(panel, BorderLayout.CENTER);
    
    }


        


}