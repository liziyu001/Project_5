import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    private ArrayList<Course> courseList;
    private ArrayList<Account> accountList;
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


    public ArrayList<Submission> getSubmissionList() {
        return submissionList;
    }

    public ArrayList<Account> getAccountList() {
        return accountList;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
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

    public synchronized void updateSubmission() {
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

    public synchronized void updateCourse() {
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
    public synchronized void updateAccount() {
        try {
            File f = new File("Account.txt");
            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pr = new PrintWriter(fos);
            for (int i = 0; i < accountList.size(); i++) {
                String info = accountList.get(i).getUsername() + ";"
                        + accountList.get(i).getPassword() + ";" +
                        accountList.get(i).isStudent();
                pr.println(info);
            }
            pr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
}
