package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 228. Summary Ranges

 Given a sorted integer array without duplicates, return the summary of its ranges.

 Example 1:
 Input: [0,1,2,4,5,7]
 Output: ["0->2","4->5","7"]
 Example 2:
 Input: [0,2,3,4,6,8,9]
 Output: ["0","2->4","6","8->9"]
 */
public class L_Leetcode_228 {
    /**
     * @param nums
     * @return
     *
     * just one pass traverse
     *
     * Time - O(n)
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while(i < nums.length){
            int j = i + 1;
            while(j < nums.length && nums[j] == nums[j - 1] + 1) j++;
            int start = nums[i];
            int end = nums[j - 1];
            if(start == end) res.add("" + start);
            else res.add("" + start + "->" + end);
            i = j;
        }
        return res;
    }
}
