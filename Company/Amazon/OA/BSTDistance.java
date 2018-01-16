package Company.Amazon.OA;

/**
 1. take an integer array as input and construct BST
 2. find shortest distance of two values in BST
 */
public class BSTDistance {
    private static class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int val;
        public TreeNode(int val){
            this.val = val;
        }
    }

    TreeNode root;

    public int distance(int[] arr, int a, int b){
        if(arr.length == 0) return -1;
        root = new TreeNode(arr[0]);
        for(int i = 1; i < arr.length; i++){
            TreeNode next = new TreeNode(arr[i]);
            insert(root, next);
        }

        TreeNode nodeA = search(root, a);
        if(nodeA == null) return -1;

        TreeNode nodeB = search(root, b);
        if(nodeB == null) return -1;

        TreeNode lca = lca(root, a, b);
        return dis(lca, nodeA) + dis(lca, nodeB);
    }

    private void insert(TreeNode root, TreeNode next){
        if(root.val == next.val) return;
        if(root.val > next.val){
            if(root.left == null) root.left = next;
            else insert(root.left, next);
        }else{
            if(root.right == null) root.right = next;
            else insert(root.right, next);
        }
    }

    private TreeNode search(TreeNode root, int val){
        if(root == null || root.val == val) return root;
        if(root.val > val) return search(root.left, val);
        return search(root.right, val);
    }

    private TreeNode lca(TreeNode root, int a, int b){
        if(root.val == a || root.val == b) return root;
        if(root.val > a && root.val > b) return lca(root.left, a, b);
        if(root.val < a && root.val < b) return lca(root.right, a, b);
        return root;
    }

    private int dis(TreeNode root, TreeNode a){
        if(root.val == a.val) return 0;
        if(root.val > a.val) return dis(root.left, a) + 1;
        return dis(root.right, a) + 1;
    }

    public static void main(String[] args){
        BSTDistance bstDistance = new BSTDistance();
        int[] arr = new int[]{5,6,3,1,2,4};
        System.out.println(bstDistance.distance(arr, 3, 6)); // 2
        System.out.println(bstDistance.distance(arr, 5, 6)); // 1
        System.out.println(bstDistance.distance(arr, 3, 8)); // -1
        System.out.println(bstDistance.distance(arr, 2, 6)); // 4
        System.out.println(bstDistance.distance(arr, 2, 4)); // 3
    }
}
