package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Authentification extends JFrame implements ActionListener{
	
	//JFrame frame = new JFrame("Authentification");
	//JButton bouton = new JButton("Bouton");
	
	public Authentification() {
		super();
		this.setSize(800, 400);
		this.setTitle("Authentification");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		placeComponents(panel);
		this.getContentPane().add(panel, BorderLayout.CENTER);
		//this.add(bouton, BorderLayout.NORTH);*/
	
	}

		private static void placeComponents(JPanel panel) {

			panel.setLayout(null);

			JLabel userLabel = new JLabel("User");
			userLabel.setBounds(10, 10, 80, 25);
			panel.add(userLabel);

			JTextField userText = new JTextField(20);
			userText.setBounds(100, 10, 160, 25);
			panel.add(userText);

			JLabel passwordLabel = new JLabel("Password");
			passwordLabel.setBounds(10, 40, 80, 25);
			panel.add(passwordLabel);

			JPasswordField passwordText = new JPasswordField(20);
			passwordText.setBounds(100, 40, 160, 25);
			panel.add(passwordText);

			JButton loginButton = new JButton("Login");
			loginButton.setBounds(10, 80, 80, 25);
			panel.add(loginButton);
			
			JButton registerButton = new JButton("register");
			registerButton.setBounds(180, 80, 80, 25);
			panel.add(registerButton);
		}

		
		public void actionPerformed(ActionEvent e) {
			
		}

}

