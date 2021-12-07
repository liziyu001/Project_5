import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class CourseMenu extends javax.swing.JFrame {

    private javax.swing.JButton createButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton backButton;
    public CourseMenu() {
        initComponents();
    }

    private void initComponents() {

        createButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        createButton.setText("Create a Quiz");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit a Quiz");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete a Quiz");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addGap(114, 114, 114)
//                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                                        .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
//                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                                                .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                                .addComponent(createButton, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
//                                .addContainerGap(132, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addGap(50, 50, 50)
//                                .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(47, 47, 47)
//                                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(31, 31, 31)
//                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addContainerGap(37, Short.MAX_VALUE))
//        );
//
//        pack();

        this.setTitle("Course menu");
        getContentPane().setLayout(new BorderLayout());
        this.setSize(600, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.add(createButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(backButton);
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to create by file import?", "File import",JOptionPane.YES_NO_OPTION);
        ArrayList<String> in = new ArrayList<String>();
        if (choice == JOptionPane.YES_OPTION) {
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
            in.add(Main.getCurrentCourse());
            in.add(JOptionPane.showInputDialog(null,
                    "Enter the name of the quiz",
                    "Create Quiz", JOptionPane.QUESTION_MESSAGE));
            String n = JOptionPane.showInputDialog(null,
                    "How many Question do you want to include",
                    "Create Quiz", JOptionPane.QUESTION_MESSAGE);
            in.add(n);
            for (int i = 0; i < Integer.parseInt(n); i++) { //Error handling required
                in.add(JOptionPane.showInputDialog(null,
                        "Enter the prompt for question" + (i + 1),
                        "Create Quiz", JOptionPane.QUESTION_MESSAGE));
                in.add(JOptionPane.showInputDialog(null,
                        "Enter the first choice for question" + (i + 1),
                        "Create Quiz", JOptionPane.QUESTION_MESSAGE));
                in.add(JOptionPane.showInputDialog(null,
                        "Enter the second choice for question" + (i + 1),
                        "Create Quiz", JOptionPane.QUESTION_MESSAGE));
                in.add(JOptionPane.showInputDialog(null,
                        "Enter the third choice for question" + (i + 1),
                        "Create Quiz", JOptionPane.QUESTION_MESSAGE));
                in.add(JOptionPane.showInputDialog(null,
                        "Enter the fourth choice for question" + (i + 1),
                        "Create Quiz", JOptionPane.QUESTION_MESSAGE));
            }
        }
        ArrayList<String> out = Main.connect(in, 4005);
        if (out.get(0).equals("Success")) {
            System.out.println(out.get(0));
            JOptionPane.showMessageDialog(null, "Successfully creating the quiz", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println(out.get(0));
            JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<String> c = new ArrayList<String>();
        c.add(Main.getCurrentCourse());
        ArrayList<String> quizList = Main.connect(c, 4003);
        String[] quizs = new String[quizList.size()];
        for (int i = 0; i < quizList.size() ; i++) {
            if (!quizList.get(i).isEmpty()) {
                quizs[i] = quizList.get(i);
            }
        }
        String choice = (String) JOptionPane.showInputDialog(null, "Select the course you want to proceed",
                "Course selection", JOptionPane.QUESTION_MESSAGE, null, quizs,
                quizs[0]);
        ArrayList<String> in = new ArrayList<String>();
        in.add(Main.getCurrentCourse());
        in.add(choice.split("\\. ")[1]);
        String n = JOptionPane.showInputDialog(null,
                "How many Question do you want to include",
                "Create Quiz", JOptionPane.QUESTION_MESSAGE);
        in.add(n);
        for (int i = 0; i < Integer.parseInt(n); i++) { //Error handling required
            in.add(JOptionPane.showInputDialog(null,
                    "Enter the prompt for question" + (i + 1),
                    "Create Quiz", JOptionPane.QUESTION_MESSAGE));
            in.add(JOptionPane.showInputDialog(null,
                    "Enter the first choice for question" + (i + 1),
                    "Create Quiz", JOptionPane.QUESTION_MESSAGE));
            in.add(JOptionPane.showInputDialog(null,
                    "Enter the second choice for question" + (i + 1),
                    "Create Quiz", JOptionPane.QUESTION_MESSAGE));
            in.add(JOptionPane.showInputDialog(null,
                    "Enter the third choice for question" + (i + 1),
                    "Create Quiz", JOptionPane.QUESTION_MESSAGE));
            in.add(JOptionPane.showInputDialog(null,
                    "Enter the fourth choice for question" + (i + 1),
                    "Create Quiz", JOptionPane.QUESTION_MESSAGE));
        }
        ArrayList<String> out = Main.connect(in, 4009);
        if (out.get(0).equals("Success")) {
            JOptionPane.showMessageDialog(null, "Successfully Editing the quiz", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<String> c = new ArrayList<String>();
        c.add(Main.getCurrentCourse());
        ArrayList<String> quizList = Main.connect(c, 4003);
        String[] quizs = new String[quizList.size()];
        for (int i = 0; i < quizList.size(); i++) {
            quizs[i] = quizList.get(i);
        }
        String choice = (String) JOptionPane.showInputDialog(null, "Select the course you want to proceed",
                "Course selection", JOptionPane.QUESTION_MESSAGE, null, quizs,
                quizs[0]);
        ArrayList<String> in = new ArrayList<String>();
        in.add(Main.getCurrentCourse());
        in.add(choice.split("\\. ")[1]);
        ArrayList<String> out = Main.connect(in, 4011);
        if (out.get(0).equals("Success")) {
            JOptionPane.showMessageDialog(null, "Successfully deleting the quiz", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        new TeacherMenu().setVisible(true);
    }

    public static void main(String args[]) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CourseMenu().setVisible(true);
            }
        });
    }




}
