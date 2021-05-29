package test;

import java.util.List;

public class Hybrid implements TimeSeriesAnomalyDetector{


    @Override
    public void learnNormal(TimeSeries ts) {
        System.out.println("");
    }

    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        return null;
    }
}
