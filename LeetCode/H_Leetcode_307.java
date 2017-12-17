package LeetCode;

/**
 307. Range Sum Query - Mutable

 Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 The update(i, val) function modifies nums by updating the element at index i to val.
 Example:
 Given nums = [1, 3, 5]

 sumRange(0, 2) -> 9
 update(1, 2)
 sumRange(0, 2) -> 8
 Note:
 The array is only modifiable by the update function.
 You may assume the number of calls to update and sumRange function is distributed evenly.
 */
public class H_Leetcode_307 {
    /**
     * Binary indexed tree
     */
    private static class NumArray {
        int[] nums;
        int[] tree;

        public NumArray(int[] nums) {
            this.nums = new int[nums.length];
            this.tree = new int[nums.length + 1];
            for(int i = 0; i < nums.length; i++){
                update(i, nums[i]);
            }
        }

        public void update(int i, int val) {
            int delta = val - nums[i];
            nums[i] = val;
            int idx = i + 1;
            while(idx < tree.length){
                tree[idx] += delta;
                idx += (idx & -idx);
            }
        }

        private int query(int i){
            int idx = i + 1;
            int sum = 0;
            while(idx > 0){
                sum += tree[idx];
                idx -= (idx & -idx);
            }
            return sum;
        }

        public int sumRange(int i, int j) {
            return query(j) - query(i - 1);
        }
    }
}
