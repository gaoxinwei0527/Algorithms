package LeetCode;

/**
 600. Non-negative Integers without Consecutive Ones

 Given a positive integer n, find the number of non-negative integers less than or equal to n, whose binary representations do NOT contain consecutive ones.

 Example 1:
 Input: 5
 Output: 5
 Explanation:
 Here are the non-negative integers <= 5 with their corresponding binary representations:
 0 : 0
 1 : 1
 2 : 10
 3 : 11
 4 : 100
 5 : 101
 Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
 Note: 1 <= n <= 109
 */
public class H_Leetcode_600 {
    /**
     * @param num
     * @return
     */
    public int findIntegers(int num) {
        /*
        * build lookup table:
        * t[i] means the num of non-negtive integers without consecutive ones and with i bits
        *
        * for example, say i = 5;
        * then the range is 00000 - 11111, it can be divided to 3 parts
        * 00000 - 01111 (t[4])
        * 10000 - 10111 (t[3])
        * 11000 - 11111 (all nums in this range have consecutive ones)
        *
        * so t[5] = t[3] + t[4];
        * in general, t[i] = t[i - 1] + t[i - 2]
        */
        int[] t = new int[32];
        t[0] = 1;
        t[1] = 2;
        t[2] = 3;
        for(int i = 3; i < 32; i++){
            t[i] = t[i - 1] + t[i - 2];
        }

        /*
        * find leftmost one in num
        */
        int k = 0;
        while((num >> k) > 0){
            k++;
        }

        /*
        * whenever find a one in num, we can assume it's set to zero and add all the eligible nums in this case. e.g.
        * num = 101010
        * 1. we can assume first one is zero, then all eligible nums in [0]00000 - [0]11111 (t[5]) can be added
        * 2. we find second one and set it to zero, then all eligible nums in [100]000 - [100]111 (t[3]) can be added
        * 3. then the third one, all eligible nums in [10100]0 - [10100]1 (t[1]) can be added
        *
        * conner case-
        * num = 110000
        * 1. [0]00000 - [0]11111 (t[5]) can be added
        * 2. [10]0000 - [10]1111 (t[4]) can be added
        * then no more number can be added, because all remaining nums with prefix [11], they all ineligible. so we can return at this time
        *
        * if eventually k reach 0 and we haven't returned, then means the num itself is eligible. then return res + 1;
        *
        */
        int res = 0;
        int prev = 0;
        while(k > 0){
            k--;
            int next = (num >> k) & 1;
            if(next == 1){
                res += t[k];
                if(prev == 1) return res;
                prev = 1;
            }else{
                prev = 0;
            }
        }
        return res + 1;
    }
}
