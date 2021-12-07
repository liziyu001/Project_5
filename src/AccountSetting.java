import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class AccountSetting extends javax.swing.JFrame {

    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editIdButton;
    private javax.swing.JButton editPwdButton;


    public AccountSetting() {
        initComponents();
    }


    private void initComponents() {

        editIdButton = new javax.swing.JButton();
        editPwdButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        editIdButton.setText("Edit Username");
        editIdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editIdButtonActionPerformed(evt);
            }
        });

        editPwdButton.setText("Edit Password");
        editPwdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPwdButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete this Account");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
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

        this.setTitle("Account Setting");
        getContentPane().setLayout(new BorderLayout());
        this.setSize(600, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.add(editIdButton);
        panel.add(editPwdButton);
        panel.add(deleteButton);
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void editIdButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String nID = JOptionPane.showInputDialog(null,
                "Enter the your new User ID",
                "Edit username", JOptionPane.QUESTION_MESSAGE);
        ArrayList<String> in = new ArrayList<String>();
        in.add(Main.getCurrentAccount());
        in.add(nID);
        ArrayList<String> out = Main.connect(in, 4006);
        if (out.get(0).equals("Success")) {
            Main.setCurrentAccount(in.get(1));
            JOptionPane.showMessageDialog(null, "Successfully editing your ID", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Your New ID has already be taken", "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editPwdButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String pwd = JOptionPane.showInputDialog(null,
                "Enter your new password",
                "Edit Password", JOptionPane.QUESTION_MESSAGE);
        ArrayList<String> in = new ArrayList<String>();
        in.add(Main.getCurrentAccount());
        in.add(pwd);
        ArrayList<String> out = Main.connect(in, 4007);
        if (out.get(0).equals("Success")) {
            JOptionPane.showMessageDialog(null, "Successfully editing your Password", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<String> in = new ArrayList<String>();
        in.add(Main.getCurrentAccount());
        ArrayList<String> out = Main.connect(in, 4008);
        if (out.get(0).equals("Success")) {
            System.exit(0); //to be changed
        } else {
            JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AccountSetting().setVisible(true);
            }
        });
    }




}
