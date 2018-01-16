package LeetCode;

import java.util.*;

/**
 587. Erect the Fence

 There are some trees, where each tree is represented by (x,y) coordinate in a two-dimensional garden. Your job is to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed. Your task is to help find the coordinates of trees which are exactly located on the fence perimeter.

 Example 1:
 Input: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]
 Explanation:

 Example 2:
 Input: [[1,2],[2,2],[4,2]]
 Output: [[1,2],[2,2],[4,2]]
 Explanation:

 Even you only have trees in a line, you need to use rope to enclose them.
 Note:

 All trees should be enclosed together. You cannot cut the rope to enclose trees that will separate them in more than one group.
 All input integers will range from 0 to 100.
 The garden has at least one tree.
 All coordinates are distinct.
 Input points have NO order. No order required for output.

 */
public class H_Leetcode_587 {
    static class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }

    /**
     * @param points
     * @return
     *
     * Not very efficient Graham scan way.
     */
    public List<Point> outerTrees(Point[] points) {

        // if point num <= 3, we should include them all.
        if(points.length <= 3) return Arrays.asList(points);

        // Graham scan, find the first point, which has smallest y (pick smallest x if there is tie)
        Arrays.sort(points, (Point a, Point b) -> {
            if(a.y != b.y) return a.y - b.y;
            return a.x - b.x;
        });

        List<Point> res = new ArrayList<>();
        res.add(points[0]);

        // sort by polar angle with point 0
        Arrays.sort(points, 1, points.length, (Point a, Point b) -> {
            int cross = crossProduct(points[0], b, a);
            if(cross != 0) return cross;
            return dis(points[0], a) - dis(points[0], b);
        });

        // filter out some points. If two points a & b where p0_a and p0_b are colinear, only keey the one with longer distance with p0
        List<Point> filtered = new ArrayList<>();
        for(int i = points.length - 1; i >= 0; i--){
            int j = i - 1;
            while(j >= 0 && crossProduct(points[0], points[i], points[j]) == 0) j--;
            filtered.add(0, points[i]);
            i = j + 1;
        }

        // initial setup for scan, now we have initial 3 points
        res.add(filtered.get(0));
        res.add(filtered.get(1));

        // for each new point c, and top 2 points a & b in current result set. If a->b->c is making a non-left (right or coliear) turn, then c should replace b, otherwise it's not convex..
        for(int i = 2; i < filtered.size(); i++){
            while(true){
                Point a = res.get(res.size() - 2);
                Point b = res.get(res.size() - 1);
                Point c = filtered.get(i);
                if(crossProduct(a, c, b) >= 0) res.remove(res.size() - 1);
                else break;
            }

            res.add(filtered.get(i));
        }


        // now we already get the convex hull of the point set.
        // but this problem also require to return all points on the edge, so for each edge, do another scan.
        List<Point> onEdge = new ArrayList<>();
        for(int i = 0; i < res.size(); i++){
            Point a = (i == 0 ? res.get(res.size() - 1) : res.get(i - 1));
            Point b = res.get(i);
            for(int j = 0; j < points.length; j++){
                Point c = points[j];
                if(c.x == a.x && c.y == a.y || c.x == b.x && c.y == b.y) continue;
                if(crossProduct(a, b, c) == 0) onEdge.add(c);
            }
        }

        res.addAll(onEdge);
        return res;
    }

    // if return > 0, means ab turned right (clockwise) from ac.
    private int crossProduct(Point a, Point b, Point c){
        return (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);
    }

    private int dis(Point a, Point b){
        return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
    }
}
