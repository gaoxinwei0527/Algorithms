package LeetCode;

/**
 553. Optimal Division

 Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.

 However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should NOT contain redundant parenthesis.

 Example:
 Input: [1000,100,10,2]
 Output: "1000/(100/10/2)"
 Explanation:
 1000/(100/10/2) = 1000/((100/10)/2) = 200
 However, the bold parenthesis in "1000/((100/10)/2)" are redundant,
 since they don't influence the operation priority. So you should return "1000/(100/10/2)".

 Other cases:
 1000/(100/10)/2 = 50
 1000/(100/(10/2)) = 50
 1000/100/10/2 = 0.5
 1000/100/(10/2) = 2
 Note:

 The length of the input array is [1, 10].
 Elements in the given array will be in range [2, 1000].
 There is only one optimal division for each test case.
 */
public class M_Leetcode_553 {
    /**
     * @param nums
     * @return
     *
     * x0 / (x1 / x2 / ... / xn) will always be optimal since xi are all positive integers.
     * x0 will always go to the numerator. so just minimize seq(x1, x2, .., xn).
     * for seq(x1, x2, ..., xn), x1 will always go to the numerator. So to minimize seq(x1, x2, ..., xn), just let x1 divided by everything.
     *
     * So, x0 / (x1 / x2 / ... / xn) is always maximum.
     */
    public String optimalDivision(int[] nums) {
        if(nums.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]);
        if(nums.length == 1) return sb.toString();
        if(nums.length == 2){
            sb.append("/").append(nums[1]);
            return sb.toString();
        }

        sb.append("/(");
        for(int i = 1; i < nums.length; i++){
            sb.append(nums[i]);
            if(i != nums.length - 1){
                sb.append("/");
            }else{
                sb.append(")");
            }
        }
        return sb.toString();
    }
}
