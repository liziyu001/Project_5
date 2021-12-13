import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Represents the server class, which starts and performs the threads of the services
 *
 * @author Ram, William, Leo, Manas, Miras
 * @version December 13, 2021
 */
public class Server extends Thread {
    //Initializes a Manager object in the server
    static Manager m = new Manager();
    //fields of services provided by the server
    ServerSocket loginService;
    ServerSocket registerService;
    ServerSocket getCourseService;
    ServerSocket getQuizService;
    ServerSocket createCourseService;
    ServerSocket createQuizService;
    ServerSocket editQuizService;
    ServerSocket editUsernameService;
    ServerSocket editPasswordService;
    ServerSocket deleteAccountService;
    ServerSocket takeQuizService;
    ServerSocket getQuizContentService;
    ServerSocket deleteQuizService;
    ServerSocket getSubmissionListService;
    ServerSocket viewGradingService;
    ServerSocket gradeSubmissionService;
    ServerSocket viewGradedService;
    ServerSocket getAnswerService;
    ServerSocket randomizeQuizService;

    /**
     * Constructs a newly allocated Server object which initializes the services with port number assignments
     */
    public Server() throws IOException {
        //Initialize the services with port numbers assignments
        loginService = new ServerSocket(4000);
        registerService = new ServerSocket(4001);
        getCourseService = new ServerSocket(4002);
        getQuizService = new ServerSocket(4003);
        createCourseService = new ServerSocket(4004);
        createQuizService = new ServerSocket(4005);
        editUsernameService = new ServerSocket(4006);
        editPasswordService = new ServerSocket(4007);
        deleteAccountService = new ServerSocket(4008);
        editQuizService = new ServerSocket(4009);
        getQuizContentService = new ServerSocket(4010);
        deleteQuizService = new ServerSocket(4011);
        getSubmissionListService = new ServerSocket(4012);
        takeQuizService = new ServerSocket(4013);
        viewGradingService = new ServerSocket(4014);
        gradeSubmissionService = new ServerSocket(4015);
        viewGradedService = new ServerSocket(4016);
        getAnswerService = new ServerSocket(4017);
        randomizeQuizService = new ServerSocket(4018);
    }

