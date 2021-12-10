import java.util.ArrayList;
import javax.swing.*;
/**
 * Represents the register aspect where people can create their username and password, choose their role, and register
 * @author Ram, William, Leo, Manas, Miras
 * @version December 13, 2021
 */
public class Register extends javax.swing.JFrame {

    /**
     * Constructs a newly allocated Register object that calls the initComponents() method
     * 
     */
    public Register() {
        initComponents();
    }

    /**
     * Creates and initializes the text fields, labels, and register button 
     * Also sets the entire layout for the register board with each JTextField, JButton, and JLabel positioned at a specific point
     */
    private void initComponents() {
        //Creates register label
        jLabel1 = new javax.swing.JLabel();
        //Creates register button
        registerButton = new javax.swing.JButton();
        //Creates username text field
        idIn = new javax.swing.JTextField();
        //Creates password text field
        pwdIn = new javax.swing.JTextField();
        //Creates username label
        jLabel2 = new javax.swing.JLabel();
        //Creates password label
        jLabel3 = new javax.swing.JLabel();
        //Creates an option to choose role (Teacher or Student)
        role = new java.awt.Choice();
        //Creates role label
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Register");

        registerButton.setText("Register");
        //Allows action of register button to be performed when clicked
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("UserName");

        jLabel3.setText("Password");

        jLabel4.setText("Role");
        //Designs the whole layout of the register board
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(163, 163, 163)
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 70, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4))
                                .addGap(32, 101, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(idIn, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                                .addComponent(pwdIn))
                                        .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(72, 72, 72))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(registerButton)
                                .addGap(147, 147, 147))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel1)
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(idIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(pwdIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(registerButton)
                                .addGap(27, 27, 27))
        );

        role.add("Teacher");
        role.add("Student");

        pack();
    }
    
    /**
     * @Description action when back button is pressed, represents registering an account with select username, password, and role chosen
     * @Param [evt] Allows the button's action to be performed
     * @return void
     */
    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<String> in = new ArrayList<String>();
        in.add(idIn.getText());
        in.add(pwdIn.getText());
        in.add(role.getSelectedItem());
        if (in.get(0).contains(";") || in.get(1).contains(";")) {
            JOptionPane.showMessageDialog(null, "No semicolons allowed", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (in.get(0).isEmpty() || in.get(1).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid input", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String> out = Main.connect(in, 4001);
            if (out.get(0).equals("Success")) {
                Main.setCurrentAccount(in.get(0));
                JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                if (role.getSelectedItem().equals("Teacher")) {
                    Main.setStudent(false);
                    TeacherMenu tm = new TeacherMenu();
                    tm.setVisible(true);
                } else {
                    Main.setStudent(true);
                    StudentMenu sm = new StudentMenu();
                    sm.setVisible(true);
                }
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "this id has already been taken", "Error", JOptionPane.ERROR_MESSAGE);
                this.setVisible(false);
            }
        }

    }

    /**
     * Main method that invokes the AWT and runs the Register object that displays the register button, textfields, labels, and all its functionalities
     * @param args Stores the command line arguments
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    //Username that the person wants to register
    private javax.swing.JTextField idIn;
    //Represents the Register label
    private javax.swing.JLabel jLabel1;
    //Represents the Username label
    private javax.swing.JLabel jLabel2;
    //Represents the Password label
    private javax.swing.JLabel jLabel3;
    //Represents the Role label
    private javax.swing.JLabel jLabel4;
    //Password that the person wants to register
    private javax.swing.JTextField pwdIn;
    //Button that gives the person the register options
    private javax.swing.JButton registerButton;
    //The role that the person can choose (Teacher or Student)
    private java.awt.Choice role;

}

