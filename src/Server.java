import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    static Manager m = new Manager();
    //field of services provided by the server
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

    public Server() throws IOException {
        //Initialize the services with port numbers
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
    }

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
                    e.printStackTrace();
                }
            }
        });
        //start the thread, since simultaneously, no need to join
        login.start();

        Thread idCheck = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        server.register(server.registerService.accept());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        idCheck.start();

        Thread getCourse = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        server.getCourse(server.getCourseService.accept());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        getCourse.start();

        Thread getQuiz = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        server.getQuiz(server.getQuizService.accept());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        getQuiz.start();

        Thread createCourse = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        server.createCourse(server.createCourseService.accept());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        createCourse.start();

        Thread createQUiz = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        server.createQuiz(server.createQuizService.accept());
                    }
                } catch (Exception e) {

                }
            }
        });
        createQUiz.start();

        Thread editID = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        server.editID(server.editUsernameService.accept());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        editID.start();

        Thread editPwd = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        server.editPassword(server.editPasswordService.accept());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        editPwd.start();

        Thread deleteAccount = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        server.deleteAccount(server.deleteAccountService.accept());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        deleteAccount.start();

        Thread editQuiz = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        server.editQuiz(server.editQuizService.accept());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        editQuiz.start();

        Thread getQuizContent = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        server.getQuizContent(server.getQuizContentService.accept());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        getQuizContent.start();

        Thread deleteQuiz = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        server.deleteQuiz(server.deleteQuizService.accept());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        deleteQuiz.start();
    }

    public void login(Socket loginRequest) throws IOException, InterruptedException {
        /*
         * @Description input username and password, return the result of login
         * @Date 12:27 PM 11/26/2021
         * @Param [loginRequest]
         * @return void
         **/
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
            e.printStackTrace();
        }
    }


    public void register(Socket registerRequest) throws Exception {
        /*
         * @Description input username and password, create account based on them
         * @Date 12:28 PM 11/26/2021
         * @Param [registerRequest]
         * @return void
         **/
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
            e.printStackTrace();
        }
    }

    public void getCourse(Socket getCourseRequest) {
        /*
         * @Description return a course list to the client
         * @Date 10:03 AM 11/27/2021
         * @Param [getCourseRequest]
         * @return void
         **/
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
            e.printStackTrace();
        }
    }

    public void getQuiz(Socket getQuizRequest) {
        /*
         * @Description input a course name, return the quiz list of the course
         * @Date 10:03 AM 11/27/2021
         * @Param [getQuizRequest]
         * @return void
         **/
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
            e.printStackTrace();
        }
    }

    public void createCourse(Socket createCourseRequest) {
        /*
         * @Description input a course name, create a course based on it
         * @Date 11:45 AM 11/27/2021
         * @Param [createCourseRequest]
         * @return void
         **/
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
            e.printStackTrace();
        }
    }

    public void createQuiz(Socket createQuizRequest) {
        /*
         * @Description input quiz information and create the quiz
         * @Date 1:01 PM 11/27/2021
         * @Param [createQuizRequest]
         * @return void
         **/
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
            e.printStackTrace();
        }
    }

    public void deleteAccount(Socket deleteAccountRequest) {
        /*
         * @Description Input account id and the account will be deleted
         * @Date 1:23 PM 11/27/2021
         * @Param [deleteAccountRequest]
         * @return void
         **/
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
            e.printStackTrace();
        }
    }

    public void editID(Socket editIDRequest) {
        /*
         * @Description Input an old id and a new id, id will be edited
         * @Date 1:24 PM 11/27/2021
         * @Param [editIDRequest]
         * @return void
         **/
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
            e.printStackTrace();
        }
    }

    private void editPassword(Socket editPasswordRequest) {
        /*
         * @Description Input id and new password, password will be updated
         * @Date 1:24 PM 11/27/2021
         * @Param [editPasswordRequest]
         * @return void
         **/
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
            e.printStackTrace();
        }
    }

    public void editQuiz(Socket editQuizRequest) {
        /*
         * @Description input course name and target quiz name and quiz info, quiz will be edited
         * @Date 8:51 PM 11/27/2021
         * @Param [editQuizRequest]
         * @return void
         **/
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
            e.printStackTrace();
        }
    }

    public void getQuizContent(Socket getQuizContentRequest) {
        /*
         * @Description Input course and quiz name, quiz content will be returned
         * @Date 8:50 PM 11/27/2021
         * @Param [getQuizContentRequest]
         * @return void
         **/
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
            e.printStackTrace();
        }
    }

    public void deleteQuiz(Socket deleteQuizRequest) {
        /*
         * @Description input course name and quiz name, quiz will be deleted
         * @Date 8:50 PM 11/27/2021
         * @Param [deleteQuizRequest]
         * @return void
         **/
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
                        writer.print("Success");
                    }
                }
            } catch (Exception e) {
                writer.println("Course or Quiz not found");
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
