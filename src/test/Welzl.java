package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Welzl {
    private Random rand = new Random();

    public test.Circle naiveAlgorithm(final List<test.Point> points) {
        // One point = nul circle
        if (points.size() == 1) {
            return new test.Circle(points.get(0).x, points.get(0).y, 0.0);
        }

        test.Circle minimumCircle = null;

        // General case
        for (final Point p : points) {
            for (final Point q : points) {
                if (p != q) {
                    minimumCircle = new test.Circle(p, q);
                    if (minimumCircle.containsAllPoints(points)) {
                        return minimumCircle;
                    }
                }
            }
        }

        // Triangle case
        minimumCircle = new test.Circle(0.0, 0.0, Double.MAX_VALUE);
        for (final Point p : points) {
            for (final Point q : points) {
                for (final Point r : points) {
                    if (p != q && q != r && p != r && !Point.areColinear(p, q, r)) {
                        test.Circle circumscribedCircle = new test.Circle(p, q, r);
                        if (circumscribedCircle.radius < minimumCircle.radius && circumscribedCircle.containsAllPoints(points)) {
                            minimumCircle = circumscribedCircle;
                        }
                    }
                }
            }
        }

        return minimumCircle;
    }

    public test.Circle minidisk(final List<Point> points) {
        return bMinidisk(points, new ArrayList<Point>());
    }

    private test.Circle bMinidisk(final List<Point> points, final List<Point> boundary) {
        test.Circle minimumCircle = null;

        if (boundary.size() == 3) {
            minimumCircle = new test.Circle(boundary.get(0), boundary.get(1), boundary.get(2));
        }
        else if (points.isEmpty() && boundary.size() == 2) {
            minimumCircle = new test.Circle(boundary.get(0), boundary.get(1));
        }
        else if (points.size() == 1 && boundary.isEmpty()) {
            minimumCircle = new test.Circle(points.get(0).x, points.get(0).y, 0.0);
        }
        else if (points.size() == 1 && boundary.size() == 1) {
            minimumCircle = new test.Circle(points.get(0), boundary.get(0));
        }
        else {
            Point p = points.remove(rand.nextInt(points.size()));
            minimumCircle = bMinidisk(points, boundary);

            if (minimumCircle != null && !minimumCircle.containsPoint(p)) {
                boundary.add(p);
                minimumCircle = bMinidisk(points, boundary);
                boundary.remove(p);
                points.add(p);
            }
        }

        return minimumCircle;
    }
}