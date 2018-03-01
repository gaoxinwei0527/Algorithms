package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 666. Path Sum IV

 If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

 For each integer in this list:
 The hundreds digit represents the depth D of this node, 1 <= D <= 4.
 The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
 The units digit represents the value V of this node, 0 <= V <= 9.
 Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.

 Example 1:
 Input: [113, 215, 221]
 Output: 12
 Explanation:
 The tree that the list represents is:
 3
 / \
 5   1

 The path sum is (3 + 5) + (3 + 1) = 12.
 Example 2:
 Input: [113, 221]
 Output: 4
 Explanation:
 The tree that the list represents is:
 3
 \
 1

 The path sum is (3 + 1) = 4.
 */
public class L_Leetcode_666 {

    /**
     * @param nums
     * @return
     *
     * dfs, if reach leaf, add the path sum to total sum
     */
    public int pathSum(int[] nums) {
        Map<Integer, Map<Integer, Integer>> tree = new HashMap<>();
        for(int i : nums){
            int level = i / 100;
            if(!tree.containsKey(level)) tree.put(level, new HashMap<>());
            tree.get(level).put((i / 10) % 10, i % 10);
        }

        int[] sum = new int[1];
        helper(sum, tree, 0, 1, 1);
        return sum[0];
    }

    private void helper(int[] sum, Map<Integer, Map<Integer, Integer>> tree, int tmp, int level, int pos){
        tmp += tree.get(level).get(pos);
        int next_level = level + 1;
        int l = pos * 2 - 1;
        int r = pos * 2;
        if(!tree.containsKey(next_level) || (!tree.get(next_level).containsKey(l) && !tree.get(next_level).containsKey(r))){
            sum[0] += tmp;
            return;
        }

        if(tree.get(next_level).containsKey(l)){
            helper(sum, tree, tmp, next_level, l);
        }

        if(tree.get(next_level).containsKey(r)){
            helper(sum, tree, tmp, next_level, r);
        }
    }
}
