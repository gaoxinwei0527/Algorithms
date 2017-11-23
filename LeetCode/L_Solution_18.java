package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 18. 4Sum

 Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

 Note: The solution set must not contain duplicate quadruplets.

 For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

 A solution set is:
 [
 [-1,  0, 0, 1],
 [-2, -1, 1, 2],
 [-2,  0, 0, 2]
 ]
 */
public class L_Solution_18 {
    /**
     * @param nums
     * @param target
     * @return
     *
     * Same as 3sum, just use 4 pointers instead, and remember to skip duplicates.
     *
     * Time - O(n ^ 3)
     * Space - O(m), where m means the num of quadruplets
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length - 3; i++){
            for(int j = i + 1; j < nums.length - 2; j++){
                int a = j + 1;
                int b = nums.length - 1;
                while(a < b){
                    int sum = nums[i] + nums[j] + nums[a] + nums[b];
                    if(sum == target){
                        List<Integer> quadruplet = new ArrayList<>();
                        quadruplet.add(nums[i]);
                        quadruplet.add(nums[j]);
                        quadruplet.add(nums[a]);
                        quadruplet.add(nums[b]);
                        res.add(quadruplet);
                        while(a < nums.length - 1 && nums[a + 1] == nums[a]){
                            a++;
                        }
                        a++;

                        while(b > 0 && nums[b - 1] == nums[b]){
                            b--;
                        }
                        b--;
                    }else if(sum < target){
                        a++;
                    }else{
                        b--;
                    }
                }

                while(j < nums.length - 3 && nums[j + 1] == nums[j]){
                    j++;
                }
            }

            while(i < nums.length - 4 && nums[i + 1] == nums[i]){
                i++;
            }
        }

        return res;
    }
}
