package LeetCode;

/**
 685. Redundant Connection II

 In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

 The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

 The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.

 Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

 Example 1:
 Input: [[1,2], [1,3], [2,3]]
 Output: [2,3]
 Explanation: The given directed graph will be like this:
 1
 / \
 v   v
 2-->3
 Example 2:
 Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 Output: [4,1]
 Explanation: The given directed graph will be like this:
 5 <- 1 -> 2
 ^    |
 |    v
 4 <- 3
 Note:
 The size of the input 2D-array will be between 3 and 1000.
 Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 */
public class H_Leetcode_685 {
    /**
     * @param edges
     * @return
     *
     * use union find.
     *
     * two cases can violate the tree rule-
     * 1. node with more than 1 in-degree
     * 2. loop
     *
     * so we need to make sure what is the case or both of them happened.
     *
     * 1. if we get node with more than 1 in-degree,
     *   1.1 if loop already happened, means it's caused by previous in-degree, we can return now.
     *   1.2 else, record both in-degree edge, and skip setting the second in-degree edge in union find
     * 2. if we get loop (root1 == root2)
     *   2.1 if we already recorded node with multiple in-degree, then we can return the first in-degree edge, because we already skipped the second in-degree edge.
     *   2.2 else, just record the edge causing loop.
     * 3. if we reach the end, means only 1 case is happening-
     *   3.1 if we have recorded multiple in-degree node, return the second in-degree edge
     *   3.2 else return the edge causing loop.
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int N = edges.length;
        int[] parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;

        int[] c1 = new int[]{-1, -1};
        int[] c2 = new int[]{-1, -1};
        int[] loop = new int[]{-1, -1};

        for(int[] edge : edges){
            if(parent[edge[1]] != edge[1]){
                c1 = new int[]{parent[edge[1]], edge[1]};
                if(loop[0] != -1) return c1;
                c2 = edge;
                continue;
            }

            int r1 = root(parent, edge[0]);
            int r2 = root(parent, edge[1]);
            if(r1 == r2) {
                if(c1[0] != -1) return c1;
                loop = edge;
                continue;
            }
            parent[edge[1]] = edge[0];
        }

        if(c2[0] != -1) return c2;
        return loop;
    }

    private int root(int[] parent, int i){
        while(parent[i] != i) i = parent[i];
        return i;
    }
}
