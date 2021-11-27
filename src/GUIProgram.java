import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

/**
 * A program that represents a drawing board
 * Purdue University -- CS18000 -- Fall 2021 -- Homework 12 -- Challenge
 *
 * @author Leo Pan 33415057
 * @version November 15, 2021
 */
public class GUIProgram extends JComponent implements Runnable {

    JFrame frame;
    Container loginWindow;


    JButton loginButton; // a button to login
    JButton signupButton; // a button to signup

    JTextField usernameField;
    JTextField passwordField;

    GUIProgram guiProgram;

    /* action listener for buttons */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {
                frame.setContentPane(loginWindow);
                frame.invalidate();
                frame.validate();
            }
            if (e.getSource() == signupButton) {

            }

        }
    };

    public GUIProgram() {
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUIProgram());
    }

    public void run() {
        /* set up JFrame */
        frame = new JFrame("Quiz System");
        Container startWindow = frame.getContentPane();
        startWindow.setLayout(new FlowLayout());

        loginButton = new JButton("Login");
        loginButton.addActionListener(actionListener);
        signupButton = new JButton("Signup");
        signupButton.addActionListener(actionListener);
        usernameField = new JTextField("");
        usernameField.addActionListener(actionListener);
        passwordField = new JTextField("");
        passwordField.addActionListener(actionListener);

        JPanel panel = new JPanel();
        panel.add(loginButton);
        panel.add(signupButton);
        startWindow.add(panel);

        // Layout of the login window
        loginWindow = new Container();
        loginWindow.setLayout(new BorderLayout());

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(2,2));
        loginPanel.add(new JLabel("Username: "));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password: "));
        loginPanel.add(passwordField);
        loginWindow.add(loginPanel, BorderLayout.CENTER);


        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        

        //while(stage1.equals(""));



        /*
        usernameField = new JTextField("username");
        usernameField.addActionListener(actionListener);
        passwordField = new JTextField("password");
        passwordField.addActionListener(actionListener);


        JPanel panel2 = new JPanel();
        panel2.add(usernameField);
        panel2.add(passwordField);
        content.add(panel2, BorderLayout.CENTER);
         */



    }

}
