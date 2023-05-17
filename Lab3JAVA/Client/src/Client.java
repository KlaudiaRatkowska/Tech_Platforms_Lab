import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String args[])
    {
        try
        {
            Socket socket = new Socket("localhost", 1234);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            int n;

            System.out.println("Server connected");

            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();


            System.out.println("Type number of messages");
            n = scanner.nextInt();

            oos.writeInt(n);
            oos.flush();

            System.out.println("Write messages");
            oos.flush();

            for(int i=0; i<n; i++)
            {
                String content = scanner.next();
                content += " ";
                content += name;
                Message msg = new Message(i, content);
                oos.writeObject(msg);
                oos.flush();
            }

            String isFinished;
            do
            {
                isFinished = (String) ois.readObject();
            }
            while(!isFinished.equals("finished"));

            System.out.println("Server finished");
            oos.flush();

            oos.close();
            ois.close();
            socket.close();
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
}