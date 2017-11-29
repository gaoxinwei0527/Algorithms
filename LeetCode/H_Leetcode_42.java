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
}
