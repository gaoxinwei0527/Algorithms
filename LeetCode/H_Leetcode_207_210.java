package LeetCode;

import java.util.*;
public class H_Leetcode_207_210 {
    /**
     207. Course Schedule

     There are a total of n courses you have to take, labeled from 0 to n - 1.

     Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

     Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

     For example:

     2, [[1,0]]
     There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

     2, [[1,0],[0,1]]
     There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
     */

    /**
     * @param numCourses
     * @param prerequisites
     * @return
     *
     * topo sort, this approach is kinda overkill, because it can actually give the order of courses.
     * this question is basically asking if there is a cycle or not.
     *
     * Time - O(V + E)
     * Space - O(V + E)
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];
        Map<Integer, Set<Integer>> out = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int a = prerequisite[1];
            int b = prerequisite[0];
            if (!out.containsKey(a)) out.put(a, new HashSet<>());
            in[b]++;
            out.get(a).add(b);
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(in[i] == 0) q.offer(i);
        }

        int count = 0;
        while(!q.isEmpty()){
            int next = q.poll();
            count++;
            for(int k : out.getOrDefault(next, new HashSet<>())){
                in[k]--;
                if(in[k] == 0) q.offer(k);
            }
        }

        return (count == numCourses);
    }

    /**
     * @param numCourses
     * @param prerequisites
     * @return
     *
     * Incorrect but fast way, kinda like union find, this can pass leetcode but actually wrong.
     * E.g. it cannot detect cycle in {{1, 0}, {1, 2}, {0, 1}} because it only record direct parent in directed graph.
     * This direct parent could be overwritten.
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        int[] parent = new int[numCourses];
        for(int i = 0; i < parent.length; i++) parent[i] = i;

        for(int i = 0; i < prerequisites.length; i++){
            int a = prerequisites[i][1];
            int b = prerequisites[i][0];
            if(a == b) return false;
            int tmp = a;
            while(parent[tmp] != tmp){
                tmp = parent[tmp];
                if(tmp == b) return false;
            }
            parent[b] = a;
        }

        return true;
    }

    /*
    210. Course Schedule II

    There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
click to show more hints.

Hints:
This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
    */

    /**
     * @param numCourses
     * @param prerequisites
     * @return
     *
     * same as 207, just return the topo order.
     *
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int[] in = new int[numCourses];
        Map<Integer, Set<Integer>> out = new HashMap<>();
        for(int[] pre : prerequisites){
            in[pre[0]]++;
            if(!out.containsKey(pre[1])) out.put(pre[1], new HashSet<>());
            out.get(pre[1]).add(pre[0]);
        }

        int k = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(in[i] == 0) q.offer(i);
        }

        while(!q.isEmpty()){
            int next = q.poll();
            res[k++] = next;
            for(int i : out.getOrDefault(next, new HashSet<>())){
                in[i]--;
                if(in[i] == 0) q.offer(i);
            }
        }

        if(k != numCourses) return new int[0];
        return res;
    }
}
