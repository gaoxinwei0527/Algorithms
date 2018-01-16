package LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 314. Binary Tree Vertical Order Traversal

 Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

 If two nodes are in the same row and column, the order should be from left to right.

 Examples:

 Given binary tree [3,9,20,null,null,15,7],
 3
 /\
 /  \
 9  20
 /\
 /  \
 15   7
 return its vertical order traversal as:
 [
 [9],
 [3,15],
 [20],
 [7]
 ]
 Given binary tree [3,9,8,4,0,1,7],
 3
 /\
 /  \
 9   8
 /\  /\
 /  \/  \
 4  01   7
 return its vertical order traversal as:
 [
 [4],
 [9],
 [3,0,1],
 [8],
 [7]
 ]
 Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
 3
 /\
 /  \
 9   8
 /\  /\
 /  \/  \
 4  01   7
 /\
 /  \
 5   2
 return its vertical order traversal as:
 [
 [4],
 [9,5],
 [3,0,1],
 [8,2],
 [7]
 ]
 */
public class M_Leetcode_314 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class OffsetNode{
        TreeNode node;
        int offset;
        public OffsetNode(TreeNode node, int offset){
            this.node = node;
            this.offset = offset;
        }
    }

    /**
     * @param root
     * @return
     *
     * level order traverse and also record offset of the nodes.
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        List<List<Integer>> left = new ArrayList<>();
        List<List<Integer>> right = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();

        OffsetNode rt = new OffsetNode(root, 0);
        List<OffsetNode> level = new ArrayList<>();
        level.add(rt);
        while(!level.isEmpty()){
            List<OffsetNode> next = new ArrayList<>();
            for(OffsetNode n : level){
                if(n.offset == 0){
                    mid.add(n.node.val);
                }else if(n.offset > 0){
                    if(n.offset > right.size()) right.add(new ArrayList<>());
                    right.get(n.offset - 1).add(n.node.val);
                }else{
                    if((-n.offset) > left.size()) left.add(new ArrayList<>());
                    left.get(-n.offset - 1).add(n.node.val);
                }

                if(n.node.left != null){
                    OffsetNode l = new OffsetNode(n.node.left, n.offset - 1);
                    next.add(l);
                }

                if(n.node.right != null){
                    OffsetNode r = new OffsetNode(n.node.right, n.offset + 1);
                    next.add(r);
                }
            }

            level = next;
        }

        Collections.reverse(left);
        List<List<Integer>> res = new ArrayList<>();
        res.addAll(left);
        res.add(mid);
        res.addAll(right);

        return res;
    }
}
