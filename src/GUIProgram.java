import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
    ArrayList<Course> courses;
    Course currentCourse;
    ArrayList<Quiz> quizzes;
    Quiz currentQuiz;
    Question[] questions;
    Question currentQuestion;
    String account;
    String[] studentAnswers;


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
    // Where the student courses are stored
    Container studentCoursesWindow;
    //Where the teacher course are stored
    Container teacherCoursesWindow;
    // Where the student views all quizzes
    Container studentViewQuizzesWindow;
    //Where the teacher views all quizzes
    Container teacherViewQuizzesWindow;
    // Where the student takes the quiz
    Container studentQuizWindow;

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
    JButton viewCourseStudentButton;
    JButton viewQuizStudentButton;
    JButton previousQuestionButton;
    JButton nextQuestionButton;
    JButton selectAnswerButton;
    JButton submitQuizButton;

    // Teacher-related buttons
    JButton viewCoursesTeacherButton;
    JButton accountSettingsTeacherButton;
    JButton createCourseButton;
    JButton exitTeacherButton;
    JButton editPasswordTeacherButton;
    JButton editUsernameTeacherButton;
    JButton deleteAccountTeacherButton;
    JButton backAccountSettingsTeacherButton;
    JButton backLoginWindowButton;
    JButton backSignUpWindowButton;
    JButton viewCourseTeacherButton;
    JButton addQuizButton;
    JButton editQuizButton;
    JButton ViewSubmissionsButton;
    JTextField usernameFieldLogin;
    JTextField passwordFieldLogin;

    JTextField usernameFieldSignup;
    JTextField passwordFieldSignup;

    JLabel questionDisplay;
    JComboBox<String> answerDisplay;

    JComboBox<String> courseListGUI;
    JComboBox<String> courseListGUITeacher;
    JComboBox<String> quizListGUI;
    JComboBox<String> quizListGUITeacher;

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
                //prepare input for the connection
                ArrayList<String> in = new ArrayList<String>();
                in.add(usernameFieldLogin.getText());
                in.add(passwordFieldLogin.getText());
                Container nextWindow = null;
                //receive output(validation result) from the server
                ArrayList<String> out = connect(in, 4000);
                System.out.println(out.get(0));
                if (!out.get(0).equals("Fail")) {
                    if (out.get(0).equals("true")) {
                        nextWindow = studentWindow;
                    } else {
                        nextWindow = teacherWindow;
                    }
                    currentAccount = new Account(in.get(0), in.get(1), Boolean.parseBoolean(out.get(0)));
                    account = in.get(0);
                    //no need to use Account field(its function will be replaced by server), only store id as server input for other services
                    // ^^ not sure abt this, need currentAccount to have a value - Manas
                }

                if (nextWindow != null) {
                    previousWindow = frame.getContentPane();
                    frame.setContentPane(nextWindow);
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(null, "Username or Password was incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == createAccountButton) {
                ArrayList<Account> accounts = manager.getAccountList();
                String name = usernameFieldSignup.getText();
                String password = passwordFieldSignup.getText();
                boolean isStudent = studentOrTeacherButton.getText().equals("Student");
                boolean valid = true;

                if (name.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (name.contains(";")) {
                    JOptionPane.showMessageDialog(null, "Usernames cannot contain semicolons", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (password.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a password", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (name.contains(";")) {
                    JOptionPane.showMessageDialog(null, "Passwords cannot contain semicolons", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                for (Account a : accounts) {
                    if (a.getUsername().equals(name)) {
                        JOptionPane.showMessageDialog(null, "Someone already has this username!", "Error", JOptionPane.ERROR_MESSAGE);
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    Account a = new Account(name, password, isStudent);
                    currentAccount = a;
                    manager.getAccountList().add(a);

                    // TODO FILE UPDATING
                    manager.updateAccount();

                    if (isStudent) {
                        previousWindow = frame.getContentPane();
                        frame.setContentPane(studentWindow);
                        refresh();
                    } else {
                        previousWindow = frame.getContentPane();
                        frame.setContentPane(teacherWindow);
                        refresh();
                    }
                }


            }
            if (e.getSource() == studentOrTeacherButton) {
                if (studentOrTeacherButton.getText().equals("Student")) {
                    studentOrTeacherButton.setText("Teacher");
                } else { // equals "Teacher"
                    studentOrTeacherButton.setText("Student");
                }
            }
            if (e.getSource() == accountSettingsStudentButton) {
                previousWindow = frame.getContentPane();
                frame.setContentPane(studentAccountWindow);
                refresh();
            }
            if (e.getSource() == accountSettingsTeacherButton) {
                previousWindow = frame.getContentPane();
                frame.setContentPane(teacherAccountWindow);
                refresh();
            }
            if (e.getSource() == exitStudentButton || e.getSource() == exitTeacherButton) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
            if (e.getSource() == viewCoursesStudentButton) {
                previousWindow = frame.getContentPane();
                frame.setContentPane(studentCoursesWindow);
                refresh();
            }
            if (e.getSource() == viewCoursesTeacherButton){
                previousWindow = frame.getContentPane();
                frame.setContentPane(teacherCoursesWindow);
                refresh();
            }
            if (e.getSource() == editUsernameStudentButton) {
                while (true) {
                    String newUsername = JOptionPane.showInputDialog(null, "Enter your new username:", "Edit Username", JOptionPane.QUESTION_MESSAGE);
                    if (newUsername == null) { // user closed the window
                        frame.setContentPane(previousWindow);
                        refresh();
                        break;
                    } else if (newUsername.equals("")) { // user entered a blank username
                        JOptionPane.showMessageDialog(null, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (newUsername.contains(";")) { // user's newUsername has a ";"
                        JOptionPane.showMessageDialog(null, "Username cannot include a semicolon", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (newUsername.equals(account)) {
                        JOptionPane.showMessageDialog(null, "Please enter a different username", "Error", JOptionPane.ERROR_MESSAGE);
                    } else { // their input was valid but we have to check for existing usernames
                        ArrayList<String> in = new ArrayList<>();
                        in.add(account);
                        in.add(newUsername);
                        ArrayList<String> out = connect(in, 4006);
                        if (out.get(0).equals("Duplicate new ID")) { //validation is done by the server, use if to chect the result only
                            JOptionPane.showMessageDialog(null, "Someone has taken this username!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (out.get(0).equals("Success")) {
                            JOptionPane.showMessageDialog(null, "Editing success", "Success", JOptionPane.INFORMATION_MESSAGE);
                            account = newUsername;
                            currentAccount.setUsername(account);
                        } else if (out.get(0).equals("Account not found")) {
                            JOptionPane.showMessageDialog(null, "There was a problem accessing this account's info", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }

                }

            }
            if (e.getSource() == editPasswordStudentButton) {
                while (true) {
                    String newPassword = JOptionPane.showInputDialog(null, "Enter your new password:", "Edit Username", JOptionPane.QUESTION_MESSAGE);
                    if (newPassword == null) { // user closed the window
                        frame.setContentPane(previousWindow);
                        refresh();
                        break;
                    } else if (newPassword.equals("")) { // user entered a blank password
                        JOptionPane.showMessageDialog(null, "Please enter a password", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (newPassword.contains(";")) { // user's newPassword has a ";"
                        JOptionPane.showMessageDialog(null, "Password cannot include a semicolon", "Error", JOptionPane.ERROR_MESSAGE);
                    } else { // their input was valid
                        ArrayList<String> in = new ArrayList<>();
                        in.add(account);
                        in.add(newPassword);
                        ArrayList<String> out = connect(in, 4007);
                        if (out.get(0).equals("Success")) {
                            JOptionPane.showMessageDialog(null, "Editing success", "Success", JOptionPane.INFORMATION_MESSAGE);
                            currentAccount.setPassword(newPassword);
                        } else if (out.get(0).equals("Account not found")) {
                            JOptionPane.showMessageDialog(null, "There was a problem accessing this account's info", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }

                }

            }
            if (e.getSource() == deleteAccountStudentButton) {
                ArrayList<String> in = new ArrayList<>();
                in.add(account);
                ArrayList<String> out = connect(in, 4008);
                if (out.get(0).equals("Success")) {
                    JOptionPane.showMessageDialog(null, "Deleting success", "Success", JOptionPane.INFORMATION_MESSAGE);
                    currentAccount=null;
                    frame.setContentPane(startWindow);
                    refresh();
                } else if (out.get(0).equals("Account not found")) {
                    JOptionPane.showMessageDialog(null, "There was a problem accessing this account's info", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == backAccountSettingsStudentButton) {
                frame.setContentPane(previousWindow);
                refresh();
            }

            if (e.getSource() == editUsernameTeacherButton) {
                while (true) {
                    String newUsername = JOptionPane.showInputDialog(null, "Enter your new username:", "Edit Username", JOptionPane.QUESTION_MESSAGE);
                    if (newUsername == null) { // user closed the window
                        frame.setContentPane(previousWindow);
                        refresh();
                        break;
                    } else if (newUsername.equals("")) { // user entered a blank username
                        JOptionPane.showMessageDialog(null, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (newUsername.contains(";")) { // user's newUsername has a ";"
                        JOptionPane.showMessageDialog(null, "Username cannot include a semicolon", "Error", JOptionPane.ERROR_MESSAGE);
                    } else { // their input was valid but we have to check for existing usernames
                        ArrayList<String> in = new ArrayList<>();
                        in.add(account);
                        in.add(newUsername);
                        ArrayList<String> out = connect(in, 4006);
                        if (out.get(0).equals("Duplicate new ID")) { //validation is done by the server, use if to chect the result only
                            JOptionPane.showMessageDialog(null, "Someone has taken this username!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (out.get(0).equals("Success")) {
                            JOptionPane.showMessageDialog(null, "Editing success", "Success", JOptionPane.INFORMATION_MESSAGE);
                            account = newUsername;
                            currentAccount.setUsername(account);
                        } else if (out.get(0).equals("Account not found")) {
                            JOptionPane.showMessageDialog(null, "There was a problem accessing this account's info", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }

                }

            }
            if (e.getSource() == editPasswordTeacherButton) {
                while (true) {
                    String newPassword = JOptionPane.showInputDialog(null, "Enter your new password:", "Edit Username", JOptionPane.QUESTION_MESSAGE);
                    if (newPassword == null) { // user closed the window
                        frame.setContentPane(previousWindow);
                        refresh();
                        break;
                    } else if (newPassword.equals("")) { // user entered a blank password
                        JOptionPane.showMessageDialog(null, "Please enter a password", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (newPassword.contains(";")) { // user's newPassword has a ";"
                        JOptionPane.showMessageDialog(null, "Password cannot include a semicolon", "Error", JOptionPane.ERROR_MESSAGE);
                    } else { // their input was valid
                        ArrayList<String> in = new ArrayList<>();
                        in.add(account);
                        in.add(newPassword);
                        ArrayList<String> out = connect(in, 4007);
                        if (out.get(0).equals("Success")) {
                            JOptionPane.showMessageDialog(null, "Editing success", "Success", JOptionPane.INFORMATION_MESSAGE);
                            currentAccount.setPassword(newPassword);
                        } else if (out.get(0).equals("Account not found")) {
                            JOptionPane.showMessageDialog(null, "There was a problem accessing this account's info", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }

                }

            }
            if (e.getSource() == deleteAccountTeacherButton) {
                ArrayList<String> in = new ArrayList<>();
                in.add(account);
                ArrayList<String> out = connect(in, 4008);
                if (out.get(0).equals("Success")) {
                    JOptionPane.showMessageDialog(null, "Deleting success", "Success", JOptionPane.INFORMATION_MESSAGE);
                    currentAccount=null;
                    frame.setContentPane(startWindow);
                    refresh();
                } else if (out.get(0).equals("Account not found")) {
                    JOptionPane.showMessageDialog(null, "There was a problem accessing this account's info", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == backAccountSettingsTeacherButton) {
                frame.setContentPane(previousWindow);
                refresh();
            }
            if (e.getSource() == backLoginWindowButton){
                frame.setContentPane(previousWindow);
                refresh();
            }
            if (e.getSource() == backSignUpWindowButton){
                frame.setContentPane(previousWindow);
                refresh();
            }
            if (e.getSource() == viewCourseStudentButton) {
                for (Course c : courses) {
                    if (c.getName().equals(courseListGUI.getSelectedItem())) {
                        currentCourse = c;
                    }
                }

                quizzes = currentCourse.getCourseQuiz();
                quizListGUI.removeAllItems();
                for (Quiz q : quizzes) {
                    quizListGUI.addItem(q.getName());
                }

                previousWindow = frame.getContentPane();
                frame.setContentPane(studentViewQuizzesWindow);
                refresh();
            }
            if (e.getSource() == viewCourseTeacherButton){
                for (Course c : courses) {
                    if (c.getName().equals(courseListGUITeacher.getSelectedItem())) {
                        currentCourse = c;
                    }
                }
                quizzes = currentCourse.getCourseQuiz();
                quizListGUITeacher.removeAllItems();
                for (Quiz q: quizzes){
                    quizListGUITeacher.addItem(q.getName());
                }
                previousWindow = frame.getContentPane();
                frame.setContentPane(teacherViewQuizzesWindow);
                refresh();
            }
            if (e.getSource() == createCourseButton){
                while (true) {
                    String newCourse = JOptionPane.showInputDialog(null, "Enter name of the Course:", "New Course", JOptionPane.QUESTION_MESSAGE);
                    if (newCourse == null) { // user closed the window
                        frame.setContentPane(teacherWindow);
                        refresh();
                        break;
                    } else if (newCourse.equals("")) { // user entered a blank password
                        JOptionPane.showMessageDialog(null, "Please enter a password", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (newCourse.contains(";")) { // user's newPassword has a ";"
                        JOptionPane.showMessageDialog(null, "Password cannot include a semicolon", "Error", JOptionPane.ERROR_MESSAGE);
                    } else { // their input was valid
                        ArrayList<String> in = new ArrayList<>();
                        in.add(newCourse);

                        ArrayList<String> out = connect(in, 4004);
                        if (out.get(0).equals("Success")) {
                            JOptionPane.showMessageDialog(null, "Creating course success", "Success", JOptionPane.INFORMATION_MESSAGE);

                        } else if (out.get(0).equals("Account not found")) {
                            JOptionPane.showMessageDialog(null, "There was a problem adding course ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }

                }
            }
            if (e.getSource() == viewQuizStudentButton) {
                for (Quiz q : quizzes) {
                    if (q.getName().equals(quizListGUI.getSelectedItem())) {
                        currentQuiz = q;
                    }
                }

                questions = currentQuiz.getQuestions();
                studentAnswers = new String[questions.length];
                answerDisplay.removeAllItems();

                for (int i = 0; i < studentAnswers.length; i++) {
                    studentAnswers[i] = "";
                }
                currentQuestion = questions[0];

                questionDisplay.setText(currentQuestion.getPrompt());
                for (String answer : currentQuestion.getAnswerChoices()) {
                    answerDisplay.addItem(answer);
                }

                previousWindow = frame.getContentPane();
                frame.setContentPane(studentQuizWindow);
                refresh();
            }

            if (e.getSource() == selectAnswerButton) {
                int index = -1;
                for (int i = 0; i < questions.length; i++) {
                    if (questions[i].getPrompt().equals(currentQuestion.getPrompt())) {
                        index = i;
                        break;
                    }
                }
                studentAnswers[index] = answerDisplay.getSelectedItem().toString();
            }
            if (e.getSource() == nextQuestionButton) {
                int index = -1;
                for (int i = 0; i < questions.length; i++) {
                    if (questions[i].getPrompt().equals(currentQuestion.getPrompt())) {
                        index = i;
                        break;
                    }
                }
                if (index == questions.length - 1) {
                    index = 0;
                } else {
                    index++;
                }
                currentQuestion = questions[index];
                answerDisplay.removeAllItems();
                questionDisplay.setText(currentQuestion.getPrompt());
                for (String answer : currentQuestion.getAnswerChoices()) {
                    answerDisplay.addItem(answer);
                }
            }
            if (e.getSource() == previousQuestionButton) {
                int index = -1;
                for (int i = 0; i < questions.length; i++) {
                    if (questions[i].getPrompt().equals(currentQuestion.getPrompt())) {
                        index = i;
                        break;
                    }
                }
                if (index == 0) {
                    index = questions.length - 1;
                } else {
                    index--;
                }
                currentQuestion = questions[index];
                answerDisplay.removeAllItems();
                questionDisplay.setText(currentQuestion.getPrompt());
                for (String answer : currentQuestion.getAnswerChoices()) {
                    answerDisplay.addItem(answer);
                }
            }
            if (e.getSource() == submitQuizButton) {
                boolean answered = true;
                for (int i = 0; i < studentAnswers.length; i++) {
                    if (studentAnswers[i].equals("")) {
                        JOptionPane.showMessageDialog(null, "Please answer all questions in the quiz!", "Error", JOptionPane.ERROR_MESSAGE);
                        answered = false;
                        break;
                    }
                }
                if (answered) {
                    // TODO
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
        viewCourseStudentButton = new JButton("View Course");
        viewCourseStudentButton.addActionListener(actionListener);
        viewQuizStudentButton = new JButton("View Quiz");
        viewQuizStudentButton.addActionListener(actionListener);
        previousQuestionButton = new JButton("Previous Question");
        previousQuestionButton.addActionListener(actionListener);
        nextQuestionButton = new JButton("Next Question");
        nextQuestionButton.addActionListener(actionListener);
        selectAnswerButton = new JButton("Select Answer");
        selectAnswerButton.addActionListener(actionListener);
        submitQuizButton = new JButton("Submit Quiz");
        submitQuizButton.addActionListener(actionListener);
        addQuizButton = new JButton("Create Quiz");
        addQuizButton.addActionListener(actionListener);
        editQuizButton = new JButton("Edit Quiz");
        editQuizButton.addActionListener(actionListener);
        ViewSubmissionsButton = new JButton("View Submissions");
        ViewSubmissionsButton.addActionListener(actionListener);

        courseListGUI = new JComboBox<String>();
        courseListGUI.addActionListener(actionListener);
        courseListGUITeacher = new JComboBox<>();
        courseListGUITeacher.addActionListener(actionListener);
        quizListGUI = new JComboBox<String>();
        quizListGUI.addActionListener(actionListener);
        quizListGUITeacher = new JComboBox<>();
        quizListGUITeacher.addActionListener(actionListener);
        quizListGUI.setEditable(true);
        questionDisplay = new JLabel();
        answerDisplay = new JComboBox<String>();
        answerDisplay.addActionListener(actionListener);
        answerDisplay.setEditable(true);

        viewCoursesTeacherButton = new JButton("View Courses");
        viewCoursesTeacherButton.addActionListener(actionListener);
        viewCourseTeacherButton = new JButton("View Info About the Course");
        viewCourseTeacherButton.addActionListener(actionListener);
        createCourseButton = new JButton("Create course");
        createCourseButton.addActionListener(actionListener);
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

        backLoginWindowButton = new JButton("Back");
        backLoginWindowButton.addActionListener(actionListener);
        backSignUpWindowButton = new JButton("Back");
        backSignUpWindowButton.addActionListener(actionListener);
        // Layout of the start window
        JPanel panel = new JPanel();
        panel.add(loginButton);
        panel.add(signupButton);
        startWindow.add(panel);

        // Layout of the login window
        loginWindow = new Container();
        loginWindow.setLayout(new BorderLayout());

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(2, 2));
        loginPanel.add(new JLabel("Username: "));
        loginPanel.add(usernameFieldLogin);
        loginPanel.add(new JLabel("Password: "));
        loginPanel.add(passwordFieldLogin);
        JPanel loginButtons = new JPanel();
        loginButtons.add(signInButton);
        loginButtons.add(backLoginWindowButton);
        loginWindow.add(loginPanel, BorderLayout.CENTER);
        loginWindow.add(loginButtons, BorderLayout.SOUTH);

        // Layout of the signup window
        signupWindow = new Container();
        signupWindow.setLayout(new BorderLayout());

        JPanel signupPanel = new JPanel();
        signupPanel.setLayout(new GridLayout(2, 2));
        signupPanel.add(new JLabel("Username: "));
        signupPanel.add(usernameFieldSignup);
        signupPanel.add(new JLabel("Password: "));
        signupPanel.add(passwordFieldSignup);
        signupWindow.add(signupPanel, BorderLayout.CENTER);
        JPanel sinupButtons = new JPanel();
        sinupButtons.add(createAccountButton);
        sinupButtons.add(backSignUpWindowButton);
        signupWindow.add(sinupButtons, BorderLayout.SOUTH);
        signupWindow.add(studentOrTeacherButton, BorderLayout.NORTH);

        // Layout of the student window
        studentWindow = new Container();
        studentWindow.setLayout(new BorderLayout());

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new GridLayout(3, 1));
        studentPanel.add(viewCoursesStudentButton);
        studentPanel.add(accountSettingsStudentButton);
        studentPanel.add(exitStudentButton);
        studentWindow.add(studentPanel, BorderLayout.CENTER);

        // Layout of the teacher window
        teacherWindow = new Container();
        teacherWindow.setLayout(new BorderLayout());

        JPanel teacherPanel = new JPanel();
        teacherPanel.setLayout(new GridLayout(3, 1));
        teacherPanel.add(viewCoursesTeacherButton);
        teacherPanel.add(createCourseButton);
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

        // Layout of the teacher account settings window
        teacherAccountWindow = new Container();
        teacherAccountWindow.setLayout(new BorderLayout());

        JPanel teacherAccountPanel = new JPanel();
        teacherAccountPanel.setLayout(new GridLayout(2, 2));
        teacherAccountPanel.add(editUsernameTeacherButton);
        teacherAccountPanel.add(editPasswordTeacherButton);
        teacherAccountPanel.add(deleteAccountTeacherButton);
        teacherAccountPanel.add(backAccountSettingsTeacherButton);
        teacherAccountWindow.add(teacherAccountPanel, BorderLayout.CENTER);

        // Layout of the student course selection window
        studentCoursesWindow = new Container();
        studentCoursesWindow.setLayout(new BorderLayout());

        courses = manager.getCourseList();
        String[] names = new String[courses.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = courses.get(i).getName();
        }
        courseListGUI = new JComboBox<String>(names);
        courseListGUI.setEditable(true);

        JPanel coursesPanel = new JPanel();
        coursesPanel.setLayout(new GridLayout(2, 1));
        coursesPanel.add(courseListGUI);
        coursesPanel.add(viewCourseStudentButton);
        studentCoursesWindow.add(coursesPanel, BorderLayout.CENTER);

        //Layout of the teacher course selection Window
        teacherCoursesWindow =  new Container();
        teacherCoursesWindow.setLayout(new BorderLayout());

        String[] namesTeacher = new String[courses.size()];
        for (int i =0; i<namesTeacher.length; i++){
            namesTeacher[i] = courses.get(i).getName();
        }
        courseListGUITeacher = new JComboBox<String>(namesTeacher);
        courseListGUITeacher.setEditable(true);

        JPanel coursesTeacherPanel = new JPanel();
        coursesTeacherPanel.setLayout(new GridLayout(2, 1));
        coursesTeacherPanel.add(courseListGUITeacher);
        coursesTeacherPanel.add(viewCourseTeacherButton);
        teacherCoursesWindow.add(coursesTeacherPanel, BorderLayout.CENTER);

        // Layout of the quizzes for a course window Student
        studentViewQuizzesWindow = new Container();
        studentViewQuizzesWindow.setLayout(new BorderLayout());

        JPanel quizzesPanel = new JPanel();
        quizzesPanel.setLayout(new GridLayout(2, 1));
        quizzesPanel.add(quizListGUI);
        quizzesPanel.add(viewQuizStudentButton);
        studentViewQuizzesWindow.add(quizzesPanel, BorderLayout.CENTER);

        //Layour of the quizzes for a course window Teacher
        teacherViewQuizzesWindow = new Container();
        teacherViewQuizzesWindow.setLayout(new BorderLayout());

        JPanel quizzPanelTeacher = new JPanel();
        quizzPanelTeacher.setLayout(new GridLayout(2, 1));
        quizzPanelTeacher.add(quizListGUITeacher);
        JPanel quizzPanelTeacherSouth = new JPanel();
        quizzPanelTeacherSouth.add(addQuizButton);
        quizzPanelTeacherSouth.add(editQuizButton);
        quizzPanelTeacherSouth.add(ViewSubmissionsButton);
        teacherViewQuizzesWindow.add(quizzPanelTeacher, BorderLayout.CENTER);
        teacherViewQuizzesWindow.add(quizzPanelTeacherSouth, BorderLayout.SOUTH);
        // Layout of the quiz taking window
        studentQuizWindow = new Container();
        studentQuizWindow.setLayout(new BorderLayout());

        JPanel quizOptionsPanel = new JPanel();
        quizOptionsPanel.setLayout(new FlowLayout());
        quizOptionsPanel.add(previousQuestionButton);
        quizOptionsPanel.add(selectAnswerButton);
        quizOptionsPanel.add(nextQuestionButton);
        quizOptionsPanel.add(submitQuizButton);
        studentQuizWindow.add(quizOptionsPanel, BorderLayout.SOUTH);
        studentQuizWindow.add(questionDisplay, BorderLayout.NORTH);
        studentQuizWindow.add(answerDisplay, BorderLayout.CENTER);


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
    private void refresh() {
        frame.invalidate();
        frame.validate();
        frame.pack();
    }

    /*
     * @Description input a port number and list of input for the server, receive a list of information received
     * @Date 8:48 PM 11/29/2021
     * @Param [input, port]
     * @return java.util.ArrayList<java.lang.String>
     **/
    public ArrayList<String> connect(ArrayList<String> input, int port) {

        ArrayList<String> result = new ArrayList<String>();
        try {
            Socket socket = new Socket("localhost", port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            for (int i = 0; i < input.size(); i++) {
                writer.println(input.get(i));
            }
            writer.flush();
            String s1 = reader.readLine();
            while (s1 != null) {
                result.add(s1);
                s1 = reader.readLine();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
