package PatternRecognition;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private LineSegment[] segmentsResult;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int n = points.length;
        for (int k = 0; k < n; k++) {
            if (points[k] == null) {
                throw new IllegalArgumentException("array contains null point");
            }
            for (int j = k + 1; j < n; j++) {
                if (points[k].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("array contains"
                            + " a repeated point");
                }
            }
        }

        List<LineSegment> segments = new ArrayList<>();
        List<Point> lines = new ArrayList<>();
        List<Double> slopes = new ArrayList<>();
        Point[] tempPointsList = points.clone();
        Point[] pointsList = points.clone();
        Arrays.sort(pointsList);
        List<Point> cpoints = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            Arrays.sort(tempPointsList, pointsList[i].slopeOrder());


            for (int j = 1; j < n - 1; j++) {
                {

                    double slope = tempPointsList[0].slopeTo(tempPointsList[j]);
                    if (slopes.contains(slope) && lines.contains(tempPointsList[j])) {
                        continue;
                    }

                    int l = j;
                    int m = 0;
                    int p = j + 1;

                    while ((p < n) && (slope == tempPointsList[0].slopeTo(tempPointsList[p]))) {

                        cpoints.add(tempPointsList[l]);


                        l++;
                        m++;
                        p++;

                    }

                    if (m > 1) {
                        cpoints.add(tempPointsList[0]);
                        cpoints.add(tempPointsList[p - 1]);
                        lines.addAll(cpoints);


                        Point arr[] = new Point[cpoints.size()];
                        arr = cpoints.toArray(arr);
                        Arrays.sort(arr);

                        segments.add(new LineSegment(pointsList[i], arr[arr.length - 1]));
                        slopes.add(slope);
                        cpoints.clear();

                    }

                    else  {
                        cpoints.clear();}

                    j = l;
                }
            }


        }

        segmentsResult = segments.toArray(new LineSegment[segments.size()]);
    }

    public int numberOfSegments() {
        return segmentsResult.length;


    }

    public LineSegment[] segments() {

        return segmentsResult.clone();

    }


    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
