package LeetCode;

import java.util.*;

/**
 248. Strobogrammatic Number III

 A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

 Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

 For example,
 Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

 Note:
 Because the range might be a large number, the low and high numbers are represented as string.
 */
public class H_Leetcode_246_247_248 {

    private static Map<Character, Character> pair = new HashMap<>();
    static{
        pair.put('0', '0');
        pair.put('1', '1');
        pair.put('6', '9');
        pair.put('8', '8');
        pair.put('9', '6');
    }

    private static Set<Character> single = new HashSet<>();
    static{
        single.add('0');
        single.add('1');
        single.add('8');
    }

    /**
     246. Strobogrammatic Number

     A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

     Write a function to determine if a number is strobogrammatic. The number is represented as a string.

     For example, the numbers "69", "88", and "818" are all strobogrammatic.
     */

    public boolean isStrobogrammatic(String num) {
        int i = 0;
        int j = num.length() - 1;
        while(i < j){
            char a = num.charAt(i);
            char b = num.charAt(j);
            if(!pair.containsKey(a) || pair.get(a) != b) return false;
            i++;
            j--;
        }

        if(i == j && !single.contains(num.charAt(i))) return false;
        return true;
    }

    /**
     247. Strobogrammatic Number II

     A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

     Find all strobogrammatic numbers that are of length = n.

     For example,
     Given n = 2, return ["11","69","88","96"]
     */

    public List<String> findStrobogrammatic(int n) {
        if(n == 0) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        if(n % 2 == 0) {
            res.add("");
            helper(res, n);
        }else{
            for(char c : single){
                res.add("" + c);
            }
            helper(res, n - 1);
        }

        return res;
    }

    private void helper(List<String> res, int n){
        if(n == 0){
            return;
        }

        List<String> tmp = new ArrayList<>();
        for(String cur : res){
            for(char c : pair.keySet()){
                if(c == '0' && n == 2) continue;
                tmp.add(c + cur + pair.get(c));
            }
        }

        res.clear();
        res.addAll(tmp);
        helper(res, n - 2);
    }


    /**
     248. Strobogrammatic Number III

     A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

     Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

     For example,
     Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

     Note:
     Because the range might be a large number, the low and high numbers are represented as string.
     */

    /*
    try to construct all Strobogrammatic Numbers whose length l satisfy low.length() <= l <= high.length();
    and update the res counter.
    special cases are, when l == low.length() || l == high.length(), we need to exclude the numbers outside the range.
    Also, numbers start with 0 should be excluded;
    */
    public int strobogrammaticInRange(String low, String high) {
        int[] res = new int[1];

        for(int i = low.length(); i <= high.length(); i++){
            StringBuilder tmp = new StringBuilder();
            helper(tmp, i, res, low, high, pair, single);
        }

        return res[0];
    }

    private void helper(StringBuilder tmp, int target, int[] res, String low, String high, Map<Character, Character> pair, Set<Character> single){
        if(tmp.length() == 0 && target % 2 == 1){
            for(char c : single){
                tmp.append(c);
                helper(tmp, target, res, low, high, pair, single);
                tmp.deleteCharAt(tmp.length() - 1);
            }
        }else if(tmp.length() < target){
            for(Map.Entry<Character, Character> entry : pair.entrySet()){
                if(tmp.length() == target - 2 && entry.getKey() == '0'){
                    continue;
                }
                tmp.insert(0, entry.getKey());
                tmp.append(entry.getValue());
                helper(tmp, target, res, low, high, pair, single);
                tmp.deleteCharAt(0);
                tmp.deleteCharAt(tmp.length() - 1);
            }
        }else{
            if(inRange(tmp.toString(), low, high)){
                res[0]++;
            }
        }
    }

    private boolean inRange(String tmp, String low, String high){
        if(tmp.length() > low.length() && tmp.length() < high.length()){
            return true;
        }

        if(tmp.length() == low.length() && !compare(tmp, low)){
            return false;
        }

        if(tmp.length() == high.length() && !compare(high, tmp)){
            return false;
        }

        return true;
    }

    private boolean compare(String a, String b){
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) < b.charAt(i)){
                return false;
            }else if(a.charAt(i) > b.charAt(i)){
                return true;
            }
        }

        return true;
    }
}
