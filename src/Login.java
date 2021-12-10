import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * Represents the login aspect of the program which allows a person to enter their username and password, and login to their account
 * @author Ram, William, Leo, Manas, Miras
 * @version December 13, 2021
 */
public class Login extends javax.swing.JFrame {
    //Text Field where the person can enter their username
    private javax.swing.JTextField idIn;
    //Represents the label for login
    private javax.swing.JLabel jLabel1;
    //Represents the label for username
    private javax.swing.JLabel jLabel2;
    //Represents the label for password
    private javax.swing.JLabel jLabel3;
    //Button that allows the person to login
    private javax.swing.JButton loginButton;
    //Text Field where the person can enter their password
    private javax.swing.JTextField pwdIn;
    
    /** 
     * Constructs a newly allocated Login object that calls the initComponents() method
     */
    public Login() {
        initComponents();
    }
    
    /**
     * Creates and initializes the text fields, labels, and login button
     * Also sets the entire layout for the login board with each JTextField, JButton, and JLabel positioned at a specific point 
     */
    private void initComponents() {
        //Creates the Login JLabel
        jLabel1 = new javax.swing.JLabel();
        //Creates the Username JLabel
        jLabel2 = new javax.swing.JLabel();
        //Creates the Username Text Field
        idIn = new javax.swing.JTextField();
        //Creates the Password Text Field
        pwdIn = new javax.swing.JTextField();
        //Creates the Password JLabel
        jLabel3 = new javax.swing.JLabel();
        //Creates the login Button
        loginButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Login");

        jLabel2.setText("Username");

        jLabel3.setText("Password");

        loginButton.setText("Login");
        //Allows the loginButton to be performed when clicked
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        //Designs the whole layout of the login board
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(103, 103, 103)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3))
                                                .addGap(86, 86, 86)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(pwdIn, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                                        .addComponent(idIn)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(166, 166, 166)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(14, 14, 14)
                                                                .addComponent(loginButton)))))
                                .addContainerGap(125, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(idIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(pwdIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addComponent(loginButton)
                                .addGap(42, 42, 42))
        );

        pack();
    }
    
    /**
     * @Description action when login button is pressed, represents logging into an account
     * @Param [evt] Allows the button's action to be performed
     * @return void
     */
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<String> in = new ArrayList<String>();
        in.add(idIn.getText());
        in.add(pwdIn.getText());
        if (in.get(0).contains(";") || in.get(1).contains(";")) {
            JOptionPane.showMessageDialog(null, "No semicolons allowed", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String> out = Main.connect(in, 4000);
            if (!out.get(0).equals("Fail")) {
                if (out.get(0).equals("true")) {
                    JOptionPane.showMessageDialog(null, "Successfully login as Student " + in.get(0), "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Successfully login as Teacher " + in.get(0), "Success", JOptionPane.INFORMATION_MESSAGE);
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
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /**
     * Main method that invokes the AWT and runs the Login object that displays the login button, text fields, labels, and all its functionalities
     * @param args Stores the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }


}
