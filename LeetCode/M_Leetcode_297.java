package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 297. Serialize and Deserialize Binary Tree

 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

 For example, you may serialize the following tree

 1
 / \
 2   3
 / \
 4   5
 as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.



 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

 Credits:
 Special thanks to @Louis1992 for adding this problem and creating all test cases.
 */
public class M_Leetcode_297 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param root
     * @return
     *
     * level order traverse.
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "null";
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        StringBuilder sb = new StringBuilder();
        while(!level.isEmpty()){
            List<TreeNode> next = new ArrayList<>();
            for(TreeNode n : level){
                if(n == null) sb.append(",null");
                else{
                    sb.append(",").append(n.val);
                    next.add(n.left);
                    next.add(n.right);
                }
            }

            boolean lastLevel = true;
            for(TreeNode k : next){
                if(k != null) lastLevel = false;
            }

            if(lastLevel) break;
            level = next;
        }

        return sb.toString().substring(1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("null")) return null;
        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        int i = 1;
        while(!level.isEmpty() && i < nodes.length){
            List<TreeNode> next = new ArrayList<>();
            for(TreeNode n : level){
                if(i < nodes.length){
                    if(nodes[i].equals("null")){
                        n.left = null;
                    }else{
                        n.left = new TreeNode(Integer.parseInt(nodes[i]));
                        next.add(n.left);
                    }
                    i++;
                }else{
                    break;
                }

                if(i < nodes.length){
                    if(nodes[i].equals("null")){
                        n.right = null;
                    }else{
                        n.right = new TreeNode(Integer.parseInt(nodes[i]));
                        next.add(n.right);
                    }
                    i++;
                }else{
                    break;
                }
            }
            level = next;
        }

        return root;
    }
}
