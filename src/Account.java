
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/* 
 * Represents the Account of a Teacher or a Student that gives access to certain options based on what type of account the person has 
 */
public class Account {
    //The username of the person's account
    private String username;
    //The password of the person's account
    private String password;
    //Determines if the person is a student or not
    private boolean isStudent;
    //Represents the account id number of the person
    
    /*
     * Constructs a newly allocated object Account object with the specified username, password, and student status
     * @param username The username to be used in construction
     * @param password The password to be used in construction
     * @param isStudent The student status to be used in construction
     */
    public Account(String username, String password, boolean isStudent) {
        this.username = username;
        this.password = password;
        this.isStudent = isStudent;
    }
    
    /*
     * Creates a Course object based on the name of the course
     * @return Returns a Course object with the specified name of the course
     */
    public Course createCourse(Scanner s) {
        /* GUI version 
        name = JOptionPane.showInputDialog(null, "Enter the name of the course", "Course Name", JOptionPane.QUESTION_MESSAGE);
        return new Course(name);
        */
        System.out.println("Enter the name of the course");
        return new Course(s.nextLine());
    }
    
    /* 
     * Creates a quiz with a specified amount of questions with four answer choices in each question
     * @return Returns a Quiz object that represents a quiz with a certain name that has a certain amount of questions
     */
    public Quiz createQuiz(Scanner s) {
        /* GUI version 
        JOptionPane.showInputDialog(null, "Enter the Name of the Quiz", "Quiz Name", JOptionPane.QUESTION_MESSAGE);
        int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of questions in the quiz", "Questions", JOptionPane.QUESTION_MESSAGE));
        Question[] q = new Question[number];
        for (int i = 0; i < number; i++) {
            String prompt = JOptionPane.showInputDialog(null, "Enter the prompt of the Question", "Question Prompt", JOptionPane.QUESTION_MESSAGE);
            String[] options = new String[4];
            options[0] = JOptionPane.showInputDialog(null, "Enter the First Option prompt", "First Prompt", JOptionPane.QUESTION_MESSAGE);
            options[1] = JOptionPane.showInputDialog(null, "Enter the Second Option prompt", "Second Prompt", JOptionPane.QUESTION_MESSAGE);
            options[2] = JOptionPane.showInputDialog(null, "Enter the Third Option prompt", "Third Prompt", JOptionPane.QUESTION_MESSAGE);
            options[3] = JOptionPane.showInputDialog(null, "Enter the Fourth Option prompt", "Fourth Prompt", JOptionPane.QUESTION_MESSAGE);
            q[i] = new Question(prompt, options);
            JOptionPane.showMessageDialog(null, "Question " + (i + 1) + " has been created", "Question Created", JOptionPane.INFORMATION_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, "Created Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        return new Quiz(name, q);
        */
        System.out.println("Enter the Name of the Quiz");
        String name = s.nextLine();
        System.out.println("Enter the number of questions in the quiz");
        int number = Integer.parseInt(s.nextLine());
        Question[] q = new Question[number];
        for (int i = 0; i < number; i++) {
            System.out.println("Enter the prompt of the Question");
            String prompt = s.nextLine();
            String[] options = new String[4];
            System.out.println("Enter the First Option prompt");
            options[0] = s.nextLine();
            System.out.println("Enter the Second Option prompt");
            options[1] = s.nextLine();
            System.out.println("Enter the Third Option prompt");
            options[2] = s.nextLine();
            System.out.println("Enter the Fourth Option prompt");
            options[3] = s.nextLine();
            q[i] = new Question(prompt, options);
            System.out.println("Question " + (i + 1) + " has been created");
        }
        System.out.println("Created Successfully");
        
        return new Quiz(name, q);
    }
    
