package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 655. Print Binary Tree

 Print a binary tree in an m*n 2D string array following these rules:

 The row number m should be equal to the height of the given binary tree.
 The column number n should always be an odd number.
 The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
 Each unused space should contain an empty string "".
 Print the subtrees following the same rules.
 Example 1:
 Input:
 1
 /
 2
 Output:
 [["", "1", ""],
 ["2", "", ""]]
 Example 2:
 Input:
 1
 / \
 2   3
 \
 4
 Output:
 [["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
 Example 3:
 Input:
 1
 / \
 2   5
 /
 3
 /
 4
 Output:

 [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 Note: The height of binary tree is in the range of [1, 10].
 */
public class M_Leetcode_655 {
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
     * recursively print left & right subtree, then merge.
     */
    public List<List<String>> printTree(TreeNode root) {
        if(root == null) return new ArrayList<>();
        if(root.left == null && root.right == null){
            List<String> list = new ArrayList<>();
            list.add("" + root.val);
            List<List<String>> res = new ArrayList<>();
            res.add(list);
            return res;
        }

        List<List<String>> left = printTree(root.left);
        List<List<String>> right = printTree(root.right);
        int side = 0;
        if(left.size() > 0) side = Math.max(side, left.get(0).size());
        if(right.size() > 0) side = Math.max(side, right.get(0).size());

        List<List<String>> res = new ArrayList<>();
        List<String> rootLevel = new ArrayList<>();
        for(int i = 0; i < side; i++){
            rootLevel.add("");
        }
        rootLevel.add("" + root.val);
        for(int i = 0; i < side; i++){
            rootLevel.add("");
        }
        res.add(rootLevel);

        int i = 0;
        int j = 0;
        while(i < left.size() || j < right.size()){
            List<String> next = new ArrayList<>();
            if(i < left.size()){
                int offset = (side - left.get(i).size()) / (left.get(i).size() + 1);
                for(int k = 0; k < left.get(i).size(); k++){
                    for(int x = 0; x < offset; x++){
                        next.add("");
                    }
                    next.add(left.get(i).get(k));
                }

                for(int x = 0; x < offset; x++){
                    next.add("");
                }
                i++;
            }else{
                for(int k = 0; k < side; k++){
                    next.add("");
                }
            }
            next.add(""); // for root

            if(j < right.size()){
                int offset = (side - right.get(j).size()) / (right.get(j).size() + 1);
                for(int k = 0; k < right.get(j).size(); k++){
                    for(int x = 0; x < offset; x++){
                        next.add("");
                    }
                    next.add(right.get(j).get(k));
                }

                for(int x = 0; x < offset; x++){
                    next.add("");
                }
                j++;
            }else{
                for(int k = 0; k < side; k++){
                    next.add("");
                }
            }

            res.add(next);
        }

        return res;
    }
}
