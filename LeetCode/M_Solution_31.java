package LeetCode;

/**
 31. Next Permutation

 Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

 If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

 The replacement must be in-place, do not allocate extra memory.

 Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1
 */
public class M_Solution_31 {
    /**
     * @param nums
     *
     * if nums is in descending order, just reverse it.
     * otherwise, find the last ascending point, eg.
     * 4 2 5 3 1
     * 2 -> 5 is last ascending point, but we should not exchange 2 and 5, we should find the minimal num in descending sequence which is greater than 2,
     * e.g. 3 in this case. After exchange 2 and 3, we get-
     * 4 3 5 2 1, then we should sort (actually just reverse, because it's guranteed descending) the sequence after exchange point (3), so we get-
     * 4 3 1 2 5, which is the result.
     *
     * Time - O(n)
     * Space - O(1)
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while(i > 0 && nums[i - 1] >= nums[i]){
            i--;
        }

        if(i == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int tmp = nums[i - 1];
        int j = i;
        while(j < nums.length - 1 && nums[j + 1] > tmp){
            j++;
        }

        nums[i - 1] = nums[j];
        nums[j] = tmp;
        reverse(nums, i, nums.length - 1);
    }

    private void reverse(int[] nums, int a, int b){
        while(a < b){
            int tmp = nums[a];
            nums[a] = nums[b];
            nums[b] = tmp;
            a++;
            b--;
        }
    }
}
