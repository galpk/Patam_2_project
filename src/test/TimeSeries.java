package test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TimeSeries
{
	public static class ColData
	{
		public String Fname;
		public List<Float> Vlist;
		public ColData(String name)
		{
			this.Fname = name;
			this.Vlist = new ArrayList<Float>();
		}
	}
	List<ColData> Dtable = new ArrayList<>();

	public List<ColData> getDtable() {
		return Dtable;
	}

	public void addRow(String[] s)
	{
		for (int i = 0; i < this.Dtable.size(); i++)
		{
			this.Dtable.get(i).Vlist.add(Float.parseFloat(s[i]));

		}

	}
	public void SetcorrelationThresh(double correlationThresh){
		this.correlationThresh =correlationThresh;
	}
	public double correlationThresh = 0.9;

	public  TimeSeries(String csvFileName)
	{
		try {
			String line = null;
			BufferedReader bur = new BufferedReader(new FileReader(csvFileName));
			if((line = bur.readLine())!= null) {
				String[] s = line.split(",");
				for(int i = 0 ; i < s.length; i++) {
					ColData tempfeature = new ColData(s[i]);
					this.Dtable.add(tempfeature);
				}
			}
			while((line = bur.readLine())!= null) {
				String[] CurrentL = line.split(",");
				addRow(CurrentL);
			}
		}
		catch(IOException e) {
			e.printStackTrace();

		}


	}

	public float[] help(List<Float> l) {
	float[] f = new float [l.size()];
	int count =0;
	for(float element :l) {
		f[count] = element;
		count++;
	}
	return f;

}
	public Point[] arrtopoint(List<Float> l1 , List<Float> l2) {
		Point[] p = new Point [l1.size()];
		for(int i = 0; i<l1.size(); i++) {
			p[i] = new Point(l1.get(i), l2.get(i));
		}
		return p;
	}



}

