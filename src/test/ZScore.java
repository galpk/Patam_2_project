package test;


import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ZScore implements TimeSeriesAnomalyDetector
{

    public static class MaxCol
    {
        public String Colname;
        float MaxNum;
        public MaxCol(String name , Float num)
        {
            this.Colname = name;
            this.MaxNum = num;
        }

    }
    private List<MaxCol> ThresholdList ;
    @Override
    public void learnNormal(TimeSeries ts)
    {
        ThresholdList = new ArrayList<>();
        MaxCol mc;

        for (int i=0; i < ts.Dtable.size(); i++)
        {
            float zs , max=-999;
            float avg = StatLib.avg(ts.help(ts.Dtable.get(i).Vlist));
            float var = (float)Math.sqrt(StatLib.var(ts.help(ts.Dtable.get(i).Vlist)));
            for (int j = 0; j < ts.Dtable.get(i).Vlist.size(); j++)
            {
                if(var == 0)
                {
                    zs =0;
                }
                else
                {
                    zs = Math.abs(ts.Dtable.get(i).Vlist.get(j) - avg)/var;
                }

                if (zs > max)
                {
                    max =zs;
                }
            }
        mc = new MaxCol(ts.Dtable.get(i).Fname,max);
        ThresholdList.add(mc);
        }
    }

    @Override
    public List<AnomalyReport> detect(TimeSeries ts)
    {
        List<AnomalyReport> DetectListReport = new ArrayList<>();

        for (int i=0; i < ts.Dtable.size(); i++)
        {
            float zs , max=-999;
            float avg = StatLib.avg(ts.help(ts.Dtable.get(i).Vlist));
            float var = (float)Math.sqrt(StatLib.var(ts.help(ts.Dtable.get(i).Vlist)));
            for (int j = 0; j < ts.Dtable.get(i).Vlist.size(); j++)
            {
                if (var !=0)
                {
                    for (int k = 0 ; k < j; k++)
                    {
                        zs = Math.abs(ts.Dtable.get(i).Vlist.get(k) - avg)/var;
                        if (zs >= ThresholdList.get(i).MaxNum)
                        {
                                AnomalyReport anomReport = new AnomalyReport(ts.Dtable.get(k).Fname,(long)zs);
                                DetectListReport.add(anomReport);
                        }
                    }
                }
            }

        }
        return  DetectListReport;
    }


}

