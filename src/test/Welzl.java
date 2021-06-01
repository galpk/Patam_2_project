package test;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
/*
public class Welzl  implements  TimeSeriesAnomalyDetector{
    class Circle
    {
      Point C;
      double Radius;
    };

    @Override
    public void learnNormal(TimeSeries ts) {


    }
    public double dist(final Point a ,final Point b)
    {
        return Math.sqrt(Math.pow(a.x - b.x,2)+Math.pow(a.y - b.y,2));
    }
    boolean is_inside_the_circle(final Circle c, final Point p )
    {
        return dist(c.C , p) <=c.Radius;
    }
    Circle smallest_circle (Circle circle ,final Point A , final Point B){
        Point cir = new Point((A.x + B.y)/2,(A.y+B.y)/2);
       circle.C =cir;
       circle.Radius = dist(A, B)/2;

       return circle;
    }
    boolean valid_circle(final Circle c , final Vector<Point> P)
    {
        for (final Point p :P)
        {
            if(!is_inside_the_circle(c,p))
                return false;
        }
        return true;
    }
    Circle welzl_help (Vector<Point> P1 , Vector<Point>P2  ,int n)
    {
        if(n==0|| P2.size() ==3)
        {
            return  valid_circle(P1);
        }
        int idx = (int)(Math.random()%n);
        Point p = P1.get(idx);

        Collections.swap(P1.get(idx),P1.get()[n-1]);

        Circle d = welzl_help(P1,P2,n-1);
        if (!is_inside_the_circle(d,p)){
            return d;
        }
        P2.add(p);
        return welzl_help(P1,P2,n-1);
    }

    Circle welzl(const Vector<Point> R){
        Vector<Point> R_copy = R;
       // Collections.swap(R_copy.firstElement(),R_copy.lastElement()););
    }

    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        return null;
    }


}
*/