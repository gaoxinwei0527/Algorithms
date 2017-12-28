package LeetCode;

/**
 189. Rotate Array

 Rotate an array of n elements to the right by k steps.

 For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

 Note:
 Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

 [show hint]

 Related problem: Reverse Words in a String II

 Credits:
 Special thanks to @Freezen for adding this problem and creating all test cases.
 */
public class L_Leetcode_189 {
    /**
     * @param nums
     * @param k
     *
     * use another array to store result.
     *
     * Time - O(n)
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if(n <= 1) return;
        k %= n;
        int[] res = new int[nums.length];
        for(int i = 0; i < k; i++){
            res[i] = nums[n - k + i];
        }

        for(int i = k; i < n; i++){
            res[i] = nums[i - k];
        }

        System.arraycopy(res, 0, nums, 0, n);
    }
}
