package Client_Server_Communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    
    public static void main(String[] args) {

        try {
            
            System.out.println("Client started...");
            Socket socket = new Socket("localhost", 9806);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a string : ");
            String str = userInput.readLine();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(str);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();
            System.out.println(response);
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
