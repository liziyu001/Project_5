import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Represents a student's menu that allows them to view their grades, courses, and their settings
 *
 * @author Ram, William, Leo, Manas, Miras
 * @version December 13, 2021
 */
public class StudentMenu extends javax.swing.JFrame {
    //Button that allows the student to go to their settings
    private javax.swing.JButton settingButton;
    //Button that allows the student to view their courses
    private javax.swing.JButton viewCourseButton;
    //Button that allows the student to view their grades
    private javax.swing.JButton viewGradingButton;

    /**
     * Constructs a newly allocated StudentMenu object that calls the initComponents() method
     */
    public StudentMenu() {
        initComponents();
    }

    /**
     * Creates and initalizes all the buttons, and allows all the buttons to be displayed on the panels and accurately performed when clicked
     */
    private void initComponents() {
        //Creates the viewCourseButton
        viewCourseButton = new javax.swing.JButton();
        //Creates the viewGradingButton
        viewGradingButton = new javax.swing.JButton();
        //Creates the settingButton
        settingButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        viewCourseButton.setText("Take a quiz");
        //Allows the viewCourseButton to be performed when clicked
        viewCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCourseButtonActionPerformed(evt);
            }
        });

        viewGradingButton.setText("View Grading");
        //Allows the viewGradingButton to be performed when clicked
        viewGradingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewGradingButtonActionPerformed(evt);
            }
        });

        settingButton.setText("Account Setting");
        //Allows the settingButton to be performed when clicked
        settingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingButtonActionPerformed(evt);
            }
        });

        //Sets up panels and border layout, and adds the buttons to the panel
        this.setTitle("Student Menu");
        getContentPane().setLayout(new BorderLayout());
        this.setSize(600, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.add(viewCourseButton);
        panel.add(viewGradingButton);
        panel.add(settingButton);
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
    }

    /**
     * @return void
     * @Description action when viewCourse button is pressed, represents list of student's courses
     * @Param [evt] Allows the button's action to be performed
     */
    private void viewCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String[] options = new String[]{"1", "2", "3", "4"};
        ArrayList<String> courseList = Main.connect(new ArrayList<>(), 4002);
        String[] courses = new String[courseList.size()];
        for (int i = 0; i < courseList.size(); i++) {
            courses[i] = courseList.get(i);
        }
        if (courses.length == 0) {
            JOptionPane.showMessageDialog(null, "There are currently no courses!", "Fail", JOptionPane.ERROR_MESSAGE);
        } else {
            String choice = (String) JOptionPane.showInputDialog(null, "Select the course to proceed",
                    "Course selection", JOptionPane.QUESTION_MESSAGE, null, courses,
                    courses[0]);
            Main.setCurrentCourse(choice.split("\\. ")[1]);
            ArrayList<String> c = new ArrayList<String>();
            c.add(Main.getCurrentCourse());
            ArrayList<String> quizList = Main.connect(c, 4003);
            String[] quizs = new String[quizList.size()];
            for (int i = 0; i < quizList.size(); i++) {
                if (!quizList.get(i).isEmpty()) {
                    quizs[i] = quizList.get(i);
                }
            }
            choice = (String) JOptionPane.showInputDialog(null, "Select the quiz you want to take",
                    "Quiz selection", JOptionPane.QUESTION_MESSAGE, null, quizs,
                    quizs[0]);
            ArrayList<String> in = new ArrayList<String>();
            in.add(Main.getCurrentCourse());
            in.add(choice.split("\\. ")[1]);
            ArrayList<String> quizContent = Main.connect(in, 4010);
            in.add(Main.getCurrentAccount());
            int takeViaFile = JOptionPane.showConfirmDialog(null, "Do you want to take this quiz by file import?", "File import", JOptionPane.YES_NO_OPTION);
            if (takeViaFile == JOptionPane.YES_OPTION) {
                String path = JOptionPane.showInputDialog(null,
                        "Enter the path of your file",
                        "Path input", JOptionPane.QUESTION_MESSAGE);
                try {
                    File f = new File(path);
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String line = br.readLine();
                    while (line != null) {
                        in.add(line);
                        line = br.readLine();
                    }
                    br.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "There was a problem accessing this file!", "Fail", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            } else {
                String ans = "";
                for (int i = 0; i < ((quizContent.size() - 1) / 5); i++) {
                    String q = "";
                    for (int j = ((i + 1) * 5 - 4); j <= (i + 1) * 5; j++) {
                        q = q + "\n" + quizContent.get(j);
                    }
                    ans = ans + (String) JOptionPane.showInputDialog(null, "Enter the your answer to this question" + q,
                            "Taking quiz", JOptionPane.QUESTION_MESSAGE, null, options,
                            options[0]) + ",";
                }
                in.add(ans.substring(0, ans.length() - 1));
            }
            ArrayList<String> out = Main.connect(in, 4013);
            if (out.get(0).equals("Error")) {
                JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Successfully submitting your answer at " + out.get(0), "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    /**
     * @return void
     * @Description action when viewGrading button is pressed, represents list of student's grades
     * @Param [evt] Allows the button's action to be performed
     */
    private void viewGradingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(Main.getCurrentAccount());
        ArrayList<String> sub = Main.connect(temp, 4016);
        String[] submissions = new String[sub.size()];
        for (int i = 0; i < sub.size(); i++) {
            if (!sub.get(i).isEmpty()) {
                submissions[i] = sub.get(i);
            }
        }
        if (submissions.length == 0) {
            JOptionPane.showMessageDialog(null, "There are currently no graded submissions!", "Fail", JOptionPane.ERROR_MESSAGE);
        } else {
            String choice = (String) JOptionPane.showInputDialog(null, "Select the graded submission you want to view",
                    "Submission selection", JOptionPane.QUESTION_MESSAGE, null, submissions,
                    submissions[0]);
            ArrayList<String> contentRequest = new ArrayList<String>();
            contentRequest.add(choice.split("-")[0]);
            contentRequest.add(choice.split("-")[1]);
            ArrayList<String> quizContent = Main.connect(contentRequest, 4010);
            contentRequest.add(choice.split("-")[2]);
            ArrayList<String> answers = Main.connect(contentRequest, 4017);
            ArrayList<String> grades = Main.connect(contentRequest, 4014);
            for (int i = 0; i < answers.get(0).split(",").length; i++) {
                String q = "";
                for (int j = ((i + 1) * 5 - 4); j <= (i + 1) * 5; j++) {
                    q = q + "\n" + quizContent.get(j);
                }
                JOptionPane.showMessageDialog(null, "Question " + (i + 1) + "\n" + q + "\n\n"
                                + "Your answer: " + answers.get(0).split(",")[i] + "\n\n" + "Grade for this answer: "
                                + grades.get(0).split(",")[i],
                        "View Grading", JOptionPane.INFORMATION_MESSAGE);

            }
            JOptionPane.showMessageDialog(null, "Total Grade: " + grades.get(1),
                    "View Grading", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * @return void
     * @Description action when setting button is pressed, represents account settings
     * @Param [evt] Allows the button's action to be performed
     */
    private void settingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        AccountSetting as = new AccountSetting();
        as.setVisible(true);
    }

    /**
     * Main method that invokes the AWT and runs the StudentMenu object that displays the buttons and all its functionalities
     *
     * @param args Stores the command line arguments
     */
    public static void main(String args[]) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentMenu().setVisible(true);
            }
        });
    }

}
