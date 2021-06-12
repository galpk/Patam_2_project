package Model;

import test.AnomalyReport;
import test.TimeSeries;
import test.TimeSeriesAnomalyDetector;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Observable;

public class model extends Observable implements TimeSeriesAnomalyDetector {
    private int throttle;

    public int getThrottle() {
        return throttle;
    }

    public void setThrottle(int throttle) {
        this.throttle = throttle;
    }

    public void Read(){
        throttle=7;
        setChanged();
        notifyObservers();

    }

    @Override
    public void learnNormal(TimeSeries ts) {

    }

    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        return null;
    }

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
