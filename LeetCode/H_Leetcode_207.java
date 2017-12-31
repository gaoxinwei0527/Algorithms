package LeetCode;

import java.util.*;

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
public class H_Leetcode_207 {
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
        for(int i = 0; i < prerequisites.length; i++){
            int a = prerequisites[i][1];
            int b = prerequisites[i][0];
            if(!out.containsKey(a)) out.put(a, new HashSet<>());
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
}
