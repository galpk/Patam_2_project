package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {
    int AmountOfClients;

    public interface ClientHandler{
        public void Com(InputStream in , OutputStream out);
    }
    volatile boolean Stop;

    public Server(){
        Stop = false;
    }
    private void startServer(int port,ClientHandler clientHandler)
    {
        try
        {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(1000);
            while (!Stop)
            {
                try {
                    Socket socket = server.accept();
                    clientHandler.Com(socket.getInputStream(), socket.getOutputStream());
                }
                catch (SocketTimeoutException e){}
            }
            server.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start(int port, ClientHandler clientHandler)
    {
        new Thread(()->startServer(port, clientHandler)).start();
    }
    public void stop()
    {
        Stop = true;
    }
}
