package Email_Sending;
import java.io.*;
import javax.net.ssl.*;
import java.util.*;

class Email {

  private static DataOutputStream dos;
  public static BufferedReader br;

  public static void main(String argv[]) throws Exception {
    String user = "ahsanhabibsadik@gmail.com";
    String pass = "hqwvedufmnlhxbzw";

    String username = new String(Base64.getEncoder().encode(user.getBytes()));
    String password = new String(Base64.getEncoder().encode(pass.getBytes()));
    SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com", 465);
    dos = new DataOutputStream(s.getOutputStream());
    br = new BufferedReader(new InputStreamReader(s.getInputStream()));

    System.out.println("SERVER: " + br.readLine()); // Read initial greeting

    send("EHLO smtp.gmail.com\r\n");
    String line;
    while ((line = br.readLine()) != null) {
      System.out.println("SERVER: " + line);
      if (line.startsWith("250 "))
        break; // Last line has no hyphen
    }

    send("AUTH LOGIN\r\n");
    System.out.println("SERVER: " + br.readLine());

    send(username + "\r\n");
    System.out.println("SERVER: " + br.readLine());

    send(password + "\r\n");
    System.out.println("SERVER: " + br.readLine());

    send("MAIL FROM:<ahsanhabibsadik@gmail.com>\r\n");
    System.out.println("SERVER: " + br.readLine());

    send("RCPT TO:<asifzaman3180@gmail.com>\r\n");
    System.out.println("SERVER: " + br.readLine());

    send("DATA\r\n");
    System.out.println("SERVER: " + br.readLine());

    send("FROM: <ahsanhabibsadik@gmail.com>\r\n");
    send("TO: <asifzaman3180@gmail.com>\r\n");
    send("Subject: Send Test Email" + "\r\n");
    send("\r\n"); // Blank line required between headers and body
    send("Hello sir. I am Md Ahsan Habib Sadik. My roll number is 2210976101\r\n");
    send(".\r\n");
    System.out.println("SERVER: " + br.readLine());

    send("QUIT\r\n");
    System.out.println("SERVER: " + br.readLine());

  }

  private static void send(String s) throws Exception {
    dos.writeBytes(s);
    Thread.sleep(1000);
    System.out.println("CLIENT: " + s);
  }
}