package LeetCode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 632. Smallest Range

 You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

 We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

 Example 1:
 Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 Output: [20,24]
 Explanation:
 List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 List 2: [0, 9, 12, 20], 20 is in range [20,24].
 List 3: [5, 18, 22, 30], 22 is in range [20,24].
 Note:
 The given list may contain duplicates, so ascending order means >= here.
 1 <= k <= 3500
 -105 <= value of elements <= 105.
 For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.
 */
public class H_Leetcode_632 {
    /*
    Initialization: pointer[] includes current pointers of all lists. Pointers are all point to 0 initially as the lists are ascending. Then we can get initial range (max - min) for current pointers. Since this is range with smallest numbers that covers all lists. we cannot decrease the max value to reduce the size (because it will exclude some lists). So we can only keep growing the min value of the range (by move the pointer of min value in pointer[]).
    Fastest way to get the new range with movement-
    1. compare the new pointer value with current max value, and update the max if necessary
    2. maintain min heap to keep updating min value

    tip- use priority queue in this case, we can always know the left side (min value) from priority queue, the only thing we need to maintain is the right side (max value)
    */
    public int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();
        if(k == 1) return new int[]{nums.get(0).get(0), nums.get(0).get(0)};
        int[] pointer = new int[k];
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.comparingInt(a -> nums.get(a).get(pointer[a])));
        int max = -100001;

        for(int i = 0; i < k; i++){
            max = Math.max(max, nums.get(i).get(0));
            q.offer(i);
        }

        int[] res = new int[]{nums.get(q.peek()).get(0), max};
        while(pointer[q.peek()] < nums.get(q.peek()).size() - 1){
            int next = q.poll();
            pointer[next]++;
            max = Math.max(max, nums.get(next).get(pointer[next]));
            q.offer(next);
            if(max - nums.get(q.peek()).get(pointer[q.peek()]) < res[1] - res[0]){
                res = new int[]{nums.get(q.peek()).get(pointer[q.peek()]), max};
            }
        }

        return res;
    }
}
