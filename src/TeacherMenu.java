import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class TeacherMenu extends javax.swing.JFrame {

    private javax.swing.JButton createCourseButton;
    private javax.swing.JButton settingButton;
    private javax.swing.JButton viewCourseButton;
    private javax.swing.JButton gradingButton;

    public TeacherMenu() {
        initComponents();
    }

    private void initComponents() {

        viewCourseButton = new javax.swing.JButton();
        createCourseButton = new javax.swing.JButton();
        settingButton = new javax.swing.JButton();
        gradingButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        viewCourseButton.setText("View Courses");
        viewCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCourseButtonActionPerformed(evt);
            }
        });

        createCourseButton.setText("Create new Course");
        createCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCourseButtonActionPerformed(evt);
            }
        });

        settingButton.setText("Account Setting");
        settingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingButtonActionPerformed(evt);
            }
        });

        gradingButton.setText("Grade Submissions");
        gradingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradingButtonActionPerformed(evt);
            }
        });

//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addContainerGap(127, Short.MAX_VALUE)
//                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
//                                        .addComponent(viewCourseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
//                                        .addComponent(settingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                        .addComponent(createCourseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
//                                .addContainerGap(127, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addGap(51, 51, 51)
//                                .addComponent(viewCourseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
//                                .addGap(18, 18, 18)
//                                .addComponent(createCourseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(32, 32, 32)
//                                .addComponent(settingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(42, 42, 42))
//        );
//
//        pack();

        this.setTitle("Teacher menu");
        getContentPane().setLayout(new BorderLayout());
        this.setSize(600, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.add(viewCourseButton);
        panel.add(createCourseButton);
        panel.add(settingButton);
        panel.add(gradingButton);
        getContentPane().add(panel, BorderLayout.CENTER);
    }
    private void viewCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        ArrayList<String> courseList = Main.connect(new ArrayList<>(), 4002);
        String[] courses = new String[courseList.size()];
        for (int i = 0; i < courseList.size(); i++) {
            courses[i] = courseList.get(i);
        }
        String choice = (String) JOptionPane.showInputDialog(null, "Select the course you want to proceed",
                "Course selection", JOptionPane.QUESTION_MESSAGE, null, courses,
                courses[0]);
        Main.setCurrentCourse(choice.split("\\. ")[1]);
        CourseMenu cm = new CourseMenu();
        cm.setVisible(true);
    }

    private void createCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String name = JOptionPane.showInputDialog(null,
                "Enter the name of the course you want to create",
                "Create Course", JOptionPane.QUESTION_MESSAGE);
        ArrayList<String> in = new ArrayList<String>();
        in.add(name);
        ArrayList<String> out = Main.connect(in, 4004);
        if (out.get(0).equals("Success")) {
            JOptionPane.showMessageDialog(null, "Successfully creating the new course", "Fail", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Course with same name has already been created", "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void settingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        AccountSetting as = new AccountSetting();
        as.setVisible(true);
    }

    private void gradingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<String> sub = Main.connect(new ArrayList<String>(), 4012);
        String[] submissions = new String[sub.size()];
        for (int i = 0; i < sub.size() ; i++) {
            if (!sub.get(i).isEmpty()) {
                submissions[i] = sub.get(i);
            }
        }
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
        } else {
            JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String args[]) {


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TeacherMenu().setVisible(true);
            }
        });
    }




}
