package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 658. Find K Closest Elements

 Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

 Example 1:
 Input: [1,2,3,4,5], k=4, x=3
 Output: [1,2,3,4]
 Example 2:
 Input: [1,2,3,4,5], k=4, x=-1
 Output: [1,2,3,4]
 Note:
 The value k is positive and will always be smaller than the length of the sorted array.
 Length of the given array is positive and will not exceed 104
 Absolute value of elements in the array and x will not exceed 104
 UPDATE (2017/9/19):
 The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition to get the latest changes.
 */
public class L_Leetcode_658 {
    /**
     * @param arr
     * @param k
     * @param x
     * @return
     *
     * two pointers move from two ends to mid, until window size is k
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int i = 0;
        int j = arr.length - 1;
        while(j - i + 1 > k){
            int d1 = Math.abs(arr[i] - x);
            int d2 = Math.abs(arr[j] - x);
            if(d1 > d2) i++;
            else j--;
        }

        List<Integer> res = new ArrayList<>();
        for(int a = i; a <= j; a++){
            res.add(arr[a]);
        }

        return res;
    }
}