    /**
     * Main method which initializes the server, performs the thread of each service by listening to the port
     * And starts the service once it receives connection
     *
     * @param args Stores the command line arguments
     */
    public static void main(String[] args) throws Exception {
        //Initialize the server
        Server server = new Server();

        //thread of a service, simultaneously listening to the port
        Thread login = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.login(server.loginService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        login.start();

        //thread of a service, simultaneously listening to the port
        Thread idCheck = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.register(server.registerService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        idCheck.start();

        //thread of a service, simultaneously listening to the port
        Thread getCourse = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.getCourse(server.getCourseService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        getCourse.start();

        //thread of a service, simultaneously listening to the port
        Thread getQuiz = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.getQuiz(server.getQuizService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        getQuiz.start();

        //thread of a service, simultaneously listening to the port
        Thread createCourse = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.createCourse(server.createCourseService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        createCourse.start();

        //thread of a service, simultaneously listening to the port
        Thread createQuiz = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.createQuiz(server.createQuizService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        createQuiz.start();

        //thread of a service, simultaneously listening to the port
        Thread editID = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.editID(server.editUsernameService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        editID.start();

        //thread of a service, simultaneously listening to the port
        Thread editPwd = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.editPassword(server.editPasswordService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        editPwd.start();

        //thread of a service, simultaneously listening to the port
        Thread deleteAccount = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.deleteAccount(server.deleteAccountService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        deleteAccount.start();

        //thread of a service, simultaneously listening to the port
        Thread editQuiz = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.editQuiz(server.editQuizService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        editQuiz.start();

        //thread of a service, simultaneously listening to the port
        Thread getQuizContent = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.getQuizContent(server.getQuizContentService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        getQuizContent.start();

        //thread of a service, simultaneously listening to the port
        Thread deleteQuiz = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.deleteQuiz(server.deleteQuizService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        deleteQuiz.start();

        //thread of a service, simultaneously listening to the port
        Thread getSubmissionList = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.getSubmissionList(server.getSubmissionListService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        getSubmissionList.start();

        //thread of a service, simultaneously listening to the port
        Thread takeQuiz = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.takeQuiz(server.takeQuizService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        takeQuiz.start();

        //thread of a service, simultaneously listening to the port
        Thread viewGrading = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.viewGrading(server.viewGradingService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        viewGrading.start();

        //thread of a service, simultaneously listening to the port
        Thread gradeSubmission = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.gradeSubmission(server.gradeSubmissionService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        gradeSubmission.start();

        //thread of a service, simultaneously listening to the port
        Thread viewGraded = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.getGradedSubmissionList(server.viewGradedService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        viewGraded.start();

        //thread of a service, simultaneously listening to the port
        Thread getAnswer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.getAnswer(server.getAnswerService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        getAnswer.start();

        //thread of a service, simultaneously listening to the port
        Thread randomizeQuiz = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        // start the service after receiving connection
                        server.randomizeQuiz(server.randomizeQuizService.accept());
                    }
                } catch (Exception e) {
                    int i = 0;
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        randomizeQuiz.start();
    }


    /**
     * @param loginRequest A Socket object used to determine whether the person logged into
     *                     their account correctly or not
     * @return void
     * @Description input username and password, return the result of login
     * @Date 12:27 PM 11/26/2021
     */
    public void login(Socket loginRequest) throws IOException, InterruptedException {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(loginRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(loginRequest.getOutputStream());
            String id = reader.readLine();
            String pwd = reader.readLine();
            boolean found = false;
            for (int i = 0; i < m.getAccountList().size(); i++) {
                if (m.getAccountList().get(i).getUsername().equals(id)) {
                    found = true;
                    if (m.getAccountList().get(i).getPassword().equals(pwd)) {
                        writer.print(m.getAccountList().get(i).isStudent());
                    } else {
                        writer.write("Fail");
                    }
                    writer.println();
                    writer.flush();
                }
            }
            if (!found) {
                writer.write("Fail");
                writer.flush();
            }
            reader.close();
            writer.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param registerRequest Socket object used to determine if person registered successfully or not
     * @return void
     * @Description input username and password, create account based on them
     * @Date 12:28 PM 11/26/2021
     */
    public void register(Socket registerRequest) throws Exception {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(registerRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(registerRequest.getOutputStream());
            String id = reader.readLine();
            String pwd = reader.readLine();
            String role = reader.readLine();
            boolean fail = false;
            for (int i = 0; i < m.getAccountList().size(); i++) {
                if (m.getAccountList().get(i).getUsername().equals(id)) {
                    writer.print("Fail");
                    fail = true;
                }
            }
            if (!fail) {
                switch (role) {
                    case "Teacher":
                        m.getAccountList().add(new Account(id, pwd, false));
                        m.updateAccount();
                        writer.print("Success");
                        break;
                    case "Student":
                        m.getAccountList().add(new Account(id, pwd, true));
                        m.updateAccount();
                        writer.print("Success");
                        break;
                    default:
                        writer.print("Fail");
                }

            }
            writer.println();
            writer.flush();
            reader.close();
            writer.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param getCourseRequest Socket object that gives access to the course list
     * @return void
     * @Description return a course list to the client
     * @Date 10:03 AM 11/27/2021
     */
    public void getCourse(Socket getCourseRequest) {

        try {
            PrintWriter writer = new PrintWriter(getCourseRequest.getOutputStream());
            String course = "";
            for (int i = 0; i < m.getCourseList().size(); i++) {
                course = course + (i + 1) + ". " + m.getCourseList().get(i).getName() + "\n";
            }
            writer.print(course);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param getQuizRequest Socket object that gives access to the quiz list
     * @return void
     * @Description input a course name, return the quiz list of the course
     * @Date 10:03 AM 11/27/2021
     */
    public void getQuiz(Socket getQuizRequest) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getQuizRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(getQuizRequest.getOutputStream());
            String courseName = reader.readLine();
            String quiz = "";
            boolean found = false;
            for (int i = 0; i < m.getCourseList().size(); i++) {
                if (m.getCourseList().get(i).getName().equals(courseName)) {
                    found = true;
                    for (int j = 0; j < m.getCourseList().get(i).getCourseQuiz().size(); j++) {
                        quiz = quiz + (j + 1) + ". " + m.getCourseList().get(i).getCourseQuiz().get(j).getName() + "\n";
                    }
                    if (quiz.isEmpty()) {
                        writer.println("No quiz found in this course");
                    } else {
                        writer.println(quiz);
                    }
                }
            }
            if (!found) {
                writer.println("Given Course is not found");
            }
            writer.flush();
            reader.close();
            writer.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param createCourseRequest Socket object that allows a course to be created if it does not already exist
     * @return void
     * @Description input a course name, create a course based on it
     * @Date 11:45 AM 11/27/2021
     */
    public void createCourse(Socket createCourseRequest) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(createCourseRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(createCourseRequest.getOutputStream());
            String name = reader.readLine();
            Course c = new Course(name);
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
                writer.println("Success");
            } else {
                writer.println("Fail");
            }
            writer.flush();
            reader.close();
            writer.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param createQuizRequest Socket object that allows a quiz to be created if it does not already exist
     * @return void
     * @Description input quiz information and create the quiz
     * @Date 1:01 PM 11/27/2021
     */
    public void createQuiz(Socket createQuizRequest) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(createQuizRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(createQuizRequest.getOutputStream());
            String courseName = reader.readLine();
            String quizName = reader.readLine();
            Course c = null;
            boolean found = false;
            for (int i = 0; i < m.getCourseList().size(); i++) {
                if (m.getCourseList().get(i).getName().equals(courseName)) {
                    c = m.getCourseList().get(i);
                    found = true;
                }
            }
            try {
                int number = Integer.parseInt(reader.readLine());
                Question[] q = new Question[number];
                for (int i = 0; i < number; i++) {
                    String prompt = reader.readLine();
                    String[] options = new String[4];
                    options[0] = reader.readLine();
                    options[1] = reader.readLine();
                    options[2] = reader.readLine();
                    options[3] = reader.readLine();
                    for (int j = 0; j < 4; j++) {
                        if (options[j].equals("null")) {
                            throw new Exception();
                        }
                    }
                    q[i] = new Question(prompt, options);
                }
                Quiz quiz = new Quiz(quizName, q);
                c.getCourseQuiz().add(quiz);
                m.updateCourse();
                writer.println("Success");
            } catch (Exception e) {
                writer.println("Fail");
            }

            writer.flush();
            reader.close();
            writer.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param deleteAccountRequest Socket object that allows the account to be deleted if it exists
     * @return void
     * @Description Input account id and the account will be deleted
     * @Date 1:23 PM 11/27/2021
     */
    public void deleteAccount(Socket deleteAccountRequest) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(deleteAccountRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(deleteAccountRequest.getOutputStream());
            String id = reader.readLine();
            boolean found = false;
            for (int i = 0; i < m.getAccountList().size(); i++) {
                if (m.getAccountList().get(i).getUsername().equals(id)) {
                    found = true;
                    m.getAccountList().remove(i);
                    m.updateAccount();
                    break;
                }
            }
            if (found) {
                writer.println("Success");
            } else {
                writer.println("Account not found");
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param editIDRequest Socket object that allows username to be edited
     * @return void
     * @Description Input an old id and a new id, id will be edited
     * @Date 1:24 PM 11/27/2021
     */
    public void editID(Socket editIDRequest) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(editIDRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(editIDRequest.getOutputStream());
            String oldID = reader.readLine();
            String newID = reader.readLine();
            boolean found = false;
            boolean available = true;
            int index = 0;
            for (int i = 0; i < m.getAccountList().size(); i++) {
                if (m.getAccountList().get(i).getUsername().equals(newID)) {
                    available = false;
                }
                if (m.getAccountList().get(i).getUsername().equals(oldID)) {
                    found = true;
                    index = i;
                }
            }
            if (found && available) {
                m.getAccountList().get(index).setUsername(newID);
                m.updateAccount();
                writer.println("Success");
            } else if (found && !available) {
                writer.println("Duplicate new ID");
            } else {
                writer.println("Account not found");
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param editPasswordRequest Socket object that allows password to be edited
     * @return void
     * @Description Input id and new password, password will be updated
     * @Date 1:24 PM 11/27/2021
     */
    public void editPassword(Socket editPasswordRequest) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(editPasswordRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(editPasswordRequest.getOutputStream());
            String id = reader.readLine();
            String pwd = reader.readLine();
            boolean found = false;
            for (int i = 0; i < m.getAccountList().size(); i++) {
                if (m.getAccountList().get(i).getUsername().equals(id)) {
                    found = true;
                    m.getAccountList().get(i).setPassword(pwd);
                    m.updateAccount();
                    break;
                }
            }
            if (found) {
                writer.println("Success");
            } else {
                writer.println("Account not found");
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param editQuizRequest Socket object that allows the quiz to be edited
     * @return void
     * @Description input course name and target quiz name and quiz info, quiz will be edited
     * @Date 8:51 PM 11/27/2021
     */
    public void editQuiz(Socket editQuizRequest) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(editQuizRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(editQuizRequest.getOutputStream());
            String courseName = reader.readLine();
            String quizName = reader.readLine();
            Course c = null;
            boolean found = false;
            for (int i = 0; i < m.getCourseList().size(); i++) {
                if (m.getCourseList().get(i).getName().equals(courseName)) {
                    c = m.getCourseList().get(i);
                    found = true;
                }
            }
            try {
                int number = Integer.parseInt(reader.readLine());
                Question[] q = new Question[number];
                for (int i = 0; i < number; i++) {
                    String prompt = reader.readLine();
                    String[] options = new String[4];
                    options[0] = reader.readLine();
                    options[1] = reader.readLine();
                    options[2] = reader.readLine();
                    options[3] = reader.readLine();
                    q[i] = new Question(prompt, options);
                }
                for (int i = 0; i < c.getCourseQuiz().size(); i++) {
                    if (c.getCourseQuiz().get(i).getName().equals(quizName)) {
                        c.getCourseQuiz().get(i).setQuestions(q);
                        m.updateCourse();
                        break;
                    }
                }
                writer.println("Success");
            } catch (Exception e) {
                writer.println("Fail");
            }
            writer.flush();
            reader.close();
            writer.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param getQuizContentRequest Socket object that allows the contents of the quiz to be accessed
     * @return void
     * @Description Input course and quiz name, quiz content will be returned
     * @Date 8:50 PM 11/27/2021
     */
    public void getQuizContent(Socket getQuizContentRequest) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getQuizContentRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(getQuizContentRequest.getOutputStream());
            String courseName = reader.readLine();
            String quizName = reader.readLine();
            Course c = null;
            try {
                for (int i = 0; i < m.getCourseList().size(); i++) {
                    if (m.getCourseList().get(i).getName().equals(courseName)) {
                        c = m.getCourseList().get(i);
                    }
                }
                for (int i = 0; i < c.getCourseQuiz().size(); i++) {
                    if (c.getCourseQuiz().get(i).getName().equals(quizName)) {
                        writer.print(c.getCourseQuiz().get(i).toString());
                    }
                }
            } catch (Exception e) {
                writer.println("Course or Quiz not found");
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param deleteQuizRequest Socket object that allows the quiz to be deleted if it exists
     * @return void
     * @Description input course name and quiz name, quiz will be deleted
     * @Date 8:50 PM 11/27/2021
     */
    public void deleteQuiz(Socket deleteQuizRequest) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(deleteQuizRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(deleteQuizRequest.getOutputStream());
            String courseName = reader.readLine();
            String quizName = reader.readLine();
            Course c = null;
            try {
                for (int i = 0; i < m.getCourseList().size(); i++) {
                    if (m.getCourseList().get(i).getName().equals(courseName)) {
                        c = m.getCourseList().get(i);
                    }
                }
                for (int i = 0; i < c.getCourseQuiz().size(); i++) {
                    if (c.getCourseQuiz().get(i).getName().equals(quizName)) {
                        c.getCourseQuiz().remove(i);
                        m.updateCourse();
                        writer.println("Success");
                    }
                }
            } catch (Exception e) {
                writer.println("Course or Quiz not found");
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param getSubmissionListRequest Socket object that allows the submission list to be accessed
     * @return void
     * @Description return the list of ungraded submissions
     * @Date 5:48 PM 12/3/2021
     */
    public void getSubmissionList(Socket getSubmissionListRequest) {
        try {
            PrintWriter writer = new PrintWriter(getSubmissionListRequest.getOutputStream());
            String out = "";
            for (int i = 0; i < m.getSubmissionList().size(); i++) {
                Submission sub = m.getSubmissionList().get(i);
                if (!sub.isGraded()) {
                    out = out + sub.getCourseName() + "-" + sub.getQuizName() + "-" + sub.getId() + "\n";
                }
            }
            writer.print(out);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param gradeSubmissionRequest Socket object that gives access for the submission that needs to be graded
     * @return void
     * @Description Input submission identifiers and subGrades, the submission will be graded
     * @Date 5:47 PM 12/3/2021
     */
    public void gradeSubmission(Socket gradeSubmissionRequest) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(gradeSubmissionRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(gradeSubmissionRequest.getOutputStream());
            try {
                String courseName = reader.readLine();
                String quizName = reader.readLine();
                String id = reader.readLine();
                String scores = reader.readLine();
                boolean found = false;
                int[] subScores = new int[scores.split(",").length];
                for (int i = 0; i < subScores.length; i++) {
                    subScores[i] = Integer.parseInt(scores.split(",")[i]);
                }
                for (int i = 0; i < m.getSubmissionList().size(); i++) {
                    if (m.getSubmissionList().get(i).getCourseName().equals(courseName)
                            && m.getSubmissionList().get(i).getId().equals(id)
                            && m.getSubmissionList().get(i).getQuizName().equals(quizName)) {
                        found = true;
                        m.getSubmissionList().get(i).setSubGrades(subScores);
                        m.getSubmissionList().get(i).setGraded(true);
                        m.updateSubmission();
                        writer.println("Success");
                        break;
                    }
                }
                if (!found) {
                    writer.println("Submission not found");
                }
            } catch (Exception e) {
                writer.println("Input Format Not Correct");
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param takeQuizRequest Socket object that gives access to the quiz
     * @return void
     * @Description input submission identifiers and answers, the new submission will be recorded,
     * newer attempt will replace older ones
     * @Date 5:54 PM 12/3/2021
     */
    public void takeQuiz(Socket takeQuizRequest) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(takeQuizRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(takeQuizRequest.getOutputStream());
            try {
                String courseName = reader.readLine();
                String quizName = reader.readLine();
                String id = reader.readLine();
                String ans = reader.readLine();
                int[] answers = new int[ans.split(",").length];
                for (int i = 0; i < answers.length; i++) {
                    answers[i] = Integer.parseInt(ans.split(",")[i]);
                }
                boolean found = false;
                for (int i = 0; i < m.getSubmissionList().size(); i++) {
                    if (m.getSubmissionList().get(i).getCourseName().equals(courseName)
                            && m.getSubmissionList().get(i).getId().equals(id)
                            && m.getSubmissionList().get(i).getQuizName().equals(quizName)) {
                        found = true;
                        m.getSubmissionList().get(i).setAnswers(answers);
                        m.getSubmissionList().get(i).setGraded(false);
                        m.getSubmissionList().get(i).setSubGrades(new int[answers.length]);
                    }
                }
                Submission s = new Submission(courseName, quizName, id, answers);
                if (!found) {
                    m.getSubmissionList().add(s);
                }
                m.updateSubmission();
                writer.println(s.getTimestamp());
            } catch (Exception e) {
                writer.println("Error");
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param viewGradingRequest Socket object that gives access to the quiz grade
     * @return void
     * @Description input submission identifier to get its detailed grading
     * @Date 6:02 PM 12/3/2021
     */
    public void viewGrading(Socket viewGradingRequest) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(viewGradingRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(viewGradingRequest.getOutputStream());
            String courseName = reader.readLine();
            String quizName = reader.readLine();
            String id = reader.readLine();
            int total = 0;
            String sub = "";
            boolean found = false;
            for (int i = 0; i < m.getSubmissionList().size(); i++) {
                if (m.getSubmissionList().get(i).getCourseName().equals(courseName)
                        && m.getSubmissionList().get(i).getId().equals(id)
                        && m.getSubmissionList().get(i).getQuizName().equals(quizName)) {
                    found = true;
                    if (m.getSubmissionList().get(i).isGraded()) {
                        for (int j = 0; j < m.getSubmissionList().get(i).getSubGrades().length; j++) {
                            sub = sub + m.getSubmissionList().get(i).getSubGrades()[j] + ",";
                            total = total + m.getSubmissionList().get(i).getSubGrades()[j];
                        }
                        sub = sub.substring(0, sub.length() - 1);
                        writer.println(sub);
                        writer.println(total);
                    } else {
                        writer.println("Not graded");
                    }
                }
            }
            if (!found) {
                writer.println("Submission not found");
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param getSubmissionListRequest Socket object that gives access to list of graded submissions
     * @return void
     * @Description Uses Socket to get student's usernames and their submissions
     */
    public void getGradedSubmissionList(Socket getSubmissionListRequest) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getSubmissionListRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(getSubmissionListRequest.getOutputStream());
            String id = reader.readLine();
            String out = "";
            for (int i = 0; i < m.getSubmissionList().size(); i++) {
                Submission sub = m.getSubmissionList().get(i);
                if (sub.isGraded() && sub.getId().equals(id)) {
                    out = out + sub.getCourseName() + "-" + sub.getQuizName() + "-" + sub.getId() + "\n";
                }
            }
            writer.print(out);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param getAnswerRequest Socket object that gives access to student's answers
     * @return void
     * @Description Uses Socket to get the course name, quiz name, and student's usernames to get
     * access to the student's answers
     */
    public void getAnswer(Socket getAnswerRequest) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAnswerRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(getAnswerRequest.getOutputStream());
            String courseName = reader.readLine();
            String quizName = reader.readLine();
            String id = reader.readLine();
            int total = 0;
            String ans = "";
            boolean found = false;
            for (int i = 0; i < m.getSubmissionList().size(); i++) {
                if (m.getSubmissionList().get(i).getCourseName().equals(courseName)
                        && m.getSubmissionList().get(i).getId().equals(id)
                        && m.getSubmissionList().get(i).getQuizName().equals(quizName)) {
                    found = true;
                    for (int j = 0; j < m.getSubmissionList().get(i).getAnswers().length; j++) {
                        ans = ans + m.getSubmissionList().get(i).getAnswers()[j] + ",";
                    }
                    ans = ans.substring(0, ans.length() - 1);
                    writer.println(ans);
                    writer.println(total);
                }
            }
            if (!found) {
                writer.println("Submission not found");
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

    /**
     * @param randomizeQuizRequest Socket object that gives access to a randomized quiz version
     * @return void
     * @Description Uses Socket to get the course name and quiz name to help generate a randomized quiz
     */
    public void randomizeQuiz(Socket randomizeQuizRequest) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(randomizeQuizRequest.getInputStream()));
            PrintWriter writer = new PrintWriter(randomizeQuizRequest.getOutputStream());
            try {
                String courseName = reader.readLine();
                String quizName = reader.readLine();
                Course c = null;
                for (int i = 0; i < m.getCourseList().size(); i++) {
                    if (m.getCourseList().get(i).getName().equals(courseName)) {
                        c = m.getCourseList().get(i);
                    }
                }
                for (int i = 0; i < c.getCourseQuiz().size(); i++) {
                    if (c.getCourseQuiz().get(i).getName().equals(quizName)) {
                        c.getCourseQuiz().get(i).randomizeQuiz();
                        m.updateCourse();
                        writer.println("Success");
                    }
                }

            } catch (Exception e) {
                writer.println("Fail");
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (Exception e) {
            int i = 0;
        }
    }

}

