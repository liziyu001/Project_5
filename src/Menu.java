import javax.swing.*;
import java.awt.*;

/**
 * Represents the beginning menu where you can login if you already have an account, or you can register if you haven't created an account
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
                RegisterActionPerformed(evt);
            }
        });

//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//
//        layout.setHorizontalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                        .addGroup(layout.createSequentialGroup()
//                                                .addGap(179, 179, 179)
//                                                .addComponent(jLabel1))
//                                        .addGroup(layout.createSequentialGroup()
//                                                .addGap(119, 119, 119)
//                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                                                        .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                                        .addComponent(Register, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))))
//                                .addContainerGap(132, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addGap(30, 30, 30)
//                                .addComponent(jLabel1)
//                                .addGap(36, 36, 36)
//                                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(36, 36, 36)
//                                .addComponent(Register, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addContainerGap(82, Short.MAX_VALUE))
//        );
//
//        pack();
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
    }
    
    /**
     * @Description action when login button is pressed, represents logging in
     * @Param [evt] Allows the button's action to be performed
     * @return void
     */
    private void loginActionPerformed(java.awt.event.ActionEvent evt) {
        Login l = new Login();
        l.setVisible(true);
        this.setVisible(false);
    }
    
    /**
     * @Description action when back button is pressed, represents registering an account
     * @Param [evt] Allows the button's action to be performed
     * @return void
     */
    private void RegisterActionPerformed(java.awt.event.ActionEvent evt) {
        Register r = new Register();
        r.setVisible(true);
        this.setVisible(false);
    }

    /**
     * Main method that invokes the AWT and runs the Menu object that displays the buttons and all its functionalities
     * @param args Stores the command line arguments
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }




}
