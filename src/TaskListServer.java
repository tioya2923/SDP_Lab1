import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;


public class TaskListServer {
    public static void main(String[] args) throws IOException {

        DataOutputStream out;
        DataInputStream in;

        ArrayList<String> tasks = new ArrayList();

        ServerSocket skServer = new ServerSocket(5000);
        Socket client = skServer.accept(); //Server accepts the connection with the client

        while (true) {
            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());

            String clientMessage = in.readUTF(); //Server reads the message from client
            System.out.println(clientMessage);
            switch (clientMessage){
                case "L":
                    String showList = Arrays.toString(tasks.toArray());
                    out.writeUTF(showList);
                    break;
                case "R":
                    String newTask = in.readUTF();
                    if (newTask.length() <= 120){
                        tasks.add(newTask);
                        out.writeUTF("Task added succesfully");
                    } else {
                        out.writeUTF("Caracters cannot be over than 120");
                    }
                    break;
                case "Q":
                    out.close();
                    in.close();
                    client.close();
                    break;
                default:
                    out.writeUTF("This option is not valid");
            }
            if (clientMessage.equals("Q")){ //Needed to quit the while loop and finish without exceptions erros
                //System.out.println("Server is closed");
                break;
            }
        }
    }
}