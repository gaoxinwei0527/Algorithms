package LeetCode;

/**
 421. Maximum XOR of Two Numbers in an Array

 Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 2^31.

 Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

 Could you do this in O(n) runtime?

 Example:

 Input: [3, 10, 5, 25, 2, 8]

 Output: 28

 Explanation: The maximum result is 5 ^ 25 = 28.
 */
public class H_Leetcode_421 {
    private static class TrieNode{
        public TrieNode[] children = new TrieNode[2];
        public int val = 0;
    }

    TrieNode root = new TrieNode();

    /**
     * @param nums
     * @return
     *
     * parse all nums into a trie with each node as a bit, the level of the trie would be fixed to 32.
     * then for each num i, try to always search for reversed bit (e.g. 0->1, 1->0) and xor with the found num j, which should be max xor pair for i.
     *
     * Time - O(n)
     */
    public int findMaximumXOR(int[] nums) {
        for(int num : nums) add(num);

        int max = 0;
        for(int num : nums){
            TrieNode cur = root;
            for(int i = 31; i >= 0; i--){
                int k = (num >>> i) & 1;
                if(k == 1 && cur.children[0] != null) cur = cur.children[0];
                else if(k == 0 && cur.children[1] != null) cur = cur.children[1];
                else cur = cur.children[k];
            }

            max = Math.max(max, num ^ cur.val);
        }

        return max;
    }

    private void add(int num){
        TrieNode cur = root;
        for(int i = 31; i >= 0; i--){
            int k = (num >>> i) & 1;
            if(cur.children[k] == null) cur.children[k] = new TrieNode();
            cur = cur.children[k];
        }
        cur.val = num;
    }
}
