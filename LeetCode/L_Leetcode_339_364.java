package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 339. Nested List Weight Sum

 Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Example 1:
 Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

 Example 2:
 Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
 */
public class L_Leetcode_339_364 {

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {
        // Constructor initializes an empty nested list.
        // public NestedInteger();

        // Constructor initializes a single integer.
        //public NestedInteger(int value);

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    /**
     * @param nestedList
     * @return
     *
     * bfs
     */
    public int depthSum(List<NestedInteger> nestedList) {
        int sum = 0;
        int depth = 1;
        while(!nestedList.isEmpty()){
            List<NestedInteger> next = new ArrayList<>();
            for(NestedInteger n : nestedList){
                if(n.isInteger()) sum += (depth * n.getInteger());
                else{
                    next.addAll(n.getList());
                }
            }
            depth++;
            nestedList = next;
        }
        return sum;
    }

    /*
    364. Nested List Weight Sum II

    Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
    */

    /**
     * @param nestedList
     * @return
     *
     * 1. maintain unweighted sum
     * 2. for each new level, accumulate current unweighted sum (means all existing nodes promoted one level)
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int sum = 0;
        int unweighted = 0;
        while(!nestedList.isEmpty()){
            sum += unweighted;
            List<NestedInteger> next = new ArrayList<>();
            for(NestedInteger n : nestedList){
                if(n.isInteger()){
                    sum += n.getInteger();
                    unweighted += n.getInteger();
                }else{
                    next.addAll(n.getList());
                }
            }
            nestedList = next;
        }

        return sum;
    }
}
