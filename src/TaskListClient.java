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

            if (clientMessage.equalsIgnoreCase("L")){
                out.writeUTF("L");
            } else if (clientMessage.equalsIgnoreCase("R")){
                out.writeUTF("R");
                String newTask = scan.nextLine();
                /*if (newTask.length() > 120){
                    System.out.println("Texto largo");
                } else {
                    out.writeUTF(newTask);
                }*/
                out.writeUTF(newTask);
            } else if (clientMessage.equalsIgnoreCase("Q")){
                out.writeUTF("Q");
                out.close();
                in.close();
                skClient.close();
            } else {
                out.writeUTF(clientMessage);
            }

            System.out.println("Below you can see the message from the server");
            String serverMessage = in.readUTF();
            System.out.println(serverMessage);

        }
    }
}