package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 663. Equal Tree Partition

 Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.

 Example 1:
 Input:
 5
 / \
 10 10
 /  \
 2   3

 Output: True
 Explanation:
 5
 /
 10

 Sum: 15

 10
 /  \
 2    3

 Sum: 15
 Example 2:
 Input:
 1
 / \
 2  10
 /  \
 2   20

 Output: False
 Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
 Note:
 The range of tree node value is in the range of [-100000, 100000].
 1 <= n <= 10000
 */
public class L_Leetcode_663 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param root
     * @return
     *
     * get the sum of whole tree as x, check if we get any subtree (the tree itself not considered) have sum as x / 2;
     */
    public boolean checkEqualTree(TreeNode root) {
        if(root == null) return false;
        if(root.left == null && root.right == null) return false;
        Set<Integer> visited = new HashSet<>();
        int sum = root.val;
        if(root.left != null){
            sum(visited, root.left);
            sum += root.left.val;
        }

        if(root.right != null){
            sum(visited, root.right);
            sum += root.right.val;
        }

        if(sum % 2 != 0) return false;
        return visited.contains(sum / 2);
    }

    private void sum(Set<Integer> visited, TreeNode root){
        if(root == null) return;
        if(root.left == null && root.right == null){
            visited.add(root.val);
            return;
        }

        if(root.left != null){
            sum(visited, root.left);
            root.val += root.left.val;
        }

        if(root.right != null){
            sum(visited, root.right);
            root.val += root.right.val;
        }
        visited.add(root.val);
    }
}