    /* 
     * Modifies and changes the name and questions of the specified quiz identified by setting a new name and list of questions
     * @param quiz The specified quiz that is being modified and changed
     */
    public void editQuiz(Scanner s, Quiz quiz) {
        /* GUI version
        String name = JOptionPane.showInputDialog(null, "Enter the new Name of the Quiz", "New Quiz Name", JOptionPane.QUESTION_MESSAGE);
        quiz.setName(name);
        int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of questions in the quiz", "Number of Quiz Questions", JOptionPane.QUESTION_MESSAGE));
        Question[] q = new Question[number];
        for (int i = 0; i < number; i++) {
            String prompt = JOptionPane.showInputDialog(null, "Enter the prompt of the Question", "Prompt", JOptionPane.QUESTION_MESSAGE);
            String[] options = new String[3];
            options[0] = JOptionPane.showInputDialog(null, "Enter the First Option prompt", "First Option Prompt", JOptionPane.QUESTION_MESSAGE);
            options[1] = JOptionPane.showInputDialog(null, "Enter the Second Option prompt", "Second Option Prompt", JOptionPane.QUESTION_MESSAGE);
            options[2] = JOptionPane.showInputDialog(null, "Enter the Third Option prompt", "Third Option Prompt", JOptionPane.QUESTION_MESSAGE);
            q[i] = new Question(prompt, options);
            JOptionPane.showMessageDialog(null, "Question" + i + i + "has been created", "Question Created", JOptionPane.INFORMATION_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, "Edited Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        quiz.setQuestions(q);
        */
        System.out.println("Enter the new Name of the Quiz");
        String name = s.nextLine();
        quiz.setName(name);
        System.out.println("Enter the number of questions in the quiz");
        int number = Integer.parseInt(s.nextLine());
        Question[] q = new Question[number];
        for (int i = 0; i < number; i++) {
            System.out.println("Enter the prompt of the Question");
            String prompt = s.nextLine();
            String[] options = new String[3];
            System.out.println("Enter the First Option prompt");
            options[0] = s.nextLine();
            System.out.println("Enter the Second Option prompt");
            options[1] = s.nextLine();
            System.out.println("Enter the Third Option prompt");
            options[2] = s.nextLine();
            q[i] = new Question(prompt, options);
            System.out.println("Question" + i + i + "has been created");
        }
        System.out.println("Edited Successfully");
        quiz.setQuestions(q);
    }
    
    /*
     * Grades the student's submission for each individual question, calculates the total score for the student, and returns that student's submission
     * @param sub The student's submission that is being graded
     * @return The Submission object that represents the student's submission that was being graded
     */
    public Submission gradeSubmission(Scanner s, Submission sub) {
        /* GUI version
        int[] subScore = new int[sub.getAnswers().length];
        for (int i = 0; i < subScore.length; i++) {
            JOptionPane.showMessageDialog(null, "Question: " + sub.getQuiz().getQuestions()[i].toString(), "Question", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "The student's answer is " + sub.getAnswers()[i], "Student's Answer", JOptionPane.INFORMATION_MESSAGE);
            subScore[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "What grade do you want to give?", "Grade", JOptionPane.QUESTION_MESSAGE);
            
        }
        JOptionPane.showMessageDialog(null, "Grading finished", "Grading Done", JOptionPane.INFORMATION_MESSAGE);
        sub.setSubGrades(subScore);
        sub.setGraded(true);
        int total = 0;
        for (int j : subScore) {
            total += j;
        }
        sub.setTotalGrades(total);
        return sub;
        */
        int[] subScore = new int[sub.getAnswers().length];
        for (int i = 0; i < subScore.length; i++) {
            System.out.println("Question: " + sub.getQuiz().getQuestions()[i].toString());
            System.out.println("The student's answer is " + sub.getAnswers()[i]);
            System.out.println("What grade do you want to give");
            subScore[i] = Integer.parseInt(s.nextLine());
        }
        System.out.println("Grading finished");
        sub.setSubGrades(subScore);
        sub.setGraded(true);
        int total = 0;
        for (int j : subScore) {
            total += j;
        }
        sub.setTotalGrades(total);
        return sub;
    }
    
