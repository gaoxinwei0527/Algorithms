package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 57. Insert Interval

 Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

 You may assume that the intervals were initially sorted according to their start times.

 Example 1:
 Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

 Example 2:
 Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

 This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
public class M_Leetcode_57 {
    private static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    /**
     * @param intervals
     * @param newInterval
     * @return
     *
     * simple insertion
     * Time - O(n)
     * Space - O(1)
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        int i = 0;
        int n = intervals.size();
        while(i < n && intervals.get(i).end < newInterval.start) res.add(intervals.get(i++));
        while(i < n && intervals.get(i).start <= newInterval.end){
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            i++;
        }

        res.add(newInterval);
        while(i < n) res.add(intervals.get(i++));
        return res;
    }
}
