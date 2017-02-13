import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by tearsyu on 13/02/17.
 */
public class CreateLogin extends JFrame implements ActionListener{
    JLabel firstname, lastname, addr, email, pwd, repwd;
    JTextField firstnameT, lastnameT, addrT, emailT;
    JPasswordField pwdT, repwdT;
    JButton create, exit, gotoLogin;
    ConnectionJDBC conn;

    public CreateLogin(ConnectionJDBC conn){
        super("Create your account");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.conn = conn;
        setLayout(null); //setlayout null is an obligation or it displays with bizarre phenomenon

        firstname = new JLabel("Firstname *");
        firstname.setBounds(30, 20, 180, 30);
        lastname = new JLabel("Lastname *");
        lastname.setBounds(250, 20, 180, 30);
        addr = new JLabel("Address");
        addr.setBounds(30, 100, 180, 30);
        email = new JLabel("Email *");
        email.setBounds(250, 100, 180, 30);
        pwd = new JLabel("Password *");
        pwd.setBounds(30, 180, 180, 30);
        repwd = new JLabel("Confirm pwd *");
        repwd.setBounds(250, 180, 180, 30);

        firstnameT = new JTextField(20);
        firstnameT.setBounds(30, 50, 180, 30);
        lastnameT = new JTextField(20);
        lastnameT.setBounds(250, 50, 180, 30);
        addrT = new JTextField(60);
        addrT.setBounds(30, 130, 180, 30);
        emailT = new JTextField(60);
        emailT.setBounds(250, 130, 180, 30);
        pwdT = new JPasswordField(12);
        pwdT.setBounds(30, 210, 180, 30);
        repwdT = new JPasswordField(12);
        repwdT.setBounds(250, 210, 180, 30);

        create = new JButton("Create");
        create.addActionListener(this);
        create.setBounds(30, 300, 100, 30);
        exit = new JButton("Exit");
        exit.addActionListener(this);
        exit.setBounds(150, 300, 100, 30);
        gotoLogin = new JButton("Return");
        gotoLogin.addActionListener(this);
        gotoLogin.setBounds(300, 300, 100, 30);

        getContentPane().add(firstname);
        getContentPane().add(lastname);
        getContentPane().add(addr);
        add(email);
        add(pwd);
        add(repwd);
        getContentPane().add(firstnameT);
        getContentPane().add(lastnameT);
        getContentPane().add(addrT);
        add(emailT);
        add(pwdT);
        add(repwdT);
        add(create);
        add(exit);
        add(gotoLogin);
    }

    //Check if password is enough strong
    public int checkPassword(String passwordStr) {
        String regexZ = "\\d*";
        String regexS = "[a-zA-Z]+";
        String regexT = "\\W+$";
        String regexZT = "\\D*";
        String regexST = "[\\d\\W]*";
        String regexZS = "\\w*";
        String regexZST = "[\\w\\W]*";

        if (passwordStr.matches(regexZ)) {
            return 0;
        }
        if (passwordStr.matches(regexS)) {
            return 0;
        }
        if (passwordStr.matches(regexT)) {
            return 0;
        }
        if (passwordStr.matches(regexZT)) {
            return 0;
        }
        if (passwordStr.matches(regexST)) {
            return 1;
        }
        if (passwordStr.matches(regexZS)) {
            return 1;
        }
        if (passwordStr.matches(regexZST)) {
            return 2;
        }
        return 2;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create){
            String getfirstname = firstnameT.getText();
            String getlastname = lastnameT.getText();
            String getemail = emailT.getText();
            String getaddr = addrT.getText();
            String getpwd = String.valueOf(pwdT.getPassword());
            String getrepwd = String.valueOf(repwdT.getPassword());

            if (getfirstname.equals("") || getlastname.equals("") || getemail.equals("") || getpwd.equals("") || getrepwd.equals("")){
                JOptionPane.showMessageDialog(null, "Please fill all text field with asterisk.");
            } else {
                System.out.println(checkPassword(getpwd));
                if (checkPassword(getpwd) == 0){
                    JOptionPane.showMessageDialog(null, "Password must be composed by number and letter with a length greater than 6.");
                } else if (getpwd.equals(getrepwd)){
                    try {
                        conn.statement = conn.conn.createStatement();
                        String sql = "INSERT INTO account(firstname, lastname, pwd, email, addr) VALUES (" +
                            "'" + getfirstname + "', '" +getlastname + "', '" + getpwd + "', '" + getemail
                            + "', '" + getaddr + "');";
                        System.out.println("SQL: " + sql);
                        conn.statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "You are registered.");
                        conn.closeJDBC();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords are not equal. Please retry.");
                    pwdT.setText("");
                    repwdT.setText("");
                }

            }
        }
        if (e.getSource() == exit){
            System.exit(0);
        }

        if (e.getSource() == gotoLogin){
            conn.closeJDBC();
            this.dispose();
            LoginWindow w = new LoginWindow();
            w.setVisible(true);
        }
    }
}
