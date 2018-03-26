package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 311. Sparse Matrix Multiplication

 Given two sparse matrices A and B, return the result of AB.

 You may assume that A's column number is equal to B's row number.

 Example:

 A = [
 [ 1, 0, 0],
 [-1, 0, 3]
 ]

 B = [
 [ 7, 0, 0 ],
 [ 0, 0, 0 ],
 [ 0, 0, 1 ]
 ]


      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
       | 0 0 1 |
 */
public class M_Leetcode_311 {
    /**
     * @param A
     * @param B
     * @return
     *
     * use map to record necessary info
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length;
        int n = B[0].length;
        int[][] res = new int[m][n];

        Map<Integer, Integer>[] mapA = new HashMap[m];
        for(int i = 0; i < m; i++){
            mapA[i] = new HashMap<>();
            for(int j = 0; j < A[i].length; j++){
                if(A[i][j] != 0) mapA[i].put(j, A[i][j]);
            }
        }

        Map<Integer, Integer>[] mapB = new HashMap[n];
        for(int i = 0; i < n; i++){
            mapB[i] = new HashMap<>();
            for(int j = 0; j < B.length; j++){
                if(B[j][i] != 0) mapB[i].put(j, B[j][i]);
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int k : mapA[i].keySet()){
                    if(mapB[j].containsKey(k)) res[i][j] += mapA[i].get(k) * mapB[j].get(k);
                }
            }
        }

        return res;
    }
}
