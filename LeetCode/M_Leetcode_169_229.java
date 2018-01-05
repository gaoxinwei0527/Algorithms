package LeetCode;


import java.util.ArrayList;
import java.util.List;

public class M_Leetcode_169_229 {
    /**
     169. Majority Element

     Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

     You may assume that the array is non-empty and the majority element always exist in the array.

     Credits:
     Special thanks to @ts for adding this problem and creating all test cases.
     */

    /**
     * @param nums
     * @return
     *
     * classic Boyer–Moore majority vote
     *
     * Time - O(n)
     * Space - O(1)
     */
    public int majorityElement(int[] nums) {
        int res = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == res){
                count++;
            }else{
                if(count == 0) res = nums[i];
                else count--;
            }
        }

        return res;
    }

    /**
     229. Majority Element II

     Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
     */

    /**
     * @param nums
     * @return
     *
     * same as 169. Majority Element, just vote for 2 candidates
     *
     * Time - O(n)
     * Space - O(1)
     */
    public List<Integer> majorityElement2(int[] nums) {
        int n1 = 0;
        int c1 = 0;
        int n2 = 0;
        int c2 = 0;

        for(int i : nums){
            if(n1 == i) {
                c1++;
                continue;
            }else if(n2 == i){
                c2++;
                continue;
            }

            if(c1 == 0){
                n1 = i;
                c1 = 1;
            }else if(c2 == 0){
                n2 = i;
                c2 = 1;
            }else{
                c1--;
                c2--;
            }
        }

        List<Integer> res = new ArrayList<>();
        if(c1 > 0) res.add(n1);
        if(c2 > 0) res.add(n2);

        int[] count = new int[res.size()];
        for(int i : nums){
            for(int k = 0; k < res.size(); k++){
                if(i == res.get(k)) count[k]++;
            }
        }

        for(int i = res.size() - 1; i >= 0; i--){
            if(count[i] <= nums.length / 3) res.remove(i);
        }
        return res;
    }
}
