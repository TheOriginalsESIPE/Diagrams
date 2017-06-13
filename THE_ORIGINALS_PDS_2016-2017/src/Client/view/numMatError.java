package Client.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class numMatError extends JFrame implements ActionListener {

	JButton ButtonOk = new JButton ("OK");
	JLabel message = new JLabel("Erreur, Verifié votre numéro matricule !!  appuyez sur OK pour quitter");
	JFrame fen = new JFrame ("Erreur Create");

	public numMatError()  {

		fen.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		fen.setVisible(true);
		fen.setSize(500,100);
		fen.setLocationRelativeTo(null);
		fen.setBackground(Color.BLUE);
		ButtonOk.addActionListener(this);
		fen.setLayout(new FlowLayout(FlowLayout.LEFT));


		fen.add(message);
		fen.add(ButtonOk);

	}

	public void actionPerformed(ActionEvent evt) {
		fen.dispose();
	}


}
