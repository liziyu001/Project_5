import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException {
        while (true) {
            Scanner scan = new Scanner(System.in);
            Socket socket = new Socket("localhost", 4006);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            for (int i = 0; i < 2; i++) {
                System.out.println("What do you want to send to the server?");
                String s = scan.nextLine();
                writer.write(s);
                writer.println();
            }
            writer.flush(); // ensure data is sent to the server
            String receive = "";
            String s1 = reader.readLine();
            while (s1 != null) {
                receive = receive + s1 + "\n";
                s1 = reader.readLine();
            }
            System.out.println("Received from server:");
            System.out.print(receive);
            writer.close();
            reader.close();
        }
    }

    public ArrayList<String> connect (ArrayList<String> input, int port) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            Socket socket = new Socket("localhost", 4010);
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
}
