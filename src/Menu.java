import javax.swing.*;
import java.awt.*;


public class Menu extends javax.swing.JFrame {

    private javax.swing.JButton register;
    private javax.swing.JButton login;


    public Menu() {
        initComponents();
    }

    private void initComponents() {


        login = new javax.swing.JButton();
        register = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        register.setText("Register");
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

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {
        Login l = new Login();
        l.setVisible(true);
        this.setVisible(false);
    }

    private void RegisterActionPerformed(java.awt.event.ActionEvent evt) {
        Register r = new Register();
        r.setVisible(true);
        this.setVisible(false);
    }


    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }




}
