package model;

import java.util.List;

public class Circle {
    private static final double MULTIPLICATIVE_EPSILON = 1 + 1e-14;
    public Point center;
    public double radius;

    public Circle(final Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }
    public boolean contains(Point p) {
        return center.distanceTo(p) <= radius * MULTIPLICATIVE_EPSILON;
    }

    public Circle(double x, double y, double radius) {
        center = new Point(x, y);
        this.radius = radius;
    }

    public Circle(final Point p1, final Point p2) {
        center = new Point((p1.x + p2.x) * 0.5, (p1.y + p2.y) * 0.5);
        radius = center.distanceTo(p1);
    }

    public Circle(final Point p1, final Point p2, final Point p3) {
        final double P2_MINUS_P1_Y = p2.y - p1.y;
        final double P3_MINUS_P2_Y =  p3.y - p2.y;

        if (P2_MINUS_P1_Y == 0.0 || P3_MINUS_P2_Y == 0.0) {
            center = new Point(0.0, 0.0);
            radius = 0.0;
        }
        else {
            final double A = -(p2.x - p1.x) / P2_MINUS_P1_Y;
            final double A_PRIME = -(p3.x - p2.x) / P3_MINUS_P2_Y;
            final double A_PRIME_MINUS_A = A_PRIME - A;

            if (A_PRIME_MINUS_A == 0.0) {
                center = new Point(0.0, 0.0);
                radius = 0.0;
            }
            else {
                final double P2_SQUARED_X = p2.x * p2.x;
                final double P2_SQUARED_Y = p2.y * p2.y;


                final double B = (P2_SQUARED_X - p1.x * p1.x + P2_SQUARED_Y - p1.y * p1.y) /
                        (2.0 * P2_MINUS_P1_Y);
                final double B_PRIME = (p3.x * p3.x - P2_SQUARED_X + p3.y * p3.y - P2_SQUARED_Y) /
                        (2.0 * P3_MINUS_P2_Y);


                final double XC = (B - B_PRIME) / A_PRIME_MINUS_A;
                final double YC = A * XC + B;

                final double DXC = p1.x - XC;
                final double DYC = p1.y - YC;

                center = new Point(XC, YC);
                radius = Math.sqrt(DXC * DXC + DYC * DYC);
            }
        }
    }

    public boolean containsAllPoints(final List<Point> points2d) {
        for (final Point p : points2d) {
            if (p != center && !containsPoint(p)) {
                return false;
            }
        }

        return true;
    }

    public boolean containsPoint(final Point p) {
        return p.distanceSquaredTo(center) <= radius * radius;
    }

    @Override
    public String toString() {
        return center.toString()  +  ", Radius: " + radius;
    }
}