package LeetCode;

/**
 667. Beautiful Arrangement II

 Given two integers n and k, you need to construct a list which contains n different positive integers ranging from 1 to n and obeys the following requirement:
 Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.

 If there are multiple answers, print any of them.

 Example 1:
 Input: n = 3, k = 1
 Output: [1, 2, 3]
 Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3, and the [1, 1] has exactly 1 distinct integer: 1.
 Example 2:
 Input: n = 3, k = 2
 Output: [1, 3, 2]
 Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3, and the [2, 1] has exactly 2 distinct integers: 1 and 2.
 Note:
 The n and k are in the range 1 <= k < n <= 104.
 */
public class H_Leetcode_667 {
    /**
     * @param n
     * @param k
     * @return
     *
     * maintain two pointers min and max, initialized as 1 and n;
    1. alternately use min and max to fill the res[], this will create uniq |res[i] - res[i+1]| everytime, so we can do k-- for each fill, and keep updating min and max.
    2. when k reached 1, means the remaining elements should have same diff. then we should keep the remaining elements in order (either ascending or descending).
    Depends on useMax flag, we can tell if last element is max or min, then we can determine if we want following sequence descending or ascending.
     */
    public int[] constructArray(int n, int k) {
        int min = 1;
        int max = n;
        boolean lastMax = false;
        int[] res = new int[n];
        int i = 0;
        while(k > 0){ // check k > 0 instead of k > 1, because fill the first element will also decrease k
            if(lastMax){
                res[i++] = min++;
                lastMax = false;
            }else{
                res[i++] = max--;
                lastMax = true;
            }

            k--;
        }

        if(lastMax){
            for(int j = max; j >= min; j--){
                res[i++] = j;
            }
        }else{
            for(int j = min; j <= max; j++){
                res[i++] = j;
            }
        }

        return res;
    }
}
