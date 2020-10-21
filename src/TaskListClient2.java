import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TaskListClient2 {
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        DataOutputStream out2;
        DataInputStream in2;

        Socket skClient2 = new Socket("127.0.0.1", 5500);


        while(true){
            out2 = new DataOutputStream(skClient2.getOutputStream());
            in2 = new DataInputStream(skClient2.getInputStream());

            System.out.println("You are the second client, please send a message to the server");
            String client2Message = scan.nextLine();

            switch (client2Message){
               /* case "L":
                    out.writeUTF("L");
                    break;*/
                case "R":
                    out2.writeUTF("R");
                    String newTask = scan.nextLine();
                    out2.writeUTF(newTask);
                    break;
                case "Q":
                  //  out.writeUTF("Q");
                    out2.close();
                    in2.close();
                    skClient2.close();
                    skClient2 = null;
                    break;
                default:
                    out2.writeUTF(client2Message);
            }

            if (skClient2 != null){
                System.out.println("Below you can see the message from the server");
                String server2Message = in2.readUTF();
                System.out.println(server2Message);
            } else {
                break; //Needed to quit the while loop and finish without exception erros
            }
        }
    }
}
