 package test;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Welzl1 implements  TimeSeriesAnomalyDetector{
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
    Point get_center_of_circle(double bx,double by, double cx , double cy){
        double B = bx * bx + by * by;
        double C = cx *cx + cy * cy;
        double D = bx * cy + by * cx ;
        Point p= new Point(0,0) ;
        p.x = (float)((bx *C - cx * B)/(2*D));
        p.y = (float)(( cy * B - by * C)/(2*D));
        return p;
    }
    Circle circle_from_3Points( final Point A , final Point B , final Point C){
        Circle c =new Circle();
        Point I = get_center_of_circle(B.x - A.x, B.y - A.y,C.x - A.x,C.y - A.y);
        I.x = A.x;
        I.y = A.y;
        c.C = I;
        c.Radius = dist(I,A);
        return c ;
    }
    Circle circle_from_2Points (final Point A , final Point B){
       Circle circle =null;
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
    Circle min_circle(Vector<Point> P){
        Circle circle = null;

        assert (P.size() <= 3);
        if(P.isEmpty()) {
            circle.C= new Point (0,0);
            circle.Radius = 0;
            return circle;
        }
        else if(P.size() ==1){
            circle.C = P.get(0);
            circle.Radius = 0;
            return circle;
            }
        else if(P.size() ==2){
            return circle_from_2Points(P.get(0),P.get(1));
        }
        for (int i = 0; i< 3; i++){
            for (int j = i + 1; j < 3; j++ ){
                Circle c = circle_from_2Points(P.get(i), P.get(j));
                if(valid_circle(c,P)){
                    return c;
                }
            }
        }
        return  circle_from_3Points(P.get(0), P.get(1),P.get(2));
    }
    Circle welzl_help (Vector<Point> P1 , Vector<Point>P2  ,int n)
    {
        if(n==0|| P2.size() ==3)
        {
            return  min_circle(P2);
        }
        int idx = (int)(Math.random()%n);
        Point p = P1.get(idx);

        Collections.swap(P1,idx,n-1);

        Circle d = welzl_help(P1,P2,n-1);
        if (is_inside_the_circle(d,p)){
            return d;
        }
        P2.add(p);
        return welzl_help(P1,P2,n-1);
    }

    Circle welzl(final Vector<Point> point){
        Vector<Point> point_copy = point;
       Collections.swap(point_copy,point_copy.indexOf(point_copy,0),point_copy.lastIndexOf(point_copy));;
        return welzl_help(point_copy,null,point_copy.size());
    }

    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        return null;
    }


}
