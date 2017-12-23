package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 149. Max Points on a Line

 Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */
public class H_Leetcode_149 {
    class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }

    /**
     * @param points
     * @return
     *
     * for each point a, try to decide slope of line constructed by a and all following points.
     * (no need to traverse points before a, since they are all processed already).
     *
     * For slope of line (tangent), it can be represented as x / y as it's rational, which is same as (x2 - x1) / (y2 - y1).
     * let's say m is gcd of x2 - x1 and y2 - y1, then x = (x2 - x1) / m, y = (y2 - y1) / m;
     * use x and y and point a, we can identify a single line.
     *
     * Time - O(n ^ 2)
     * Space - O(n)
     */
    public int maxPoints(Point[] points) {
        int n = points.length;
        if(n < 2) return n;
        int max = 0;

        for(int i = 0; i < n; i++){
            Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
            int x1 = points[i].x;
            int y1 = points[i].y;
            int overlap = 0;
            int local = 0;
            for(int j = i + 1; j < n; j++){
                int x2 = points[j].x;
                int y2 = points[j].y;
                if(x2 == x1 && y2 == y1){
                    overlap++;
                    continue;
                }

                int dx = x2 - x1;
                int dy = y2 - y1;
                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;

                if(!map.containsKey(dx)){
                    map.put(dx, new HashMap<>());
                }

                map.get(dx).put(dy, map.get(dx).getOrDefault(dy, 0) + 1);
                local = Math.max(local, map.get(dx).get(dy));
            }
            max = Math.max(max, overlap + 1 + local);
        }

        return max;
    }

    // Euclidean algorithm
    private int gcd(int x, int y){
        if(y == 0) return x;
        return gcd(y, x % y);
    }
}
