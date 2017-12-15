package LeetCode;

import java.util.Arrays;
import java.util.List;

/**
 120. Triangle

 Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

 For example, given the following triangle
 [
 [2],
 [3,4],
 [6,5,7],
 [4,1,8,3]
 ]
 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

 Note:
 Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public class L_Leetcode_120 {
    /**
     * @param triangle
     * @return
     *
     * top-down level order traverse
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if(n == 0) return 0;
        int[] res = new int[triangle.get(0).size()];
        res[0] = triangle.get(0).get(0);
        for(int i = 1; i < n; i++){
            List<Integer> cur = triangle.get(i);
            int[] tmp = new int[cur.size()];
            for(int j = 0; j < cur.size(); j++){
                if(j == 0) tmp[j] = cur.get(j) + res[0];
                else if(j == cur.size() - 1) tmp[j] = cur.get(j) + res[res.length - 1];
                else tmp[j] = cur.get(j) + Math.min(res[j - 1], res[j]);
            }
            res = tmp;
        }

        Arrays.sort(res);
        return res[0];
    }
}
