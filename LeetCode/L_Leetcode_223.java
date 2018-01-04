package LeetCode;

/**
 223. Rectangle Area

 Find the total area covered by two rectilinear rectangles in a 2D plane.

 Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

 Assume that the total area is never beyond the maximum possible value of int.
 */
public class L_Leetcode_223 {
    /**
     * @param A
     * @param B
     * @param C
     * @param D
     * @param E
     * @param F
     * @param G
     * @param H
     * @return
     *
     * check if there is overlap, if so, calculate overlap area
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B);
        int area2 = (G - E) * (H - F);
        if(A >= G || E >= C || B >= H || F >= D) return (area1 + area2);
        int overlap = (Math.min(C, G) - Math.max(A, E)) * (Math.min(D, H) - Math.max(B, F));
        return (area1 + area2 - overlap);
    }
}
