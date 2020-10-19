import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class TaskListClient {
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        final String localhost = "127.0.0.1";
        final int port = 5000;
        DataOutputStream out;
        DataInputStream in;

        Socket skClient = new Socket(localhost, port);


        while(true){
            out = new DataOutputStream(skClient.getOutputStream());
            in = new DataInputStream(skClient.getInputStream());

            System.out.println("You are the client, please send a message to the server");
            String clientMessage = scan.nextLine();

            switch (clientMessage){
                case "L":
                    out.writeUTF("L");
                    break;
                case "R":
                    out.writeUTF("R");
                    String newTask = scan.nextLine();
                    out.writeUTF(newTask);
                    break;
                case "Q":
                    out.writeUTF("Q");
                    //in.readUTF();
                    out.close();
                    in.close();
                    skClient.close();
                    break;
                default:
                    out.writeUTF(clientMessage);
            }

            System.out.println("Below you can see the message from the server");
            String serverMessage = in.readUTF();
            System.out.println(serverMessage);

        }
    }
}