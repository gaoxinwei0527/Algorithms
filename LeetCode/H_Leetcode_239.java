package LeetCode;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 239. Sliding Window Maximum

 Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

 For example,
 Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 Therefore, return the max sliding window as [3,3,5,5,6,7].

 Note:
 You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

 Follow up:
 Could you solve it in linear time?
 */
public class H_Leetcode_239 {
    /**
     * @param nums
     * @param k
     * @return
     *
     * use priority queue to maintain the window.
     *
     * Time - O(nlogk), where k is size of window
     * Space - O(k)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0) return new int[0];
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        int[] res = new int[nums.length - k + 1];
        for(int i = 0; i < k; i++){
            q.offer(nums[i]);
        }

        for(int i = 0; i < res.length; i++){
            res[i] = q.peek();
            if(i + k < nums.length){
                q.offer(nums[i + k]);
                boolean remove = q.remove(nums[i]);
            }
        }

        return res;
    }

    /**
     * @param nums
     * @param k
     * @return
     *
     * use deque to maintain a list of indexes inside the window, these indexes have following features-
     * 1. it will end with current window right end i.
     * 2. all other indexes k in deque, they all have nums[k] > nums[i], and k is inside window (this is easy to understand,
     * because we don't need indexes x with nums[x] <= nums[i] && x < i, they won't have impact on the window max, at the worst, we would pick nums[i] as max instead of them)
     * 3. the indexes k in deque have descending order based upon nums[k]. (same as above point, former one in ascending order won't have impact on window max)
     *
     * This way can also solve the fixed sliding window minimal problem. We only need to keep the index sequence in deque in ascending order based upon nums value.
     * And the last index in deque is current processing index.
     * It may also be used to solve other fixed sliding window problems.
     *
     * Time - O(n)
     * Space - O(k)
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if(nums.length == 0) return new int[0];
        Deque<Integer> deq = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for(int i = 0; i < nums.length; i++){
            if(!deq.isEmpty() && (i - deq.getFirst()) == k) deq.removeFirst();
            while(!deq.isEmpty() && nums[deq.getLast()] <= nums[i]) deq.removeLast();
            deq.addLast(i);
            if(i >= k - 1) res[i - k + 1] = nums[deq.getFirst()];
        }

        return res;
    }

    /**
     * @param nums
     * @param k
     * @return
     *
     * fixed size rolling window.
     * maintain the index of current window max. move both left & right side of window every time.
     * if previous window max index is not in the window any more, travers the whole window to find new max and update the max index
     * otherwise, if new right side of window >= max, update max index to right side index.
     *
     * Time - worst case O(n * k)
     * Extra Space - O(1)
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        if(nums.length == 0) return new int[0];
        int l = 0;
        int r = k - 1;
        int max_index = -1;
        int[] res = new int[nums.length - k + 1];
        while(r < nums.length){
            if(max_index < l){
                max_index = l;
                for(int i = l + 1; i <= r; i++){
                    if(nums[i] >= nums[max_index]) max_index = i;
                }
            }

            if(nums[r] >= nums[max_index]) max_index = r;
            res[l] = nums[max_index];
            l++;
            r++;
        }

        return res;
    }
}
