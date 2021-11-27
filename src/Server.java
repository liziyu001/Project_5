import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    static Manager m = new Manager();
    ServerSocket loginService; //field of services provided by the server
    ServerSocket registerService;
    ServerSocket getCourseService;
    ServerSocket getQuizService;

    public Server() throws IOException {
        loginService = new ServerSocket(4000); //Initialize the services with port numbers
        registerService = new ServerSocket(4001);
        getCourseService = new ServerSocket(4002);
        getQuizService = new ServerSocket(4003);
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(); //Initialize the server

        Thread login = new Thread(new Runnable() { //thread of a service, simultaneously listening to the port
            @Override
            public void run() {
                try {
                    while (true) { // 'keep' listening to the port
                        server.login(server.loginService.accept()); // start the service after receiving connection
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        login.start(); //start the thread, since simultaneously, no need to join

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
                    case "Teacher" :
                        m.getAccountList().add(new Account(id, pwd, false));
                        m.updateAccount();
                        writer.print("Success");
                        break;
                    case "Student" :
                        m.getAccountList().add(new Account(id, pwd, true));
                        m.updateAccount();
                        writer.print("Success");
                        break;
                    default :
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
                        quiz = quiz + (j + 1) + ". "
                                + m.getCourseList().get(i).getCourseQuiz().get(j).getName() + "\n";
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
}
