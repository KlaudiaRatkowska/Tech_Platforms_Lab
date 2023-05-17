import java.io.*;
import java.io.ObjectInputStream;
import java.net.*;

public class Handler implements Runnable {

    private final Socket clientSocket;

    public Handler(Socket s)
    {
        this.clientSocket = s;
    }

    @Override
    public void run()
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());;
            oos.writeObject("ready");
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            int n = ois.readInt();
            oos.writeObject("ready for messages");
            oos.flush();

            for(int i=0; i<n; i++)
            {
                Message msg=(Message)ois.readObject();
                int m = msg.getNumber();
                m++;
                System.out.println(m + " - " + msg.getContent());
            }

            oos.writeObject("finished");
            oos.flush();
            oos.close();
            ois.close();
            clientSocket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}