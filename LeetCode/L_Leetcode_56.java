package LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 56. Merge Intervals

 Given a collection of intervals, merge all overlapping intervals.

 For example,
 Given [1,3],[2,6],[8,10],[15,18],
 return [1,6],[8,10],[15,18].
 */
public class L_Leetcode_56 {
    private static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    /**
     * @param intervals
     * @return
     *
     * sort and merge
     *
     * Time - O(nlogn)
     * Space - O(n)
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if(intervals.isEmpty()) return res;
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b){
                if(a.start !=  b.start) return a.start - b.start;
                return a.end - b.end;
            }
        });

        Interval next = intervals.get(0);
        for(int i = 1; i < intervals.size(); i++){
            Interval tmp = intervals.get(i);
            if(tmp.start <= next.end){
                next.end = Math.max(next.end, tmp.end);
            }else{
                res.add(next);
                next = tmp;
            }
        }
        res.add(next);
        return res;
    }
}
