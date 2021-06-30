package model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeSeries implements Serializable {
	private HashMap<String, ArrayList<Float>> Map;
	private ArrayList<String> Features;
	private int sizeFeatures;
	private int sizeArrayList;

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
	public ArrayList<String> getFeatures()
	{
		return (this.Features);
	}
	public void setFeatures(String s, int i)
	{
		this.Features.set(i, s);
	}
	public void setFeatures(ArrayList<String> arr) { // new method 18.4.21

		this.Features = arr;
	}
	public HashMap<String, ArrayList<Float>> getHashMap()
	{
		return (this.Map);
	}
	public void setHashMap(HashMap<String, ArrayList<Float>> hm)
	{
		this.Map = hm;
	}
	public int getsizefFeatures()
	{
		return Features.size();
	}
	public void setsizeFeatures(int size)
	{
		this.sizeFeatures = size;
	}
	public int getsizeArrayList()
	{
		String f = this.Features.get(0);
		int i = 0;
		return (this.Map.get(f).size());

	}
	public void setsizeArrayList(int size)
	{
		this.sizeArrayList = size;
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

