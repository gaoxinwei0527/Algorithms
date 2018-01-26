package LintCode;

import java.util.Arrays;

/**
 On one line there are n houses. Give you an array of integer means the position of each house. Now you need to pick k position to build k post office, so that the sum distance of each house to the nearest post office is the smallest. Return the least possible sum of all distances between each village and its nearest post office.
 Example
 Given array a = [1,2,3,4,5], k = 2.
 return 3.+

 Challenge
 Could you solve this problem in O(n^2) time ?
 */
public class H_Lintcode_435 {
    public int postOffice(int[] A, int k) {
        int n = A.length;
        if(k >= n) return 0;
        Arrays.sort(A);
        int[][] dis = preprocess(A);

        int[][] dp = new int[n][k + 1];
        for(int i = 1; i < n; i++){
            dp[i][1] = dis[0][i];
        }

        for(int x = 2; x <= k; x++){
            for(int i = x; i < n; i++){
                dp[i][x] = Integer.MAX_VALUE;
                for(int j = 0; j < i; j++){
                    dp[i][x] = Math.min(dp[i][x], dp[j][x - 1] + dis[j + 1][i]);
                }
            }
        }

        return dp[n - 1][k];
    }

    private int[][] preprocess(int[] A){
        int n = A.length;
        int[][] dis = new int[n][n];
        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                int m = (i + j) >>> 1;
                for(int k = i; k <= j; k++){
                    dis[i][j] += Math.abs(A[k] - A[m]);
                }
            }
        }

        return dis;
    }

    public static void main(String[] args){
        int[] arr = new int[]{112,122,360,311,85,225,405,53,405,43,342,13,588,424,299,37,104,289,404,414};
        H_Lintcode_435 h_lintcode_435 = new H_Lintcode_435();
        System.out.println(h_lintcode_435.postOffice(arr, 3));
    }
}
