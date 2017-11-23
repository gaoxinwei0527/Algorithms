package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 15. 3Sum

 Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

 Note: The solution set must not contain duplicate triplets.

 For example, given array S = [-1, 0, 1, 2, -1, -4],

 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]
 */
public class L_Solution_15 {
    /**
     * @param nums
     * @return
     *
     * Sort the array, move pointer i from 0 to nums.length - 2; Then for nums[i+1, nums.length - 1], do 2sum with target as -num[i];
     * Note that only uniq results required, so we need to skip duplicate elements.
     *
     * Time - O(n ^ 2)
     * Space - O(3m), where m means the num of result triplets
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length - 2; i++){
            int target = -nums[i];
            int a = i + 1;
            int b = nums.length - 1;
            while(a < b){
                if((nums[a] + nums[b]) == target){
                    List<Integer> next = new ArrayList<>();
                    next.add(nums[i]);
                    next.add(nums[a]);
                    next.add(nums[b]);
                    res.add(next);
                    while(a < nums.length - 1 && nums[a + 1] == nums[a]){
                        a++;
                    }
                    a++;

                    while(b > 0 && nums[b - 1] == nums[b]){
                        b--;
                    }
                    b--;
                }else if((nums[a] + nums[b]) < target){
                    a++;
                }else{
                    b--;
                }
            }

            while(i < nums.length - 1 && nums[i + 1] == nums[i]){
                i++;
            }
        }

        return res;
    }
}
