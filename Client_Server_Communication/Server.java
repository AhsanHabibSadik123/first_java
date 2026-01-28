package Client_Server_Communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Waiting for client...");
        try {

            ServerSocket ss = new ServerSocket(9806);
            Socket socket = ss.accept();
            System.out.println("Connection established...");
            BufferedReader userInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str = userInput.readLine();
            System.out.println("Received from client: " + str);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String reply;
            if (str != null && str.trim().equalsIgnoreCase("hello")) {
                reply = "hi";
            } else {
                reply = "Server says: " + str;
            }
            out.println(reply);
            socket.close();
            ss.close();
            

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
