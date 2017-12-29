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

    /**
     * @param nums
     * @param k
     *
     * in-place way.
     * 1. reverse the whole array
     * 2. reverse nums[0 - k - 1] and nums[k, len - 1];
     *
     * e.g.
     * [1,2,3,4,5,6,7] -> [7,6,5,4,3,2,1] -> [5,6,7,1,2,3,4]
     */
    public void rotate2(int[] nums, int k) {
        if(nums.length <= 1) return;
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int i, int j){
        while(i < j){
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
