package LeetCode;

import java.util.TreeMap;

/**
 659. Split Array into Consecutive Subsequences

 You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.

 Example 1:
 Input: [1,2,3,3,4,5]
 Output: True
 Explanation:
 You can split them into two consecutive subsequences :
 1, 2, 3
 3, 4, 5
 Example 2:
 Input: [1,2,3,3,4,4,5,5]
 Output: True
 Explanation:
 You can split them into two consecutive subsequences :
 1, 2, 3, 4, 5
 3, 4, 5
 Example 3:
 Input: [1,2,3,4,4,5]
 Output: False
 Note:
 The length of the input is in range of [1, 10000]
 */
public class H_Leetcode_659 {
    /**
     * @param nums
     * @return
     *
     * greedy, every iteration, get the smallest num k, keep increase k to see if k+1 is still remaining and count(k + 1) >= count(k);
     * if count(k + 1) < count(k), then we should stop increasing k, because k+1 is required to eliminate all remaining k.
     */
    public boolean isPossible(int[] nums) {
        int n = nums.length;
        if(n < 3) return false;
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for(int i : nums){
            count.put(i, count.getOrDefault(i, 0) + 1);
        }

        while(!count.isEmpty()){
            int start = count.firstKey();
            int k = start;
            count.put(start, count.get(start) - 1);
            if(count.get(start) == 0) count.remove(start);
            while(count.containsKey(k + 1)){
                k++;
                count.put(k, count.get(k) - 1);
                if(count.get(k) == 0) count.remove(k);
                if(!count.containsKey(k + 1) || count.get(k + 1) <= count.getOrDefault(k, 0)) break;
            }

            if(k - start < 2) return false;
        }

        return true;
    }
}
