package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SimpleAnomalyDetector implements TimeSeriesAnomalyDetector {

	private float threshold = 0.9f;
	public HashMap<Integer, Float> anomalymap;
	private List<CorrelatedFeatures> QW = new ArrayList<>();
	float CuCor;
	int index = 0;
	private List<CorrelatedFeatures> listCF;
	public SimpleAnomalyDetector(TimeSeries ts) {

		this.setThreshold((float)0.9);
		this.learnNormal(ts);
		this.anomalymap = new HashMap<>();
	}
	public SimpleAnomalyDetector(TimeSeries ts, float tr) {

		this.setThreshold(tr);
		this.learnNormal(ts);
		this.anomalymap = new HashMap<>();
	}
	@Override
	public void learnNormal(TimeSeries ts)
	{
		for(int i= 0; i<ts.Dtable.size();i++) {
			float highCor = 0;

			for( int j = i + 1; j<ts.Dtable.size();j++) {
				float count = StatLib.pearson(ts.help(ts.Dtable.get(i).Vlist), ts.help(ts.Dtable.get(j).Vlist));
				CuCor = Math.abs(count);
				if(CuCor >= highCor) {
					highCor =CuCor;
					index = j;
				}
			}
			if (highCor >= threshold) {
				Point[] p = ts.arrtopoint(ts.Dtable.get(i).Vlist, ts.Dtable.get(index).Vlist);
				float highP = 0;
				for(int k = 0; k < p.length; k++)
				{
					if(StatLib.dev(p[k], p)> highP)
					{
						highP = StatLib.dev(p[k], p);
					}

				}
				CorrelatedFeatures cf = new CorrelatedFeatures(ts.Dtable.get(i).Fname, ts.Dtable.get(index).Fname, highCor, StatLib.linear_reg(p), (highP*1.1f));
				this.QW.add(cf);
			}

		}
	}

	public static int GetFeaIndex(String Fname2 , TimeSeries ts) {

		for(int i= 0; i<ts.Dtable.size();i++) {
			if(Fname2.equals(ts.Dtable.get(i).Fname)) {
				return i;
			}
		}
		return 0;
	}


	//
	@Override
	public List<AnomalyReport> detect(TimeSeries ts) {


		List<AnomalyReport> ReportList = new ArrayList<>();
		for(int i= 0; i<this.QW.size();i++)
		{
			int F1Index = GetFeaIndex(this.QW.get(i).feature1,ts);
			int F2Index = GetFeaIndex(this.QW.get(i).feature2,ts);
			Point[] pArray = ts.arrtopoint(ts.Dtable.get(F1Index).Vlist, ts.Dtable.get(F2Index).Vlist);
			for(int j= 0; j < pArray.length;j++)
			{
				if(StatLib.dev(pArray[j], this.QW.get(i).lin_reg) > this.QW.get(i).threshold)
				{
					AnomalyReport UpReport = new AnomalyReport((this.QW.get(i).feature1 +"-"+ this.QW.get(i).feature2), j+1);
					ReportList.add(UpReport);
				}
			}
		}
		return ReportList;
	}

	public List<CorrelatedFeatures> getNormalModel(){
		return QW;
	}
	public List<CorrelatedFeatures> getList() {
		return listCF;
	}
	public float getThreshold() {
		return threshold;
	}


	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}
}