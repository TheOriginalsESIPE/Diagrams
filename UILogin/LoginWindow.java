import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by tearsyu on 13/02/17.
 */
public class LoginWindow extends JFrame implements ActionListener{

    JLabel login, pwd;
    JButton submit, exit, createLogin;
    JTextField loginEntry;
    JPasswordField pwdEntry;
    ConnectionJDBC conn;

    public LoginWindow(){
        super("Welcome to use CSC console");
        setSize(500,200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conn = new ConnectionJDBC();
        conn.connectJDBC();

        login = new JLabel("Username ");
        login.setBounds(30, 20, 80, 30);
        pwd = new JLabel("Password ");
        pwd.setBounds(30, 70, 80, 30);

        submit = new JButton("Sight in");
        submit.setBounds(20, 130, 100, 30);
        exit = new JButton("Exit");
        exit.setBounds(150, 130, 100, 30);
        createLogin = new JButton("I have no login");
        createLogin.setBounds(300, 130, 150, 30 );
        submit.addActionListener(this);
        exit.addActionListener(this);
        createLogin.addActionListener(this);

        loginEntry = new JTextField(50);
        loginEntry.setBounds(150, 20, 180, 30);
        pwdEntry = new JPasswordField(15);
        pwdEntry.setBounds(150, 70, 180, 30);

        add(login);
        add(pwd);
        add(submit);
        add(exit);
        add(createLogin);
        add(loginEntry);
        add(pwdEntry);
    }


    public static void main(String[] args){
        LoginWindow w = new LoginWindow();
        w.setVisible(true);

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit){
            String getLogin = loginEntry.getText();
            String getPwd = String.valueOf(pwdEntry.getPassword());
            try {
                conn.statement = conn.conn.createStatement();
                String sql = "SELECT pwd FROM account WHERE email='"+getLogin+"'";
                conn.rs = conn.statement.executeQuery(sql);
                if (conn.rs.first()){
                    if (getPwd.equals(conn.rs.getString("pwd"))){
                        JOptionPane.showMessageDialog(null, "Connect successfully!");
                    } else  {
                        JOptionPane.showMessageDialog(null, "Password error! Please retry");
                        pwdEntry.setText("");
                    }
                } else JOptionPane.showMessageDialog(null, "Login not found! Please retry.");
                conn.rs.close();
                conn.closeJDBC();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
        if (e.getSource() == exit){
            conn.closeJDBC();
            System.exit(0);

        }
        if (e.getSource() == createLogin){
            dispose();
            CreateLogin w = new CreateLogin(conn);
            w.setVisible(true);
        }
    }
}
