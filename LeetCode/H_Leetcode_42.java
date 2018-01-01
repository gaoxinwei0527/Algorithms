package LeetCode;

/**
 42. Trapping Rain Water

 Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

 For example,
 Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class H_Leetcode_42 {
    /**
     * @param height
     * @return
     *
     * For index i, the capacity[i] is depending on the highest left and right elevation. So do two iterations to get all left_max and right_max for every index.
     * Then iterate the array, for index i, if Math.min(left_max, right_max) > height[i], then res can add the capacity, which is Math.min(left_max, right_max) - height[i];
     *
     * Time - O(n)
     * Space - O(n)
     */
    public int trap(int[] height) {
        if(height == null || height.length < 3){
            return 0;
        }

        int[] left_max = new int[height.length];
        int[] right_max = new int[height.length];
        int l_max = height[0];
        int r_max = height[height.length-1];

        for(int i = 1; i<height.length; i++){
            left_max[i] = l_max;
            l_max = Math.max(height[i], l_max);
        }

        for(int j = height.length - 2; j >= 0; j--){
            right_max[j] = r_max;
            r_max = Math.max( height[j], r_max);
        }

        int res = 0;
        for(int k = 1; k < height.length - 1; k++){
            int min = Math.min(left_max[k], right_max[k]);
            if(min > height[k]){
                res += (min - height[k]);
            }
        }

        return res;
    }

    /**
     * @param height
     * @return
     *
     * 木桶原理
     * This is a way inspired by trap rain water II, only need one pass traverse.
     * in trap rain water II, we use priority queue to store current defending walls to get the shortest wall.
     * in this case, it's doing the same thing, but we don't need a queue, because it's 2-D, so we would always only have 2 walls.
     *
     * initially, the walls l & r would be index 0 & n - 1.
     * we detect the shortest wall (bottleneck) by compare l and r, whichever shorter moves.
     *
     * E.g. l < r, we move l, but before we really moves l, we need to process all l's shorter neighbors. This is exactly same as trap water II.
     * Just like the water level is at l's height, then l's shorter neighbors will be overwhelmed, if l's neighbor is higher, then it will become a new wall.
     *
     * say for index in [l + 1, l + k], they all shorter then l, then we do res += (h[l] - h[l + 1]), res += (h[l] - h[l + 2])..etc, cause l is shortest outer wall now.
     * all other walls in queue (only r in this case) are higher than l.
     */
    public int trap2(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int res = 0;
        while(r - l > 1){ // if l & r are adjacent, then they could not trap any more water
            if(height[l] > height[r]){
                int k = r - 1;
                while(height[k] < height[r]){
                    res += (height[r] - height[k]);
                    k--;
                }
                r = k;
            }else{
                int k = l + 1;
                while(height[k] < height[l]){
                    res += (height[l] - height[k]);
                    k++;
                }
                l = k;
            }
        }
        return res;
    }
}
