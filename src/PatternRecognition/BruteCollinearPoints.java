package PatternRecognition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private LineSegment[] ls;


    public BruteCollinearPoints(Point[] points) {
        
        if (points == null) throw new IllegalArgumentException("argument is null");
        int n = points.length;
        for (int i = 0; i < n; i++) {
            if (points[i] == null) throw new IllegalArgumentException("array contains null point");
            for (int j = i + 1; j < n; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("array contains a repeated point");
            }
        }
        Point[] ps = points.clone();
        Arrays.sort(ps);
        List<LineSegment> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point[] p = new Point[4];
                        p[0] = ps[i];
                        p[1] = ps[j];
                        p[2] = ps[k];
                        p[3] = ps[l];
                        double s1 = p[0].slopeTo(p[1]);
                        double s2 = p[0].slopeTo(p[2]);
                        if (s1 != s2) continue;
                        double s3 = p[0].slopeTo(p[3]);
                        if (s1 == s3) {
                            Arrays.sort(p);
                            list.add(new LineSegment(p[0], p[3]));
                        }
                    }
                }
            }
        }

        ls = list.toArray(new LineSegment[list.size()]);
    }

    /**
     * the number of line segments
     *
     * @return
     */
    public int numberOfSegments() {
        return ls.length;
    }

    /**
     * the line segments
     *
     * @return
     */
    public LineSegment[] segments() {
        return ls.clone();
    }
}