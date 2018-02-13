package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 652. Find Duplicate Subtrees

 Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

 Two trees are duplicate if they have the same structure with same node values.

 Example 1:
 1
 / \
 2   3
 /   / \
 4   2   4
 /
 4
 The following are two duplicate subtrees:
 2
 /
 4
 and
 4
 Therefore, you need to return above trees' root in the form of a list.
 */
public class L_Leetcode_652 {
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
     * serialize and count serialized strings
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if(root == null) return res;

        Map<String, Integer> visited = new HashMap<>();
        if(root.left != null) serialize(visited, res, root.left);
        if(root.right != null) serialize(visited, res, root.right);
        return res;
    }

    private String serialize(Map<String, Integer> visited, List<TreeNode> res, TreeNode root){
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        if(root.left != null){
            sb.append(',').append(serialize(visited, res, root.left));
            if(root.right != null){
                sb.append(',').append(serialize(visited, res, root.right));
            }else{
                sb.append(",#");
            }
        }else if(root.right != null){
            sb.append(",#,").append(serialize(visited, res, root.right));
        }else{
            sb.append(",#,#");
        }

        String serialized = sb.toString();
        if(visited.getOrDefault(serialized, 0) == 1) res.add(root);
        visited.put(serialized, visited.getOrDefault(serialized, 0) + 1);
        return serialized;
    }
}
