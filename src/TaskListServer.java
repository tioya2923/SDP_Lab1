import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;


public class TaskListServer {
    public static void main(String[] args) throws IOException {

        DataOutputStream out;
        DataInputStream in;
        DataOutputStream out2;
        DataInputStream in2;

        ArrayList<String> tasks = new ArrayList();

        ServerSocket skServer = new ServerSocket(5000);
        Socket client = skServer.accept(); //Server accepts the connection with the client

        ServerSocket skServer2 = new ServerSocket(5500);
        Socket client2 = skServer2.accept();

        while (true) {
            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());

            out2 = new DataOutputStream(client2.getOutputStream());
            in2 = new DataInputStream(client2.getInputStream());

            String clientMessage = in.readUTF(); //Server reads the message from client
            String client2Message = in2.readUTF();

            if (clientMessage != null){
                System.out.println(clientMessage);
                switch (clientMessage){
                    case "L":
                        String showList = Arrays.toString(tasks.toArray());
                        out.writeUTF(showList);
                        break;
                    /*case "R":
                        String newTask = in.readUTF();
                        if (newTask.length() <= 120){
                            tasks.add(newTask);
                            out.writeUTF("Task added succesfully");
                        } else {
                            out.writeUTF("Caracters cannot be over than 120");
                        }
                        break;*/
                    case "Q":
                        out.close();
                        in.close();
                        client.close();
                        break;
                    default:
                        out.writeUTF("This option is not valid");
                }
                if (clientMessage.equals("Q")){ //Needed to quit the while loop and finish without exception erros
                    //System.out.println("Server is closed");
                    break;
                }
            } else {
                System.out.println(client2Message);
                switch (client2Message){
                    /*case "L":
                        String showList = Arrays.toString(tasks.toArray());
                        out.writeUTF(showList);
                        break;*/
                    case "R":
                        String newTask = in2.readUTF();
                        if (newTask.length() <= 120){
                            tasks.add(newTask);
                            out2.writeUTF("Task added succesfully");
                        } else {
                            out2.writeUTF("Caracters cannot be over than 120");
                        }
                        break;
                    case "Q":
                        out2.close();
                        in2.close();
                        client2.close();
                        break;
                    default:
                        out2.writeUTF("This option is not valid");
                }
                if (client2Message.equals("Q")){ //Needed to quit the while loop and finish without exception erros
                    //System.out.println("Server is closed");
                    break;
                }
            }

        }
    }
}