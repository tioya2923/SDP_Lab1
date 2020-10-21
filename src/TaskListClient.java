import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class TaskListClient {
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        DataOutputStream out;
        DataInputStream in;

        Socket skClient = new Socket("127.0.0.1", 5000);


        while(true){
            out = new DataOutputStream(skClient.getOutputStream());
            in = new DataInputStream(skClient.getInputStream());

            System.out.println("You are the first client, please send a message to the server");
            String clientMessage = scan.nextLine();

            switch (clientMessage){
                case "L":
                    out.writeUTF("L");
                    break;
              /*  case "R":
                    out.writeUTF("R");
                    String newTask = scan.nextLine();
                    out.writeUTF(newTask);
                    break;*/
                case "Q":
                    out.writeUTF("Q");
                    out.close();
                    in.close();
                    skClient.close();
                    skClient = null;
                    break;
                default:
                    out.writeUTF(clientMessage);
            }

            if (skClient != null){
                System.out.println("Below you can see the message from the server");
                String serverMessage = in.readUTF();
                System.out.println(serverMessage);
            } else {
                break; //Needed to quit the while loop and finish without exception erros
            }
        }
    }
}