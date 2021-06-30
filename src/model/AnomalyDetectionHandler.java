package model;

import model.Commands.DefaultIO;
import model.Server.ClientHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class AnomalyDetectionHandler implements ClientHandler {

    Scanner in;
    PrintWriter out;
    public class SocketIO implements DefaultIO
    {
        @Override
        public String readText() {
            return in.nextLine();
        }
        @Override
        public void write(String text) {
            out.print(text);
        }
        @Override
        public float readVal() {
            return in.nextFloat();
        }
        @Override
        public void write(float val) {
            out.print(val);
        }
    }

    @Override
    public void Com(InputStream in, OutputStream out)
    {
        this.in = new Scanner(in);
        this.out = new PrintWriter(out);
        SocketIO socket = new SocketIO();
        CLI client = new CLI(socket);
        client.start();
        this.out.println("Goodbye");
        this.out.close();
        this.in.close();
    }
}

