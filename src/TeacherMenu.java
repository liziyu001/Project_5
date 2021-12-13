import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a teacher's menu with the options of creating and viewing a course, grading a quiz, and accessing their settings
 *
 * @author Ram, William, Leo, Manas, Miras
 * @version December 13, 2021
 */
public class TeacherMenu extends javax.swing.JFrame {
    //Button that allows a teacher to create a course
    private javax.swing.JButton createCourseButton;
    //Button that allows a teacher to access their settings
    private javax.swing.JButton settingButton;
    //Button that allows a teacher to view their courses
    private javax.swing.JButton viewCourseButton;
    //Button that allows a teacher to grade the student's quizzes
    private javax.swing.JButton gradingButton;

    /**
     * Constructs a newly allocated TeacherMenu object that calls the initComponents() method
     */
    public TeacherMenu() {
        initComponents();
    }

    /**
     * Creates and initalizes all the buttons, and allows all the buttons to be displayed on the panels and accurately performed when clicked
     */
    private void initComponents() {
        //Creates viewCourse Button
        viewCourseButton = new javax.swing.JButton();
        //Creates createCourse Button
        createCourseButton = new javax.swing.JButton();
        //Creates setting Button
        settingButton = new javax.swing.JButton();
        //Creates grading Button
        gradingButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        viewCourseButton.setText("View Courses");
        //Allows the viewCourseButton to be performed when clicked
        viewCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCourseButtonActionPerformed(evt);
            }
        });

        createCourseButton.setText("Create new Course");
        //Allows the createCourseButton to be performed when clicked
        createCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCourseButtonActionPerformed(evt);
            }
        });

        settingButton.setText("Account Setting");
        //Allows the settingButton to be performed when clicked
        settingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingButtonActionPerformed(evt);
            }
        });

        gradingButton.setText("Grade Submissions");
        //Allows the gradingButton to be performed when clicked
        gradingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradingButtonActionPerformed(evt);
            }
        });

        //Sets up panels and border layout, and adds the buttons to the panel
        this.setTitle("Teacher menu");
        getContentPane().setLayout(new BorderLayout());
        this.setSize(600, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.add(viewCourseButton);
        panel.add(createCourseButton);
        panel.add(settingButton);
        panel.add(gradingButton);
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();

    }

    /**
     * @return void
     * @Description action when viewCourse button is pressed, represents list of teacher's courses
     * @Param [evt] Allows the button's action to be performed
     */
    private void viewCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        try {
            ArrayList<String> courseList = Main.connect(new ArrayList<>(), 4002);
            String[] courses = new String[courseList.size()];
            for (int i = 0; i < courseList.size(); i++) {
                courses[i] = courseList.get(i);
            }
            if (courses.length == 0) {
                JOptionPane.showMessageDialog(null, "No courses currently exist!", "Fail", JOptionPane.ERROR_MESSAGE);
                this.setVisible(true);
            } else {
                String choice = (String) JOptionPane.showInputDialog(null, "Select the course you want to proceed",
                        "Course selection", JOptionPane.QUESTION_MESSAGE, null, courses,
                        courses[0]);

                Main.setCurrentCourse(choice.split("\\. ")[1]);
                CourseMenu cm = new CourseMenu();
                cm.setVisible(true);
            }
        } catch (Exception e) {
            this.setVisible(true);
        }
    }

    /**
     * @return void
     * @Description action when createCourse button is pressed, represents creating a course
     * @Param [evt] Allows the button's action to be performed
     */
    private void createCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        try {
            String name = JOptionPane.showInputDialog(null,
                    "Enter the name of the course you want to create",
                    "Create Course", JOptionPane.QUESTION_MESSAGE);
            ArrayList<String> in = new ArrayList<String>();
            in.add(name);
            if (in.get(0).isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid input", "Error", JOptionPane.ERROR_MESSAGE);
                this.setVisible(true);
            } else {
                ArrayList<String> out = Main.connect(in, 4004);
                if (out.get(0).equals("Success")) {
                    JOptionPane.showMessageDialog(null, "Successfully creating the new course", "Success", JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Course with same name has already been created", "Fail", JOptionPane.ERROR_MESSAGE);
                    this.setVisible(true);
                }
            }
        } catch (Exception e) {
            this.setVisible(true);
        }


    }

    /**
     * @return void
     * @Description action when settingCourse button is pressed, represents access to settings
     * @Param [evt] Allows the button's action to be performed
     */
    private void settingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        AccountSetting as = new AccountSetting();
        as.setVisible(true);
    }

    /**
     * @return void
     * @Description action when grading button is pressed, represents grading of quizzes
     * @Param [evt] Allows the button's action to be performed
     */
    private void gradingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        try {
            ArrayList<String> sub = Main.connect(new ArrayList<String>(), 4012);
            String[] submissions = new String[sub.size()];
            for (int i = 0; i < sub.size(); i++) {
                if (!sub.get(i).isEmpty()) {
                    submissions[i] = sub.get(i);
                }
            }
            if (submissions.length == 0) {
                JOptionPane.showMessageDialog(null, "There are currently no submissions!", "Fail", JOptionPane.ERROR_MESSAGE);
                this.setVisible(true);
            } else {
                String choice = (String) JOptionPane.showInputDialog(null, "Select the submission you want to grade",
                        "Submission selection", JOptionPane.QUESTION_MESSAGE, null, submissions,
                        submissions[0]);
                ArrayList<String> contentRequest = new ArrayList<String>();
                contentRequest.add(choice.split("-")[0]);
                contentRequest.add(choice.split("-")[1]);
                ArrayList<String> quizContent = Main.connect(contentRequest, 4010);
                contentRequest.add(choice.split("-")[2]);
                ArrayList<String> answers = Main.connect(contentRequest, 4017);
                ArrayList<String> in = contentRequest;
                String grades = "";
                for (int i = 0; i < ((quizContent.size() - 1) / 5); i++) {
                    String q = "";
                    for (int j = ((i + 1) * 5 - 4); j <= (i + 1) * 5; j++) {
                        q = q + "\n" + quizContent.get(j);
                    }
                    grades = grades + JOptionPane.showInputDialog(null,
                            "Question: " + (i + 1) + "\n" + q + "\n\n" + "The student's answer is:\n" + answers.get(0).split(",")[i] + "\n\n"
                                    + "Enter the score you want to give",
                            "Grade submission", JOptionPane.QUESTION_MESSAGE) + ",";

                }
                in.add(grades.substring(0, grades.length() - 1));
                ArrayList<String> out = Main.connect(in, 4015);
                if (out.get(0).equals("Success")) {
                    Main.setCurrentAccount(in.get(1));
                    JOptionPane.showMessageDialog(null, "Successfully grading this submission", "Success", JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
                    this.setVisible(true);
                }
            }
        } catch (Exception e) {
            this.setVisible(true);
        }


    }

    /**
     * Main method that invokes run using EDT and runs the TeacherMenu object that displays the buttons and all its functionalities
     *
     * @param args Stores the command line arguments
     */
    public static void main(String args[]) {


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TeacherMenu().setVisible(true);
            }
        });
    }


}
