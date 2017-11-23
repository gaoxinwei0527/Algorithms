package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 17. Letter Combinations of a Phone Number

 Given a digit string, return all possible letter combinations that the number could represent.

 A mapping of digit to letters (just like on the telephone buttons) is given below.

 Number	Letter
 0	none
 1	none
 2	ABC
 3	DEF
 4	GHI
 5	JKL
 6	MNO
 7	PQRS
 8	TUV
 9	WXYZ

 Input:Digit string "23"
 Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 Note:
 Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class L_Solution_17 {
    /**
     * @param digits
     * @return
     *
     * Simple brute force
     *
     * Time ~ O(3 ^ n)
     * Space ~ O(3 ^ n)
     */
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) return new ArrayList<>();
        char[][] map = new char[][]{{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        List<String> res = new ArrayList<>();
        res.add("");
        for(int i = 0; i < digits.length(); i++){
            List<String> next = new ArrayList<>();
            char[] chars = map[(int)(digits.charAt(i) - '0')];
            for(String s : res){
                for(char c : chars){
                    next.add(s + c);
                }
            }

            res = next;
        }

        return res;
    }
}
