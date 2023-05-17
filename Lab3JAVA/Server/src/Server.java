import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String args[])
    {
        try (ServerSocket server = new ServerSocket(1234))
        {
            Socket socket;
            for(int i=0; true; i++)
            {
                socket = server.accept();
                Handler handler = new Handler(socket);
                Thread thread = new Thread(handler);
                System.out.println("Client connected");
                thread.start();
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}