package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 366. Find Leaves of Binary Tree

 Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 Example:
 Given binary tree
 1
 / \
 2   3
 / \
 4   5
 Returns [4, 5, 3], [2], [1].

 Explanation:
 1. Removing the leaves [4, 5, 3] would result in this tree:

 1
 /
 2
 2. Now removing the leaf [2] would result in this tree:

 1
 3. Now removing the leaf [1] would result in the empty tree:

 []
 Returns [4, 5, 3], [2], [1].

 Credits:
 Special thanks to @elmirap for adding this problem and creating all test cases.
 */
public class H_Leetcode_366 {
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
     * recursive way
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        List<Integer> t = new ArrayList<>();
        t.add(root.val);
        if(root.left == null && root.right == null){
            res.add(t);
            return res;
        }else if(root.left == null){
            List<List<Integer>> r = findLeaves(root.right);
            res.addAll(r);
            res.add(t);
            return res;
        }else if(root.right == null){
            List<List<Integer>> l = findLeaves(root.left);
            res.addAll(l);
            res.add(t);
            return res;
        }else{
            List<List<Integer>> l = findLeaves(root.left);
            List<List<Integer>> r = findLeaves(root.right);
            int i = 0;
            int j = 0;
            while(i < l.size() || j < r.size()){
                List<Integer> next = new ArrayList<>();
                if(i < l.size()) next.addAll(l.get(i++));
                if(j < r.size()) next.addAll(r.get(j++));
                res.add(next);
            }
            res.add(t);
            return res;
        }
    }
}
