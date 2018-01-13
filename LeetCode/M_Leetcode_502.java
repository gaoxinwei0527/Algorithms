package LeetCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 502. IPO

 Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.

 You are given several projects. For each project i, it has a pure profit Pi and a minimum capital of Ci is needed to start the corresponding project. Initially, you have W capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.

 To sum up, pick a list of at most k distinct projects from given projects to maximize your final capital, and output your final maximized capital.

 Example 1:
 Input: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].

 Output: 4

 Explanation: Since your initial capital is 0, you can only start the project indexed 0.
 After finishing it you will obtain profit 1 and your capital becomes 1.
 With capital 1, you can either start the project indexed 1 or the project indexed 2.
 Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
 Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
 Note:
 You may assume all numbers in the input are non-negative integers.
 The length of Profits array and Capital array will not exceed 50,000.
 The answer is guaranteed to fit in a 32-bit signed integer.
 */
public class M_Leetcode_502 {
    private static class Pair{
        public int profit;
        public int capital;
        Pair(int profit, int capital){
            this.profit = profit;
            this.capital = capital;
        }
    }

    /**
     * @param k
     * @param W
     * @param Profits
     * @param Capital
     * @return
     *
     * Greedy- Always try project with max profit and min required capital
     */
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        PriorityQueue<Pair> q = new PriorityQueue<>(Profits.length, new Comparator<Pair>(){
            @Override
            public int compare(Pair a, Pair b){
                if(a.profit != b.profit){
                    return b.profit - a.profit;
                }

                return a.capital - b.capital;
            }
        });

        for(int i = 0; i < Profits.length; i++){
            Pair p = new Pair(Profits[i], Capital[i]);
            q.offer(p);
        }

        int count = 0;
        while(count < k){
            int cur = -1;
            List<Pair> visited = new ArrayList<>();
            while(!q.isEmpty()){
                if(q.peek().capital > W){
                    visited.add(q.poll());
                }else{
                    cur = q.poll().profit;
                    break;
                }
            }

            if(cur == -1){
                return W;
            }

            W += cur;
            count++;
            for(Pair p : visited){
                q.offer(p);
            }
        }

        return W;
    }
}
