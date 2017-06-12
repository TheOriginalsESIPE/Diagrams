package view;

import enumeration.EnumUserToken;
import server.ControllerIndicatorActivity;
import server.ControllerPieceDetached;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

/**
 * Created by tearsyu on 11/06/17.
 * This class create the window raised after login. It proposes the service
 * according to user's login.
 * Constraint:
 * 1. Repairer and manutentionaire have no access to Operation synthese
 * 2. etc.. (A vous completer!)
 */

/**
 * Attention!!!!  Vous devez ajouter votre partie de service de maniere suivante:
 * 1. Créer un button pour lancer votre service et l'ajouter dans la vue.(Vous pouvez modifier cette vue si nécéssaire.)
 * 2. Ajouter les constraints pour votre service dans la methode showTokenMsg() si votre partie n'est
 *    destinée que pour l'un ou deux rôles. Les rôles sont @param token.
 * 3. Ajouter les codes pour lancer votre vue et controlleur dans actionPerformed(ActionEvent e) tout en bas.
 * 4. Tester est important!
 */
public class WelcomeView extends JFrame implements ActionListener{
    private JLabel welcome;
    private JLabel jtoken;
    private JButton vehicleInfo, idctOperation, changeStatus, pieceDetached;
    private String token;
    private Socket client;

    /**
     * @param token This is the EnumUserToken: REPAIRER, ADMINISTRATOR, DIRECTOR, MANUT
     * @param client The socket which is used to launch the service window's controller.
     */
    public WelcomeView(String token, Socket client){
        super("Services");
        this.token = token;
        this.client = client;
        setLayout(null);
        setSize(400, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcome = new JLabel("Welcome to use theOriginals technology");
        welcome.setBounds(50, 20, 300, 50);
        jtoken = new JLabel();
        jtoken.setBounds(35, 90, 350, 50);

        //Button
        vehicleInfo = new JButton("Vehicle Information");
        vehicleInfo.setBounds(100, 170, 200, 50);
        idctOperation = new JButton("Operation syntheses");
        idctOperation.setBounds(100, 380, 200, 50);
        changeStatus = new JButton("Way out vehicle");
        changeStatus.setBounds(100, 240, 200, 50);
        pieceDetached = new JButton("Add Detached Piece");
        pieceDetached.setBounds(100,310, 200, 50);

        this.showTokenMsg();
        vehicleInfo.addActionListener(this);
        changeStatus.addActionListener(this);
        pieceDetached.addActionListener(this);
        this.add(welcome);
        this.add(jtoken);
        this.add(vehicleInfo);
        this.add(changeStatus);
        this.add(pieceDetached);
        this.add(idctOperation);
    }

    public void showTokenMsg(){
        if(!token.equals(EnumUserToken.DIRECTOR.name()) && !token.equals(EnumUserToken.ADMINISTRATOR.name())){
            idctOperation.setEnabled(false);
            jtoken.setText("You are denied to use Operation Syntheses");
        } else {
            jtoken.setText("You are allowed to all service.");
            idctOperation.addActionListener(this);
        }

    }

    public void disposeView(){
        System.out.println("Exit with 0.");
        this.dispose();
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == idctOperation){
            SwingUtilities.invokeLater(()->{
                IndicatorActivityView idct = new IndicatorActivityView();
                ControllerIndicatorActivity c= new ControllerIndicatorActivity(idct, client);
                c.control();
            });
        }

        if (e.getSource() == vehicleInfo){

        }

        if (e.getSource() == pieceDetached){
            SwingUtilities.invokeLater(()->{
                ViewPieceDetached v = new ViewPieceDetached();
                ControllerPieceDetached c = new ControllerPieceDetached(v, client);
                c.control();
            });
        }

        if (e.getSource() == changeStatus){

        }
    }
}
