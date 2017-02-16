import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

/**
 * Created by tearsyu on 13/02/17.
 */
public class CreateLogin extends JFrame implements ActionListener, ItemListener{
    JLabel firstname, lastname, addr, numTel, pwd, repwd, role;
    JTextField firstnameT, lastnameT, addrT, numTelT;
    JPasswordField pwdT, repwdT;
    JButton create, exit, gotoLogin;
    JComboBox role_box;
    ConnectionJDBC conn;
    String roleT = "administrateur";
    public CreateLogin(ConnectionJDBC conn){
        super("Create your account");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.conn = conn;
        setLayout(null); //setlayout null is an obligation or it displays with bizarre phenomenon


        firstname = new JLabel("Firstname *");
        firstname.setBounds(30, 20, 180, 30);
        lastname = new JLabel("Lastname *");
        lastname.setBounds(250, 20, 180, 30);
        addr = new JLabel("Address");
        addr.setBounds(30, 100, 180, 30);
        numTel = new JLabel("Tel *");
        numTel.setBounds(250, 100, 180, 30);
        pwd = new JLabel("Password *");
        pwd.setBounds(30, 180, 180, 30);
        repwd = new JLabel("Confirm pwd *");
        repwd.setBounds(250, 180, 180, 30);
        role = new JLabel("Role");
        role.setBounds(30, 300, 100, 30);

        firstnameT = new JTextField(20);
        firstnameT.setBounds(30, 50, 180, 30);
        lastnameT = new JTextField(20);
        lastnameT.setBounds(250, 50, 180, 30);
        addrT = new JTextField(60);
        addrT.setBounds(30, 130, 180, 30);
        numTelT = new JTextField(60);
        numTelT.setBounds(250, 130, 180, 30);
        pwdT = new JPasswordField(12);
        pwdT.setBounds(30, 210, 180, 30);
        repwdT = new JPasswordField(12);
        repwdT.setBounds(250, 210, 180, 30);

        create = new JButton("Create");
        create.addActionListener(this);
        create.setBounds(30, 380, 100, 30);
        exit = new JButton("Exit");
        exit.addActionListener(this);
        exit.setBounds(180, 380, 100, 30);
        gotoLogin = new JButton("Return");
        gotoLogin.addActionListener(this);
        gotoLogin.setBounds(330, 380, 100, 30);

        role_box = new JComboBox();
        role_box.addItem("administrateur");
        role_box.addItem("directeur");
        role_box.addItem("reparateur");
        role_box.setBounds(150, 300, 180, 30);
        role_box.addItemListener(this);

        getContentPane().add(firstname);
        getContentPane().add(lastname);
        getContentPane().add(addr);
        add(numTel);
        add(pwd);
        add(repwd);
        getContentPane().add(firstnameT);
        getContentPane().add(lastnameT);
        getContentPane().add(addrT);
        add(numTelT);
        add(pwdT);
        add(repwdT);
        add(create);
        add(exit);
        add(gotoLogin);
        add(role);
        add(role_box);
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
            String getnum = numTelT.getText();
            String getaddr = addrT.getText();
            String getpwd = String.valueOf(pwdT.getPassword());
            String getrepwd = String.valueOf(repwdT.getPassword());

            if (getfirstname.equals("") || getlastname.equals("") || getnum.equals("") || getpwd.equals("") || getrepwd.equals("")){
                JOptionPane.showMessageDialog(null, "Please fill all text field with asterisk.");
            } else {
                System.out.println(checkPassword(getpwd));
                if (checkPassword(getpwd) == 0){
                    JOptionPane.showMessageDialog(null, "Password must be composed by number and letter with a length greater than 6.");
                } else if (getpwd.equals(getrepwd)){
                    try {
                        /*conn.statement = conn.conn.createStatement();
                        String sql = "INSERT INTO account(firstname, lastname, pwd, email, addr) VALUES (" +
                            "'" + getfirstname + "', '" +getlastname + "', '" + getpwd + "', '" + getemail
                            + "', '" + getaddr + "');";
                        System.out.println("SQL: " + sql);
                        conn.statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "You are registered.");*/

                        String sql = "call add_role(?,?,?,?,?,?)";
                        conn.callst = conn.conn.prepareCall(sql);
                        conn.callst.setString(1, roleT);
                        conn.callst.setString(2, getfirstname);
                        conn.callst.setString(3, getlastname);
                        conn.callst.setString(4, getpwd);
                        conn.callst.setString(5, getnum);
                        conn.callst.setString(6, getaddr);
                        conn.callst.execute();

                        sql = "SELECT login, password From " + roleT + " WHERE nom = '" + getlastname
                                + "' AND prenom = '" + getfirstname +"' order by login desc limit 1";
                        conn.statement = conn.conn.createStatement();
                        conn.rs = conn.statement.executeQuery(sql);

                        String login = null, passwd = null;
                        if (conn.rs.next()){
                            login = conn.rs.getString("login");
                            passwd = conn.rs.getString("password");
                        }
                        JOptionPane.showMessageDialog(null, "You are registered. \nYour login is :" + login +
                                "\nYour password is:" + passwd);
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

    /**
     * Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     *
     * @param e
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            roleT = e.getItem().toString();
            System.out.println(roleT);
        }
    }
}
