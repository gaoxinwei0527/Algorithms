package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 119. Pascal's Triangle II

 Given an index k, return the kth row of the Pascal's triangle.

 For example, given k = 3,
 Return [1,3,3,1].

 Note:
 Could you optimize your algorithm to use only O(k) extra space?
 */
public class L_Leetcode_119 {
    /**
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        while(rowIndex > 0){
            List<Integer> next = new ArrayList<>();
            for(int i = 0; i < row.size() + 1; i++){
                if(i == 0) next.add(row.get(0));
                else if(i == row.size()) next.add(row.get(row.size() - 1));
                else next.add(row.get(i - 1) + row.get(i));
            }
            row = next;
            rowIndex--;
        }
        return row;
    }
}
