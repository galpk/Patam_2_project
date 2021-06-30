package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LineRegHelp {

    public String f1;
    public String f2;
    public long timeStep;
    public float value;

    public  LineRegHelp(){

    }

    public LineRegHelp readAnomolay(AnomalyReport a, TimeSeries ts){

        LineRegHelp lin = new LineRegHelp();
        Scanner in = new Scanner(a.description).useDelimiter("-");
        LineRegHelp b = new LineRegHelp();
        b.f1=in.next();
        b.f2 = in.next();
        b.timeStep = a.timeStep;
        b.value = ts.getHashMap().get(b.f1).get((int) timeStep);

        return lin;
    }

    public List<LineRegHelp> makeList(List<AnomalyReport> listar, TimeSeries ts) {

        List<LineRegHelp> list = new ArrayList<LineRegHelp>();
        for(int i=0; i<listar.size(); i++){
            LineRegHelp lin = readAnomolay(listar.get(i), ts) ;
        }
        return list;
    }

    public int checkIfExist(List<LineRegHelp> list, String name){

        for(int i=0; i<list.size(); i++){

            if(list.get(i).f1.equals(name) || list.get(i).f2.equals(name)){
                return i;
            }

        }

        return -1;
    }
}
