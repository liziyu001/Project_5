import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Represents the account setting options of a person
 * @author Ram, William, Leo, Manas, Miras
 * @version December 13, 2021
 */
public class AccountSetting extends javax.swing.JFrame {
    //Button that allows person to delete their account
    private javax.swing.JButton deleteButton;
    //Button that allows person to edit their username
    private javax.swing.JButton editIdButton;
    //Button that allows person to edit their password
    private javax.swing.JButton editPwdButton;
    //Button that allows person to go back
    private javax.swing.JButton backButton;

    /**
     * Constructs a newly allocated AccountSetting object that calls the initComponents() method
     */
    public AccountSetting() {
        initComponents();
    }

    /**
     * Creates and initializes the four buttons with the set texts and the action listeners
     * That helps perform the functionality of each button when clicked
     */
    private void initComponents() {
        //Creates an edit ID Button
        editIdButton = new javax.swing.JButton();
        //Creates an edit password Button
        editPwdButton = new javax.swing.JButton();
        //Creates a delete Button
        deleteButton = new javax.swing.JButton();
        //Creates a back Button
        backButton = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        editIdButton.setText("Edit Username");
        //Allows the editId Button to be performed when clicked
        editIdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editIdButtonActionPerformed(evt);
            }
        });

        editPwdButton.setText("Edit Password");
        //Allows the editPwd Button to be performed when clicked
        editPwdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPwdButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete this Account");
        //Allows the delete Button to be performed when clicked
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        //Allows the back Button to be performed when clicked
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addGap(133, 133, 133)
//                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                        .addComponent(editIdButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                                                .addComponent(editPwdButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                .addComponent(deleteButton)))
//                                .addContainerGap(140, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addContainerGap(53, Short.MAX_VALUE)
//                                .addComponent(editIdButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(35, 35, 35)
//                                .addComponent(editPwdButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(34, 34, 34)
//                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(52, 52, 52))
//        );
//
//        pack();
        //Sets up panels and border layout, and adds the buttons to the panel
        this.setTitle("Account Setting");
        getContentPane().setLayout(new BorderLayout());
        this.setSize(600, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.add(editIdButton);
        panel.add(editPwdButton);
        panel.add(deleteButton);
        panel.add(backButton);
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    /**
     * @Description action when edit id button is pressed, represents editing username
     * @Date 8:23 PM 12/7/2021
     * @Param [evt] Allows the button's action to be performed
     * @return void
     */
    private void editIdButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String nID = JOptionPane.showInputDialog(null,
                "Enter the your new User ID",
                "Edit username", JOptionPane.QUESTION_MESSAGE);
        ArrayList<String> in = new ArrayList<String>();
        in.add(Main.getCurrentAccount());
        in.add(nID);
        if (in.get(1).contains(";")) {
            JOptionPane.showMessageDialog(null, "No semicolons allowed", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String> out = Main.connect(in, 4006);
            if (out.get(0).equals("Success")) {
                Main.setCurrentAccount(in.get(1));
                JOptionPane.showMessageDialog(null, "Successfully editing your ID", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Your New ID has already be taken", "Fail", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /**
     * @Description action when edit password button is pressed, represents editing password
     * @Date 8:24 PM 12/7/2021
     * @Param [evt] Allows the button's action to be performed
     * @return void
     */
    private void editPwdButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String pwd = JOptionPane.showInputDialog(null,
                "Enter your new password",
                "Edit Password", JOptionPane.QUESTION_MESSAGE);
        ArrayList<String> in = new ArrayList<String>();
        in.add(Main.getCurrentAccount());
        in.add(pwd);
        if (in.get(1).contains(";")) {
            JOptionPane.showMessageDialog(null, "No semicolons allowed", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String> out = Main.connect(in, 4007);
            if (out.get(0).equals("Success")) {
                JOptionPane.showMessageDialog(null, "Successfully editing your Password", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
            }
        }


    }
    
    /**
     * @Description action when delete button is pressed, represents deleting account
     * @Param [evt] Allows the button's action to be performed
     * @return void
     */
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<String> in = new ArrayList<String>();
        in.add(Main.getCurrentAccount());
        ArrayList<String> out = Main.connect(in, 4008);
        if (out.get(0).equals("Success")) {
            this.setVisible(false);
            JOptionPane.showMessageDialog(null, "Successfully deleting your account, you are now logged out", "Success", JOptionPane.INFORMATION_MESSAGE);
            new Menu().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * @Description action when back button is pressed
     * @Param [evt] Allows the button's action to be performed
     * @return void
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        if (Main.isStudent() == true) {
            new StudentMenu().setVisible(true);
        } else {
            new TeacherMenu().setVisible(true);
        }
    }
    
    /**
     * Main method that invokes the AWT and runs the AccountSetting object that displays the buttons and all its functionalities
     * @param args Stores the command line arguments
     */
    public static void main(String args[]) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AccountSetting().setVisible(true);
            }
        });
    }




}