    /* 
     * Grades a student's submission based on the file path indicated
     * A score is given per line (per question) and a total score is calulcated with the submission being returned after it is graded
     * @param sub The student's submission that is being graded
     * @return The Submission object that represents the student's submission that was being graded, if it cannot return the submission then it returns null
     */
    public Submission gradeSubmissionViaFile(Scanner s, Submission sub) {
        /* GUI version
        for (int i = 0; i < sub.getAnswers().length; i++) {
            JOptionPane.showMessageDialog(null, "Question: " + sub.getQuiz().getQuestions()[i].toString(), "Question", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "The student's answer is " + sub.getAnswers()[i], "Student's Answer", JOptionPane.INFORMATION_MESSAGE);
        }
        String file = JOptionPane.showMessageDialog(null, "Enter the file path, please include a score per line", "File Path", JOptionPane.QUESTION_MESSAGE);
        try {
            File f = new File(file);
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            ArrayList<String> info = new ArrayList<String>();
            String line = bfr.readLine();
            while (line != null) {
                info.add(line);
                line = bfr.readLine();
            }
            bfr.close();
            int[] subScore = new int[info.size()];
            for (int i = 0; i < subScore.length; i++) {
                subScore[i] = Integer.parseInt(info.get(i));
            }
            sub.setSubGrades(subScore);
            sub.setGraded(true);
            int total = 0;
            for (int i = 0; i < subScore.length; i++) {
                i = i + subScore[i];
            }
            sub.setTotalGrades(total);
            return sub;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        */
        for (int i = 0; i < sub.getAnswers().length; i++) {
            System.out.println("Question: " + sub.getQuiz().getQuestions()[i].toString());
            System.out.println("The student's answer is " + sub.getAnswers()[i]);
        }
        System.out.println("Enter the file path, please include a score per line");
        try {
            File f = new File(s.nextLine());
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            ArrayList<String> info = new ArrayList<String>();
            String line = bfr.readLine();
            while (line != null) {
                info.add(line);
                line = bfr.readLine();
            }
            bfr.close();
            int[] subScore = new int[info.size()];
            for (int i = 0; i < subScore.length; i++) {
                subScore[i] = Integer.parseInt(info.get(i));
            }
            sub.setSubGrades(subScore);
            sub.setGraded(true);
            int total = 0;
            for (int i = 0; i < subScore.length; i++) {
                i = i + subScore[i];
            }
            sub.setTotalGrades(total);
            return sub;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /* 
     * Student answers the questions on the quiz and submits it
     * @param q The quiz that the student is taking
     * @return Returns the Submission object that represents the student's quiz submission
     */
    public Submission takeQuiz(Scanner s, Quiz q) {
        /* GUI version
        JOptionPane.showMessageDialog(null, "You are now taking Quiz " + q.getName(), "Take Quiz", JOptionPane.INFORMATION_MESSAGE);
        int [] answers = new int[q.getQuestions().length];
        for (int i = 0; i < answers.length; i++) {
            q.getQuestions()[i].displayQuestion();
            answers[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your choice", "Answer", JOptionPane.QUESTION_MESSAGE));
        }
        JOptionPane.showMessageDialog(null, "Finished taking the quiz", "Quiz Finished", JOptionPane.INFORMATION_MESSAGE);
        return new Submission(this, answers, q);
        */
        System.out.println("You are now taking Quiz " + q.getName());
        int[] answers = new int[q.getQuestions().length];
        for (int i = 0; i < answers.length; i++) {
            q.getQuestions()[i].displayQuestion();
            System.out.println("Enter your choice");
            answers[i] = Integer.parseInt(s.nextLine());
        }
        System.out.println("Finished taking the quiz");
        return new Submission(this, answers, q);
    }
    
    /* 
     * Student takes the quiz based on the file path they enter
     * The student enters an answer on each line and submits their answers when done
     * @param q The quiz that the student is taking
     * @return Returns the Submission object that represents the student's submission if the submission was a success, otherwise it returns null
     */
    public Submission takeQuizViaFile(Scanner s, Quiz q) {
        /* GUI version
        int[] answers = new int[q.getQuestions().length];
        for (int i = 0; i < answers.length; i++) {
            JOptionPane.showMessageDialog(null, q.getQuestions()[i].toString(), "Questions", JOptionPane.INFORMATION_MESSAGE);
        }
        String file = JOptionPane.showInputDialog(null, "Enter the file path, please include an answer per line", "File Path", JOptionPane.QUESTION_MESSAGE);
        try {
            File f = new File(file);
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            ArrayList<String> info = new ArrayList<String>();
            String line = bfr.readLine();
            while (line != null) {
                info.add(line);
                line = bfr.readLine();
            }
            bfr.close();
            for (int i = 0; i < answers.length; i++) {
                answers[i] = Integer.parseInt(info.get(i));
            }
            return new Submission(this, answers, q);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        */
        int[] answers = new int[q.getQuestions().length];
        for (int i = 0; i < answers.length; i++) {
            System.out.println(q.getQuestions()[i].toString());
        }
        System.out.println("Enter the file path, please include an answer per line");
        try {
            File f = new File(s.nextLine());
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            ArrayList<String> info = new ArrayList<String>();
            String line = bfr.readLine();
            while (line != null) {
                info.add(line);
                line = bfr.readLine();
            }
            bfr.close();
            for (int i = 0; i < answers.length; i++) {
                answers[i] = Integer.parseInt(info.get(i));
            }
            return new Submission(this, answers, q);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /* 
     * Returns the specified password
     * @return Returns the specified password
     */ 
    public String getPassword() {
        return password;
    }
    
    /* 
     * Returns the specified student status
     * @return Returns the specified student status
     */ 
    public boolean isStudent() {
        return isStudent;
    }
    
    /* 
     * Returns the specified username
     * @return Returns the specified username
     */ 
    public String getUsername() {
        return username;
    }
    
    /* 
     * Updates the password of this account with the specified password
     * @param password The password to be used in the update
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /* 
     * Updates the username of this account with the specified username
     * @param username The username to be used in the update
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
