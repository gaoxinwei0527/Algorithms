package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 118. Pascal's Triangle

 Given numRows, generate the first numRows of Pascal's triangle.

 For example, given numRows = 5,
 Return

 [
 [1],
 [1,1],
 [1,2,1],
 [1,3,3,1],
 [1,4,6,4,1]
 ]
 */
public class L_Leetcode_118 {
    /**
     * @param numRows
     * @return
     *
     * naive way
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows == 0) return res;
        List<Integer> level = new ArrayList<>();
        level.add(1);
        res.add(level);
        while(numRows > 1){
            List<Integer> next = new ArrayList<>();
            for(int i = 0; i < level.size() + 1; i++){
                if(i == 0) next.add(level.get(0));
                else if(i == level.size()) next.add(level.get(level.size() - 1));
                else next.add(level.get(i - 1) + level.get(i));
            }
            res.add(next);
            level = next;
            numRows--;
        }
        return res;
    }
}
