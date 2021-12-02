import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Account {
    //The username of the person's account
    private String username;
    //The password of the person's account
    private String password;
    //Determines if the person is a student or not
    private boolean isStudent;
    //Represents the account id number of the person

    public Account(String username, String password, boolean isStudent) {
        this.username = username;
        this.password = password;
        this.isStudent = isStudent;
    }

    public Course createCourse(Scanner s) {
        /* GUI version 
        name = JOptionPane.showInputDialog(null, "Enter the name of the course", "Course Name", JOptionPane.QUESTION_MESSAGE);
        return new Course(name);
        */
        System.out.println("Enter the name of the course");
        return new Course(s.nextLine());
    }

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

    public void editQuiz(Scanner s, Quiz quiz) {
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

    public Submission gradeSubmission(Scanner s, Submission sub) {
        int[] subScore = new int[sub.getAnswers().length];
        for (int i = 0; i < subScore.length; i++) {
            System.out.println("Question: " + sub.getQuiz().getQuestions()[i].toString());
            System.out.println("The student's answer is " + sub.getAnswers()[i]);
            System.out.println("What grade do you wan to give");
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

    public Submission gradeSubmissionViaFile(Scanner s, Submission sub) {
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

    public Submission takeQuiz (Scanner s, Quiz q) {
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

    public Submission takeQuizViaFile (Scanner s, Quiz q) {
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

    public String getPassword() {
        return password;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
