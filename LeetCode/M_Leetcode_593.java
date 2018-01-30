package LeetCode;

import java.util.Arrays;

/**
 593. Valid Square

 Given the coordinates of four points in 2D space, return whether the four points could construct a square.

 The coordinate (x,y) of a point is represented by an integer array with two integers.

 Example:
 Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 Output: True
 Note:

 All the input integers are in the range [-10000, 10000].
 A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 Input points have no order.
 */
public class M_Leetcode_593 {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if(same(p1, p2) || same(p1, p3) || same(p1, p4) || same(p2, p3) || same(p2, p4) || same(p3, p4)) return false;

        int[][] shape = new int[4][2];
        shape[0] = p1;
        shape[1] = p2;
        shape[2] = p3;
        shape[3] = p4;

        Arrays.sort(shape, (int[] a, int[] b) -> {
            if(a[0] != b[0]) return (a[0] - b[0]);
            return (a[1] - b[1]);
        });

        int d1 = dis(shape[0], shape[1]);
        int d2 = dis(shape[0], shape[2]);
        int d3 = dis(shape[3], shape[1]);
        int d4 = dis(shape[3], shape[2]);
        int d5 = dis(shape[1], shape[2]);
        if(!(d1 == d2 && d2 == d3 && d3 == d4)) return false;
        if(!(d5 == 2 * d1)) return false;
        return true;
    }

    private boolean same(int[] p1, int[] p2){
        return (p1[0] == p2[0] && p1[1] == p2[1]);
    }

    private int dis(int[] p1, int[] p2){
        return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
    }
}
