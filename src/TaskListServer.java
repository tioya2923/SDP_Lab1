import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;


public class TaskListServer {
    public static void main(String[] args) throws IOException {
        ArrayList<String> tasks = new ArrayList();
        ServerSocket skServer = new ServerSocket(5000);

        while (true) {
            Socket client = null;
            try {
                client = skServer.accept(); //Server accepts the connection with the client
                System.out.println("A new client is connected: " + client);
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                DataInputStream in = new DataInputStream(client.getInputStream());
                //Assigning new thread for this client
                Thread newThread = new ClientHandler(client, in, out);
                newThread.start();

            } catch (IOException e) {
                client.close();
                e.printStackTrace();
            }
        }
    }
}

class ClientHandler extends Thread {
    DataInputStream dataIn;
    DataOutputStream dataOut;
    Socket socket;

    public ClientHandler(Socket socket, DataInputStream dataIn, DataOutputStream dataOut) {
        this.socket = socket;
        this.dataIn = dataIn;
        this.dataOut = dataOut;
    }

    @Override
    public void run (){
        String clientMessage = null;
        ArrayList<String> tasks = new ArrayList();

        while(true){
            try{
                clientMessage = dataIn.readUTF();
                switch (clientMessage){
                    case "L":
                        String showList = Arrays.toString(tasks.toArray());
                        dataOut.writeUTF(showList);
                        break;
                    case "R":
                        String newTask = dataIn.readUTF();
                        if (newTask.length() <= 120){
                            tasks.add(newTask);
                            dataOut.writeUTF("Task added succesfully");
                        } else {
                            dataOut.writeUTF("Caracters cannot be over than 120");
                        }
                        break;
                    case "Q":
                        this.dataOut.close();
                        this.dataIn.close();
                        this.socket.close();
                        break;
                    default:
                        dataOut.writeUTF("This option is not valid");
                }

            } catch (IOException e) {
                e.printStackTrace();

            }
            if (clientMessage.equals("Q")){ //Needed to quit the while loop and finish without exceptions erros
                //System.out.println("Server is closed");
                break;
            }
        }
    }
}