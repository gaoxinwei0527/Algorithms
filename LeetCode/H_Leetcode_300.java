package LeetCode;

import java.util.Arrays;

/**

 */
public class H_Leetcode_300 {
    /**
     * @param nums
     * @return
     *
     * 设d[len]表示长度为len的最长子列的最小末尾元素，这里之所以说是最小，是因为可能出现多个解，而显然其中结尾最小的最具有变得更长的潜力，因此我们应该选取结尾最小的作为最优解。当我们遍历nums[i]中的每个元素时，都能在d[len]中找到合适的位置插入它，规则为：

    当nums[i]比d[len]还大，说明可以插入到当前LIS的尾部，也就是可以找到一个更长的LIS，这时候我们更新d[++len]=nums[i]，这样不仅更新了LIS的尾部，也更新了长度。

    当nums[i]不比d[len]大时，并不能改变LIS的长度，但是可以对LIS进行优化，比如更新一个长度比len小的LIS的尾部元素为更小的值，虽然一次这样的更新可能无法带来len的增加，但是可以减小d[len]，例如序列10,9,2,5,3,7，我们开始会得到d[1]=10，接着9的到来会更新d[1]=9，2的到来会更新d[1]=2，只有这样，当5到来时，才能得到5>d[1]，从而得到d[2]=5。

     Time - O(n * logn)
     Space - O(n)
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(n <= 1) return n;
        int[] dp = new int[n];
        int len = 0;
        int res = 0;
        for(int num : nums){
            int i = Arrays.binarySearch(dp, 0, len, num);
            if(i < 0) i = -i - 1;
            dp[i] = num;
            if(i == len) len++;
            res = Math.max(len, res);
        }

        return res;
    }

    /**
     * @param nums
     * @return
     *
     * normal dp way
     * dp[i] means the length of LIS end with ith num
     * then for any j < i && nums[j] < nums[i], dp[i] = max(dp[i], dp[j] + 1);
     *
     * Time - O(n ^ 2)
     * Space - O(n)
     */
    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        if(n <= 1) return n;
        int[] dp = new int[n];
        dp[0] = 1;
        int res = 1;
        for(int i = 1; i < n; i++){
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j--){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
