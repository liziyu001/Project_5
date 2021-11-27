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
    Account currentAccount;


    Container startWindow;
    Container loginWindow;
    Container signupWindow;

    // Where all student related actions are stored
    Container studentWindow;
    // Where all teacher related actions are stored
    Container teacherWindow;
    // Where the student account settings are stored
    Container studentAccountWindow;
    // Where the teacher account settings are stored
    Container teacherAccountWindow;

    Container previousWindow;


    JButton loginButton; // a button to login
    JButton signupButton; // a button to signup
    JButton signInButton;
    JButton createAccountButton;
    JButton studentOrTeacherButton; // a button which will alternate its text when clicked

    // Student-related buttons
    JButton viewCoursesStudentButton;
    JButton accountSettingsStudentButton;
    JButton exitStudentButton;
    JButton editPasswordStudentButton;
    JButton editUsernameStudentButton;
    JButton deleteAccountStudentButton;
    JButton backAccountSettingsStudentButton;

    // Teacher-related buttons
    JButton viewCoursesTeacherButton;
    JButton accountSettingsTeacherButton;
    JButton exitTeacherButton;
    JButton editPasswordTeacherButton;
    JButton editUsernameTeacherButton;
    JButton deleteAccountTeacherButton;
    JButton backAccountSettingsTeacherButton;
    


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
                previousWindow = frame.getContentPane();
                frame.setContentPane(loginWindow);
                refresh();
            }
            if (e.getSource() == signupButton) {
                previousWindow = frame.getContentPane();
                frame.setContentPane(signupWindow);
                refresh();
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
                        currentAccount = a;
                        break;
                    }
                }
                if(nextWindow!=null){
                    previousWindow = frame.getContentPane();
                    frame.setContentPane(nextWindow);
                    refresh();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Username or Password was incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == createAccountButton) {
                ArrayList<Account> accounts = manager.getAccountList();
                String name = usernameFieldSignup.getText();
                String password = passwordFieldSignup.getText();
                boolean isStudent = studentOrTeacherButton.getText().equals("Student");
                boolean valid = true;

                if(name.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(name.contains(";")){
                    JOptionPane.showMessageDialog(null, "Usernames cannot contain semicolons", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(password.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter a password", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(name.contains(";")){
                    JOptionPane.showMessageDialog(null, "Passwords cannot contain semicolons", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                for(Account a : accounts){
                    if(a.getUsername().equals(name)){
                        JOptionPane.showMessageDialog(null, "Someone already has this username!", "Error", JOptionPane.ERROR_MESSAGE);
                        valid = false;
                        break;
                    }
                }

                if(valid){
                    Account a = new Account(name, password, isStudent);
                    currentAccount = a;
                    manager.getAccountList().add(a);

                    // TODO FILE UPDATING
                    manager.updateAccount();

                    if(isStudent){
                        previousWindow = frame.getContentPane();
                        frame.setContentPane(studentWindow);
                        refresh();
                    }
                    else{
                        previousWindow = frame.getContentPane();
                        frame.setContentPane(teacherWindow);
                        refresh();
                    }
                }


            }
            if (e.getSource() == studentOrTeacherButton){
                if(studentOrTeacherButton.getText().equals("Student")){
                    studentOrTeacherButton.setText("Teacher");
                }
                else{ // equals "Teacher
                    studentOrTeacherButton.setText("Student");
                }
            }
            if (e.getSource() == accountSettingsStudentButton){
                previousWindow = frame.getContentPane();
                frame.setContentPane(studentAccountWindow);
                refresh();
            }
            if (e.getSource() == accountSettingsTeacherButton){
                previousWindow = frame.getContentPane();
                frame.setContentPane(teacherAccountWindow);
                refresh();
            }

            if (e.getSource() == editUsernameStudentButton) {
                startAgain:
                while(true){
                    String newUsername = JOptionPane.showInputDialog(null, "Enter your new username:", "Edit Username", JOptionPane.QUESTION_MESSAGE);
                    if(newUsername==null){ // user closed the window
                        frame.setContentPane(previousWindow);
                        refresh();
                        break;
                    }
                    else if(newUsername.equals("")){ // user entered a blank username
                        JOptionPane.showMessageDialog(null, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
                        continue startAgain;
                    }
                    else if(newUsername.contains(";")) { // user's newUsername has a ";"
                        JOptionPane.showMessageDialog(null, "Username cannot include a semicolon", "Error", JOptionPane.ERROR_MESSAGE);
                        continue startAgain;
                    }
                    else{ // their input was valid but we have to check for existing usernames
                        ArrayList<Account> accounts = manager.getAccountList();
                        for(Account a : accounts){
                            if(a.getUsername().equals(newUsername)){
                                JOptionPane.showMessageDialog(null, "Someone has taken this username!", "Error", JOptionPane.ERROR_MESSAGE);
                                continue startAgain;
                            }
                            else{
                                currentAccount.setUsername(newUsername);

                                // TODO FILE EDITING HERE
                                manager.updateAccount();
                            }
                        }
                        break;
                    }

                }

            }
            if (e.getSource() == editPasswordStudentButton) {
                startAgain:
                while(true){
                    String newPassword = JOptionPane.showInputDialog(null, "Enter your new password:", "Edit Username", JOptionPane.QUESTION_MESSAGE);
                    if(newPassword==null){ // user closed the window
                        frame.setContentPane(previousWindow);
                        refresh();
                        break;
                    }
                    else if(newPassword.equals("")){ // user entered a blank password
                        JOptionPane.showMessageDialog(null, "Please enter a password", "Error", JOptionPane.ERROR_MESSAGE);
                        continue startAgain;
                    }
                    else if(newPassword.contains(";")) { // user's newPassword has a ";"
                        JOptionPane.showMessageDialog(null, "Username cannot include a semicolon", "Error", JOptionPane.ERROR_MESSAGE);
                        continue startAgain;
                    }
                    else{ // their input was valid

                        currentAccount.setPassword(newPassword);

                        // TODO FILE EDITING HERE
                        manager.updateAccount();
                        break;
                    }

                }

            }
            if (e.getSource() == deleteAccountStudentButton){
                manager.getAccountList().remove(currentAccount);
                manager.updateAccount();
                frame.setContentPane(startWindow);
                refresh();
            }
            if (e.getSource() == backAccountSettingsStudentButton){
                frame.setContentPane(previousWindow);
                refresh();
            }

            if (e.getSource() == editUsernameTeacherButton) {
                startAgain:
                while(true){
                    String newUsername = JOptionPane.showInputDialog(null, "Enter your new username:", "Edit Username", JOptionPane.QUESTION_MESSAGE);
                    if(newUsername==null){ // user closed the window
                        frame.setContentPane(previousWindow);
                        refresh();
                        break;
                    }
                    else if(newUsername.equals("")){ // user entered a blank username
                        JOptionPane.showMessageDialog(null, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
                        continue startAgain;
                    }
                    else if(newUsername.contains(";")) { // user's newUsername has a ";"
                        JOptionPane.showMessageDialog(null, "Username cannot include a semicolon", "Error", JOptionPane.ERROR_MESSAGE);
                        continue startAgain;
                    }
                    else{ // their input was valid but we have to check for existing usernames
                        ArrayList<Account> accounts = manager.getAccountList();
                        for(Account a : accounts){
                            if(a.getUsername().equals(newUsername)){
                                JOptionPane.showMessageDialog(null, "Someone has taken this username!", "Error", JOptionPane.ERROR_MESSAGE);
                                continue startAgain;
                            }
                            else{
                                currentAccount.setUsername(newUsername);

                                // TODO FILE EDITING HERE
                                manager.updateAccount();
                            }
                        }
                        break;
                    }

                }

            }
            if (e.getSource() == editPasswordTeacherButton) {
                startAgain:
                while(true){
                    String newPassword = JOptionPane.showInputDialog(null, "Enter your new password:", "Edit Username", JOptionPane.QUESTION_MESSAGE);
                    if(newPassword==null){ // user closed the window
                        frame.setContentPane(previousWindow);
                        refresh();
                        break;
                    }
                    else if(newPassword.equals("")){ // user entered a blank password
                        JOptionPane.showMessageDialog(null, "Please enter a password", "Error", JOptionPane.ERROR_MESSAGE);
                        continue startAgain;
                    }
                    else if(newPassword.contains(";")) { // user's newPassword has a ";"
                        JOptionPane.showMessageDialog(null, "Username cannot include a semicolon", "Error", JOptionPane.ERROR_MESSAGE);
                        continue startAgain;
                    }
                    else{ // their input was valid

                        currentAccount.setPassword(newPassword);

                        // TODO FILE EDITING HERE
                        manager.updateAccount();
                        break;
                    }

                }

            }
            if (e.getSource() == deleteAccountTeacherButton){
                manager.getAccountList().remove(currentAccount);
                manager.updateAccount();
                frame.setContentPane(startWindow);
                refresh();
            }
            if (e.getSource() == backAccountSettingsTeacherButton){
                frame.setContentPane(previousWindow);
                refresh();
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
        studentOrTeacherButton = new JButton("Student");
        studentOrTeacherButton.addActionListener(actionListener);
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
        
        viewCoursesStudentButton = new JButton("View Courses");
        viewCoursesStudentButton.addActionListener(actionListener);
        accountSettingsStudentButton = new JButton("Account Settings");
        accountSettingsStudentButton.addActionListener(actionListener);
        exitStudentButton = new JButton("Exit");
        exitStudentButton.addActionListener(actionListener);
        editUsernameStudentButton = new JButton("Edit Username");
        editUsernameStudentButton.addActionListener(actionListener);
        editPasswordStudentButton = new JButton("Edit Password");
        editPasswordStudentButton.addActionListener(actionListener);
        deleteAccountStudentButton = new JButton("Delete Account");
        deleteAccountStudentButton.addActionListener(actionListener);
        backAccountSettingsStudentButton = new JButton("Back");
        backAccountSettingsStudentButton.addActionListener(actionListener);

        viewCoursesTeacherButton = new JButton("View Courses");
        viewCoursesTeacherButton.addActionListener(actionListener);
        accountSettingsTeacherButton = new JButton("Account Settings");
        accountSettingsTeacherButton.addActionListener(actionListener);
        exitTeacherButton = new JButton("Exit");
        exitTeacherButton.addActionListener(actionListener);
        editUsernameTeacherButton = new JButton("Edit Username");
        editUsernameTeacherButton.addActionListener(actionListener);
        editPasswordTeacherButton = new JButton("Edit Password");
        editPasswordTeacherButton.addActionListener(actionListener);
        deleteAccountTeacherButton = new JButton("Delete Account");
        deleteAccountTeacherButton.addActionListener(actionListener);
        backAccountSettingsTeacherButton = new JButton("Back");
        backAccountSettingsTeacherButton.addActionListener(actionListener);

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
        signupWindow.add(studentOrTeacherButton, BorderLayout.NORTH);

        // Layout of the student window
        studentWindow = new Container();
        studentWindow.setLayout(new BorderLayout());

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new GridLayout(3,1));
        studentPanel.add(viewCoursesStudentButton);
        studentPanel.add(accountSettingsStudentButton);
        studentPanel.add(exitStudentButton);
        studentWindow.add(studentPanel, BorderLayout.CENTER);

        // Layout of the teacher window
        teacherWindow = new Container();
        teacherWindow.setLayout(new BorderLayout());

        JPanel teacherPanel = new JPanel();
        teacherPanel.setLayout(new GridLayout(3,1));
        teacherPanel.add(viewCoursesTeacherButton);
        teacherPanel.add(accountSettingsTeacherButton);
        teacherPanel.add(exitTeacherButton);
        teacherWindow.add(teacherPanel, BorderLayout.CENTER);

        // Layout of the student account settings window
        studentAccountWindow = new Container();
        studentAccountWindow.setLayout(new BorderLayout());

        JPanel studentAccountPanel = new JPanel();
        studentAccountPanel.setLayout(new GridLayout(2, 2));
        studentAccountPanel.add(editUsernameStudentButton);
        studentAccountPanel.add(editPasswordStudentButton);
        studentAccountPanel.add(deleteAccountStudentButton);
        studentAccountPanel.add(backAccountSettingsStudentButton);
        studentAccountWindow.add(studentAccountPanel, BorderLayout.CENTER);
        
        // Layout of the student account settings window
        teacherAccountWindow = new Container();
        teacherAccountWindow.setLayout(new BorderLayout());

        JPanel teacherAccountPanel = new JPanel();
        teacherAccountPanel.setLayout(new GridLayout(2, 2));
        teacherAccountPanel.add(editUsernameTeacherButton);
        teacherAccountPanel.add(editPasswordTeacherButton);
        teacherAccountPanel.add(deleteAccountTeacherButton);
        teacherAccountPanel.add(backAccountSettingsTeacherButton);
        teacherAccountWindow.add(teacherAccountPanel, BorderLayout.CENTER);


        // Makes the frame visible
        frame.setSize(600, 400);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    /**
     * Helper method which will refresh the frame, and pack it to best fit all containers.
     */
    private void refresh(){
        frame.invalidate();
        frame.validate();
        frame.pack();
    }

}
