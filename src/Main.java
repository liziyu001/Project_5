import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Represents a class that pops up the beginning menu, connects to a
 * certain port with certain features/service that is currently active
 * And updates the current account, course, and quiz
 *
 * @author Ram, William, Leo, Manas, Miras
 * @version December 13, 2021
 */
public class Main {
    //Represents the current Account that is currently connected
    private static String currentAccount;
    //Determines if the account is under a student or is not under a student
    private static boolean student;
    //Represents the current Course that is currently connected
    private static String currentCourse;
    //Represents the current Quiz that is currently connected
    private static String currentQuiz;

    /**
     * Connects to a certain port with a given service, and reads and writes
     * the contents that came from the port that the Socket connected to
     *
     * @return Returns the list of input that was read and written from the port that the Socket connnected to
     */
    public static ArrayList<String> connect(ArrayList<String> input, int port) {

        ArrayList<String> result = new ArrayList<>();

        try {
            //InetAddress address = InetAddress.getByName("10.186.83.236");
            //Socket socket = new Socket(address , port);
            // comment out the line with localhost and uncomment the previous
            // two lines and enter your ip to connect to a remote server
            Socket socket = new Socket("localhost", port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            for (int i = 0; i < input.size(); i++) {
                writer.println(input.get(i));
            }
            writer.flush();
            String s1 = reader.readLine();
            while (s1 != null) {
                result.add(s1);
                s1 = reader.readLine();
            }
            return result;
        } catch (Exception e) {
            int i = 0;
        }
        return null;
    }

    /**
     * Updates the student status of the current account in the Main class with the specified student status
     *
     * @param student The specified student status used for construction
     */
    public static void setStudent(boolean student) {
        Main.student = student;
    }

    /**
     * Returns the student status of the current account
     *
     * @return Returns the student status of the current account
     */
    public static boolean isStudent() {
        return student;
    }

    /**
     * Returns the current account that is connected
     *
     * @return Returns the current account that is connected
     */
    public static String getCurrentAccount() {
        return currentAccount;
    }

    /**
     * Updates the current account that is connected in the Main class with the specified current account
     *
     * @param currentAccount The specified current account used for the update
     */
    public static void setCurrentAccount(String currentAccount) {
        Main.currentAccount = currentAccount;
    }

    /**
     * Returns the current course that is connected
     *
     * @return Returns the current course that is connected
     */
    public static String getCurrentCourse() {
        return currentCourse;
    }

    /**
     * Returns the current quiz that is connected
     *
     * @return Returns the current quiz that is connected
     */
    public static String getCurrentQuiz() {
        return currentQuiz;
    }

    /**
     * Updates the current quiz that is connected in the Main class with the specified current quiz
     *
     * @param currentQuiz The specified current quiz used for the update
     */
    public static void setCurrentQuiz(String currentQuiz) {
        Main.currentQuiz = currentQuiz;
    }

    /**
     * Updates the current course that is connected in the Main class with the specified current course
     *
     * @param currentCourse The specified current course used for the update
     */
    public static void setCurrentCourse(String currentCourse) {
        Main.currentCourse = currentCourse;
    }

    /**
     * The main method that creates a menu with the login and register
     * options, and is eventually connected to a certain port
     *
     * @param args Stores the command line arguments
     */
    public static void main(String[] args) {
        Menu m = new Menu();
        m.setVisible(true);
    }
}
