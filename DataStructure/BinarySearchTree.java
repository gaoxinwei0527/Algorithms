package DataStructure;

public class BinarySearchTree {

    /******************************************************************************/
    private static class NormalBSTNode{
        public int val;
        public NormalBSTNode left;
        public NormalBSTNode right;
        public NormalBSTNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    NormalBSTNode root = null;

    public void insert(int val){
        if(root == null){
            root = new NormalBSTNode(val);
        }else{
            NormalBSTNode cur = root;
            while(cur != null){
                if(cur.val == val){
                    return;
                }

                if(cur.val > val){
                    if(cur.left == null){
                        cur.left = new NormalBSTNode(val);
                        return;
                    }else{
                        cur = cur.left;
                    }
                }else{
                    if(cur.right == null){
                        cur.right = new NormalBSTNode(val);
                        return;
                    }else{
                        cur = cur.right;
                    }
                }
            }
        }
    }

    public NormalBSTNode search(int target){
        NormalBSTNode cur = root;
        while(cur != null){
            if(cur.val == target) return cur;
            if(cur.val < target){
                cur = cur.right;
            }else{
                cur = cur.left;
            }
        }
        return null; // not found;
    }
    /******************************************************************************/

    
}
