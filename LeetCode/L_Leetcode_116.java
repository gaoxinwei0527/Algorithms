package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 116. Populating Next Right Pointers in Each Node

 Given a binary tree

 struct TreeLinkNode {
 TreeLinkNode *left;
 TreeLinkNode *right;
 TreeLinkNode *next;
 }
 Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

 Initially, all next pointers are set to NULL.

 Note:

 You may only use constant extra space.
 You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 For example,
 Given the following perfect binary tree,
 1
 /  \
 2    3
 / \  / \
 4  5  6  7
 After calling your function, the tree should look like:
 1 -> NULL
 /  \
 2 -> 3 -> NULL
 / \  / \
 4->5->6->7 -> NULL
 */
public class L_Leetcode_116 {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

    /**
     * @param root
     *
     * level order traverse
     */
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        List<TreeLinkNode> level = new ArrayList<>();
        level.add(root);
        while(!level.isEmpty()){
            List<TreeLinkNode> next = new ArrayList<>();
            for(int i = 0; i < level.size(); i++){
                TreeLinkNode n = level.get(i);
                if(n.left != null) next.add(n.left);
                if(n.right != null) next.add(n.right);
                n.next = (i == level.size() - 1 ? null : level.get(i + 1));
            }
            level = next;
        }
    }
}
