import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

//import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 * Represents the course menu that displays all the quiz options
 *
 * @author Ram, William, Leo, Manas, Miras
 * @version December 13, 2021
 */
public class CourseMenu extends javax.swing.JFrame {
    //Button that allows teacher to create a quiz
    private javax.swing.JButton createButton;
    //Button that allows teacher to delete a quiz
    private javax.swing.JButton deleteButton;
    //Button that allows teacher to edit a quiz
    private javax.swing.JButton editButton;
    //Button that allows teacher to go back
    private javax.swing.JButton backButton;

    /**
     * Constructs a newly allocated CourseMenu object that calls the initComponents() method
     */
    public CourseMenu() {
        initComponents();
    }

    /**
     * Creates and initializes the four buttons with the set texts and the action listeners
     * That helps perform the functionality of each button when clicked
     */
    private void initComponents() {
        //Creates the create Button
        createButton = new javax.swing.JButton();
        //Creates the edit Button
        editButton = new javax.swing.JButton();
        //Creates the delete Button
        deleteButton = new javax.swing.JButton();
        //Creates the back Button
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        createButton.setText("Create a Quiz");
        //Allows the createButton to be performed when clicked
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit a Quiz");
        //Allows the editButton to be performed when clicked
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete a Quiz");
        //Allows the deleteButton to be performed when clicked
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        //Allows the backButton to be performed when clicked
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        //Sets up panels and border layout, and adds the buttons to the panel
        this.setTitle("Course menu");
        getContentPane().setLayout(new BorderLayout());
        this.setSize(600, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.add(createButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(backButton);
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
    }

    /**
     * @return void
     * @Description action when create button is pressed, represents creating a quiz
     * @Param [evt] Allows the button's action to be performed
     */
    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        boolean end = false;
        try {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to create by file import?", "File import", JOptionPane.YES_NO_OPTION);
            ArrayList<String> in = new ArrayList<String>();
            in.add(Main.getCurrentCourse());
            if (choice == JOptionPane.YES_OPTION) {
                String path = JOptionPane.showInputDialog(null,
                        "Enter the path of your file(.txt)",
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
                    JOptionPane.showMessageDialog(null, "Successfully created the quiz", "Success", JOptionPane.INFORMATION_MESSAGE);
                    ArrayList<String> out = Main.connect(in, 4005);
                    int c = JOptionPane.showConfirmDialog(null,
                            "Do you want to randomize the order of questions and the order of potential options?",
                            "Randomize", JOptionPane.YES_NO_OPTION);
                    if (c == JOptionPane.YES_OPTION) {
                        ArrayList<String> temp = new ArrayList<String>();
                        temp.add(Main.getCurrentCourse());
                        temp.add(in.get(1));
                        ArrayList<String> a = Main.connect(temp, 4018);
                        if (a.get(0).equals("Fail")) {
                            JOptionPane.showMessageDialog(null, "Randomize Error", "Fail", JOptionPane.ERROR_MESSAGE);
                            this.setVisible(true);
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "There was a problem creating this quiz!", "Fail", JOptionPane.ERROR_MESSAGE);
                    this.setVisible(true);
                }
            } else {
                in.add(JOptionPane.showInputDialog(null,
                        "Enter the name of the quiz",
                        "Create Quiz", JOptionPane.QUESTION_MESSAGE));
                String n = JOptionPane.showInputDialog(null,
                        "How many Questions do you want to include?",
                        "Create Quiz", JOptionPane.QUESTION_MESSAGE);
                in.add(n);
                int numQuestions;
                try {
                    numQuestions = Integer.parseInt(n);
                    if (numQuestions <= 0) {
                        throw new Exception();
                    }
                    for (int i = 0; i < numQuestions; i++) { //Error handling required
                        String temp = JOptionPane.showInputDialog(null,
                                "Enter the prompt for question" + (i + 1),
                                "Create Quiz", JOptionPane.QUESTION_MESSAGE);
                        if (temp == null) {
                            end = true;
                            break;
                        }
                        in.add(temp);
                        temp = JOptionPane.showInputDialog(null,
                                "Enter the first choice for question" + (i + 1),
                                "Create Quiz", JOptionPane.QUESTION_MESSAGE);
                        if (temp == null) {
                            end = true;
                            break;
                        }
                        in.add(temp);
                        temp = JOptionPane.showInputDialog(null,
                                "Enter the second choice for question" + (i + 1),
                                "Create Quiz", JOptionPane.QUESTION_MESSAGE);
                        if (temp == null) {
                            end = true;
                            break;
                        }
                        in.add(temp);
                        temp = JOptionPane.showInputDialog(null,
                                "Enter the third choice for question" + (i + 1),
                                "Create Quiz", JOptionPane.QUESTION_MESSAGE);
                        if (temp == null) {
                            end = true;
                            break;
                        }
                        in.add(temp);
                        temp = JOptionPane.showInputDialog(null,
                                "Enter the fourth choice for question" + (i + 1),
                                "Create Quiz", JOptionPane.QUESTION_MESSAGE);
                        if (temp == null) {
                            end = true;
                            break;
                        }
                        in.add(temp);
                    }
                    if (end) {
                        this.setVisible(true);
                        return;
                    }
                    ArrayList<String> out = Main.connect(in, 4005);
                    int c = JOptionPane.showConfirmDialog(null,
                            "Do you want to randomize the order of questions and the order of potential options?",
                            "Randomize", JOptionPane.YES_NO_OPTION);
                    if (c == JOptionPane.YES_OPTION) {
                        ArrayList<String> temp = new ArrayList<String>();
                        temp.add(Main.getCurrentCourse());
                        temp.add(in.get(1));
                        ArrayList<String> a = Main.connect(temp, 4018);
                        if (a.get(0).equals("Fail")) {
                            JOptionPane.showMessageDialog(null, "Randomize Error", "Fail", JOptionPane.ERROR_MESSAGE);
                            this.setVisible(true);
                        }
                    }
                    if (out.get(0).equals("Success")) {
                        JOptionPane.showMessageDialog(null, "Successfully creating the quiz", "Success", JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
                        this.setVisible(true);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Enter a valid input", "Fail", JOptionPane.ERROR_MESSAGE);
                    this.setVisible(true);
                }

            }
        } catch (Exception e) {
            this.setVisible(true);
        }


    }

    /**
     * @return void
     * @Description action when edit button is pressed, represents editing a quiz
     * @Param [evt] Allows the button's action to be performed
     */
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        try {
            ArrayList<String> c = new ArrayList<String>();
            c.add(Main.getCurrentCourse());
            ArrayList<String> quizList = Main.connect(c, 4003);
            String[] quizs = new String[quizList.size()];
            for (int i = 0; i < quizList.size(); i++) {
                if (!quizList.get(i).isEmpty()) {
                    quizs[i] = quizList.get(i);
                }
            }

            String choice = (String) JOptionPane.showInputDialog(null, "Select the quiz you want to proceed",
                    "Quiz selection", JOptionPane.QUESTION_MESSAGE, null, quizs,
                    quizs[0]);
            ArrayList<String> in = new ArrayList<String>();
            in.add(Main.getCurrentCourse());
            in.add(choice.split("\\. ")[1]);
            //System.out.println(choice);
//        String n = JOptionPane.showInputDialog(null,
//                "How many Question do you want to include",
//                "Create Quiz", JOptionPane.QUESTION_MESSAGE);
//        in.add(n);
            boolean end = false;
            while (!end) {
                String numbOfQuestions = JOptionPane.showInputDialog(null, "Enter new amount of questions", "Edit Quiz", JOptionPane.QUESTION_MESSAGE);
                if (numbOfQuestions == null) {
                    this.setVisible(true);
                    break;
                } else {
                    try {
                        in.add(numbOfQuestions);
                        int numberOfQuestions = Integer.parseInt(numbOfQuestions);
                        if (numberOfQuestions <= 0) {
                            throw new Exception();
                        }
                        for (int i = 0; i < numberOfQuestions; i++) {
                            String questionPrompt = JOptionPane.showInputDialog(null, "Enter new question Prompt:", "Edit Quiz", JOptionPane.QUESTION_MESSAGE);
                            if (questionPrompt == null) {
                                this.setVisible(true);
                                end = true;
                                break;
                            } else if (questionPrompt.contains(";")) {
                                JOptionPane.showMessageDialog(null, "Value cannot include a semicolon", "Error", JOptionPane.ERROR_MESSAGE);
                                this.setVisible(true);
                                end = true;
                                break;
                            } else if (questionPrompt.equals("")) {
                                JOptionPane.showMessageDialog(null, "Value cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                                this.setVisible(true);
                                end = true;
                                break;
                            } else {
                                in.add(questionPrompt);
                                for (int j = 0; j < 4; j++) {
                                    String answerChoice = (String) JOptionPane.showInputDialog(null, "Enter " + (j + 1) + " choice of the Question:", "Edit Questions", JOptionPane.QUESTION_MESSAGE);
                                    if (answerChoice == null) {
                                        this.setVisible(true);
                                        end = true;
                                        break;
                                    } else if (answerChoice.contains(";")) {
                                        JOptionPane.showMessageDialog(null, "Value cannot include a semicolon", "Error", JOptionPane.ERROR_MESSAGE);
                                        this.setVisible(true);
                                        end = true;
                                        break;
                                    } else if (answerChoice.equals("")) {
                                        JOptionPane.showMessageDialog(null, "Value cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                                        this.setVisible(true);
                                        end = true;
                                        break;
                                    } else {
                                        in.add(answerChoice);
                                    }
                                }
                            }
                        }
                        if (end) {
                            break;
                        }
                        ArrayList<String> out = Main.connect(in, 4009);
                        if (out.get(0).equals("Success")) {
                            JOptionPane.showMessageDialog(null, "Successfully edited the quiz", "Success", JOptionPane.INFORMATION_MESSAGE);
                            this.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
                            this.setVisible(true);
                        }
                        break;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid input", "Error", JOptionPane.ERROR_MESSAGE);
                        this.setVisible(true);
                        break;
                    }
                }
            }
//        for (int i = 0; i < Integer.parseInt(n); i++) { //Error handling required
//            in.add(JOptionPane.showInputDialog(null,
//                    "Enter the prompt for question" + (i + 1),
//                    "Create Quiz", JOptionPane.QUESTION_MESSAGE));
//            in.add(JOptionPane.showInputDialog(null,
//                    "Enter the first choice for question" + (i + 1),
//                    "Create Quiz", JOptionPane.QUESTION_MESSAGE));
//            in.add(JOptionPane.showInputDialog(null,
//                    "Enter the second choice for question" + (i + 1),
//                    "Create Quiz", JOptionPane.QUESTION_MESSAGE));
//            in.add(JOptionPane.showInputDialog(null,
//                    "Enter the third choice for question" + (i + 1),
//                    "Create Quiz", JOptionPane.QUESTION_MESSAGE));
//            in.add(JOptionPane.showInputDialog(null,
//                    "Enter the fourth choice for question" + (i + 1),
//                    "Create Quiz", JOptionPane.QUESTION_MESSAGE));
//        }
//        ArrayList<String> out = Main.connect(in, 4009);
//        if (out.get(0).equals("Success")) {
//            JOptionPane.showMessageDialog(null, "Successfully Editing the quiz", "Success", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
//        }
        } catch (Exception e) {
            this.setVisible(true);
        }

    }

    /**
     * @return void
     * @Description action when delete button is pressed, represents deleting a quiz
     * @Param [evt] Allows the button's action to be performed
     */
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        try {
            ArrayList<String> c = new ArrayList<String>();
            c.add(Main.getCurrentCourse());
            ArrayList<String> quizList = Main.connect(c, 4003);
            String[] quizs = new String[quizList.size()];
            for (int i = 0; i < quizList.size(); i++) {
                quizs[i] = quizList.get(i);
            }
            String choice = (String) JOptionPane.showInputDialog(null, "Select the quiz you want to delete",
                    "Course selection", JOptionPane.QUESTION_MESSAGE, null, quizs,
                    quizs[0]);
            ArrayList<String> in = new ArrayList<String>();
            in.add(Main.getCurrentCourse());
            in.add(choice.split("\\. ")[1]);
            ArrayList<String> out = Main.connect(in, 4011);
            if (out.get(0).equals("Success")) {
                JOptionPane.showMessageDialog(null, "Successfully deleting the quiz", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Error", "Fail", JOptionPane.ERROR_MESSAGE);
                this.setVisible(true);
            }
        } catch (Exception e) {
            this.setVisible(true);
        }

    }

    /**
     * @return void
     * @Description action when back button is pressed
     * @Param [evt] Allows the button's action to be performed
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        new TeacherMenu().setVisible(true);
    }

    /**
     * Main method that invokes run using EDT and runs the CourseMenu object that displays the buttons and all its functionalities
     *
     * @param args Stores the command line arguments
     */
    public static void main(String args[]) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CourseMenu().setVisible(true);
            }
        });
    }


}
