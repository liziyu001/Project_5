import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    private static String currentAccount;
    private static boolean student;
    private static String currentCourse;
    private static String currentQuiz;
    public static ArrayList<String> connect(ArrayList<String> input, int port) {

        ArrayList<String> result = new ArrayList<String>();

        try {
            InetAddress address = InetAddress.getByName("10.186.124.152");
            Socket socket = new Socket(address , port);
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
            e.printStackTrace();
        }
        return null;
    }

    public static void setStudent(boolean student) {
        Main.student = student;
    }

    public static boolean isStudent() {
        return student;
    }

    public static String getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(String currentAccount) {
        Main.currentAccount = currentAccount;
    }

    public static String getCurrentCourse() {
        return currentCourse;
    }

    public static String getCurrentQuiz() {
        return currentQuiz;
    }

    public static void setCurrentQuiz(String currentQuiz) {
        Main.currentQuiz = currentQuiz;
    }

    public static void setCurrentCourse(String currentCourse) {
        Main.currentCourse = currentCourse;
    }

    public static void main(String[] args) {
        Menu m = new Menu();
        m.setVisible(true);
    }
}
