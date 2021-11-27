import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * A program that represents a drawing board
 * Purdue University -- CS18000 -- Fall 2021 -- Homework 12 -- Challenge
 *
 * @author Leo Pan 33415057
 * @version November 15, 2021
 */
public class GUIProgram extends JComponent implements Runnable {

    JFrame frame;
    static Manager manager;


    Container startWindow;
    Container loginWindow;
    Container signupWindow;

    // Where all student related actions are stored
    Container studentWindow;
    // Where all teacher related actions are stored
    Container teacherWindow;


    JButton loginButton; // a button to login
    JButton signupButton; // a button to signup
    JButton signInButton;
    JButton createAccountButton;

    JTextField usernameFieldLogin;
    JTextField passwordFieldLogin;

    JTextField usernameFieldSignup;
    JTextField passwordFieldSignup;

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
                frame.setContentPane(signupWindow);
                frame.invalidate();
                frame.validate();
            }
            if (e.getSource() == signInButton) {
                ArrayList<Account> accounts = manager.getAccountList();
                String name = usernameFieldLogin.getText();
                String password = passwordFieldLogin.getText();

                Container nextWindow = null;
                for(Account a : accounts){
                    if(a.getUsername().equals(name) && a.getPassword().equals(password)){
                        if(a.isStudent()){
                            nextWindow = studentWindow;
                        }
                        else{
                            nextWindow = teacherWindow;
                        }
                        break;
                    }
                }
                if(nextWindow!=null){
                    frame.setContentPane(nextWindow);
                    frame.invalidate();
                    frame.validate();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Username or Password was incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    };

    public GUIProgram() {
        
    }

    public static void main(String[] args) {
        manager = new Manager();
        SwingUtilities.invokeLater(new GUIProgram());
    }

    public void run() {
        /* set up JFrame */
        frame = new JFrame("Quiz System");
        startWindow = new Container();
        startWindow.setLayout(new FlowLayout());

        // Initial window to be used
        frame.setContentPane(startWindow);

        // Button and Field creation
        loginButton = new JButton("Login");
        loginButton.addActionListener(actionListener);
        signInButton = new JButton("Sign In!");
        signInButton.addActionListener(actionListener);
        createAccountButton = new JButton("Create Account!");
        createAccountButton.addActionListener(actionListener);
        signupButton = new JButton("Signup");
        signupButton.addActionListener(actionListener);
        usernameFieldLogin = new JTextField("");
        usernameFieldLogin.addActionListener(actionListener);
        passwordFieldLogin = new JTextField("");
        passwordFieldLogin.addActionListener(actionListener);
        usernameFieldSignup = new JTextField("");
        usernameFieldSignup.addActionListener(actionListener);
        passwordFieldSignup = new JTextField("");
        passwordFieldSignup.addActionListener(actionListener);

        // Layout of the start window
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
        loginPanel.add(usernameFieldLogin);
        loginPanel.add(new JLabel("Password: "));
        loginPanel.add(passwordFieldLogin);
        loginWindow.add(loginPanel, BorderLayout.CENTER);
        loginWindow.add(signInButton, BorderLayout.SOUTH);

        // Layout of the signup window
        signupWindow = new Container();
        signupWindow.setLayout(new BorderLayout());

        JPanel signupPanel = new JPanel();
        signupPanel.setLayout(new GridLayout(2,2));
        signupPanel.add(new JLabel("Username: "));
        signupPanel.add(usernameFieldSignup);
        signupPanel.add(new JLabel("Password: "));
        signupPanel.add(passwordFieldSignup);
        signupWindow.add(signupPanel, BorderLayout.CENTER);
        signupWindow.add(createAccountButton, BorderLayout.SOUTH);

        // Makes the frame visible
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        

        //while(stage1.equals(""));



        /*
        usernameFieldLogin = new JTextField("username");
        usernameFieldLogin.addActionListener(actionListener);
        passwordFieldLogin = new JTextField("password");
        passwordFieldLogin.addActionListener(actionListener);


        JPanel panel2 = new JPanel();
        panel2.add(usernameFieldLogin);
        panel2.add(passwordFieldLogin);
        content.add(panel2, BorderLayout.CENTER);
         */



    }

}
