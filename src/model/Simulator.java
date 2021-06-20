package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class Simulator {

    public void flightgear() throws IOException, InterruptedException {
        Socket fg=new Socket("localhost", 5400);
        BufferedReader in=new BufferedReader(new FileReader("reg_flight.csv"));
        PrintWriter out=new PrintWriter(fg.getOutputStream());
        String line;
        while((line=in.readLine())!=null) {
            out.println(line);
            out.flush();
            Thread.sleep(100);
        }
        out.close();
        in.close();
        fg.close();
    }
}
