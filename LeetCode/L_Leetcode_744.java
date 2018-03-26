package LeetCode;

/**
 744. Find Smallest Letter Greater Than Target

 Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

 Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

 Examples:
 Input:
 letters = ["c", "f", "j"]
 target = "a"
 Output: "c"

 Input:
 letters = ["c", "f", "j"]
 target = "c"
 Output: "f"

 Input:
 letters = ["c", "f", "j"]
 target = "d"
 Output: "f"

 Input:
 letters = ["c", "f", "j"]
 target = "g"
 Output: "j"

 Input:
 letters = ["c", "f", "j"]
 target = "j"
 Output: "c"

 Input:
 letters = ["c", "f", "j"]
 target = "k"
 Output: "c"
 Note:
 letters has a length in range [2, 10000].
 letters consists of lowercase letters, and contains at least 2 unique letters.
 target is a lowercase letter.
 */
public class L_Leetcode_744 {
    public char nextGreatestLetter(char[] letters, char target) {
        char res = '*';
        char min = letters[0];
        for(char c : letters){
            min = (char) Math.min(c, min);
            if(c > target){
                res = (res == '*' ? c : (char) Math.min(res, c));
            }
        }

        if(res == '*') return min;
        return res;
    }
}
