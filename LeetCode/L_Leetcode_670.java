package LeetCode;

/**
 670. Maximum Swap

 Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

 Example 1:
 Input: 2736
 Output: 7236
 Explanation: Swap the number 2 and the number 7.
 Example 2:
 Input: 9973
 Output: 9973
 Explanation: No swap.
 Note:
 The given number is in the range [0, 108]
 */
public class L_Leetcode_670 {
    /**
     * @param num
     * @return
     *
     * max[i] means max digit index in [i, len];
     * traverse from 0 - len, try to find first i where digit[i] < digit[max[i]], then swap digits i & max[i];
     */
    public int maximumSwap(int num) {
        char[] arr = Integer.toString(num).toCharArray();
        int[] max = new int[arr.length];
        int m = arr.length - 1;
        for(int i = arr.length - 1; i >= 0; i--){
            if(arr[i] <= arr[m]) max[i] = m;
            else{
                max[i] = i;
                m = i;
            }
        }

        for(int i = 0; i < arr.length; i++){
            if(arr[i] < arr[max[i]]){
                char tmp = arr[i];
                arr[i] = arr[max[i]];
                arr[max[i]] = tmp;
                break;
            }
        }

        return Integer.parseInt(new String(arr));
    }
}
