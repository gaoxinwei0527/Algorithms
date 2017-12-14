package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 117. Populating Next Right Pointers in Each Node II

 Follow up for problem "Populating Next Right Pointers in Each Node".

 What if the given tree could be any binary tree? Would your previous solution still work?

 Note:

 You may only use constant extra space.
 For example,
 Given the following binary tree,
 1
 /  \
 2    3
 / \    \
 4   5    7
 After calling your function, the tree should look like:
 1 -> NULL
 /  \
 2 -> 3 -> NULL
 / \    \
 4-> 5 -> 7 -> NULL
 */
public class L_Leetcode_117 {
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
