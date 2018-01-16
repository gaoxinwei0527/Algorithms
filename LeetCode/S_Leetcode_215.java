package LeetCode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 215. Kth Largest Element in an Array

 Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 For example,
 Given [3,2,1,5,6,4] and k = 2, return 5.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.

 Credits:
 Special thanks to @mithmatt for adding this problem and creating all test cases.
 */
public class S_Leetcode_215 {
    /**
     * @param nums
     * @param k
     * @return
     *
     * naive way, just sort it
     *
     * Time - O(nlogn)
     * Space - O(1)
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * @param nums
     * @param k
     * @return
     *
     * use priority queue to maintain k max values.
     *
     * Time - O(nlogk)
     * Space - O(k)
     */
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i : nums){
            q.offer(i);
            if(q.size() > k) q.poll();
        }
        return q.poll();
    }

    /**
     * @param nums
     * @param k
     * @return
     *
     * randomized quick selector
     */
    public int findKthLargest3(int[] nums, int k) {
        shuffle(nums);
        return findK(nums, 0, nums.length - 1, nums.length - k + 1);
    }

    private void shuffle(int nums[]) {
        final Random random = new Random();
        for(int ind = 1; ind < nums.length; ind++) {
            final int r = random.nextInt(ind + 1);
            swap(nums, ind, r);
        }
    }

    private void swap(int[] nums, int i, int j) {
        final int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int findK(int[] nums, int i, int j, int k){
        if(i == j) return nums[i];
        int pivot = nums[i];
        int a = i;
        int b = j;
        while(a < b){
            while(a <= b && nums[a] <= pivot) a++;
            while(b >= a && nums[b] > pivot) b--;
            if(a < b) swap(nums, a, b);
        }

        nums[i] = nums[b];
        nums[b] = pivot;
        if(b - i + 1 == k) return nums[b];
        if(b - i + 1 < k){
            return findK(nums, b + 1, j, k - (b - i + 1));
        }else{
            return findK(nums, i, b - 1, k);
        }
    }
}
