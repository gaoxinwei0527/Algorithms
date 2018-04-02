package LeetCode;

/**
 738. Monotone Increasing Digits

 Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.

 (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

 Example 1:
 Input: N = 10
 Output: 9
 Example 2:
 Input: N = 1234
 Output: 1234
 Example 3:
 Input: N = 332
 Output: 299
 Note: N is an integer in the range [0, 10^9].
 */
public class M_Leetcode_738 {
    /**
     * @param N
     * @return
     *
     * 1. iterate digits from left to right, find the first place where the digit drops.
     * 2. modify left side digit of drop d to d-1, then fill all '9' for the following digits.
     *
     * The trick is, num like '16663', the drop happens at d[3] -> d[4]. but we cannot make d[3] = d[3] - 1;
     * because that will make result '16659', which is not 'Monotone Increasing', so we should search back and
     * keep moving if d == d[3], then we stop at d[1] and do d[1] = d[1] - 1, then we get '15999', which is good.
     */
    public int monotoneIncreasingDigits(int N) {
        char[] arr = Integer.toString(N).toCharArray();
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] > arr[i + 1]){
                int j = i;
                while(j > 0 && arr[j - 1] == arr[j]) j--;
                arr[j] = (char) (arr[j] - 1);
                for(int k = j + 1; k < arr.length; k++) arr[k] = '9';
                break;
            }
        }
        return Integer.parseInt(new String(arr));
    }
}
