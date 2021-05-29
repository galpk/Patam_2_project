package test;




public class StatLib{


	// simple average
	public static float avg(float[] x){
		if(x.length == 0)
		{
			return 0;
		}
		float sum = 0;
		for(int  i =0; i < x.length ; i++)
		{
			sum = sum + x[i];
		}
		return sum/x.length;
	}

	// returns the variance of X and Y
	public static float var(float[] x){
		float var = 0;
		for(int  i =0; i < x.length ; i++)
		{
			var += Math.pow(x[i] - avg(x) , 2);
		}
		var = var/x.length;
		return var;

	}

	// returns the covariance of X and Y
	public static float cov(float[] x, float[] y){

		float sum = 0;
		for(int i=0; i < x.length; i++) {
			sum+=(x[i]-avg(x))*(y[i]-avg(y));
		}
		return sum/x.length;
	}


	// returns the Pearson correlation coefficient of X and Y
	public static float pearson(float[] x, float[] y){

		return (cov(x, y))/ (float)Math.sqrt(var(x)*var(y));

	}

	// performs a linear regression and returns the line equation
	public static Line linear_reg(Point[] points){

		float[] x = new float[points.length] ;
		float[] y = new float[points.length] ;

		for(int i = 0; i < x.length; i++)
		{
			x[i] = points[i].x;
			y[i] = points[i].y;

		}
		float a = (cov(x, y))/var(x);
		float b = avg(y) - (a*avg(x));
		Line g = new Line(a, b);

		return g;

	}

	// returns the deviation between point p and the line equation of the points
	public static float dev(Point p,Point[] points){
		float a,b;
		a = linear_reg(points).a ;
		b = linear_reg(points).b ;
		Line g = new Line (a,b) ;
		return Math.abs(g.f(p.x) - p.y);
	}

	// returns the deviation between point p and the line
	public static float dev(Point p,Line l){
		return Math.abs(l.f(p.x) - p.y);
	}

}

