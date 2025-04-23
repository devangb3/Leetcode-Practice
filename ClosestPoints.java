package leetcodepractice.src.com.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClosestPoints {

    /** Public API: returns a 2×2 array {{x1,y1},{x2,y2}} of the closest pair. */
    public int[][] findClosestPoints(int[][] points) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("At least two points are required");
        }
        // Make copies sorted by X and by Y
        int[][] xSorted = Arrays.copyOf(points, points.length);
        Arrays.sort(xSorted, Comparator.comparingInt(a -> a[0]));

        int[][] ySorted = Arrays.copyOf(points, points.length);
        Arrays.sort(ySorted, Comparator.comparingInt(a -> a[1]));

        // Recurse
        Result res = closestRec(xSorted, ySorted, 0, points.length);
        return new int[][] { res.p1, res.p2 };
    }

    /** Holds a candidate closest‑pair and its distance. */
    private static class Result {
        int[] p1, p2;
        double dist;
        Result(int[] a, int[] b) {
            this.p1 = a;
            this.p2 = b;
            this.dist = euclid(a, b);
        }
        Result(int[] a, int[] b, double d) {
            this.p1 = a;
            this.p2 = b;
            this.dist = d;
        }
    }

    /** Recursive D&C on xSorted[lo..hi) and full ySorted. */
    private Result closestRec(int[][] xSorted, int[][] ySorted, int lo, int hi) {
        int n = hi - lo;
        // Base case: brute force for ≤3 points
        if (n <= 3) {
            return bruteForce(xSorted, lo, hi);
        }

        int mid = lo + n/2;
        int[] midPoint = xSorted[mid];

        // Partition ySorted into leftY/rightY in O(n)
        Set<int[]> leftSet = new HashSet<>();
        for (int i = lo; i < mid; i++) {
            leftSet.add(xSorted[i]);
        }
        List<int[]> leftY = new ArrayList<>(n/2+1), rightY = new ArrayList<>(n/2+1);
        for (int[] p : ySorted) {
            if (leftSet.contains(p)) leftY.add(p);
            else                   rightY.add(p);
        }
        int[][] leftYArr  = leftY.toArray(new int[0][]);
        int[][] rightYArr = rightY.toArray(new int[0][]);

        // Recurse on left and right halves
        Result leftRes  = closestRec(xSorted, leftYArr,  lo,   mid);
        Result rightRes = closestRec(xSorted, rightYArr, mid,  hi);
        Result best = (leftRes.dist <= rightRes.dist) ? leftRes : rightRes;
        double δ = best.dist;

        // Build the strip of points within δ of mid‑x, in y‑order
        List<int[]> strip = new ArrayList<>();
        for (int[] p : ySorted) {
            if (Math.abs(p[0] - midPoint[0]) < δ) {
                strip.add(p);
            }
        }

        // Check up to next 7 neighbors in strip
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i+1; j < strip.size() && j <= i+7; j++) {
                double d = euclid(strip.get(i), strip.get(j));
                if (d < best.dist) {
                    best = new Result(strip.get(i), strip.get(j), d);
                    δ = d;
                }
            }
        }
        return best;
    }

    /** Brute‑force scan over xSorted[lo..hi). */
    private Result bruteForce(int[][] pts, int lo, int hi) {
        Result minR = new Result(pts[lo], pts[lo+1]);
        for (int i = lo; i < hi; i++) {
            for (int j = i+1; j < hi; j++) {
                double d = euclid(pts[i], pts[j]);
                if (d < minR.dist) {
                    minR = new Result(pts[i], pts[j], d);
                }
            }
        }
        return minR;
    }

    /** Euclidean distance between two points. */
    private static double euclid(int[] a, int[] b) {
        double dx = a[0] - b[0], dy = a[1] - b[1];
        return Math.hypot(dx, dy);
    }

    public static void main(String[] args) {
        ClosestPoints cp = new ClosestPoints();
        int[][] pts = { {0,0}, {5,4}, {3,1}, {9,6}, {4,3} };
        int[][] ans = cp.findClosestPoints(pts);
        System.out.printf("Closest pair: (%d,%d) and (%d,%d)%n",
            ans[0][0], ans[0][1], ans[1][0], ans[1][1]);
    }
}
