import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    //Represents the list of courses the teacher creates and the student takes
    private ArrayList<Course> courseList;
    //Represents the list of accounts created
    private ArrayList<Account> accountList;
    //Represents the list of submissions that the student made
    private ArrayList<Submission> submissionList;

    /*
     * @Description Initialize Manager by reading from Account and Course File
     * @Date 4:36 PM 11/18/2021
     * @Param []
     * @return
     **/
    public Manager() {
        courseList = readCourses();
        accountList = readAccounts();
        submissionList = readSubmission();
    }
    
    /** 
     * Returns the list of courses that are in the file
     * @return Returns the list of courses that are in the file
     */
    public ArrayList<Course> getCourseList() {
        return courseList;
    }
    
    /** 
     * Returns the list of accounts that are in the file
     * @return Returns the list of accounts that are in the file
     */
    public ArrayList<Account> getAccountList() {
        return accountList;
    }
    
    /**
     * Updates the list of accounts with the specified list of accounts, and updates the accounts in general
     * @param accountList The account list that is used in the update
     */
    public void setAccountList(ArrayList<Account> accountList) {
        this.accountList = accountList;
        updateAccount();
    }
    
    /** 
     * Returns the list of submissions that are in the file
     * @return Returns the list of submissions that are in the file
     */
    public ArrayList<Submission> getSubmissionList() {
        return submissionList;
    }
    
    /**
     * Updates the list of courses with the specified list of courses, and updates the courses in general
     * @param courseList The course list that is used in the update
     */
    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
        updateCourse();
    }

    /*
     * @Description ask information; return corresponding account; return null if duplicate id
     * @Date 4:20 PM 11/18/2021
     * @Param [s]
     * @return Account
     **/
    public Account createAccount(Scanner s) {
        /* GUI version
        String id = JOptionPane.showInputDialog(null, "Enter your User ID", "User ID", JOptionPane.QUESTION_MESSAGE);
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUsername.equals(id)) {
                JOptionPane.showMessageDialog(null, "This id has already been taken, please try again", "ID already taken", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        String pwd = JOptionPane.showInputDialog(null, "Enter your Password", "Password", JOptionPane.QUESTION_MESSAGE);
        String role = JOptionPane.showInputDialog(null, "Your role: 1. Teacher   2. Student", "Role", JOptionPane.INFORMATION_MESSAGE);
        switch(role)) {
            case "1":
                return new Account(id, pwd, false);
            case "2":
                return new Account(id, pwd, true);
            default:
                //error handling can be added
                return null; 
        }
        */
        System.out.println("Enter your User ID"); // ; shouldn't be contained
        String id = s.nextLine();

        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUsername().equals(id)) {
                System.out.println("This id has already been taken, please try again");
                return null;
            }

        }
        System.out.println("Enter your Password");
        String pwd = s.nextLine();
        System.out.println("Your role: 1. Teacher   2. Student");
        switch (s.nextLine()) {
            case "1":
                return new Account(id, pwd, false);
            case "2":
                return new Account(id, pwd, true);
            default:
                //error handling can be added
                return null;
        }

    }

    /*
     * @Description ask information; return corresponding account; return null if not found
     * @Date 4:19 PM 11/18/2021
     * @Param [s]
     * @return Account
     **/
    public Account login(Scanner s) { // ; shouldn't be contained
        /* GUI version
        String id = JOptionPane.showInputDialog(null, "Enter your User ID", "User ID", JOptionPane.QUESTION_MESSAGE);
        String pwd = JOptionPane.showInputDialog(null, "Enter your Password", "Password", JOptionPane.QUESTION_MESSAGE);
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUsername().equals(id)) {
                if (accountList.get(i).getPassword().equals(pwd)) {
                    return accountList.get(i);
                } else {
                    JOptionPane.showMessageDialog(null, "Password not correct", "Invalid Password", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Your id hasn't been created", "Couldn't Create ID", JOptionPane.ERROR_MESSAGE);
        return null;
        */
        System.out.println("Enter you id");
        String id = s.nextLine();
        System.out.println("Enter you password");
        String pwd = s.nextLine();
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUsername().equals(id)) {
                if (accountList.get(i).getPassword().equals(pwd)) {
                    return accountList.get(i);
                } else {
                    System.out.println("Password not correct");
                    return null;
                }
            }
        }
        System.out.println("Your id hasn't been created");
        return null;
    }

    /*
     * @Description read from Account.txt and return a list of existed accounts
     * @Date 4:42 PM 11/18/2021
     * @Param []
     * @return java.util.ArrayList<Account>
     **/
    public ArrayList<Account> readAccounts() {

        ArrayList<Account> accounts = new ArrayList<>();
        try {
            File file = new File("Account.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                String[] accountInfo = line.split(";");
                accounts.add(new Account(accountInfo[0], accountInfo[1], Boolean.parseBoolean(accountInfo[2])));
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    /*
     * @Description read from Course.txt and related course file and return a list of existed courses
     * @Date 5:04 PM 11/18/2021
     * @Param []
     * @return java.util.ArrayList<Course>
     **/
    public ArrayList<Course> readCourses() {

        ArrayList<String> coursePaths = new ArrayList<String>();
        ArrayList<Course> courses = new ArrayList<Course>();
        try {
            File file = new File("Courses.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                coursePaths.add(line);
                line = br.readLine();
            }
            br.close();
            for (int i = 0; i < coursePaths.size(); i++) {
                String courseName = coursePaths.get(i).split("\\.")[0];
                ArrayList<Quiz> courseQuiz = new ArrayList<Quiz>();
                file = new File(coursePaths.get(i));
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                line = br.readLine();
                while (line != null) {
                    String quizName = line.split(":")[0];
                    Question[] quizQuestion = new Question[Integer.parseInt(line.split(":")[1])];
                    for (int j = 0; j < quizQuestion.length; j++) {
                        String qPrompt = br.readLine();
                        String[] choice = new String[4];
                        choice[0] = br.readLine();
                        choice[1] = br.readLine();
                        choice[2] = br.readLine();
                        choice[3] = br.readLine();
                        quizQuestion[j] = new Question(qPrompt, choice);
                    }
                    courseQuiz.add(new Quiz(quizName, quizQuestion));
                    line = br.readLine();
                }
                courses.add(new Course(courseName, courseQuiz));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }

    /*
     * @Description Update the current AccountList to files
     * @Date 3:25 PM 11/20/2021
     * @Param []
     * @return void
     **/
    public void updateAccount() {
        /* GUI version
        try {
            File f = new File("Account.txt");
            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pr = new PrintWriter(fos);
            for (int i = 0; i < accountList.size(); i++) {
                String info = accountList.get(i).getUsername() + ";"
                        + accountList.get(i).getPassword() + ";" +
                        accountList.get(i).isStudent();
                pr.println(info);
                JOptionPane.showMessageDialog(null, "updated", "Account Updated", JOptionPane.INFORMATION_MESSAGE);
            }
            pr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        try {
            File f = new File("Account.txt");
            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pr = new PrintWriter(fos);
            for (int i = 0; i < accountList.size(); i++) {
                String info = accountList.get(i).getUsername() + ";"
                        + accountList.get(i).getPassword() + ";" +
                        accountList.get(i).isStudent();
                pr.println(info);
                System.out.println("updated");
            }
            pr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * @Description Update the current courseList to files
     * @Date 3:25 PM 11/20/2021
     * @Param []
     * @return void
     **/
    public void updateCourse() {
        /* GUI version
         try {
            File f = new File("Courses.txt");
            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pr = new PrintWriter(fos);
            for (int i = 0; i < courseList.size(); i++) { //for every course
                String path = courseList.get(i).getName() + ".txt";
                pr.println(path); //update the Course.txt
                File f1 = new File(path);
                FileOutputStream fos1 = new FileOutputStream(f1);
                PrintWriter pr1 = new PrintWriter(fos1);
                for (int j = 0; j < courseList.get(i).getCourseQuiz().size(); j++) { // for every course quiz
                    Quiz q = courseList.get(i).getCourseQuiz().get(j);
                    if (q != null) {
                        pr1.println(q.getName() + ":" + q.getQuestions().length);
                        for (int k = 0; k < q.getQuestions().length; k++) { // for every quiz question
                            Question question = q.getQuestions()[k];
                            pr1.println(question.getPrompt());
                            for (int l = 0; l < 4; l++) { // for each option
                                pr1.println(question.getAnswerChoices()[l]);
                            }
                        }
                    }
                }
                pr1.close();
            }
            pr.close();
            JOptionPane.showMessageDialog(null, "\n", "", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        try {
            File f = new File("Courses.txt");
            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pr = new PrintWriter(fos);
            for (int i = 0; i < courseList.size(); i++) { //for every course
                String path = courseList.get(i).getName() + ".txt";
                pr.println(path); //update the Course.txt
                File f1 = new File(path);
                FileOutputStream fos1 = new FileOutputStream(f1);
                PrintWriter pr1 = new PrintWriter(fos1);
                for (int j = 0; j < courseList.get(i).getCourseQuiz().size(); j++) { // for every course quiz
                    Quiz q = courseList.get(i).getCourseQuiz().get(j);
                    if (q != null) {
                        pr1.println(q.getName() + ":" + q.getQuestions().length);
                        for (int k = 0; k < q.getQuestions().length; k++) { // for every quiz question
                            Question question = q.getQuestions()[k];
                            pr1.println(question.getPrompt());
                            for (int l = 0; l < 4; l++) { // for each option
                                pr1.println(question.getAnswerChoices()[l]);
                            }
                        }
                    }
                }
                pr1.close();
            }
            pr.close();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * @Description return a list of existed course for user to choose
     * @Date 4:23 PM 11/20/2021
     * @Param []
     * @return java.lang.String
     **/
    public String listCourses() {
        String s = "";
        for (int i = 0; i < courseList.size(); i++) {
            s = s + (i + 1) + ". " + courseList.get(i).getName() + "\n";
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }

    public Account editID(Scanner s, Account ac, int index) {
        /* GUI version
        String id = JOptionPane.showInputDialog(null, "Enter your User ID", "User ID", JOptionPane.QUESTION_MESSAGE);
        for (int i = 0; i < accountList.size(); i++) {
            if (i != index && accountList.get(i).getUsername().equals(id)) {
                JOptionPane.showMessageDialog(null, "This id has already been taken, please try again", "ID Already Taken", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        ac.setUsername(id);
        return ac;
        */
        System.out.println("Enter your User ID"); // ; shouldn't be contained
        String id = s.nextLine();
        for (int i = 0; i < accountList.size(); i++) {
            if (i != index && accountList.get(i).getUsername().equals(id)) {
                System.out.println("This id has already been taken, please try again");
                return null;
            }
        }
        ac.setUsername(id);
        return ac;
    }

    public Account editPwd(Scanner s, Account ac) {
        /* GUI version
        String pwd = JOptionPane.showInputDialog(null, "Enter your new Password", "Password", JOptionPane.QUESTION_MESSAGE);
        ac.setPassword(pwd);
        return ac;
        */
        System.out.println("Enter your new Password"); // ; shouldn't be contained
        String pwd = s.nextLine();
        ac.setPassword(pwd);
        return ac;
    }

    public ArrayList<Submission> readSubmission() {
        ArrayList<Submission> list = new ArrayList<Submission>();
        ArrayList<String> info = new ArrayList<String>();
        try {
            File f = new File("Submissions.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                info.add(line);
                line = br.readLine();
            }
            br.close();
            for (int i = 0; i < info.size(); i++) {
                String[] current = info.get(i).split(";");
                String id = current[2];
                String courseName = current[0];
                String quizName = current[1];
                boolean graded = Boolean.parseBoolean(current[3]);
                int[] answers = new int[current[4].split(",").length];
                int[] subGrades = new int[answers.length];
                for (int j = 0; j < answers.length; j++) {
                    answers[j] = Integer.parseInt(current[4].split(",")[j]);
                    subGrades[j] = Integer.parseInt(current[5].split(",")[j]);
                }
                list.add(new Submission(courseName, quizName, id, graded, answers, subGrades));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateSubmission() {
        try {
            File f = new File("Submissions.txt");
            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pr = new PrintWriter(fos);
            for (int i = 0; i < submissionList.size(); i++) {
                Submission sub = submissionList.get(i);
                String line = "";
                line = line + sub.getCourseName() + ";"
                        + sub.getQuizName() + ";"
                        + sub.getId() + ";"
                        + sub.isGraded() + ";";
                for (int j = 0; j < sub.getAnswers().length; j++) {
                    line = line + sub.getAnswers()[j] + ",";
                }
                line = line.substring(0,line.length() - 1) + ";";
                for (int j = 0; j < sub.getSubGrades().length; j++) {
                    line = line + sub.getSubGrades()[j] + ",";
                }
                line = line.substring(0,line.length() - 1);
                pr.println(line);
            }
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
