package LeetCode;

/**
 420. Strong Password Checker

 A password is considered strong if below conditions are all met:

 It has at least 6 characters and at most 20 characters.
 It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
 It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
 Write a function strongPasswordChecker(s), that takes a string s as input, and return the MINIMUM change required to make s a strong password. If s is already strong, return 0.

 Insertion, deletion or replace of any one character are all considered as one change.
 */
public class H_Leetcode_420 {
    /**
     * @param s
     * @return
     *
     * This problem is hard because a single change can fix multiple violations. We need to analyze different situations
     *
     * firstly we can iterate over each char to get some some info-
     * 1. missing = num of type of chars missed (lower case, upper case, or digit)
     * 2. repeat[i] record the repeat frequency of i, which >= 3
     *
     * Then we analyze based upon two situations-
     * 1. if s.len < 6, res = Math.max(missing, 6 - len), this is easy to figure out
     * 2. otherwise, if len > 20, we set over = len - 20, over needs deletion to fix, missing needs insertion or replace to fix.
     * so, over and missing are exclusive, so we can initialize res = over + missing.
     * the problem now is, how can these changes be reused to reduce the changes to fix repeating pattern-
     *
     * two features need be utilized to fix repeating patterns
     * 1. repeating pattern can be fixed most efficiently by replace. e.g. aaaaa can be fixed by 1 replace or 2 insert or 3 deletion.
     * 2. repeating pattern can be fixed most efficiently when repeat num % 3 == 2; e.g. aaaa need 1 replace, while aaaaa also only need 1 replace.
     *
     * with these two features, we can do-
     * 1. firstly, try to use over to trim repeat patterns to 2 mod 3;
     * 2. if we still get over > 0, try to reduce repeat pattern by 3 until repeat < 3 or over < 0, the reason to reduce by 3 is,
     * we can assume that now all repeating patterns are 2 mod 3, reduce it by 1 or 2 won't have impact on fix.
     * 3. if we got missing fix, each of it can also fix repeat pattern of length 3.
     *
     * After all these fixes got reused, the remaining repeating patterns need extra replace op to fix, res += repeat / 3;
     *
     * Time - O(n)
     * Space - O(n)
     */
    public int strongPasswordChecker(String s) {
        char[] arr = s.toCharArray();
        int[] repeat = new int[arr.length];
        int low = 1;
        int up = 1;
        int digit = 1;

        for(int i = 0; i< arr.length; i++){
            if(low > 0 && arr[i] >= 'a' && arr[i] <= 'z') low--;
            if(up > 0 && arr[i] >= 'A' && arr[i] <= 'Z') up--;
            if(digit > 0 && arr[i] >= '0' && arr[i] <= '9') digit--;

            int count = 1;
            while(i + 1 < arr.length && arr[i + 1] == arr[i]){
                count++;
                i++;
            }

            repeat[i] = count;
        }

        if(arr.length < 6){
            return Math.max(6 - arr.length, low + up + digit);
        }else{
            int over = (arr.length > 20 ? (arr.length - 20) : 0);
            int missing = low + up + digit;
            int res = over + missing;

            if(over > 0){
                for(int k = 0; k < 2; k++){
                    for(int i = 0; i < arr.length; i++){
                        if(repeat[i] >= 3 && repeat[i] % 3 == k){
                            repeat[i] -= Math.min(k + 1, over);
                            over -= (k + 1);
                        }
                    }
                }
            }

            for(int i = 0; i < arr.length; i++){
                if(repeat[i] >= 3) {
                    while(repeat[i] >= 3 && missing > 0){
                        repeat[i] -= 3;
                        missing--;
                    }

                    while(repeat[i] >= 3 && over >= 3){
                        repeat[i] -= 3;
                        over -= 3;
                    }
                    res += (repeat[i] / 3);
                }
            }

            return res;
        }
    }
}
