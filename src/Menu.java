import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Represents the beginning menu where you can login if you already have an account,
 * or you can register if you haven't created an account
 *
 * @author Ram, William, Leo, Manas, Miras
 * @version December 13, 2021
 */
public class Menu extends javax.swing.JFrame {

    //Button that allows person to register
    private javax.swing.JButton register;
    //Button that allows person to login
    private javax.swing.JButton login;

    /**
     * Constructs a newly allocated Menu object that calls the initComponents() method
     */
    public Menu() {
        initComponents();
    }

    /**
     * Creates and initializes the register and login button with its action performed when clicked
     * Panels and BorderLayout is also set up
     */
    private void initComponents() {

        //Creates login button
        login = new javax.swing.JButton();
        //Creates register button
        register = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        login.setText("Login");
        //Allows action of login button to be performed when clicked
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        register.setText("Register");
        //Allows action of register button to be performed when clicked
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });

        //Sets the panel and border layout
        this.setTitle("Menu");
        getContentPane().setLayout(new BorderLayout());
        this.setSize(600, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.add(login);
        panel.add(register);
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
    }

    /**
     * @return void
     * @Description action when login button is pressed, represents logging in
     * @Param [evt] Allows the button's action to be performed
     */
    private void loginActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        try {
            String id = JOptionPane.showInputDialog(null,
                    "Enter your Username",
                    "Username input", JOptionPane.QUESTION_MESSAGE);
            String pwd = JOptionPane.showInputDialog(null,
                    "Enter your Password",
                    "Password input", JOptionPane.QUESTION_MESSAGE);
            ArrayList<String> in = new ArrayList<>();
            in.add(id);
            in.add(pwd);
            if (in.get(0).contains(";") || in.get(1).contains(";")) {
                JOptionPane.showMessageDialog(null,
                        "No semicolons allowed", "Error", JOptionPane.ERROR_MESSAGE);
                this.setVisible(true);
            } else {
                ArrayList<String> out = Main.connect(in, 4000);
                if (!out.get(0).equals("Fail")) {
                    if (out.get(0).equals("true")) {
                        JOptionPane.showMessageDialog(null,
                                "Successfully login as Student " + in.get(0),
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Successfully login as Teacher " + in.get(0),
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    Main.setCurrentAccount(in.get(0));
                    if (out.get(0).equals("true")) {
                        Main.setStudent(true);
                        StudentMenu sm = new StudentMenu();
                        sm.setVisible(true);
                    } else {
                        Main.setStudent(false);
                        TeacherMenu tm = new TeacherMenu();
                        tm.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    this.setVisible(true);
                }
            }
        } catch (Exception e) {
            this.setVisible(true);
        }

    }

    /**
     * @return void
     * @Description action when back button is pressed, represents registering an account
     * @Param [evt] Allows the button's action to be performed
     */
    private void registerActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        try {
            String[] roles = {"Teacher", "Student"};
            String id = JOptionPane.showInputDialog(null,
                    "Enter your Username",
                    "Username input", JOptionPane.QUESTION_MESSAGE);
            String pwd = JOptionPane.showInputDialog(null,
                    "Enter your Password",
                    "Password input", JOptionPane.QUESTION_MESSAGE);
            ArrayList<String> in = new ArrayList<String>();
            in.add(id);
            in.add(pwd);
            String role = (String) JOptionPane.showInputDialog(null, "What" +
                            "Your role:",
                    "Role Choice", JOptionPane.QUESTION_MESSAGE, null, roles,
                    roles[0]);
            in.add(role);
            if (in.get(0).contains(";") || in.get(1).contains(";")) {
                JOptionPane.showMessageDialog(null, "No semicolons allowed",
                        "Error", JOptionPane.ERROR_MESSAGE);
                this.setVisible(true);
            } else if (in.get(0).isEmpty() || in.get(1).isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid input",
                        "Error", JOptionPane.ERROR_MESSAGE);
                this.setVisible(true);
            } else {
                ArrayList<String> out = Main.connect(in, 4001);
                if (out.get(0).equals("Success")) {
                    Main.setCurrentAccount(in.get(0));
                    JOptionPane.showMessageDialog(null, "Success",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    if (role.equals("Teacher")) {
                        Main.setStudent(false);
                        TeacherMenu tm = new TeacherMenu();
                        tm.setVisible(true);
                    } else {
                        Main.setStudent(true);
                        StudentMenu sm = new StudentMenu();
                        sm.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "this id has already been taken", "Error", JOptionPane.ERROR_MESSAGE);
                    this.setVisible(true);
                }
            }
        } catch (Exception e) {
            this.setVisible(true);
        }

    }

    /**
     * Main method that invokes run using EDT and runs the Menu object that displays the
     * buttons and all its functionalities
     *
     * @param args Stores the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }


}
