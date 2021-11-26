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


    public Server() throws IOException {
        loginService = new ServerSocket(4000); //Initialize the services with port numbers
        registerService = new ServerSocket(4001);
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


}
