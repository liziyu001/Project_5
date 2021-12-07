import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class StudentMenu extends javax.swing.JFrame {

    private javax.swing.JButton settingButton;
    private javax.swing.JButton viewCourseButton;
    private javax.swing.JButton viewGradingButton;
    public StudentMenu() {
        initComponents();
    }


    private void initComponents() {

        viewCourseButton = new javax.swing.JButton();
        viewGradingButton = new javax.swing.JButton();
        settingButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        viewCourseButton.setText("Take a quiz");
        viewCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCourseButtonActionPerformed(evt);
            }
        });

        viewGradingButton.setText("View Grading");
        viewGradingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewGradingButtonActionPerformed(evt);
            }
        });

        settingButton.setText("Account Setting");
        settingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingButtonActionPerformed(evt);
            }
        });

//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addGap(110, 110, 110)
//                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                                        .addComponent(settingButton, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
//                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                                                .addComponent(viewGradingButton, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
//                                                .addComponent(viewCourseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
//                                .addContainerGap(119, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addGap(49, 49, 49)
//                                .addComponent(viewCourseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(18, 18, 18)
//                                .addComponent(viewGradingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(18, 18, 18)
//                                .addComponent(settingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addContainerGap(39, Short.MAX_VALUE))
//        );
//
//        pack();

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
    }

    private void viewCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<String> courseList = Main.connect(new ArrayList<>(), 4002);
        String[] courses = new String[courseList.size()];
        for (int i = 0; i < courseList.size(); i++) {
            courses[i] = courseList.get(i);
        }
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
        choice = (String) JOptionPane.showInputDialog(null, "Select the course you want to take",
                "Course selection", JOptionPane.QUESTION_MESSAGE, null, quizs,
                quizs[0]);
        ArrayList<String> in = new ArrayList<String>();
        in.add(Main.getCurrentCourse());
        in.add(choice.split("\\. ")[1]);
        ArrayList<String> quizContent = Main.connect(in, 4010);
        in.add(Main.getCurrentAccount());
        int takeViaFile = JOptionPane.showConfirmDialog(null, "Do you want to create by file import?", "File import", JOptionPane.YES_NO_OPTION);
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
                e.printStackTrace();
            }
        } else {
            String ans = "";
            for (int i = 0; i < ((quizContent.size() - 1) / 5); i++) {
                String q = "";
                for (int j = ((i + 1) * 5 - 4); j <= (i + 1) * 5; j++) {
                    q = q + "\n" + quizContent.get(j);
                }
                ans = ans + JOptionPane.showInputDialog(null,
                        "Enter the your answer to this question" + q,
                        "Taking quiz", JOptionPane.QUESTION_MESSAGE) + ",";

            }
            in.add(ans.substring(0, ans.length() - 1));
        }
        ArrayList<String> out = Main.connect(in, 4013);
        if (out.get(0).equals("Success")) {
            Main.setCurrentAccount(in.get(1));
            JOptionPane.showMessageDialog(null, "Successfully submitting your answer", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewGradingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void settingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        AccountSetting as = new AccountSetting();
        as.setVisible(true);
    }

    public static void main(String args[]) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentMenu().setVisible(true);
            }
        });
    }

}
