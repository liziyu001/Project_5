import java.util.Scanner;


public class ManagementSystem {
    static Manager m = new Manager();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Account currentAccount;
        System.out.println("Welcome to the system");
        loginOption:
        while (true) {
            System.out.println("1. create account");
            System.out.println("2. login");
            switch (s.nextLine()) {
                case "1":
                    Account a = m.createAccount(s);
                    if (a == null) {
                        continue loginOption;
                    }
                    m.getAccountList().add(a);
                    m.updateAccount();
                    currentAccount = a;
                    System.out.println("Create Account Successfully");
                    break;
                case "2":
                    Account ac = m.login(s);
                    if (ac == null) {
                        continue loginOption;
                    }
                    currentAccount = ac;
                    System.out.println("Successfully login as " + ac.getUsername());
                    break;
                default :
                    System.out.println("Invalid Option");
                    continue loginOption;
            }
            break;
        }

        if (!currentAccount.isStudent()) {
            Teacher:
            while (true) {
                System.out.println("1. View Courses");
                System.out.println("2. Create a Course");
                System.out.println("3. Account Setting");
                System.out.println("0. Exit");
                switch (s.nextLine()) {
                    case "1":
                        Course currentCourse;
                        Quiz currentQuiz;
                        CourseSelection:
                        while (true) {
                            System.out.println("Select the course you want to view");
                            System.out.println(m.listCourses());
                            currentCourse = m.getCourseList().get(Integer.parseInt(s.nextLine()) - 1);
                            if (currentCourse == null) {
                                System.out.println("Invalid Choice");
                                continue CourseSelection;
                            }
                            break;
                        }
                        if (currentCourse.listQuiz().isEmpty()) {
                            System.out.println("There is no quiz in this Course");
                            continue Teacher;
                        } else {
                            QuizSelection:
                            while (true) {
                                System.out.println("Select the Quiz you want to view");
                                System.out.println(currentCourse.listQuiz());
                                System.out.println("0. Create a new Quiz");
                                String temp = s.nextLine();
                                if (temp.equals("0")){
                                    Quiz q = currentAccount.createQuiz(s);
                                    if (q == null) {
                                        continue Teacher;
                                    } else {
                                        currentCourse.getCourseQuiz().add(q);
                                        m.updateCourse();//update the file
                                        System.out.println("Create Quiz Successfully");
                                        continue Teacher;
                                    }
                                }else {
                                    currentQuiz = currentCourse.getCourseQuiz().get(Integer.parseInt(temp) - 1);
                                    if (currentQuiz == null) {
                                        System.out.println("Invalid Choice");
                                        continue QuizSelection;
                                    }
                                    QuizOption:
                                    while (true) {
                                        System.out.println("1. Edit the Quiz");
                                        System.out.println("0. Back");
                                        switch (s.nextLine()) {
                                            case "1":
                                                Quiz q = currentAccount.createQuiz(s);
                                                if (q == null) {
                                                    continue Teacher;
                                                } else {
                                                    int index = currentCourse.getCourseQuiz().indexOf(currentQuiz);
                                                    currentCourse.getCourseQuiz().set(index, q);
                                                    m.updateCourse();//update the file
                                                    System.out.println("Create Edited Successfully");
                                                    continue Teacher;
                                                }
                                            case "0" :
                                                continue Teacher;
                                            case "a"://just to make the break statement reachable
                                                break;
                                            default :
                                                System.out.println("Invalid Option");
                                                continue QuizSelection;
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    case "2":
                        Course c = currentAccount.createCourse(s);
                        boolean available = true;
                        for (int i = 0; i < m.getCourseList().size(); i++) {  //Check availability
                            if (c.getName().equals(m.getCourseList().get(i).getName())) {
                                available = false;
                            }
                        }
                        if (available) {
                            m.getCourseList().add(c);
                            m.updateCourse();
                            System.out.println("Successfully created course " + c.getName());
                            continue Teacher;
                        } else {
                            System.out.println("You can 't have two course with the same name");
                            continue Teacher;
                        }
                    case "3":
                        AccountOptions:
                        while (true) {
                            int i = m.getAccountList().indexOf(currentAccount);
                            Account a;
                            System.out.println("1. Edit your username");
                            System.out.println("2. Edit your password");
                            System.out.println("3. Delete your account");
                            System.out.println("0. Back");
                            switch (s.nextLine()) {
                                case "1":
                                    a = m.editID(s, currentAccount, i);
                                    if (a == null) {
                                        continue AccountOptions;
                                    } else {
                                        currentAccount = a; //update the copy
                                        m.getAccountList().set(i, a);//update the manager
                                        m.updateAccount();//update the file
                                        System.out.println("Edit success");
                                        continue Teacher;
                                    }
                                case "2" :
                                    a = m.editPwd(s, currentAccount);
                                    currentAccount = a;
                                    m.getAccountList().set(i, a);
                                    m.updateAccount();
                                    System.out.println("Edit success");
                                    continue Teacher;
                                case "3":
                                    m.getAccountList().remove(i);
                                    System.out.println("Delete Successfully");
                                    m.updateAccount();
                                    break;
                                case "0":
                                    continue Teacher;
                            }
                            break;
                        }
                    case "0" :
                        break Teacher;
                }
                break;
            }
        } else {
            Student:
            while (true) {
                System.out.println("1. View Courses");
                System.out.println("2. Account Setting");
                System.out.println("0. Exit");
                switch (s.nextLine()) {
                    case "1" :
                        Course currentCourse;
                        Quiz currentQuiz;
                        CourseSelection:
                        while (true) {
                            System.out.println("Select the course you want to view");
                            System.out.println(m.listCourses());
                            try {
                                currentCourse = m.getCourseList().get(Integer.parseInt(s.nextLine()) - 1);
                            }catch (Exception e) {
                                System.out.println("Invalid Choice");
                                continue CourseSelection;
                            }
                            if (currentCourse.listQuiz().isEmpty()) {
                                System.out.println("There is no quiz in this Course");
                                continue CourseSelection;
                            }
                            break;
                        }
                        QuizSelection:
                        while (true) {
                            System.out.println("Select the Quiz you want to take");
                            System.out.println(currentCourse.listQuiz());
                            currentQuiz = currentCourse.getCourseQuiz().get(Integer.parseInt(s.nextLine()) - 1);
                            if (currentQuiz == null) {
                                System.out.println("Invalid Choice");
                                continue QuizSelection;
                            }
                            Submission sub = currentAccount.takeQuiz(s, currentQuiz);
                            m.getSubmissionList().add(sub);
                            System.out.println("Submission Recorded");
                            continue Student;
                        }
                    case "2" :
                        AccountOptions:
                        while (true) {
                            int i = m.getAccountList().indexOf(currentAccount);
                            Account a;
                            System.out.println("1. Edit your username");
                            System.out.println("2. Edit your password");
                            System.out.println("3. Delete your account");
                            System.out.println("0. Back");
                            switch (s.nextLine()) {
                                case "1":
                                    a = m.editID(s, currentAccount, i);
                                    if (a == null) {
                                        continue AccountOptions;
                                    } else {
                                        currentAccount = a; //update the copy
                                        m.getAccountList().set(i, a);//update the manager
                                        m.updateAccount();//update the file
                                        System.out.println("Edit success");
                                        continue Student;
                                    }
                                case "2" :
                                    a = m.editPwd(s, currentAccount);
                                    currentAccount = a;
                                    m.getAccountList().set(i, a);
                                    m.updateAccount();
                                    System.out.println("Edit success");
                                    continue Student;
                                case "3":
                                    m.getAccountList().remove(i);
                                    System.out.println("Delete Successfully");
                                    m.updateAccount();
                                    break;
                                case "0":
                                    continue Student;
                            }
                            break;
                        }
                    case "0" :
                        break Student;
                }
                break;
            }
        }

    }
}