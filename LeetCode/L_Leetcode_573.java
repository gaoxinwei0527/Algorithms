package LeetCode;

/**
 573. Squirrel Simulation

 There's a tree, a squirrel, and several nuts. Positions are represented by the cells in a 2D grid. Your goal is to find the minimal distance for the squirrel to collect all the nuts and put them under the tree one by one. The squirrel can only take at most one nut at one time and can move in four directions - up, down, left and right, to the adjacent cell. The distance is represented by the number of moves.
 Example 1:
 Input:
 Height : 5
 Width : 7
 Tree position : [2,2]
 Squirrel : [4,4]
 Nuts : [[3,0], [2,5]]
 Output: 12
 Explanation:

 Note:
 All given positions won't overlap.
 The squirrel can take at most one nut at one time.
 The given positions of nuts have no order.
 Height and width are positive integers. 3 <= height * width <= 10,000.
 The given positions contain at least one nut, only one tree and one squirrel.
 */
public class L_Leetcode_573 {
    /**
     * @param height
     * @param width
     * @param tree
     * @param squirrel
     * @param nuts
     * @return
     *
     * the only thing we need to care is the first pick. All remaining picks are just round-trip from tree to nut.
     * we need to minimize (dis(squirrel, first_nut) - dis(first_nut, tree)). If this value is negative, means we actually saved some distance (compared to round-trip between tree & nut)
     */
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int res = 0;
        int save = Integer.MAX_VALUE;
        for(int[] nut : nuts){
            int dis_1 = Math.abs(nut[0] - tree[0]) + Math.abs(nut[1] - tree[1]);
            int dis_2 = Math.abs(nut[0] - squirrel[0]) + Math.abs(nut[1] - squirrel[1]);
            save = Math.min(save, dis_2 - dis_1);
            res += (2 * dis_1);
        }

        res += save;
        return res;
    }
}
