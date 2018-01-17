package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 315. Count of Smaller Numbers After Self

 You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 Example:

 Given nums = [5, 2, 6, 1]

 To the right of 5 there are 2 smaller elements (2 and 1).
 To the right of 2 there is only 1 smaller element (1).
 To the right of 6 there is 1 smaller element (1).
 To the right of 1 there is 0 smaller element.
 Return the array [2, 1, 1, 0].
 */
public class S_Leetcode_315 {
    /**
     * @param nums
     * @return
     *
     * keep the nums after current processing num sorted. then use binary search to find the insertion position of current num.
     *
     * Time - worst O(n *  n * logn), because insert element to a list is slow (with shift)
     * Space - O(n)
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        List<Integer> sorted = new ArrayList<>();
        for(int i = nums.length - 1; i >= 0; i--){
            int index = search(sorted, nums[i]);
            sorted.add(index, nums[i]);
            res.add(0, index);
        }
        return res;
    }

    private int search(List<Integer> sorted, int target){
        int i = 0;
        int j = sorted.size() - 1;
        while(i <= j){
            int m = (i + j) >>> 1;
            int mid = sorted.get(m);
            if(mid >= target){
                j = m - 1;
            }else{
                i = m + 1;
            }
        }
        return i;
    }

    private static class BSTNode{
        public int val;
        public int dup;
        public int left_count;
        public BSTNode left;
        public BSTNode right;
        public BSTNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
            this.dup = 1;
            this.left_count = 0;
        }
    }

    BSTNode root = null;

    /**
     * @param nums
     * @return
     *
     * Use extended BST, for each BST node, despite val / left child / right child, we also record 2 info-
     * 1. dup, means the duplicate num of cur.val, because the input could have duplicates, but BST by nature shouldn't include duplicates
     * 2. left_count, num count (including duplicates) in left subtree of current node
     *
     * With these info, we can calculate the smaller num count when we insert a target-
     * 1. if target == cur_node.val, cur_node.dup++, return 0;
     * 2. if target < cur_node.val, cur_node.left_count++, return insert(cur_node.left, target);
     * 3. if target > cur_node.val, return (cur_node.left_count + cur_node.dup + insert(cur_node.right, target));
     *
     * Basically, every time we turn to right subtree, we add the cur_node.dup + cur_node.left_count;
     *
     * Time - O(n * logn)
     * Space - O(n)
     */
    public List<Integer> countSmaller2(int[] nums) {
        Integer[] arr = new Integer[nums.length];
        for(int i = nums.length - 1; i >= 0; i--){
            arr[i] = insert(root, nums[i]);
        }
        return Arrays.asList(arr);
    }

    private int insert(BSTNode root, int val){
        if(this.root == null){
            this.root = new BSTNode(val);
            return 0;
        }

        if(root.val == val){
            root.dup++;
            return root.left_count;
        }else if(root.val < val){
            int cur = root.dup + root.left_count;
            if(root.right == null){
                root.right = new BSTNode(val);
                return cur;
            }else{
                return cur + insert(root.right, val);
            }
        }else{
            root.left_count++;
            if(root.left == null){
                root.left = new BSTNode(val);
                return 0;
            }else{
                return insert(root.left, val);
            }
        }
    }
}
