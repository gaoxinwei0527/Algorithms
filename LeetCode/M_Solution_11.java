package LeetCode;

/**
 11. Container With Most Water

 Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

 Note: You may not slant the container and n is at least 2.
 */
public class M_Solution_11 {
    /**
     * @param height
     * @return
     *
     * Use two pointers move from [0, len - 1] toward middle.
     * Every time only move the lower height pointer and keep update the max area.
     *
     * Time - O(n)
     * Space - O(1)
     */
    public int maxArea(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1;
        while(i < j){
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if(height[i] > height[j]){
                j--;
            }else{
                i++;
            }
        }

        return max;
    }
}
