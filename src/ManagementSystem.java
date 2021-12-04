import java.util.Scanner;


public class ManagementSystem {
    static Manager m = new Manager();

    public static void main(String[] args) {
        /* GUI version
        Account currentAccount;
        JOptionPane.showMessageDialog(null, "Welcome to the system", "Welcome", JOptionPane.PLAIN_MESSAGE);
        loginOption:
        while (true) {
            String login = JOptionPane.showInputDialog(null, "1. create account 2. login", "Account Login", JOptionPane.QUESTION_MESSAGE);
            switch (login) {
                case "1":
                    Account a = m.createAccount(s);
                    if (a == null) {
                        continue loginOption;
                    }
                    m.getAccountList().add(a);
                    m.updateAccount();
                    currentAccount = a;
                    JOptionPane.showMessageDialog(null, "Creat Account Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "2":
                    Account ac = m.login(s);
                    if (ac == null) {
                        continue loginOption;
                    }
                    currentAccount = ac;
                    JOptionPane.showMessageDialog(null, "Successfully login as " + ac.getUsername(), "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid Option", "Error", JOptionPane.ERROR_MESSAGE);
                    continue loginOption;
            }
            break;
        }
        
        if (!currentAccount.isStudent()) {
            Teacher:
            while (true) {
                String teacherOptions = JOptionPane.showInputDialog(null, "1. View Courses 2. Create a Course 3. Account Setting 0. Exit", "Teacher's Options", JOptionPane.QUESTION_MESSAGE);
                switch (teacherOptions) {
                    case "1":
                        Course currentCourse;
                        Quiz currentQuiz;
                        CourseSelection:
                        while (true) {
                            JOptionPane.showMessageDialog(null, m.listCourses(), "Course List", JOptionPane.INFORMATION_MESSAGE);
                            int courseSelect = Integer.parseInt(JOptionPane.showInputDialog(null, "Select the course you want to view", "Select Course", JOptionPane.INFORMATION_MESSAGE));
                            currentCourse = m.getCourseList().get(courseSelect - 1);
                            if (currentCourse == null) {
                                JOptionPane.showMessageDialog(null, "Invalid Choice", "Error", JOptionPane.ERROR_MESSAGE);
                                continue CourseSelection;
                            }
                            break;
                        }
                        if (currentCourse.listQuiz().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "There is no quiz in this Course", "No Quiz", JOptionPane.ERROR_MESSAGE);
                            continue Teacher;
                        } else {
                            QuizSelection:
                            while (true) {
                                JOptionPane.showMessageDialog(null, currentCourse.listQuiz() + " 0. Create a new Quiz", "Quiz List", JOptionPane.INFORMATION_MESSAGE);
                                String temp = JOptionPane.showInputDialog(null, "Select the Quiz you want to view", "Quiz Select", JOptionPane.QUESTION_MESSAGE);
                                if (temp.equals("0")) {
                                    Quiz q = currentAccount.createQuiz(s);
                                    if (q == null) {
                                        continue Teacher;
                                    } else {
                                        currentCourse.getCourseQuiz().add(q);
                                        //update the file
                                        m.updateCourse();
                                        JOptionPane.showMessageDialog(null, "Create Quiz Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        continue Teacher;
                                    }
                                } else {
                                    currentQuiz = currentCourse.getCourseQuiz().get(Integer.parseInt(temp) - 1);
                                    if (currentQuiz == null) {
                                        JOptionPane.showMessageDialog(null, "Invalid Choice", "Invalid", JOptionPane.ERROR_MESSAGE);
                                        continue QuizSelection;
                                    }
                                    QuizOption:
                                    while (true) {
                                        String editQuiz = JOptionPane.showInputDialog(null, "1. Edit the Quiz 0. Back", "Edit Quiz", JOptionPane.QUESTION_MESSAGE);
                                        switch (editQuiz) {
                                            case "1":
                                                Quiz q = currentAccount.createQuiz(s);
                                                if (q == null) {
                                                    continue Teacher;
                                                } else {
                                                    int index = currentCourse.getCourseQuiz().indexOf(currentQuiz);
                                                    currentCourse.getCourseQuiz().set(index, q);
                                                    //update the file
                                                    m.updateCourse();
                                                    JOptionPane.showMessageDialog(null, "Create Edited Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                                    continue Teacher;
                                                }
                                            case "0":
                                                continue Teacher;
                                            //just to make the break statement reachable
                                            case "a":
                                                break;
                                            default: 
                                                JOptionPane.showMessageDialog(null, "Invalid Option", "Invalid", JOptionPane.ERROR_MESSAGE);
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
                        for (int i = 0; i < m.getCourseList().size(); i++) {  
                            //Check availability
                            if (c.getName().equals(m.getCourseList().get(i).getName())) {
                                available = false;
                            }
                        }
                        if (available) {
                            m.getCourseList().add(c);
                            m.updateCourse();
                            JOptionPane.showMessageDialog(null, "Successfully created course " + c.getName(), "Success", JOptionPane.INFORMATION_MESSAGE);
                            continue Teacher;
                        } else {
                            JOptionPane.showMessageDialog(null, "You can't have two courses with the same name", "Duplicate Name", JOptionPane.ERROR_MESSAGE);
                            continue Teacher;
                        }
                    case "3":
                        AccountOptions:
                        while (true) {
                            int i = m.getAccountList().indexOf(currentAccount);
                            Account a;
                            String accountEdit = JOptionPane.showInputDialog(null, "1. Edit your username 2. Edit your password 3. Delete your account 0. Back", "Account Edits", JOptionPane.QUESTION_MESSAGE);
                            switch (accountEdit) {
                                case "1":
                                    a = m.editID(s, currentAccount, i);
                                    if (a == null) {
                                        continue AccountOptions;
                                    } else {
                                        //update the copy
                                        currentAccount = a; 
                                        //update the manager
                                        m.getAccountList().set(i, a);
                                        //update the file
                                        m.updateAccount();
                                        JOptionPane.showMessageDialog(null, "Edit success", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        continue Teacher;
                                    }
                                case "2":
                                    a = m.editPwd(s, currentAccount);
                                    currentAccount = a;
                                    m.getAccountList().set(i, a);
                                    m.updateAccount();
                                    JOptionPane.showMessageDialog(null, "Edit success", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    continue Teacher;
                                case "3":
                                    m.getAccountList().remove(i);
                                    JOptionPane.showMessageDialog(null, "Delete Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    m.updateAccount();
                                    break;
                                case "0":
                                    continue Teacher;
                            }
                            break;
                        }
                    case "0":
                        break Teacher;
                }
                break;
            }
        } else {
            Student:
            while (true) {
                String studentAccount = JOptionPane.showInputDialog(null, "1. View Courses 2. Account Setting 0. Exit", "Student Account Options", JOptionPane.QUESTION_MESSAGE);
                switch (studentAccount) {
                    case "1":
                        Course currentCourse;
                        Quiz currentQuiz;
                        CourseSelection:
                        while (true) {
                            JOptionPane.showMessageDialog(null, m.listCourses(), "Course List", JOptionPane.INFORMATION_MESSAGE);
                            int course = Integer.parseInt(JOptionPane.showInputDialog(null, "Select the course you want to view", "Course", JOptionPane.QUESTION_MESSAGE));
                            try {
                                currentCourse = m.getCourseList().get(course - 1);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Invalid Choice", "Invalid", JOptionPane.ERROR_MESSAGE);
                                continue CourseSelection;
                            }
                            if (currentCourse.listQuiz().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "There is no quiz in this Course", "No Quiz", JOptionPane.ERROR_MESSAGE);
                                continue CourseSelection;
                            }
                            break;
                        }
                        QuizSelection:
                        while (true) {
                            JOptionPane.showMessageDialog(null, currentCourse.listQuiz(), "Quiz List", JOptionPane.INFORMATION_MESSAGE);
                            String quizChoice = JOptionPane.showInputDialog(null, "Select the Quiz you want to take","Quiz Selection", JOptionPane.QUESTION_MESSAGE);
                            currentQuiz = currentCourse.getCourseQuiz().get(Integer.parseInt(quizChoice) - 1);
                            if (currentQuiz == null) {
                                JOptionPane.showMessageDialog(null, "Invalid Choice", "Invalid", JOptionPane.ERROR_MESSAGE);
                                continue QuizSelection;
                            }
                            Submission sub = currentAccount.takeQuiz(s, currentQuiz);
                            m.getSubmissionList().add(sub);
                            JOptionPane.showMessageDialog(null, "Submission Recorded", "Submission Recorded", JOptionPane.INFORMATION_MESSAGE);
                            continue Student;
                        }
                    case "2":
                        AccountOptions:
                        while (true) {
                            int i = m.getAccountList().indexOf(currentAccount);
                            Account a;
                            String accountOptions = JOptionPane.showInputDialog(null, "1. Edit your username 2. Edit your pasword 3. Delete your account 0. Back", "Student Account Options", JOptionPane.QUESTION_MESSAGE);
                            switch (accountOptions) {
                                case "1":
                                    a = m.editID(s, currentAccount, i);
                                    if (a == null) {
                                        continue AccountOptions;
                                    } else {
                                        //update the copy
                                        currentAccount = a; 
                                        //update the manager
                                        m.getAccountList().set(i, a);
                                        //update the file
                                        m.updateAccount();
                                        JOptionPane.showMessageDialog(null, "Edit success", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        continue Student;
                                    }
                                case "2":
                                    a = m.editPwd(s, currentAccount);
                                    currentAccount = a;
                                    m.getAccountList().set(i, a);
                                    m.updateAccount();
                                    JOptionPane.showMessageDialog(null, "Edit success", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    continue Student;
                                case "3":
                                    m.getAccountList().remove(i);
                                    JOptionPane.showMessageDialog(null, "Delete Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    m.updateAccount();
                                    break;
                                case "0":
                                    continue Student;
                            }
                            break;
                        } 
                    case "0":
                        break Student;
                }
                break;
            }
        }   
        */
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
                default:
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
                                if (temp.equals("0")) {
                                    Quiz q = currentAccount.createQuiz(s);
                                    if (q == null) {
                                        continue Teacher;
                                    } else {
                                        currentCourse.getCourseQuiz().add(q);
                                        //update the file
                                        m.updateCourse();
                                        System.out.println("Create Quiz Successfully");
                                        continue Teacher;
                                    }
                                } else {
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
                                                    //update the file
                                                    m.updateCourse();
                                                    System.out.println("Create Edited Successfully");
                                                    continue Teacher;
                                                }
                                            case "0":
                                                continue Teacher;
                                            //just to make the break statement reachable
                                            case "a":
                                                break;
                                            default:
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
                        for (int i = 0; i < m.getCourseList().size(); i++) {  
                            //Check availability
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
                            System.out.println("You can't have two course with the same name");
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
                                        //update the copy
                                        currentAccount = a; 
                                        //update the manager
                                        m.getAccountList().set(i, a);
                                        //update the file
                                        m.updateAccount();
                                        System.out.println("Edit success");
                                        continue Teacher;
                                    }
                                case "2":
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
                    case "0":
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
                    case "1":
                        Course currentCourse;
                        Quiz currentQuiz;
                        CourseSelection:
                        while (true) {
                            System.out.println("Select the course you want to view");
                            System.out.println(m.listCourses());
                            try {
                                currentCourse = m.getCourseList().get(Integer.parseInt(s.nextLine()) - 1);
                            } catch (Exception e) {
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
                    case "2":
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
                                        //update the copy
                                        currentAccount = a; 
                                        //update the manager
                                        m.getAccountList().set(i, a);
                                        //update the file
                                        m.updateAccount();
                                        System.out.println("Edit success");
                                        continue Student;
                                    }
                                case "2":
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
                    case "0":
                        break Student;
                }
                break;
            }
        }

    }
}
